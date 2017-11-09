package com.cn.ssm.controller;

import java.awt.Color;
import java.awt.Font;  
import java.awt.GradientPaint;
import java.awt.Point;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import org.jfree.chart.ChartColor;  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;  
import org.jfree.chart.axis.ValueAxis;  
import org.jfree.chart.plot.CategoryPlot;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialFrame;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialRange;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.chart.plot.dial.DialPointer.Pointer;
import org.jfree.chart.renderer.category.BarRenderer;  
import org.jfree.chart.servlet.ServletUtilities;  
import org.jfree.chart.title.TextTitle;  
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset;  
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.ModelMap;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.servlet.ModelAndView;  
  
@Controller  
public class ChartController {  
      
    @RequestMapping("/resultmap")   
    public String resultmap(){  
    	
        return "/resultmap";  
    }  
      
    //显示柱状图  
    @RequestMapping(value = "/getColumnChart")  
    public ModelAndView getColumnChart(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) throws Exception{  
        //1. 获得数据集合  
        CategoryDataset dataset = getDataSet();  
        //2. 创建柱状图  
        JFreeChart chart = ChartFactory.createBarChart3D("学生对教师授课满意度", // 图表标题  
                "课程名", // 目录轴的显示标签  
                "百分比", // 数值轴的显示标签  
                dataset, // 数据集  
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直  
                false, // 是否显示图例(对于简单的柱状图必须是false)  
                false, // 是否生成工具  
                false // 是否生成URL链接  
                );  
        //3. 设置整个柱状图的颜色和文字（char对象的设置是针对整个图形的设置）  
        chart.setBackgroundPaint(ChartColor.WHITE); // 设置总的背景颜色  
          
        //4. 获得图形对象，并通过此对象对图形的颜色文字进行设置  
        CategoryPlot p = chart.getCategoryPlot();// 获得图表对象  
        p.setBackgroundPaint(ChartColor.lightGray);//图形背景颜色  
        p.setRangeGridlinePaint(ChartColor.WHITE);//图形表格颜色  
          
        //5. 设置柱子宽度  
        BarRenderer renderer = (BarRenderer)p.getRenderer();  
        renderer.setMaximumBarWidth(0.06);  
          
        //解决乱码问题  
        getChartByFont(chart);  
          
        //6. 将图形转换为图片，传到前台  
        String fileName = ServletUtilities.saveChartAsPNG(chart, 700, 400, null, request.getSession());  
        //生成URL，用于请求
        String chartURL=request.getContextPath() + "/DisplayChart?filename="+fileName;  
        
        //饼状图的生成程序
        DefaultPieDataset dpd = new DefaultPieDataset();
        dpd.setValue("管理人员", 25);
        dpd.setValue("市场人员", 25);
        dpd.setValue("开发人员", 45);
        dpd.setValue("其他", 10);
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
       
    JFreeChart chart2 = ChartFactory.createPieChart("某公司组织结构图",dpd, true, false, false);
    chart2.setBackgroundPaint(Color.white);
    String fileName2 = ServletUtilities.saveChartAsPNG(chart2,600,400,request.getSession()); 
    //ServletUtilities是面向web开发的工具类，返回一个字符串文件名,文件名自动生成，生成好的图片会自动放在服务器（tomcat）的临时文件下（temp）
    
    String PieURL = request.getContextPath() + "/DisplayChart?filename=" + fileName2;
    //根据文件名去临时目录下寻找该图片，这里的/DisplayChart路径要与配置文件里用户自定义的<url-pattern>一致
		  //将URL传送到前台，用于在JSP页面中访问
    		modelMap.put("PieURL", PieURL);
		    modelMap.put("chartURL", chartURL);  
			
        //请求URL时，将URL返回到指定的JSP页面
        return new ModelAndView("resultmap",modelMap);  
    }  

