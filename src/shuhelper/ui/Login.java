package shuhelper.ui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login {
	//窗口和面板
	private JFrame LoginFrame;
	private JPanel panel;
	//账号密码数据
	private String username;
	private String password;
	//账号密码文本框
	private JTextField UserText;
	private JPasswordField  PassWordText;
	//按钮
	private JButton LoginButton;
	private JButton ResetButton;
	
	//获取文本信息
	private void GetText()
	{
		username = UserText.getText();
		password = new String(PassWordText.getPassword());
		System.out.println(username);
		System.out.println(password);
	}
	
	//按钮监听
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
	
	//创建登录窗口
	private void LoginFrame()
	{
		// 确保一个漂亮的外观风格
		JFrame.setDefaultLookAndFeelDecorated(true);

		// 创建窗口
		LoginFrame = new JFrame("SHU-Helper Login");
		LoginFrame.setLocationRelativeTo(null);
		LoginFrame.setSize(435 , 352);
		LoginFrame.setResizable(false);
		LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//创建面板
		panel = new JPanel();
		LoginFrame.add(panel);
		//布局
		panel.setLayout(null);
		
		//创建标签
		JLabel UserLabel = new JLabel("学号:");
		UserLabel.setBounds(100,150,80,25);
		UserLabel.setFont(new java.awt.Font("Dialog", 1, 25));  
		panel.add(UserLabel);
		JLabel PassWord = new JLabel("密码：");
		PassWord.setBounds(100,200,80,25);
		PassWord.setFont(new java.awt.Font("Dialog", 1, 25));
		panel.add(PassWord, PassWord);
		
		//创建文本域
		UserText = new JTextField(10);
		UserText.setBounds(170,150,165,25);
        panel.add(UserText);
        
        
        PassWordText = new JPasswordField (20);
        PassWordText.setBounds(170,200,165,25);
        panel.add(PassWordText);
        
        //按钮
        LoginButton = new JButton("登录");
        LoginButton.addActionListener(new LoginBottonHandler());
        LoginButton.setBounds(100,250,80,25);
        panel.add(LoginButton);        
        
        ResetButton = new JButton("重置");
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
