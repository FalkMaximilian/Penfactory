public int maxLength = 20;

public class Artikel {
	
	// Attribute von Artikel
	public String produktBezeichnung;
	public String kategorie;
	public int anzahl;
	public double gewicht;
	public double preis;
	public int platzNummer;
	
	
	// Konstruktor
	public Artikel(String name, String kat, int anz, double gew, double preis, int platz) {
		this.produktBezeichnung = name;
		this.kategorie = kat;
		this.anzahl = anz;
		this.gewicht = gew;
		this.platzNummer = platz;
		
		System.out.println("Artikel " + name + " was created!");
	}
	
	
	// Getter - Methoden
	public String getName() {
		System.out.println("GET: getName() returned " + this.produktBezeichnung);
		return this.produktBezeichnung;
	}
	
	public String getKategorie() {
		System.out.println("GET: getKategorie() returned " + this.kategorie);
		return this.kategorie;
	}
	
	public int getAnzahl() {
		System.out.println("GET: getAnzahl() returned " + this.anzahl);
		return this.anzahl;
	}
	
	public double getGewicht() {
		System.out.println("GET: getGewicht() returned " + this.gewicht);
		return this.gewicht;
	}
	
	public double getPreis() {
		System.out.println("GET: getPreis() returned " + this.preis);
		return this.preis;
	}
	
	public int getPlatznummer() {
		System.out.println("GET: getPlatznummer() returned " + this.platzNummer);
		return this.platzNummer;
	}
	
	
	// Setter - Methoden
	public boolean setName(String newName) {
		if (newName.length() > maxLength) {
			System.out.println("SET: setName() failed -> Input too long");
			return false;
		} else {
			this.produktBezeichnung = newName;
			System.out.println("SET: produktBezeichnung was set to: " + newName);
			return true;
		}
	}
	
	public boolean setKategorie(String newKategorie) {
		if (newKategorie.length() > maxLength) {
			System.out.println("SET: setKategorie() failed -> Input too long");
			return false;
		} else {
			this.kategorie = newKategorie;
			System.out.println("SET: kategorie was set to: " + newKategorie);
			return true;
		}
	}
	
	public boolean setAnzahl(int newAnzahl) {
		if (newAnzahl < 0) {
			System.out.println("SET: setAnzahl() failed -> Input is smaller than 0");
			return false;
		} else {
			this.anzahl = newAnzahl;
			System.out.println("SET: anzahl was set to: " + newAnzahl);
			return true;
		}
	}
	
	public boolean setGewicht(double newGewicht) {
		if (newGewicht < 0) {
			System.out.println("SET: setGewicht() failed -> Input is smaller than 0");
			return false;
		} else {
			this.gewicht = newGewicht;
			System.out.println("SET: gewicht was set to: " + newGewicht);
			return true;
		}
	}
	
	public boolean setPreis(double newPreis) {
		if (newPreis < 0) {
			System.out.println("SET: setPreis() failed -> Input is smaller than 0");
			return false;
		} else {
			this.preis = newPreis;
			System.out.println("SET: preis was set to: " + newPreis);
			return true;
		}
	}
	
	public boolean setPlatznummer(int newPlatz) {
		if (newPlatz < 0) {
			System.out.println("SET: setPlatznummer() failed -> Input is smaller than 0");
			return false;
		} else {
			this.platzNummer = newPlatz;
			System.out.println("SET: platzNummer was set to: " + newPlatz);
			return true;
		}
	}
	
	public boolean chageAnzahl(int amount) {
		if ((this.anzahl + amount) < 0) {
			System.out.println("CHANGE: anzahl could't be changed -> Not enough Atrikel left");
			return false;
		} else {
			this.anzahl = this.anzahl + amount;
			System.out.ptintln("CHANGE: anzahl was changed by " + amount);
			return true;
		}
	}
}