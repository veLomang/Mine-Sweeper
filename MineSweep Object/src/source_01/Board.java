package source_01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class Board {

	Start s;
	
	ArrayList<Cell> cells;
	
	public Board(Start s) {
		this.s = s;
		
		cells = s.cells;
	}
	
	public void paint(Graphics g) {
		g.setColor(new Color(109,109,109));
		g.fillRect(0, 0, s.SIZE, s.SIZE);
		
		// Loop for cells
		for(int x=0; x < cells.size(); x++) {
			if(cells.get(x).getClicked()) // Clicked Cells
				g.setColor(new Color(148,148,148));
			else
				g.setColor(new Color(171,171,171)); // Untouched cells
			
			g.fillRect(cells.get(x).getX(), cells.get(x).getY(), s.CELL_SIZE, s.CELL_SIZE); // Cells

			if(cells.get(x).getMine() && cells.get(x).getClicked()) {
				g.setColor(new Color(148,148,148));
				g.fillRect(cells.get(x).getX(), cells.get(x).getY(), s.CELL_SIZE, s.CELL_SIZE);
				
				g.setColor(Color.BLACK); // Clicked bombs
				g.fillOval(cells.get(x).getX() + (s.CELL_SIZE / 4), cells.get(x).getY() + (s.CELL_SIZE / 4), s.CELL_SIZE / 2, s.CELL_SIZE / 2);
				g.fillRect(cells.get(x).getX() + (s.CELL_SIZE / 4) - 2, cells.get(x).getY() + (s.CELL_SIZE / 4) + 4, s.CELL_SIZE - 6, s.CELL_SIZE / 2 - 8);
				g.fillRect(cells.get(x).getX() + (s.CELL_SIZE / 4) + 4, cells.get(x).getY() + (s.CELL_SIZE / 4) - 2, s.CELL_SIZE / 2 - 8, s.CELL_SIZE - 6);
				s.loss = true;
			}
		
			if(cells.get(x).getFlagged()) { // Flags
				g.setColor(Color.RED);
				g.fillRect(cells.get(x).getX() + (s.CELL_SIZE / 4), cells.get(x).getY() + (s.CELL_SIZE / 4), s.CELL_SIZE / 2 - 5, s.CELL_SIZE / 2 - 1);
				g.fillRect(cells.get(x).getX() + (s.CELL_SIZE / 4) + 3, cells.get(x).getY() + (s.CELL_SIZE / 4) + 1, (s.CELL_SIZE / 2) - 4, (s.CELL_SIZE / 2) - 3);
				g.fillRect(cells.get(x).getX() + (s.CELL_SIZE / 4) + 8, cells.get(x).getY() + (s.CELL_SIZE / 4) + 2, (s.CELL_SIZE / 2) - 6, (s.CELL_SIZE / 2) - 5);

				g.setColor(Color.BLACK);
				g.fillRect(cells.get(x).getX() + (s.CELL_SIZE / 4) - 1, cells.get(x).getY() + (s.CELL_SIZE / 4), s.CELL_SIZE / 2 - 8, (s.CELL_SIZE / 2) + 3);
			}
			
			if(cells.get(x).getClicked() && !cells.get(x).getMine()) {
				g.setFont(new Font("Verdana", Font.BOLD, 18));
				String neighbors = String.valueOf(cells.get(x).getNeighbors());
				
				if(neighbors.equals("1")) g.setColor(Color.BLUE);
				else if(neighbors.equals("2")) g.setColor(new Color(5,116,44));
				else if(neighbors.equals("3")) g.setColor(Color.RED);
				else if(neighbors.equals("4")) g.setColor(new Color(8,5,116));
				else if(neighbors.equals("5")) g.setColor(new Color(116,32,5));
				else if(neighbors.equals("6")) g.setColor(new Color(5,94,116));
				else if(neighbors.equals("7")) g.setColor(Color.BLACK);
				else if(neighbors.equals("8")) g.setColor(new Color(65,65,65));
				
				if(!neighbors.equals("0"))
					g.drawString(neighbors, cells.get(x).getX() + 4, cells.get(x).getY() + 17);
			}
		}	
	}
	
}
