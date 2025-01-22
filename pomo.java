
package practicegui;

import java.awt.EventQueue;

import java.util.LinkedList; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JEditorPane;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTree;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JTable; 
import javax.swing.Timer;


class Node{
	String subjectDone;
	int timeDone;
	int itemNum; 
	Node next; 
	
	public Node(String subjectDone, int timeDone, int itemNum) {
		this.subjectDone = subjectDone;
		this.timeDone = timeDone;
		this.itemNum = itemNum; 
		this.next=null; 
	}
}

class customList{
	private Node head; 

	//Function to add nodes
	public void add(String subjectDone, int timeDone, int itemNum) {
		Node newNode = new Node(subjectDone, timeDone, itemNum);
		if (head == null) {
			head = newNode; // If list is empty, new node becomes the head
		} else {
			Node current = head;
			while (current.next != null) { // Traverse to the end of the list
				current = current.next;
			}
			current.next = newNode; // Add new node at the end
		}
	}
	
	public void display(int num) {
		int count=0; 
		Node temp = head; 
		while(temp!=null) {
			
			if(temp.itemNum == num) {
				break; 
			}
			temp=temp.next; 
		}
		while(temp!=null && count!=4) {
			count++; 
			System.out.println("Subject done is = " + temp.subjectDone);
			temp=temp.next;
		}
	} 
}


