package main.java.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EscapeGridSP {
	private int[][] nodes;
	private int startVal;
	
	public EscapeGridSP(int[][] nodes, int startVal) {
		this.nodes = nodes;
		this.startVal = startVal;
	}

	public List<Node> shortestPath() {

		Map<Node, Node> parents = new HashMap<Node, Node>();
		Node start = null;
		Node end = null;

		for (int row = 0; row < nodes.length; row++) {
			for (int column = 0; column < nodes[row].length; column++) {
				if (nodes[row][column] == startVal) {
					start = new Node(row, column, nodes[row][column]);
					break;
				}
			}
			if (start != null) {
				break;
			}
		}

		if (start == null) {
			throw new RuntimeException("Start node is not found!!!");
		}

		List<Node> temp = new ArrayList<Node>();
		temp.add(start);
		parents.put(start, null);

		boolean reachedDestination = false;

		while (temp.size() > 0 && !reachedDestination) {
			Node currentNode = temp.remove(0);
			List<Node> children = getChildern(currentNode);
			for (Node child : children) {
				if (!parents.containsKey(child)) {
					parents.put(child, currentNode);
					int val = child.getValue();
					if (val == 0) {
						if(child.getX() == 0 || child.getY() == 0)  {
							temp.add(child);
							reachedDestination = true;
							end = child;
							break;
						}else {
							temp.add(child);
						}
					}
				}
			}
		}

		if (end == null) {
			throw new RuntimeException("End node is not found!!!");
		}

		Node node = end;
		List<Node> path = new ArrayList<Node>();
		while (node != null) {
			path.add(0, node);
			node = parents.get(node);
		}
		printPath(path);
		return path;
	}

	private List<Node> getChildern(Node parent) {
		List<Node> children = new ArrayList<Node>();

		int x = parent.getX();
		int y = parent.getY();

		if (x - 1 >= 0) {
			children.add(new Node((x - 1), y, nodes[x - 1][y]));
		}

		if (y - 1 >= 0) {
			children.add(new Node(x, (y - 1), nodes[x][y - 1]));
		}

		if (x + 1 < nodes.length) {
			children.add(new Node((x + 1), y, nodes[x + 1][y]));
		}

		if (y + 1 < nodes[0].length) {
			children.add(new Node(x, (y + 1), nodes[x][y + 1]));
		}

		return children;
	}

	private void printPath(List<Node> path) {
		String ANSI_RESET = "";
		String ANSI_RED = "";

		for (int row = 0; row < nodes.length; row++) {
			for (int column = 0; column < nodes[row].length; column++) {
				String value = nodes[row][column] + "";

				// mark path with red X
				for (int i = 1; i < path.size() - 1; i++) {
					Node node = path.get(i);
					if (node.getX() == row && node.getY() == column) {
						value = ANSI_RED + "X" + ANSI_RESET;
						break;
					}
				}
				if (column == nodes[row].length - 1) {
					System.out.println(value);
				} else {
					System.out.print(value + ".....");
				}
			}

			if (row < nodes.length - 1) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < nodes[row].length - 1; j++) {
						System.out.print(".     ");
					}
					System.out.println(".     ");
				}
			}
		}
		System.out.println();
		System.out.println("Path: " + path);
	}
}

class Node {
	private int x;
	private int y;
	private int value;

	public Node(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return x * y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (this.getClass() != o.getClass())
			return false;
		Node node = (Node) o;
		return x == node.x && y == node.y;
	}

	@Override
	public String toString() {
		return "(x: " + x + " y: " + y + ")";
	}

}
