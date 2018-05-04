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
 *@time 2017年12月22日
 */
public class MyServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket serverSocket=new ServerSocket(8888);
			System.out.println("服务端已启动，等待客户端连接..");
			Socket socket = serverSocket.accept();//侦听并接受到此套接字的连接,返回一个Socket对象
			
			InputStream inputStream = socket.getInputStream();//得到一个输入流，接收客户端传递的信息
			
			byte[] buf = new byte[1024*1024];
			int len = inputStream.read(buf);
			String reqXML = new String(buf,0,len,"UTF-8");
			
			System.out.println("服务端接收到客户端信息："+reqXML+",当前客户端ip为："+socket.getInetAddress().getHostAddress());
			OutputStream outputStream = socket.getOutputStream();//获取一个输出流，向服务端发送信息
			PrintWriter printWriter = new PrintWriter(outputStream);//将输出流包装成打印流
			printWriter.print("你好，服务端已接收到您的信息");
			printWriter.flush();
			socket.shutdownOutput();//关闭输出流
			
			//关闭相对应的资源
			printWriter.close();
			outputStream.close();
			inputStream.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