public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
    private boolean errorFlag = true; 
    private int pomosComplete=0; 
    private int pomoSetCount=0; 
  //  LinkedList<String> list = new LinkedList<>(); 
    private boolean firstPomo = true;
    private boolean secondPomo = false; 
    private boolean thirdPomo = false;
    private boolean fourthPomo = false; 
    private int timeRemaining= 0;
    private JLabel timerLabel; 
    private JComboBox comboLinked;
    private int counter = 0; 
    private customList list = new customList(); 
    
	public boolean isError() {
		return errorFlag; 
	}
	
	//Main function
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//MainFrame function
	public MainFrame() {	  
		//Set up configuration design
		setForeground(new Color(204, 153, 255));
		setType(Type.UTILITY);
		setBackground(new Color(255, 185, 250));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 325, 525, 487);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(10, 20));
		contentPane.setBackground(new Color(231, 204, 244));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Pomodoro label
		JLabel Pomodoro = new JLabel("Pomodoro");
		Pomodoro.setForeground(new Color(128, 0, 128));
		Pomodoro.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 48));
		Pomodoro.setBounds(145, 0, 230, 64);
		contentPane.add(Pomodoro);
		
		//Pomodoros completed label
		JLabel lblNewLabel = new JLabel("Pomodoros Completed: ");
		lblNewLabel.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 20));
		lblNewLabel.setBounds(76, 102, 237, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Second Pomo:");
		lblNewLabel_2.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_2.setBounds(42, 218, 110, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Study Session");
		lblNewLabel_3.setForeground(new Color(128, 0, 128));
		lblNewLabel_3.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 19));
		lblNewLabel_3.setBounds(192, 48, 121, 19);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Timer:");
		lblNewLabel_4.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 20));
		lblNewLabel_4.setBounds(364, 102, 74, 20);
		contentPane.add(lblNewLabel_4);
		
		//Timer label button
		timerLabel = new JLabel("00:00");
		timerLabel.setOpaque(true);
		timerLabel.setBackground(new Color(226, 150, 213));
		timerLabel.setFont(new Font("Harlow Solid Italic", Font.BOLD, 60));
		timerLabel.setBounds(322, 148, 143, 64);
		contentPane.add(timerLabel);
		
		

		comboLinked = new JComboBox();
		comboLinked.setBounds(322, 255, 143, 21);
		comboLinked.addItem("Select Pomodoro"); 
		contentPane.add(comboLinked);
		
		
		/****************************************First Pomo*********************************************/
		//First pomo label
		JLabel lblNewLabel_1 = new JLabel("First Pomo:");
		lblNewLabel_1.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_1.setBounds(43, 148, 84, 13);
		contentPane.add(lblNewLabel_1);
		
		//Pomo 1 textfield
		textField = new JTextField();
		textField.setBounds(43, 164, 96, 30);
		contentPane.add(textField);
		textField.setColumns(10);
				
		//First Combo box for the first pomo
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 10));
		comboBox.setBackground(new Color(226, 150, 213));
		comboBox.setBounds(217, 168, 61, 21);
		comboBox.addItem("Time");
		comboBox.addItem("10");
		comboBox.addItem("15");
		comboBox.addItem("20");
		comboBox.addItem("25");
		comboBox.addItem("30");
		comboBox.addItem("45");
		comboBox.addItem("60");
		contentPane.add(comboBox);
		
		//Error label for first pomo
		JLabel error1Label = new JLabel(""); 
		error1Label.setForeground(new Color(128, 0, 128));
		error1Label.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 10));
		error1Label.setBounds(43, 199, 143, 13);
		contentPane.add(error1Label);
		
		//Go button for first pomo
		JButton btnNewButton = new JButton("Go");
		btnNewButton.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 10));
		btnNewButton.setBackground(new Color(226, 150, 213)); 
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstField = textField.getText();
				if (firstField.equals("")) {
					System.out.println("Nothing entered in field");
				}
				
				if (isError()){
					errorFlag = false; 
					error1Label.setText("Please enter in a time"); 
				}else {
					error1Label.setText(""); 
				}
				
				String selected = (String) comboBox.getSelectedItem(); 
				System.out.println("Selected string is = " + selected);
				
				if(firstPomo) {//If timer running is false...
					counter++; 
					if(selected.equals("10")) {
						error1Label.setText("");
						timeRemaining = 10;  
						startTimer(); 
						list.add(firstField,10, counter);
						
						
						System.out.println("Timer for 10 mins has started..."); 	
					}else if(selected.equals("15")) {
						timeRemaining = 60*15; 
						System.out.println("Timer for 15 mins has started..."); 
					}else if(selected.equals("20")) {
						System.out.println("Timer for 20 mins has started..."); 
					}else if(selected.equals("25")) {
						System.out.println("Timer for 25 mins has started..."); 
					}else if(selected.equals("30")) {
						System.out.println("Timer for 30 mins has started..."); 
					}else if(selected.equals("45")) {
						System.out.println("Timer for 45 mins has started..."); 
					}else if(selected.equals("60")) {
						System.out.println("Timer for 60 mins has started..."); 
					}else {
						errorFlag = true; 
						System.out.println("Error occured with the go button"); 
						error1Label.setText("Please enter in a time"); 
					}
				}
			}
			
		});
		btnNewButton.setBounds(146, 168, 65, 21);
		contentPane.add(btnNewButton);
		
		/********************************End of first pomo*******************************************/
		
		
		/********************************Start of second pomo***************************************/ 
		//Pomo 2 textfield
		textField_1 = new JTextField();
		textField_1.setBounds(43, 234, 96, 30);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		//Combo box for the second pomo
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 10));
		comboBox_1.setBackground(new Color(226, 150, 213));
		comboBox_1.setBounds(217, 238, 61, 21);
		comboBox_1.addItem("Time");
		comboBox_1.addItem("10");
		comboBox_1.addItem("15");
		comboBox_1.addItem("20");
		comboBox_1.addItem("25");
		comboBox_1.addItem("30");
		comboBox_1.addItem("45");
		comboBox_1.addItem("60");
		contentPane.add(comboBox_1);
		
		JLabel error2Label = new JLabel("");
		error2Label.setForeground(new Color(128, 0, 128));
		error2Label.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 10));
		error2Label.setBounds(43, 274, 143, 13);
		contentPane.add(error2Label);
	
		JButton btnNewButton_1 = new JButton("Go");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstField_1 = textField_1.getText(); 
				if (firstField_1.equals("")) {
					System.out.println("Nothing entered in field");
				}
				
				String selected_1 = (String) comboBox_1.getSelectedItem(); 
				System.out.println("Selected string is = " + selected_1); 

				if (isError()) {
					System.out.println("Inside isError");
					errorFlag = false; 
					error2Label.setText("Please enter in a time"); 
				}else {
					error2Label.setText(""); 
				}
				System.out.println("Outside of checking if on secondPomo ");  
				if (secondPomo){
					counter++;
					if(selected_1.equals("10")) { 
						timeRemaining = 10;
						startTimer(); 
						list.add(firstField_1, 10, counter);
						System.out.println("Timer for 10 mins has started..."); 
					}else if(selected_1.equals("15")) {
						System.out.println("Timer for 15 mins has started..."); 
					}else if(selected_1.equals("20")) {
						System.out.println("Timer for 20 mins has started..."); 
					}else if(selected_1.equals("25")) {
						System.out.println("Timer for 25 mins has started..."); 
					}else if(selected_1.equals("30")) {
						System.out.println("Timer for 30 mins has started..."); 
					}else if(selected_1.equals("45")) {
						System.out.println("Timer for 45 mins has started..."); 
					}else if(selected_1.equals("60")) {
						System.out.println("Timer for 60 mins has started..."); 
					}else {
						errorFlag = true; 
						System.out.println("Error occured with the go button"); 
						error2Label.setText("Please enter in a time"); 
					}
				}
			}
			
		});
		btnNewButton_1.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 10));
		btnNewButton_1.setBackground(new Color(226, 150, 213));
		btnNewButton_1.setBounds(145, 238, 66, 21);
		contentPane.add(btnNewButton_1);
		
		/**********************************End of second pomo************************************/
		
		/**********************************Start of third pomo***********************************/
		
		//Label for third pomo
		JLabel lblNewLabel_7 = new JLabel("Third Pomo:");
		lblNewLabel_7.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_7.setBounds(43, 288, 96, 13);
		contentPane.add(lblNewLabel_7);
		
		//Textfield for the third pomo 
		textField_2 = new JTextField();
		textField_2.setBounds(43, 306, 96, 30);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		//Error label for the third pomo
		JLabel error3Label = new JLabel("");
		error3Label.setForeground(new Color(128, 0, 128));
		error3Label.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 10));
		error3Label.setBounds(42, 346, 144, 13);
		contentPane.add(error3Label);
		
		//Combo box for third pomo
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 10));
		comboBox_2.setBackground(new Color(226, 150, 213));
		comboBox_2.setBounds(217, 310, 61, 21);
		comboBox_2.addItem("Time");
		comboBox_2.addItem("10");
		comboBox_2.addItem("15");
		comboBox_2.addItem("20");
		comboBox_2.addItem("25");
		comboBox_2.addItem("30");
		comboBox_2.addItem("45");
		comboBox_2.addItem("60");
		contentPane.add(comboBox_2);
		
		//Go button for third pomo
		JButton btnNewButton_2 = new JButton("Go");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstField_2 = textField_2.getText(); 
				if (firstField_2.equals("")) {
					System.out.println("Nothing entered in field");
				}
				
				String selected_2 = (String) comboBox_2.getSelectedItem(); 
				System.out.println("Selected string is = " + selected_2); 

				if (isError()) {
					errorFlag = false; 
					error3Label.setText("Please enter in a time"); 
				}else {
					error3Label.setText(""); 
				}
				
				if (thirdPomo){
					counter++; 
					if(selected_2.equals("10")) { 
						timeRemaining = 10;
						startTimer(); 
						list.add(firstField_2,10, counter);
						System.out.println("Timer for 10 mins has started..."); 
					}else if(selected_2.equals("15")) {
						System.out.println("Timer for 15 mins has started..."); 
					}else if(selected_2.equals("20")) {
						System.out.println("Timer for 20 mins has started..."); 
					}else if(selected_2.equals("25")) {
						System.out.println("Timer for 25 mins has started..."); 
					}else if(selected_2.equals("30")) {
						System.out.println("Timer for 30 mins has started..."); 
					}else if(selected_2.equals("45")) {
						System.out.println("Timer for 45 mins has started..."); 
					}else if(selected_2.equals("60")) {
						System.out.println("Timer for 60 mins has started..."); 
					}else {
						errorFlag = true; 
						System.out.println("Error occured with the go button"); 
						error3Label.setText("Please enter in a time"); 
					}
				}
			}
		});
		btnNewButton_2.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 10));
		btnNewButton_2.setBackground(new Color(226, 150, 213));
		btnNewButton_2.setBounds(145, 310, 66, 21);
		contentPane.add(btnNewButton_2);
		
		/**********************************End of third pomodoro****************************************/
		

		
		/**********************************Start of fourth pomodoro*************************************/
		//Label for fourth pomo
		JLabel lblNewLabel_8 = new JLabel("Fourth Pomo:");
		lblNewLabel_8.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_8.setBounds(43, 358, 96, 13);
		contentPane.add(lblNewLabel_8);
		
		//Textfield for the fourth pomo
		textField_3 = new JTextField();
		textField_3.setBounds(43, 376, 96, 30);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		//Combo box for fourth pomo
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 10));
		comboBox_3.setBackground(new Color(226, 150, 213));
		comboBox_3.setBounds(217, 380, 61, 21);
		comboBox_3.addItem("Time");
		comboBox_3.addItem("10");
		comboBox_3.addItem("15");
		comboBox_3.addItem("20");
		comboBox_3.addItem("25");
		comboBox_3.addItem("30");
		comboBox_3.addItem("45");
		comboBox_3.addItem("60");
		contentPane.add(comboBox_3);

		//Error label for the fourth pomo
		JLabel error4Label = new JLabel("");
		error4Label.setForeground(new Color(128, 0, 128));
		error4Label.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 10));
		error4Label.setBounds(42, 411, 144, 13);
		contentPane.add(error4Label);

		//Button for the fourth pomo
		JButton btnNewButton_3 = new JButton("Go");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstField_3 = textField_3.getText(); 
				if (firstField_3.equals("")) {
					System.out.println("Nothing entered in field");
				}
				
				String selected_3 = (String) comboBox_3.getSelectedItem(); 
				System.out.println("Selected string is = " + selected_3); 

				if (isError()) {
					System.out.println("Inside isError");
					errorFlag = false; 
					error4Label.setText("Please enter in a time"); 
				}else {
					error4Label.setText(""); 
				}
				System.out.println("Outside of checking if on secondPomo ");  
				if (fourthPomo){
					counter++; 
					if(selected_3.equals("10")) {
						timeRemaining = 10;
						startTimer(); 
						list.add(firstField_3, 10, counter);
						
						
						/* if (pomosComplete == 4) {
		                    	pomosComplete = 0;
		                    	pomoSetCount++; 
		                    	String created = "#" + pomoSetCount + " Pomodoro"; 
		                    	comboLinked.addItem(created);
		                    	String selectedCombo = (String) comboLinked.getSelectedItem();
		                    	
		                    		System.out.println(""+selectedCombo.charAt(1));
		                    		char character = selectedCombo.charAt(1);
		                    		if(Character.isDigit(character)) {
		                    			String numericPart = selectedCombo.split(" ")[0].substring(1);
		                    			int number = Integer.parseInt(numericPart);
		                    			list.display(number); 
		                    		
		                    		System.out.println("Completed a pomo set!");
		                    	}  
		                */ 
						
					
						
						System.out.println("Timer for 10 mins has started..."); 
					}else if(selected_3.equals("15")) {
						System.out.println("Timer for 15 mins has started..."); 
					}else if(selected_3.equals("20")) {
						System.out.println("Timer for 20 mins has started..."); 
					}else if(selected_3.equals("25")) {
						System.out.println("Timer for 25 mins has started..."); 
					}else if(selected_3.equals("30")) {
						System.out.println("Timer for 30 mins has started..."); 
					}else if(selected_3.equals("45")) {
						System.out.println("Timer for 45 mins has started..."); 
					}else if(selected_3.equals("60")) {
						System.out.println("Timer for 60 mins has started..."); 
					}else {
						errorFlag = true; 
						System.out.println("Error occured with the go button"); 
						error4Label.setText("Please enter in a time"); 
					}
				}			
			}
		});
		btnNewButton_3.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 10));
		btnNewButton_3.setBackground(new Color(226, 150, 213));
		btnNewButton_3.setBounds(145, 380, 66, 21);
		contentPane.add(btnNewButton_3);
	
		/*************************************End of fourth pomo**********************************************/
		
		
		/*************************************Working with the linked list************************************/
		
		
		JLabel lblNewLabel_6 = new JLabel("Completed Pomodoros:");
		lblNewLabel_6.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_6.setBounds(322, 234, 164, 13);
		contentPane.add(lblNewLabel_6);
		
	}
	
	/****************************************End of working with linked list**********************************/ 
	
	  //Start timer function
	  public void startTimer(){
		  
	        ActionListener countdownAction = new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	
	                if (timeRemaining>0) {
	                    timeRemaining--;
	                    updateTimerLabel();
	                } else {
	                    //Timer finished
	                    ((Timer)e.getSource()).stop();
	                    if (firstPomo) {
	                    	firstPomo = false; 
	                    	secondPomo = true;
	                    }else if(secondPomo) {
	                    	secondPomo = false;
	                    	thirdPomo = true; 
	                    }else if (thirdPomo) {
	                    	thirdPomo = false;
	                    	fourthPomo = true; 
	                    }else if (fourthPomo) {
	                    	firstPomo = true; 
	                    	fourthPomo = false;  
	                    }
	                    pomosComplete++;
	                    if (pomosComplete == 4) {
	                    	pomosComplete = 0;
	                    	pomoSetCount++; 
	                    	String created = "#" + pomoSetCount + " Pomodoro"; 
	                    	comboLinked.addItem(created);
	                    	String selectedCombo = (String) comboLinked.getSelectedItem();
	                    	
	                    	System.out.println(""+selectedCombo.charAt(1));
	                    	char character = selectedCombo.charAt(1);
	                    	boolean okay = true; 
	                    	while(okay == true) {
	                    	if(Character.isDigit(character)) {
	                    		String numericPart = selectedCombo.split(" ")[0].substring(1);
	                    		int number = Integer.parseInt(numericPart);
	                    		list.display(number); 
	                    		okay = false; 
	                    		}
	                    		System.out.println("Completed a pomo set!");
	                    	}  
	                    }
	                
	                }
	            }
	        };
	        Timer timer = new Timer(1000, countdownAction);
	        timer.start();
	    }
	  
	  	//Update the timer label
	    private void updateTimerLabel() {
	        int minutes = timeRemaining/60;
	        int seconds = timeRemaining%60;
	        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
	    }

}
