import java.io.PrintStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

final class Logger {
  static boolean printLogs = false;

  private Logger() {
  }

  private static final void print(String severity, Object message) {
    if (printLogs) {
      PrintStream stream;

      if (severity.equals("WARN")) {
        stream = System.out;
      } else if (severity.equals("ERROR")) {
        stream = System.err;
      } else {
        stream = System.out;
      }

      stream.println('[' + severity + "] " + message);
    }
  }

  public static final void setLogging(boolean enabled) {
    printLogs = enabled;
  }

  public static final void printInfo(Object message) {
    print("INFO", message);
  }

  public static final void printWarning(Object message) {
    print("WARN", message);
  }

  public static final void printError(Object message) {
    print("ERROR", message);
  }
}

public class RSA {
  private static SecureRandom random = new SecureRandom();

  private RSA() {
    random.setSeed(new Date().getTime());
  }

  private static BigInteger generateCoprime(BigInteger phi) {
    BigInteger coprime = BigInteger.TWO;

    do {
      coprime = coprime.add(BigInteger.ONE);
    } while (!coprime.gcd(phi).equals(BigInteger.ONE));

    return coprime;
  }

  public static void main(String[] args) {
    Logger.setLogging(true);

    final int BIT_LENGTH = 2048;

    // BigInteger p = BigInteger.valueOf(31);
    BigInteger p = BigInteger.probablePrime(BIT_LENGTH / 2, random);
    // BigInteger q = BigInteger.valueOf(19);
    BigInteger q = BigInteger.probablePrime(BIT_LENGTH / 2, random);
    Logger.printInfo("p: " + p);
    Logger.printInfo("q: " + q);

    BigInteger n = p.multiply(q);
    BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    Logger.printInfo("n: " + n);
    Logger.printInfo("phi(n): " + phi);

    BigInteger e = generateCoprime(phi);
    Logger.printInfo("e: " + e);

    BigInteger d = e.modInverse(phi);
    Logger.printInfo("d: " + d);

    // BigInteger message = new BigInteger(random.nextInt(100), random);
    String message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
    Logger.printInfo("message: " + message);

    // BigInteger encrypted = message.modPow(e, n);
    BigInteger encrypted = new BigInteger(message.getBytes()).modPow(e, n);
    Logger.printInfo("encrypted: " + encrypted);

    // BigInteger decrypted = encrypted.modPow(d, n);
    String decrypted = new String(encrypted.modPow(d, n).toByteArray());
    Logger.printInfo("decrypted: " + decrypted);
  }
}