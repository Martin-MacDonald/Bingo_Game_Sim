package com.martin_bolton_macdonald;

import com.martin_bolton_macdonald.BingoPlayer;
import com.martin_bolton_macdonald.BingoBalls;

public class BingoGameSim{
	
	private BingoPlayer bp1;
	private BingoPlayer bp2;
	private	BingoPlayer bp3;
	private BingoBalls bb;
	
	public BingoGameSim(){
		
		createPlayers();
		createBalls();
		startPlaying();
		
	}
	
	public static void main(String[] args){
		
		new BingoGameSim();
	
	}
	
	private void createPlayers(){
		
		bp1 = new BingoPlayer();
		bp2 = new BingoPlayer();
		bp3 = new BingoPlayer();
		
	}
	
	private void createBalls(){
		
		bb = new BingoBalls();
		
	}
	
	private void startPlaying(){
		
		while (!bp1.isBoardComplete() && !bp2.isBoardComplete() && !bp3.isBoardComplete()){
			
			int nextBall = bb.drawNextBall();
			
			bp1.markBoardNumber(nextBall);
			bp2.markBoardNumber(nextBall);	
			bp3.markBoardNumber(nextBall);	
			
			System.out.println(nextBall);
			
			try{
				Thread.sleep(500);				
			} catch (InterruptedException ie){
				ie.printStackTrace();
			}

			
		}
		
		if (bp1.isBoardComplete()){
			System.out.println("Player one wins");
		} else if (bp2.isBoardComplete()){
			System.out.println("Player two wins");
		} else if (bp3.isBoardComplete()){
			System.out.println("Player three wins");
		}
		
	}
	
}