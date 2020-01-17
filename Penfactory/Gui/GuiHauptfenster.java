package Gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import Src.*;


public class GuiHauptfenster implements ActionListener{

    JFrame frame;
    JLabel suche = new JLabel("Suche: ");
    JTextField textsuche = new JTextField(40);
    JButton btnSuche = new JButton("suchen");
    JList<Artikel> artikelliste;  //Array benötigt
    List<Artikel> artikel = Src.Datenverwaltung.getAList();
    JPanel oben;
    JPanel unten;
    JPanel komplett;
    JButton btnArtikelChange = new JButton("Artikel aendern");
    JButton btnArtikelDel = new JButton("Artikel loeschen");
    JButton btnArtikelAdd = new JButton("Artikel hinzufuegen");
    GridBagConstraints gridOben;
    GridBagConstraints gridUnten;
    JComboBox comboEigenschaft;

    public GuiHauptfenster(){
            frame = new JFrame("Artikelverwaltung");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800,400);
            komplett = new JPanel(new FlowLayout(1, 0, 0));
            oben = new JPanel(new GridBagLayout());
            unten = new JPanel(new GridBagLayout());
            btnArtikelAdd.addActionListener(this);
            btnArtikelChange.addActionListener(this);
            btnArtikelDel.addActionListener(this);
            btnSuche.addActionListener(this);
            textsuche.addActionListener(this);
            //artikelliste = new JList (artikel);  //selber Datentyp wie oben
            //artikelliste.setSize(20,20);
            //artikelliste.setVisible(true);
            //artikelliste.addActionListener(this);
            comboEigenschaft = new JComboBox(Src.Eigenschaft.values());
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
            //gridOben.gridy = 1;
            //oben.add(artikelliste);
            gridUnten.gridx = 0;
            gridUnten.gridy = 0;
            unten.add(btnArtikelChange, gridUnten);
            gridUnten.gridx = 1;
            gridUnten.gridy = 0;
            unten.add(btnArtikelDel, gridUnten);
            gridUnten.gridx = 2;
            gridUnten.gridy = 0;
            unten.add(btnArtikelAdd, gridUnten);
            komplett.add(oben);
          
            komplett.add(unten);
            frame.getContentPane().add(komplett);
            frame.setVisible(true);

    }

        public void actionPerformed(ActionEvent action){
        	Eigenschaft eigenschaft = (Eigenschaft) comboEigenschaft.getSelectedItem();
        	String suchText = textsuche.getText();
        	
            if (action.getSource() == btnArtikelAdd){
                frame.dispose();
                GuiArtikelAdd neuesFenster = new GuiArtikelAdd();
            }

            if (action.getSource() == btnArtikelChange){
                frame.dispose();
                //ArtikelChange muss selected artikel Ã¼bergeben werden!!!
                GuiArtikelChange neuesFenster = new GuiArtikelChange();
            }

            if (action.getSource() == btnArtikelDel){
            		//selected von artikelliste
            }

            if (action.getSource() == btnSuche){
            	List<Artikel> suchliste = Src.Datenverwaltung.search(suchText, eigenschaft);
            	//artikel.setListData = suchliste;
            }
        }



}