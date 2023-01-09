package DTO;

public class L2FileHeaderofGMS {
    private String satelliteName;  /*卫星名*/
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int channel; /*通道号：
                                 ＝1：红外通道（10.3-11.3）
                                 ＝2：水汽通道 (6.3-7.6)
                                 ＝3：红外分裂窗通道 (11.5-12.5)
                                 ＝4：可见光通道 (0.5-0.9)
                                 ＝5：中红外通道（3.5-4.0）
                                 ＝6－100：备用
                                 */
    private int projectionMode; /*投影方式：
                                         ＝0：未投影（卫星投影）
                                         ＝1：兰勃托投影
                                         ＝2：麦卡托投影
                                         ＝3：极射投影
                                         ＝4：等经纬度投影
                                         ＝5：等面积投影
                                         */
    private int imageWidth; /*图像宽度*/
    private int imageHeight; /*图像高度*/
    private int imageLeftUpScanLineNum; /*图像左上角扫描线号,当投影方式为 0 时，这两项内容有效。表示了原始的未经投影的图象产品的起始坐标，需说明的是，坐标值是以
红外通道为基准的，对于可见光通道图象，须乘以 4 方为实际的起始坐标。*/
    private int imageLeftUpPixelNum; /*图像左上角象元号,当投影方式为 0 时，这两项内容有效。表示了原始的未经投影的图象产品的起始坐标，需说明的是，坐标值是以
红外通道为基准的，对于可见光通道图象，须乘以 4 方为实际的起始坐标。*/
    private int sampleRate; /*抽样率：指未投影图象相对于原始卫星图象的抽样率。对投影图象，该项无意义。*/
    private int northLatOfGeoRange; /*地理范围（北纬）,单位为度。纬度范围 –90o～ +90o（北纬为正），经度范围 –180o ～ +180o（东经为正）*/
    private int southLatOfGeoRange;  /*地理范围（南纬） */
    private int westLongOfGeoRange; /*地理范围（西经） */
    private int eastLongOfGeoRange; /*地理范围（东经） */
    private double projectionCenterLat; /*投影中心纬度：度*100*/
    private double projectionCenterLong; /*投影中心纬度：度*100*/
    private double projectionStandard1LatLong; /*投影标准纬度 1（或标准经度）：度*100*/
    private double projectionStandard2LatLong; /*标准投影纬度 2：度*100*/
    private double projectionHorRate;  /*投影水平分辨率 ：公里*100*/
    private double projectionVerRage;  /*投影垂直分辨率 ：公里*100*/
    private int geoGridOverLay;  /*地理网格叠加标志：
                                                 ＝0：未叠加地理网格
                                                 ＝1：叠加了地理网格
                                                 */
    private int geoGridOverLayValue; /*地理网格叠加值，图象上已叠加地理网格时，该项内容表示所叠加的地理网格的图象灰度值。*/
    private int colorMixDataLength; /*调色表数据块长度 ，为 0 则表示不含调色表     */
    private int scalerDataLength; /*定标数据块长度 ，为 0 则表示无定标数据块*/
    private int locationDataLength; /*定位数据块长度 ，为 0 则表示无定位数据块*/
    private int reserve; /*保留*/

    public L2FileHeaderofGMS(byte[] data){
        byte[] dataSatelliteName = new byte[8];
        for (int i = 0; i < 8; i++) {
            dataSatelliteName[i] = data[i];
        }
        this.satelliteName = new String(dataSatelliteName);

        this.year = (data[8] & 0xff) + (data[9]  & 0xff)* 256;
        this.month = (data[10] & 0xff) + (data[11] & 0xff) * 256;
        this.day = (data[12] & 0xff) + (data[13] & 0xff) * 256;
        this.hour = (data[14] & 0xff) + (data[15] & 0xff) * 256;
        this.minute = (data[16] & 0xff) + (data[17] & 0xff) * 256;
        this.channel = data[18] & 0xff;
        this.projectionMode = data[20] & 0xff;
        this.imageWidth = (data[22] & 0xff) + (data[23] & 0xff) * 256;
        this.imageHeight = (data[24] & 0xff) + (data[25] & 0xff) * 256;
        this.imageLeftUpScanLineNum = (data[26] & 0xff) + (data[27] & 0xff) * 256;
        this.imageLeftUpPixelNum = (data[28] & 0xff) + (data[29] & 0xff) * 256;
        this.sampleRate = (data[30] & 0xff) + (data[31] & 0xff) * 256;
        this.northLatOfGeoRange = ((data[32] & 0xff) + (data[33] & 0xff) * 256);
        this.southLatOfGeoRange = ((data[34] & 0xff) + (data[35] & 0xff) * 256);
        this.westLongOfGeoRange = ((data[36] & 0xff) + (data[37] & 0xff) * 256);
        this.eastLongOfGeoRange = ((data[38] & 0xff) + (data[39] & 0xff) * 256);
        this.projectionCenterLat = ((data[40] & 0xff) + (data[41] & 0xff) * 256) / 100.0;
        this.projectionCenterLong = ((data[42] & 0xff) + (data[43] & 0xff) * 256) / 100.0;
        this.projectionStandard1LatLong = ((data[44] & 0xff) + (data[45] & 0xff) * 256) / 100.0;
        this.projectionStandard2LatLong = ((data[46] & 0xff) + (data[47] & 0xff) * 256) / 100.0;
        this.projectionHorRate = ((data[48] & 0xff)+ (data[49] & 0xff) * 256) / 100.0;
        this.projectionVerRage = ((data[50] & 0xff) + (data[51] & 0xff) * 256) / 100.0;
        this.geoGridOverLay = data[52] & 0xff;
        this.geoGridOverLayValue = ((data[54] & 0xff) + (data[55] & 0xff) * 256);
        this.colorMixDataLength = ((data[56] & 0xff) + (data[57] & 0xff) * 256);
        this.scalerDataLength = ((data[58] & 0xff) + (data[59] & 0xff) * 256);
        this.locationDataLength = ((data[60] & 0xff) + (data[61]  & 0xff)* 256);
        this.reserve = ((data[62] & 0xff) + (data[63] & 0xff) * 256);
    }

    public int getScalerDataLength(){
        return scalerDataLength;
    }

    public int getNorthLatOfGeoRange(){
        return northLatOfGeoRange;
    }

    public int getWestLongOfGeoRange(){
        return westLongOfGeoRange;
    }

    public int getSouthLatOfGeoRange(){
        return southLatOfGeoRange;
    }

    public int getEastLongOfGeoRange(){
        return eastLongOfGeoRange;
    }
}
