import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Datenverwaltung {
	
	static private List<Artikel> a_list;
	static private List<Kategorie> k_list;
	static private List<Double> regalGewicht;
	
	public boolean init(String passwort) {
		if (passwort == "123456") {
			addArtikel(new ArrayList<>(Arrays.asList(
					new Artikel("Stift", "Schreibwaren", 3, 0.2, 1.5, 11223344),
					new Artikel("Radiergummi", "Schreibwaren", 2, 0.1, 1.99, 11223345)
			)));
			
			k_list = new ArrayList<>(Arrays.asList(
					new Kategorie("Schreibwaren", 2)
			));
			
			regalGewicht = new ArrayList<>(Arrays.asList(
					0.8
			));
			
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Ändere das Passwort auf ein neues.
	 * 
	 * @param oldPas Das aktuelle Passwort des Programms zur authentifizierung.
	 * @param newPas Das neue Passwort das gesetzt werden soll.
	 * @return boolean Wenn das Passwort erfolgreich geändert wurde, wird true zurückgegeben. In allen anderen Fällen false.
	 */
	public boolean changePas(String oldPas, String newPas) {
		if (oldPas == "123456") {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Diese funktion dient zum suchen nach {@link Artikel}n welche dem Eingabewert in entsprechender Eigenschaft entsprechen. Für den Fall dass nach
	 * einem ungültigen oder falschen Wert gesucht wird, wird eine leere Liste zurückgegeben.
	 * 
	 * @param wert Der Wert nachdem gesucht wird im String Format.
	 * @param para Die {@link Eigenschaft} nach welcher gesucht wird.
	 * @return List<{@link Artikel}> Es wird immer eine Liste zurückgegeben. Im Falle eines Fehlers wird eine leere Liste zurückgegeben und
	 *  auf der Konsole wird eine entsprechende Meldung ausgegeben.
	 */
	public List<Artikel> search(String wert, Eigenschaft para) {
		List<Artikel> searchResult = new ArrayList<>(); // Eine leere Liste, die am Ende mit allen gefundenen Objekten zurückgegeben wird.
		int a_list_size = a_list.size();				// Länge der Liste
		
		if (para == Eigenschaft.Produktbezeichnung) { 	// Wenn nach der Produktbezeichnung gesucht wird
			for (int i = 0; i < a_list_size; i++) {		// Überprüfe jedes Element der Liste
				Artikel tempElement = a_list.get(i);	// Hole das aktuelle Element
				if (tempElement.produktBezeichnung == wert) {
					searchResult.add(tempElement);		// Wenn die Produktbezeichnung stimmt, füge es zur Liste hinzu
				}
			}
			return searchResult;	// Nachdem alle Artikel überprüft wurden, gibt die Liste zurück
		} else if (para == Eigenschaft.Kategorie) {		// Wenn nach der Kategorie gesucht wird
			for(int i = 0; i < a_list_size; i++) {		// Überprüfe jedes Element der Liste
				Artikel tempElement = a_list.get(i);	// Hole das aktuelle Element der Liste
				if (tempElement.kategorie == wert) {
					searchResult.add(tempElement);		// Wenn die Kategorie stimmt, füge es zur Liste hinzu
				}
			}
			return searchResult;	// Nachdem alle Artikel überprüft wurden, gibt die Liste zurück
		} else if (para == Eigenschaft.Anzahl) {
			
			int wert_int;
			try {
				wert_int = Integer.parseInt(wert);	// Try and convert the String to int
			} catch (NumberFormatException e) {
				System.out.println("DATENVERWALLTUNG -> SEARCH -> ANZAHL: Input konnte nicht zu int gecaster werden.");
				return searchResult;	// Return an Empty List
			}
			
			if (wert_int < 0) {
				System.out.println("DATENVERWALLTUNG -> SEARCH -> ANZAHL: Input ist ungültig");
				return searchResult;	// Return an Empty List
			} else {
				for (int i = 0; i < a_list_size; i++) {		// Überprüfe jedes Element der Liste
					Artikel tempElement = a_list.get(i);	// Hole das aktuelle Element der Liste
					if(tempElement.anzahl == wert_int) {
						searchResult.add(tempElement);	// Wenn die Anzahl stimmt, füge es zur Liste hinzu
					}
				}
				return searchResult;	// Nachdem alle Artikel überprüft wurden, gibt die Liste zurück
			}
		} else if (para == Eigenschaft.Gewicht) {
			
			double wert_double;
			try {
				wert_double = Double.parseDouble(wert);
			} catch (NumberFormatException e) {
				System.out.println("DATENVERWALTUNG -> SEARCH -> GEWICHT: Input konnte nicht zu double gecastet werden.");
				return searchResult;	// Gib eine leere Liste zurück
			}
			
			if (wert_double < 0) {
				System.out.println("DATENVERWALLTUNG -> SEARCH -> GEWICHT: Input ist ungültig");
				return searchResult;	// Gib eine leere Liste zurück
			} else {
				for (int i = 0; i < a_list_size; i++) {			// Überprüfe jedes Element der Liste
					Artikel tempElement = a_list.get(i);		// Hole das aktuelle Element der Liste
					if (tempElement.gewicht == wert_double) {
						searchResult.add(tempElement);	// Wenn das Gewicht stimmt, füge tempElement zur Liste hinzu
					}
				}
				return searchResult;	// Nachdem alle Artikel überprüft wurden, gibt die Liste zurück
			}
			
		} else if (para == Eigenschaft.Preis) {
			
			double wert_double;
			try {
				wert_double = Double.parseDouble(wert);
			} catch (NumberFormatException e) {
				System.out.println("DATENVERWALTUNG -> SEARCH -> PREIS: Input konnte nicht zu double gecastet werden.");
				return searchResult;	// Gib eine leere Liste zurück
			}
			
			if (wert_double < 0) {
				System.out.println("DATENVERWALLTUNG -> SEARCH -> PREIS: Input ist ungültig");
				return searchResult;	// Gib eine leere Liste zurück
			} else {
				for (int i = 0; i < a_list_size; i++) {			// Überprüfe jedes Element der Liste
					Artikel tempElement = a_list.get(i);		// Hole das aktuelle Element der Liste
					if (tempElement.preis == wert_double) {
						searchResult.add(tempElement);	// Wenn der Preis stimmt, füge tempElement zur Liste hinzu
					}
				}
				return searchResult;	// Nachdem alle Artikel überprüft wurden, gibt die Liste zurück
			}
		} else {
			
			int wert_int;
			try {
				wert_int = Integer.parseInt(wert);
			} catch (Exception e) {
				System.out.println("DATENVERWALLTUNG -> SEARCH -> PLATZNUMMER: Input konnte nicht zu int gecaster werden.");
				return searchResult;	// Return an Empty List
			}
			
			if (wert_int < 0) {
				System.out.println("DATENVERWALLTUNG -> SEARCH -> PLATZNUMMER: Input ist ungültig");
				return searchResult;	// Return an Empty List
			} else {
				for (int i = 0; i < a_list_size; i++) {
					Artikel tempElement = a_list.get(i);
					if (tempElement.platzNummer == wert_int) {
						searchResult.add(tempElement);
					}
				}
				return searchResult;
			}
		}
	}
	
	public void load() {
		
	}
	
	public void save(Artikel a) {
		
	}
	
	public void save(Kategorie k) {
		
	}
	
	public boolean addArtikel(Artikel neuerArtikel) {
		
	}
	
	public Artikel getArtikel(String name) {
		
	}
	
	public boolean changeArtikel(String name, Artikel a) {
		
	}
	
	public boolean deleteArtikel(String name) {
		
	}
	
	public Kategorie getKategorie(String name) {
		
	}
	
	public boolean addKategorie(Kategorie k) {
		
	}
}






















