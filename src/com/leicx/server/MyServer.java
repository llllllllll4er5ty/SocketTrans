package com.leicx.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *@author leicx 
 *@time 2017��12��22��
 */
public class MyServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket serverSocket=new ServerSocket(8888);
			System.out.println("��������������ȴ��ͻ�������..");
			Socket socket = serverSocket.accept();//���������ܵ����׽��ֵ�����,����һ��Socket����
			
			InputStream inputStream = socket.getInputStream();//�õ�һ�������������տͻ��˴��ݵ���Ϣ
			
			byte[] buf = new byte[1024*1024];
			int len = inputStream.read(buf);
			String reqXML = new String(buf,0,len,"UTF-8");
			
			System.out.println("����˽��յ��ͻ�����Ϣ��"+reqXML+",��ǰ�ͻ���ipΪ��"+socket.getInetAddress().getHostAddress());
			OutputStream outputStream = socket.getOutputStream();//��ȡһ��������������˷�����Ϣ
			PrintWriter printWriter = new PrintWriter(outputStream);//���������װ�ɴ�ӡ��
			printWriter.print("��ã�������ѽ��յ�������Ϣ");
			printWriter.flush();
			socket.shutdownOutput();//�ر������
			
			//�ر����Ӧ����Դ
			printWriter.close();
			outputStream.close();
			inputStream.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
