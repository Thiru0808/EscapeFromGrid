package test.java.com;

import main.java.com.EscapeGridSP;

public class EscapeGridSPTest {

	public static void main(String[] args) {
		int[][] grid2 = {
				{1,1,1,0,1},
				{1,0,2,0,1},
				{0,0,1,0,1},
				{1,0,1,1,0},
				};
		
		EscapeGridSP test = new EscapeGridSP(grid2, 2);
		
		System.out.println(test.shortestPath().size()-1);

	}

}
