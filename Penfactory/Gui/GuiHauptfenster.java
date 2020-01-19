package Gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import Src.*;

/*
 * Klasse zum erstellen des Hauptfensters, auf welchem alle Artikel in einer Tabelle ausgegeben werden, sich die 
 * Suchfunktion befindet und von dem alle weiteren Funktionen gestartet werden
 */

public class GuiHauptfenster implements ActionListener{

    JFrame frame;
    JLabel suche = new JLabel("Suche: ");
    JTextField textsuche = new JTextField(40);
    JButton btnSuche = new JButton("suchen");
    JTable table;
    List<Artikel> artikel = null;
    DefaultTableModel model;
    JPanel oben;
    JPanel mitte;
    JPanel unten;
    JPanel komplett;
    JButton btnArtikelChange = new JButton("Artikel aendern");
    JButton btnArtikelDel = new JButton("Artikel loeschen");
    JButton btnArtikelAdd = new JButton("Artikel hinzufuegen");
    JButton btnKategorien = new JButton("Kategorien anzeigen");
    GridBagConstraints gridOben;
    GridBagConstraints gridUnten;
    JComboBox comboEigenschaft;
    
    
    public GuiHauptfenster(){
    		
    		//Grundgerüst des Frames und seiner Komponenten erstellen
    		table = new JTable();
    		artikel = Src.Datenverwaltung.getAList();
            frame = new JFrame("Artikelverwaltung");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900,700);
            komplett = new JPanel(new FlowLayout(1, 0, 0));
            oben = new JPanel(new GridBagLayout());
            unten = new JPanel(new GridBagLayout());
            mitte = new JPanel();
           
            
            //ActionListener hinzufügen
            btnArtikelAdd.addActionListener(this);
            btnArtikelChange.addActionListener(this);
            btnArtikelDel.addActionListener(this);
            btnKategorien.addActionListener(this);
            btnSuche.addActionListener(this);
            textsuche.addActionListener(this);
           
            //erstellen der Tabelle mit allen vorhandenen Artikeln
            createTabel(artikel); 
            
            //Drop-Down-Menü erstellen und befüllen
            comboEigenschaft = new JComboBox(Src.Eigenschaft.values());
            
            //Grid für den Frameaufbau erstellen 
            gridOben = new GridBagConstraints();
            gridUnten = new GridBagConstraints();
            gridOben.insets = new Insets(20,10,30,10);
            gridUnten.insets = new Insets(50,50,15,50);
            gridOben.gridx = 0;
            gridOben.gridy = 0;
            oben.add(suche, gridOben);
            gridOben.gridx = 1;
            gridOben.gridy = 0;
            oben.add(comboEigenschaft, gridOben);
            gridOben.gridx = 2;
            gridOben.gridy = 0;
            oben.add(textsuche, gridOben);
            gridOben.gridx = 3;
            gridOben.gridy = 0;
            oben.add(btnSuche, gridOben);
            gridUnten.gridx = 0;
            gridUnten.gridy = 0;
            unten.add(btnArtikelChange, gridUnten);
            gridUnten.gridx = 1;
            gridUnten.gridy = 0;
            unten.add(btnArtikelDel, gridUnten);
            gridUnten.gridx = 2;
            gridUnten.gridy = 0;
            unten.add(btnArtikelAdd, gridUnten);
            gridUnten.gridx = 3;
            gridUnten.gridy = 0;
            unten.add(btnKategorien, gridUnten);
            
            //Frame mit Panels füllen 
            komplett.add(oben);
            komplett.add(mitte);
            komplett.add(unten);
            frame.getContentPane().add(komplett);
            
