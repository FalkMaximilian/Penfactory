package Gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Src.*;

/*
 * Fenster zum Erstellen einer neuen Kategorie
 */

public class GuiKategorieAdd implements ActionListener{

    JFrame frame;
    JPanel panel;
    JLabel ueberschrift = new JLabel("Kategorie hinzufuegen");
    JLabel name = new JLabel("Kategoriename");
    JTextField textName = new JTextField(10);
    JButton btnAddKategorie = new JButton("Okay");
    JButton btnAbbrechen = new JButton("Abbrechen");
    GridBagConstraints gridPanel;

    public GuiKategorieAdd(){
    	//Frame Grundgerüst erstellen
        frame = new JFrame("Kategorie hinzufuegen");
        frame.setSize(800,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //ActionListener hinzufügen
        btnAbbrechen.addActionListener(this);
        btnAddKategorie.addActionListener(this);
        textName.addActionListener(this);
        
        //Grid für den Frame erstellen und befüllen
        panel = new JPanel(new GridBagLayout());
        gridPanel = new GridBagConstraints();
        gridPanel.insets = new Insets(10,5,20,5);
        gridPanel.gridx = 1;
        gridPanel.gridy = 0;
        panel.add(ueberschrift, gridPanel);
        gridPanel.gridx = 0;
        gridPanel.gridy = 1;
        panel.add(name, gridPanel);
        gridPanel.gridx = 1;
        gridPanel.gridy = 1;
        panel.add(textName, gridPanel);
        gridPanel.gridx = 0;
        gridPanel.gridy = 2;
        panel.add(btnAbbrechen, gridPanel);
        gridPanel.gridx = 1;
        gridPanel.gridy = 2;
        panel.add(btnAddKategorie, gridPanel);
        
        //Panel dem Frame hinzufügen
        frame.getContentPane().add(panel);
        
        //Frame für den Benutzer sichtbar setzen
        frame.setVisible(true);
    }

     public void actionPerformed(ActionEvent action){
    	 	
    	 		//Name der neuen Kategorie
                String kategorieName = textName.getText();

                //Hinzufügen einer neuen Kategorie mit 0 Artikeln
                if (action.getSource() == btnAddKategorie){
                    
                    Kategorie k =  new Src.Kategorie(kategorieName, 0);
                    boolean test = Src.Datenverwaltung.addKategorie(k);
                    if (test==true) {
                    	JOptionPane.showMessageDialog((Component)null, "Kategorie " + kategorieName + " wurde hinzugefuegt");
                    	frame.dispose();
                    }
                    else {
                    	JOptionPane.showMessageDialog((Component)null, "Kategorie " + kategorieName + " existiert bereits");
                    }
                    
                }

                //fenster schließen
                if (action.getSource() == btnAbbrechen){
                    frame.dispose();
                }


}}