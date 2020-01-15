/**
* Die Klasse Kategorie besitzt die Attribute "name" und "artikelAnzahl"
* welche Speichern, wieviele Artikel unter einer bestimmten Kategorie
* gespeichert sind.
*
* Es gibt eine getter und setter Methode für name und eine getter Methode für
* artikelAnzahl. Artikelanzahl kann über die in- und decrease Methoden verändert werden.
*
* @author 	Maximilian Falk
* @version 	1.0
* @since 	06-01-2020
*/


public class Kategorie {
	
	// Attribute von Kategorie
	/**
	* Der Name dieser Kategorie.
	*/
	public String name;
	
	/**
	* Die Anzahl der Artikel, die unter dieser Kategorie gespeichert sind.
	*/
	public int artikelAnzahl;
	
	// Konstruktor
	/**
	* Initialisiere eine neue Kategorie.
	* @param name Der Name der zu initialisierenden Kategorie.
	* @param anzahl Die Anzahl der Artikel die unter dieser Kategorie gespeichert sind.
	*/
	public Kategorie(String name, int anzahl) {
		this.name = name;
		this.artikelAnzahl = anzahl;
	}
	
	// Getter Methoden
	/**
	* Hole den Namen der Kategorie.
	* @return String Der {@link #name} der Kategorie wird zurückgegeben.
	*/
	public String getName() {
		System.out.println("GET: getName() returned " + this.name);
		return this.name;
	}
	
	/**
	* Hole die Anzahl der unter dieser Kategorie gespeicherten Artikel.
	* @return int Die {@link #artikelAnzahl} der unter dieser Kategorie gespeicherten Artikel wird zurückgegeben.
	*/
	public int getAnzahl() {
		System.out.println("GET: getAnzahl() returned " + this.artikelAnzahl);
		return this.artikelAnzahl;
	}
	
	
	// Setter Methoden
	/**
	* Setze den {@link #name} einer Kategorie auf einen neuen.
	* @param neuName Der neue {@link #name} der Kategorie
	* @return boolean Wenn der neue Name erfolgreich zugewiesen wurde wird true zurückgegeben. In allen anderen Fällen false.
	*/
	public boolean setName(String neuName) {
		if (neuName.length() > 20) {
			System.out.println("SET: setName() failed -> Input too long");
			return false;
		} else {
			this.name = neuName;
			System.out.println("SET: name was set to: " + neuName);
			return true;
		}
	}
	
	
	// In- and Decrease
	/**
	* Erhöhe die {@link #artikelAnzahl} der unter dieser Kategorie gespeicherten Artikel um 1.
	*/
	public void increase() {
		this.artikelAnzahl += 1;
	}
	
	/**
	* Senke die {@link #artikelAnzahl} der unter dieser Kategorie gespeicherten Artikel um 1 solange der neue Wert nicht negativ wird.
	* @return boolean Wenn {@link #artikelAnzahl} erfolgreich gesenkt wurde wird true zurückgegeben. In allen anderen Fällen false.
	*/
	public boolean decrease() {
		if ((artikelAnzahl - 1) < 0) {
			System.out.println("DEC: decrease() failed -> New value is smaller than 0");
			return false;
		} else {
			this.artikelAnzahl -= 1;
			System.out.println("DEC: anzahl was decreased by 1");
			return true;
		}
	}
	
	public boolean equals(Kategorie b) {
		if (this.name.toLowerCase() == b.name.toLowerCase() && this.artikelAnzahl == b.artikelAnzahl) {
			return true;
		} else {
			return false;
		}
	}
}