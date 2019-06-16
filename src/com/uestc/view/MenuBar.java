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
	private JFileChooser jfc = new JFileChooser();// �ļ�ѡ����
	public MenuBar() {
//		setLayout(null);
		
//		JMenuBar this = new JMenuBar();
//		this.setBounds(0, 0, 872, 26);
//		add(this);
		
		JMenu menu = new JMenu("�ļ�");
		menu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		this.add(menu);
		
		JMenuItem menuItem = new JMenuItem("��ͼƬ");
		menuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menu.add(menuItem);
		
		JMenu menu_1 = new JMenu("��¼");
		menu_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		this.add(menu_1);
		
		JMenuItem menuItem_1 = new JMenuItem("����");
		menuItem_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menu_1.add(menuItem_1);
		
		JMenu menu_2 = new JMenu("����");
		menu_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		this.add(menu_2);
		
		JMenuItem menuItem_2 = new JMenuItem("�ı����");
		menuItem_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menu_2.add(menuItem_2);
		
		JMenu menu_3 = new JMenu("����");
		menu_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		this.add(menu_3);
		
		JMenuItem menuItem_3 = new JMenuItem("ʹ��˵��");
		menuItem_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menu_3.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("�汾��Ϣ");
		menuItem_4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		menu_3.add(menuItem_4);
		
		//ע�����
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(menuItem)) {// �жϴ��������İ�ť���ĸ�
					// jfc.setFileSelectionMode(1);//�趨ֻ��ѡ���ļ���
					int state = jfc.showOpenDialog(null);// �˾��Ǵ��ļ�ѡ��������Ĵ������
					jfc.setCurrentDirectory(jfc.getSelectedFile());

					if (state == 1) {
						return;// �����򷵻�
					} else {
						File f = jfc.getSelectedFile();// fΪѡ�񵽵�Ŀ¼
						String testName = JOptionPane.showInputDialog(null, "������������");
						testService.test(f, testName, Frame.testPanel);
					

					}
				}

			}
		});
		menuItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �����µĴ���
				Frame.recoderPanel=new RecoderPanel();
				Frame.panel.add(Frame.recoderPanel,"2");
				Frame.card.show(Frame.panel, "2");
			}

		});
		menuItem_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ʾ�汾��Ϣ
				// ���ð�ť��ʾЧ��

				JOptionPane.showMessageDialog(null, "\r\n" + "�����;��FOB�����\n" + "���ʹ�ò��裺\n" + "һ������ģʽѡ��\r\n"
						+ "�����ż��ģʽ��\r\n" + "����Ҫ���Ե�ͼƬ��ק�������ż�⡱֮�Ϸ����У���������ż�⡱��Ȼ����������ߵ��������߱�ţ�\r\n"
						+  "�ڡ����ż��ģʽ�����֮�󣬵���м�ı����ͼƬ���Բ鿴ԭͼ��\n" + "���������ģʽ��\r\n"
						+ "����Ҫ����ͼƬ�ļ�����ק���·���������ں󣬵�����������ԡ���Ȼ����������ߵ��������߱�ţ�\r\n" + "�ڡ��������ģʽ���£���ȴ������ȵ���100%��ʾ�����ɡ�\n"
						+ "����������ͼ�¼\r\n" + "������ᰴ�տ����ԴӴ�С���У��������ļ�Ϊ������ռ������Ľ����\n"
						+ "���֮��Ľ���ᱣ������������ͨ������ġ���¼��ѯ��->�����������������鿴���ļ�¼��\r\n" + "�����������\r\n" + "L��������Ⱦ��\r\n"
						+ "N���������ԣ�\r\n" + "P���������ԣ�\r\n" + "WP������������\r\n" + "Y������ͼƬ�쳣��FOB��Ƭ�𻵻��ȱ����\r\n" + "�ġ�ע�����\n"
						+ "��ȷ��ͼƬ��·��û�������ַ���ո�\n" + "��ȷ������ļ��з��õ�·��û�������ַ����߿ո�\n" 
						+ "�����ٶȵĿ�����Ҫ�ܵ������õ�Ӱ�졣", "ʹ��˵��", JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon(".\\src\\express.png"));
			}
		});
		menuItem_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ʾ�汾��Ϣ
				JOptionPane.showMessageDialog(null, "Version1.0 ", " �汾��  CopyRight XRML",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	
}
