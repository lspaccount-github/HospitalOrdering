package com.cn.ssm.utils.unitconversion;
/**
 * 单位换算
 * 目前只实现了常用长度单位，时间单位，温度单位的换算，
 * 其他换算可根据单位换算关系再添加。
 * 
 * Designed And Developed By zjMa
 * 
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class Conversion extends JFrame implements ActionListener{

	/**
	 * @param args
	 */
	
	//建立工具栏
	JToolBar toolBar=new JToolBar();
	//建立工具栏中的按钮组件
	JButton picLength=new JButton("长度(L)");
	JButton picSquare=new JButton("面积(S)");
	JButton picVolume=new JButton("体积(V)");
	JButton picTemperature=new JButton("温度(K)");
	JButton picHeat=new JButton("热量(Q)");
	JButton picAngle=new JButton("角度(A)");
	JButton picTime=new JButton("时间(T)");
	JButton picRate=new JButton("速率(R)");
	JButton picPressure=new JButton("压强(P)");
	JButton picQuality=new JButton("质量(M)");
	JButton picPower=new JButton("功率(W)");
	//中心区
	JPanel boxLength=new JPanel();
	JPanel boxSquare=new JPanel();
	JPanel boxVolume=new JPanel();
	JPanel boxTime=new JPanel();
	JPanel boxRate=new JPanel();
	JPanel boxQuality=new JPanel();
	JPanel boxPressure=new JPanel();
	JPanel boxPower=new JPanel();
	JPanel boxTemperature=new JPanel();
	JPanel boxHeat=new JPanel();
	JPanel boxAngle=new JPanel();
	
	JMenuBar jMenuBar=new JMenuBar();
	JMenu aboutMenu=new JMenu("About");
	JMenuItem aboutItem=new JMenuItem("about");

	
	//
	String[] time={"ms","s","min","hour","day","week","μs"};
	String[] length={"cm","dm","m","km","mm","μm","nm","feet/英尺",
			"inche/英寸","Mile/英里","nautical mile/海里"};
	String[] square={};
	String[] temperature={"℃","℉","K"};
	
	//from
	JComboBox jbxLength=new JComboBox(length);
	JComboBox jbxSquare=new JComboBox();
	JComboBox jbxVolume=new JComboBox();
	JComboBox jbxTime=new JComboBox(time);
	JComboBox jbxRate=new JComboBox();
	JComboBox jbxQuality=new JComboBox();
	JComboBox jbxPressure=new JComboBox();
	JComboBox jbxPower=new JComboBox();
	JComboBox jbxTemperature=new JComboBox(temperature);
	JComboBox jbxHeat=new JComboBox();
	JComboBox jbxAngle=new JComboBox();
	//to
	JComboBox jbxLength1=new JComboBox(length);
	JComboBox jbxSquare1=new JComboBox();
	JComboBox jbxVolume1=new JComboBox();
	JComboBox jbxTime1=new JComboBox(time);
	JComboBox jbxRate1=new JComboBox();
	JComboBox jbxQuality1=new JComboBox();
	JComboBox jbxPressure1=new JComboBox();
	JComboBox jbxPower1=new JComboBox();
	JComboBox jbxTemperature1=new JComboBox(temperature);
	JComboBox jbxHeat1=new JComboBox();
	JComboBox jbxAngle1=new JComboBox();
	
	//输入框  from - to
	JTextField jtfLength=new JTextField(20);
	JTextField jtfLength1=new JTextField(20);
	JTextField jtfSquare=new JTextField(20);
	JTextField jtfSquare1=new JTextField(20);
	JTextField jtfVolume=new JTextField(20);
	JTextField jtfVolume1=new JTextField(20);
	JTextField jtfTime=new JTextField(20);
	JTextField jtfTime1=new JTextField(20);
	JTextField jtfRate=new JTextField(20);
	JTextField jtfRate1=new JTextField(20);
	JTextField jtfQuality=new JTextField(20);
	JTextField jtfQuality1=new JTextField(20);
	JTextField jtfPressure=new JTextField(20);
	JTextField jtfPressure1=new JTextField(20);
	JTextField jtfPower=new JTextField(20);
	JTextField jtfPower1=new JTextField(20);
	JTextField jtfTemperature=new JTextField(20);
	JTextField jtfTemperature1=new JTextField(20);
	JTextField jtfHeat=new JTextField(20);
	JTextField jtfHeat1=new JTextField(20);
	JTextField jtfAngle=new JTextField(20);
	JTextField jtfAngle1=new JTextField(20);
	
	
	CardLayout centerLayout=new CardLayout(5,5);
	JPanel centerPane=new JPanel();
	
	
	
	public Conversion(){
		//设置 JTextField1为不可编辑
		jtfLength1.setEditable(false);
		jtfSquare1.setEditable(false);
		jtfVolume1.setEditable(false);
		jtfTime1.setEditable(false);
		jtfRate1.setEditable(false);
		jtfQuality1.setEditable(false);
		jtfPressure1.setEditable(false);
		jtfPower1.setEditable(false);
		jtfTemperature1.setEditable(false);
		jtfHeat1.setEditable(false);
		jtfAngle1.setEditable(false);
		
		
		//字体设置
		Font myFont=new Font("微软雅黑",1,20);
		setTitle("单位换算");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置各个面板的布局
		boxLength.setLayout(new BoxLayout(boxLength,BoxLayout.Y_AXIS));
		boxSquare.setLayout(new BoxLayout(boxSquare,BoxLayout.Y_AXIS));
		boxVolume.setLayout(new BoxLayout(boxVolume,BoxLayout.Y_AXIS));
		boxTime.setLayout(new BoxLayout(boxTime,BoxLayout.Y_AXIS));
		boxRate.setLayout(new BoxLayout(boxRate,BoxLayout.Y_AXIS));
		boxQuality.setLayout(new BoxLayout(boxQuality,BoxLayout.Y_AXIS));
		boxPressure.setLayout(new BoxLayout(boxPressure,BoxLayout.Y_AXIS));
		boxPower.setLayout(new BoxLayout(boxPower,BoxLayout.Y_AXIS));
		boxTemperature.setLayout(new BoxLayout(boxTemperature,BoxLayout.Y_AXIS));
		boxHeat.setLayout(new BoxLayout(boxHeat,BoxLayout.Y_AXIS));
		boxAngle.setLayout(new BoxLayout(boxAngle,BoxLayout.Y_AXIS));
		
		//在工具栏中添加按钮组件
		toolBar.add(picLength);		
		toolBar.add(picSquare);
		toolBar.add(picVolume);
		toolBar.add(picTime);		
		toolBar.add(picRate);
		toolBar.add(picQuality);
		toolBar.add(picPressure);
		toolBar.add(picPower);
		toolBar.add(picTemperature);
		toolBar.add(picHeat);
		toolBar.add(picAngle);
		//设置热键
		picLength.setMnemonic('L');
		picSquare.setMnemonic('S');
		picVolume.setMnemonic('V');
		picTime.setMnemonic('T');
		picRate.setMnemonic('R');
		picQuality.setMnemonic('M');
		picPressure.setMnemonic('P');
		picPower.setMnemonic('W');
		picTemperature.setMnemonic('K');
		picHeat.setMnemonic('Q');
		picAngle.setMnemonic('A');
		
		
		//length布局
		boxLength.add(new JLabel("From:"));
		boxLength.add(jbxLength);
		boxLength.add(jtfLength);
		boxLength.add(new JLabel("To:"));
		boxLength.add(jbxLength1);
		boxLength.add(jtfLength1);
		//square布局
		boxSquare.add(new JLabel("From:"));
		boxSquare.add(jbxSquare);
		boxSquare.add(jtfSquare);
		boxSquare.add(new JLabel("To:"));
		boxSquare.add(jbxSquare1);
		boxSquare.add(jtfSquare1);
		//volume布局
		boxVolume.add(new JLabel("From:"));
		boxVolume.add(jbxVolume);
		boxVolume.add(jtfVolume);
		boxVolume.add(new JLabel("To:"));
		boxVolume.add(jbxVolume1);
		boxVolume.add(jtfVolume1);
		//time布局
		boxTime.add(new JLabel("From:"));
		boxTime.add(jbxTime);
		boxTime.add(jtfTime);
		boxTime.add(new JLabel("To:"));
		boxTime.add(jbxTime1);
		boxTime.add(jtfTime1);
		//rate布局
		boxRate.add(new JLabel("From:"));
		boxRate.add(jbxRate);
		boxRate.add(jtfRate);
		boxRate.add(new JLabel("To:"));
		boxRate.add(jbxRate1);
		boxRate.add(jtfRate1);
		//Quality布局
		boxQuality.add(new JLabel("From:"));
		boxQuality.add(jbxQuality);
		boxQuality.add(jtfQuality);
		boxQuality.add(new JLabel("To:"));
		boxQuality.add(jbxQuality1);
		boxQuality.add(jtfQuality1);
		//Pressure布局
		boxPressure.add(new JLabel("From:"));
		boxPressure.add(jbxPressure);
		boxPressure.add(jtfPressure);
		boxPressure.add(new JLabel("To:"));
		boxPressure.add(jbxPressure1);
		boxPressure.add(jtfPressure1);
		//Power布局
		boxPower.add(new JLabel("From:"));
		boxPower.add(jbxPower);
		boxPower.add(jtfPower);
		boxPower.add(new JLabel("To:"));
		boxPower.add(jbxPower1);
		boxPower.add(jtfPower1);
		//Temperature布局
		boxTemperature.add(new JLabel("From:"));
		boxTemperature.add(jbxTemperature);
		boxTemperature.add(jtfTemperature);
		boxTemperature.add(new JLabel("To:"));
		boxTemperature.add(jbxTemperature1);
		boxTemperature.add(jtfTemperature1);
		//Heat布局
		boxHeat.add(new JLabel("From:"));
		boxHeat.add(jbxHeat);
		boxHeat.add(jtfHeat);
		boxHeat.add(new JLabel("To:"));
		boxHeat.add(jbxHeat1);
		boxHeat.add(jtfHeat1);
		//Angle布局
		boxAngle.add(new JLabel("From:"));
		boxAngle.add(jbxAngle);
		boxAngle.add(jtfAngle);
		boxAngle.add(new JLabel("To:"));
		boxAngle.add(jbxAngle1);
		boxAngle.add(jtfAngle1);
		
		
		//设置中心区布局
		centerPane.setLayout(centerLayout);
		//向CardLayout 中添加组件时要有名称   组件是Box JPanel都可以
		centerPane.add("Length page",boxLength);
		centerPane.add("Square page",boxSquare);
		centerPane.add("Volume page",boxVolume);
		centerPane.add("Time page",boxTime);
		centerPane.add("Rate page",boxRate);
		centerPane.add("Quality page",boxQuality);
		centerPane.add("Pressure page",boxPressure);
		centerPane.add("Power page",boxPower);
		centerPane.add("Temperature page",boxTemperature);
		centerPane.add("Heat page",boxHeat);
		centerPane.add("Angle page",boxAngle);
		//设置中心区布局over**************	
		
		//关于的设置
		aboutMenu.add(aboutItem);
		jMenuBar.add(aboutMenu);
		setJMenuBar(jMenuBar);
		
		//向内容面板中添加panel组件
		Container contentPane=getContentPane();
		contentPane.add(toolBar,BorderLayout.NORTH);
		contentPane.add(centerPane, BorderLayout.CENTER);
		
		aboutItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ap){
				AboutFrame aboutFrame=new AboutFrame();
				aboutFrame.setBounds(0, 0, 400, 300);
				aboutFrame.setVisible(true);
			}
		});
		
		
		//为工具栏中组件添加监听
		picLength.addActionListener(this);
		picSquare.addActionListener(this);
		picVolume.addActionListener(this);
		picTime.addActionListener(this);
		picRate.addActionListener(this);
		picQuality.addActionListener(this);
		picPressure.addActionListener(this);
		picPower.addActionListener(this);
		picTemperature.addActionListener(this);
		picHeat.addActionListener(this);
		picAngle.addActionListener(this);
				
		//各个面板中下拉框添加监听
		jtfLength.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				indexListener(1);
			}
		});
		jtfLength.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
