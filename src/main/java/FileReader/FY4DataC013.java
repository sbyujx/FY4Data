package FileReader;

import DTO.C013Point;
import DTO.L1FileHeader;
import DTO.L2FileHeaderofGMS;
import DTO.LatLon;
import Palette.ScalerBlockData;

import java.io.*;

public class FY4DataC013 {
    private L1FileHeader l1FileHeader;
    private L2FileHeaderofGMS l2FileHeaderofGMS;
    private ScalerBlockData scalerBlockData;
    private byte[] fileData;

    private C013Point[] c013Points;

    /*95°E~110°E，25°S~35°S*/


    public static final int SC_SOUTH_LAT = 2500;  /*南纬  2500*/
    public static final int SC_NORTH_LAT = 3500;  /*北纬 3500*/
    public static final int SC_EAST_LONG = 11000;  /*东经  11000*/
    public static final int SC_WEST_LONG = 9500;  /*西经  9500*/

    public FY4DataC013(String filePath) throws IOException {
        File file = new File(filePath);
        InputStream in = null;
        in = new FileInputStream(file);
        fileData = new byte[in.available()];
        in.read(fileData);
        in.close();

        byte[] dataL1FileHeader = new byte[40];
        for (int i = 0; i < 40; i++) {
            dataL1FileHeader[i] = fileData[i];
        }
        this.l1FileHeader = new L1FileHeader(dataL1FileHeader);

        byte[] dataL2FileHeaderofGMS = new byte[64];
        for (int i = 0; i < 64; i++) {
            dataL2FileHeaderofGMS[i] = fileData[i + 40];
        }
        this.l2FileHeaderofGMS = new L2FileHeaderofGMS(dataL2FileHeaderofGMS);

        int scalerDataLength = this.l2FileHeaderofGMS.getScalerDataLength();
        if(scalerDataLength != 0){
            byte[] dataScaler = new byte[scalerDataLength];
            for(int i = 0; i < scalerDataLength; i++){
                dataScaler[i] = fileData[i + 104];
            }
            this.scalerBlockData = new ScalerBlockData(dataScaler, scalerDataLength);
        }
    }

    public C013Point[] getC013Points() {
        int productByteLength = this.l1FileHeader.getProductByteLength();
        this.c013Points = new C013Point[productByteLength];
        int headerByteLength = this.l1FileHeader.GetFileHeaderByteLength();

        int startLat = this.l2FileHeaderofGMS.getNorthLatOfGeoRange();
        int startLong = this.l2FileHeaderofGMS.getWestLongOfGeoRange();
        int endLat = this.l2FileHeaderofGMS.getSouthLatOfGeoRange();
        int endLong = this.l2FileHeaderofGMS.getEastLongOfGeoRange();
        int lat = startLat;
        int lon = startLong;
        for(int i = 0; i < productByteLength; i++){
            int grayData = this.fileData[headerByteLength + i] & 0xff;
            int briTemp = this.scalerBlockData.getBriTem(grayData-1);
            c013Points[i] = new C013Point(new LatLon(lat / 100.0, lon / 100.0), grayData, briTemp);
            lon += 4;
            if(lon > endLong){
                lon = startLong;
                lat -= 4;
            }
        }
        return c013Points;
    }

    public C013Point[] getSiChuanC013Points(){
        int productByteLength = this.l1FileHeader.getProductByteLength();
        int siChuanByteLength = ((SC_NORTH_LAT - SC_SOUTH_LAT) / 4 + 1) * ((SC_EAST_LONG - SC_WEST_LONG) / 4 + 1);
        this.c013Points = new C013Point[siChuanByteLength];
        int headerByteLength = this.l1FileHeader.GetFileHeaderByteLength();

        int startLat = this.l2FileHeaderofGMS.getNorthLatOfGeoRange();
        int startLong = this.l2FileHeaderofGMS.getWestLongOfGeoRange();
        int endLat = this.l2FileHeaderofGMS.getSouthLatOfGeoRange();
        int endLong = this.l2FileHeaderofGMS.getEastLongOfGeoRange();
        int lat = startLat;
        int lon = startLong;
        int j = 0;
        for(int i = 0; i < productByteLength; i++){
            int grayData = this.fileData[headerByteLength + i] & 0xff;
            int briTemp = this.scalerBlockData.getBriTem(grayData-1);
            if((lat <= SC_NORTH_LAT) && (lat >= SC_SOUTH_LAT) && (lon <= SC_EAST_LONG) && (lon >= SC_WEST_LONG)) {
                c013Points[j] = new C013Point(new LatLon(lat / 100.0, lon / 100.0), grayData, briTemp);
                j++;
            }
            lon += 4;
            if(lon > endLong){
                lon = startLong;
                lat -= 4;
            }
        }
        return c013Points;
    }

    public int getSiChuanLatPointNums(){
        int nums =  ((SC_NORTH_LAT - SC_SOUTH_LAT) / 4 + 1);
        return nums;
    }

    public int getSiChuanLongPointNums(){
        int nums =  ((SC_EAST_LONG - SC_WEST_LONG) / 4 + 1);
        return nums;
    }
}
