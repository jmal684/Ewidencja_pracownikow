package model;


import java.io.Serializable;

public class Pracownik implements Serializable {
    private String pesel, imie, nazwisko, stanowisko, telefon;
    private int wynagrodzenie;

    public Pracownik(){}
    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public String getTelefon() {
        return telefon;
    }

    public int getWynagrodzenie() {
        return wynagrodzenie;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPesel() {
        return pesel;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setWynagrodzenie(int wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }

    boolean sprawdzSumePesel(String pesel){
        return (pesel.charAt( 0 ) + 3*pesel.charAt( 1 ) + 7*pesel.charAt( 2 ) + 9*pesel.charAt( 3 ) + pesel.charAt( 4 ) + 3*pesel.charAt( 5 ) +
                7*pesel.charAt( 6 ) + 9*pesel.charAt( 7 ) + pesel.charAt( 8 ) + 3*pesel.charAt( 9 ) + pesel.charAt( 10 )) % 10 == 0;
    }

}