//				jtfLength.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
//				jtfLength.setText("请选择换算单位后输入");
			}
			
		});
		jbxLength1.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie) {
				if(jtfLength.getText().equals("")){
					jtfLength.setText("0");				
				}
				indexListener(1);
			}			
		});
		jtfSquare.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				indexListener(2);
			}
		});
		jbxSquare1.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie) {
				if(jtfSquare.getText().equals("")){
					jtfSquare.setText("0");				
				}
				indexListener(2);
			}			
		});
		jtfVolume.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				indexListener(3);
			}
		});
		jbxVolume1.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie) {
				if(jtfVolume.getText().equals("")){
					jtfVolume.setText("0");				
				}
				indexListener(3);
			}			
		});
		jtfTime.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){			
				indexListener(4);
			}
		});
		jbxTime1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(jtfTime.getText().equals("")){
					jtfTime.setText("0");				
				}
				indexListener(4);
			}
		});
		jtfRate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){			
				indexListener(5);
			}
		});
		jbxRate1.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie) {
				if(jtfRate.getText().equals("")){
					jtfRate.setText("0");				
				}
				indexListener(5);
			}			
		});
		jtfQuality.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){			
				indexListener(6);
			}
		});
		jbxQuality1.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie) {
				if(jtfQuality.getText().equals("")){
					jtfQuality.setText("0");				
				}
				indexListener(6);
			}			
		});
		jtfPressure.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){			
				indexListener(7);
			}
		});
		jbxPressure1.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie) {
				if(jtfPressure.getText().equals("")){
					jtfPressure.setText("0");				
				}
				indexListener(7);
			}			
		});
		jtfPower.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){			
				indexListener(8);
			}
		});
		jbxPower1.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie) {
				if(jtfPower.getText().equals("")){
					jtfPower.setText("0");				
				}
				indexListener(8);
			}			
		});
		jtfTemperature.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){			
				indexListener(9);
			}
		});
		jbxTemperature1.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie) {
				if(jtfTemperature.getText().equals("")){
					jtfTemperature.setText("0");				
				}
				indexListener(9);
			}			
		});
		jtfHeat.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){			
				indexListener(10);
			}
		});
		jbxHeat1.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie) {
				if(jtfHeat.getText().equals("")){
					jtfHeat.setText("0");				
				}
				indexListener(10);
			}			
		});
		jtfAngle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){			
				indexListener(11);
			}
		});
		jbxAngle1.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie) {
				if(jtfAngle.getText().equals("")){
					jtfAngle.setText("0");				
				}
				indexListener(11);
			}			
		});
		
	}
	//换算关系的方法
	public void indexListener(int index){
		switch (index){
		case 1://length
			int cb10=jbxLength.getSelectedIndex();
			int cb11=jbxLength1.getSelectedIndex();
			Double temDouble1;
		/*	if(jtfLength.getText().trim()==null){
				temDouble1=0.0;
			}
			else{
				temDouble1=Double.valueOf(jtfLength.getText().trim());
			}*/
			temDouble1=Double.valueOf(jtfLength.getText().trim());
			if(cb10==cb11){}
			//************
			else if(cb10==0&&cb11==1){temDouble1/=10;}
			else if(cb10==0&&cb11==2){temDouble1/=100;}
			else if(cb10==0&&cb11==3){temDouble1/=100*1000;}
			else if(cb10==0&&cb11==4){temDouble1*=10;}
			else if(cb10==0&&cb11==5){temDouble1*=10*1000;}
			else if(cb10==0&&cb11==6){temDouble1*=10*1000*1000;}
			else if(cb10==0&&cb11==7){temDouble1*=3.2808/100;}
			else if(cb10==0&&cb11==8){temDouble1*=0.3937;}
			else if(cb10==0&&cb11==9){temDouble1*=0.6214/100000;}
			else if(cb10==0&&cb11==10){temDouble1*=5.3996/1000000;}
			//***************
			else if(cb10==1&&cb11==0){temDouble1*=10;}
			else if(cb10==1&&cb11==2){temDouble1/=10;}
			else if(cb10==1&&cb11==3){temDouble1/=10*1000;}
			else if(cb10==1&&cb11==4){temDouble1*=100;}
			else if(cb10==1&&cb11==5){temDouble1*=100*1000;}
			else if(cb10==1&&cb11==6){temDouble1*=100*1000*1000;}
			else if(cb10==1&&cb11==7){temDouble1*=0.32808;}
			else if(cb10==1&&cb11==8){temDouble1*=3.937;}
			else if(cb10==1&&cb11==9){temDouble1*=0.6214/10000;}
			else if(cb10==1&&cb11==10){temDouble1*=5.3996/100000;}
			//************
			else if(cb10==2&&cb11==0){temDouble1*=100;}
			else if(cb10==2&&cb11==1){temDouble1*=10;}
			else if(cb10==2&&cb11==3){temDouble1/=1000;}
			else if(cb10==2&&cb11==4){temDouble1*=1000;}
			else if(cb10==2&&cb11==5){temDouble1*=1000*1000;}
			else if(cb10==2&&cb11==6){temDouble1*=1000*1000*1000;}
			else if(cb10==2&&cb11==7){temDouble1*=3.2808;}
			else if(cb10==2&&cb11==8){temDouble1*=0.3937/100;}
			else if(cb10==2&&cb11==9){temDouble1*=6.214/10000;}
			else if(cb10==2&&cb11==10){temDouble1/=1852;}
			//********************
			else if(cb10==3&&cb11==0){temDouble1*=100*1000;}
			else if(cb10==3&&cb11==1){temDouble1*=10*1000;}
			else if(cb10==3&&cb11==2){temDouble1*=1000;}
			else if(cb10==3&&cb11==4){temDouble1*=1000*1000;}
			else if(cb10==3&&cb11==5){temDouble1*=1000*1000*1000;}
			else if(cb10==3&&cb11==6){temDouble1*=1000*1000*1000*1000;}
			else if(cb10==3&&cb11==7){temDouble1*=3281;}
			else if(cb10==3&&cb11==8){temDouble1*=39370;}
			else if(cb10==3&&cb11==9){temDouble1*=0.621;}
			else if(cb10==3&&cb11==10){temDouble1/=1.852;}
			//***********************
			else if(cb10==4&&cb11==0){temDouble1/=10;}
			else if(cb10==4&&cb11==1){temDouble1/=100;}
			else if(cb10==4&&cb11==2){temDouble1/=1000;}
			else if(cb10==4&&cb11==3){temDouble1/=1000*1000;}
			else if(cb10==4&&cb11==5){temDouble1*=1000;}
			else if(cb10==4&&cb11==6){temDouble1*=1000*1000;}
			else if(cb10==4&&cb11==7){temDouble1*=3.2808/1000;}
			else if(cb10==4&&cb11==8){temDouble1*=3.937/100;}
			else if(cb10==4&&cb11==9){temDouble1*=6.2137e-7;}
			else if(cb10==4&&cb11==10){temDouble1*=5.3996e-7;}
			//************************
			else if(cb10==5&&cb11==0){temDouble1/=10*1000;}
			else if(cb10==5&&cb11==1){temDouble1/=100*1000;}
			else if(cb10==5&&cb11==2){temDouble1/=1000*1000;}
			else if(cb10==5&&cb11==3){temDouble1/=1000*1000*1000;}
			else if(cb10==5&&cb11==4){temDouble1/=1000;}
			else if(cb10==5&&cb11==6){temDouble1*=1000;}
			else if(cb10==5&&cb11==7){temDouble1*=3.281e-6;}
			else if(cb10==5&&cb11==8){temDouble1*=3.937e-5;}
			else if(cb10==5&&cb11==9){temDouble1*=6.214e-10;}
			else if(cb10==5&&cb11==10){temDouble1*=5.3996e-10;}
			//****************************
			else if(cb10==6&&cb11==0){temDouble1/=10*1000*1000;}
			else if(cb10==6&&cb11==1){temDouble1/=100*1000*1000;}
			else if(cb10==6&&cb11==2){temDouble1/=1000*1000*1000;}
			else if(cb10==6&&cb11==3){temDouble1/=1000*1000*1000*1000;}
			else if(cb10==6&&cb11==4){temDouble1/=1000*1000;}
			else if(cb10==6&&cb11==5){temDouble1/=1000;}
			else if(cb10==6&&cb11==7){temDouble1*=3.281e-9;}
			else if(cb10==6&&cb11==8){temDouble1*=3.937e-8;}
			else if(cb10==6&&cb11==9){temDouble1*=6.214e-13;}
			else if(cb10==6&&cb11==10){temDouble1*=5.3996e-13;}
			//************************
			else if(cb10==7&&cb11==0){temDouble1*=30.48;}
			else if(cb10==7&&cb11==1){temDouble1*=30.48/10;}
			else if(cb10==7&&cb11==2){temDouble1*=30.48/100;}
			else if(cb10==7&&cb11==3){temDouble1*=30.48/100*1000;}
			else if(cb10==7&&cb11==4){temDouble1*=304.8;}
			else if(cb10==7&&cb11==5){temDouble1*=304.8*1000;}
			else if(cb10==7&&cb11==6){temDouble1*=304.8*1000*1000;}
			else if(cb10==7&&cb11==8){temDouble1*=12;}
			else if(cb10==7&&cb11==9){temDouble1*=1.894e-4;}
			else if(cb10==7&&cb11==10){temDouble1*=1.646e-4;}
			//***************************
			else if(cb10==8&&cb11==0){temDouble1*=2.54;}
			else if(cb10==8&&cb11==1){temDouble1*=2.54/10;}
			else if(cb10==8&&cb11==2){temDouble1*=2.54/100;}
			else if(cb10==8&&cb11==3){temDouble1*=2.54/100*1000;}
			else if(cb10==8&&cb11==4){temDouble1*=25.4;}
			else if(cb10==8&&cb11==5){temDouble1*=25.4*1000;}
			else if(cb10==8&&cb11==6){temDouble1*=25.4*1000*1000;}
			else if(cb10==8&&cb11==7){temDouble1/=12;}
			else if(cb10==8&&cb11==9){temDouble1/=63360;}
			else if(cb10==8&&cb11==10){temDouble1*=1.371e-5;}
			//**************************
			else if(cb10==9&&cb11==0){temDouble1*=160934.4;}
			else if(cb10==9&&cb11==1){temDouble1*=160934.4/10;}
			else if(cb10==9&&cb11==2){temDouble1*=160934.4/100;}
			else if(cb10==9&&cb11==3){temDouble1*=160934.4/100*1000;}
			else if(cb10==9&&cb11==4){temDouble1*=1609344;}
			else if(cb10==9&&cb11==5){temDouble1*=1609344*1000;}
			else if(cb10==9&&cb11==6){temDouble1*=1609344*1000*1000;}
			else if(cb10==9&&cb11==7){temDouble1*=5280;}
			else if(cb10==9&&cb11==8){temDouble1*=5280*12;}
			else if(cb10==9&&cb11==10){temDouble1*=0.869;}
			//*******************************
			else if(cb10==10&&cb11==0){temDouble1*=185200;}
			else if(cb10==10&&cb11==1){temDouble1*=18520;}
			else if(cb10==10&&cb11==2){temDouble1*=1852;}
			else if(cb10==10&&cb11==3){temDouble1*=1.852;}
			else if(cb10==10&&cb11==4){temDouble1*=1852*1000;}
			else if(cb10==10&&cb11==5){temDouble1*=1852*1000*1000;}
			else if(cb10==10&&cb11==6){temDouble1*=1852*1000*1000*1000;}
			else if(cb10==10&&cb11==7){temDouble1*=6076.12;}
			else if(cb10==10&&cb11==8){temDouble1*=72913.39;}
			else if(cb10==10&&cb11==9){temDouble1*=1.1508;}				
			jtfLength1.setText(temDouble1.toString());
			break;
			//****************length  over***********
		case 2://square
			int cb20=jbxSquare.getSelectedIndex();
			int cb21=jbxSquare1.getSelectedIndex();
			Double temDouble2;
			temDouble2=Double.valueOf(jtfSquare.getText().trim());
			if((cb20==cb21)){}
			//*****************
			
			
			jtfSquare1.setText(temDouble2.toString());	
			
			break;
			//*************square over************
			
		case 3://volume
			int cb30=jbxVolume.getSelectedIndex();
			int cb31=jbxVolume1.getSelectedIndex();
			Double temDouble3;
			temDouble3=Double.valueOf(jtfVolume.getText().trim());
			if((cb30==cb31)){}
			//*****************
			
			
			jtfVolume1.setText(temDouble3.toString());		
			break;
			//****************volume over ***************						
		case 4://time
			int cb=jbxTime.getSelectedIndex();
			int cb1=jbxTime1.getSelectedIndex();
			Double temDouble;
		/*	if(jtfTime.getText().trim()==null){
				temDouble=0.0;
			}
			else{
				temDouble=Double.valueOf(jtfTime.getText().trim());	
			}*/
			temDouble=Double.valueOf(jtfTime.getText().trim());	
			if(cb==cb1){}
			//************
			else if(cb==0&&cb1==1){temDouble=temDouble/1000;}
			else if(cb==0&&cb1==2){temDouble=temDouble/(1000*60);}
			else if(cb==0&&cb1==3){temDouble=temDouble/(1000*60*60);}
			else if(cb==0&&cb1==4){temDouble=temDouble/(1000*60*60*24);}
			else if(cb==0&&cb1==5){temDouble=temDouble/(1000*60*60*24*7);}
			else if(cb==0&&cb1==6){temDouble=temDouble*1000;}
			//************
			else if(cb==1&&cb1==0){temDouble*=1000;}
			else if(cb==1&&cb1==2){temDouble/=60;}
			else if(cb==1&&cb1==3){temDouble/=60*60;}
			else if(cb==1&&cb1==4){temDouble/=60*60*24;}
			else if(cb==1&&cb1==5){temDouble/=60*60*24*7;}
			else if(cb==1&&cb1==6){temDouble*=1000*1000;}
			//****************
			else if(cb==2&&cb1==0){temDouble*=60*1000;}
			else if(cb==2&&cb1==1){temDouble*=60;}
			else if(cb==2&&cb1==3){temDouble/=60;}
			else if(cb==2&&cb1==4){temDouble/=60*24;}
			else if(cb==2&&cb1==5){temDouble/=60*24*7;}
			else if(cb==2&&cb1==6){temDouble*=60*1000*1000;}
			//*****************
			else if(cb==3&&cb1==0){temDouble*=60*60*1000;}
			else if(cb==3&&cb1==1){temDouble*=60*60;}
			else if(cb==3&&cb1==2){temDouble*=60;}
			else if(cb==3&&cb1==4){temDouble/=24;}
			else if(cb==3&&cb1==4){temDouble/=24;}
			else if(cb==3&&cb1==5){temDouble/=24*7;}
			else if(cb==3&&cb1==6){temDouble*=60*60*1000*1000;}
			//*****************
			else if(cb==4&&cb1==0){temDouble*=24*60*60*1000;}
			else if(cb==4&&cb1==1){temDouble*=24*60*60;}
			else if(cb==4&&cb1==2){temDouble*=24*60;}
			else if(cb==4&&cb1==3){temDouble*=24;}
			else if(cb==4&&cb1==5){temDouble/=7;}
			else if(cb==4&&cb1==6){temDouble*=24*60*60*1000*1000;}
			//************
			else if(cb==5&&cb1==0){temDouble*=7*24*60*60*1000;}
			else if(cb==5&&cb1==1){temDouble*=7*24*60*60;}
			else if(cb==5&&cb1==2){temDouble*=7*24*60;}
			else if(cb==5&&cb1==3){temDouble*=7*24;}
			else if(cb==5&&cb1==4){temDouble*=7;}
			else if(cb==5&&cb1==6){temDouble*=7*24*60*60*1000*1000;}
			//*******************
			else if(cb==6&&cb1==0){temDouble/=1000;}
			else if(cb==6&&cb1==1){temDouble/=1000*1000;}
			else if(cb==6&&cb1==2){temDouble/=1000*1000*60;}
			else if(cb==6&&cb1==3){temDouble/=1000*1000*60*60;}
			else if(cb==6&&cb1==4){temDouble/=1000*1000*60*60*24;}
			else if(cb==6&&cb1==5){temDouble/=1000*1000*60*60*24*7;}
			
			
			
			jtfTime1.setText(temDouble.toString());
			break;
			//**********time  over*************
			
		case 5://rate
			int cb50=jbxRate.getSelectedIndex();
			int cb51=jbxRate1.getSelectedIndex();
			Double temDouble5;
			temDouble5=Double.valueOf(jtfRate.getText().trim());
			if((cb50==cb51)){}
			//*****************
			
			
			jtfRate1.setText(temDouble5.toString());
			
			break;
			//************rate over*************
		case 6://quality
			int cb60=jbxQuality.getSelectedIndex();
			int cb61=jbxQuality1.getSelectedIndex();
			Double temDouble6;
			temDouble6=Double.valueOf(jtfQuality.getText().trim());
			if((cb60==cb61)){}
			//*****************
			
			
			jtfQuality1.setText(temDouble6.toString());
			break;
			//*****************quality over***********
		case 7: //pressure
			int cb70=jbxPressure.getSelectedIndex();
			int cb71=jbxPressure1.getSelectedIndex();
			Double temDouble7;
			temDouble7=Double.valueOf(jtfPressure.getText().trim());
			if((cb70==cb71)){}
			//*****************
			
			
			jtfPressure1.setText(temDouble7.toString());
			break;
			//************pressure over***********
		case 8://power 
			int cb80=jbxPower.getSelectedIndex();
			int cb81=jbxPower1.getSelectedIndex();
			Double temDouble8;
			temDouble8=Double.valueOf(jtfPower.getText().trim());
			if((cb80==cb81)){}
			//*****************
			
			
			jtfPower1.setText(temDouble8.toString());
			break;
			//************power over **********
		case 9: //temperature
			int cb90=jbxTemperature.getSelectedIndex();
			int cb91=jbxTemperature1.getSelectedIndex();
			Double temDouble9;
			temDouble9=Double.valueOf(jtfTemperature.getText().trim());
			if((cb90==cb91)){}
			//************
			else if(cb90==0&&cb91==1){ temDouble9=(temDouble9*9/5)+32;}
			else if(cb90==0&&cb91==2){temDouble9+=273.16;}
			//**************
			else if(cb90==1&&cb91==0){temDouble9=(temDouble9-32)*5/9;}//C＝(F－32)×5／9
			else if(cb90==1&&cb91==2){temDouble9=(temDouble9-32)*5/9+273.16;}//
			//******************
			else if(cb90==2&&cb91==0){temDouble9-=273.16;}
			else if(cb90==2&&cb91==1){temDouble9=(temDouble9-273.16)*9/5+32;}//K ->℉
			
			jtfTemperature1.setText(temDouble9.toString());
			break;
			//*****************temperature over ***********
		case 10://heat
			int cb100=jbxHeat.getSelectedIndex();
			int cb101=jbxHeat1.getSelectedIndex();
			Double temDouble10;
			temDouble10=Double.valueOf(jtfHeat.getText().trim());
			if((cb100==cb101)){}
			//*****************
			
			
			jtfHeat1.setText(temDouble10.toString());		
			break;
			//*************heat over*************
		case 11://angle
			int cb110=jbxAngle.getSelectedIndex();
			int cb111=jbxAngle1.getSelectedIndex();
			Double temDouble11;
			temDouble11=Double.valueOf(jtfAngle.getText().trim());
			if((cb110==cb111)){}
			//*****************
			
			
			jtfAngle1.setText(temDouble11.toString());
			
			break;
			//*******************angle over*****************
		default:
			break;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		/* *****工具栏中的按钮监听*******/
		if(e.getSource()==picLength){
			centerLayout.first(centerPane);
		}
		else if(e.getSource()==picSquare){
			centerLayout.first(centerPane);
			centerLayout.next(centerPane);			
		}
		else if(e.getSource()==picVolume){
			centerLayout.first(centerPane);
			centerLayout.next(centerPane);
			centerLayout.next(centerPane);
		}
		else if(e.getSource()==picTime){
			centerLayout.first(centerPane);
			centerLayout.next(centerPane);
			centerLayout.next(centerPane);
			centerLayout.next(centerPane);
		}
		else if(e.getSource()==picRate){
			centerLayout.first(centerPane);
			centerLayout.next(centerPane);
			centerLayout.next(centerPane);
			centerLayout.next(centerPane);
			centerLayout.next(centerPane);
		}
		else if(e.getSource()==picQuality){
			centerLayout.first(centerPane);
			centerLayout.next(centerPane);
			centerLayout.next(centerPane);
			centerLayout.next(centerPane);
			centerLayout.next(centerPane);
			centerLayout.next(centerPane);
		}
		else if(e.getSource()==picPressure){
			centerLayout.first(centerPane);//1
			centerLayout.next(centerPane);//2
			centerLayout.next(centerPane);//3
			centerLayout.next(centerPane);//4
			centerLayout.next(centerPane);//5
			centerLayout.next(centerPane);//6
			centerLayout.next(centerPane);//7
		}
		else if(e.getSource()==picPower){
			centerLayout.first(centerPane);//1
			centerLayout.next(centerPane);//2
			centerLayout.next(centerPane);//3
			centerLayout.next(centerPane);//4
			centerLayout.next(centerPane);//5
			centerLayout.next(centerPane);//6
			centerLayout.next(centerPane);//7
			centerLayout.next(centerPane);//8
		}
		else if(e.getSource()==picTemperature){
			centerLayout.first(centerPane);//1
			centerLayout.next(centerPane);//2
			centerLayout.next(centerPane);//3
			centerLayout.next(centerPane);//4
			centerLayout.next(centerPane);//5
			centerLayout.next(centerPane);//6
			centerLayout.next(centerPane);//7
			centerLayout.next(centerPane);//8
			centerLayout.next(centerPane);//9
		}
		else if(e.getSource()==picHeat){
			centerLayout.first(centerPane);//1
			centerLayout.next(centerPane);//2
			centerLayout.next(centerPane);//3
			centerLayout.next(centerPane);//4
			centerLayout.next(centerPane);//5
			centerLayout.next(centerPane);//6
			centerLayout.next(centerPane);//7
			centerLayout.next(centerPane);//8
			centerLayout.next(centerPane);//9
			centerLayout.next(centerPane);//10
		}
		else if(e.getSource()==picAngle){
			centerLayout.last(centerPane);//11			
		}
		/* *****工具栏中的按钮监听  over*******/
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}
		catch(Exception e){			
		}
		Conversion conversion=new Conversion();
		conversion.pack();
		conversion.setVisible(true);
		
	}
}
