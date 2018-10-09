package com.tiger.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangshaohu on 10/8/18.
 */
public abstract class ExcelAbstract extends DefaultHandler{
    private SharedStringsTable sst;
    private String lastContents;
    private boolean nextIsString;

    private String curCellName;

    private int curRow = 0;

    private Map<String,String> rowValueMap = new HashMap<String,String>();


    public abstract void optRow(int rownum, Map<String,String> rowValueMap);

    public void readOneSheet(String filePath, int sheetNum){
        try {
            OPCPackage pkg = OPCPackage.open(filePath);
            XSSFReader r =  new XSSFReader(pkg);
            SharedStringsTable sst = r.getSharedStringsTable();
            XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
            this.sst = sst;
            parser.setContentHandler(this);

            // 根据 rId# 或 rSheet# 查找sheet
            InputStream sheet2 = r.getSheet("rId" + sheetNum);
            InputSource sheetSource = new InputSource(sheet2);
            parser.parse(sheetSource);
            sheet2.close();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (OpenXML4JException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("c")){
            String cellType = attributes.getValue("t");
            if (cellType != null && cellType.equals("s")){
                nextIsString = true;
            }else{
                nextIsString = false;
            }
        }
        lastContents = "";
        String cellName = attributes.getValue("r");
        if(cellName !=null && !cellName.isEmpty()){
            curCellName = cellName;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (nextIsString){
            int idx = Integer.parseInt(lastContents);
            lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
            nextIsString = false;
        }

        if (qName.equals("v")){
            String value = lastContents.trim();
            System.out.println("content Value:" + value);
            rowValueMap.put(curCellName,lastContents);
        }else if (qName.equals("row")){
            optRow(curRow, rowValueMap);
            rowValueMap.clear();
            curRow++;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        lastContents += new String(ch, start, length);
    }
}



