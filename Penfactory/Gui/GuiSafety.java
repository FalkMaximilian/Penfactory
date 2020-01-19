package Gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Src.*;

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
			frage = new JLabel("Wolle sie " + name + " wirklich loeschen?");
		 	frame.setSize(400,200);
		 	obj = objekt;
		 	objName=name;
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        panel = new JPanel(new GridBagLayout());
	        gridPanel = new GridBagConstraints();
	        gridPanel.insets = new Insets(10,10,20,10);
	        btnJa.addActionListener(this);
	        btnNein.addActionListener(this);
	        gridPanel.gridy = 0;
	        panel.add(frage, gridPanel);
	        gridPanel.gridx = 0;
	        gridPanel.gridy = 1;
	        panel.add(btnNein, gridPanel);
	        gridPanel.gridx = 1;
	        gridPanel.gridy = 1;
	        panel.add(btnJa, gridPanel);
	        frame.getContentPane().add(panel);
	        frame.setVisible(true);
		
	}
	
	 public void actionPerformed(ActionEvent action){
		 if (action.getSource() == btnJa){
			 if (obj == "kategorie") {
				 boolean test = Src.Datenverwaltung.deleteArtikel(objName);
		    	 if (test == false) {
		    		 JOptionPane.showMessageDialog((Component)null, "Kategorie " + objName + " konnte nicht geloescht werden");
		    	 }
			 }
			 if (obj == "artikel") {

				 boolean test = Src.Datenverwaltung.deleteArtikel(objName);
            	 if (test == false) {
            		 JOptionPane.showMessageDialog((Component)null, "Artikel " + objName + " konnte nicht geloescht werden");
            	 }
            	 
			 }
		 }
		 
		 if (action.getSource() == btnNein){
			 frame.dispose();
		 }
		 
	 }
	
	
}