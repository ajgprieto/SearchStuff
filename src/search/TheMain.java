package search;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TheMain {

	private static Random rand = new Random();

	public static void main(String[] args) {

		Search bs = new Search();

		List<String> loadedTerms = loadTerms("words.txt");
		List<String> searchTerms = getTerms(loadedTerms);

		for (String term : searchTerms) {
			System.out.println(term);
		}

		try {
			bs.setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			bs.testSearch(searchTerms);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			bs.tearDown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static List<String> loadTerms(String words) {
		List<String> loadedTerms = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(words));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String line = "";

		try {
			while ((line = br.readLine()) != null) {
				loadedTerms.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return loadedTerms;
	}

	private static List<String> getTerms(List<String> loadedTerms) {
		List<String> terms = new ArrayList<>();

		for (int x = 0; x < 21; x++) {
			int index = (int) (rand.nextDouble() * loadedTerms.size());
			String term = loadedTerms.get(index);
			terms.add(term);
		}
		return terms;

	}
}
