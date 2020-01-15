package Src;
/**
* Die Klasse Artikel besitzt die Attribute "produktbezeichnung", "kategorie",
* "anzahl", "gewicht", "preis" und "platzNummer".
*
* Es gibt zu jedem Attribut jeweils eine getter und setter Methode.
*
* Außerdem gibt es eine changeAnzahl Methode, mit welcher die Anzahl um ein delta verändert
* werden kann.
*
* @author 	Maximilian Falk
* @version 	1.0
* @since 	06-01-2020
*/

public class Artikel {
	
	// Attribute von Artikel
	/**
	* Der "name" des Artikels.
	*/
	public String produktBezeichnung;
	
	/**
	* Die {@link Kategorie} des Artikels.
	*/
	public String kategorie;
	
	/**
	* Die Anzahl wie oft es diesen Artikel gibt.
	*/
	public int anzahl;
	
	/**
	* Das Gewicht eines Artikels.
	*/
	public double gewicht;
	
	/**
	* Der Preis eines Artikels.
	*/
	public double preis;
	
	/**
	* Die Platznummer eines Artikels.
	*/
	public int platzNummer;
	
	
	// Konstruktor
	/**
	* Initialisiere einen neuen Artikel.
	*
	* @param name Der Name des zu initialisierenden Artikels.
	* @param kat Die Kategorie welcher der Artikel zugehören soll.
	* @param anz Die Anzahl wie of es den Artikel gibt.
	* @param gew Die Anzahl eines Artikels.
	* @param preis Der Preis eines Artikels.
	* @param platz Die Platznummer des zu erstellenden Artikels.
	*/
	public Artikel(String name, String kat, int anz, double gew, double preis, int platz) {
		this.produktBezeichnung = name;
		this.kategorie = kat;
		this.anzahl = anz;
		this.gewicht = gew;
		this.platzNummer = platz;
		
		System.out.println("Artikel " + name + " was created!");
	}
	
	
	// Getter - Methoden
	/**
	* Hole den Namen des Artikels.
	* @return String Die {@link #produktBezeichnung} des Artikels wird zurückgegeben.
	*/
	public String getName() {
		System.out.println("GET: getName() returned " + this.produktBezeichnung);
		return this.produktBezeichnung;
	}
	
	/**
	* Hole die Kategorie des Artikels.
	* @return String Die {@link #kategorie} des Artikels wird zurückgegeben.
	*/
	public String getKategorie() {
		System.out.println("GET: getKategorie() returned " + this.kategorie);
		return this.kategorie;
	}
	
	/**
	* Hole die Anzahl, wie oft es diesen Artikel gibt.
	* @return int Die {@link #anzahl} des Artikels wird zurückgegeben.
	*/
	public int getAnzahl() {
		System.out.println("GET: getAnzahl() returned " + this.anzahl);
		return this.anzahl;
	}
	
	/**
	* Hole das Gewicht des Artikels.
	* @return double Das {@link #gewicht} eines Artikels wird zurückgegeben.
	*/
	public double getGewicht() {
		System.out.println("GET: getGewicht() returned " + this.gewicht);
		return this.gewicht;
	}
	
	/**
	* Hole den Preis des Artikels.
	* @return double Der {@link #preis} eines Artikels wird zurückgegeben.
	*/
	public double getPreis() {
		System.out.println("GET: getPreis() returned " + this.preis);
		return this.preis;
	}
	
	/**
	* Hole die Platznummer des Artikels.
	* @return int Die {@link #platzNummer} eines Aretikels wird zurückgegeben.
	*/
	public int getPlatznummer() {
		System.out.println("GET: getPlatznummer() returned " + this.platzNummer);
		return this.platzNummer;
	}
	
	
	// Setter - Methoden
	/**
	* Setze die {@link #produktBezeichnung} auf eine neue.
	* @param neuName Die neue {@link #produktBezeichnung} des Artikels.
	* @return boolean Wenn die neue {@link #produktBezeichnung} erfolgreich zugewiesen wurde, wird true zurückgegeben. In allen anderen Fällen false.
	*/
	public boolean setName(String neuName) {
		if (neuName.length() > 20) {
			System.out.println("SET: setName() failed -> Input too long");
			return false;
		} else {
			this.produktBezeichnung = neuName;
			System.out.println("SET: produktBezeichnung was set to: " + neuName);
			return true;
		}
	}
	
