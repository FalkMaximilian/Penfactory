package Gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Src.*;


public class GuiKategorieChange implements ActionListener {
	
		JFrame frame;
		Kategorie altKategorie;
	    JPanel panel;
	    JLabel ueberschrift = new JLabel("Kategorie aendern");
	    JLabel name = new JLabel("Kategoriename");
	    JTextField textName = new JTextField(100);
	    JButton btnChangeKategorie = new JButton("Kategorie aendern");
	    JButton btnAbbrechen = new JButton("Abbrechen");
	    GridBagConstraints gridPanel;

	    public GuiKategorieChange(Kategorie kategorie){
	        frame = new JFrame("Kategorie aendern");
	        frame.setSize(800,200);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        panel = new JPanel(new GridBagLayout());
	        btnAbbrechen.addActionListener(this);
	        btnChangeKategorie.addActionListener(this);
	        altKategorie = kategorie;
	        textName.setText(kategorie.name);
	        textName.addActionListener(this);
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
	        frame.getContentPane().add(panel);
	        frame.setVisible(true);
	    }

	     public void actionPerformed(ActionEvent action){
	                String neuName = textName.getText();

	                if (action.getSource() == btnChangeKategorie){
	                    frame.dispose();
	                    Src.Datenverwaltung.changeKategorie(neuName, altKategorie);
	                    JOptionPane.showMessageDialog((Component)null, "Kategorie " + altKategorie.name + "heisst jetz" + neuName);
	                }

	                if (action.getSource() == btnAbbrechen){
	                    frame.dispose();
	                    GuiKategorie neuesFenster = new GuiKategorie();
	                }

	     }
}