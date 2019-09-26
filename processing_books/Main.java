package zavrsniprojekat;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Recnik db = new Recnik("jdbc:sqlite:C:\\Users\\User\\Desktop\\Dictionary.db");
		Knjiga knjiga = new Knjiga();

		db.connect();

		HashMap<String, String> hmRecnik = db.ispisRecnika();
		HashMap<String, Integer> hmKnjiga = knjiga.ucitajKnjigu();

		db.kreirajTabeluNoveReci();

		HashMap<String, Integer> brPonavljanja = knjiga.brPonavljanja(hmKnjiga, hmRecnik);
		brPonavljanja.keySet();
		System.out.println("Broj ponavljanja reci koje se nalaze u recniku:");
		for (String kljuc : brPonavljanja.keySet()) {
			System.out.println(kljuc + " - " + brPonavljanja.get(kljuc));
		}

		LinkedHashMap<String, Integer> sortirana = knjiga.sortirajPoVrednosti(brPonavljanja);
		sortirana.keySet();
		int i = 0;
		System.out.println("20 najcesce koriscenih reci:");
		for (String kljuc : sortirana.keySet()) {
			i++;
			if (i == 21)
				break;
			System.out.println(i + ". " + kljuc + " - " + sortirana.get(kljuc));
		}

		HashMap<String, Integer> noveReci = knjiga.noveReci(hmKnjiga, hmRecnik);
		noveReci.keySet();
		String upit = "INSERT INTO noveReci (Ime) VALUES ";
		for (String kljuc : noveReci.keySet()) {
			upit += "(\"" + kljuc + "\"),";
		}

		db.dodajNoveReci(upit.substring(0, upit.length() - 1));

		TreeMap<String, String> tmRecnik = NoviFajl.tmRecnik(hmRecnik);
		TreeMap<String, String> noviFajl = NoviFajl.tmNoveReci(noveReci, tmRecnik);
		NoviFajl.upisNovogFajla(noviFajl);

		db.disconnect();
	}

}
