package main;

public class Node implements Comparable<Node>{
	private int x;
	private int y;
	private double weight;
	private double pathPassed; //
	private Node parrent;

	public Node(int x, int y, double weight) {
		super();
		this.x = x;
		this.y = y;
		this.weight = weight;
		this.pathPassed = 0;
		this.setParrent(null);
	}
	
	public Node(int x, int y, double weight, Node parrent, double currWeight) {
		super();
		this.x = x;
		this.y = y;
		this.weight = weight;
		this.pathPassed = parrent.pathPassed + currWeight;
		this.parrent = parrent;
	}
	
	public double getPathPassed() {
		return pathPassed;
	}

	public void setPathPassed(double pathPassed) {
		this.pathPassed = pathPassed;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Node other) {
		if (this.weight + this.pathPassed < other.weight + other.pathPassed){
			return 1;
		} else if (this.weight + this.pathPassed > other.weight + other.pathPassed){
			return -1;
		} else {
			return 0;
		}
	}

	public Node getParrent() {
		return parrent;
	}

	public void setParrent(Node parrent) {
		this.parrent = parrent;
	}
	
	@Override
	public String toString() {
		return "Node [x=" + x + ", y=" + y + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Node other = (Node) obj;
		return (this.x == other.x) && (this.y == other.y);
	}
	
}
