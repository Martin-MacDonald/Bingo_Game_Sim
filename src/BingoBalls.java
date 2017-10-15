package com.martin_bolton_macdonald;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class BingoBalls{
	
	private final int NUM_OF_BALLS = 90;
	private List<Integer> remainingBallList = new ArrayList<>();
	private List<Integer> calledBallsList = new ArrayList<>();
	
	public BingoBalls(){
		
		createNewBallList();
		
	}
	
	private void createNewBallList(){
		
		for (int i = 0; i < NUM_OF_BALLS; i++){
			remainingBallList.add(i + 1);
		}
		
	}
	
	public int drawNextBall(){
		
		int nextBall = remainingBallList.get(new Random().nextInt(remainingBallList.size()));
		
		setCalledBall(nextBall);
		
		return nextBall;
		
	}
	
	private void setCalledBall(int i){
		
		remainingBallList.remove(new Integer(i));
		calledBallsList.add(i);
		
		if (calledBallsList.size() > 1){
			
			calledBallsList.sort(new Comparator<Integer>(){
				
				@Override
				public int compare(Integer i, Integer j){
					return i.compareTo(j);
				}
				
			});
		}
		
	}
	
	public List<Integer> getRemainingBallList(){
		return remainingBallList;
	}
	
	public List<Integer> getCalledBallsList(){
		return calledBallsList;
	}
	
	public void resetBalls(){
		
		remainingBallList.clear();
		calledBallsList.clear();
		createNewBallList();
		
	}
	
}