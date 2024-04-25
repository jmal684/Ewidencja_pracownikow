package model;
import controller.Controller;
import view.View;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.zip.*;

public class Model {
    private LinkedHashMap<String, Pracownik> listaPracownikow = new LinkedHashMap<>();

    public LinkedHashMap<String, Pracownik> getListaPracownikow() {
        return listaPracownikow;
    }

    private final String PLIK_SERIALIZACJI = "Lista.txt";
    private final String PLIK_SERIALIZACJI_DEKOM = "Lista2.txt";

    public void dodajPracownika(){
        System.out.println("\n");

        char wybor = Controller.wyborZnakowy("[D]yrektor/[H]handlowiec: ", 'D', 'H');
        System.out.println("------------------------------------------------------------------");

        boolean czyPodanoPoprawnyPesel = false;
        String pesel = " ";

        while(!czyPodanoPoprawnyPesel)
        {
            pesel = Controller.pobierzPesel();

            if(sprawdzPesel(pesel))
            {
                czyPodanoPoprawnyPesel = true;
            }

        }

        if(wybor == 'D')
        {
            Dyrektor dyrektor = Controller.pobierzDaneDyrektora(pesel);
            System.out.println("------------------------------------------------------------------");

            wybor = Controller.wyborZnakowy("[Z]apisz/[P]orzuc: ", 'Z', 'P');

            if(wybor == 'Z')
            {
                listaPracownikow.put(dyrektor.getPesel(), dyrektor);
                System.out.println("Pomyslnie dodano pracownika.");
                return;
            }
            else
            {
                System.out.println("Anulowano dodawanie pracownika.");
                return;
            }
            
        }
        else
        {
            Handlowiec handlowiec = Controller.pobierzDaneHandlowca(pesel);
            System.out.println("------------------------------------------------------------------");

            wybor = Controller.wyborZnakowy("[Z]apisz/[P]orzuc: ", 'Z', 'P');

            if(wybor == 'Z')
            {
                listaPracownikow.put(handlowiec.getPesel(), handlowiec);
                System.out.println("Pomyslnie dodano pracownika.");
                return;
            }
            else
            {
                System.out.println("Anulowano dodawanie pracownika.");
                return;
            }
        }

    }

    public void usunPracownika(){
        String pesel = Controller.pobierzPesel();
        Pracownik pracownik =  wyszukajPracownika(pesel);
        char wybor = ' ';

        if(pracownik == null)
        {
            System.err.println("Blad: Pracownik o podanym PESELu nie istnieje.");
            return;
        }

        System.out.println("------------------------------------------------------------------");

        if(pracownik instanceof Dyrektor)
        {
            View.wyswietlDaneDyrektora((Dyrektor) pracownik);
        }
        else if (pracownik instanceof Handlowiec)
        {
            View.wyswietlDaneHandlowca((Handlowiec) pracownik);
        }

        System.out.println("------------------------------------------------------------------");

        wybor = Controller.wyborZnakowy("[U]sun/[P]orzuc: ", 'U', 'P');

        if(wybor == 'U')
        {
            listaPracownikow.remove(pracownik.getPesel());
            System.out.println("Pomyslnie usunieto pracownika.");
            return;
        }


    }
    public Pracownik wyszukajPracownika(String pesel){
        return listaPracownikow.get(pesel);
    }

    public void kopiaZapasowa(){
        char wybor = Controller.wyborZnakowy("[Z]achowaj/[O]dtwórz: ", 'Z', 'O');
        char wybor2 = ' ';
        String nazwaPliku = "";
        System.out.println("------------------------------------------------------------------");

        if (wybor == 'Z')
        {

            nazwaPliku = Controller.pobierzNazwePliku();
            String rozszerzenie = Ut.pobierzRozszerzeniePliku(nazwaPliku);

            if(rozszerzenie == null && rozszerzenie != "gzip" && rozszerzenie != "zip" )
            {
                wybor = Controller.wyborZnakowy("Kompresja [G]zip/[Z]ip: ", 'G', 'Z');

                if(wybor == 'G')
                {
                    rozszerzenie = "gzip";
                }
                else
                {
                    rozszerzenie = "zip";
                }

                nazwaPliku = nazwaPliku.concat("." + rozszerzenie);
            }
            System.out.println("------------------------------------------------------------------");

            wybor2 = Controller.wyborZnakowy("[Z]apisz/[P]orzuc: ", 'Z', 'P');
            if(wybor2 == 'P')
            {
                return;
            }

            serializujPracownikow();

            if(rozszerzenie.equals("gzip"))
            {
                kompresjaGzip(nazwaPliku);

            }
            else if(rozszerzenie.equals("zip"))
            {
                kompresjaZip(nazwaPliku);
            }
        }
        else if (wybor == 'O')
        {
            nazwaPliku = Controller.pobierzNazwePliku();
            String rozszerzenie = Ut.pobierzRozszerzeniePliku(nazwaPliku);

            if(rozszerzenie == null && rozszerzenie != "gzip" && rozszerzenie != "zip" )
            {
                wybor = Controller.wyborZnakowy("Dekompresja [G]zip/[Z]ip: ", 'G', 'Z');

                if(wybor == 'G')
                {
                    rozszerzenie = "gzip";
                }
                else
                {
                    rozszerzenie = "zip";
                }

                nazwaPliku = nazwaPliku.concat("." + rozszerzenie);
            }
            
            if(!czyPlikIstnieje(nazwaPliku))
            {
            	System.err.println("Blad: Podany plik nieistnieje.");
            	return;
            }
            
            if(rozszerzenie.equals("gzip"))
            {
                dekompresjaGzip(nazwaPliku);
                deserializujPracownikow();

            }
            else if(rozszerzenie.equals("zip"))
            {
                dekomprezjaZip(nazwaPliku);
                deserializujPracownikow();
            }
        }
    }

