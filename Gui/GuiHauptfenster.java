package Gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


public class GuiHauptfenster implements ActionListener{

    JFrame frame;
    JLabel suche = new JLabel("Suche: ");
    JTextField textsuche = new JTextField(40);
    JButton btnSuche = new JButton("suchen");
    JList artikelliste;
    ArrayList<String> kategorieListe = new ArrayList();
    ArrayList<String> artikel = new ArrayList();
    JPanel oben;
    JPanel unten;
    JPanel komplett;
    JButton btnArtikelChange = new JButton("Artikel aendern");
    JButton btnArtikelDel = new JButton("Artikel loeschen");
    JButton btnArtikelAdd = new JButton("Artikel hinzufuegen");
    GridBagConstraints gridOben;
    GridBagConstraints gridUnten;
    JComboBox comboKategorie;

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
            artikelliste = new JList(artikel.toArray());
            artikelliste.setSize(20,20);
            artikelliste.setVisible(true);
            comboKategorie = new JComboBox(kategorieListe.toArray());
            gridOben = new GridBagConstraints();
            gridUnten = new GridBagConstraints();
            gridOben.insets = new Insets(20,10,30,10);
            gridUnten.insets = new Insets(50,50,15,50);
            gridOben.gridx = 0;
            gridOben.gridy = 0;
            oben.add(suche, gridOben);
            gridOben.gridx = 1;
            gridOben.gridy = 0;
            oben.add(textsuche, gridOben);
            gridOben.gridx = 2;
            gridOben.gridy = 0;
            oben.add(btnSuche, gridOben);
            gridOben.gridy = 1;
            oben.add(artikelliste);
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
            if (action.getSource() == btnArtikelAdd){
                frame.dispose();
                GuiArtikelAdd neuesFenster = new GuiArtikelAdd();
            }

            if (action.getSource() == btnArtikelChange){
                frame.dispose();
                //ArtikelChange muss selected artikel übergeben werden!!!
                GuiArtikelChange neuesFenster = new GuiArtikelChange();
            }

            if (action.getSource() == btnArtikelDel){

            }

            if (action.getSource() == btnSuche){

            }
        }



}