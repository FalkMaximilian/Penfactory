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
import java.util.stream.Collectors;

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
	protected static List<Artikel> search(String wert, Eigenschaft para) {
		List<Artikel> searchResult = new ArrayList<>(); // Eine leere Liste, die am Ende mit allen gefundenen Objekten zurückgegeben wird.
		int a_list_size = a_list.size();				// Länge der Liste
		
		if (para == Eigenschaft.Produktbezeichnung) { 	// Wenn nach der Produktbezeichnung gesucht wird
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
	
	protected static List<Kategorie> getKList() {
		return k_list;
	}
	
	protected static List<Artikel> getAList() {
		return a_list;
	}
	
	protected static void load() {
		
		String a_list_fileName = "a_list.txt";
		String k_list_fileName = "k_list.txt";
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(a_list_fileName))) {
			
			List<String> temp_a_list = new ArrayList<>();
			temp_a_list = br.lines().collect(Collectors.toList());
			
			for (int i = 0; i < temp_a_list.size(); i++) {
				String tempElement = temp_a_list.get(i);
				String[] tempArray = tempElement.split(":");
				
				String produktBezeichnung = tempArray[0];
				String kategorie = tempArray[1];
				
				int anzahl;
				double gewicht;
				double preis;
				int platzNummer;
				
				try {
					anzahl = Integer.parseInt(tempArray[2]);
					try {
						gewicht = Double.parseDouble(tempArray[3]);
						try {
							preis = Double.parseDouble(tempArray[4]);
							try {
								platzNummer = Integer.parseInt(tempArray[5]);
								a_list.add(new Artikel(produktBezeichnung, kategorie, anzahl, gewicht, preis, platzNummer));
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
			File a_list_file = new File(a_list_fileName);
			try {
				Boolean result = a_list_file.createNewFile();
				if (result) {
					System.out.println("File: a_list has been created!");
				}
			} catch (IOException e1) {
				System.out.println("Couldn't open a_list file and creating a new one failed.");
			}
		}
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(k_list_fileName))) {
			
			List<String> temp_k_list = new ArrayList<>();
			temp_k_list = br.lines().collect(Collectors.toList());
			for (int i = 0; i < temp_k_list.size(); i++) {
				String tempElement = temp_k_list.get(i);
				String[] tempArray = tempElement.split(":");
				
				String name = tempArray[0];
				int anzahl;
				
				try {
					anzahl = Integer.parseInt(tempArray[1]);
					k_list.add(new Kategorie(name, anzahl));
				} catch (NumberFormatException e) {
					System.out.println("LOAD: Fehler beim anzahl casting!");
				}
			}
		} catch (Exception e) {
			File k_list_file = new File(k_list_fileName);
			try {
				Boolean result = k_list_file.createNewFile();
				if (result) {
					System.out.println("File: k_list has been created!");
				}
			} catch (IOException e2) {
				System.out.println("Couldn't open k_list file and creating a new one failed.");
			}
		}
	}
	
	protected static void save_a_list() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("a_list.txt");
			writer.close();
			
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter("a_list.txt", true));
				
				int a_list_size = a_list.size();
				for (int i = 0; i < a_list_size; i++) {
					Artikel tempArtikel = a_list.get(i);
					bw.write(tempArtikel.produktBezeichnung + ":" + tempArtikel.kategorie + ":" + tempArtikel.anzahl + ":" + tempArtikel.gewicht + ":" + tempArtikel.preis + ":" + tempArtikel.platzNummer);
					bw.newLine();
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
	
	protected static void save_k_list() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("k_list.txt");
			writer.close();
			
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter("k_list.txt", true));
				
				int k_list_size = k_list.size();
				for (int i = 0; i < k_list_size; i++) {
					Kategorie tempKategorie = k_list.get(i);
					bw.write(tempKategorie.name + ":" + tempKategorie.artikelAnzahl);
					bw.newLine();
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
	
	protected static boolean addArtikel(Artikel neuerArtikel) {
		
		int a_list_size = a_list.size();
		for (int i = 0; i < a_list_size; i++) {
			Artikel tempElement = a_list.get(i);
			if (tempElement.equals(neuerArtikel)) {
				return false;
			}
		}
		
		a_list.add(neuerArtikel);
		
		return true;
	}
	
	/**
	 * Suche einen Artikel mit einer bestimmeten Produktbezeichnung und gib ihn zurück. Wird kein übereinstimmender Artikel gefunden
	 * so wird null zurückgegeben. Dies muss von dem Aufrufenden abgefangen werden.
	 * 
	 * @param name Die {@link Artikel#produktBezeichnung} des Artikels, der geholt werden soll.
	 * @return {@link Artikel} Der Artikel mit gesuchter Produktbezeichnung wird zurückgegeben. Wird kein passender gefunden,
	 * wird null zurückgegeben.
	 */
	protected static Artikel getArtikel(String name) {
		int a_list_size = a_list.size();
		
		for (int i = 0; i < a_list_size; i++) {
			Artikel tempArtikel = a_list.get(i);
			if (tempArtikel.produktBezeichnung == name) {
				return tempArtikel;
			}
		}
		
		System.out.println("DATENVERWALTUNG -> GETARTIKEL: Es wurde kein übereinstimmender Artikel in a_list gefunden.\n" + name);
		return null;
	}
	
	protected static boolean changeArtikel(String name, Artikel a) {
		int a_list_size = a_list.size();
		
		for (int i = 0; i < a_list_size; i++) {
			Artikel tempArtikel = a_list.get(i);
			if (tempArtikel.produktBezeichnung == name) {
				if (tempArtikel.kategorie.toLowerCase() != a.kategorie.toLowerCase()) {
					int katIndex = stringToKatIndex(tempArtikel.kategorie);
					int newKatIndex = stringToKatIndex(a.kategorie);
					
					Kategorie oldKategorie = k_list.get(katIndex);
					Kategorie newKategorie = k_list.get(newKatIndex);
					
					oldKategorie.decrease();
					newKategorie.increase();
					
					k_list.set(katIndex, oldKategorie);
					k_list.set(newKatIndex, newKategorie);
				}
				a_list.set(i, a);
				return true;
			}
		}
		
		System.out.println("DATENVERWALTUNG -> CHANGEARTIKEL: Es wurde kein übereinstimmender Artikel in a_list gefunden.\n" + name);
		return false;
	}
	
	protected static boolean deleteArtikel(String name) {
		int a_list_size = a_list.size();
		
		for (int i = 0; i < a_list_size; i++) {
			Artikel tempArtikel = a_list.get(i);
			if (tempArtikel.produktBezeichnung.toLowerCase() == name.toLowerCase()) {
				int katIndex = stringToKatIndex(tempArtikel.kategorie);
				if (katIndex > -1) {
					Kategorie tempKategorie = k_list.get(katIndex);
					tempKategorie.decrease();
					k_list.set(katIndex, tempKategorie);
				} else {
					System.out.println("DATENVERWALTUNG -> DELETEARTIKEL: Kategorie konnte nicht gefunden werden! Index out of range!");
					return false;
				}
				
				a_list.remove(i);
				return true;
			}
		}
		
		System.out.println("DATENVERWALTUNG -> DELETEARTIKEL: Es wurde kein übereinstimmender Artikel in a_list gefunden.\n" + name);
		return false;
	}
	
	protected static Kategorie getKategorie(String name) {
		int k_list_size = k_list.size();
		
		for (int i = 0; i < k_list_size; i++) {
			Kategorie tempKategorie = k_list.get(i);
			if (tempKategorie.name.toLowerCase() == name.toLowerCase()) {
				return tempKategorie;
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
	protected static boolean addKategorie(Kategorie k) {
		int k_list_size = k_list.size();
		
		for (int i = 0; i < k_list_size; i++) {
			Kategorie tempKategorie = k_list.get(i);
			if (tempKategorie.equals(k)) {
				return false;
			}
		}
		k_list.add(k);
		return true;
	}
	
	protected static boolean changeKategorie(String name, Kategorie k) {
		int k_list_size = k_list.size();
		
		for (int i = 0; i < k_list_size; i++) {
			Kategorie tempElement = k_list.get(i);
			if (tempElement.name.toLowerCase() == name.toLowerCase()) {
				k_list.set(i, k);
				
				//TODO: Ist die anzahl die in der Kategorie steht korrekt?
				int a_list_size = a_list.size();
				for (int j = 0; j < a_list_size; j++) {
					Artikel tempArtikel = a_list.get(j);
					if(tempArtikel.kategorie.toLowerCase() == tempElement.name.toLowerCase()) {
						tempArtikel.kategorie = k.name;
						a_list.set(j, tempArtikel);
					}
				}
				return true;
			}
		}
		return false;
	}
	
	protected static boolean deleteKategorie(String name) {
		int a_list_size = a_list.size();
		
		for (int i = 0; i < a_list_size; i++) {
			Artikel tempElementArtikel = a_list.get(i);
			if (tempElementArtikel.kategorie.toLowerCase() == name.toLowerCase()) {
				System.out.println("DATENVERWALTUNG -> DELETEKATEGORIE: Kategorie konnte nicht gelöscht werden, da es noch Artikel gibt, die diese zugewiesen haben.");
				return false;
			}
		}
		
		int k_list_size = k_list.size();
		for (int i = 0; i < k_list_size; i++) {
			Kategorie tempKategorie = k_list.get(i);
			if (tempKategorie.name.toLowerCase() == name.toLowerCase()) {
				k_list.remove(i);
				return true;
			}
		}
		
		System.out.println("DATENVERWALTUNG -> DELETEKATEGORIE: Kategorie konnte nicht gelöscht werden, da es diese nicht mehr gibt.");
		return false;	
	}
	
	/**
	 * 
	 * @param s Ein String der dem namen der gesuchten kategorie bis auf die großschreibung übereinstimmen muss. 
	 * @return
	 */
	private static int stringToKatIndex(String s) {
		int k_list_size = k_list.size();
		
		for (int i = 0; i < k_list_size; i++) {
			Kategorie tempElement = k_list.get(i);
			if(tempElement.name.toLowerCase() == s.toLowerCase()) {
				return i;
			}
		}
		
		System.out.println("DATENVERWALTUNG -> STRINGTOKAT: Es wurde keine übereinstimmende Kategorie zu " + s + " gefunden.");
		return -1;
	}
}
