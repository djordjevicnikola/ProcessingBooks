package zavrsniprojekat;

import java.io.*;
import java.util.*;

public class NoviFajl {
	public static TreeMap<String, String> tmRecnik(HashMap<String, String> hmRecnik) {
		TreeMap<String, String> upisRecnika = new TreeMap<String, String>();
		hmRecnik.keySet();
		for (String kljuc : hmRecnik.keySet()) {
			upisRecnika.put(kljuc, hmRecnik.get(kljuc));
		}
		return upisRecnika;
	}

	public static TreeMap<String, String> tmNoveReci(HashMap<String, Integer> hmNoveReci,
			TreeMap<String, String> tmRecnik) {
		TreeMap<String, String> upisNovihReci = new TreeMap<String, String>();
		hmNoveReci.keySet();
		for (String kljuc : hmNoveReci.keySet()) {
			upisNovihReci.put(kljuc, kljuc);
		}
		tmRecnik.keySet();
		for (String kljuc : tmRecnik.keySet()) {
			upisNovihReci.put(kljuc, kljuc);
		}
		return upisNovihReci;
	}

	public static void upisNovogFajla(TreeMap<String, String> tmNoveReci) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("Prosireni recnik", true);
			tmNoveReci.keySet();
			for (String kljuc : tmNoveReci.keySet()) {
				fw.write(kljuc + "\n");
				fw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
