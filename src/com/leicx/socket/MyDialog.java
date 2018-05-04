package com.leicx.socket;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *@author leicx 
 */
// 对话框类
public class MyDialog extends JDialog implements ActionListener {
    String title;
    String content;
 
    public MyDialog(String title, String content) {
        this.title = title;
        this.content = content;
//        System.out.println(System.getProperty("user.dir"));
        ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"/res/tp.jpg");// 创建1个图标实例
        JLabel jlImg = new JLabel(icon);// 1个图片标签,显示图片
        JLabel jl = new JLabel(content);// 1个文字标签,显示文本
        jl.setForeground(Color.RED);// 设置文字的颜色为蓝色
        JButton jb = new JButton("确定");// 创建1个按钮
        jb.addActionListener(this);// 给按钮添加响应事件
//        add(jlImg);// 向对话框加入图片标签
        add(jl);// 向对话框加入文字标签
        add(jb);// 向对话框添加按钮
        setLayout(new FlowLayout());// 对话框流式布局
        setIconImage(icon.getImage());// 窗口左上角的小图标
        setTitle(title);// 设置标题
        setModal(true);// 设置为模态窗口
        setSize(200, 150);// 设置对话框大小
        setLocationRelativeTo(null);// 对话框局域屏幕中央
        setResizable(false);// 对话框不可缩放
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);// 当对话框窗口的关闭按钮[X]被点击时,销毁对话框
    }
 
    // 当确定按钮被点击时会执行下面的方法
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("确定")) {// 判断是不是确定按钮被点击
            this.setVisible(false);// 对话框不可见
            this.dispose();// 对话框销毁
        }
    }
    public static void main(String[] args) {
    	new MyDialog("test","你好").setVisible(true);;
	}
}