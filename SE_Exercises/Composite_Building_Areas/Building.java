package Composite_Building_Areas;

import java.util.List;
import java.util.ArrayList;

/** Composite */
public class Building implements Area {
    private String name;
    private List<Area> areasInTheBuilding;

    public Building(String name) {
        this.name = name;
        this.areasInTheBuilding = new ArrayList<>();
    }

    @Override
    public void printName(int indentSize) {
        for (int i = 0; i < indentSize; i++)
            System.out.print('\t');
        System.out.println(name + "----");
        areasInTheBuilding.stream().forEach(area -> area.printName(indentSize + 1));
        for (int i = 0; i < indentSize; i++)
            System.out.print('\t');
        for (int i = 0; i < name.length() + 4; i++)
            System.out.print('-');
        System.out.println();
    }

    @Override
    public void add(Area a) {
        areasInTheBuilding.add(a);
    }

    @Override
    public void remove(Area a) {
        areasInTheBuilding.remove(a);
    }
}