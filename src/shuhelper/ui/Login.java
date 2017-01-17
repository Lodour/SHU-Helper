package shuhelper.ui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login {
	//���ں����
	private JFrame LoginFrame;
	private JPanel panel;
	//�˺���������
	private String username;
	private String password;
	//�˺������ı���
	private JTextField UserText;
	private JPasswordField  PassWordText;
	//��ť
	private JButton LoginButton;
	private JButton ResetButton;
	
	//��ȡ�ı���Ϣ
	private void GetText()
	{
		username = UserText.getText();
		password = new String(PassWordText.getPassword());
		System.out.println(username);
		System.out.println(password);
	}
	
	//��ť����
	private class LoginBottonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			GetText();
			System.out.println(username);
			System.out.println(password);
		}
	}
	
	private class ResetButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			UserText.setText("");
			PassWordText.setText("");
		}
	}
	
	//������¼����
	private void LoginFrame()
	{
		// ȷ��һ��Ư������۷��
		JFrame.setDefaultLookAndFeelDecorated(true);

		// ��������
		LoginFrame = new JFrame("SHU-Helper Login");
		LoginFrame.setLocationRelativeTo(null);
		LoginFrame.setSize(435 , 352);
		LoginFrame.setResizable(false);
		LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//�������
		panel = new JPanel();
		LoginFrame.add(panel);
		//����
		panel.setLayout(null);
		
		//������ǩ
		JLabel UserLabel = new JLabel("ѧ��:");
		UserLabel.setBounds(100,150,80,25);
		UserLabel.setFont(new java.awt.Font("Dialog", 1, 25));  
		panel.add(UserLabel);
		JLabel PassWord = new JLabel("���룺");
		PassWord.setBounds(100,200,80,25);
		PassWord.setFont(new java.awt.Font("Dialog", 1, 25));
		panel.add(PassWord, PassWord);
		
		//�����ı���
		UserText = new JTextField(10);
		UserText.setBounds(170,150,165,25);
        panel.add(UserText);
        
        
        PassWordText = new JPasswordField (20);
        PassWordText.setBounds(170,200,165,25);
        panel.add(PassWordText);
        
        //��ť
        LoginButton = new JButton("��¼");
        LoginButton.addActionListener(new LoginBottonHandler());
        LoginButton.setBounds(100,250,80,25);
        panel.add(LoginButton);        
        
        ResetButton = new JButton("����");
        ResetButton.addActionListener(new ResetButtonHandler());
        ResetButton.setBounds(250,250,80,25);
        panel.add(ResetButton);
        
		LoginFrame.setVisible(true);
	}
	


	public static void main(String[] args)
	{
		Login login = new Login();
		login.LoginFrame();	
	}

}
