import com.madgag.gif.fmsware.AnimatedGifEncoder;
import org.dom4j.DocumentException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws IOException, DocumentException {
/*
        String filePath = new String("D:\\work\\MCS2022\\数据\\数据样例\\FY4A\\C013_20220424013000_FY4A.AWX");
        FileReader.FY4DataC013 fy4DataC013 = new FileReader.FY4DataC013(filePath);
        DTO.C013Point[] c013Points = fy4DataC013.getSiChuanC013Points();
        int siChuanLatPointNums = fy4DataC013.getSiChuanLatPointNums();
        int siChuanLongPointNums = fy4DataC013.getSiChuanLongPointNums();
        String xmlPath = new String("D:\\work\\MCS2022\\数据\\C013调色板.xml");
        Palette.C013Palette c013Palette = new Palette.C013Palette(xmlPath);


        BufferedImage image = new BufferedImage(siChuanLongPointNums, siChuanLatPointNums, BufferedImage.TYPE_INT_RGB);
        int x = 0;
        int y = 0;
        for (DTO.C013Point c013Point :
                c013Points) {
            int rgb = c013Palette.findRgbaInt(c013Point.getBriTemp());
            image.setRGB(x, y , rgb);
            x++;
            if(x >= siChuanLongPointNums){
                x = 0;
                y++;
            }
        }
        ImageIO.write(image, "png", new File("D:\\work\\MCS2022\\数据\\C013_image\\C013_20220424013000_FY4A.png"));
 */

        /*生成水印
        ArrayList<DTO.WaterImageDTO> list = new ArrayList<>();
        DTO.WaterImageDTO dto = new DTO.WaterImageDTO();
        dto.setText("C013_20220424013000_FY4A");
        dto.setColor(new Color(0,0,0));
        dto.setX(20);
        dto.setY(20);
        dto.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        list.add(dto);

        String srcImgPath = "D:\\work\\MCS2022\\数据\\C013_image\\C013_20220424013000_FY4A.png";
        String tarImgPath = "D:\\work\\MCS2022\\数据\\C013_image\\C013_20220424013000_FY4A_Water.png";
        utils.WaterMarkUtils.writeWaterImage(srcImgPath, tarImgPath, list);
*/
        /*实现GIF的生成*/
        BufferedImage image1 = ImageIO.read(new File("D:\\work\\MCS2022\\数据\\C013_image\\C013_20220424004918_FY4A_Water.png"));
        BufferedImage image2 = ImageIO.read(new File("D:\\work\\MCS2022\\数据\\C013_image\\C013_20220424005336_FY4A_Water.png"));
        BufferedImage image3 = ImageIO.read(new File("D:\\work\\MCS2022\\数据\\C013_image\\C013_20220424010000_FY4A_Water.png"));
        BufferedImage image4 = ImageIO.read(new File("D:\\work\\MCS2022\\数据\\C013_image\\C013_20220424013000_FY4A_Water.png"));

        AnimatedGifEncoder e = new AnimatedGifEncoder();
        //设置生成图片大小
        //e.setSize(1000, 1000);
        //生成的图片路径
        e.start(new FileOutputStream(("D:\\work\\MCS2022\\数据\\C013_image\\C013_Water.gif")));
        //图片之间的间隔时间
        e.setDelay(1000);
        //重复次数 0表示无限重复 默认不重复
        e.setRepeat(0);
        //添加图片
        e.addFrame(image1);
        e.addFrame(image2);
        e.addFrame(image3);
        e.addFrame(image4);
        e.finish();


//        JSONArray records = new JSONArray();
//
//        for (DTO.C013Point c013Point :
//                c013Points) {
//            JSONArray coordinateLeftUp = new JSONArray();
//            coordinateLeftUp.add(c013Point.getLon() + 0.02);
//            coordinateLeftUp.add(c013Point.getLat() - 0.02);
//
//            JSONArray coordinateLeftDown = new JSONArray();
//            coordinateLeftDown.add(c013Point.getLon() - 0.02);
//            coordinateLeftDown.add(c013Point.getLat() - 0.02);
//
//            JSONArray coordinateRightDown = new JSONArray();
//            coordinateRightDown.add(c013Point.getLon() - 0.02);
//            coordinateRightDown.add(c013Point.getLat() + 0.02);
//
//            JSONArray coordinateRightUp = new JSONArray();
//            coordinateRightUp.add(c013Point.getLon() + 0.02);
//            coordinateRightUp.add(c013Point.getLat() + 0.02);
//
//            JSONArray coordArray = new JSONArray();
//            coordArray.add(coordinateLeftUp);
//            coordArray.add(coordinateLeftDown);
//            coordArray.add(coordinateRightDown);
//            coordArray.add(coordinateRightUp);
//
//            JSONObject geo = new JSONObject();
//            geo.put("coordinates", coordArray);
//            geo.put("type", "Polygon");
//
//            JSONObject feature = new JSONObject();
//            feature.put("type", "Feature");
//            feature.put("geometry", geo);
//            JSONObject color = new JSONObject();
//            color.put("color", c013Palette.findRgba(c013Point.getBriTemp()));
//            feature.put("properties", color);
//
//            records.add(feature);
//        }
//
//        JSONObject js = new JSONObject();
//        js.put("features", records);
//        js.put("type", "FeatureCollection");
//
//        try (FileWriter file = new FileWriter("D:\\work\\MCS2022\\数据\\testSC.json")) {
//            file.write(js.toJSONString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}
