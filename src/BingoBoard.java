package com.martin_bolton_macdonald;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public abstract class BingoBoard{
	
	private final int MAX_BOARD_NUMBER = 90;
	private final int NUM_OF_TILES = 16;
	private List<Integer> boardNumbers = new ArrayList<>();
	
	public BingoBoard(){
		
		createBoardNums();
		
	}
	
	void createBoardNums(){
		
		boardNumbers.clear();
		
		Random rand = new Random();
		
		for (int i = 0; i < NUM_OF_TILES; i++){
			
			int nextNum;
			
			do{
				nextNum = rand.nextInt(MAX_BOARD_NUMBER) + 1;
			} while (boardNumbers.contains(new Integer(nextNum)));
			
			boardNumbers.add(nextNum);
			
		}
		
		boardNumbers.sort(new Comparator<Integer>(){
			
			@Override
			public int compare(Integer i, Integer j){
				return i.compareTo(j);
			}
			
		});
		
	}
	
	public List<Integer> getBoardNumbers(){
		return boardNumbers;
	}
	
	public void markBoardNumber(int num){
		
		boardNumbers.remove(new Integer(num));
		
	}
	
	public boolean isBoardComplete(){
		
		return boardNumbers.isEmpty();
		
	}
	
}