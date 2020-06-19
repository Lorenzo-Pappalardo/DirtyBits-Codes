package Chain_of_Responsiblity_Army;

public abstract class Officer {
    protected Officer superior;

    public void setSuperior(Officer officer) {
        superior = officer;
    }

    public abstract void check(int salary);
}