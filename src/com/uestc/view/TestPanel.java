package com.uestc.view;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.uestc.controller.TestService;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.concurrent.TimeUnit;

public class TestPanel extends JPanel{
	private JProgressBar progressBar_2,progressBar,progressBar_1,progressBar_3,progressBar_4;
	
	private JTextField textField; //测试原图
	private JTextField textField_1;//测试结果（按概率排）
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;//2-6 显示文字结果
	private JTextField textField_7;//用于拖拽的（批量）
	private JTextField textField_8;//用于拖拽的（单张）
	private JButton button;//批量检测
	private JButton button_1 ;//单张测试
	private JButton button_2 ;//停止检测
	private JLabel label;//放置图片的
	private TestService testService;
	private ImageIcon icon = new ImageIcon(".\\timgRIFIQIXZ.jpg");
	private Image img = icon.getImage();

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

	}

	public TestPanel() {
		setLayout(null);
		testService =new TestService();
		label= new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.DARK_GRAY);
		label.setBackground(Color.DARK_GRAY);
		label.setBounds(39, 83, 359, 346);
		add(label);
		
		textField = new JTextField();
		textField.setText("测试原图");
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(154, 29, 100, 41);
		add(textField);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("");
		textField_1.setText("测试结果（按概率排）");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(Color.WHITE);
		textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(588, 64, 164, 35);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(499, 160, 167, 33);
		add(textField_2);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setMinimum(0);
		progressBar.setMaximum(10000);
		progressBar.setForeground(new Color(203, 38, 3));
		progressBar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		progressBar.setBorderPainted(true);
		progressBar.setBackground(Color.WHITE);
		progressBar.setBounds(666, 161, 167, 33);
		add(progressBar);
		
		progressBar_1 = new JProgressBar();
		progressBar_1.setStringPainted(true);
		progressBar_1.setMinimum(0);
		progressBar_1.setMaximum(10000);
		progressBar_1.setForeground(new Color(243, 46, 3));
		progressBar_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		progressBar_1.setBorderPainted(true);
		progressBar_1.setBackground(Color.WHITE);
		progressBar_1.setBounds(666, 194, 167, 33);
		add(progressBar_1);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(499, 193, 167, 33);
		add(textField_3);
		
		progressBar_2= new JProgressBar();
		progressBar_2.setStringPainted(true);
		progressBar_2.setMinimum(0);
		progressBar_2.setMaximum(10000);
		progressBar_2.setForeground(new Color(252, 77, 39));
		progressBar_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		progressBar_2.setBorderPainted(true);
		progressBar_2.setBackground(Color.WHITE);
		progressBar_2.setBounds(666, 227, 167, 33);
		add(progressBar_2);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(499, 226, 167, 33);
		add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(499, 258, 167, 33);
		add(textField_5);
		
		progressBar_3 = new JProgressBar();
		progressBar_3.setStringPainted(true);
		progressBar_3.setMinimum(0);
		progressBar_3.setMaximum(10000);
		progressBar_3.setForeground(new Color(253, 123, 96));
		progressBar_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		progressBar_3.setBorderPainted(true);
		progressBar_3.setBackground(Color.WHITE);
		progressBar_3.setBounds(666, 258, 167, 33);
		add(progressBar_3);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(499, 290, 167, 33);
		add(textField_6);
		
		progressBar_4 = new JProgressBar();
		progressBar_4.setStringPainted(true);
		progressBar_4.setMinimum(0);
		progressBar_4.setMaximum(10000);
		progressBar_4.setForeground(new Color(253, 128, 125));
		progressBar_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		progressBar_4.setBorderPainted(true);
		progressBar_4.setBackground(Color.WHITE);
		progressBar_4.setBounds(666, 290, 167, 33);
		add(progressBar_4);
		
		textField_7 = new JTextField(30);
		testService.drag(textField_7,this);
		textField_7.setText("将待测图片文件夹拖拽于此");
		textField_7.setForeground(Color.LIGHT_GRAY);
		textField_7.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_7.setBounds(439, 396, 200, 40);
		add(textField_7);
		
		button= new JButton("批量检测");
		button.setToolTipText("\u8BF7\u5C06\u56FE\u7247\u6587\u4EF6\u5939\uFF0C\u6216\u8005\u56FE\u7247\u62D6\u81F3\u5DE6\u6846\u540E\u70B9\u51FB\u3002");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button.setBackground(new Color(245, 245, 245));
		button.setBounds(666, 395, 86, 40);
		add(button);
		
		button_1 = new JButton("单张测试");button_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_1.setBackground(new Color(245, 245, 245));
		button_1.setBounds(134, 412, 120, 40);
		add(button_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(65, 105, 225));
		panel.setBounds(498, 157, 335, 166);
		add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		textField_8 = new JTextField();
		testService.drag(textField_8, this);
		textField_8.setOpaque(false);
		textField_8.setColumns(10);
		textField_8.setBounds(37, 82, 378, 316);
		add(textField_8);
		
		button_2 = new JButton("停止检测");
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button_2.setBackground(new Color(245, 245, 245));
		button_2.setBounds(756, 395, 92, 40);
		add(button_2);
		register(textField_8,button,button_1,button_2);
	}
	/**
	 * 为组件注册监听
	 * @param textField_8
	 * @param button
	 * @param button_1
	 * @param button_2
	 */
	private void register(JTextField textField_8,JButton button,JButton button_1,JButton button_2){
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("fsfsf");
				if (label.getIcon() == null) {
					JOptionPane.showMessageDialog(TestPanel.this, "请拖拽图片");
					return;
				}

				textField_7.setText("");
				String testName = JOptionPane.showInputDialog(null, "请输入姓名：");
				System.out.println(testName+"***************************");
				if(testName!=null){
					testService.test(label.getIcon().toString(), testName,TestPanel.this);
					
				}
					

			}
		});
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// textField_7.getText().trim().equals("请将待测试图片文件夹拖拽于此") ||
				// "".equals(textField_7.getText().trim())
				if (textField_7.getText().trim().equals("请将待测试图片文件夹拖拽于此") || "".equals(textField_7.getText().trim())) {
					JOptionPane.showMessageDialog(TestPanel.this, "请拖拽文件夹");
					return;
				}
				String f = textField_7.getText().trim();
				String testName = JOptionPane.showInputDialog(null, "请输入姓名：");				
				String path = textField_7.getText().trim();
				if(testName!=null)
					testService.test(path, testName,TestPanel.this);

			}
		});
		button_2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TestService.exe.shutdownNow();
				TestService.schedule.interrupt();
				Thread t=new Thread(){
					@Override
					public void run(){
						boolean flag=true;
						while(flag){
							if(TestService.exe.isTerminated()){
								flag=false;
								progressBar.setValue((new Double(0 * 10000)).intValue());
								progressBar_1.setValue((new Double(0 * 10000)).intValue());
								progressBar_2.setValue((new Double(0 * 10000)).intValue());
								progressBar_3.setValue((new Double(0 * 10000)).intValue());
								progressBar_4.setValue((new Double(0 * 10000)).intValue());
								textField_7.setFont(new Font("微软雅黑", Font.PLAIN, 12));
								textField_7.setBackground(Color.white);
								textField_7.setText("请将待测试图片文件夹拖拽于此");
								label.setIcon(null);
								try {
									TimeUnit.MICROSECONDS.sleep(200);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}			
				};	
				t.start();
			}
			
		});
		textField_8.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if ("请将待测试图片或文件夹拖拽于此".equalsIgnoreCase(textField_8.getText())) {
					textField_8.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if ("".equals(textField_8.getText())) {
					textField_8.setText("请将待测试图片或文件夹拖拽于此");
				}
			}
		});

	}
	public JProgressBar getProgressBar_2() {
		return progressBar_2;
	}

	public void setProgressBar_2(JProgressBar progressBar_2) {
		this.progressBar_2 = progressBar_2;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public JProgressBar getProgressBar_1() {
		return progressBar_1;
	}

	public void setProgressBar_1(JProgressBar progressBar_1) {
		this.progressBar_1 = progressBar_1;
	}

	public JProgressBar getProgressBar_3() {
		return progressBar_3;
	}

	public void setProgressBar_3(JProgressBar progressBar_3) {
		this.progressBar_3 = progressBar_3;
	}

	public JProgressBar getProgressBar_4() {
		return progressBar_4;
	}

	public void setProgressBar_4(JProgressBar progressBar_4) {
		this.progressBar_4 = progressBar_4;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public void setTextField_1(JTextField textField_1) {
		this.textField_1 = textField_1;
	}

	public JTextField getTextField_2() {
		return textField_2;
	}

	public void setTextField_2(JTextField textField_2) {
		this.textField_2 = textField_2;
	}

	public JTextField getTextField_3() {
		return textField_3;
	}

	public void setTextField_3(JTextField textField_3) {
		this.textField_3 = textField_3;
	}

	public JTextField getTextField_4() {
		return textField_4;
	}

	public void setTextField_4(JTextField textField_4) {
		this.textField_4 = textField_4;
	}

	public JTextField getTextField_5() {
		return textField_5;
	}

	public void setTextField_5(JTextField textField_5) {
		this.textField_5 = textField_5;
	}

	public JTextField getTextField_6() {
		return textField_6;
	}

	public void setTextField_6(JTextField textField_6) {
		this.textField_6 = textField_6;
	}

	public JTextField getTextField_7() {
		return textField_7;
	}

	public void setTextField_7(JTextField textField_7) {
		this.textField_7 = textField_7;
	}

	public JTextField getTextField_8() {
		return textField_8;
	}

	public void setTextField_8(JTextField textField_8) {
		this.textField_8 = textField_8;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public JButton getButton_1() {
		return button_1;
	}

	public void setButton_1(JButton button_1) {
		this.button_1 = button_1;
	}

	public JButton getButton_2() {
		return button_2;
	}

	public void setButton_2(JButton button_2) {
		this.button_2 = button_2;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
}
