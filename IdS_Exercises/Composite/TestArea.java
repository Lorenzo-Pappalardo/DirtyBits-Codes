package Composite;

import java.util.ArrayList;

public class TestArea implements Area {
    private int ID;
    private String name;
    private ArrayList<Area> children;
    public TestArea(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.children = new ArrayList<Area>();
    }

    @Override
    public void printName() {
        children.forEach(Area::printName);
    }

    @Override
    public void add(Area a) {
        children.add(a);
    }

    @Override
    public void remove(Area a) {
        children.remove(a);
    }
}