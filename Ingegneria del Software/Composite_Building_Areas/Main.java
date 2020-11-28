package Composite_Building_Areas;

public class Main {
    public static void main(String[] args) {
        Area areaRicerca = new AreaRicerca("Research Area");
        Area areaSviluppo = new AreaSviluppo("Development Area");
        Area areaCommerciale = new AreaCommerciale("Commercial Area");
        Area areaFinanziaria = new AreaFinanziaria("Financial Area");

        Area ricercaESviluppo = new Building("Research & Development Area");
        ricercaESviluppo.add(areaRicerca);
        ricercaESviluppo.add(areaSviluppo);

        Area areaRoot = new Building("Company");
        areaRoot.add(areaFinanziaria);
        areaRoot.add(areaCommerciale);
        areaRoot.add(ricercaESviluppo);

        areaRoot.printName(0);
    }
}