    private void kompresjaGzip(String nazwaPliku){
        BufferedInputStream in;
        BufferedOutputStream out;
        try {
             in = new BufferedInputStream(new FileInputStream(PLIK_SERIALIZACJI));
             out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(nazwaPliku)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int c;
        while(true)
        {
            try {
                if (!((c = in.read()) != -1))
                    break;

                out.write(c);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            in.close();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    private void dekompresjaGzip(String nazwaPliku){
        try (GZIPInputStream gis = new GZIPInputStream(
                new FileInputStream(nazwaPliku));
             FileOutputStream fos = new FileOutputStream(PLIK_SERIALIZACJI_DEKOM)) {


            byte[] buffer = new byte[1024];
            int len;
            while ((len = gis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void kompresjaZip(String nazwaPliku){
        try(ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(nazwaPliku));
        FileInputStream fIS = new FileInputStream(PLIK_SERIALIZACJI)){
            ZipEntry zipEntry = new ZipEntry(PLIK_SERIALIZACJI);
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int l;

            while((l = fIS.read(bytes)) >= 0)
            {
                zipOut.write(bytes,0,l);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void dekomprezjaZip(String nazwaPliku){
        try(ZipInputStream zis = new ZipInputStream(new FileInputStream(nazwaPliku));
        FileOutputStream fos = new FileOutputStream(PLIK_SERIALIZACJI_DEKOM))
        {
            byte[] buffer = new byte[1024];
            ZipEntry zipEntry = zis.getNextEntry();

            while(zipEntry != null){
                int l;
                while((l = zis.read(buffer)) > 0)
                {
                    fos.write(buffer,0,l);
                }
                zipEntry = zis.getNextEntry();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void deserializujPracownikow() {
        LinkedHashMap<String, Pracownik> nowaListaPracownikow = null;

        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(PLIK_SERIALIZACJI_DEKOM))){
            nowaListaPracownikow = (LinkedHashMap) in.readObject();
            listaPracownikow = nowaListaPracownikow;
        } catch (FileNotFoundException e) {
            System.err.println("Blad: Nie odnaleziono pliku.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    private void serializujPracownikow(){
        try(ObjectOutputStream ou = new ObjectOutputStream(new FileOutputStream(PLIK_SERIALIZACJI))){
            ou.writeObject(listaPracownikow);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean sprawdzPesel(String pesel){
        //TODO zmien komunikaty
        if(pesel.length() != 11){
            System.err.println("Blad: Nieprawidlowa dlugosc PESELU");
            return false;
        }
        if(sprawdzSumePesel(pesel)){
            if(czyPeselUnikalny(pesel))
            {
                return true;
            }

            System.err.println("Blad: Podany PESEL jest juz w bazie danych. ");
            return false;

        }
        else{
            System.err.println("Blad: Nieprawidlowa suma kontrolna PESELU");
            return false;
        }
    }
  private  boolean sprawdzSumePesel(String pesel){
        return (pesel.charAt( 0 ) + 3*pesel.charAt( 1 ) + 7*pesel.charAt( 2 ) + 9*pesel.charAt( 3 ) + pesel.charAt( 4 ) + 3*pesel.charAt( 5 ) +
                7*pesel.charAt( 6 ) + 9*pesel.charAt( 7 ) + pesel.charAt( 8 ) + 3*pesel.charAt( 9 ) + pesel.charAt( 10 )) % 10 == 0;
    }
  private boolean czyPeselUnikalny(String pesel){
        if(listaPracownikow.get(pesel) == null)
            return true;

        return false;
    }
  
  private boolean czyPlikIstnieje(String nazwaPliku){
	  File f = new File(nazwaPliku);
	  if(f.exists() && !f.isDirectory())
		  return true;
	  return false;
  }
}
