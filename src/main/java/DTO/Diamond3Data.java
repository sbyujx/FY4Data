package DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wenxinfeng
 * @descripton Diamond3文件数据的单条数据
 * 数据：
 * 区站号（长整数）经度  纬度  拔海高度（均为浮点数） 站点值1 站点值2   （均为字符串）
 * @version 1.0
 */

@Setter
@Getter
public class Diamond3Data {
    private long stationNum;
    private LatLon latLon;
    private double altitude;
    private double stationValue1;
    private double stationValue2;
}
