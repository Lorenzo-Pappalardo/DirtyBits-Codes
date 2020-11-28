package Decorator_Hamburger;

public class Main {
    public static void main(String[] args) {
        IComponent hamburger = new Hamburger();

        System.out.println("Panino scelto" + hamburger.getProductName() + ", costo: "
                + String.format("%.2f", hamburger.getPrice()));

        IComponent hamburgerConBarbecue = new ExtraBarbecue(hamburger);
        IComponent hamburgerConBarbecueECetriolo = new ExtraCetriolo(hamburgerConBarbecue);

        System.out.println("Panino scelto: " + hamburgerConBarbecueECetriolo + ", costo: "
                + String.format("%.2f", hamburgerConBarbecueECetriolo.getPrice()));

        IComponent hamburgerConBarbecueDoppiaECetriolo = new ExtraBarbecue(hamburgerConBarbecueECetriolo);

        System.out.println("Panino scelto: " + hamburgerConBarbecueECetriolo + ", costo: "
                + String.format("%.2f", hamburgerConBarbecueDoppiaECetriolo.getPrice()));
    }
}