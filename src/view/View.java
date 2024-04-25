package view;
import controller.Controller;
import model.*;

import java.util.*;

public class View {
    public static void WyczyscEkran()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void WyswietlMenuGlowne(){
        System.out.println("\n\nMENU");
        System.out.println("1. Lista pracownikow");
        System.out.println("2. Dodaj pracownika");
        System.out.println("3. Usun pracownika");
        System.out.println("4. Kopia zapasowa");
        System.out.println("5. Wyjscie");
    }
    public static void wyswieltPracownikow(LinkedHashMap<String, Pracownik> listaPracownikow){
        if(listaPracownikow.size() <= 0)
        {
            System.out.println("Lista jest pusta.");
            return;
        }

        boolean czyPrzeglada = true;
        int aktualnaPozycja = 1;
        char wybor = ' ';


        while(czyPrzeglada)
        {
            for (String pesel: listaPracownikow.keySet())
            {
                System.out.printf("\n[Rekord %d/%d]\n", aktualnaPozycja, listaPracownikow.size());

                if(listaPracownikow.get(pesel) instanceof  Dyrektor)
                {
                    wyswietlDaneDyrektora((Dyrektor) listaPracownikow.get(pesel));
                }
                else if(listaPracownikow.get(pesel) instanceof Handlowiec)
                {
                    wyswietlDaneHandlowca((Handlowiec) listaPracownikow.get(pesel));
                }

                wybor = Controller.wyborZnakowy("[N]astepny/[P]owrot: ", 'N', 'P');

                if(wybor == 'N')
                {
                    ++aktualnaPozycja;
                }
                else
                {
                    czyPrzeglada = false;
                    break;
                }

            }
            aktualnaPozycja = 1;
        }


    }
    public static void wyswietlDaneDyrektora(Dyrektor dyrektor){
        System.out.printf("%-30s: %s\n", "Identyfikator PESEL", dyrektor.getPesel());

        System.out.printf("%-30s: %s\n", "Imie", dyrektor.getImie());

        System.out.printf("%-30s: %s\n", "Nazwisko", dyrektor.getNazwisko());

        System.out.printf("%-30s: %s\n", "Stanowisko", dyrektor.getStanowisko());

        System.out.printf("%-30s: %s\n", "Wynagrodzenie (zl)", dyrektor.getWynagrodzenie());

        System.out.printf("%-30s: %s\n", "Telefon sluzbowy numer", dyrektor.getTelefon());

        System.out.printf("%-30s: %s\n", "Dodatek sluzbowy (zl)", dyrektor.getDodatekSluzbowy());

        System.out.printf("%-30s: %s\n", "Karta sluzbowa numer", dyrektor.getNumerKartySluzbowej());

        System.out.printf("%-30s: %s\n\n", "Limit kosztow/miesiac (zl)", dyrektor.getLimitKosztow());
    }
    public static void wyswietlDaneHandlowca(Handlowiec handlowiec){
        System.out.printf("%-30s: %s\n", "Identyfikator PESEL", handlowiec.getPesel());

        System.out.printf("%-30s: %s\n", "Imie", handlowiec.getImie());

        System.out.printf("%-30s: %s\n", "Nazwisko", handlowiec.getNazwisko());

        System.out.printf("%-30s: %s\n", "Stanowisko", handlowiec.getStanowisko());

        System.out.printf("%-30s: %s\n", "Wynagrodzenie (zl)", handlowiec.getWynagrodzenie());

        System.out.printf("%-30s: %s\n", "Telefon sluzbowy numer", handlowiec.getTelefon());

        System.out.printf("%-30s: %s\n", "Prowizja (%)", handlowiec.getProwizja());

        System.out.printf("%-30s: %s\n\n", "Limit prowizji/miesiac (%)", handlowiec.getLimitProwizji());
    }
}
