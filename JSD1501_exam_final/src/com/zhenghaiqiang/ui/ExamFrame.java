package com.zhenghaiqiang.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.zhenghaiqiang.dao.QuestionDao;
import com.zhenghaiqiang.dao.UserDao;
import com.zhenghaiqiang.dao.impl.QuestionDaoImpl;
import com.zhenghaiqiang.dao.impl.UserDaoImpl;
import com.zhenghaiqiang.entity.Question;
import com.zhenghaiqiang.entity.User;

public class ExamFrame extends JFrame{
	JLabel showlabel;
	int times = 600;
	private int i = 0;
	int ii = 0;
	JTextArea area;
	JLabel djslabel2;
	private User user;
	int totalscore = 0;
	JButton btn2;
	JCheckBox a; 
	JCheckBox b;
	JCheckBox c;
	JCheckBox d;
	private int[] count = new int[20];//���ڴ�����ɵ�20�������
	private int[] arranswer = new int[20];//���20���𰸴�
	private QuestionDao dao = new QuestionDaoImpl();//�����������20����Ŀ
	List<Question> questions = new ArrayList<Question>();
	public ExamFrame() {
		init();
	}
	public ExamFrame(User user) {
		
		//�������20����Ŀ
		Random r = new Random();
		for (int i = 0; i < count.length; i++) {
			count[i] = r.nextInt(58)+1;
		}
		this.getQuestion();
		this.user = user;
		init();
		//��ʱ��
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				times--;
				djslabel2.setText("ʣ��ʱ��: "+times+"��");
			}
		},0, 1000);
		
	}
	//ͨ����ţ��ó�20����Ŀ����ӵ�������
	public void getQuestion() {
		questions = new ArrayList<Question>();
		for (int i = 0; i < count.length; i++) {
			Question q = dao.getQuestionById(count[i]);
			questions.add(q);
		}
		//System.out.println("size"+questions.size());
	}

	private void init() {
		this.setTitle("���߿���ϵͳ");
		this.setLocationRelativeTo(this);//
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 400);
		this.setContentPane(createContentPane());
	}

	private JPanel createContentPane() {
		JPanel panel = new JPanel(new BorderLayout());
		ImageIcon icon = new ImageIcon(this.getClass().getResource("exam_title.png"));
		JLabel label = new JLabel(icon);
		panel.add(label,BorderLayout.NORTH);
		panel.add(createCenterPane(),BorderLayout.CENTER);
		panel.add(createBottomPane(),BorderLayout.SOUTH);
		return panel;
	}

	private JPanel createBottomPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(8, 8, 8, 8));
		showlabel = new JLabel("���:"+20+"��ĵ�"+(i+1)+" ��");
		 djslabel2 = new JLabel("ʣ��ʱ��: "+times+"��");
		panel.add(showlabel,BorderLayout.WEST);
		panel.add(djslabel2,BorderLayout.EAST);
		panel.add(createBtnPane(),BorderLayout.CENTER);
		return panel;
	}

	private Component createBtnPane() {
		JPanel panel = new JPanel();
		final JButton btn1 = new JButton("<<��һ��");
		
		//�����һ������ť�ͱ�ҡ�
		if(i==0) {
			btn1.setEnabled(false);
		}
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
					btn2.setEnabled(true);
					i--;
					
					if(arranswer[i] == 0) {
						a.setSelected(true);
						b.setSelected(false);
						c.setSelected(false);
						d.setSelected(false);
					}
					if(arranswer[i] == 1) {
						b.setSelected(true);
						a.setSelected(false);
						c.setSelected(false);
						d.setSelected(false);
						
					}
					if(arranswer[i] == 2) {
						c.setSelected(true);
						a.setSelected(false);
						b.setSelected(false);
						d.setSelected(false);
					}
					if(arranswer[i] == 3) {
						d.setSelected(true);
						a.setSelected(false);
						b.setSelected(false);
						c.setSelected(false);
					}
					
					//System.out.println("��һ��i="+i);
					area.setText(questions.get(i).getTitle());
					if(i<=0) {
						btn1.setEnabled(false);
					}
					showlabel.setText("���:"+20+"��ĵ�"+(i+1)+" ��");
					//System.out.println(Arrays.toString(arranswer));
				
				
			}
		});
		btn2 = new JButton("��һ��>>");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!a.isSelected()&&!b.isSelected()&&!c.isSelected()&&!d.isSelected()) {
					showBtn();
					return;
				}
				
				//��������һ������һ����ť�ͱ�ɿɵ��
					btn1.setEnabled(true);
					if(a.isSelected()) {
						arranswer[i] = 0;
					}
					if(b.isSelected()) {
						arranswer[i] = 1;
					}
					if(c.isSelected()) {
						arranswer[i] = 2;
					}
					if(d.isSelected()) {
						arranswer[i] = 3;
					}
					
					i++;
					//System.out.println("��һ��i="+i);
					area.setText(questions.get(i).getTitle());
					showlabel.setText("���:"+20+"��ĵ�"+(i+1)+" ��");
					if(i>=19) {
						btn2.setEnabled(false);
					} 
					a.setSelected(false);
					b.setSelected(false);
					c.setSelected(false);
					d.setSelected(false);
				
			}

			
		});
		JButton btn3 = new JButton("�ύ");
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//i=arranswer.length;
				commit();
			}

			
		});
		panel.add(btn1);
		panel.add(btn2);
		panel.add(btn3);
		return panel;
	}

	private void showBtn() {
		JOptionPane.showMessageDialog(this, "����ѡһ��");
		
	}
	
	private Component createCenterPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(8, 8, 8, 8));
		String name = user.getName();
		int bh = user.getId();
		String txtlabel = "����:"+name+" ���:"+bh+" ����ʱ��:10���� "+"���Կ�Ŀ:JAVASE"+"��Ŀ����:"+20;
		JLabel label = new JLabel(txtlabel,JLabel.CENTER);
		area = new JTextArea();
		area.setText(questions.get(i).getTitle());
		area.setEditable(false);
		panel.add(label,BorderLayout.NORTH);
		panel.add(area,BorderLayout.CENTER);
		panel.add(createCheck(),BorderLayout.SOUTH);
		return panel;
	}

	private JPanel createCheck() {
		JPanel panel = new JPanel();
		a = new JCheckBox("A");
		b = new JCheckBox("B");
		c = new JCheckBox("C");
		d = new JCheckBox("D");
		panel.add(a);
		panel.add(b);
		panel.add(c);
		panel.add(d);
		return panel;
	}
	
	//����
	private void commit() {
		if(!a.isSelected()&&!b.isSelected()&&!c.isSelected()&&!d.isSelected()) {
			showBtn();
			return;
		}
		if(a.isSelected()) {
			arranswer[i] = 0;
		}
		if(b.isSelected()) {
			arranswer[i] = 1;
		}
		if(c.isSelected()) {
			arranswer[i] = 2;
		}
		if(d.isSelected()) {
			arranswer[i] = 3;
		}
			
		//����ɼ������ǵ�ѡ�����ȷ����Ƚϣ��Եļӷ֣���Ĳ��ӷ֡�
		for (int i = 0; i < questions.size(); i++) {
			Question question = questions.get(i);
			if(Integer.parseInt(question.getAnswer())==arranswer[i]) {
				totalscore = totalscore + 5;
			}
		}
		//�ύ������������Ϊ�ѿ���
		UserDao dao = new UserDaoImpl();
		user.setGrade(totalscore);
		user.setStart(true);
		JOptionPane.showMessageDialog(this, "�÷�:"+totalscore+"��");
		dao.updateUser(user);
		this.dispose();//�رյ�ǰҳ��
	}
	
}
