package Gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Src.*;

/*
 * Fenster zum verändern des Namens einer bereits bestehenden Kategorie
 */

public class GuiKategorieChange implements ActionListener {
	
		JFrame frame;
		Kategorie altKategorie;
	    JPanel panel;
	    JLabel ueberschrift = new JLabel("Kategorie aendern");
	    JLabel name = new JLabel("Kategoriename");
	    JTextField textName = new JTextField(100);
	    JButton btnChangeKategorie = new JButton("Okay");
	    JButton btnAbbrechen = new JButton("Abbrechen");
	    GridBagConstraints gridPanel;

	    public GuiKategorieChange(Kategorie kategorie){
	    	
	    	//erstellen des Grundgerüsts des Frames
	        frame = new JFrame("Kategorie aendern");
	        frame.setSize(800,200);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        panel = new JPanel(new GridBagLayout());
	        
	        //ActioListener hinzufügen
	        btnAbbrechen.addActionListener(this);
	        btnChangeKategorie.addActionListener(this);
	        textName.addActionListener(this);
	        
	        //speichern der alten Kategorie
	        altKategorie = kategorie;
	        
	        //Textfeld mit aktuellem Namen befüllen
	        textName.setText(kategorie.name);
	     
	        //Grid für den Frame erstellen und befüllen
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
	        panel.add(btnChangeKategorie, gridPanel);
	        
	        //Frame mit Panel befüllen
	        frame.getContentPane().add(panel);
	        
	        //Frame für Benutzer sichtbar setzen
	        frame.setVisible(true);
	    }

	     public void actionPerformed(ActionEvent action){
	    	 
	    	 		//neuer Kategoriename
	                String neuName = textName.getText();

	                //verändern des Kategorienamens
	                if (action.getSource() == btnChangeKategorie){
	                    
	                    boolean test = Src.Datenverwaltung.changeKategorie(altKategorie.name, new Kategorie(neuName, altKategorie.artikelAnzahl));
	                    if (test == true) {
	                    	JOptionPane.showMessageDialog((Component)null, "Kategorie " + altKategorie.name + "heisst jetzt" + neuName);
	                    	GuiKategorie neuesFenster = new GuiKategorie();
	                    	frame.dispose();
	                    	}
	                    else {
	                    	JOptionPane.showMessageDialog((Component)null, "Kategorie " + altKategorie.name + " existiert nicht");
	                    }
	                    
	                }

	                //Vorgang abbrechen und schließen des Fensters
	                if (action.getSource() == btnAbbrechen){
	                    frame.dispose();
	                    GuiKategorie neuesFenster = new GuiKategorie();
	                }

	     }
}
