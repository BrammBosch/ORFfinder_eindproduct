package ORFfinderGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.io.IOException;

public class opslagGui extends JFrame{
    private JPanel panel1;
    private JTextArea Resultatenveld;
    private JButton opslaanButton;
    private JTextArea databaseveld;
    private JLabel informatieResultaten;
    private JLabel informatieopslag;
    private JLabel informatieDatabase;
    private JPanel guiPanel;

    public opslagGui(String ingevoerdeSequentieBlast) throws IOException {
        exportNaarDatabase.bestandLezen();
        add(guiPanel);
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Resultatenveld.setEnabled(false);
        databaseveld.setEnabled(false);
        //Resultatenveld.setText(String.valueOf(testLijst));
        String alles = exportNaarDatabase.everything;
        String zonderhaken = alles.replace("[", "").replace("]", "");
        String values = zonderhaken.replace(",", "\t");
        String ingevoerdeSequentie = ingevoerdeSequentieBlast;
        String tekst = exportNaarDatabase.legenda + values + "\n" + "Ingevoerde sequentie:" + "\n" + ingevoerdeSequentie ;
        Resultatenveld.setText(tekst);

        opslaanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        } );}

        public void actionPerformed(ActionEvent e) {
        if (e.getSource() == opslaanButton){

        }
    }
}

