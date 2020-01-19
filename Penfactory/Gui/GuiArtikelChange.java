package Gui;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import Src.*;

/*
 * Fenster zur Bearbeitung eines bereits bestehenden Artikels.
 * Es ist zudem möglich eine neue Kategorie zu erstellen
 */
public class GuiArtikelChange extends JFrame implements ActionListener{

    JFrame frame;
    String changeName;
    ArrayList<String> kategorieListe = new ArrayList();
    JLabel ueberschrift = new JLabel("Aritikel veraendern");
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
    JButton btnArtikelChange;
    JButton btnAbbrechen;
    JPanel ganzes;
    JPanel oben;
    JPanel unten;
    GridBagConstraints gridOben;
    GridBagConstraints gridUnten;


    public GuiArtikelChange(String artikelName) {
    	
    	//Speichern der alten Produktbezeichnung
    	changeName = artikelName;
    	
    	//Artikeldaten zum befüllen der Textxfelder und des Drop-Down-Menüs
    	Src.Artikel artikel = Src.Datenverwaltung.getArtikel(artikelName);
    	
    	//Erstellen des Grundgerüsts des Frames
        frame = new JFrame("Artikel veraendern");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Artikeldaten in ihre Komponenten eintragen
        textBezeichnung.setText(artikel.produktBezeichnung);
        String anz = Integer.toString(artikel.anzahl);
        textAnzahl.setText(anz); 
        String gew = Double.toString(artikel.gewicht);
        textGewicht.setText(gew);
        String nummer = Integer.toString(artikel.platzNummer);
        textPlatznummer.setText(nummer);
        String price = Double.toString(artikel.preis);
        textPreis.setText(price);
        btnKategorieAdd = new JButton("Kategorie hinzufuegen");
        btnArtikelChange = new JButton("Okay");
        btnAbbrechen = new JButton("Abbrechen");
        List<Kategorie> kListe = Src.Datenverwaltung.getKList();
		for(int i =0; i < kListe.size(); i++) {
			Kategorie tmp = kListe.get(i);
			kategorieListe.add(tmp.name);
		}
        comboKategorie = new JComboBox(kategorieListe.toArray());
        comboKategorie.setSelectedItem(artikel.kategorie);
        
        //ActionListener hinzufügen
        btnArtikelChange.addActionListener(this);
        btnAbbrechen.addActionListener(this);
        btnKategorieAdd.addActionListener(this);
        textAnzahl.addActionListener(this);
        textBezeichnung.addActionListener(this);
        textGewicht.addActionListener(this);
        textPlatznummer.addActionListener(this);
        comboKategorie.addActionListener(this);
        
        //Grid für den Frame erstellen und befüllen
        ganzes = new JPanel(new FlowLayout(1, 0, 0));
        oben = new JPanel(new GridBagLayout());
        unten = new JPanel(new GridBagLayout());
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
        unten.add(btnArtikelChange, gridUnten);
        
        //Frame mit Panels füllen
        ganzes.add(oben);
        ganzes.add(unten);
        frame.getContentPane().add(ganzes);
        
        //Frame für Benutzer sichtbar setzen
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
          
          //Fenster zum hinzufügen einer Kategorie öffnen
          if (action.getSource() == btnKategorieAdd){
          		GuiKategorieAdd fenster = new GuiKategorieAdd();

          	}

          //Vorgang abbrechen und zurück zum Hauptfenster kehren
          if (action.getSource() == btnAbbrechen){
              frame.dispose();
              GuiHauptfenster fenster = new GuiHauptfenster();

          }
         
         
          	
            //Eingabe auf Korrektheit und Vollständigkeit prüfen
          	if (action.getSource() == btnArtikelChange){
          		 if (gew == 0.00||anz==-1||number==-1||kategorie==null||artikelBezeichnung==null) {
                   	JOptionPane.showMessageDialog((Component)null, "Bitte fuellen Sie alle Felder korrekt aus");
                   }
                   else {
                	   	
                	   //Artikel verändern
                	   Artikel neuerArtikel = new Src.Artikel(artikelBezeichnung, kategorie, anz, gew, price, number);
                	   boolean test = Src.Datenverwaltung.changeArtikel(changeName, neuerArtikel);
                	   if (test == true) {
                		   JOptionPane.showMessageDialog((Component)null, "Artikel " + artikelBezeichnung + " wurde veraendert");
                		   frame.dispose();
                		   GuiHauptfenster fenster = new GuiHauptfenster();
                		   }
                	   else {
                		   JOptionPane.showMessageDialog((Component)null, "Artikel " + artikelBezeichnung + " existiert nicht");
                	   }

          	}
          }}
    }

