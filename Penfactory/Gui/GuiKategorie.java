package Gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import Src.*;

public class GuiKategorie implements ActionListener{

	JLabel ueberschhrift = new JLabel("Kategorien");
	JPanel panel;
	GridBagConstraints gridPanel;
	JFrame frame;
	JComboBox comboKategorie;
	JButton btnBack = new JButton("Zurueck");
	JButton btnKategorieChange = new JButton("Kategorie bearbeiten");
	JButton btnKategorieDel = new JButton("Kategorie loeschen");
	JButton btnKategorieAdd = new JButton("Kategorie hinzufuegen");
	
	public GuiKategorie() {
		frame = new JFrame("Kategorien: ");
		frame.setSize(700,200);
		comboKategorie = new JComboBox(Src.Datenverwaltung.getKList().toArray());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel(new GridBagLayout());
        btnBack.addActionListener(this);
        btnKategorieAdd.addActionListener(this);
        btnKategorieChange.addActionListener(this);
        btnKategorieDel.addActionListener(this);
        comboKategorie.addActionListener(this);
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
        frame.getContentPane().add(panel);
        frame.setVisible(true);
	}
	
    public void actionPerformed(ActionEvent action){
    	Kategorie kat = (Kategorie) comboKategorie.getSelectedItem();
		
    	if (action.getSource() == btnKategorieAdd) {
    		GuiKategorieAdd neuesFenster = new GuiKategorieAdd();
    	}
    	
    	if (action.getSource() == btnBack) {
    		frame.dispose();
    		GuiHauptfenster neuesFenster = new GuiHauptfenster();
    	}
    	
    	if (action.getSource() == btnKategorieDel) {
    		GuiSafety safetyFenster = new GuiSafety("kategorie", kat.name);
    	}
    	
    	if (action.getSource() == btnKategorieChange) {
    		frame.dispose();
    		GuiKategorieChange neuesFenster = new GuiKategorieChange(kat);
    	}
    }
    
}
