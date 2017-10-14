package com.martin_bolton_macdonald;

import com.martin_bolton_macdonald.BingoBoard;

public class BingoPlayer extends BingoBoard{
	
	private int numOfWins;
	private int gamesCompleted;
	
	public BingoPlayer(){
		super();
		numOfWins = 0;
		gamesCompleted = 0;
	}
	
	public void playAgain(){
		super.createBoardNums();
	}
	
	public void setGamesCompleted(){
		gamesCompleted++;
	}
	
	public void setGameTally(boolean gameWon){
		
		if (gameWon){
			numOfWins++;
		}
		
		gamesCompleted++;
	}
	
	public int getNumOfWins(){
		return numOfWins;
	}
	
	public int getGamesCompleted(){
		return gamesCompleted;
	}
}