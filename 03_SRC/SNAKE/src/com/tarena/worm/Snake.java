package com.tarena.worm;

import java.util.LinkedList;
import java.util.List;

/**
 * 蛇 
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
		//默认的蛇的位置, 是蛇的方向
		direction = DOWN;
		for(int i=0; i<=10; i++){
			cells.add(new Cell(0, i));
		}
	}
	/** 当前方向走 */
	public void step(){
		Cell head = newHead();
		cells.addFirst(head);//add(0, head);
		cells.removeLast();//remove(size()-1)
	}
	/** 创建在默认方向上将要出现的下一个节点 */
	private Cell newHead() {
		Cell head = cells.getFirst();
		//新的头节点 原头节点 + direction
		int row = head.getRow() + direction/10;
		int col = head.getCol() + direction%10;
		return new Cell(row, col); 
	}
	/** 创建在指定方向上将要出现的下一个节点 */
	private Cell newHead(int direction) {
		Cell head = cells.getFirst();
		//新的头节点 原头节点 + direction
		int row = head.getRow() + direction/10;
		int col = head.getCol() + direction%10;
		return new Cell(row, col); 
	}
	/** 换方向走, 不能掉头走 */
	public void step(int direction){
		if(this.direction + direction==0){
			return;
		}
		this.direction = direction;//换方向
		step();
	}
	/** 检查在默认运行方向的下一步是否出界 */
	public boolean outOfBounds(){
		//下个新节点在界外
		Cell head = newHead();//下一步的位置
		int row = head.getRow();
		int col = head.getCol();
		return (row<0 || row>=SnakeGame.ROWS || 
				col<0 || col>=SnakeGame.COLS);
	}
	/** 检查在默认方向上运行时候是否能够吃到自己 */
	public boolean eatSelf(){
		Cell head = newHead();
		List<Cell> subList = 
			cells.subList(0, cells.size()-1);
		return subList.contains(head);
	}
	/** 检查当前蛇身上是否包含指定的节点 */
	public boolean contains(Cell food) {
		return cells.contains(food); 
	}
	/** 获取全部的蛇的节点, 用于绘制蛇神图像 */
	public LinkedList<Cell> getCells() {
		return cells;
	}
	/** 蛇走一步, 参数是食物, 如果蛇的新节点在食物的
	 * 位置上, 认为吃到食物, 返回true, 否则false
	 * 蛇吃到食物时候, 就不再删除末尾节点,蛇变长了!
	 * */
	public boolean step(Cell food) {
		Cell head = newHead();
		boolean eat = head.equals(food);
		cells.addFirst(head);
		if(!eat){//如果没有吃到食物, 就删除尾部节点
			cells.removeLast();
		}
		return eat;
	}
	/** 检查在指定方向上是否吃到自己的身体, 末尾节点不
	 * 包括在内 */
	public boolean eatSelf(int direction) {
		Cell head = newHead(direction);
		List<Cell> subList = 
			cells.subList(0, cells.size()-1);
		return subList.contains(head);
	}
	/** 检查在指定方向上运行的话, 是否出界了 */
	public boolean outOfBounds(int direction) {
		Cell head = newHead(direction);
		int row = head.getRow();
		int col = head.getCol();
		return row<0 || row>=SnakeGame.ROWS || 
					col<0 || col>=SnakeGame.COLS;
	}
	/** 在指定方向上运行, 如果吃到食物返回true
	 * 并且蛇变长 */
	public boolean step(int direction, Cell food) {
		if(this.direction==-direction){
			return false;
		}
		this.direction = direction;
		return this.step(food); 
	}
	
	
}







