package com.tarena.worm;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnakeGame extends JPanel {
	private Cell food;
	private Snake worm;
	public static final int ROWS = 35;
	public static final int COLS = 35;
	public static final int CELL_SIZE = 10;
	public static final int INTERVAL = 200;
	private Timer timer;

	private static BufferedImage bg;
	private static BufferedImage cellImg;
	private static BufferedImage foodImg;
	static{
		try {
			bg=ImageIO.read(SnakeGame.class.getResource("bg.png")); 
			cellImg=ImageIO.read(SnakeGame.class.getResource("cell.png")); 
			foodImg=ImageIO.read(SnakeGame.class.getResource("food.png")); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public SnakeGame() {
		worm = new Snake();
		food = randomFood();
	}
	/** ����, ������������ */
	private Cell randomFood() {
		Random r = new Random();
		Cell food;
		do {
			int row = r.nextInt(ROWS);
			int col = r.nextInt(COLS);
			food = new Cell(row, col);
		} while (worm.contains(food));
		return food;
	}


	public void action() {
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				switch (key) {
				case KeyEvent.VK_UP:
					stepAction(Snake.UP);	break;
				case KeyEvent.VK_DOWN:
					stepAction(Snake.DOWN);	break;
				case KeyEvent.VK_RIGHT:
					stepAction(Snake.RIGHT);	break;
				case KeyEvent.VK_LEFT:
					stepAction(Snake.LEFT);	break;
				case KeyEvent.VK_Q:
					System.exit(0);
				}
				repaint();
			}
		});
		this.setFocusable(true);
		this.requestFocus();
		
		timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				stepAction();
				repaint();
			}
		},0,INTERVAL);
	}
	/** �ߵ��˶�������, ��Ҫ������ͳԵ��Լ� */
	public void stepAction(int direction){
		if(worm.outOfBounds(direction) 
				|| worm.eatSelf(direction)){
			//������� �����ж�,�������������¿�ʼ
			worm = new Snake();
			food = randomFood();
		}else{
			boolean eat = worm.step(direction, food);
			if(eat){
				food = randomFood();
			}
		}
	}
	/** �ߵ��˶�������, ��Ҫ������ͳԵ��Լ�
	 * ����Ĭ�ϵķ������� */
	public void stepAction(){
		if(worm.outOfBounds() 
				|| worm.eatSelf()){
			worm = new Snake();
			food = randomFood();
		}else{
			boolean eat = worm.step(food);
			if(eat){
				food = randomFood();
			}
		}
	}


	@Override
	public void paint(Graphics g) {
		//��ձ���
		//���Ʊ߽�
		//������
		//����ʳ��
		//g.setColor(Color.black);
		//g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(bg, 0, 0, null);
		g.translate(45, 45);
		//���Ʊ߽�
		//g.setColor(Color.gray);
		//g.drawRect(0, 0, 
		//		COLS*CELL_SIZE, ROWS*CELL_SIZE);
		//������
		//g.setColor(Color.lightGray);
		List<Cell> cells = worm.getCells();
		for(Cell cell: cells){
			int x = cell.getCol() * CELL_SIZE;
			int y = cell.getRow() * CELL_SIZE;
			//g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
			g.drawImage(cellImg, x, y, null);
		}
		//����ʳ��
		//g.setColor(Color.yellow);
		int x = food.getCol() * CELL_SIZE;
		int y = food.getRow() * CELL_SIZE;
		//g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
		g.drawImage(foodImg, x, y, null);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("̰����");
		SnakeGame game = new SnakeGame();
		frame.add(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(440, 440);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setVisible(true);
		game.action();
	}
}







