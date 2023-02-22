package org.fitog4.view;

class ShellResourceChangeRegexHelperTest {

  // todo filippo switch to junit in maven
  public static void main(String[] args) {
    System.out.println(new ShellRegexHelper().parseAsResourceChange("+112e -2t +9S, -3E +3p -+4h").toString());

    System.out.println(new ShellRegexHelper().parseAsAllocation("8 4stn 5n 3st 1n, 20n+26tn3"));
  }
}
