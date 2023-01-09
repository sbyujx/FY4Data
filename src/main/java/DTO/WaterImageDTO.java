package DTO;

import lombok.Setter;
import lombok.Getter;

import java.awt.*;

/*水印图片元素的模板*/
@Setter
@Getter
public class WaterImageDTO {
    //文字内容
    private String text;
    //字体衍射和透明度
    private Color color;
    //字体和大小
    private Font font;
    //所在图片的x坐标
    private int x;
    //所在图片的y坐标
    private int y;

}
