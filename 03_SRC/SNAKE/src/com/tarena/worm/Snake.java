package com.tarena.worm;

import java.util.LinkedList;
import java.util.List;

/**
 * �� 
 *
 */
public class Snake {
	private LinkedList<Cell> cells = new LinkedList<Cell>();
	private int direction;
	
	public static final int UP = -10;
	public static final int DOWN = 10;
	public static final int RIGHT = 1;
	public static final int LEFT = -1;
	
	public Snake() {
		//Ĭ�ϵ��ߵ�λ��, ���ߵķ���
		direction = DOWN;
		for(int i=0; i<=10; i++){
			cells.add(new Cell(0, i));
		}
	}
	/** ��ǰ������ */
	public void step(){
		Cell head = newHead();
		cells.addFirst(head);//add(0, head);
		cells.removeLast();//remove(size()-1)
	}
	/** ������Ĭ�Ϸ����Ͻ�Ҫ���ֵ���һ���ڵ� */
	private Cell newHead() {
		Cell head = cells.getFirst();
		//�µ�ͷ�ڵ� ԭͷ�ڵ� + direction
		int row = head.getRow() + direction/10;
		int col = head.getCol() + direction%10;
		return new Cell(row, col); 
	}
	/** ������ָ�������Ͻ�Ҫ���ֵ���һ���ڵ� */
	private Cell newHead(int direction) {
		Cell head = cells.getFirst();
		//�µ�ͷ�ڵ� ԭͷ�ڵ� + direction
		int row = head.getRow() + direction/10;
		int col = head.getCol() + direction%10;
		return new Cell(row, col); 
	}
	/** ��������, ���ܵ�ͷ�� */
	public void step(int direction){
		if(this.direction + direction==0){
			return;
		}
		this.direction = direction;//������
		step();
	}
	/** �����Ĭ�����з������һ���Ƿ���� */
	public boolean outOfBounds(){
		//�¸��½ڵ��ڽ���
		Cell head = newHead();//��һ����λ��
		int row = head.getRow();
		int col = head.getCol();
		return (row<0 || row>=SnakeGame.ROWS || 
				col<0 || col>=SnakeGame.COLS);
	}
	/** �����Ĭ�Ϸ���������ʱ���Ƿ��ܹ��Ե��Լ� */
	public boolean eatSelf(){
		Cell head = newHead();
		List<Cell> subList = 
			cells.subList(0, cells.size()-1);
		return subList.contains(head);
	}
	/** ��鵱ǰ�������Ƿ����ָ���Ľڵ� */
	public boolean contains(Cell food) {
		return cells.contains(food); 
	}
	/** ��ȡȫ�����ߵĽڵ�, ���ڻ�������ͼ�� */
	public LinkedList<Cell> getCells() {
		return cells;
	}
	/** ����һ��, ������ʳ��, ����ߵ��½ڵ���ʳ���
	 * λ����, ��Ϊ�Ե�ʳ��, ����true, ����false
	 * �߳Ե�ʳ��ʱ��, �Ͳ���ɾ��ĩβ�ڵ�,�߱䳤��!
	 * */
	public boolean step(Cell food) {
		Cell head = newHead();
		boolean eat = head.equals(food);
		cells.addFirst(head);
		if(!eat){//���û�гԵ�ʳ��, ��ɾ��β���ڵ�
			cells.removeLast();
		}
		return eat;
	}
	/** �����ָ���������Ƿ�Ե��Լ�������, ĩβ�ڵ㲻
	 * �������� */
	public boolean eatSelf(int direction) {
		Cell head = newHead(direction);
		List<Cell> subList = 
			cells.subList(0, cells.size()-1);
		return subList.contains(head);
	}
	/** �����ָ�����������еĻ�, �Ƿ������ */
	public boolean outOfBounds(int direction) {
		Cell head = newHead(direction);
		int row = head.getRow();
		int col = head.getCol();
		return row<0 || row>=SnakeGame.ROWS || 
					col<0 || col>=SnakeGame.COLS;
	}
	/** ��ָ������������, ����Ե�ʳ�ﷵ��true
	 * �����߱䳤 */
	public boolean step(int direction, Cell food) {
		if(this.direction==-direction){
			return false;
		}
		this.direction = direction;
		return this.step(food); 
	}
	
	
}







