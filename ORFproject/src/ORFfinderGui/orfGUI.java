package ORFfinderGui;

import ORFfind.resultPrinter;
import ORFfind.ORFzoeker;
import streamConverter.streamConverter;
import buttonActions.*;
import readFile.readFasta;
import ORFfinderGui.*;
import ORFtranslate.translatie;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class orfGUI extends JFrame implements ActionListener, ItemListener {

    private JPanel guiPanel;
    private JButton openButton;
    private JTextField invoerVeld;
    private JButton browseButton;
    private JTextArea blastVeld;
    private JButton blastButton;
    private JLabel blastLabel;
    private JButton saveButton;
    private JTextField orfLengteVeld;
    private JComboBox orfBox;
    public JTextArea uitvoerVeld;
    private JTextPane orf1PlusOutput;
    private static BufferedReader ingelezenFile;
    public ArrayList<String> sequentieLijst = new ArrayList<>();
    private String seq;

    private ArrayList<ArrayList<Integer>> orfList = new ArrayList<>();


    public String path;

    public orfGUI() {
        add(guiPanel);
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        browseButton.addActionListener(this);
        openButton.addActionListener(this);
        blastButton.addActionListener(this);
        orfBox.addItemListener(this);
        saveButton.addActionListener(this);

    }

    public void openBestand() throws IOException {


        ingelezenFile = new BufferedReader(new FileReader(invoerVeld.getText()));
        sequentieLijst = readFasta.readFile(ingelezenFile);


        String OrfVeldText = orfLengteVeld.getText();
        orfList = ORFzoeker.zoekORF(sequentieLijst, OrfVeldText, orfList);

        String results = resultPrinter.uitvoer(sequentieLijst, orfBox, orfList);
        uitvoerVeld.setText(results);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == browseButton) {
            try {
                JFileChooser chooser = new JFileChooser();
                int choice = chooser.showOpenDialog(null);
                if (choice != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                invoerVeld.setText(chooser.getSelectedFile().getAbsolutePath());
                path = invoerVeld.getText();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Er is iets fout gegaan");
            }
        }
        if (e.getSource() == openButton) {
            try {
                openBestand();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Er is iets fout gegaan");
            }
        }


        if (e.getSource() == blastButton) {

            //System.out.println(seq);
            seq = blastVeld.getText();

            blastButton.setEnabled(false);

            blastButtonAction.blasten(path, seq, blastButton);


        }
        if (e.getSource() == saveButton) {
            try {
                ArrayList<String> testLijst = new ArrayList<>();
                testLijst.add("A");
                opslagGui opslagGui = new opslagGui(seq);
                opslagGui.setVisible(true);
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, "Check of de blastbutton nog grijs is, dan moet je nog even wachten. \nZoniet, dan zijn er geen resultaten gevonden.");
                ex.printStackTrace();

            }
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == orfBox) {
            String results = resultPrinter.uitvoer(sequentieLijst, orfBox, orfList);
            uitvoerVeld.setText(results);

        }
    }
}

