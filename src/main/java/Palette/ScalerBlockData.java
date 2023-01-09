package Palette;

public class ScalerBlockData {
    private byte[] data;
    private int byteLength;
    private int[] grayToBriTem;  /*灰度到亮温的映射值*/

    private int dataLength;

    public ScalerBlockData(byte[] data, int byteLength) {
        this.data = data;
        this.byteLength = byteLength;
        this.dataLength = byteLength / 8;
        this.grayToBriTem = new int[dataLength];
        for (int i = 0; i < dataLength; i++) {
            this.grayToBriTem[i] = ((data[i * 8] & 0xff) + (data[i * 8 + 1] & 0xff) * 256); /*0.01K*/
        }
    }

    public int getBriTem(int gray){
        return this.grayToBriTem[gray];
    }

}
