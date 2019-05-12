package source_01;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Start extends JPanel {
	
	final int CELLS = 15;
	final int CELL_SIZE = 20;
	final int GAP = 1;
	final int SIZE = (CELLS * CELL_SIZE) + (CELLS * GAP) + GAP;
	final int MINES = (int) ((int) (CELLS * CELLS) * 0.1);
	
	Board board;
	Click click;
	
	public int completion = 0;
	public boolean loss = false;
	
	public ArrayList<Cell> cells = new ArrayList<>();
	
	public Start() {
		this.setFocusable(true);
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					click.clicked(e.getX(), e.getY(), false);
				else if(e.getButton() == MouseEvent.BUTTON3)
					click.clicked(e.getX(), e.getY(), true);
			}
		});
	}
	
	public static void main(String[] args) {
		Start start = new Start();
		start.setPreferredSize(new Dimension(start.SIZE,start.SIZE));
		
		JFrame frame = new JFrame("Mine Sweeper");
		frame.add(start);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		start.init();
	}
	
	ActionListener loop = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {			
			repaint();
			loss();
			complete();
		}
	};
	
	Timer timer = new Timer(15, loop);
	
	private void init() {
		makeCells();
		makeMines();
		
		board = new Board(this);
		click = new Click(this,board);
		
		timer.start();
	}
	
	@Override
	public void paint(Graphics g1) {
		super.paint(g1);
		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
		board.paint(g);
	}
	
	private void makeCells() {
		for(int x=0; x < CELLS; x++)
			for(int y=0; y < CELLS; y++)
				cells.add(new Cell((x * CELL_SIZE) + (x * GAP) + GAP, (y * CELL_SIZE) + (y * GAP) + GAP));
	}
	
	private void makeMines() {
		Random rand = new Random();
		
		for(int x=0; x < MINES; x++) {
			int random = rand.nextInt(cells.size());
			if(cells.get(random).getMine() == false)
				cells.get(random).setMine(true);
			else
				x--;
		}
	}
	
	public void complete() {
		if(completion == (CELLS * CELLS) - MINES) {
			timer.stop();
			JOptionPane.showMessageDialog(this, "The board has been sweeped!", "Success", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public void loss() {
		if(loss) {
			timer.stop();
			JOptionPane.showMessageDialog(this, "You hit a mine!", "Loss", JOptionPane.PLAIN_MESSAGE);
			System.exit(-1);			
		}
	}

}