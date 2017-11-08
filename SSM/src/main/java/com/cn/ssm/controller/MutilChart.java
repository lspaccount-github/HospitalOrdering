package com.cn.ssm.controller;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.util.TableOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class MutilChart {
	
	@RequestMapping(value="/getMutilChart")
	public ModelAndView mutilChart(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) throws Exception {
		 
		String checkInType[] = { "出勤", "病假", "事假", "其他" };
		String clazzs[] = { "JSD1208班", "JSD1209班", "JSD1210班", "JSD1211班" };
		String chartTitle = "各班级出勤情况";
		//创建主题样式  
        StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
        //设置标题字体  
        standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));  
        //设置图例的字体  
        standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));  
        //设置轴向的字体  
        standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));  
        //应用主题样式  
        ChartFactory.setChartTheme(standardChartTheme);   
		
		// 创建数据集
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		 
		// 构建数据
		int checkInData;
		for (int i = 0; i < checkInType.length; i++) {
		for (int j = 0; j < clazzs.length; j++) {
		checkInData = 1 + (int) (Math.random() * 1000);
		dataset.addValue(checkInData, checkInType[i], clazzs[j]);
		}
		}
		 
		// 获取JFreeChart对象
		JFreeChart	chart = ChartFactory.createMultiplePieChart(
		 
		chartTitle, // 图表标题
		dataset, // 数据集
		TableOrder.BY_COLUMN, // 指定被提取数据的顺序
		false, // 是否包含图例
		true, // 是否包含提示工具
		false // 是否包含url
		);
		// 获取PiePlot对象
		MultiplePiePlot multiPlot = (MultiplePiePlot) chart.getPlot();
		JFreeChart obj = multiPlot.getPieChart();
		PiePlot plot = (PiePlot) obj.getPlot();
		 
		  //设置标签格式
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {1} ({2})", NumberFormat.getNumberInstance(),
		                new DecimalFormat("0.00%")));
		 
		// 分离圆弧
		for (int i = 0; i < clazzs.length; i++) {
		//plot.setExplodePercent(i, 0.03D);
			plot.setExplodePercent("two", 0.3d);
		}
		 
		ChartRenderingInfo info = new ChartRenderingInfo(
		new StandardEntityCollection());
		 String fileName = ServletUtilities.saveChartAsPNG(chart,1000,800,request.getSession()); 
		  String MutilChart = request.getContextPath() + "/DisplayChart?filename=" + fileName;
		  modelMap.put("MutilChart", MutilChart);
	    return new ModelAndView("resultmap",modelMap); 
		}

}
