package com.zhenghaiqiang.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.zhenghaiqiang.dao.UserDao;
import com.zhenghaiqiang.dao.impl.UserDaoImpl;
import com.zhenghaiqiang.entity.User;

public class MenuFrame extends JFrame implements ActionListener {
	private User user;
	JButton btn1;
	JButton btn2;
	JButton btn3;
	JButton btn4;
	public MenuFrame() {
		init();
	}
	public MenuFrame(User u) {
		this.user = u;
		init();
	}

	private void init() {
		this.setTitle("在线测评");
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(createContentPane());
	}

	private JPanel createContentPane() {
		JPanel panel = new JPanel(new BorderLayout());
		ImageIcon icon = new ImageIcon(this.getClass().getResource("title.png"));
		JLabel label = new JLabel(icon);
		panel.add(label,BorderLayout.NORTH);
		JLabel label2 = new JLabel("版权所有，盗版必究  ",JLabel.RIGHT);
		panel.add(label2,BorderLayout.SOUTH);
		panel.add(createCenterPane(),BorderLayout.CENTER);
		
		return panel ;
	}

	private Component createCenterPane() {
		JPanel panel = new JPanel(new BorderLayout());
		String str ;
		if(user!=null) {
			str = user.getName();
		} else {
			str = "";
		}
		
		JLabel userlabel = new JLabel(str+",欢迎登录在线考试系统",JLabel.CENTER);
		panel.add(userlabel,BorderLayout.NORTH);
		panel.add(createBtnPane(), BorderLayout.CENTER);
		return panel;
	}
	
	
	/* JButton button = new JButton(text, ico);  
     button.setIconTextGap(2);  
     button.setHorizontalTextPosition(JButton.CENTER); 
     button.setVerticalTextPosition(JButton.BOTTOM);  
*/
	private Component createBtnPane() {
		JPanel panel = new JPanel(new FlowLayout());
		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("exam.png"));
		btn1 = new JButton("开始",icon1);
		 btn1.setIconTextGap(2);  //字和图标的间距
		 btn1.setHorizontalTextPosition(JButton.CENTER); //字在图片中的水平位置
		 btn1.setVerticalTextPosition(JButton.BOTTOM);  //字在图片中的垂直位置
		 btn1.addActionListener(this);
		panel.add(btn1);
		
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("result.png"));
		btn2 = new JButton("考试成绩",icon2);
		 btn2.setIconTextGap(2);  
		 btn2.setHorizontalTextPosition(JButton.CENTER); 
		 btn2.setVerticalTextPosition(JButton.BOTTOM);  
		 btn2.addActionListener(this);
		panel.add(btn2);
		
		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("message.png"));
		btn3 = new JButton("考试规则",icon3);
		 btn3.setIconTextGap(2);  
		 btn3.setHorizontalTextPosition(JButton.CENTER); 
		 btn3.setVerticalTextPosition(JButton.BOTTOM);  
		 btn3.addActionListener(this);
		panel.add(btn3);
		
		ImageIcon icon4 = new ImageIcon(this.getClass().getResource("exit.png"));
		btn4 = new JButton("离开",icon4);
		 btn4.setIconTextGap(2);  
		 btn4.setHorizontalTextPosition(JButton.CENTER); 
		 btn4.setVerticalTextPosition(JButton.BOTTOM);  
		 btn4.addActionListener(this);
		panel.add(btn4);
		return panel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn1) {
			if(user.isStart())  {
				JOptionPane.showMessageDialog(this, "你已经考过了，不能再次考试了");
			} else {
				ExamFrame examFrame = new ExamFrame(user);
				examFrame.setVisible(true);
			/*	btn1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});*/
			}
		}else if(e.getSource()==btn2) {
			UserDao dao = new UserDaoImpl();
			User u = dao.getUserById(user.getId());
			JOptionPane.showMessageDialog(this, "你考了"+u.getGrade()+"分");
			
		}
		
		else if(e.getSource()==btn3){
			JOptionPane.showMessageDialog(this,"   违规行为的认定与处理第五条  考生不遵守考场纪律，不服从考试工作人员的安排与要求，有下列行为之一的，应当认定为考试违纪：\n" +
					"（一）携带与考试无关的物品进入考场或者未放在指定位置的；"+"\n（二）未在规定的考点、考场和座位参加考试的；"+
					"\n（三）考试开始信号发出前答题或者考试结束信号发出后继续答题的；"+
					"\n（四）在考试过程中旁窥、交头接耳、互打暗号或者手势的；"+
					"\n（五）在考场或者教育考试机构禁止的范围内，喧哗、吸烟或者实施其他影响考场秩序的行为的；"+
					"\n（六）未经考试工作人员同意在考试过程中擅自离开考场的；"+
					"\n（七）将试卷、答卷（含答题卡、答题纸等，下同）、草稿纸等考试用纸带出考场的；"+
					"\n（八）用规定以外的笔或者纸答题或者在试卷规定以外的地方书写姓名、考号或者以其他方式在答卷上标记信息的；"+
					"\n（九）其他违反考场规则但尚未构成作弊的行为。");
		} else if(e.getSource()==btn4) {
			int value = JOptionPane.showConfirmDialog(this, "确定退出吗");
			if(value==0) {
				System.exit(0);
			}
		}
	}
}
