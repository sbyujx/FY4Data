package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import DTO.WaterImageDTO;
import java.util.List;

public class WaterMarkUtils {
    public static void writeWaterImage(String srcImgPath, String targetImgPath, List<WaterImageDTO> list){
        FileOutputStream outImgStream = null;
        try{
            //读取原图片信息
           File srcImgFile = new File(srcImgPath);
           Image srcImg = ImageIO.read(srcImgFile);
           int srcImgWidth = srcImg.getWidth(null);
           int srcImgHeight = srcImg.getHeight(null);
           
           //添加文字
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            for(WaterImageDTO imgDTO : list){
                g.setColor(imgDTO.getColor());
                g.setFont(imgDTO.getFont());
                g.drawString(imgDTO.getText(), imgDTO.getX(), imgDTO.getY());
            }
            
            g.dispose();
            
            //输出图片
            outImgStream = new FileOutputStream(targetImgPath);
            ImageIO.write(bufImg, "png", outImgStream);
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(null != outImgStream){
                    outImgStream.flush();
                    outImgStream.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
