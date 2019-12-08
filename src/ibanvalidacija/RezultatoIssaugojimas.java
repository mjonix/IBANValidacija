package ibanvalidacija;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class RezultatoIssaugojimas {

    RezultatoIssaugojimas(String rezultatas, String failas) throws IOException {
        FileWriter fileWriter = new FileWriter(failas, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("%s" + "%n", rezultatas);
        printWriter.close();
    }
}
