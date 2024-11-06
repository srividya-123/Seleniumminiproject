package orangeHrm;

import java.io.FileInputStream;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class apachiePoi {
	public static String getExcelValue(int row,int col) throws IOException {
		   FileInputStream fi=new FileInputStream(System.getProperty("user.dir")+"\\projectdata\\testdata1.xlsx");
		   XSSFWorkbook wo=  new XSSFWorkbook(fi);
		   XSSFSheet sh=wo.getSheetAt(0);
		   XSSFRow ro=sh.getRow(row);
		   XSSFCell cell= ro.getCell(col);
		   String data;
		    try {
		    	String data1=cell.toString();
		    	data=data1.replaceAll("\\.0$","");
		    }catch(Exception e) {
		    	data="";
		    }
		    return data;
		   
	   }

}
