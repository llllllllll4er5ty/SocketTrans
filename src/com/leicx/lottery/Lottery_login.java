package com.leicx.lottery;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

/**
 *@author leicx 
 *@time 2017年12月23日
 */
public class Lottery_login extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lottery_login frame = new Lottery_login();
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
	public Lottery_login() {
		setTitle("\u5E78\u8FD0\u5927\u62BD\u5956");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("\u5E78\u8FD0\u5927\u62BD\u5956\uFF0C\u5FEB\u6765\u8BD5\u8BD5\u4F60\u7684\u8FD0\u6C14\u5427");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(118, 59, 297, 45);
		contentPane.add(label_1);
		
		JButton button = new JButton("\u767B\u5F55");
		button.setBounds(118, 186, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u6CE8\u518C");
		button_1.setBounds(297, 186, 93, 23);
		contentPane.add(button_1);
	}
}
