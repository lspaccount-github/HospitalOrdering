package com.cn.ssm.utils.unitconversion;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class AboutFrame extends JFrame {
	JPanel contentPane;
	JScrollPane jSPane=new JScrollPane();
	JTextArea aboutText=new JTextArea(5,5);
	String temStr;
	
	Font aboutFont=new Font("微软雅黑",1,15);
	
	// 构造函数  初始化图形用户界面
	public AboutFrame(){
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		setSize(new Dimension(600,500));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		contentPane=(JPanel) getContentPane();
		contentPane.setLayout(new BorderLayout());		
		setSize(new Dimension(400,300));
		setTitle("About copyright and informations!");
			
		contentPane=(JPanel) getContentPane();
		contentPane.setLayout(new BorderLayout());

		
		temStr="此程序中只实现了常用长度、时间、温度单位的换算，\r\n其它的换算功能可在代码中根据换算公式添加。"+
				"\r\n\r\n\r\n"+"Designed And Developed By zjMa";
		aboutText.setText(temStr);
		aboutText.setFont(aboutFont);
		jSPane.getViewport().add(aboutText,null);
		
		contentPane.add(jSPane, "Center");
		this.aboutText.setEditable(false);
		
	}

	
}
