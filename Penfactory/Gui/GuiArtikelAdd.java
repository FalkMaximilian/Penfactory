package Gui;

import java.awt.*;
import javax.swing.*;

import Src.Artikel;
import Src.Kategorie;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/*
 * Fenster zur Eingabe aller Daten eines neuen Artikels.
 * Ermöglicht es auch eine neue Kategorie zu erstellen.
 */

public class GuiArtikelAdd extends JFrame implements ActionListener {

    JFrame frame;
    ArrayList<String> kategorieListe = new ArrayList();
    JLabel ueberschrift = new JLabel("Aritikel hinzufuegen");
    JLabel bezeichnung = new JLabel("Bezeichnung");
    JLabel kategorie = new JLabel("Kategorie");
    JLabel anzalh = new JLabel("Anzahl");
    JLabel gewicht = new JLabel("Gewicht");
    JLabel preis = new JLabel("Preis");
    JLabel platzNummer = new JLabel("Platznummer");
    JLabel stueck = new JLabel("Stk");
    JLabel euro = new JLabel("Euro");
    JLabel kilo = new JLabel("kg");
    JTextField textBezeichnung = new JTextField(20);
    JTextField textAnzahl = new JTextField(10);
    JTextField textGewicht = new JTextField(10);
    JTextField textPlatznummer = new JTextField(20);
    JTextField textPreis = new JTextField(10);
    JComboBox comboKategorie;
    JButton btnKategorieAdd;
    JButton btnArtikelAdd;
    JButton btnAbbrechen;
    JPanel ganzes;
    JPanel oben;
    JPanel unten;
    GridBagConstraints gridOben;
    GridBagConstraints gridUnten;
    
    


