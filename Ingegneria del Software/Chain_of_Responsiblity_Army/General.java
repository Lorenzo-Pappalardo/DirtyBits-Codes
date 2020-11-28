package Chain_of_Responsiblity_Army;

public class General extends Officer {
    @Override
    public void check(int salary) {
        if (salary > 6000)
            System.err.println("Salary too high!");
        else
            System.out.println(getClass().getSimpleName());
    }
}