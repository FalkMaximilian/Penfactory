package Gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Src.*;

/*
 * Fenster zur Sicherheitsabfrage beim Löschen um versehentliches löschen zu vermeiden
 */
public class GuiSafety implements ActionListener{
	
	JFrame frame =new JFrame("Wollen Sie wirklich loeschen");
	JButton btnJa = new JButton("Ja");
	JButton btnNein = new JButton("Nein");
	JLabel frage;
	String obj;
	JPanel panel;
	 GridBagConstraints gridPanel;
	 String objName;
	
	public GuiSafety(String objekt, String name) {
		 	
			//Erstellen des Grundgerüsts des frames
			frage = new JLabel("Wolle sie " + name + " wirklich loeschen?");
		 	frame.setSize(400,200);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        //übergebene Parameter speichern
	        obj = objekt;
		 	objName=name;
		 	
		 	//ActionListener hinzufügen
		 	btnJa.addActionListener(this);
	        btnNein.addActionListener(this);
		 	
		 	//Grid für den Frame erstellen
	        panel = new JPanel(new GridBagLayout());
	        gridPanel = new GridBagConstraints();
	        gridPanel.insets = new Insets(10,10,20,10);
	        gridPanel.gridy = 0;
	        panel.add(frage, gridPanel);
	        gridPanel.gridx = 0;
	        gridPanel.gridy = 1;
	        panel.add(btnNein, gridPanel);
	        gridPanel.gridx = 1;
	        gridPanel.gridy = 1;
	        panel.add(btnJa, gridPanel);
	        
	        //Panel dem Frame hinzufügen
	        frame.getContentPane().add(panel);
	        
	        //Frame für den Benutzer sichtbar setzen
	        frame.setVisible(true);
		
	}
	
	 public void actionPerformed(ActionEvent action){
		 
		 //löschen des Objektes
		 if (action.getSource() == btnJa){
			 
			 //unterscheidung zwischen löschen eines Artikels und einer Kategorie
			 if (obj == "kategorie") {
				 
				 //Kategorie löschen
				 boolean test = Src.Datenverwaltung.deleteArtikel(objName);
		    	 if (test == false) {
		    		 JOptionPane.showMessageDialog((Component)null, "Kategorie " + objName + " konnte nicht geloescht werden");
		    	 } else {
		    		 frame.dispose();
		    		 JOptionPane.showMessageDialog((Component)null, "Kategorie " + objName + "wurde erfolgreich geloescht!");
		    	 }
			 }
			 if (obj == "artikel") {

				 //Artikel löschen
				 boolean test = Src.Datenverwaltung.deleteArtikel(objName);
            	 if (test == false) {
            		 JOptionPane.showMessageDialog((Component)null, "Artikel " + objName + " konnte nicht geloescht werden");
            	 } else {
            		 frame.dispose();
            		 JOptionPane.showMessageDialog((Component)null, "Artikel " + objName + "wurde erfolgreich geloescht!");
            	 }
            	 
			 }
		 }
		 
		 //löschen abbrechen
		 if (action.getSource() == btnNein){
			 frame.dispose();
		 }
		 
	 }
	
	
}