package com.leicx.socket;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.JComboBox;

/**
 *@author leicx 
 *@time 2017年12月22日
 */
public class SocketTrans extends JFrame {

	private JPanel contentPane;
	private JTextField IP;
	private JTextField Port;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SocketTrans frame = new SocketTrans();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SocketTrans() {
		setTitle("\u62A5\u6587\u6D4B\u8BD5");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 431);
		//设置位置
		int windowWidth = getWidth();                     //获得窗口宽
	    int windowHeight = getHeight();                   //获得窗口高
	    Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
	    Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
	    int screenWidth = screenSize.width;                     //获取屏幕的宽
	    int screenHeight = screenSize.height;                   //获取屏幕的高
		setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示  
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		IP = new JTextField();
		IP.setBounds(10, 43, 129, 23);
		IP.setText("10.229.169.58");
		contentPane.add(IP);
		IP.setColumns(10);
		
		Port = new JTextField();
		Port.setBounds(10, 119, 129, 23);
		Port.setText("39001");
		contentPane.add(Port);
		Port.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("IP\u5730\u5740");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(10, 18, 54, 15);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("\u7AEF\u53E3\u53F7");
		lblNewLabel_1.setBounds(10, 94, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(179, 43, 400, 99);
		contentPane.add(textArea);
		
		final JTextArea textArea_1 = new JTextArea();
		
		JLabel label = new JLabel("\u8BF7\u6C42\u62A5\u6587");
		label.setBounds(179, 18, 54, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u8FD4\u56DE\u62A5\u6587");
		label_1.setBounds(179, 202, 54, 15);
		contentPane.add(label_1);
		
		final JComboBox comboBox = new JComboBox(new String[] {"UTF-8","GBK","ISO-8859-1"});
		comboBox.setBounds(10, 198, 98, 23);
		contentPane.add(comboBox);
		
		JButton button = new JButton("\u53D1\u9001");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String ip = IP.getText().toString().trim();
				final String port = Port.getText().toString().trim();
				final String code = ""+comboBox.getSelectedItem();
				//判断是否为空
				if("".equals(ip) || "".equals(port)) {
					AlertDialog.getDialog("提示","请输入IP地址或端口号").setVisible(true);
					return;
				}
				System.out.println(ip+","+port);
				final String reqXML = textArea.getText().toString().trim();
				if("".equals(reqXML)) {
					AlertDialog.getDialog("提示","请输入请求报文").setVisible(true);
					return;
				}
				// 开启一个线程发送报文
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							// 建立tcp服务
							Socket socket = new Socket(ip,Integer.parseInt(port));
							// 获取socket的输出流对象
							OutputStream os = socket.getOutputStream();
							
							String head = String.valueOf(reqXML.length());
							while(head.length()<8){
								head = "0" + head;
							};
							
							String sendXML = head + reqXML;
							System.out.println("发送数据：" +sendXML);
							// 写出数据
							os.write(sendXML.getBytes());
							
							// 获取输入流对象，以获取服务端的数据
							InputStream is = socket.getInputStream();
							byte[] buf = new byte[1024*1024];
							int len = is.read(buf);
							String rspXML = new String(buf,0,len,code);
							System.out.println("接收数据：" + rspXML);
							rspXML = rspXML.substring(8);
							
							// 关闭资源
							socket.close();
							
							//给textArea_1赋值
							String oriText = textArea_1.getText().toString().trim();
							if("".equals(oriText)) {
								textArea_1.setText(rspXML);
							}else {
								textArea_1.setText(oriText + "\r\n" + rspXML);
							}
						} catch(ConnectException connectException) {
							AlertDialog.getDialog("提示","连接失败，请检查您的网络是否正确连接！").setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}).start();
			}
		});
		button.setBounds(179, 158, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u6E05\u7A7A");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		button_1.setBounds(298, 158, 93, 23);
		contentPane.add(button_1);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(179, 43, 400, 99);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane(textArea_1);
		scrollPane_1.setBounds(179, 227, 400, 110);
		contentPane.add(scrollPane_1);
		
		JLabel lblNewLabel_2 = new JLabel("编码");
		lblNewLabel_2.setBounds(10, 173, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		JButton button_2 = new JButton("清空");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText("");
			}
		});
		button_2.setBounds(179, 359, 93, 23);
		contentPane.add(button_2);
		
	}
}
