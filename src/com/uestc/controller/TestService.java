package com.uestc.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.uestc.view.TestPanel;


public class TestService {
	/**
	 * ��������
	 */
	public static ExecutorService exe = Executors.newFixedThreadPool(2);// ����2���߳�ȥִ��
	public static Thread schedule;
	private JButton btnNewButton;
	private JPanel panel_2;
	private static double size=0;//�ܹ�Ҫ���Ե�ͼƬ��
	private static double now=0;//��ǰ�ڲ��Եڼ���
	public void test(String path, String testName,TestPanel testPanel) {
		exe = Executors.newFixedThreadPool(2);// ����2���߳�ȥִ��
		size=0;
		now=0;
		// System.out.println(path);
		File dir = new File(path);
		// �ж��ǵ���ͼƬ�����ļ���
		if (!dir.isDirectory()) {
			test(dir, testName,testPanel);
			return;
		}
		File[] images = dir.listFiles();
		size=images.length;
		try {
			for (int i = 0; i < images.length; i++) {
				System.out.println(images[i]);
				File image = images[i];
				// ÿһ��ͼƬ������һ���߳�ȥִ��
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						test(image, testName,testPanel);
					}

				});
				exe.submit(t);
				// test(image,testName);
				// TimeUnit.MILLISECONDS.sleep(1000);
			}
			
			//����һ���߳�ȥ������Խ���
			schedule=new Thread(){
				@Override
				public void run(){
					boolean flag=true;
					String s=Math.round((now/size)*100)+"%";
					while(flag&&!s.equals("100%")){
						//���쳣�����жϱ�־λ����
						s=Math.round((now/size)*100)+"%";
						Font f=new Font("����", Font.PLAIN, 20);
						testPanel.getTextField_7().setFont(f);
						testPanel.getTextField_7().setBackground(Color.black);
						testPanel.getTextField_7().setText("���Խ��ȣ�"+s);
						System.out.println("schedule.interrupted()-------------------------------"+schedule.isInterrupted());
						System.out.println("Thread.interrupted()-------------------------------"+Thread.interrupted());
						try {
							TimeUnit.MILLISECONDS.sleep(200);
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							flag=false;
							//��ʼ��		
							
						}
					}
					testPanel.getTextField_7().setFont(new Font("΢���ź�", Font.PLAIN, 12));
					testPanel.getTextField_7().setBackground(Color.white);
				}
			};
			schedule.start();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ����ͼƬ����
	 * 
	 * @param f
	 */
	public void test(File f, String testName,TestPanel testPanel) {
		String fileName = "Tra" + f.getName();
		
		cutImage(160, 0, 960, 960, f.getAbsolutePath(), fileName);

		Process pro;
		try {

			pro = Runtime.getRuntime()
					.exec("classify.exe -g FOB.pb -l imagenet_slim_labels.txt -iw 224 -ih 224 -il input_4 -ol  output_node0 -im 44.39732360839843750,44.39732360839843750,44.39732360839843750 -is 54.33131208691206250,54.08889007568359375,56.24784851074218750 -i "
							+ ".\\Transit\\" + fileName);

			BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			String str;
			ArrayList<String> list = new ArrayList<String>();
			while ((str = br.readLine()) != null) {
				list.add(str);
			}
			pro.destroy();
			// list.get(0)�������ĸ���
			String p = list.get(0).substring(7, 12);// ����ֵ
			double ip = Double.parseDouble(p);
			String property = list.get(0).substring(0, 1);
			String propertyString = new String();// ����
			switch (property) {
			case "P":
				propertyString = "����";

				break;
			case "N":
				propertyString = "����";
				break;
			case "W":
				propertyString = "����";
				break;
			case "L":
				propertyString = "����Ⱦ";
				break;
			case "Y":
				propertyString = "���쳣";
				break;

			default:
				break;
			}
			showResult(f, list,testPanel);
			// �����д��out.txt
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(date);// ʱ��

			String result = testName + "\t" + dateString + "\t" + propertyString + "\t" + ip * 100 + "%" + "\t"
					+ f.getName() + "\r\n";
			System.out.println(result);
			FileWriter pw = new FileWriter(".\\out.txt", true);
			pw.write(result);

			pw.flush();
			pw.close();
			// ��ͼƬ�ֵ���Ӧ���ļ���
			 copy(f,propertyString,fileName);
			br.close();
			File fi = new File(".\\Transit\\" + fileName);
			fi.delete();
			now++;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void showResult(File f, List<String> list,TestPanel testPanel) {
		ImageIcon image = new ImageIcon(f.getAbsolutePath());
		image.setImage(image.getImage().getScaledInstance(294, 220, Image.SCALE_DEFAULT));
		testPanel.getLabel().setIcon(image);

		double result1 = Double.parseDouble(list.get(0).substring(7, 12));
		if (result1 > 1)
			result1 = 0;
		testPanel.getProgressBar().setValue((new Double(result1 * 10000)).intValue());

		double result2 = Double.parseDouble(list.get(1).substring(7, 12));
		if (result2 > 1)
			result2 = 0;
		testPanel.getProgressBar_1().setValue((new Double(result2 * 10000)).intValue());

		double result3 = Double.parseDouble(list.get(2).substring(7, 12));
		if (result3 > 1)
			result3 = 0;
		testPanel.getProgressBar_2().setValue((new Double(result3 * 10000)).intValue());
		System.out.println(list.get(3));
		System.out.println(list.get(3).substring(7, 12));
		double result4 = Double.parseDouble(list.get(3).substring(7, 12));
		if (result4 > 1)
			result4 = 0;
		testPanel.getProgressBar_3().setValue((new Double(result4 * 10000)).intValue());
		double result5 = Double.parseDouble(list.get(4).substring(7, 12));
		if (result5 > 1)
			result5 = 0;
		testPanel.getProgressBar_4().setValue((new Double(result5 * 10000)).intValue());
		testPanel.getTextField_5().setText(list.get(0));
		testPanel.getTextField_4().setText(list.get(1));
		testPanel.getTextField_6().setText(list.get(2));
		testPanel.getTextField_2().setText(list.get(3));
		testPanel.getTextField_3().setText(list.get(4));
	}
	// ����ͼƬ
		private void copy(File f, String propertyString, String fileName) {

			try {
				FileInputStream fis = new FileInputStream(f);
				File dir = new File(".\\���Խ��\\" + propertyString);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				File fi = new File(".\\���Խ��\\" + propertyString + "\\" + fileName);
				FileOutputStream fos = new FileOutputStream(fi);
				byte[] buf = new byte[1024];
				int n = 0;
				while ((n = fis.read(buf)) != -1) {
					fos.write(buf);
				}
				fos.close();
				fis.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		/**
		 * �и�ͼƬ
		 * 
		 * @param x
		 *            �ص������ (����ʼ����)
		 * @param y
		 *            �ص������� (���Ͽ�ʼ����)
		 * @param width
		 *            ��ȡ�Ŀ��
		 * @param height
		 *            ��ȡ�ĳ���
		 * @param oldpath
		 *            ͼƬλ��
		 * @param newpath
		 *            �����ɵ�ͼƬλ��
		 */
		public static void cutImage(int x, int y, int width, int height, String oldpath, String newpath) {
			FileInputStream is = null;
			ImageInputStream iis = null;
			File dir = new File(".\\Transit");
			if (!dir.exists())
				dir.mkdirs();
			// ����ǻ�ȡͼƬ��չ���ķ��������磺jpg�����������ֳɵģ����û�У��Լ�ʵ��
			String imgType = oldpath.substring(oldpath.lastIndexOf(".") + 1);
			System.out.println(imgType);

			try {
				is = new FileInputStream(oldpath);
				Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(imgType);
				ImageReader reader = it.next();
				iis = ImageIO.createImageInputStream(is);
				reader.setInput(iis, true);
				ImageReadParam param = reader.getDefaultReadParam();
				Point p = new Point();
				p.setLocation(x, y);
				Dimension d = new Dimension();
				d.setSize(width, height);
				Rectangle rect = new Rectangle(p, d);
				param.setSourceRegion(rect);
				BufferedImage bi = reader.read(0, param);
				ImageIO.write(bi, imgType, new File(".\\Transit\\" + newpath));
				is.close();
				iis.close();
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
		/**
		 * ʵ����ק�ķ���
		 * 
		 * @param component
		 *            ��קĿ�ĵ����
		 */
		public void drag(final Component component,TestPanel testPanel)// �������ק����
		{
			// panel��ʾҪ������ק�Ŀؼ�
			new DropTarget(component, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetAdapter() {
				@Override
				public void drop(DropTargetDropEvent dtde)// ��д��������drop����
				{
					try {
						if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor))// ���������ļ���ʽ��֧��
						{
							dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);// ������ק��������
							@SuppressWarnings("unchecked")
							java.util.List<File> list = (java.util.List<File>) (dtde.getTransferable()
									.getTransferData(DataFlavor.javaFileListFlavor));

							dragResponse(list, component,testPanel);
							dtde.dropComplete(true);// ָʾ��ק���������
						} else {
							dtde.rejectDrop();// ����ܾ���ק��������
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				/*
				 * Ĭ��ʵ��
				 */
				protected void dragResponse(java.util.List<File> list, Component component,TestPanel testPanel) {
					String filePath = ((list.get(0))).getAbsolutePath();
					if (component instanceof JTextComponent) {
						JTextComponent text = (JTextComponent) component;
						// ���ı������������Ϊ��ק�ļ���ȫ·��
						text.setText(filePath);
						String s=testPanel.getTextField_8().getText().trim();
//						System.out.println(s+"(((((");
						if(!s.equals("")){
							testPanel.getTextField_8().setText("");
							//��ʼ��
							testPanel.getProgressBar().setValue((new Double(0 * 10000)).intValue());
							testPanel.getProgressBar_1().setValue((new Double(0 * 10000)).intValue());
							testPanel.getProgressBar_2().setValue((new Double(0 * 10000)).intValue());
							testPanel.getProgressBar_3().setValue((new Double(0 * 10000)).intValue());
							testPanel.getProgressBar_4().setValue((new Double(0 * 10000)).intValue());
							ImageIcon image = new ImageIcon(filePath);
							image.setImage(image.getImage().getScaledInstance(294, 220, Image.SCALE_DEFAULT));
							testPanel.getLabel().setIcon(image);
						}
					}
				}
			});
		}
}