	/**
	* Setze die {@link #kategorie} auf eine neue.
	* @param neuKategorie Die neue {@link #kategorie} des Artikels.
	* @return boolean Wenn die neue {@link #kategorie} erfolgreich zugewiesen wurde, wird true zurückgegeben. In allen anderen Fällen false.
	*/
	public boolean setKategorie(String neuKategorie) {
		if (neuKategorie.length() > 20) {
			System.out.println("SET: setKategorie() failed -> Input too long");
			return false;
		} else {
			this.kategorie = neuKategorie;
			System.out.println("SET: kategorie was set to: " + neuKategorie);
			return true;
		}
	}
	
	/**
	* Setze die {@link #anzahl} auf eine neue.
	* @param neuAnzahl Die neue {@link #anzahl} des Artikels.
	* @return boolean Wenn die neue {@link #anzahl} erfolgreich zugewiesen wurde, wird true zurückgegeben. In allen anderen Fällen false.
	*/
	public boolean setAnzahl(int neuAnzahl) {
		if (neuAnzahl < 0) {
			System.out.println("SET: setAnzahl() failed -> Input is smaller than 0");
			return false;
		} else {
			this.anzahl = neuAnzahl;
			System.out.println("SET: anzahl was set to: " + neuAnzahl);
			return true;
		}
	}
	
	/**
	* Setze das {@link #gewicht} auf ein neues.
	* @param neuGewicht Das neue {@link #gewicht} des Artikels.
	* @return boolean Wenn das neue {@link #gewicht} erfolgreich zugewiesen wurde, wird true zurückgegeben. In allen anderen Fällen false.
	*/
	public boolean setGewicht(double neuGewicht) {
		if (neuGewicht < 0) {
			System.out.println("SET: setGewicht() failed -> Input is smaller than 0");
			return false;
		} else {
			this.gewicht = neuGewicht;
			System.out.println("SET: gewicht was set to: " + neuGewicht);
			return true;
		}
	}
	
	/**
	* Setze den {@link #preis} auf einen neuen.
	* @param neuPreis Der neue {@link #preis} des Artikels.
	* @return boolean Wenn der neue {@link #preis} erfolgreich zugewiesen wurde, wird true zurückgegeben. In allen anderen Fällen false.
	*/
	public boolean setPreis(double neuPreis) {
		if (neuPreis < 0) {
			System.out.println("SET: setPreis() failed -> Input is smaller than 0");
			return false;
		} else {
			this.preis = neuPreis;
			System.out.println("SET: preis was set to: " + neuPreis);
			return true;
		}
	}
	
	/**
	* Setze die {@link #platzNummer} auf eine neue.
	* @param neuPlatz Die neue {@link #platzNummer} des Artikels.
	* @return boolean Wenn die neue {@link #platzNummer} erfolgreich zugewiesen wurde, wird true zurückgegeben. In allen anderen Fällen false.
	*/
	public boolean setPlatznummer(int neuPlatz) {
		if (neuPlatz < 0) {
			System.out.println("SET: setPlatznummer() failed -> Input is smaller than 0");
			return false;
		} else {
			this.platzNummer = neuPlatz;
			System.out.println("SET: platzNummer was set to: " + neuPlatz);
			return true;
		}
	}
	
	/**
	* Ändere die {@link #anzahl} des Artikels um ein gewisses delta.
	* @param delta Der Wert um den die {@link #anzahl} des Artikels geändert werden soll.
	* @return boolean Wenn die {@link #anzahl} erfolgreich um das delta verändert wurde, wird true zurückgegeben. In allen anderen Fällen false.
	*/
	public boolean changeAnzahl(int delta) {
		if ((this.anzahl + delta) < 0) {
			System.out.println("CHANGE: anzahl could't be changed -> Not enough Atrikel left");
			return false;
		} else {
			this.anzahl = this.anzahl + delta;
			System.out.println("CHANGE: anzahl was changed by " + delta);
			return true;
		}
	}
	
	public boolean equals(Artikel b) {
		if (this.produktBezeichnung.toLowerCase() == b.produktBezeichnung.toLowerCase() && this.kategorie.toLowerCase() == b.kategorie.toLowerCase() && this.anzahl == b.anzahl && this.gewicht == b.gewicht && this.preis == b.preis && this.platzNummer == b.platzNummer) {
			return true;
		} else {
			return false;
		}
	}
}