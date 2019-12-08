package ibanvalidacija;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Validatorius {

    boolean validuotiKoda(String kodas) {
        kodas = kodas.replaceAll("\\s", "");
        if (kodas.length() < 15 || kodas.length() > 34) {
            return false;
        }

        kodas = kodas.substring(4) + kodas.substring(0, 4);
        long suma = 0;

        for (int i = 0; i < kodas.length(); i++) {
            int simbolioSkaitineVerte = Character.getNumericValue(kodas.charAt(i));
            if (simbolioSkaitineVerte < 0 || simbolioSkaitineVerte > 35) {
                return false;
            }

            if (simbolioSkaitineVerte > 9) {
                suma *= 100;
            } else {
                suma *= 10;
            }

            suma += simbolioSkaitineVerte;
            if (suma > 999999999) {
                suma = (suma % 97);
            }
        }
        return (suma % 97) == 1;
    }

    String validuotiIsFailo(String failoPavadinimas) {
        if (failoPavadinimas.length() < 5) {
            return "Klaida! Neteisingas failo pavadinimas!";
        }
        if (!failoPavadinimas.substring(failoPavadinimas.length() - 4).equals(".txt")) {
            return "Klaida! Nuskaitomo failo plėtinys turi būti '.txt'";
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(failoPavadinimas));
            String kodas, rezultatas = "";
            while ((kodas = bufferedReader.readLine()) != null) {
                rezultatas += kodas + ";" + validuotiKoda(kodas) + "\n";
            }

            String rezultatoFailas = failoPavadinimas.substring(0, failoPavadinimas.length() - 4) + ".out";
            new RezultatoIssaugojimas(rezultatas, rezultatoFailas);

            return "Rezultatas išsaugotas '" + rezultatoFailas + "'";
        } catch (IOException ex) {
            return "Failo '" + failoPavadinimas + "' rasti nepavyko.";
        }
    }
}
