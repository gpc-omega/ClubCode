import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class SignInForm extends JFrame{
	
	static final int WIDTH = 400;
	static final int HEIGHT = 400;
	
	private SignInFile file;
	
	JPanel mainPanel;
	JPanel namePanel;
	JPanel phonePanel;
	JPanel emailPanel;
	JPanel studIDPanel;
	JPanel buttonPanel;
	JPanel outputPanel;
	
	JLabel titleLabel;
	JLabel nameLabel;
	JLabel phoneLabel;
	JLabel emailLabel;
	JLabel studIDLabel;
	JLabel outputLabel;
	
	JTextField nameField;
	JTextField phoneField;
	JTextField emailField;
	JTextField studIDField;
	
	JButton submitButton;
	JButton clearButton;
	
	public static void main(String[] args){
		JFrame frame = new SignInForm();
		frame.setTitle("Project Omega: Sign In Form");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

	public SignInForm() {
		file = new SignInFile();
		
		titleLabel = new JLabel("Welcome to the Club Meeting.\nPlease sign in:");
		
		namePanel = new JPanel();
		nameLabel = new JLabel("Student Name: ");
		nameField = new JTextField(10);
		nameField.setText("Jane Doe");
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		
		phonePanel = new JPanel();
		phoneLabel = new JLabel("Phone Number: ");
		phoneField = new JTextField(10);
		phoneField.setText("555-123-4567");
		phonePanel.add(phoneLabel);
		phonePanel.add(phoneField);
		
		emailPanel = new JPanel();
		emailLabel = new JLabel("Email Address: ");
		emailField = new JTextField(10);
		emailField.setText("JaneDoe@example.com");
		emailPanel.add(emailLabel);
		emailPanel.add(emailField);
		
		studIDPanel = new JPanel();
		studIDLabel = new JLabel("      Student ID: ");
		studIDField = new JTextField(10);
		studIDField.setText("900555555");
		studIDPanel.add(studIDLabel);
		studIDPanel.add(studIDField);

		buttonPanel = new JPanel();
		submitButton = new JButton("submit");
		clearButton = new JButton("clear");
		buttonPanel.add(submitButton);
		buttonPanel.add(clearButton);

		outputPanel = new JPanel();
		outputLabel = new JLabel("");
		outputPanel.add(outputLabel);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(new JLabel(" "));
		mainPanel.add(titleLabel);
		mainPanel.add(new JLabel(" "));
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(namePanel);
		namePanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(phonePanel);
		phonePanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(emailPanel);
		emailPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(studIDPanel);
		studIDPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonPanel);
		buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(outputPanel);
		outputPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		add(mainPanel);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		
		ActionListener bl = new ButtonListener();
		submitButton.addActionListener(bl);
		clearButton.addActionListener(bl);
	}
	
	private void submit(){
		try{
			Student student = new Student(nameField.getText(), phoneField.getText(),
				emailField.getText(), Integer.parseInt(studIDField.getText()));
			if(!file.signedIn(student)){
				file.addStudent(student);
				valid();
				save();
			} else {
				outputLabel.setText(student.save().split(" ")[0] + " is already signed in!");
				outputLabel.setForeground(Color.RED);
			}
		} catch(IllegalArgumentException e){
			invalid(e);
		}
		clear(false);
	}
	
	private void clear(boolean all){
		nameField.setText("");
		phoneField.setText("");
		emailField.setText("");
		studIDField.setText("");
		if(all){
			outputLabel.setText("");
		}
	}
	
	private void save(){
		file.save();
		
	}
	
	private void valid(){
		outputLabel.setText("Student signed in! Welcome "+nameField.getText().split(" ")[0]);
		outputLabel.setForeground(Color.RED);
	}
	
	private void invalid(Exception e){
		outputLabel.setText(e.getMessage());
		outputLabel.setForeground(Color.RED);
	}
	
	
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == submitButton){
				submit();
			}
			if(e.getSource() == clearButton){
				clear(true);
			}
			
		}
		
	}

}
