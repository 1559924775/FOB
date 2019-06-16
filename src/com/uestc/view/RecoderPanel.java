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
//	private static JPanel jp;		//总面板
	private static TableModel m;
//	private static JScrollPane jsp; //中面板的中间
//	private static JPanel sp;	//总面板中的上面板
//	private static JPanel xp;	//总面板中的上面板
	//finalRows位总记录数
	private static CopyOnWriteArrayList<CopyOnWriteArrayList<String>> finalRows;//这个row取到后不变，以供返回使用
	private static CopyOnWriteArrayList<CopyOnWriteArrayList<String>> rows;
	private static boolean flag=true;//保证finalRows只有一次赋值;
	private static int pageNow=1; //当前页
	private static int rowCount; //总记录数
	private static int pageCount; //总页数
	private static int pageSize=10;//每页20条记录
	
	//组件定义
	JLabel jl;
//	JLabel jb;
//	JButton jb2;
//	JButton jb3;
	JButton jb4;
	JButton jb5;
	JButton jb6;
	JTextField go;
	JButton jb7;//跳转
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
		// 下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
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
		
		//sp的组件
		
//		jb2=new JButton("查询");
//		jb3=new JButton("返回");
//		jb6=new JButton("刷新");
		//xp的组件
		rowCount=finalRows.size();
		pageCount=rowCount/pageSize+1;
		jb4=new JButton("上一页");
		jb5=new JButton("下一页");
		go=new JTextField(3);
		jb7=new JButton("跳转");
		
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
		
		label_recoder=new JLabel("查看当前行测试报告");
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
		jl=new JLabel("第"+pageNow+"页 （共"+pageCount+"页）");
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
	 * 组件注册监听
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
						//找到了
						selectedRows.add(row);			
					}								
				}
				TableModel.setRows(selectedRows);
				jtb.repaint() ;
				if(selectedRows.size()==0)			
					JOptionPane.showMessageDialog(null, "无记录");
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
					JOptionPane.showMessageDialog(RecoderPanel.this, "请选中一行");
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
	 * 分页显示
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
		jl.setText("第"+pageNow+"页 （共"+pageCount+"页）");
		jl.repaint();
		jtb.repaint() ;
	}
	/**
	 * 显示测试报告页面
	 * @param row
	 */
	private void showResultPanel(List<String> row){
		
		String name=row.get(0);
		String time=row.get(1);
		String result=row.get(2);
		String p=row.get(3);
		String path=".\\测试结果\\"+result+"\\Tra"+row.get(4);
		System.out.println(path);
		String imageName=row.get(4);
		
		//显示测试报告页面
		ReportPanel report=new ReportPanel(name,time,result,p,path,imageName);
		Frame.panel.add(report,"3");
		Frame.card.show(Frame.panel,"3");
	}
}
