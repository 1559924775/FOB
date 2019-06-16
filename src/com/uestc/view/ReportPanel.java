package com.uestc.view;
//package te;

import javax.swing.JPanel;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ReportPanel extends JPanel{
	ImageIcon icon = new ImageIcon(".\\timgRIFIQIXZ.jpg");
	Image img = icon.getImage();

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

	}
	public ReportPanel(String name,String time,String result,String p,String path,String imageName) {
		setLayout(null);
		Font f=new Font("宋体",Font.BOLD,20);
		Font f2=new Font("宋体",Font.PLAIN,18);
		JLabel lblNewLabel = new JLabel();//"T原图"
		lblNewLabel.setBounds(454, 115, 344, 291);
		add(lblNewLabel);
		ImageIcon icon=new ImageIcon(path);
		lblNewLabel.setIcon(icon);
		lblNewLabel.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frame1 = new JFrame("显示原图");
				// 设置在屏幕的位置
				frame1.setLocation(50, 0);
				
				ImageIcon image = new ImageIcon(".\\src\\app.png");
				frame1.setIconImage(image.getImage());
				// 窗体大小
				frame1.setSize(1280, 960);
				// 显示窗体
				frame1.setVisible(true);
				JPanel jp = new JPanel(new BorderLayout());
				frame1.getContentPane().add(jp);
				JLabel jb = new JLabel();
				jb.setBounds(0, 0, 1280, 960);
				jp.add(jb);
	
				ImageIcon image2 = new ImageIcon(lblNewLabel.getIcon().toString());
	
				System.out.println(lblNewLabel.getIcon().toString());
				//放大图片
				image2.setImage(image2.getImage().getScaledInstance(1280, 960, Image.SCALE_DEFAULT));
	
				jb.setIcon(image2);
			}
	
		});
		
		JLabel label = new JLabel("测试原图");
		label.setBounds(518, 84, 227, 18);
		label.setFont(f);
		add(label);
		
		JLabel label_1 = new JLabel("测试报告");
		label_1.setBounds(379, 13, 139, 35);
		label_1.setBackground(Color.blue);
		add(label_1);
		label_1.setFont(new Font("宋体",Font.BOLD,30));
		
		JLabel label_2 = new JLabel("测试人");
		label_2.setFont(f);
		label_2.setBounds(33, 115, 72, 18);
		add(label_2);
		
		JLabel label_3 = new JLabel(name);
		label_3.setBounds(201, 117, 72, 18);
		label_3.setFont(f2);
		add(label_3);
		
		JLabel label_4 = new JLabel("测试时间");
		label_4.setBounds(33, 172, 133, 18);
		label_4.setFont(f);
		add(label_4);
		
		JLabel label_5 = new JLabel(time);
		label_5.setBounds(203, 174, 209, 18);
		label_5.setFont(f2);
		add(label_5);
		
		JLabel label_6 = new JLabel("测试结果");
		label_6.setBounds(33, 230, 139, 18);
		label_6.setFont(f);
		add(label_6);
		
		JLabel label_7 = new JLabel(result);
		label_7.setBounds(201, 232, 72, 18);
		label_7.setFont(f2);
		add(label_7);
		
		JLabel label_8 = new JLabel("可信度");
		label_8.setBounds(33, 284, 94, 18);
		label_8.setFont(f);
		add(label_8);
		
		JLabel lblT = new JLabel(p);
		lblT.setBounds(201, 284, 257, 18);
		lblT.setFont(f2);
		add(lblT);
		
		JLabel label_9 = new JLabel("图片名称");
		label_9.setBounds(33, 341, 139, 18);
		label_9.setFont(f);
		add(label_9);
		
		JLabel lblNewLabel_1 = new JLabel(imageName);
		lblNewLabel_1.setBounds(203, 343, 209, 18);
		lblNewLabel_1.setFont(f2);
		add(lblNewLabel_1);
		
		JLabel label_10 = new JLabel("返回记录页面");
		label_10.addMouseListener(new MouseAdapter(){			

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Frame.card.show(Frame.panel, "2");
			}
			
		});
		label_10.setBounds(14, 13, 133, 18);
		add(label_10);
	}
	
}
