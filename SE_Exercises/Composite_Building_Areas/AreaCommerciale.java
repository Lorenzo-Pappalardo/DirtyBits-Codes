package Composite_Building_Areas;

/** Leaf */
public class AreaCommerciale implements Area {
    private String name;

    public AreaCommerciale(String name) {
        this.name = name;
    }

    @Override
    public void printName(int indentSize) {
        for (int i = 0; i < indentSize; i++)
            System.out.print('\t');
        // System.out.println(getClass().getSimpleName());
        System.out.println(name);
    }

    @Override
    public void add(Area a) {
    }

    @Override
    public void remove(Area a) {

    }
}