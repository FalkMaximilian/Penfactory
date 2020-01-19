package Gui;

import java.awt.*;
import javax.swing.*;

import com.sun.istack.internal.localization.NullLocalizable;
import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.ParentIterator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Src.*;


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
        frame = new JFrame("Kategorie hinzufuegen");
        frame.setSize(800,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel(new GridBagLayout());
        btnAbbrechen.addActionListener(this);
        btnAddKategorie.addActionListener(this);
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
        panel.add(btnAddKategorie, gridPanel);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

     public void actionPerformed(ActionEvent action){
                String kategorieName = textName.getText();

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

                if (action.getSource() == btnAbbrechen){
                    frame.dispose();
                }


}}