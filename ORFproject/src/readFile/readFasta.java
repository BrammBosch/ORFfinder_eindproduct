package readFile;

import ORFtranslate.translatie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

public class readFasta {
    public static ArrayList readFile(BufferedReader ingelezenFile) throws IOException {
        String sequentie = "";
        String reverse = "";
        String regel = "";
        String header = "";
        ArrayList<String> sequentieLijst = new ArrayList<>();


        header = ingelezenFile.readLine();

        while ((regel = ingelezenFile.readLine()) != null) {
            sequentie += regel;
        }


        for (int i = sequentie.length() - 1; i >= 0; i--) {
            reverse = reverse + sequentie.charAt(i);
        }
        String sequentie1 = translatie.translate(sequentie);
        String sequentie2 = translatie.translate(sequentie.substring(1));
        String sequentie3 = translatie.translate(sequentie.substring(2));

        String sequentie3Reverse = translatie.translate(reverse);
        String sequentie2Reverse = translatie.translate(reverse.substring(1));
        String sequentie1Reverse = translatie.translate(reverse.substring(2));

        sequentieLijst.add(sequentie1);
        sequentieLijst.add(sequentie2);
        sequentieLijst.add(sequentie3);
        sequentieLijst.add(sequentie1Reverse);
        sequentieLijst.add(sequentie2Reverse);
        sequentieLijst.add(sequentie3Reverse);

        return sequentieLijst;

    }
}
