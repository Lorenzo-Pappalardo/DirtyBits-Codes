package Chain_of_Responsiblity_Army;

public class Main {
    public static void main(String[] args) {
        Officer general = new General();
        Officer major = new Major();
        Officer lieutenant = new Lieutenant();
        Officer colonnel = new Colonnel();
        Officer captain = new Captain();

        captain.setSuperior(major);
        major.setSuperior(lieutenant);
        lieutenant.setSuperior(colonnel);
        colonnel.setSuperior(general);
        general.setSuperior(null);

        System.out.println("Who gets at least 3000?");
        captain.check(3000);
        System.out.println("Who gets at least 1000?");
        captain.check(1000);
        System.out.println("Who gets at least 10000?");
        captain.check(10000);
    }
}