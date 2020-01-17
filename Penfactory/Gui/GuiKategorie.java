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
	JList kategorieListe;
	JButton btnBack = new JButton("Zurueck");
	JButton btnKategorieChange = new JButton("Kategorie bearbeiten");
	JButton btnKategorieDel = new JButton("Kategorie loeschen");
	JButton btnKategorieAdd = new JButton("Kategorie hinzufuegen");
	
	public GuiKategorie() {
		frame = new JFrame("Kategorien");
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel(new GridBagLayout());
        btnBack.addActionListener(this);
        btnKategorieAdd.addActionListener(this);
        btnKategorieChange.addActionListener(this);
        btnKategorieDel.addActionListener(this);
        gridPanel = new GridBagConstraints();
        gridPanel.insets = new Insets(10,5,20,5);
        gridPanel.gridy=0;
        panel.add(ueberschhrift, gridPanel);
        gridPanel.gridy=1;
        panel.add(kategorieListe);
        gridPanel.gridx = 0;
        gridPanel.gridy =2;
        panel.add(btnBack);
        gridPanel.gridx = 1;
        gridPanel.gridy =2;
        panel.add(btnKategorieDel);
        gridPanel.gridx = 2;
        gridPanel.gridy =2;
        panel.add(btnKategorieChange);
        gridPanel.gridx = 3;
        gridPanel.gridy =2;
        panel.add(btnKategorieAdd);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
	}
	
    public void actionPerformed(ActionEvent action){
    	if (action.getSource() == btnKategorieAdd) {
    		GuiKategorieAdd neuesFenster = new GuiKategorieAdd();
    		//kliste neu laden und in die JList reinschreiben
    	}
    	
    	if (action.getSource() == btnBack) {
    		frame.dispose();
    		GuiHauptfenster neuesFenster = new GuiHauptfenster();
    	}
    	
    	if (action.getSource() == btnKategorieDel) {
    		//genauso wie ArtikelDel
    	}
    	
    	if (action.getSource() == btnKategorieChange) {
    		frame.dispose();
    		//selected Kategorie von JList übergeben
    		GuiKategorieChange neuesFenster = new GuiKategorieChange();
    	}
    }
    
}