            //Frame für den Benutzer sichtbar schalten
            frame.setVisible(true);

    }

        public void actionPerformed(ActionEvent action){
        	
        	//Eigenschaft für die Suche aus dem Drop-Down-Menü
        	Eigenschaft eigenschaft = (Eigenschaft) comboEigenschaft.getSelectedItem();
        	//Text nach welchem gesucht werden sull aus dem Textfeld
        	String suchText = textsuche.getText();
        	
        	//Button welcher ein neues Fenster zum hinzufügen eines Artikels öffnen
            if (action.getSource() == btnArtikelAdd){
                frame.dispose();
                GuiArtikelAdd neuesFenster = new GuiArtikelAdd();
            }

            //Button welcher ein Fenster zum bearbeiten den ausgewählten Artikels öffnet
            if (action.getSource() == btnArtikelChange){
                
           	 int i = table.getSelectedRow();
             if(i >= 0){
            	 String artName = (String) table.getValueAt(i, 0);
            	 System.out.println(artName);
            	 frame.dispose();
            	 GuiArtikelChange neuesFenster = new GuiArtikelChange(artName);
             }
             else{
            	 JOptionPane.showMessageDialog((Component)null, "Bitte waehlen Sie den Artikel, welchen Sie aendern wollen aus");
             }
        }
                
            
            //Button welcher die Sicherheitsabfrage öffnet ob der ausgewählte Artikel wirklich gelöscht werden soll
            if (action.getSource() == btnArtikelDel){
            	 int i = table.getSelectedRow();
                 if(i >= 0){
                	 String artName = (String) table.getValueAt(i, 0);
                	 System.out.println(artName);
                	 GuiSafety safetyFenster = new GuiSafety("artikel", artName);
                	 artikel = Src.Datenverwaltung.getAList();
                	 DefaultTableModel model = (DefaultTableModel) table.getModel();
                     createTabel(artikel);
                 }
                 else{
                	 JOptionPane.showMessageDialog((Component)null, "Bitte waehlen Sie den Artikel, welchen Sie loeschen wollen aus");
                 }
            }

            /*
             * Button welcher nach dem Text aus dem Textfeld in der ausgewählten Eigenschaft sucht und 
             * die Ergebnisse in die Tabelle schreibt.
             */
            if (action.getSource() == btnSuche){

           	 	DefaultTableModel model = (DefaultTableModel) table.getModel();
            	List<Artikel> suchliste = Src.Datenverwaltung.search(suchText, eigenschaft);
            	createTabel(suchliste);    
            }
            
            //Button welcher ein Fenster zum bearbeiten, hinzufügen und löschen von Kategorien öffnet 
            if(action.getSource() == btnKategorien) {
            	frame.dispose();
            	GuiKategorie neuesFenster = new GuiKategorie();
            }
            
        }

        //Funktion zum erstellen und befüllen der Tabelle mit einer Liste
        public void createTabel(List<Artikel> aListe){
        	
        	//Erstellen einer leeren Tabelle und festlegen der Spalten beschriftung
        	DefaultTableModel model = new DefaultTableModel();
        	model.setRowCount(0);
        	Object[] columns = {"Artikelbezeichnung","Kategorie","Preis","Anzahl", "Platznummer", "Gewicht"};
            model.setColumnIdentifiers(columns);
            table.setModel(model);
            
            // Festlegen der Tabelleneigenschaften
            table.setBackground(Color.LIGHT_GRAY);
            table.setForeground(Color.black);
            Font font = new Font("",1,11);
            table.setFont(font);
            table.setRowSelectionAllowed(true);
            table.enableInputMethods(false);
            table.setRowHeight(30);
            
            JScrollPane pane = new JScrollPane(table);
            
            
            int a_list_size = aListe.size();
            
            //Befüllen der Tabelle mit den Daten aus der Liste
            for (int i = 0; i < a_list_size; i++) {
            	System.out.println("add");
            	String[] row = new String[6];
    			Artikel tmpArtikel = aListe.get(i);
    			row[0] = tmpArtikel.produktBezeichnung;
    			row[1] = tmpArtikel.kategorie;
    			String preis = Double.toString(tmpArtikel.preis);
    			row[2] = preis;
    			String anz = Integer.toString(tmpArtikel.anzahl);
    			row[3] = anz;
    			String nummer= Integer.toString(tmpArtikel.platzNummer);
    			row[4] = nummer;
    			String gew = Double.toString(tmpArtikel.gewicht);
    			row[5] = gew;
    			
    		
    			model.addRow(row);
    			
    			
    		}
    		
            // Befüllte Tabelle dem Frame hinzufügen
    		mitte.add(pane);
    		
    		//Frame updaten um neu befüllte Tabelle anzuzeigen
    		SwingUtilities.updateComponentTreeUI(frame);
    		
            
        }

}