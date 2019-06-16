package com.uestc.model;

import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.io.*;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel{
	private static CopyOnWriteArrayList<CopyOnWriteArrayList<String>> rows;
	
	private static CopyOnWriteArrayList<String> lines;
	InputStream in;
	BufferedReader br;
	public TableModel(){
		rows=new CopyOnWriteArrayList<>(); 
		lines=new CopyOnWriteArrayList<String>();
		try {
			in = new FileInputStream(".\\out.txt");
			br=new BufferedReader(new InputStreamReader(in));
			try{
				String oneLine=null;
				while((oneLine=br.readLine())!=null){
					String[] values=oneLine.split("\t");//tab�����
					CopyOnWriteArrayList<String> row=new CopyOnWriteArrayList<>(); 	
					for(int i=0;i<values.length;i++){
						row.add(values[i]);	
					}
					rows.add(row);
				}
			}catch(Exception e){
				
			}finally{
				try {			
					br.close();
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lines.add("����");
		lines.add("����ʱ��");
		lines.add("���Խ��");
		lines.add("���Ŷ�");
		lines.add("ͼƬ����");
		
		
		
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rows.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return lines.size();
	}

	
	//���ж�λֵ
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((CopyOnWriteArrayList<String>)rows.get(rowIndex)).get(columnIndex);
	}	
	//����
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String)this.lines.get(column);
	}	
	public static CopyOnWriteArrayList<CopyOnWriteArrayList<String>> getRows() {
		return rows;
	}
	public static void setRows(CopyOnWriteArrayList<CopyOnWriteArrayList<String>> rows) {
		TableModel.rows = rows;
	}
	public static CopyOnWriteArrayList<String> getLines() {
		return lines;
	}
	public static void setLines(CopyOnWriteArrayList<String> lines) {
		TableModel.lines = lines;
	}
}
