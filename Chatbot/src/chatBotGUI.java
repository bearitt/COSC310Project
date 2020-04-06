
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class chatBotGUI implements ActionListener{
	
	int count = 0;
	JLabel labelMessage;
	JLabel labelChat;
	JButton button;
	JPanel panel;
	JTextField userText;
	String chatLog;
	int counter = 0;
	
	public chatBotGUI() {
		
		chatLog = "Enter your question (type \"Help\" for more information): ";
		chatLog += "\n(Warning: the response to your first question will take a few seconds"
				+ " to load. Please be patient!)";
		panel = new JPanel();
		
		JFrame frame = new JFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		frame.setTitle("ChatBot GUI");
		
		button = new JButton("Send");
		button.setBounds(500, 500, 80 , 25);
		button.addActionListener(this);
		panel.add(button);
		
		labelMessage = new JLabel("Message:");
		labelMessage.setBounds(20, 500,80,25);
		panel.add(labelMessage);
		userText = new JTextField(20);
		userText.setBounds(100, 500, 250, 25);
		
		labelChat = new JLabel("");
		labelChat.setBounds(30,20,470,480);
		labelChat.setVerticalAlignment(JLabel.TOP);
		labelChat.setHorizontalAlignment(JLabel.LEFT);
		labelChat.setText(convertToMultiline(chatLog));
		
		JLabel label1 = new JLabel("Chat:");
		label1.setBounds(30,5,50,20);
		panel.add(label1);
		panel.add(labelChat);
		
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(null);
		panel.add(userText);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new chatBotGUI();
	}

	public static String convertToMultiline(String orig)
	{
	    return "<html>" + orig.replaceAll("\n", "<br>");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Bot chatbot = new Bot();
//		if(counter==0) {
//			chatLog += "Enter your question (type \"Help\" for more information): ";
//			labelChat.setText(convertToMultiline(chatLog));
//		}
		counter++;
		String userInput = userText.getText();
		chatLog = chatLog+"\nYOU: "+userInput;
		if(counter>=9) 
			chatLog = chatLog.substring(chatLog.indexOf("\n",1));
		userInput = userInput.toLowerCase();
		String botOutput = chatbot.receiveQuery(userInput);
		chatLog = chatLog+"\nChatBot: "+botOutput;
		if(counter>=9) 
			chatLog = chatLog.substring(chatLog.indexOf("\n",1));
		labelChat.setText(convertToMultiline(chatLog));
	}
	
	private void delayResponse() {
		//String[] spinner = new String[] { "\u0008\u0008\u0008.  ", "\u0008\u0008\u0008.. ", "\u0008\u0008\u0008..." };
		String[] spinner = new String[] { "\b\b\b.  ", "\b\b\b.. ", "\b\b\b..." };
		System.out.printf("Chatbot: ");
		System.out.printf(".  ");
		try {
			for (int i = 0; i < 10; i++) {
				Thread.sleep(150);
				System.out.printf("%s", spinner[i % spinner.length]);
			}
			System.out.printf("\b\b\b");
		} catch (InterruptedException e) {
			System.out.printf("Something went wrong\n");
		}
	}

}
