package Gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import Src.*;

/*
 * Fenster zum bearbeiten, löschen und hinzufügen von Kategorien
 */

public class GuiKategorie implements ActionListener{

	
	JLabel ueberschhrift = new JLabel("Kategorien");
	JPanel panel;
	GridBagConstraints gridPanel;
	JFrame frame;
	List<String> kategorieListe  =new ArrayList<>();;
	JComboBox comboKategorie;
	JButton btnBack = new JButton("Zurueck");
	JButton btnKategorieChange = new JButton("Kategorie bearbeiten");
	JButton btnKategorieDel = new JButton("Kategorie loeschen");
	JButton btnKategorieAdd = new JButton("Kategorie hinzufuegen");
	
	public GuiKategorie() {
		
		//erstellen des Grundgerüsts
		frame = new JFrame("Kategorien: ");
		frame.setSize(700,200);
		List<Kategorie> kListe = Src.Datenverwaltung.getKList();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel(new GridBagLayout());
        
        //Drop-Down-Menü erstellen und befüllen
		for(int i =0; i < kListe.size(); i++) {
			Kategorie tmp = kListe.get(i);
			kategorieListe.add(tmp.name);
		}
		comboKategorie = new JComboBox(kategorieListe.toArray());
		
        

        //actionListener hinzufügen
        btnBack.addActionListener(this);
        btnKategorieAdd.addActionListener(this);
        btnKategorieChange.addActionListener(this);
        btnKategorieDel.addActionListener(this);
        comboKategorie.addActionListener(this);
        
        //Grid für Frame erstellen und befüllen
        gridPanel = new GridBagConstraints();
        gridPanel.insets = new Insets(10,5,20,5);
        gridPanel.gridy= 0;
        gridPanel.gridx= 1;
        panel.add(ueberschhrift, gridPanel);
        gridPanel.gridy= 0;
        gridPanel.gridx= 2;
        panel.add(comboKategorie,gridPanel);
        gridPanel.gridx = 0;
        gridPanel.gridy = 1;
        panel.add(btnBack,gridPanel);
        gridPanel.gridx = 1;
        gridPanel.gridy = 1;
        panel.add(btnKategorieDel,gridPanel);
        gridPanel.gridx = 2;
        gridPanel.gridy = 1;
        panel.add(btnKategorieChange,gridPanel);
        gridPanel.gridx = 3;
        gridPanel.gridy = 1;
        panel.add(btnKategorieAdd, gridPanel);
        
        //Panel dem Frame hinzufügen
        frame.getContentPane().add(panel);
        
        //Frame für Benutzer sichtbar setzen
        frame.setVisible(true);
	}
	
    public void actionPerformed(ActionEvent action){
    	
    	//Im Drop-Down-Menü ausgewählte Kategorie
    	Kategorie kat = Datenverwaltung.getKategorie((String) comboKategorie.getSelectedItem());
    			
		//Button welcher ein Fenster zum hinzufügen einer neuen Kategorie öffnet
    	if (action.getSource() == btnKategorieAdd) {
    		GuiKategorieAdd neuesFenster = new GuiKategorieAdd();
    	}
    	
    	//Button welcher das Fenster schließt und zum zurückkehren ins Hauptfenster dient
    	if (action.getSource() == btnBack) {
    		frame.dispose();
    		GuiHauptfenster neuesFenster = new GuiHauptfenster();
    	}
    	
    	//Button welcher die Sicherheitsabfrage zum löschen der ausgewählten Kategorie dient
    	if (action.getSource() == btnKategorieDel) {
    		GuiSafety safetyFenster = new GuiSafety("kategorie", kat.name);
    	}
    	
    	//Button welcher ein Fenster zum ändern des ausgewählten Kategorienamens öffnet 
    	if (action.getSource() == btnKategorieChange) {
    		frame.dispose();
    		GuiKategorieChange neuesFenster = new GuiKategorieChange(kat);
    	}
    }
    
}