  //设置文字样式  
    private static void getChartByFont(JFreeChart chart) {  
        //1. 图形标题文字设置  
        TextTitle textTitle = chart.getTitle();     
        textTitle.setFont(new Font("宋体",Font.BOLD,20));  
          
        //2. 图形X轴坐标文字的设置  
        CategoryPlot plot = (CategoryPlot) chart.getPlot();  
        CategoryAxis axis = plot.getDomainAxis();  
        axis.setLabelFont(new Font("宋体",Font.BOLD,22));  //设置X轴坐标上标题的文字  
        axis.setTickLabelFont(new Font("宋体",Font.BOLD,15));  //设置X轴坐标上的文字  
          
      //2. 图形Y轴坐标文字的设置  
        ValueAxis valueAxis = plot.getRangeAxis();  
        valueAxis.setLabelFont(new Font("宋体",Font.BOLD,15));  //设置Y轴坐标上标题的文字  
        valueAxis.setTickLabelFont(new Font("sans-serif",Font.BOLD,12));//设置Y轴坐标上的文字  
    }  
  
    // 获取一个演示用的组合数据集对象 ;可以使用模拟的数据集，也可以采用数据库返回的数据集，通过控制器将模型的数据传送到视图层
    private static CategoryDataset getDataSet() {  
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
        dataset.addValue(40, "", "普通动物学");  
        dataset.addValue(50, "", "生物学");  
        dataset.addValue(60, "", "动物解剖学");  
        dataset.addValue(70, "", "生物理论课");  
        dataset.addValue(80, "", "动物理论课");  
        return dataset;  
    }  
    
    
    //显示饼状图
    @RequestMapping(value="getPieChart")
    public ModelAndView getPieChart(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) throws Exception{  
	        DefaultPieDataset dpd = new DefaultPieDataset();
	        
	        dpd.setValue("管理人员", 25);
	        dpd.setValue("市场人员", 25);
	        dpd.setValue("开发人员", 45);
	        dpd.setValue("其他", 10);
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
	       
        JFreeChart chart = ChartFactory.createPieChart("某公司组织结构图",dpd, true, false, false);
        chart.setBackgroundPaint(Color.white);
        String fileName = ServletUtilities.saveChartAsPNG(chart,600,400,request.getSession()); 
        //ServletUtilities是面向web开发的工具类，返回一个字符串文件名,文件名自动生成，生成好的图片会自动放在服务器（tomcat）的临时文件下（temp）
        
        String PieURL = request.getContextPath() + "/DisplayChart?filename=" + fileName;
        //根据文件名去临时目录下寻找该图片，这里的/DisplayChart路径要与配置文件里用户自定义的<url-pattern>一致
    	modelMap.put("PieURL", PieURL);
    	return new ModelAndView("resultmap",modelMap); 
    }
    
