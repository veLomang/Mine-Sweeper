package source_01;

public class Cell {
	
	private int x;
	private int y;
	private int neighbors;
	private boolean flagged;
	private boolean clicked;
	private boolean mine;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		
		neighbors = 0;
		flagged = false;
		clicked = false;
		mine = false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getNeighbors() {
		return neighbors;
	}
	
	public boolean getFlagged() {
		return flagged;
	}
	
	public boolean getClicked() {
		return clicked;
	}
	
	public boolean getMine() {
		return mine;
	}
	
	public void setNeighbors(int neighbors) {
		this.neighbors = neighbors;
	}
	
	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}
	
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	
	public void setMine(boolean mine) {
		this.mine = mine;
	}
	
}
