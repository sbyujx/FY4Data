package DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author wenxinfeng
 * @descripton Diamond3文件头描述
 * 文件头：
 * diamond  3  数据说明（字符串）  年  月  日  时次  层次
 * 等值线条数（均为整数）  等值线值1  等值线值2      平滑系数  加粗线值（均为浮点数）
 * 剪切区域边缘线上的点数（整数）边缘线上各点的经度值1 纬度值1 经度值2 纬度值2     （均为浮点数）
 * 单站填图要素的个数  总站点数（均为整数）
 * @version 1.0
 */

@Setter
@Getter
public class Diamond3Header {
    private String dataDescription;  //数据说明（字符串）
    private int year;  //年
    private int month;  //月
    private int day; //日
    private int reportHour; //时次
    private int level;  //层次
    private int numsOfIsoline;  //等值线条数
    private List<Double> isolineValueList;  //等值线数据
    private double smoothingFactor;  //平滑系数
    private double boldLineValue;  //加粗线值
    private int edgeLinePoints; //剪切区域边缘线上的点数
    private List<LatLon> edgeLinePointList; //边缘线上各点的经纬度
    private int elementNums;  //单站填图要素的个数
    private int stationNums; //总站点数
}
