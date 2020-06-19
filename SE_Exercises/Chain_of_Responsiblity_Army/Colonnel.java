package Chain_of_Responsiblity_Army;

public class Colonnel extends Officer {
    @Override
    public void check(int salary) {
        if (salary > 3500)
            superior.check(salary);
        else
            System.out.println(getClass().getSimpleName());
    }
}