package Palette;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class C013Palette {
    private HashMap<Integer, String> paletteMap;
    private HashMap<Integer, Integer> paletteMapInt;

    public C013Palette(String filePath) throws MalformedURLException, DocumentException {
        paletteMap = new HashMap<>();
        paletteMapInt = new HashMap<>();
        File file = new File(filePath);
        URL url = file.toURI().toURL();
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);

        Element rootElement = document.getRootElement();
        Iterator iterator = rootElement.elementIterator();
        while (iterator.hasNext()){
            Element entry = (Element) iterator.next();
            List<Attribute> attributes = entry.attributes();
            int value = Integer.parseInt(attributes.get(0).getValue().replace(".",""));
//            int value = (int)(Double.parseDouble(attributes.get(0).getValue()) * 100);
            String rgba = attributes.get(1).getValue();
            String[] temp = rgba.split(",");
            int r = Integer.parseInt(temp[0]);
            String rSharp = String.format("%02X", r);
            int g = Integer.parseInt(temp[1]);
            String gSharp = String.format("%02X", g);
            int b = Integer.parseInt(temp[2]);
            String bSharp = String.format("%02X", b);
            String rgbSharp = "#" + rSharp + gSharp + bSharp;
            int rgb = (r << 16) | (g << 8) | b;

            paletteMap.put(value, rgbSharp);
            paletteMapInt.put(value, rgb);
        }
    }

    public String findRgba(int grayValue){
        return this.paletteMap.get(grayValue);
    }
    public int findRgbaInt(int grayValue){
        return this.paletteMapInt.get(grayValue);
    }
}
