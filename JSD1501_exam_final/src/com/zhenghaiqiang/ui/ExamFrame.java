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
	private int[] count = new int[20];//用于存放生成的20个随机数
	private int[] arranswer = new int[20];//存放20个答案答案
	private QuestionDao dao = new QuestionDaoImpl();//存放随机抽出的20个题目
	List<Question> questions = new ArrayList<Question>();
	public ExamFrame() {
		init();
	}
	public ExamFrame(User user) {
		
		//随机生成20个题目
		Random r = new Random();
		for (int i = 0; i < count.length; i++) {
			count[i] = r.nextInt(58)+1;
		}
		this.getQuestion();
		this.user = user;
		init();
		//计时器
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				times--;
				djslabel2.setText("剩余时间: "+times+"秒");
			}
		},0, 1000);
		
	}
	//通过题号，拿出20个题目，添加到集合中
	public void getQuestion() {
		questions = new ArrayList<Question>();
		for (int i = 0; i < count.length; i++) {
			Question q = dao.getQuestionById(count[i]);
			questions.add(q);
		}
		//System.out.println("size"+questions.size());
	}

	private void init() {
		this.setTitle("在线考试系统");
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
		showlabel = new JLabel("题号:"+20+"题的第"+(i+1)+" 题");
		 djslabel2 = new JLabel("剩余时间: "+times+"秒");
		panel.add(showlabel,BorderLayout.WEST);
		panel.add(djslabel2,BorderLayout.EAST);
		panel.add(createBtnPane(),BorderLayout.CENTER);
		return panel;
	}

	private Component createBtnPane() {
		JPanel panel = new JPanel();
		final JButton btn1 = new JButton("<<上一个");
		
		//如果第一个，按钮就变灰。
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
					
					//System.out.println("上一个i="+i);
					area.setText(questions.get(i).getTitle());
					if(i<=0) {
						btn1.setEnabled(false);
					}
					showlabel.setText("题号:"+20+"题的第"+(i+1)+" 题");
					//System.out.println(Arrays.toString(arranswer));
				
				
			}
		});
		btn2 = new JButton("下一个>>");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!a.isSelected()&&!b.isSelected()&&!c.isSelected()&&!d.isSelected()) {
					showBtn();
					return;
				}
				
				//如果点击下一步，上一个按钮就变成可点击
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
					//System.out.println("下一个i="+i);
					area.setText(questions.get(i).getTitle());
					showlabel.setText("题号:"+20+"题的第"+(i+1)+" 题");
					if(i>=19) {
						btn2.setEnabled(false);
					} 
					a.setSelected(false);
					b.setSelected(false);
					c.setSelected(false);
					d.setSelected(false);
				
			}

			
		});
		JButton btn3 = new JButton("提交");
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
		JOptionPane.showMessageDialog(this, "必须选一个");
		
	}
	
	private Component createCenterPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(8, 8, 8, 8));
		String name = user.getName();
		int bh = user.getId();
		String txtlabel = "姓名:"+name+" 编号:"+bh+" 考试时间:10分钟 "+"考试科目:JAVASE"+"题目数量:"+20;
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
	
	//交卷
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
			
		//计算成绩，我们的选项和正确答案相比较，对的加分，错的不加分。
		for (int i = 0; i < questions.size(); i++) {
			Question question = questions.get(i);
			if(Integer.parseInt(question.getAnswer())==arranswer[i]) {
				totalscore = totalscore + 5;
			}
		}
		//提交分数并且设置为已考过
		UserDao dao = new UserDaoImpl();
		user.setGrade(totalscore);
		user.setStart(true);
		JOptionPane.showMessageDialog(this, "得分:"+totalscore+"分");
		dao.updateUser(user);
		this.dispose();//关闭当前页面
	}
	
}
