package model;

public class Handlowiec extends Pracownik{
    private int prowizja, limitProwizji;

    public int getProwizja() {
        return prowizja;
    }

    public int getLimitProwizji() {
        return limitProwizji;
    }

    public Handlowiec() {}
    public void setProwizja(int prowizja) {
        this.prowizja = prowizja;
    }

    public void setLimitProwizji(int limitProwizji) {
        this.limitProwizji = limitProwizji;
    }


}
