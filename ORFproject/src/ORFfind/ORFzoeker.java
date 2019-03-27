package ORFfind;

import java.util.ArrayList;
import java.util.Stack;

public class ORFzoeker {


    private static Stack<Integer> locatieStack = new Stack();

    public static ArrayList zoekORF(ArrayList<String> sequentieLijst, String OrfVeldText,ArrayList<ArrayList<Integer>> orfList) {


        int lengteORF = 0;
        if (OrfVeldText.equals("")) {
            lengteORF = 50;
        } else {
            lengteORF = Integer.parseInt(OrfVeldText);
        }
        ArrayList<Integer> tijdelijkeLocatieList = new ArrayList<>();

        orfList.clear();
        for (int sequentieTeller = 0; sequentieTeller < sequentieLijst.size(); sequentieTeller++) {

            for (int i = 0; i < sequentieLijst.get(sequentieTeller).length(); i++) {
                if (sequentieLijst.get(sequentieTeller).charAt(i) == 'M') {
                    locatieStack.push(i);
                } else if (sequentieLijst.get(sequentieTeller).charAt(i) == '*') {
                    while (!locatieStack.empty()) {
                        if (i - locatieStack.peek() > lengteORF) {
                            tijdelijkeLocatieList.add(locatieStack.peek());
                            tijdelijkeLocatieList.add(i);
                            tijdelijkeLocatieList.add(sequentieTeller);
                            //De derde integer in de lijst is in welke frame de orf staat. Het is +1=0,+2=1,+3=2,-1=3,-2=4,-3=5
                            orfList.add(new ArrayList<>(tijdelijkeLocatieList));
                            tijdelijkeLocatieList.clear();

                        }
                        locatieStack.pop();

                    }
                }

            }
        }
    return orfList;
    }

}
