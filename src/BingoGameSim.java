package com.martin_bolton_macdonald;

import com.martin_bolton_macdonald.BingoPlayer;
import com.martin_bolton_macdonald.BingoBalls;
import javax.swing.JFrame;
import java.awt.EventQueue;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.ButtonModel;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.Box;

public class BingoGameSim extends JFrame{
	
	private BingoPlayer bp1;
	private BingoPlayer bp2;
	private	BingoPlayer bp3;
	private BingoBalls bb;
	private JButton nextBallButton;
	private JLabel player1Label;
	private JLabel player2Label;
	private JLabel player3Label;
	private JLabel ballLabel;
	private JLabel gameOverLabel;
	private boolean gameInProgress;
	private JLabel scoreLabel;
	
	public BingoGameSim(){
		
		gameInProgress = true;
		createPlayers();
		createBalls();
		initComponents();
		
	}
	
	public static void main(String[] args){

		EventQueue.invokeLater(new Runnable(){
			
			@Override
			public void run(){
				new BingoGameSim().setVisible(true);
			}
			
		});
	
	}
	
	private void initComponents(){
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Bingo Game Simulator");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		scoreLabel = new JLabel("Win rate: 0/0");
		
		ballLabel = new JLabel("Ball: XX");
		
		nextBallButton = new JButton("Start");
		ButtonModel nextBallButtonModel = nextBallButton.getModel();
		nextBallButtonModel.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){

				if (nextBallButton.getText().equals("Play again?")){
					resetGame();
				} else {
					nextBall();
				}
				
				
			}
			
		});
		
		player1Label = new JLabel("You have the remaining balls: " + bp1.getBoardNumbers());
		player2Label = new JLabel("Player 2 has the remaining balls: " + bp2.getBoardNumbers());
		player3Label = new JLabel("Player 3 has the remaining balls: " + bp3.getBoardNumbers());
		
		gameOverLabel = new JLabel("You win");
		gameOverLabel.setVisible(false);
		
		setComponentFontAndAlign(scoreLabel, ballLabel, nextBallButton, player1Label, player2Label, player3Label, gameOverLabel);
		
		mainPanel.add(ballLabel);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(nextBallButton);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(player1Label);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(player2Label);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(player3Label);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(gameOverLabel);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(scoreLabel);
		mainPanel.add(Box.createVerticalStrut(20));
		add(mainPanel);
		setSize(1500, 600);
		
	}
	
	private void setComponentFontAndAlign(JComponent... args){
		
		Font font = new Font("Serif", Font.BOLD, 30);
		
		for (JComponent arg : args){
			arg.setFont(font);
			arg.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		}
		
	}
	
	private void createPlayers(){
		
		bp1 = new BingoPlayer();
		bp2 = new BingoPlayer();
		bp3 = new BingoPlayer();
		
	}
	
	private void createBalls(){
		
		bb = new BingoBalls();
		
	}
	
	private void nextBall(){
		
		int nextBall = bb.drawNextBall();
		
		ballLabel.setText("Next Ball: " + nextBall);
		
		bp1.markBoardNumber(nextBall);
		bp2.markBoardNumber(nextBall);	
		bp3.markBoardNumber(nextBall);

		nextBallButton.setText("Next Ball");
		
		if (bp1.isBoardComplete() || bp2.isBoardComplete() || bp3.isBoardComplete()){
			if (bp1.isBoardComplete()){
				bp1.setGameTally(true);
				gameOverLabel.setText("YOU WON!!!!");
			}else{
				bp1.setGameTally(false);
				gameOverLabel.setText("SORRY. BETTER LUCK NEXT TIME!!!");
			}
			
			
			gameInProgress = false;
			changeGameState();			
		}
		
		updateBalls();		
		
	}
	
	private void updateBalls(){
		
		player1Label.setText("You have the remaining balls: " + bp1.getBoardNumbers());
		player2Label.setText("Player 2 has the remaining balls: " + bp2.getBoardNumbers());
		player3Label.setText("Player 3 has the remaining balls: " + bp3.getBoardNumbers());
		
	}
	
	private void changeGameState(){
		
		if (!gameInProgress){
			
			ballLabel.setVisible(false);
			nextBallButton.setText("Play again?");
			player1Label.setVisible(false);
			player2Label.setVisible(false);
			player3Label.setVisible(false);
			gameOverLabel.setVisible(true);
			scoreLabel.setVisible(false);
			
		} else {
			
			ballLabel.setVisible(true);
			ballLabel.setText("Ball: XX");
			nextBallButton.setText("Start");
			player1Label.setVisible(true);
			player2Label.setVisible(true);
			player3Label.setVisible(true);
			gameOverLabel.setVisible(false);
			scoreLabel.setVisible(true);
			scoreLabel.setText("Win rate: " + bp1.getNumOfWins() + "/" + bp1.getGamesCompleted());
			
		}
		
	}
	
	private void resetGame(){
		
		gameInProgress = true;
		
		bb.resetBalls();
		
		bp1.playAgain();
		bp2.playAgain();
		bp3.playAgain();
		
		updateBalls();
		
		changeGameState();

	}
	
}