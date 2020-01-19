package Gui;

/*
 * Main Class zum starten des Programmes.
 * Ruft GuiHauuptfenster auf und lädt die Textdateien.
 */

public class GUI {
   public GUI(){}

    public static void main(String[] args){
    	Src.Datenverwaltung.load();
        GuiHauptfenster gui = new GuiHauptfenster();
    }

}

