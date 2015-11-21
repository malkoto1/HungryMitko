package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {

	static String path = "C:\\Users\\Vojda\\Downloads\\map1.csv";
	static int startX = 0;
	static int startY = 5;
	static int endX = 7;
	static int endY = 4;
	static ArrayList<ArrayList<Character>> mazeList = readFromFile(path);
	static int length = mazeList.size();
	static int width = mazeList.get(0).size();
	static ArrayList<Node> visited = new ArrayList<Node>();
	
	static char[][] maze = transformToArray(mazeList);
	
	static PriorityQueue<Node> queue = new PriorityQueue<Node>();

	public static void main(String[] args) {
//		Maze maze = new Maze(args[0]);
//		Point start = new Point(args[1], args[2]));
//		Point end = new Point(args[3], args[4]));
//		
//		List<Point> path = maze.findPath(start, end);
//		
//		for (Point step : path) {
//    			System.out.print(step.toString());
//
//    			if (!step.equals(end)) {
//    				System.out.print(" ,");
//    			}	
//		}

//		String path = args[0];
		
		double startWeight = calculateweight(startX, startY, 0);
		
		Node start = new Node(startX, startY, 0);
		
		queue.add(start);
		
		while (true){
			//Return element to trace the path if the end is reached
			Node current = queue.poll();
			Node node = null;
			if (current != null){
				node = generateChildren(current);
			}
			
			if (node != null){
				System.out.println(node);
				while(node.getParrent() != null){
					node = node.getParrent();
					System.out.println(node);
				}
				break;
			}
		}

	}

	//BEWARE THE COPY-PASTE DRIVEN DEVELOPMENT!!!
	private static Node generateChildren(Node node) {
		visited.add(node);
		for (int i = -1; i < 2; i++){
			for (int j = -1; j < 2; j++){
				if ( i!= 0 || j != 0){
					int newX = node.getX() + i;
					int newY = node.getY() + j;
					double newWeight = 1;
					char field;
					try {
						field = maze[newX][newY];
					} catch (IndexOutOfBoundsException e){break;}
					if (i != 0 && j != 0){
						newWeight += 0.5;
					}
					if (field == '~'){
						newWeight += 2;
					}
					
					Node newNode = new Node(newX, newY, calculateweight(newX, newY, newWeight + node.getPathPassed()), node, newWeight + node.getPathPassed());
					if (!visited.contains(newNode) && (field != 'N')){
						queue.add(newNode);
					}
					if (newNode.getX() == endX && newNode.getY() == endY){
						return newNode;
					}
				}
			}
		}
		
		return null;
	}

	private static char[][] transformToArray(
			ArrayList<ArrayList<Character>> mazeList) {
		char[][] map = new char[mazeList.get(0).size()][mazeList.size()];
		for (int i = 0; i < mazeList.size(); i++) {
			ArrayList<Character> line = mazeList.get(i);
			for (int j = 0; j < line.size(); j++) {
				map[j][i] = line.get(j).charValue();
			}
		}
		return map;
	}

	private static double calculateweight(int startX, int startY, double weight) {
		double result = modul(endX - startX) + modul(endY - startY) + weight;
		
		return result;
	}

	private static int modul(int i) {
		if (i < 0){
			return -i;
		}
		return i;
	}

	private static ArrayList<ArrayList<Character>> readFromFile(String path) {
		String csvFile = path;
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