    //显示仪表图
    @RequestMapping(value="getMeterChart")
    public ModelAndView getMeterChart(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) throws Exception{  
    	   //1,数据集合对象 此处为DefaultValueDataset  
        DefaultValueDataset dataset = new DefaultValueDataset();  
        //  当前指针指向的位置，即：我们需要显示的数据  
        dataset.setValue(new Double(60D));  
        /** 
         *  获取图表区域对象 
         * 
         * A. setDataSet(int index, DataSet dataSet); 
         * 为表盘设定使用的数据集，通常一个表盘上可能存在多个指针， 
         * 因此需要制定该数据集与哪个指针相互关联。 
         * 可以将指针想象成数据集的一种体现方式。 
         */  
        DialPlot dialplot = new DialPlot();  
        dialplot.setView(0.0D, 0.0D, 1.0D, 1.0D);  
        dialplot.setDataset(0,dataset);  
        /** 
         * 开始设置显示框架结构 
         * B. setDailFrame(DailFrame dailFrame);设置表盘的底层面板图像，通常表盘是整个仪表的最底层。 
         */  
        DialFrame dialframe =new StandardDialFrame();  
        dialplot.setDialFrame(dialframe);  
        /** 
         * 结束设置显示框架结构 
         * C. setBackground(Color color);设置表盘的颜色，可以采用Java内置的颜色控制方式来调用该方法。 
         */  
        GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));  
        DialBackground dialbackground = new DialBackground(gradientpaint);  
        dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));  
        dialplot.setBackground(dialbackground);  
        //  设置显示在表盘中央位置的信息  
        DialTextAnnotation dialtextannotation = new DialTextAnnotation("温度");  
        dialtextannotation.setFont(new Font("Dialog", 1, 14));  
        dialtextannotation.setRadius(0.69999999999999996D);  
        dialplot.addLayer(dialtextannotation);  
        DialValueIndicator dialvalueindicator = new DialValueIndicator(0);  
        dialplot.addLayer(dialvalueindicator);  
        //  根据表盘的直径大小（0.75），设置总刻度范围  
        /** 
         * E. addScale(int index, DailScale dailScale); 
         * 用于设定表盘上的量程，index指明该量程属于哪一个指针所指向的数据集， 
         * DailScale指明该量程的样式，如量程的基本单位等信息。 
         * 
         * StandardDialScale(double lowerBound, double upperBound, double startAngle, 
         * double extent, double majorTickIncrement, int minorTickCount) 
         * new StandardDialScale(-40D, 60D, -120D, -300D,30D); 
         */  
        //  
        StandardDialScale standarddialscale =new StandardDialScale();  
        standarddialscale.setLowerBound(0D);  
        standarddialscale.setUpperBound(100D);  
        standarddialscale.setStartAngle(-120D);  
        standarddialscale.setExtent(-300D);  
        standarddialscale.setTickRadius(0.88D);  
        standarddialscale.setTickLabelOffset(0.14999999999999999D);  
        standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));  
        dialplot.addScale(0, standarddialscale);  
        /** 
         * F. addLayer(DailRange dailRange); 
         * 用于设定某一特定量程的特殊表现，通常位于量程之下，如红色范围标注，绿色范围标注等。 
         * 在调用该方法之前需要设定DailRange的一些信息，包括位置信息，颜色信息等等。 
         */  
        //设置刻度范围（红色）  
        StandardDialRange standarddialrange = new StandardDialRange(0D, 60D, Color.red);  
        standarddialrange.setInnerRadius(0.52000000000000002D);  
        standarddialrange.setOuterRadius(0.55000000000000004D);  
        dialplot.addLayer(standarddialrange);  
        //设置刻度范围（橘黄色）             
        StandardDialRange standarddialrange1 = new StandardDialRange(60D, 80D, Color.orange);  
        standarddialrange1.setInnerRadius(0.52000000000000002D);  
        standarddialrange1.setOuterRadius(0.55000000000000004D);  
        dialplot.addLayer(standarddialrange1);  
        //设置刻度范围（绿色）                 
        StandardDialRange standarddialrange2 = new StandardDialRange(80D, 100D, Color.green);  
        standarddialrange2.setInnerRadius(0.52000000000000002D);  
        standarddialrange2.setOuterRadius(0.55000000000000004D);  
        dialplot.addLayer(standarddialrange2);  
          
        /** 
         * 设置指针 
         * G. addPointer(DailPointer dailPointer); 
         * 用于设定表盘使用的指针样式，JFreeChart中有很多可供选择指针样式， 
         * 用户可以根据使用需要，采用不同的DailPoint的实现类来调用该方法 
         */  
        Pointer pointer = new Pointer(); //内部内  
        dialplot.addPointer(pointer); //addLayer(pointer);  
        /** 
         * 实例化DialCap 
         * H. setCap(DailCap dailCap);设定指针上面的盖帽的样式。 
         */  
        DialCap dialcap = new DialCap();  
        dialcap.setRadius(0.10000000000000001D);  
        dialplot.setCap(dialcap);  
        //生成chart对象  
        JFreeChart jfreechart = new JFreeChart(dialplot);  
        //设置标题  
        jfreechart.setTitle("设备取水温度采样");  
        ChartFrame frame = new ChartFrame("CityInfoPort公司组织架构图 ", jfreechart,true);  
        String fileName =ServletUtilities.saveChartAsPNG(jfreechart,600,400, request.getSession());
        String MeterURL = request.getContextPath() + "/DisplayChart?filename=" + fileName;
    	modelMap.put("MeterURL", MeterURL);
    	return new ModelAndView("resultmap",modelMap); 
    }
    
    
    
    
    
   
    
}  