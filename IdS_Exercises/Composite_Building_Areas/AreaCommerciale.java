package Composite_Building_Areas;

public class AreaCommerciale implements Area {
    private int ID;
    private String name;

    public AreaCommerciale(int ID, String name) {
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