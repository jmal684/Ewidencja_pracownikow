package model;

public class Ut {
    public static String pobierzRozszerzeniePliku(String nazwaPliku){
        int indx = nazwaPliku.lastIndexOf('.');
        String rozszerzenie = nazwaPliku.substring(indx+1);
        if(indx < 0 || rozszerzenie.length() <= 0)
        {
            return null;
        }
        return rozszerzenie;
    }
}
