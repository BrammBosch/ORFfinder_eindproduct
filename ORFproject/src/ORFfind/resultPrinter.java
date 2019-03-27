package ORFfind;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ORFfinderGui.orfGUI;

import javax.swing.*;

public class resultPrinter {

    public static String uitvoer(ArrayList<String> sequentieLijst, JComboBox orfBox, ArrayList<ArrayList<Integer>> orfList) {
        String results = "";

        results += (sequentieLijst.get(orfBox.getSelectedIndex()) + "\n");

        for (int i = 0; i < orfList.size(); i++) {

            if (orfList.get(i).get(2) == orfBox.getSelectedIndex()) {
                String s = " ";
                int n = orfList.get(i).get(0);
                results += IntStream.range(0, n).mapToObj(a -> s).collect(Collectors.joining(""));
                results += sequentieLijst.get(orfBox.getSelectedIndex()).substring(orfList.get(i).get(0), orfList.get(i).get(1));
                results += "\n";
            }
        }
        return results;
    }
}
