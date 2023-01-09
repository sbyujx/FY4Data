package DTO;

public class L1FileHeader {
    private String sat96FileName;  /*Sat96文件名，长度12bytes*/
    private int endian; /*字节序，0：Little endian， 1：Big endian*/
    private int L1FileHeaderLength; /*第一级文件头长度，为固定值 40（字节）*/
    private int L2FileHeaderLength; /*第二级文件头长度，根据产品类别对应的第二级头记录中含有的实际信息内容计算所得的字节数。  */
    private int stufferDataLength;  /*填充段数据长度*/
    private int recordLength;  /*记录长度，图象产品：记录长度＝图象宽度， 格点场产品：记录长度＝横向格点数×格点数据字长*/
    private int fileHeaderRecordLength; /*文件头占用记录数,一级文件头、二级文件头、填充段、扩展段以及扩展段的填充段所占用的总记录个数*/
    private int productDataRecordLength; /*产品数据占用记录数*/
    private int productClass; /*产品类别，
                                       ＝0：未定义类型的产品
                                       ＝1：静止气象卫星图象产品
                                       ＝2：极轨气象卫星图象产品
                                       ＝3：格点场定量产品
                                       ＝4：离散场定量产品
                                       ＝5：图形和分析产品
                                       */
    private int compressMode; /*压缩方式，
                                       ＝0：未压缩
                                       ＝1：行程编码压缩
                                       ＝2：LZW 方式压缩
                                       ＝3：特定方式压缩
                                       */
    private String formatDes; /*格式说明字串,格式的版本说明，现为“SAT2004”，早期版本为“SAT96”*/
    private int qualityOfData;  /*产品数据质量标记*/

    public L1FileHeader(byte[] data){
        byte[] dataFileName = new byte[12];
        for (int i = 0; i < 12; i++) {
            dataFileName[i] = data[i];
        }
        this.sat96FileName = new String(dataFileName);

        this.endian = (data[12] & 0xff);
        this.L1FileHeaderLength = (data[14] & 0xff) + (data[15]  & 0xff) * 256;
        this.L2FileHeaderLength = (data[16] & 0xff)+ (data[17] & 0xff) * 256;
        this.stufferDataLength = (data[18] & 0xff) + (data[19] & 0xff) * 256;
        this.recordLength = (data[20] & 0xff) + (data[21] & 0xff) * 256;
        this.fileHeaderRecordLength = (data[22] & 0xff) + (data[23] & 0xff) * 256;
        this.productDataRecordLength = (data[24] & 0xff) + (data[25] & 0xff) * 256;
        this.productClass = (data[26] & 0xff);
        this.compressMode = (data[28] & 0xff);

        byte[] dataFormatDes = new byte[8];
        for (int i = 0; i < 8; i++) {
            dataFormatDes[i] = data[i];
        }
        this.formatDes = new String(dataFormatDes);

        this.qualityOfData = data[38] & 0xff;
    }

    public int GetFileHeaderByteLength(){
        return recordLength * fileHeaderRecordLength;
    }

    public int getProductByteLength(){
        return recordLength * productDataRecordLength;
    }


}
