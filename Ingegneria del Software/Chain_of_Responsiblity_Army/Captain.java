package Chain_of_Responsiblity_Army;

public class Captain extends Officer {
    @Override
    public void check(int salary) {
        if (salary > 1500)
            superior.check(salary);
        else
            System.out.println(getClass().getSimpleName());
    }
}