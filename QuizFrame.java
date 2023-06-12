package Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizFrame extends JFrame {
    
	private static int intervalNum = 1;
    private static int numCorrect = 0;
    private static int attemptNum = 1;
  
    private int numQuestions;
    private String playerName;
    private JPanel startPanel;
    private JPanel componentPanel;
    private JTextField quizLength;
    private JTextField enterName;
    private JLabel fieldLabelLength;
    private JLabel fieldLabelName;
    private JPanel quizPanel;
    private JPanel questionPanel;
    private JPanel scorePanel;
    private JLabel scoreLabel;
    private JLabel questionLabel;
    private JButton startButton;
    private JPanel buttonPanel;
    private JPanel playIntervalPanel = new JPanel();
	private JButton replayInterval = new JButton("Play Interval");
	private JButton playAgainYes;
	private JButton playAgainNo;
    private ArrayList<JButton> answerButtons;
    private ArrayList<Interval> intervals = new ArrayList<Interval>();
    
    public QuizFrame() {
    	this.setTitle("Interval Training");
    	this.startPanel = new JPanel();
    	this.componentPanel = new JPanel(new GridLayout(0, 5));
    	this.quizLength = new JTextField(10);
    	this.enterName = new JTextField(10);
    	this.fieldLabelLength = new JLabel("Number of Questions: ");
    	this.fieldLabelName = new JLabel("Enter your name: ");
    	createStartButton();
    	this.setSize(1000, 900);
    	this.startPanel.add(componentPanel);
    	this.startPanel.add(fieldLabelName);
    	this.startPanel.add(enterName);
    	this.startPanel.add(fieldLabelLength);
    	this.startPanel.add(quizLength);
    	this.startPanel.add(startButton);
    	this.add(startPanel);
    }
    
    public void updateQuestionLabel() {
    	questionLabel.setText(intervalNum + " / " + numQuestions);
    	revalidate();
    	setVisible(true);
    }
    
    public void quizSetup() {
    	remove(startPanel);
    	quizPanel = new JPanel();
    	quizPanel.setLayout(new BorderLayout());
    	buttonPanel = new JPanel();
    	buttonPanel.setLayout(new GridLayout(0,2));
    	questionLabel = new JLabel(intervalNum + " / " + numQuestions);
    	questionLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
    	questionPanel = new JPanel();
    	questionPanel.setPreferredSize(new Dimension(150, 150));
    	questionPanel.add(questionLabel);
    	quizPanel.add(this.buttonPanel, BorderLayout.CENTER);
    	quizPanel.add(playIntervalPanel, BorderLayout.NORTH);
    	quizPanel.add(questionPanel, BorderLayout.SOUTH);
    	add(quizPanel);
    	generateIntervals();
    	createPlayIntervalButton();
    	createAnswerButtons();
    	revalidate();
    	setVisible(true);
    }
    
    public void finalScreenSetup() {
    	remove(quizPanel);
    	scorePanel = new JPanel();
    	scorePanel.add(scoreLabel);
    	createPlayAgainButton();
    	createQuitButton();
    	scorePanel.add(playAgainYes);
    	scorePanel.add(playAgainNo);
    	add(scorePanel);
    	revalidate();
    	setVisible(true);
    	
    }
    
    public void createStartButton() {
       startButton = new JButton("Start Training");
       setButtonColorGreen(startButton);
 
       class StartQuizListener implements ActionListener
       {
          public void actionPerformed(ActionEvent event)
          {
        	  numQuestions = Integer.parseInt(quizLength.getText());
        	  playerName = enterName.getText();
        	  quizSetup();
        	  IntervalThread intervalThread = new IntervalThread(intervals.get(0));
              Thread thread = new Thread(intervalThread);
              thread.start();
          }            
       }
 
       ActionListener listener = new StartQuizListener();
       startButton.addActionListener(listener);
    }
    
    public void createPlayAgainButton() {
        playAgainYes = new JButton("Play again");
        setButtonColorGreen(playAgainYes);
        class PlayAgainListener implements ActionListener
        {
           public void actionPerformed(ActionEvent event)
           {
         	  remove(scorePanel);
         	  intervalNum = 1;
         	  numCorrect = 0;
         	  dispose();
        	  JFrame frame = new QuizFrame();
         	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         	  frame.setVisible(true);
           }            
        }
  
        ActionListener listener = new PlayAgainListener();
        playAgainYes.addActionListener(listener);
    }
    
    public void createQuitButton() {
        playAgainNo = new JButton("Quit");
        setButtonColorRed(playAgainNo);
  
        class QuitQuizListener implements ActionListener
        {
           public void actionPerformed(ActionEvent event)
           {
        	   System.exit(0);
           }            
        }
  
        ActionListener listener = new QuitQuizListener();
        playAgainNo.addActionListener(listener);
    }
    
    public void createPlayIntervalButton() {
    	replayInterval = new JButton("Play Interval");
    	replayInterval.setPreferredSize(new Dimension(150, 70));
        playIntervalPanel.add(replayInterval);
        playIntervalPanel.setPreferredSize(new Dimension(100, 100));
  
        class PlayIntervalListener implements ActionListener
        {
           public void actionPerformed(ActionEvent event)
           {
        	   IntervalThread intervalThread = new IntervalThread(intervals.get(intervalNum - 1));
               Thread thread = new Thread(intervalThread);
               thread.start();
           }            
        }
  
        ActionListener listener = new PlayIntervalListener();
        replayInterval.addActionListener(listener);
     }
    
    public void createAnswerButtons() {
        answerButtons = new ArrayList<JButton>();
    	for (int i = 0; i < 13; i++) {
        	JButton answerButton = new JButton();
        	answerButtons.add(answerButton);
        	buttonPanel.add(answerButton);
        }
    	answerButtons.get(0).setText("P1");
    	answerButtons.get(1).setText("m2");
    	answerButtons.get(2).setText("M2");
    	answerButtons.get(3).setText("m3");
    	answerButtons.get(4).setText("M3");
    	answerButtons.get(5).setText("P4");
    	answerButtons.get(6).setText("d5");
    	answerButtons.get(7).setText("P5");
    	answerButtons.get(8).setText("m6");
    	answerButtons.get(9).setText("M6");
    	answerButtons.get(10).setText("m7");
    	answerButtons.get(11).setText("M7");
    	answerButtons.get(12).setText("P8");
    	
        class AnswerListener implements ActionListener {
        	public void actionPerformed(ActionEvent event) {
        		if (event.getActionCommand().equals(intervals.get(intervalNum - 1).getIntervalName())) {
    				numCorrect++;
    			}
        		intervalNum++;
        		if (intervalNum <= numQuestions) {
        			updateQuestionLabel();
            		IntervalThread intervalThread = new IntervalThread(intervals.get(intervalNum - 1));
            		Thread thread = new Thread(intervalThread);
            		thread.start();
        		}
        		else {
        			attemptCheck();
        			sql();
        			finalScreenSetup();
        		}
        	}            
        }
        	    
        ActionListener listener = new AnswerListener();
        for (int i = 0; i < 13; i++) {
        	answerButtons.get(i).addActionListener(listener);
        	if (answerButtons.get(i).getText().contains("M")) {
        		setButtonColorGreen(answerButtons.get(i));
        	}
        	else if (answerButtons.get(i).getText().contains("m")) {
        		setButtonColorCyan(answerButtons.get(i));
        	}
        	else if (answerButtons.get(i).getText().contains("d")) {
        		setButtonColorOrange(answerButtons.get(i));
        	}
        	else if (answerButtons.get(i).getText().contains("P")) {
        		setButtonColorYellow(answerButtons.get(i));
        	}
        }	
    }
    
	public void generateIntervals() {
		for (int i = 0; i < numQuestions; i++) {
			Interval interval = new Interval();
			intervals.add(interval);
		}
	}
	
	public void setButtonColorGreen(JButton button) {
		button.setBackground(new Color(0, 153, 0));
        button.setOpaque(true);
        button.setBorderPainted(false);
	}
	
	public void setButtonColorRed(JButton button) {
		button.setBackground(new Color(255, 51, 51));
		button.setOpaque(true);
		button.setBorderPainted(false);
	}
	
	public void setButtonColorYellow(JButton button) {
		button.setBackground(new Color(255, 255, 153));
		button.setOpaque(true);
		button.setBorderPainted(false);
	}
	
	public void setButtonColorCyan(JButton button) {
		button.setBackground(new Color(51, 153, 255));
		button.setOpaque(true);
		button.setBorderPainted(false);
	}
	
	public void setButtonColorOrange(JButton button) {
		button.setBackground(new Color(255, 153, 0));
		button.setOpaque(true);
		button.setBorderPainted(false);
	}
	
	public void attemptCheck() {
		Connection connection =null;
		try {
			connection = DriverManager.getConnection
					("jdbc:sqlite:intervalquizdb_Attempt.db");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		String sqlInstruction = "select AttemptNum from Attempt";
		
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sqlInstruction);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	    ResultSet resultSet = null;
		try {
			resultSet = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	    try {
			while (resultSet.next()) {
				attemptNum++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	public void sql() {
		
	    Connection connection =null;
		try {
			connection = DriverManager.getConnection
			  ("jdbc:sqlite:intervalquizdb_Attempt.db");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		String sqlInstruction = "INSERT INTO Attempt (AttemptNum, PlayerName, "
				+ "NumQuestions, NumCorrect) VALUES (?,?,?,?)";
		
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sqlInstruction);
			stmt.setInt(1, attemptNum);
			stmt.setString(2, playerName);
			stmt.setInt(3, numQuestions);
			stmt.setDouble(4, numCorrect);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		String sqlInstruction2 = "select NumQuestions, NumCorrect from Attempt";
		
		PreparedStatement stmt2 = null;
		try {
			stmt2 = connection.prepareStatement(sqlInstruction2);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	    ResultSet resultSet = null;
		try {
			resultSet = stmt2.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	    try {
			while(resultSet.next()) {
				scoreLabel = new JLabel("Your final score is " + resultSet.getInt(2) + "/" + 
						resultSet.getInt(1) + " (" + Math.round(resultSet.getDouble(2) / resultSet.getDouble(1) * 100) + 
						"%). Play again?");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	    try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}
  
}
