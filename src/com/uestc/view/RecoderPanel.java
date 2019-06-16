package com.uestc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.*;

import com.uestc.model.TableModel;

public class RecoderPanel extends JPanel{
	private static JTable jtb;
//	private static JPanel jp;		//�����
	private static TableModel m;
//	private static JScrollPane jsp; //�������м�
//	private static JPanel sp;	//������е������
//	private static JPanel xp;	//������е������
	//finalRowsλ�ܼ�¼��
	private static CopyOnWriteArrayList<CopyOnWriteArrayList<String>> finalRows;//���rowȡ���󲻱䣬�Թ�����ʹ��
	private static CopyOnWriteArrayList<CopyOnWriteArrayList<String>> rows;
	private static boolean flag=true;//��֤finalRowsֻ��һ�θ�ֵ;
	private static int pageNow=1; //��ǰҳ
	private static int rowCount; //�ܼ�¼��
	private static int pageCount; //��ҳ��
	private static int pageSize=10;//ÿҳ20����¼
	
	//�������
	JLabel jl;
//	JLabel jb;
//	JButton jb2;
//	JButton jb3;
	JButton jb4;
	JButton jb5;
	JButton jb6;
	JTextField go;
	JButton jb7;//��ת
	private JPanel panel;
	private JPanel panel_1;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JLabel label_1;
	private JLabel label_recoder;
	public static void main(String[] args){
		new RecoderPanel();
	}
	ImageIcon icon = new ImageIcon(".\\timgRIFIQIXZ.jpg");
	Image img = icon.getImage();

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// ����������Ϊ�˱���ͼƬ���Ը��洰�����е�����С�������Լ����óɹ̶���С
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

	}
	public RecoderPanel(){
		this.setLayout(null);
		panel = new JPanel(null);
		panel.setBounds(0, 0, 885, 50);
		add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 387, 885, 50);
		
		add(panel_1);
		

		m=new TableModel();
		jtb=new JTable(m);
		
		jtb.setRowHeight(25);
		
		JScrollPane scrollPane = new JScrollPane(jtb);
		scrollPane.setBounds(10, 63, 861, 311);
		add(scrollPane);
		this.add(scrollPane);
		finalRows=TableModel.getRows();
		
		//sp�����
		
//		jb2=new JButton("��ѯ");
//		jb3=new JButton("����");
//		jb6=new JButton("ˢ��");
		//xp�����
		rowCount=finalRows.size();
		pageCount=rowCount/pageSize+1;
		jb4=new JButton("��һҳ");
		jb5=new JButton("��һҳ");
		go=new JTextField(3);
		jb7=new JButton("��ת");
		
		JLabel lblNewLabel = new JLabel("New label");
		
//		xp.add(jb6);
		
//		this.add(sp,BorderLayout.NORTH);
//		this.add(xp,BorderLayout.SOUTH);
		this.add(panel);
		this.add(panel_1);
	
		
		JButton button = new JButton("\u67E5\u627E");
		button.setBounds(480, 13, 69, 27);
		panel.add(button);
		
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.setBounds(558, 13, 63, 27);
		panel.add(button_1);
		
		label_recoder=new JLabel("�鿴��ǰ�в��Ա���");
		label_recoder.setBounds(750,13,120,27);
		
		panel.add(label_recoder);
		
		register(button,button_1,jb4,jb5,jb7,label_recoder);
		textField = new JTextField();
		textField.setBounds(327, 11, 139, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u8FD4\u56DE\u6D4B\u8BD5");
		label.setBounds(14, 17, 89, 23);
		panel.add(label);
		label.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Frame.card.show(Frame.panel,"1");
			}

			
		});
		
		label_1 = new JLabel("\u8F93\u5165\u59D3\u540D");
		label_1.setBounds(241, 17, 72, 18);
		
		
		
		
		
		panel.add(lblNewLabel);
		
		
		
		panel.add(label_1);
		panel.add(textField);
		panel.add(button);
		panel.add(button_1);
		panel_1.add(jb4);
		jl=new JLabel("��"+pageNow+"ҳ ����"+pageCount+"ҳ��");
		panel_1.add(jl);
		panel_1.add(jb5);
		panel_1.add(go);
		panel_1.add(jb7);
		panel.add(label_1);
		showPage(pageNow,pageSize);
		
		
		
