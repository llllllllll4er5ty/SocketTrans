package com.leicx.socket;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *@author leicx 
 *@time 2017��12��22��
 */
public class AlertDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AlertDialog dialog = new AlertDialog("��ʾ", "����");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����dialog
	 */
	public static JDialog getDialog(String title, String content) {
		AlertDialog dialog = new AlertDialog(title, content);
		return dialog;
	}
	
	/**
	 * Create the dialog.
	 */
	public AlertDialog(String title, String content) {
		setResizable(false);
		setTitle(title);
		setBounds(100, 100, 261, 164);
		//����λ��
		int windowWidth = getWidth();                     //��ô��ڿ�
	    int windowHeight = getHeight();                   //��ô��ڸ�
	    Toolkit kit = Toolkit.getDefaultToolkit();              //���幤�߰�
	    Dimension screenSize = kit.getScreenSize();             //��ȡ��Ļ�ĳߴ�
	    int screenWidth = screenSize.width;                     //��ȡ��Ļ�Ŀ�
	    int screenHeight = screenSize.height;                   //��ȡ��Ļ�ĸ�
		setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//���ô��ھ�����ʾ  
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(Color.WHITE);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblip = new JLabel(content);
			lblip.setForeground(Color.BLACK);
			lblip.setFont(new Font("����", Font.BOLD, 12));
			contentPanel.add(lblip);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u786E\u5B9A");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);// �Ի��򲻿ɼ���
			            dispose();// �Ի�������
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
