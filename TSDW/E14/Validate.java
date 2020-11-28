package E14;

interface Validate {
  public static boolean validate(String row) {
    boolean[] present = new boolean[10];
    for (int i = 0; i < 10; i++)
      present[i] = false;

    for (int i = 0; i < row.length(); i++) {
      int number = Character.getNumericValue(row.charAt(i));
      if (present[number])
        return false;
      present[number] = true;
    }

    return true;
  }

  public static void test() {
    String correctRow = "123456789";
    String wrongRow = "213612345";
    if (validate(correctRow) && !(validate(wrongRow)))
      System.out.println("It works! xD");
    else
      System.out.println("It doesn't work :c");
  }
}