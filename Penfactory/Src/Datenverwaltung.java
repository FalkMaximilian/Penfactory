package Src;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.sound.sampled.LineListener;

public class Datenverwaltung {
	
	private static Datenverwaltung singleton;
	
	private static List<Artikel> a_list = new ArrayList<Artikel>();
	private static List<Kategorie> k_list = new ArrayList<Kategorie>();
	private static List<Double> regalGewicht = new ArrayList<Double>();
	
	private Datenverwaltung() {}
	
	public static Datenverwaltung getSingleton() {
		if (Datenverwaltung.singleton == null) {
			Datenverwaltung.singleton = new Datenverwaltung();
		}
		Datenverwaltung.load();
		return singleton;
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
	public static List<Artikel> search(String wert, Eigenschaft para) {
		List<Artikel> searchResult = new ArrayList<>(); // Eine leere Liste, die am Ende mit allen gefundenen Objekten zurückgegeben wird.
		int a_list_size = a_list.size();				// Länge der Liste
		
		if (para == Eigenschaft.Alle) {	// Durchsuche alle Eigenschaften der Artikel
			for (int i = 0; i < a_list_size; i++) {
				Artikel tempArtikel = a_list.get(i);	// Hole den aktuellen Artikel
				
				// Fuege den Artikel zu der Liste hinzu, ...
				if (tempArtikel.produktBezeichnung.contains(wert)) {
					searchResult.add(tempArtikel);	// Wenn die Produkbezeichnung einen Teil des gesuchten entaelt
				} else if (tempArtikel.kategorie.contains(wert)) {
					searchResult.add(tempArtikel);	// Wenn die Kategorie einen Teil des gesuchten enthaelt
				} else if (Integer.toString(tempArtikel.anzahl).contains(wert)) {
					searchResult.add(tempArtikel);	// Wenn die Anzahl einen Teil des gesuchten enthaelt
				} else if (Double.toString(tempArtikel.gewicht).contains(wert)) {
					searchResult.add(tempArtikel);	// Wenn das Gewicht einen Teil des gesuchten enthaelt
				} else if (Double.toString(tempArtikel.preis).contains(wert)) {
					searchResult.add(tempArtikel);	// Wenn der Preis einen Teil des gesuchten enthaelt
				} else if (Integer.toString(tempArtikel.platzNummer).contains(wert)) {
					searchResult.add(tempArtikel);	// Wenn die PlatzNummer einen Teil des gesuchten enthaelt
				}
			}
			return searchResult;
		} else if (para == Eigenschaft.Produktbezeichnung) { 	// Wenn nach der Produktbezeichnung gesucht wird
			for (int i = 0; i < a_list_size; i++) {		// Überprüfe jedes Element der Liste
				Artikel tempElement = a_list.get(i);	// Hole das aktuelle Element
				if (tempElement.produktBezeichnung.contains(wert)) {
					searchResult.add(tempElement);		// Wenn die Produktbezeichnung stimmt, füge es zur Liste hinzu
				}
			}
			return searchResult;	// Nachdem alle Artikel überprüft wurden, gibt die Liste zurück
		} else if (para == Eigenschaft.Kategorie) {		// Wenn nach der Kategorie gesucht wird
			for(int i = 0; i < a_list_size; i++) {		// Überprüfe jedes Element der Liste
				Artikel tempElement = a_list.get(i);	// Hole das aktuelle Element der Liste
				if (tempElement.kategorie.contains(wert)) {
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
				for (int i = 0; i < a_list_size; i++) {		// Ueberpruefe jedes Element der Liste
					Artikel tempElement = a_list.get(i);	// Hole das aktuelle Element
					if (tempElement.platzNummer == wert_int) {
						searchResult.add(tempElement);	// Wenn die Platznummer stimmt, füge tempElement zur Liste hinzu
					}
				}
				return searchResult; // Nachdem alle Artikel überprüft wurden, gibt die Liste zurück
			}
		}
	}
	
	/**
	 * Getter fuer die Liste an Kategorien
	 * 
	 * @return List<Kategorie> Die Liste aller Kategorien
	 */
	public static List<Kategorie> getKList() {
		return k_list;
	}
	
	/**
	 * Getter fuer die Liste an Artikeln
	 * 
	 * @return List<Artikel> Die Liste aller Artikel
	 */
	public static List<Artikel> getAList() {
		return a_list;
	}
	
	/**
	 * Getter fuer die Regalgewichte
	 * 
	 * @return Lis<Double> Die Liste der RegalGewichter
	 */
	public static List<Double> getGList() {
		return regalGewicht;
	}
	
	/**
	 * Laedt die Datenbanken, in welchen die Artikel, Kategorien und RegalGewichte gespeichert sind in die static Listen.
	 * Falls die Datenbanken noch nicht vorhanden sind, erstelle sie.
	 */
	public static void load() {
		String a_list_fileName = "a_list.txt";	// Dateipfad für die Artikel Datenbank
		String k_list_fileName = "k_list.txt";	// Dateipfad für die Kategorien Datenbank
		String g_list_fileName = "g_list.txt";	// Dateipfad für die Gewichts Datenbank	
		
		// Versuche die Artikel Datenbank zu oeffnen. Wenn das nicht geht, wird ein fehler geworfen.
		try (BufferedReader br = Files.newBufferedReader(Paths.get(a_list_fileName))) {
			
			a_list = new ArrayList<Artikel>();
			List<String> temp_a_list = new ArrayList<>();	// Erstelle eine neue leere Liste
			temp_a_list = br.lines().collect(Collectors.toList());	// Jeder Eintrag der Liste ist eine Zeile aus dem txt Dokument
			
			for (int i = 0; i < temp_a_list.size(); i++) {
				String tempElement = temp_a_list.get(i);	// Nehme das i-te Element aus der Liste.
				String[] tempArray = tempElement.split("Ƣ");	// Trenne das Element bei jedem :
				
				String produktBezeichnung = tempArray[0];	// Speichere ProduktBezeichnung und Kategorie in variablen
				String kategorie = tempArray[1];
				
				// Definiere die Variablen anzahl, gewicht, preis und pllatznummer
				int anzahl;
				double gewicht;
				double preis;
				int platzNummer;
				
				try {
					// Versuche die Anzahl von String zu int zu parsen. Wirft einen Fehler, falls das nicht funktioniert.
					anzahl = Integer.parseInt(tempArray[2]);
					try {
						// Versuche das Gewicht von String zu double zu parsen. Wirft einen Fehler, falls das nicht funktioniert.
						gewicht = Double.parseDouble(tempArray[3]);	
						try {
							// Versuche den Preis von String zu double zu parsen. Wirft einen Fehler, falls das nicht funktioniert.
							preis = Double.parseDouble(tempArray[4]);
							try {
								// Versuche die Platznummer von String zu int zu parsen. Wirft einen Fehler, falls das nicht funktioniert.
								platzNummer = Integer.parseInt(tempArray[5]);
								a_list.add(new Artikel(produktBezeichnung, kategorie, anzahl, gewicht, preis, platzNummer));	// Füge nun den Artikel zur Liste hinzu, da alle Werte korrekt sind.
							} catch (NumberFormatException s) {
								System.out.println("LOAD: Fehler beim platzNummer casting!");
							}
						} catch (NumberFormatException p) {
							System.out.println("LOAD: Fehler beim preis casting!");
						}
					} catch (NumberFormatException d) {
						System.out.println("LOAD: Fehler beim gewicht casting!");
					}
				} catch (NumberFormatException f) {
					System.out.println("LOAD: Fehler beim anzahl casting!");
				}
			}
		} catch (IOException e) {
			// Erstelle eine neue Artikel Datenbank, da noch keine Vorhanden ist. Sonst wäre kein Fehler geworfen worden.
			File a_list_file = new File(a_list_fileName);
			try {
				Boolean result = a_list_file.createNewFile();	// true, wenn Datei erstellt wurde. False, falls das nicht geklappt hat.
				if (result) {
					System.out.println("File: a_list has been created!");
				}
			} catch (IOException e1) {
				System.out.println("Couldn't open a_list file and creating a new one failed.");	// Sollte dieser Bllock erreicht werden, so konnte die Datei nicht geoeffnet werden und es konnt auch keine neue Datei angelegt werden.
			}
		}
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(k_list_fileName))) {
			
			k_list = new ArrayList<Kategorie>();	// Erstelle eine Leere Liste
			List<String> temp_k_list = new ArrayList<>();	// Erstelle eine weiter lerre Liste
			temp_k_list = br.lines().collect(Collectors.toList());	// Jedes element der Liste entspricht einer zeile der kategorien Datenbank
			
			for (int i = 0; i < temp_k_list.size(); i++) {
				String tempElement = temp_k_list.get(i);	// Hole die akteulle zeile der Kategoriendatenbank
				String[] tempArray = tempElement.split("Ƣ");	// Teile sie beim Trennzeichen
				
				String name = tempArray[0];	// Der Name der Kategorie ist das erste Element
				int anzahl;	// Deklariere einen int
				
				try {
					anzahl = Integer.parseInt(tempArray[1]);	// Versuche aus der Datenbank die Anzahl von String zu int zu casten
					k_list.add(new Kategorie(name, anzahl));	// Wenn das funktioniert hat füge es an die Liste an
				} catch (NumberFormatException e) {
					System.out.println("LOAD: Fehler beim anzahl casting!");
				}
			}
		} catch (Exception e) {
			File k_list_file = new File(k_list_fileName);	// Es ist noch keine Datenbank fuer Kategorien vohanden. Erstelle eine
			try {
				Boolean result = k_list_file.createNewFile();	// Versuche zu erstellen. Wenn erfolgreich, wird true zurueckgegeben
				if (result) {
					System.out.println("File: k_list has been created!");
				}
			} catch (IOException e2) {
				System.out.println("Couldn't open k_list file and creating a new one failed.");
			}
		}
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(g_list_fileName))) {
			regalGewicht = new ArrayList<Double>(); 	// Erstelle eine leere Liste
			List<String> temp_g_list = new ArrayList<>();	// Erstelle noch eine leere Liste
			temp_g_list = br.lines().collect(Collectors.toList());	// Jedes Element der Liste entspricht einer Zeile aud der regalGewicht Datenbank
			
			for (int i = 0; i < 1000; i++) {
				String tempElement = temp_g_list.get(i);	// Hole das aktuelle Element
				
				double gewicht;	// Deklariere einen double
				try {
					gewicht = Double.parseDouble(tempElement);	// Versuche den String aus der Datenbank in Double zu casten
					regalGewicht.add(gewicht);	// Wenn das erfolgreich war, fuege es zu der Liste hinzu
				} catch (NumberFormatException e) {
					System.out.println("LOAD: Fehler beim gewicht casting!");
				}
			}
		} catch (Exception e) {
			File g_list_file = new File(g_list_fileName);	// Es gibt noch keine Datenbank fuer die RegalGewichter. Erstelle eine
			try {
				Boolean result = g_list_file.createNewFile();	// Versuche die Datenbank zu erstellen
				if (result) {
					BufferedWriter bWriter = null;	// Wenn die Datenbank erstellt wurde ...
					try {
						bWriter = new BufferedWriter(new FileWriter(g_list_file)); 	// Erstelle einen schreiber ...
						for (int i = 0; i < 1000; i++) {
							regalGewicht.add(0.0);	// Und Initialisiere 1000 Zeilen mit 0
							bWriter.write("0");
							if (i != 999) {
								bWriter.newLine();
							}
							bWriter.flush();
						}
					} catch (IOException ioe) {
						ioe.printStackTrace();
					} finally {
						if (bWriter != null) try {
							bWriter.close();
						} catch (IOException ioe2) {
							// just ignore it.
						}
					}
					System.out.println("File: regalGewicht has been created!");
				}
			} catch (Exception e2) {
				System.out.println("Couldn't open k_list file and creating a new one failed.");
			}
		}
	}
	
	/**
	 * Speichere die Liste an Artikeln in der Datenbank
	 */
	protected static void save_a_list() {
		PrintWriter writer;	// Deklariere eine Schreiber
		try {
			writer = new PrintWriter("a_list.txt");	// Lösche die veralteten Daten
			writer.close();
			
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter("a_list.txt", true)); // Versuche einen neuen Schreiber zu erstellen fuer die Artikel Datenbank
				
				int a_list_size = a_list.size();	// Groesse der Liste
				for (int i = 0; i < a_list_size; i++) {
					Artikel tempArtikel = a_list.get(i);	// Hole den aktuellen Artikel
					// Schreibe on dem richtigen Format die Daten aus dem Artikel in eine Zeile
					bw.write(tempArtikel.produktBezeichnung + "Ƣ" + tempArtikel.kategorie + "Ƣ" + tempArtikel.anzahl + "Ƣ" + tempArtikel.gewicht + "Ƣ" + tempArtikel.preis + "Ƣ" + tempArtikel.platzNummer);
					bw.newLine();	// Fuege einen Zeilenumbruch ein
					bw.flush();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (bw != null) try {
					bw.close();
				} catch (IOException ioe2) {
					// just ignore it.
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Speichere die Liste an Kategorien in die Datenbank
	 */
	protected static void save_k_list() {
		PrintWriter writer; // Deklariere einen Schreiber
		try {
			writer = new PrintWriter("k_list.txt");	// Loesche die veralteten Daten
			writer.close();
			
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter("k_list.txt", true));	// Versuche einen neuen Schreiber zu erstellen fuer die Kategorien Datenbank
				
				int k_list_size = k_list.size();	// Groesse der Liste
				for (int i = 0; i < k_list_size; i++) {
					Kategorie tempKategorie = k_list.get(i);	// Hole das aktuelle Element
					bw.write(tempKategorie.name + "Ƣ" + tempKategorie.artikelAnzahl);	// Schreibe die Daten im richtigen Format in die Datenbank in eine eigene Zeile
					bw.newLine();	// Fuege einen Zeilenumbruch ein
					bw.flush();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (bw != null) try {
					bw.close();
				} catch (IOException ioe2) {
					//Just ignore it
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Speicher die Liste an Regalgewichten
	 */
	protected static void save_g_list() {
		PrintWriter writer;	// Erstelle einen Schreiber
		try {
			writer = new PrintWriter("g_list.txt");	// Lösche die veralteten Daten
			writer.close();
			
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter("g_list.txt", true));	// Erstelle einen neuen Schreiber fuer die Gewicht Datenbank
				for (int i = 0; i < 1000; i++) {
					double tempGewicht = regalGewicht.get(i);	// Hole das aktuelle Element
					bw.write(Double.toString(tempGewicht));	// Schreibe das Gewicht in eine eigene Zeile
					if (i != 999) {
						bw.newLine();	// Fuege eunen Zeilenumbruch ein
					}
					bw.flush();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (bw != null) try {
					bw.close();
				} catch (IOException ioe2) {
					//Just ignore it
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fuege einen Artikel zu der Liste an Artikeln hinzu. Wenn es den Artikel bereits gibt, oder die Platznummer bereits
	 * vergeben ist, oder das Maximalgewicht fuer das Regal ueberschritten wird, wird false zurueckgegeben. Die dazugehoerige Kategorie
	 * wird ebenfalls geupdated.
	 * 
	 * @param neuerArtikel Der neue Artikel der hinzugefuegt werden soll
	 * @return boolean Es wird false zurueckgegeben, wenn der Artikel nicht hinzugefuegt werden konnte
	 */
	public static boolean addArtikel(Artikel neuerArtikel) {
		
		int a_list_size = a_list.size();	// Die groesse der Liste
		for (int i = 0; i < a_list_size; i++) {
			Artikel tempElement = a_list.get(i);	// Hole das aktuelle Element
			if (tempElement.equals(neuerArtikel)) {	// Gibt es bereits einen Artikel mit diesem Namen, so wird der Artikel nicht hinzugefuegt
				System.out.print("DATENVERWALTUNG -> ADDARTIKEL: Name schon vergeben");
				return false;
			} else if (tempElement.platzNummer == neuerArtikel.platzNummer) { // Gibt es bereits einen Artikel mit dieser Platznummer, so wird der Artikel nicht hinzugefuegt
				System.out.print("DATENVERWALTUNG -> ADDARTIKEL: Platznummer schon vergeben");
				return false;
			}
		}
		
		int katIndex = stringToKatIndex(neuerArtikel.kategorie);	// Index der kategorie in der kategorienListe
		if (katIndex != -1) { // Gibt es die kategorie so...
			Kategorie kat = k_list.get(katIndex);	// Hole die Kategorie
			kat.increase();	// erhoehe die Anzahl um 1
			
			int nummer = (int) (neuerArtikel.platzNummer/1000);	// Die Regalnummer
			double gewicht = regalGewicht.get(nummer);	// Das Gewicht mit welchem dieses Regal belastet ist
			gewicht += (neuerArtikel.gewicht * neuerArtikel.anzahl);	// Fuege das gewicht welches durch den neuen Artikel hinzukommt zu dem bestehenden hinzu
			
			if (gewicht < 10000) {	// Solange das Gewicht nicht die maximal zulaessigen 10t uebersteigt
				regalGewicht.set(nummer, gewicht);	// Speichere das neue gewicht
				save_g_list();
				
				k_list.set(katIndex, kat);	// Speichere die geupdatete Kategorie
				save_k_list();
				
				a_list.add(neuerArtikel);	// Speichere den neuen Artikel
				save_a_list();
				
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Suche einen Artikel mit einer bestimmeten Produktbezeichnung und gib ihn zurück. Wird kein übereinstimmender Artikel gefunden
	 * so wird null zurückgegeben. Dies muss von dem Aufrufenden abgefangen werden.
	 * 
	 * @param name Die {@link Artikel#produktBezeichnung} des Artikels, der geholt werden soll.
	 * @return {@link Artikel} Der Artikel mit gesuchter Produktbezeichnung wird zurückgegeben. Wird kein passender gefunden,
	 * wird null zurückgegeben.
	 */
	public static Artikel getArtikel(String name) {
		int a_list_size = a_list.size();	// Die laenge der Liste
		
		for (int i = 0; i < a_list_size; i++) {
			Artikel tempArtikel = a_list.get(i);	// Hole den akteullen Artikel
			if (tempArtikel.produktBezeichnung.equalsIgnoreCase(name)) {
				return tempArtikel; // Gib ihn zurueck wenn der Name bis auf Grossschreibung uebereinstimmt.
			}
		}
		
		System.out.println("DATENVERWALTUNG -> GETARTIKEL: Es wurde kein übereinstimmender Artikel in a_list gefunden.\n" + name);
		return null; // Wird der Artikel nicht gefunden, gib null zurueck
	}
	
	/**
	 * Aendere einen Artikel und seine Attribute.
	 * 
	 * 
	 * @param name Der name des Artikels, der geaendert werden soll
	 * @param a	Der Artikel der den Alten ersetzt. Also die neue Version des Artikels
	 * @return	boolean Gibt true zurueck wenn der Artikel erfolgreich geaendert wurde. False andernfalls
	 */
	public static boolean changeArtikel(String name, Artikel a) {
		int a_list_size = a_list.size();	// Die Laenge der Liste
		
		for (int i = 0; i < a_list_size; i++) {
			Artikel tempArtikel = a_list.get(i);	// Hole den aktuellen Artikel
			if (tempArtikel.produktBezeichnung.equalsIgnoreCase(name)) {	// Wenn der gesuchte Artikel gefunden wurde
				
				int katIndex = stringToKatIndex(tempArtikel.kategorie);	// Hole den alten kategorie index
				int newKatIndex = stringToKatIndex(a.kategorie);	//  Hole den neuen Kategorieindex
				
				Kategorie oldKategorie = k_list.get(katIndex);		// Hole die alte kategorie
				Kategorie newKategorie = k_list.get(newKatIndex);	// Hole die neue Kategorie
				
				if (!tempArtikel.kategorie.equalsIgnoreCase(name)) {	// Wenn die alte und die neue kategorie nicht dieselbe sind
					oldKategorie.decrease();	// Verringere die alte um 1
					newKategorie.increase();	// erhoehe die neue um 1
				}
				
				int platzNummerAlt = (int) (tempArtikel.platzNummer / 1000);	// Die alte Regalnummer in dem der Artikel steht
				int platzNummerNeu = (int) (a.platzNummer / 1000);	// Die neue Regalnummer in dem der Artikel steht
					
				if (platzNummerAlt != platzNummerNeu) {	// Wenn die alte und die neue Regalnummer nicht dieselben sind
					for (int j = 0; j < a_list_size && j != i; j++) {
						Artikel tempArtikel2 = a_list.get(j);	// Hole den aktuellen Artikel	
						if (platzNummerNeu == tempArtikel2.platzNummer) {	// Wenn der Platz schon vergeben ist
							return false;	// Kann der Artikel nicht geaendert werden.
						}
					}
				}
				
				if (tempArtikel.anzahl != a.anzahl) {	// Wenn die alte Anzahl des Artikels und die neue nicht dieselbe sind
					double gewichtAlt = regalGewicht.get(platzNummerAlt);	// Hole das alte gewciht
					gewichtAlt -= tempArtikel.anzahl * tempArtikel.gewicht;	// Ziehe das Gewicht von der alten Platznummer ab
					
					double gewichtNeu = regalGewicht.get(platzNummerNeu);	// Hole das gewicht des neuen Platzes
					gewichtNeu += a.anzahl * a.gewicht;	// Und fuege das Gewicht des Artikels dort hinzu
					
					if (gewichtNeu < 10000) {	// Wenn das maximalgewicht nicht 10t ueberschreitet
						regalGewicht.set(platzNummerAlt, gewichtAlt);	// Speichere das neue gewicht des alten platzes
						regalGewicht.set(platzNummerNeu, gewichtNeu);	// Speicher das neue gewicht des neuen Platzes
						save_g_list();
					}
				}
				
				k_list.set(katIndex, oldKategorie);	// Speichere die Kategorie
				k_list.set(newKatIndex, newKategorie);
				save_k_list();
				
				a_list.set(i, a);	// Speichere den Adtikel
				save_a_list();
				return true;
			}
		}
		
		System.out.println("DATENVERWALTUNG -> CHANGEARTIKEL: Es wurde kein übereinstimmender Artikel in a_list gefunden.\n" + name);
		return false;
	}
	
	/**
	 * Loesche den Artikel, falls moeglich.
	 * 
	 * @param name Der Name des Artikels der geloescht werden soll
	 * @return boolean Gib true zurueck, wenn der Artikel erfolgreich geloescht wurde. False andernfalls
	 */
	public static boolean deleteArtikel(String name) {
		int a_list_size = a_list.size();	// Die leange der Liste
		
		for (int i = 0; i < a_list_size; i++) {
			Artikel tempArtikel = a_list.get(i);	// Hole das aktuelle Element
			if (tempArtikel.produktBezeichnung.equalsIgnoreCase(name)) {	// Wenn der Name bis auf Grosschreibung uebereinstimmt
				int katIndex = stringToKatIndex(tempArtikel.kategorie);	// Hole den Index der Kategorie
				if (katIndex > -1) {	// Wenn die Kategoerie gefunden wurde
					Kategorie tempKategorie = k_list.get(katIndex);	// Hole die Kategorie
					tempKategorie.decrease();	// Senke die Anzahl der kategorie um 1
					
					int regalNummer = (int) tempArtikel.platzNummer / 1000;	// Die Regalnummer
					double gewicht = regalGewicht.get(regalNummer);	// Das gewicht des Regals
					gewicht -= (tempArtikel.gewicht * tempArtikel.anzahl);	// Verringere das Gewicht auf dem Regal um das gewicht des Artikels
					regalGewicht.set(regalNummer, gewicht);	// Dann Speichere die Gewichtsdatenbank
					save_g_list();
					
					k_list.set(katIndex, tempKategorie);	// Speichere die Kategorie Datenbank
					save_k_list();
					
					a_list.remove(i);	// Speichere die Artikel Datenbank
					save_a_list();
					return true;
				} else {
					System.out.println("DATENVERWALTUNG -> DELETEARTIKEL: Kategorie konnte nicht gefunden werden! Index out of range!");
					return false;
				}
			}
		}
		
		System.out.println("DATENVERWALTUNG -> DELETEARTIKEL: Es wurde kein übereinstimmender Artikel in a_list gefunden.\n" + name);
		return false;
	}
	
	/**
	 * Gib die Kategorie mit dem Namen zurueck. Bis auf Grossschreibung muss der Name ident sein.
	 * 
	 * @param name Der Name der Kategorie, die zurueckgegeben werden soll
	 * @return Kategorie Es Wird das gesuchte Objekt Kategorie zurueckgegeben, falls es nicht gefunden wird, wird null zurueckgegeben.
	 */
	public static Kategorie getKategorie(String name) {
		int k_list_size = k_list.size();	// Die Leange der Liste
		
		for (int i = 0; i < k_list_size; i++) {
			Kategorie tempKategorie = k_list.get(i);	// Hole das aktuellle Element
			if (tempKategorie.name.equalsIgnoreCase(name)) {	// Wenn der Name bis auf Grossschreibung ident ist
				return tempKategorie;	// Gib diese kategorie zurueck
			}
		}
		System.out.println("DATENVERWALTUNG -> GETKATEGORIE: Es wurde keine übereinstimmende Kategorie in k_list gefunden.\n" + name);
		return null;
	}
	
	/**
	 * Die Funktion fügt eine Kategorie zu der Liste hinzu. Sie gibt einen boolean zurück. True wenn
	 * das hinzufügen Erfolgreich war und False falls die Kategorie bereits in der Liste exisitert.
	 * 
	 * @param k Die Kategorie die hinzugefügt werden soll.
	 * @return boolean Es wird false zurückgegeben, falls die Kategorie bereits in der Liste existiert.
	 */
	public static boolean addKategorie(Kategorie k) {
		int k_list_size = k_list.size();	// Die Leange der Liste
		
		for (int i = 0; i < k_list_size; i++) {
			Kategorie tempKategorie = k_list.get(i);	// Hole das aktuelle Element
			if (tempKategorie.equals(k)) {	// Wenn die Kategorie schon vorhanden ist
				return false;	// Gib false zurueck
			}
		}
		k_list.add(k);	// Sonst fuege die Kategorie hinzu
		save_k_list();	// Speichere die Kategorie Datenbank
		return true;
	}
	
	/**
	 * Aendere den Namen einer Kategorie
	 * 
	 * @param name Der Name der Kategorie die geandert werden soll
	 * @param k	Die neue Kategorie, die gespeichert werden soll
	 * @return boolean Gib true zurueck falls die Kategorie erfolgreich umbenannt wurde. False andernfalls
	 */
	public static boolean changeKategorie(String name, Kategorie k) {
		int k_list_size = k_list.size();	// Die leange der Liste
		
		for (int i = 0; i < k_list_size; i++) {
			Kategorie tempElement = k_list.get(i);	// Hole das akteulle Element
			if (tempElement.name.equalsIgnoreCase(name)) {	// Wenn der Name bis auf die Grosschreibung iden tist
				k_list.set(i, k);	// Speichere die Kategorie
				save_k_list();
				
				int a_list_size = a_list.size();	// Die Leange der artikel Liste
				for (int j = 0; j < a_list_size; j++) {
					Artikel tempArtikel = a_list.get(j);	// Hole den aktuellen Artikel
					if(tempArtikel.kategorie.equalsIgnoreCase(tempElement.name)) {	// Wenn die Kategorie des Artikels dem alten Namen entspricht
						tempArtikel.kategorie = k.name;	// Aendere die Kategorie des Artikels auf den neuen Namen der kategorie
						a_list.set(j, tempArtikel);	// Speichere den geaenderten Artikel
						save_a_list();
					}
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Loesche eine Kategorie
	 * 
	 * @param name Der Name der Kategorie die geloescht werden soll
	 * @return boollean Wenn die Kategorie erfolgreich geloescht wurde, wird true zurueckgegeben. False andernfalls
	 */
	protected static boolean deleteKategorie(String name) {
		int a_list_size = a_list.size();	// Die Laenge der Liste
		
		for (int i = 0; i < a_list_size; i++) {
			Artikel tempElementArtikel = a_list.get(i);	// Hole den aktuellen Artikel
			if (tempElementArtikel.kategorie.equalsIgnoreCase(name)) {	// Wenn es noch einen Artikel mit dieser kategorie gibt
				System.out.println("DATENVERWALTUNG -> DELETEKATEGORIE: Kategorie konnte nicht gelöscht werden, da es noch Artikel gibt, die diese zugewiesen haben.");
				return false;	// Lösche die Kategorie nicht und gib false zurueck
			}
		}
		
		int k_list_size = k_list.size();	// Die laenge der kategorien liste
		for (int i = 0; i < k_list_size; i++) {
			Kategorie tempKategorie = k_list.get(i);	// Hole die aktuelle Kategorie
			if (tempKategorie.name.equalsIgnoreCase(name)) {	// Wenn der Name bis auf Grossschreibung ident ist
				k_list.remove(i);	// Loesche die kategorie
				save_k_list();		// Speichere die Kategorien Datenbank
				return true;	// Gib true zurueck
			}
		}
		
		System.out.println("DATENVERWALTUNG -> DELETEKATEGORIE: Kategorie konnte nicht gelöscht werden, da es diese nicht mehr gibt.");
		return false;	
	}
	
	/**
	 * Suche nach dem Namen einer Kategorie und gib den index in der Kategorien Liste zueuck. Falls die gesuchte kategorie
	 * nicht gefunden werden konnte gib -1 zurueck.
	 * 
	 * @param s Ein String der dem namen der gesuchten kategorie bis auf die großschreibung übereinstimmen muss. 
	 * @return int Der index der Aktegorie wird zurueckgegeben. Wird die Kategorie nicht gefunden, so wird -1 returned.
	 */
	private static int stringToKatIndex(String s) {
		int k_list_size = k_list.size();	// Die Leange der Liste
		
		for (int i = 0; i < k_list_size; i++) {
			Kategorie tempElement = k_list.get(i);	// Hole das aktuelle Element
			if(tempElement.name.equalsIgnoreCase(s)) {	// Wenn der Name bis auf die Grossschreibung ident ist
				return i;	// Gib den Index der Kategorie zurueck
			}
		}
		
		System.out.println("DATENVERWALTUNG -> STRINGTOKAT: Es wurde keine übereinstimmende Kategorie zu " + s + " gefunden.");
		return -1;	// Falls die Kategorie nicht gefunden wurde, gib -1 zurueck
	}
}
