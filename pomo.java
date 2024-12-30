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
import java.util.Timer; 
import java.util.TimerTask; 

/* Left to do in GUI
 * Get jlabel to print countdown
 * Set up linked list
 * Get drop down arrow to show new pomo set
 * Get list to print out certain node depending on which one was selected
 * Add jlabel error message if no time selected
 */

/* Other java GUI project ideas: 
 * - Japanese flash cards
 * - MP3 player
 * - Mini calculator
 * - 2048
 * - Snake game
 * - Chess game between two computers oh yes! 
 * - Draw out a tree! 
 * - Crazy drawing on a gui? Mandala? 
 */


public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
    private boolean timerFlag=false; 
    private Timer timer; 
    private TimerTask task; 
    private boolean errorFlag=false; 
    private int pomosComplete=0; 
    private int pomoSetCount=0; 
    LinkedList<String> list = new LinkedList<>(); 
    private boolean firstPomo = false; 
	/**
	 * Launch the application.
	 */
	
	public boolean isError() {
		return errorFlag; 
	}
	public boolean isTimerRunning() {
		return timerFlag; 
	}
	public void newTimerTask() {
		task = new TimerTask() {
				public void run() {
					System.out.println("Time's up!");
					pomosComplete++;
					if (pomosComplete == 1) {
						firstPomo = true; 
					}
					System.out.println("Completed pomodoros are: " + pomosComplete); 
					timer.cancel(); 
				}
			};
	}
	
	public void startNewTimer() {
		timer = new Timer();
	}
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

	/**
	 * Create the frame.
	 */
	public MainFrame() {	 
		startNewTimer();
		newTimerTask(); 
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
		
		JLabel Pomodoro = new JLabel("Pomodoro");
		Pomodoro.setForeground(new Color(128, 0, 128));
		Pomodoro.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 48));
		Pomodoro.setBounds(145, 0, 230, 64);
		contentPane.add(Pomodoro);
		
		JLabel lblNewLabel = new JLabel("Pomodoros Completed: ");
		lblNewLabel.setFont(new Font("Harlow Solid Italic", Font.ITALIC, 20));
		lblNewLabel.setBounds(76, 102, 237, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Pomo:");
		lblNewLabel_1.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_1.setBounds(43, 148, 84, 13);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(43, 164, 96, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(43, 234, 96, 30);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(43, 306, 96, 30);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(43, 376, 96, 30);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
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
		
		JLabel error1Label = new JLabel(""); 
		error1Label.setForeground(new Color(128, 0, 128));
		error1Label.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 10));
		error1Label.setBounds(43, 199, 143, 13);
		contentPane.add(error1Label);
		
		JButton btnNewButton = new JButton("Go");
		btnNewButton.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 10));
		btnNewButton.setBackground(new Color(226, 150, 213)); 
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstField = textField.getText();
				if (firstField.equals("")) {
					System.out.println("Nothing entered in field");
				}else {
					list.add(firstField);
					System.out.println("FirstField = " + firstField); 
				}
				
				String selected = (String) comboBox.getSelectedItem(); 
				System.out.println("Selected string is = " + selected);
				if(isTimerRunning()){
					timer.cancel();
					startNewTimer();
					newTimerTask(); 
				}
				if (isError()) {
					errorFlag = false; 
					error1Label.setText(""); 
				}
				if(firstPomo) {
					startNewTimer();
					newTimerTask(); 
					
				}
				if (!firstPomo) {
				if(selected.equals("10")) {
					timerFlag=true; 
					timer.schedule(task, 10*60*1000);
					System.out.println("Timer for 10 mins has started..."); 
				}else if(selected.equals("15")) {
				    timer.schedule(task, 1); 
					System.out.println("Timer for 15 mins has started..."); 
				}else if(selected.equals("20")) {
				    timer.schedule(task, 1);
					System.out.println("Timer for 20 mins has started..."); 
				}else if(selected.equals("25")) {
				    timer.schedule(task, 25*60*1000);
					System.out.println("Timer for 25 mins has started..."); 
				}else if(selected.equals("30")) {
				    timer.schedule(task, 30*60*1000);
					System.out.println("Timer for 30 mins has started..."); 
				}else if(selected.equals("45")) {
				    timer.schedule(task, 45*60*1000);
					System.out.println("Timer for 45 mins has started..."); 
				}else if(selected.equals("60")) {
				    timer.schedule(task, 60*60*1000);
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
		
		JLabel lblNewLabel_5 = new JLabel("00:00");
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setBackground(new Color(226, 150, 213));
		lblNewLabel_5.setFont(new Font("Harlow Solid Italic", Font.BOLD, 60));
		lblNewLabel_5.setBounds(322, 148, 143, 64);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton_1 = new JButton("Go");
		btnNewButton_1.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 10));
		btnNewButton_1.setBackground(new Color(226, 150, 213));
		btnNewButton_1.setBounds(145, 238, 66, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Go");
		btnNewButton_2.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 10));
		btnNewButton_2.setBackground(new Color(226, 150, 213));
		btnNewButton_2.setBounds(145, 310, 66, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Go");
		btnNewButton_3.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 10));
		btnNewButton_3.setBackground(new Color(226, 150, 213));
		btnNewButton_3.setBounds(145, 380, 66, 21);
		contentPane.add(btnNewButton_3);
		
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
		
		JLabel lblNewLabel_6 = new JLabel("Completed Pomodoros:");
		lblNewLabel_6.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_6.setBounds(322, 234, 164, 13);
		contentPane.add(lblNewLabel_6);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(322, 255, 143, 21);
		contentPane.add(comboBox_4);
		if (pomosComplete == 4) {
			pomoSetCount++; 
			pomosComplete=0;
			comboBox_4.addItem("Pomo #" + pomoSetCount); 
		}
		
		
		JLabel lblNewLabel_7 = new JLabel("Third Pomo:");
		lblNewLabel_7.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_7.setBounds(43, 288, 96, 13);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Fourth Pomo:");
		lblNewLabel_8.setFont(new Font("Meiryo UI", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_8.setBounds(43, 358, 96, 13);
		contentPane.add(lblNewLabel_8);

	}
}
