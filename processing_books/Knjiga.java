package zavrsniprojekat;

import java.io.*;
import java.util.*;

public class Knjiga {

	public HashMap<String, Integer> ucitajKnjigu() {
		HashMap<String, Integer> hmKnjiga = new HashMap<String, Integer>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\knjiga"));
			String linija = br.readLine();
			while (linija != null) {
				String[] reci = linija.replaceAll("[^a-zA-Z ]", "").split("\\s+");
				for (int i = 0; i < reci.length; i++) {
					reci[i] = reci[i].toLowerCase();
					if (hmKnjiga.containsKey(reci[i])) {
						hmKnjiga.put(reci[i], hmKnjiga.get(reci[i]) + 1);
					} else {
						hmKnjiga.put(reci[i], 1);
					}
				}
				linija = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return hmKnjiga;
	}

	public HashMap<String, Integer> brPonavljanja(HashMap<String, Integer> hmKnjiga, HashMap<String, String> hmRecnik) {
		hmKnjiga.keySet();
		HashMap<String, Integer> brPonavljanja = new HashMap<String, Integer>();
		for (String kljuc : hmKnjiga.keySet()) {
			if (hmRecnik.containsKey(kljuc)) {
				brPonavljanja.put(kljuc, hmKnjiga.get(kljuc));
			}
		}
		return brPonavljanja;
	}

	public HashMap<String, Integer> noveReci(HashMap<String, Integer> hmKnjiga, HashMap<String, String> hmRecnik) {
		hmKnjiga.keySet();
		HashMap<String, Integer> noveReci = new HashMap<String, Integer>();
		for (String kljuc : hmKnjiga.keySet()) {
			if (kljuc != null) {
				if (!hmRecnik.containsKey(kljuc)) {
					noveReci.put(kljuc, hmKnjiga.get(kljuc));
				}
			}
		}
		return noveReci;
	}

	public LinkedHashMap<String, Integer> sortirajPoVrednosti(HashMap<String, Integer> passedMap) {
		List<String> mapKeys = new ArrayList<>(passedMap.keySet());
		List<Integer> mapValues = new ArrayList<>(passedMap.values());
		Collections.sort(mapValues);
		Collections.sort(mapKeys);
		Collections.reverse(mapValues);
		Collections.reverse(mapKeys);
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
		Iterator<Integer> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			Integer val = valueIt.next();
			Iterator<String> keyIt = mapKeys.iterator();
			while (keyIt.hasNext()) {
				String key = keyIt.next();
				Integer comp1 = passedMap.get(key);
				Integer comp2 = val;
				if (comp1.equals(comp2)) {
					keyIt.remove();
					sortedMap.put(key, val);
					break;
				}
			}
		}
		return sortedMap;
	}
}
