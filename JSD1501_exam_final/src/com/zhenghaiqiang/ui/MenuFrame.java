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
		this.setTitle("���߲���");
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
		JLabel label2 = new JLabel("��Ȩ���У�����ؾ�  ",JLabel.RIGHT);
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
		
		JLabel userlabel = new JLabel(str+",��ӭ��¼���߿���ϵͳ",JLabel.CENTER);
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
		btn1 = new JButton("��ʼ",icon1);
		 btn1.setIconTextGap(2);  //�ֺ�ͼ��ļ��
		 btn1.setHorizontalTextPosition(JButton.CENTER); //����ͼƬ�е�ˮƽλ��
		 btn1.setVerticalTextPosition(JButton.BOTTOM);  //����ͼƬ�еĴ�ֱλ��
		 btn1.addActionListener(this);
		panel.add(btn1);
		
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("result.png"));
		btn2 = new JButton("���Գɼ�",icon2);
		 btn2.setIconTextGap(2);  
		 btn2.setHorizontalTextPosition(JButton.CENTER); 
		 btn2.setVerticalTextPosition(JButton.BOTTOM);  
		 btn2.addActionListener(this);
		panel.add(btn2);
		
		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("message.png"));
		btn3 = new JButton("���Թ���",icon3);
		 btn3.setIconTextGap(2);  
		 btn3.setHorizontalTextPosition(JButton.CENTER); 
		 btn3.setVerticalTextPosition(JButton.BOTTOM);  
		 btn3.addActionListener(this);
		panel.add(btn3);
		
		ImageIcon icon4 = new ImageIcon(this.getClass().getResource("exit.png"));
		btn4 = new JButton("�뿪",icon4);
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
				JOptionPane.showMessageDialog(this, "���Ѿ������ˣ������ٴο�����");
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
			JOptionPane.showMessageDialog(this, "�㿼��"+u.getGrade()+"��");
			
		}
		
		else if(e.getSource()==btn3){
			JOptionPane.showMessageDialog(this,"   Υ����Ϊ���϶��봦�������  ���������ؿ������ɣ������ӿ��Թ�����Ա�İ�����Ҫ����������Ϊ֮һ�ģ�Ӧ���϶�Ϊ����Υ�ͣ�\n" +
					"��һ��Я���뿼���޹ص���Ʒ���뿼������δ����ָ��λ�õģ�"+"\n������δ�ڹ涨�Ŀ��㡢��������λ�μӿ��Եģ�"+
					"\n���������Կ�ʼ�źŷ���ǰ������߿��Խ����źŷ������������ģ�"+
					"\n���ģ��ڿ��Թ������Կ�����ͷ�Ӷ������򰵺Ż������Ƶģ�"+
					"\n���壩�ڿ������߽������Ի�����ֹ�ķ�Χ�ڣ����������̻���ʵʩ����Ӱ�쿼���������Ϊ�ģ�"+
					"\n������δ�����Թ�����Աͬ���ڿ��Թ����������뿪�����ģ�"+
					"\n���ߣ����Ծ���������⿨������ֽ�ȣ���ͬ�����ݸ�ֽ�ȿ�����ֽ���������ģ�"+
					"\n���ˣ��ù涨����ıʻ���ֽ����������Ծ�涨����ĵط���д���������Ż�����������ʽ�ڴ���ϱ����Ϣ�ģ�"+
					"\n���ţ�����Υ������������δ�������׵���Ϊ��");
		} else if(e.getSource()==btn4) {
			int value = JOptionPane.showConfirmDialog(this, "ȷ���˳���");
			if(value==0) {
				System.exit(0);
			}
		}
	}
}
