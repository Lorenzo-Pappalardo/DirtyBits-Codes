/*
  Implementare in C/RPC o Java/RMI, su localhost,
  un servizio costituito da una funzione/metodo hash() che:
  - Prende per unico argomento una stringa
  - Restituisce la somma dei codici ASCII dei caratteri dell'argomento stringa, modulo 26

  Scrivere un semplice cliente che invochi la funzione remota passandole come argomento il proprio cognome. 
*/

package E9;

interface Hash {
  public static int getHash(String surname) {
    int sum = 0;
    for (int i = 0; i < surname.length(); i++) {
      sum += surname.charAt(i);
    }
    return sum % 26;
  }
}
