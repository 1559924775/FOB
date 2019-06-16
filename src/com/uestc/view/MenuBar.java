package com.uestc.view;



import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.uestc.controller.TestService;

import javax.swing.JMenu;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuBar extends JMenuBar{
	private TestService testService=new TestService();
	private JFileChooser jfc = new JFileChooser();// 文件选择器
	public MenuBar() {
//		setLayout(null);
		
//		JMenuBar this = new JMenuBar();
//		this.setBounds(0, 0, 872, 26);
//		add(this);
		
		JMenu menu = new JMenu("文件");
		menu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		this.add(menu);
		
		JMenuItem menuItem = new JMenuItem("打开图片");
		menuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menu.add(menuItem);
		
		JMenu menu_1 = new JMenu("记录");
		menu_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		this.add(menu_1);
		
		JMenuItem menuItem_1 = new JMenuItem("搜索");
		menuItem_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menu_1.add(menuItem_1);
		
		JMenu menu_2 = new JMenu("设置");
		menu_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		this.add(menu_2);
		
		JMenuItem menuItem_2 = new JMenuItem("改变界面");
		menuItem_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menu_2.add(menuItem_2);
		
		JMenu menu_3 = new JMenu("帮助");
		menu_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		this.add(menu_3);
		
		JMenuItem menuItem_3 = new JMenuItem("使用说明");
		menuItem_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menu_3.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("版本信息");
		menuItem_4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menu_3.add(menuItem_4);
		
		//注册监听
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(menuItem)) {// 判断触发方法的按钮是哪个
					// jfc.setFileSelectionMode(1);//设定只能选择到文件夹
					int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
					jfc.setCurrentDirectory(jfc.getSelectedFile());

					if (state == 1) {
						return;// 撤销则返回
					} else {
						File f = jfc.getSelectedFile();// f为选择到的目录
						String testName = JOptionPane.showInputDialog(null, "请输入姓名：");
						testService.test(f, testName, Frame.testPanel);
					

					}
				}

			}
		});
		menuItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 创建新的窗口
				Frame.recoderPanel=new RecoderPanel();
				Frame.panel.add(Frame.recoderPanel,"2");
				Frame.card.show(Frame.panel, "2");
			}

		});
		menuItem_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 显示版本信息
				// 设置按钮显示效果

				JOptionPane.showMessageDialog(null, "\r\n" + "软件用途：FOB卡检测\n" + "软件使用步骤：\n" + "一、测试模式选择\r\n"
						+ "【单张检测模式】\r\n" + "将需要测试的图片拖拽到“单张检测”之上方框中，点击“单张检测”，然后输入测试者的姓名或者编号；\r\n"
						+  "在【单张检测模式】检测之后，点击中间的被检测图片可以查看原图。\n" + "【批量检测模式】\r\n"
						+ "将需要检测的图片文件夹拖拽到下方的输入框内后，点击“批量测试”，然后输入测试者的姓名或者编号；\r\n" + "在【批量检测模式】下，请等待检测进度到达100%表示检测完成。\n"
						+ "二、检测结果和记录\r\n" + "检测结果会按照可能性从大到小排列，概率最大的即为软件最终检测出来的结果；\n"
						+ "检测之后的结果会保存下来，可以通过界面的“记录查询”->“搜索”来搜索、查看检测的记录。\r\n" + "三、结果对照\r\n" + "L：代表卡污染；\r\n"
						+ "N：代表阴性；\r\n" + "P：代表阳性；\r\n" + "WP：代表弱阳；\r\n" + "Y：代表图片异常（FOB卡片损坏或残缺）。\r\n" + "四、注意事项：\n"
						+ "请确保图片的路径没有中文字符或空格；\n" + "请确保软件文件夹放置的路径没有中文字符或者空格；\n" 
						+ "处理速度的快慢主要受电脑配置的影响。", "使用说明", JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon(".\\src\\express.png"));
			}
		});
		menuItem_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 显示版本信息
				JOptionPane.showMessageDialog(null, "Version1.0 ", " 版本号  CopyRight XRML",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	
}
