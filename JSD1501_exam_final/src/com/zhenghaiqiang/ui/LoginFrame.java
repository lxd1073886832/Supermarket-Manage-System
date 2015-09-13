package com.zhenghaiqiang.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.zhenghaiqiang.dao.UserDao;
import com.zhenghaiqiang.dao.impl.UserDaoImpl;
import com.zhenghaiqiang.entity.User;

public class LoginFrame extends JFrame implements ActionListener {
	private JTextField idtext;
	private JPasswordField pwdtext;
	private JLabel errorlabel;
	private JButton cancel;
	private User user;
	public LoginFrame() {
		init();
	}

	private void init() {
		this.setTitle("登录");
		this.setSize(260,200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(createContentPan());
	}

	private JPanel createContentPan() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));//设置边距
		JLabel label = new JLabel("登录系统",JLabel.CENTER);
		panel.add(label,BorderLayout.NORTH);
		panel.add(createCenterPane(),BorderLayout.CENTER);
		panel.add(createButtonPane(),BorderLayout.SOUTH);
		return panel;
	}

	private JPanel createButtonPane() {
		JPanel panel = new JPanel();
		JButton login = new JButton("登录");
		login.addActionListener(this);
	    cancel = new JButton("取消");
	    cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(login);
		panel.add(cancel);
		return panel;
	}

	private JPanel createCenterPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(8, 8, 8, 8));//设置边框
		panel.add(createIdAndPasswordPane(),BorderLayout.NORTH);
		errorlabel = new JLabel("",JLabel.CENTER);
		panel.add(errorlabel,BorderLayout.CENTER);
		return panel;
	}

	private JPanel createIdAndPasswordPane() {
		JPanel panel = new JPanel(new GridLayout(2,1,0,6));
		panel.add(createIdPane()); 
		panel.add(createPwdPane());
		return panel;
	}

	private JPanel createPwdPane() {
		JPanel panel = new JPanel(new BorderLayout(6,0));
		JLabel label = new JLabel("密码:");
		pwdtext = new JPasswordField();
		panel.add(label,BorderLayout.WEST);
		panel.add(pwdtext, BorderLayout.CENTER);
		return panel;
	}

	private JPanel createIdPane() {
		JPanel panel = new JPanel(new BorderLayout(6,0));
		JLabel label = new JLabel("编号:");
		idtext = new JTextField();
		panel.add(label,BorderLayout.WEST);
		panel.add(idtext, BorderLayout.CENTER);
		return panel;
	}
	public void bukejian() {
		this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UserDao userDao = new UserDaoImpl();
		String id = idtext.getText();
		
		char[] password = pwdtext.getPassword();
		try {
			user = userDao.getUserById( Integer.parseInt(id));
		}catch(NumberFormatException e1)  {
			errorlabel.setText("编号不合法,请重新输入");
			return;
		}		
		
		if(user==null) {
			errorlabel.setText("用户不存在,请重新输入");
		} else {
			if(String.valueOf(password).equals(user.getPassword())) {
				this.setVisible(false);
				MenuFrame frame = new MenuFrame(user);
				frame.setVisible(true);
			} else {
				errorlabel.setText("用户名或密码不正确，请重新输入");
			}
		}
	}
}
