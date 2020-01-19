package Src;

/**
* Die Klasse Artikel besitzt die Attribute "produktbezeichnung", "kategorie",
* "anzahl", "gewicht", "preis" und "platzNummer".
*
* Es gibt zu jedem Attribut jeweils eine getter und setter Methode.
*
* Außerdem gibt es eine changeAnzahl Methode, mit welcher die Anzahl um ein delta veraendert
* werden kann.
*
* @author 	Maximilian Falk
* @version 	1.0
* @since 	06-01-2020
*/

public class Artikel extends Object {
	
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
	* Initialisiere einen neuen Artikel und pruefe vorher, ob die Parameter gueltig sind.
	*
	* @param name Der Name des zu initialisierenden Artikels.
	* @param kat Die Kategorie welcher der Artikel zugehoeren soll.
	* @param anz Die Anzahl wie of es den Artikel gibt.
	* @param gew Die Anzahl eines Artikels.
	* @param preis Der Preis eines Artikels.
	* @param platz Die Platznummer des zu erstellenden Artikels.
	*/
	public Artikel(String name, String kat, int anz, double gew, double preis, int platz) {
		
		if (name.length() < 257 && kat.length() < 257 && anz >= 0 && gew >= 0.1 && preis >= 0 && platz >= 0) {
			this.produktBezeichnung = name;
			this.kategorie = kat;
			this.anzahl = anz;
			this.gewicht = gew;
			this.platzNummer = platz;
		} else {
			System.out.println("ARTIKEL -> ARTIKEL: Ein oder mehrere Parameter sind falsch");
			return;
		}
		
		System.out.println("Artikel " + name + " was created!");
	}
	
	
	// Getter - Methoden
	/**
	* Gebe den Namen des Artikels zurueck.
	* @return String Die {@link #produktBezeichnung} des Artikels wird zurueckgegeben.
	*/
	public String getName() {
		System.out.println("GET: getName() returned " + this.produktBezeichnung);
		return this.produktBezeichnung;
	}
	
	/**
	* Hole die Kategorie des Artikels.
	* @return String Die {@link #kategorie} des Artikels wird zurueckgegeben.
	*/
	public String getKategorie() {
		System.out.println("GET: getKategorie() returned " + this.kategorie);
		return this.kategorie;
	}
	
	/**
	* Hole die Anzahl, wie oft es diesen Artikel gibt.
	* @return int Die {@link #anzahl} des Artikels wird zurueckgegeben.
	*/
	public int getAnzahl() {
		System.out.println("GET: getAnzahl() returned " + this.anzahl);
		return this.anzahl;
	}
	
	/**
	* Hole das Gewicht des Artikels.
	* @return double Das {@link #gewicht} eines Artikels wird zurueckgegeben.
	*/
	public double getGewicht() {
		System.out.println("GET: getGewicht() returned " + this.gewicht);
		return this.gewicht;
	}
	
	/**
	* Hole den Preis des Artikels.
	* @return double Der {@link #preis} eines Artikels wird zurueckgegeben.
	*/
	public double getPreis() {
		System.out.println("GET: getPreis() returned " + this.preis);
		return this.preis;
	}
	
	/**
	* Hole die Platznummer des Artikels.
	* @return int Die {@link #platzNummer} eines Aretikels wird zurueckgegeben.
	*/
	public int getPlatznummer() {
		System.out.println("GET: getPlatznummer() returned " + this.platzNummer);
		return this.platzNummer;
	}
	
	
	// Setter - Methoden
	/**
	* Setze die {@link #produktBezeichnung} auf eine neue.
	* @param neuName Die neue {@link #produktBezeichnung} des Artikels.
	* @return boolean Wenn die neue {@link #produktBezeichnung} erfolgreich zugewiesen wurde, wird true zurueckgegeben. In allen anderen Faellen false.
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
	* @return boolean Wenn die neue {@link #kategorie} erfolgreich zugewiesen wurde, wird true zurueckgegeben. In allen anderen Faellen false.
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
	* @return boolean Wenn die neue {@link #anzahl} erfolgreich zugewiesen wurde, wird true zurueckgegeben. In allen anderen Faellen false.
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
	* @return boolean Wenn das neue {@link #gewicht} erfolgreich zugewiesen wurde, wird true zurueckgegeben. In allen anderen Faellen false.
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
	* @return boolean Wenn der neue {@link #preis} erfolgreich zugewiesen wurde, wird true zurueckgegeben. In allen anderen Faellen false.
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
	* @return boolean Wenn die neue {@link #platzNummer} erfolgreich zugewiesen wurde, wird true zurueckgegeben. In allen anderen Faellen false.
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
	* Aendere die {@link #anzahl} des Artikels um ein gewisses delta.
	* @param delta Der Wert um den die {@link #anzahl} des Artikels geaendert werden soll.
	* @return boolean Wenn die {@link #anzahl} erfolgreich um das delta veraendert wurde, wird true zurueckgegeben. In allen anderen Faellen false.
	*/
	public boolean changeAnzahl(int delta) {
		if ((this.anzahl + delta) < 0) {
			System.out.println("ARTIKEL -> CHANGEANZAHL: Anzahl von Artikel konnte nicht geaendert werden -> Nicht genug Artikel");
			return false;	// Gib false zurueck falls nicht mehr genug Artikel vorhanden sind um sie um das delta zu aendern.
		} else {
			this.anzahl = this.anzahl + delta;
			System.out.println("ARTIKEL -> CHANGEANZAHL: Anzahl wurde geaendert um: " + delta);
			return true;	// Gib true zurueck wenn die Anzahl geaendert wurde.
		}
	}
	
	/**
	 * Es wird der Artikel auf welchem die Funktion aufgerufen wird, mit einem weiteren Artikel, welcher als Parameter
	 * angegeben wird, verglichen. Zwei Artikel sind gleich, wenn sie die selbe ProduktBezeichnung haben.
	 * 
	 * @param b Ein zweiter {@link Artikel} mit welchem der Vergleich durchgefuehrt wird.
	 * @return boolean Es wird true zurueckgegeben, wenn die Artikel gleich sind. False andernfalls.
	 */
	public boolean equals(Artikel b) {
		if (this.produktBezeichnung.toLowerCase() == b.produktBezeichnung.toLowerCase()) {
			return true;	// Gib true zurueck falls die Produktbezeichnung die gleiche ist.
		} else {
			return false;	// Gib false zurueck, falls die Produktbezeichnung nicht die gleiche ist.
		}
	}
}