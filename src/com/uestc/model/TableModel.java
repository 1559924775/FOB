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
					String[] values=oneLine.split("\t");//tab键间隔
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

		lines.add("姓名");
		lines.add("测试时间");
		lines.add("测试结果");
		lines.add("可信度");
		lines.add("图片名称");
		
		
		
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

	
	//行列定位值
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((CopyOnWriteArrayList<String>)rows.get(rowIndex)).get(columnIndex);
	}	
	//列名
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
