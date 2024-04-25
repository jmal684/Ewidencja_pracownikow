package main;
import controller.Controller;
import model.Model;
import view.View;

public class Main {
    public static void main(String[] args) {
        int wybor = 0;
        boolean czyZakonczono = false;
        Model model = new Model();

        while(!czyZakonczono){
            View.WyswietlMenuGlowne();
            wybor = Controller.takeUserInput(1,5);

            switch (wybor){
                case 1:
                    View.wyswieltPracownikow(model.getListaPracownikow());
                    break;
                case 2:
                    model.dodajPracownika();
                    break;
                case 3:
                    model.usunPracownika();
                    break;
                case 4:
                    model.kopiaZapasowa();
                    break;
                case 5:
                    czyZakonczono = true;
                    break;
            }
        }
    }
}
