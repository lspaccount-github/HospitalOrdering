package com.cn.ssm.utils;

import java.awt.Color;  
import java.awt.Graphics2D;  
import java.awt.Image;  
import java.awt.RenderingHints;  
import java.awt.image.BufferedImage;  
import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.net.HttpURLConnection;  
import java.net.MalformedURLException;  
import java.net.URL;  
import javax.imageio.ImageIO;  
import org.apache.commons.lang.StringUtils;  
/** 
 * 图片万能操作工具类 
 * <p>Title: ImgUtils</p> 
 * @author  Liyan 
 * @date    2017年4月14日 下午2:59:42 
 */  
public class ImgUtils {  
      
    private static BufferedOutputStream bos;  
      
    /** 
     * 根据图片url下载到本地 
     * <p>Title: downLoadByUrl</p> 
     * @author Liyan 
     * @date   2017年4月14日 下午3:04:36 
     * @param imgUrl 图片地址 
     * @param location 下载后保存的地址。如"C:\\" 
     * @param imgName 下载后保存的名称。如"a.jpg" 
     * @return Boolean true 成功；false 失败 
     */  
    public static Boolean downLoadByUrl(String imgUrl, String location, String imgName) {  
        try {  
            //图片的url  
            if (StringUtils.isEmpty(imgUrl) || StringUtils.isEmpty(location) || StringUtils.isEmpty(imgName)) {  
                return false;  
            }  
            //设置图片下载后的保存位置及图片名称  
            File file = new File(location, imgName);  
            InputStream inputStream = ImgUtils.getInputStreamByGet(imgUrl);  
            ImgUtils.saveData(inputStream, file);  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  
      
    /** 
     * 等比缩放图片(按高度或宽度) 
     * <p>Title: equalRatioResizeImage</p> 
     * @author Liyan 
     * @date   2017年4月14日 下午4:53:33 
     * @param srcImgPath 原始照片的磁盘位置 
     * @param distImgPath 目标照片的磁盘位置 
     * @param width 宽度(单位像素) 注意：和height只能填一个,都填写默认按宽度计算 
     * @param height 高度(单位像素) 注意：和width只能填一个,都填写默认按宽度计算 
     * @return Boolean true 成功；false 失败 
     */  
    public static Boolean equalRatioResizeImage (String srcImgPath, String distImgPath, int newWidth, int newHeight){    
        try {  
            //参数校验  
            if (StringUtils.isEmpty(srcImgPath) || StringUtils.isEmpty(distImgPath)) {  
                return false;  
            }  
            //获取图片原始尺寸  
            Image srcFile = ImageIO.read(new File(srcImgPath));   
            int w = srcFile.getWidth(null);      
            int h = srcFile.getHeight(null);      
            double bili;      
            if(newWidth>0){      
                bili=newWidth/(double)w;      
                newHeight = (int) (h*bili);      
            }else{      
                if(newHeight>0){      
                    bili=newHeight/(double)h;      
                    newWidth = (int) (w*bili);      
                }      
            }   
            return ImgUtils.resizeImage(srcImgPath, distImgPath, newWidth, newHeight);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }    
      
    /** 
     * 压缩图片(指定尺寸) 
     * <p>Title: resizeImage</p> 
     * @author Liyan 
     * @date   2017年4月14日 下午3:31:10 
     * @param srcImgPath 原始照片的磁盘位置 
     * @param distImgPath 目标照片的磁盘位置 
     * @param width 宽度(单位像素) 
     * @param height 高度(单位像素) 
     * @return Boolean true 成功；false 失败  
     */  
    public static Boolean resizeImage (String srcImgPath, String distImgPath, int width, int height){    
        try {  
            String subfix = "jpg";  
            subfix = srcImgPath.substring(srcImgPath.lastIndexOf(".")+1,srcImgPath.length());  
            File srcFile = new File(srcImgPath);    
            Image srcImg = ImageIO.read(srcFile);    
            BufferedImage buffImg = null;   
            if(subfix.equals("png")){  
                buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);  
            }else{  
                buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
            }  
            Graphics2D graphics = buffImg.createGraphics();  
            graphics.setBackground(Color.WHITE);  
            graphics.setColor(Color.WHITE);  
            graphics.fillRect(0, 0, width, height);  
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);  
            Image scaledInstance = srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);  
            graphics.drawImage(scaledInstance, 0, 0, null);    
            ImageIO.write(buffImg, subfix, new File(distImgPath));   
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }   
    }    
      
      
    /** 
     * 通过get请求得到读取器响应数据的数据流 
     * <p>Title: getInputStreamByGet</p> 
     * @author Liyan 
     * @throws IOException  
     * @throws MalformedURLException  
     * @date   2017年4月14日 下午2:54:25 
     */  
    private static InputStream getInputStreamByGet(String url) throws MalformedURLException, IOException {  
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();  
        conn.setReadTimeout(5000);  
        conn.setConnectTimeout(5000);  
        conn.setRequestMethod("GET");  
  
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {  
            InputStream inputStream = conn.getInputStream();  
            return inputStream;  
        }  
        return null;  
    }  
  
    /** 
     * 将服务器响应的数据流存到本地文件 
     * <p>Title: saveData</p> 
     * @author Liyan 
     * @throws IOException  
     * @date   2017年4月14日 下午2:54:44 
     */  
    private static void saveData(InputStream is, File file) throws IOException {  
        BufferedInputStream bis = new BufferedInputStream(is);  
        bos = new BufferedOutputStream(new FileOutputStream(file));  
        byte[] buffer = new byte[1024];  
        int len = -1;  
        while ((len = bis.read(buffer)) != -1) {  
            bos.write(buffer, 0, len);  
            bos.flush();  
        }  
    }  
      
    /** 
     * 测试 
     * <p>Title: main</p> 
     * @author Liyan 
     * @date   2017年4月14日 下午2:54:53 
     */  
    public static void main(String[] args) {  
        //根据图片url下载到本地  
        Boolean b1 = ImgUtils.downLoadByUrl("https://nos.netease.com/nim/MTAzODg1MQ==/bmltYV8yMjI1NDAwOThfMTQ5MDMzNjIwMTU0OV9mZjM0MzUzMC1jZTk0LTRkNDItYjJlZS1lNWFlYWE0MTJjZmU=?imageView&interlace=1&crop=0_0_1512_1653", "C://", "mydog.jpg");  
        if (b1) {  
            System.out.println("测试【根据图片url下载到本地】成功");  
        }  
        //测试指定尺寸压缩图片  
        Boolean b2 = ImgUtils.resizeImage("C:\\mydog.jpg","C:\\compressDog.jpg",302,330);   
        if (b2) {  
            System.out.println("测试【压缩图片】成功");  
        }  
        //测试按宽度等比压缩  
        Boolean b3 = ImgUtils.equalRatioResizeImage("C:\\mydog.jpg","C:\\compressDog2.jpg",151,0);   
        if (b3) {  
            System.out.println("测试【按高度等比压缩】成功");  
        }  
          
    }  
}  