    public GuiArtikelAdd() {
    	
    	//Grundgerüst des Frames erstellen
        frame = new JFrame("Artikel hinzufuegen");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btnKategorieAdd = new JButton("Kategorie hinzufuegen");
        btnArtikelAdd = new JButton("Okay");
        btnAbbrechen = new JButton("Abbrechen");
        ganzes = new JPanel(new FlowLayout(1, 0, 0));
        oben = new JPanel(new GridBagLayout());
        unten = new JPanel(new GridBagLayout());
        
        
        //Drop-Down-Menü mit den existenten Kategorien erstellen
        List<Kategorie> kListe = Src.Datenverwaltung.getKList();
		for(int i =0; i < kListe.size(); i++) {
			Kategorie tmp = kListe.get(i);
			kategorieListe.add(tmp.name);
		}
        comboKategorie = new JComboBox(kategorieListe.toArray());
        
        //ActionListener hinzufügen
        btnArtikelAdd.addActionListener(this);
        btnAbbrechen.addActionListener(this);
        btnKategorieAdd.addActionListener(this);
        textAnzahl.addActionListener(this);
        textBezeichnung.addActionListener(this);
        textGewicht.addActionListener(this);
        textPlatznummer.addActionListener(this);
        comboKategorie.addActionListener(this);
        
        //Grid für den Frame erstellen und befüllen
        gridOben = new GridBagConstraints();
        gridUnten = new GridBagConstraints();
        gridOben.insets = new Insets(10,10,10,10);
        gridUnten.insets = new Insets(50,200,15,200);
        gridOben.gridx = 1;
        gridOben.gridy = 0;
        oben.add(ueberschrift, gridOben);
        gridOben.gridx = 0;
        gridOben.gridy = 1;
        oben.add(bezeichnung, gridOben);
        gridOben.gridx = 1;
        oben.add(textBezeichnung, gridOben);
        gridOben.gridx = 0;
        gridOben.gridy = 2;
        oben.add(kategorie, gridOben);
        gridOben.gridx = 1;
        oben.add(comboKategorie, gridOben);
        gridOben.gridx = 3;
        gridOben.gridy = 2;
        oben.add(btnKategorieAdd, gridOben);
        gridOben.gridx = 0;
        gridOben.gridy = 3;
        oben.add(anzalh, gridOben);
        gridOben.gridx = 1;
        oben.add(textAnzahl, gridOben);
        gridOben.gridx = 2;
        oben.add(stueck, gridOben);
        gridOben.gridx = 0;
        gridOben.gridy = 4;
        oben.add(gewicht, gridOben);
        gridOben.gridx = 1;
        oben.add(textGewicht, gridOben);
        gridOben.gridx = 2;
        oben.add(kilo, gridOben);
        gridOben.gridx = 0;
        gridOben.gridy = 5;
        oben.add(preis, gridOben);
        gridOben.gridx = 1;
        oben.add(textPreis, gridOben);
        gridOben.gridx = 2;
        oben.add(euro, gridOben);
        gridOben.gridx = 0;
        gridOben.gridy = 6;
        oben.add(platzNummer, gridOben);
        gridOben.gridx = 1;
        gridOben.gridy = 6;
        oben.add(textPlatznummer, gridOben);
        gridUnten.gridx = 0;
        gridUnten.gridy = 5;
        unten.add(btnAbbrechen, gridUnten);
        gridUnten.gridx = 1;
        unten.add(btnArtikelAdd, gridUnten);
        
        //Frame mit Panels füllen
        ganzes.add(oben);
        ganzes.add(unten);
        frame.getContentPane().add(ganzes);
        
        //Frame für Benutzer sichtbar machen
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent action){
    	//neue Produktbezeichnung
        String artikelBezeichnung = textBezeichnung.getText();
        
        //neue Kategorie
        String kategorie =(String) comboKategorie.getSelectedItem();
        
        //neuen Preis auf notwendigen Typ casten
        String preis = textPreis.getText();
        double price;
        try {
        	price = Double.parseDouble(preis);
        }
        catch (NumberFormatException e) {
        	price = 0.00;
        }
        
        //neues Gewicht auf notwendigen Typ casten
        String gewicht = textGewicht.getText();  
        double gew;
        try {
        	gew = Double.parseDouble(gewicht);
        }
        catch (NumberFormatException e) {
        	gew = 0.00;
        }
        
        //neue Anzahl auf notwendigen Typ casten
        String anzahl = textAnzahl.getText();
        int anz;
        try {
        	anz = Integer.parseInt(anzahl);
        }
        catch (NumberFormatException e) {
        	anz = -1;
        }
        
        //neue Platznummer auf notwendigen Typ casten
        String nummer = textPlatznummer.getText();
        int number;
        try {
        	number = Integer.parseInt(nummer);
        }
        catch (NumberFormatException e) {
        	number = -1;
        }
        
        //Button welcher ein neues Fenster zum erstellen einer neuen Kategorie öffnet
        if (action.getSource() == btnKategorieAdd){
        		GuiKategorieAdd fenster = new GuiKategorieAdd();
        	}

        //Button welcher den Vorgang abbricht und wieder zurück zum Hauptfenster kehrt
        if (action.getSource() == btnAbbrechen){
            frame.dispose();
            GuiHauptfenster fenster = new GuiHauptfenster();

        }
       
       
        	
        	//Überprüfen der Eingabe und ob alle Textfelder befüllt sind
        	if (action.getSource() == btnArtikelAdd){
        		 if (gew == 0.00||anz==-1||number==-1||kategorie==null||artikelBezeichnung==null) {
        	        	JOptionPane.showMessageDialog((Component)null, "Bitte fuellen Sie alle Felder korrekt aus");
        	        }
        	        else {
        		
        	        	//hinzufügen eines neuen Artikels
        	        	Artikel neuerArtikel = new Src.Artikel(artikelBezeichnung, kategorie, anz, gew, price, number);
        	        	boolean test = Src.Datenverwaltung.addArtikel(neuerArtikel);
        	        	if (test==true) {
        	        		JOptionPane.showMessageDialog((Component)null, "Artikel " + artikelBezeichnung + " wurde hinzugefuegt");
        	        		frame.dispose();
        	        		GuiHauptfenster fenster = new GuiHauptfenster();
        	        	}
        	        	else {
        	        		JOptionPane.showMessageDialog((Component)null, "Artikel " + artikelBezeichnung + " existiert bereits oder " + nummer + " ist bereits belegt");
        	        	}

        	        }
        	}
    	}
	}

