package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		ArrayList<ArrayList<Character>> maze = readFromFile();
		
		for (Iterator iterator = maze.iterator(); iterator.hasNext();) {
			ArrayList<Character> arrayList = (ArrayList<Character>) iterator
					.next();
			for (Iterator iterator2 = arrayList.iterator(); iterator2.hasNext();) {
				Character character = (Character) iterator2.next();
				System.out.println(character);
			}
		}
	}

	private static ArrayList<ArrayList<Character>> readFromFile() {
		String csvFile = "C:\\Users\\Vojda\\Downloads\\map1.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		ArrayList<ArrayList<Character>>input = new ArrayList<>();
		
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

			        // use comma as separator
				String[] lineStringArray = line.split(cvsSplitBy);
				ArrayList<Character> array = new ArrayList<>();
				for (int i = 0; i < lineStringArray.length; i++) {
					array.add(new Character(lineStringArray[i].toCharArray()[0]));
				}
				input.add(array);
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
		
		return input;
	}

}
