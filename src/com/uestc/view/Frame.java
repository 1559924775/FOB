package com.uestc.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;
import javax.swing.UIManager;

public class Frame {
	JFileChooser jfc = new JFileChooser();// �ļ�ѡ����
	protected static boolean isopen = true;// �жϵ�ǰ�����Ƿ�򿪵ı�־
	private JFrame frame;
	private String pathString;
	private static boolean flag = true;// ��֤finalRowsֻ��һ�θ�ֵ;
	private static CopyOnWriteArrayList<CopyOnWriteArrayList<String>> finalRows;// ���rowȡ���󲻱䣬�Թ�����ʹ��
	private String path = null;
	protected static CardLayout card;
	static RecoderPanel recoderPanel;
	private static ReportPanel report;
	private static Thread schedule;//�鿴���ȵĽ���
	protected static JPanel panel ;//������
	static TestPanel testPanel;//���Խ���
	private MenuBar menuBar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Frame window = new Frame();
					window.frame.setVisible(true);
					window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// ����Ĵ���
		frame = new JFrame("����ĺ��");
		card=new CardLayout();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		ImageIcon image = new ImageIcon(".\\src\\app.png");
		frame.setIconImage(image.getImage());
		frame.getContentPane().setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		frame.getContentPane().setLayout(null);
		// frame.setResizable(false);

		// �������Panel
		panel= new JPanel() ;
		menuBar=new MenuBar();
		frame.setJMenuBar(menuBar);
		recoderPanel=new RecoderPanel();
		panel.setBounds(0, 0, 882, 550);
		panel.setBackground(new Color(192, 192, 192));
		frame.getContentPane().add(panel);
		panel.setLayout(card);
		Color color1 = new Color(203, 38, 3);
		Color color2 = new Color(243, 46, 3);
		Color color3 = new Color(252, 77, 39);
		Color color4 = new Color(253, 123, 96);
		Color color5 = new Color(253, 128, 125);;
		recoderPanel=new RecoderPanel();
		testPanel=new TestPanel();
		panel.add(testPanel,"1");
		
		card.show(panel, "1");

		frame.setBounds(430,150,890,540);
	}
	
}
