package controller;

import model.Dyrektor;
import model.Handlowiec;

import java.util.Scanner;
public class Controller {
    static int szerokosc = 20;
    static Scanner input = new Scanner(System.in);
    
    public static int takeUserInput(int rangeBegin, int rangeEnd ) {
        System.setErr(System.out);
        int option = 0;
        boolean isValid = false;

        do {
            System.out.print("Wybor> ");

            if(input.hasNextInt()){
                option = input.nextInt();
                if(option >= rangeBegin && option <= rangeEnd){
                    isValid = true;
                }
                else{
                    System.err.println("Blad: Wprowadzono niewlasciwe dane");
                }
            }
            else{
                System.err.println("Blad: Wprowadzono niewlasciwe dane");
            }

            input.nextLine();
        }while(!isValid);

        return option;
    }

    public static char wyborZnakowy(String wiadomosc, char opcja1, char opcja2){
        boolean poprawnyWybor = false;
        char wybor = ' ';

        while (!poprawnyWybor){
            System.out.print(wiadomosc);

            wybor = input.next().trim().charAt(0);

            if(Character.toUpperCase(wybor) == opcja1 || Character.toUpperCase(wybor) == opcja2)
                poprawnyWybor = true;
            else{
                System.err.println("Blad: Wprowadzono niewlasciwe dane. ");
                System.err.flush();
            }

            input.nextLine();
        }
        return Character.toUpperCase(wybor);
    }

    public static String pobierzPesel(){
        System.out.printf("%-30s: ", "Identyfikator PESEL");
        String pesel = input.nextLine();
        return pesel;
    }

    public static String pobierzNazwePliku(){
        System.out.printf("%-30s: ", "Podaj nazwe pliku");
        String nazwaPliku = input.nextLine();
        return nazwaPliku;
    }


    public static Dyrektor pobierzDaneDyrektora(String pesel){
       // input.nextLine();

        Dyrektor dyrektor = new Dyrektor();

        dyrektor.setPesel(pesel);

        System.out.printf("%-30s: ", "Imie");
        dyrektor.setImie(input.nextLine());

        System.out.printf("%-30s: ", "Nazwisko");
        dyrektor.setNazwisko(input.nextLine());

        System.out.printf("%-30s: ", "Wynagrodzenie (zl)");
        dyrektor.setWynagrodzenie(input.nextInt());
        input.nextLine();

        System.out.printf("%-30s: ", "Telefon sluzbowy numer");
        dyrektor.setTelefon(input.nextLine());

        System.out.printf("%-30s: ", "Dotatek sluzbowy (zl)");
        dyrektor.setDodatekSluzbowy(input.nextInt());
        input.nextLine();

        System.out.printf("%-30s: ", "Karta sluzbowa numer");
        dyrektor.setNumerKartySluzbowej(input.nextLine());

        System.out.printf("%-30s: ", "Limit kosztow/miesiac (zl)");
        dyrektor.setLimitKosztow(input.nextInt());
        input.nextLine();

        dyrektor.setStanowisko("Dyrektor");
        return dyrektor;
    }

    public static Handlowiec pobierzDaneHandlowca(String pesel){
        //input.nextLine();
        Handlowiec handlowiec = new Handlowiec();

        handlowiec.setPesel(pesel);

        System.out.printf("%-30s: ", "Imie");
        handlowiec.setImie(input.nextLine());

        System.out.printf("%-30s: ", "Nazwisko");
        handlowiec.setNazwisko(input.nextLine());

        System.out.printf("%-30s: ", "Wynagrodzenie (zl)");
        handlowiec.setWynagrodzenie(input.nextInt());
        input.nextLine();

        System.out.printf("%-30s: ", "Telefon sluzbowy numer");
        handlowiec.setTelefon(input.nextLine());

        System.out.printf("%-30s: ", "Prowizja (%)");
        handlowiec.setProwizja(input.nextInt());
        input.nextLine();

        System.out.printf("%-30s: ", "Limit prowizji/miesiac (zl)");
        handlowiec.setLimitProwizji(input.nextInt());
        input.nextLine();

        handlowiec.setStanowisko("Handlowiec");
        return handlowiec;
    }
}