//		this.add(jp);
//		this.setSize(882, 550);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setBounds(500,300,900,600);
//		this.setVisible(true);
//		this.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e) {
//				RecoderPanel.this.dispose();
//				UI.isopen = true;
//			}
//		});
	}
	/**
	 * ���ע�����
	 * @param i
	 * @param jb2
	 * @param jb3
	 * @param jb4
	 * @param jb5
	 */
	private void register(JButton jb2,JButton jb3,JButton jb4,JButton jb5,JButton jb7,JLabel label_recoder){
		jb2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				 //TODO Auto-generated method stub
				
				String name=textField.getText().trim();								
				
				CopyOnWriteArrayList<CopyOnWriteArrayList<String>> selectedRows=new CopyOnWriteArrayList<CopyOnWriteArrayList<String>>() ;
				for(CopyOnWriteArrayList<String> row:finalRows){
					if(row.get(0).equals(name)){
						//�ҵ���
						selectedRows.add(row);			
					}								
				}
				TableModel.setRows(selectedRows);
				jtb.repaint() ;
				if(selectedRows.size()==0)			
					JOptionPane.showMessageDialog(null, "�޼�¼");
			}
			
		});
		jb3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pageNow=1;
				showPage(pageNow,pageSize);
			}
			
		});
		jb4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(pageNow>1){
					pageNow=pageNow-1;
					showPage(pageNow,pageSize);
				}else{
					pageNow=pageCount;
					showPage(pageNow,pageSize);
				}
					
			}
			
		});
		jb5.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				pageNow=pageNow%pageCount+1;
				showPage(pageNow,pageSize);
			}
			
		});
		jb7.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int d=(pageNow-1)*pageSize+jtb.getSelectedRow();
				System.out.println(d+"_______________"+finalRows.get(d).get(0));
				
				String page=go.getText().trim();
				
				if(!page.equals("")){
					System.out.println(page);
					pageNow=Integer.parseInt(page);
					if(pageNow<=pageCount)
					showPage(pageNow,pageSize);
				}
				
			}
			
		});
		label_recoder.addMouseListener(new MouseAdapter(){			

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int d=(pageNow-1)*pageSize+jtb.getSelectedRow();
				if(d!=-1)
				RecoderPanel.this.showResultPanel(finalRows.get(d));
				else 
					JOptionPane.showMessageDialog(RecoderPanel.this, "��ѡ��һ��");
			}
			
		});
//		jb6.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//			
//				m=new TableModel();
//				jp.remove(jsp);
//				jtb=new JTable(m);				
//				jsp=new JScrollPane(jtb);
//				jtb.setRowHeight(25);
//				jp.add(jsp);
//				jp.repaint();
//				
//				finalRows=TableModel.getRows();
//				
//				pageNow=1;
//				showPage(pageNow,pageSize);
//			}
//			
//		});
	}
	/**
	 * ��ҳ��ʾ
	 * @param pageNow
	 * @param pageSize
	 */
	private void showPage(int pageNow,int pageSize){
		rows=new CopyOnWriteArrayList<>();
		for(int i=(pageNow-1)*pageSize;i<pageNow*pageSize;i++){
			if(i==finalRows.size())break;
			CopyOnWriteArrayList<String> row=finalRows.get(i);
			if(row!=null)rows.add(row);
		}
		TableModel.setRows(rows);
		jl.setText("��"+pageNow+"ҳ ����"+pageCount+"ҳ��");
		jl.repaint();
		jtb.repaint() ;
	}
	/**
	 * ��ʾ���Ա���ҳ��
	 * @param row
	 */
	private void showResultPanel(List<String> row){
		
		String name=row.get(0);
		String time=row.get(1);
		String result=row.get(2);
		String p=row.get(3);
		String path=".\\���Խ��\\"+result+"\\Tra"+row.get(4);
		System.out.println(path);
		String imageName=row.get(4);
		
		//��ʾ���Ա���ҳ��
		ReportPanel report=new ReportPanel(name,time,result,p,path,imageName);
		Frame.panel.add(report,"3");
		Frame.card.show(Frame.panel,"3");
	}
}
