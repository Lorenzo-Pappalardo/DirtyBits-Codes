package Composite;

public class AreaSviluppo implements Area {
    private int ID;
    private String name;

    public AreaSviluppo(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(getClass().getSimpleName());
    }

    @Override
    public void add(Area a) {
    }

    @Override
    public void remove(Area a) {

    }

    public int getID() {
        return ID;
    }
}