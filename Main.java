import java.util.List;
import java.io.File;
import java.io.IOException;
import java.time.Year;

/**
 * 
 * @author 	Maximilian Falk
 * @version 1.0
 * @since 	08-01-2020
 * 
 *
 */
public class Main {

	public static void main(String[] args) {
		Datenverwaltung data = Datenverwaltung.getSingleton();
		List<Artikel> testList = Datenverwaltung.getAList();
		
		for (int i = 0; i < testList.size(); i++) {
			Artikel tempArtikel = testList.get(i);
			System.out.println(tempArtikel.platzNummer);
		}
		System.out.println("\n");
		List<Artikel> searchResult = Datenverwaltung.search("Blei", Eigenschaft.Produktbezeichnung);
		for (int i = 0; i < searchResult.size(); i++) {
			Artikel temArtikel = searchResult.get(i);
			System.out.println(temArtikel.platzNummer);
		}
	}

}
