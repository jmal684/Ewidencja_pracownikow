package model;

public class Dyrektor extends Pracownik{
    private int dodatekSluzbowy, limitKosztow;
    private String numerKartySluzbowej;

    public Dyrektor(){}

    public int getDodatekSluzbowy() {
        return dodatekSluzbowy;
    }

    public int getLimitKosztow() {
        return limitKosztow;
    }

    public String getNumerKartySluzbowej() {
        return numerKartySluzbowej;
    }

    public void setDodatekSluzbowy(int dodatekSluzbowy) {
        this.dodatekSluzbowy = dodatekSluzbowy;
    }

    public void setLimitKosztow(int limitKosztow) {
        this.limitKosztow = limitKosztow;
    }

    public void setNumerKartySluzbowej(String numerKartySluzbowej) {
        this.numerKartySluzbowej = numerKartySluzbowej;
    }

}
