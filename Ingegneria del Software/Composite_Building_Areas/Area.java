package Composite_Building_Areas;

/** Component */
public interface Area {
    /** Prints Area's name */
    public void printName(int indentSize);

    /**
     * Adds a new Area to the list of areas in the Composite
     * 
     * @param a : Area
     */
    public void add(Area a);

    /**
     * Removes an Area from the list of areas in the Composite
     * 
     * @param a : Area
     */
    public void remove(Area a);
}