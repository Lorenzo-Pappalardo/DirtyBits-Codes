package Composite;

public class Main {
    public static void main(String[] args) {
        Area areaRicerca = new AreaRicerca(1, "Area Ricerca");
        Area areaSviluppo = new AreaSviluppo(2, "Area Sviluppo");
        Area areaCommerciale = new AreaCommerciale(3, "Area Commerciale");
        Area areaFinanziaria = new AreaFinanziaria(4, "Area Finanziaria");

        Area ricercaESviluppo = new TestArea(5, "Area Ricerca e Sviluppo");
        ricercaESviluppo.add(areaRicerca);
        ricercaESviluppo.add(areaSviluppo);

        Area areaRoot = new TestArea(0, "Area Generale");
        areaRoot.add(areaFinanziaria);
        areaRoot.add(areaCommerciale);
        areaRoot.add(ricercaESviluppo);

        areaRoot.printName();
    }
}