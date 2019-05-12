package source_01;

import java.util.ArrayList;

public class Click {

	Start s;
	Board b;
	
	ArrayList<Cell> cells;
	
	public Click(Start s, Board b) {
		this.s = s;
		this.b = b;
		
		cells = s.cells;
	}
	
	public void clicked(int xCor, int yCor, boolean flagged) {
		Cell cell = null;
		
		// Finds cell object that was clicked by using cordinates
		for(int z=0; z < cells.size(); z++)
			if(xCor >= cells.get(z).getX() && xCor <= cells.get(z).getX() + s.CELL_SIZE && yCor >= cells.get(z).getY() && yCor <= cells.get(z).getY() + s.CELL_SIZE)
					cell = cells.get(z);
	
		if(cell == null)
			return;
		
		if(!flagged && !cell.getMine() && !cell.getClicked() && !cell.getFlagged())
			s.completion++;
		
		if(flagged) {
			if(cell.getClicked())
				return;
			else if(cell.getFlagged())
				cell.setFlagged(false);
			else
				cell.setFlagged(true);
		}
		else if(getNeighbors(cell) == 0 && !cell.getMine() && !cell.getClicked()) {
			if(cell.getFlagged())
				return;
			
			cell.setClicked(true);
			
			clicked(xCor, yCor - s.CELL_SIZE - s.GAP, false); // Top
			clicked(xCor, yCor + s.CELL_SIZE + s.GAP, false); // Bottom
			clicked(xCor - s.CELL_SIZE - s.GAP, yCor, false); // Left
			clicked(xCor + s.CELL_SIZE + s.GAP, yCor, false); // Right
			clicked(xCor + s.CELL_SIZE + s.GAP, yCor + s.CELL_SIZE + s.GAP, false); // Top - Right
			clicked(xCor - s.CELL_SIZE - s.GAP, yCor + s.CELL_SIZE + s.GAP, false); // Top - Left
			clicked(xCor + s.CELL_SIZE + s.GAP, yCor - s.CELL_SIZE - s.GAP, false); // Bottom - Right
			clicked(xCor - s.CELL_SIZE - s.GAP, yCor - s.CELL_SIZE - s.GAP, false); // Bottom - Left
		} 
		else {
			if(cell.getFlagged())
				return;
			
			cell.setClicked(true);
			cell.setNeighbors(getNeighbors(cell));			
		}
	}
	
	public int getNeighbors(Cell cell) {
		int lastCell = cells.get(cells.size() - 1).getX();
		
		//int neighPos[] = {-1, 1, s.CELLS, s.CELLS - 1, s.CELLS + 1, s.CELLS + 1, s.CELLS - 1};
		
		int neighs = 0;
		int cellId = 0;
		
		for(int x=0; x < cells.size(); x++)
			if(cells.get(x) == cell)
				cellId = x;
		
		// Put this into a 'for' loop that uses an array ************
		
		// Top
		if(cell.getY() > 3)
			if(cells.get(cellId-1).getMine() == true)
				neighs++;
		
		// Bottom
		if(cell.getY() < lastCell)
			if(cells.get(cellId+1).getMine() == true)
				neighs++;
		
		// Right
		if(cell.getX() < lastCell)
			if(cells.get(cellId + s.CELLS).getMine() == true)
				neighs++;
		
		// Left
		if(cell.getX() > 3)
			if(cells.get(cellId - s.CELLS).getMine() == true)
				neighs++;
		
		// Top-Right
		if(cell.getX() < lastCell && cell.getY() > 3)
			if(cells.get(cellId + (s.CELLS - 1)).getMine() == true)
				neighs++;
		
		// Top-Left
		if(cell.getX() > 3 && cell.getY() > 3)
			if(cells.get(cellId - (s.CELLS + 1)).getMine() == true)
				neighs++;
		
		// Bottom-Right
		if(cell.getX() < lastCell && cell.getY() < lastCell)
			if(cells.get(cellId + (s.CELLS + 1)).getMine() == true)
				neighs++;
		
		// Bottom-Left
		if(cell.getX() > 3 && cell.getY() < lastCell)
			if(cells.get(cellId - (s.CELLS - 1)).getMine() == true)
				neighs++;

		return neighs;
	}
	
}