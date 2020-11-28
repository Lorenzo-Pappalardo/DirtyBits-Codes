package Chain_of_Responsiblity_Army;

public class Major extends Officer {
    @Override
    public void check(int salary) {
        if (salary > 2000)
            superior.check(salary);
        else
            System.out.println(getClass().getSimpleName());
    }
}