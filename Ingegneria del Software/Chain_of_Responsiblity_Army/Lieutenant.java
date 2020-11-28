package Chain_of_Responsiblity_Army;

public class Lieutenant extends Officer {
    @Override
    public void check(int salary) {
        if (salary > 3000)
            superior.check(salary);
        else
            System.out.println(getClass().getSimpleName());
    }
}