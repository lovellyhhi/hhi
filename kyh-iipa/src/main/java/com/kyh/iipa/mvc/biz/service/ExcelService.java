package com.kyh.iipa.mvc.biz.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExcelService {

	public Map<String,Object> excelReader(MultipartFile excelFile) {
		List<List<String>> list = new ArrayList<List<String>>();

		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(excelFile.getInputStream());
			br = new BufferedReader(isr);
			Charset.forName("UTF-8");
			String line = "";

			while ((line = br.readLine()) != null) {
				String[] token = line.split(",");
				List<String> tempList = new ArrayList<String>(Arrays.asList(token));
				list.add(tempList);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		Map<String,Object> rtn = new HashMap<>();
		rtn.put("fileNm", excelFile.getOriginalFilename());
		rtn.put("datas", list);

		return rtn;

	}

//	public void excelReader(MultipartFile excelFile, String type)  {
//		
//	    try {
//            OPCPackage opcPackage = OPCPackage.open(excelFile.getInputStream());
//            XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
//            
//            // 첫번째 시트 불러오기
//            XSSFSheet sheet = workbook.getSheetAt(0);
//            
//            for(int i=1; i<sheet.getLastRowNum() + 1; i++) {
////                Fruit fruit = new Fruit();
//                XSSFRow row = sheet.getRow(i);
//                
//                // 행이 존재하기 않으면 패스
//                if(null == row) {
//                    continue;
//                }
//                
//                // 행의 두번째 열(이름부터 받아오기) 
//                XSSFCell cell = row.getCell(1);
////                if(null != cell) fruit.setName(cell.getStringCellValue());
//                // 행의 세번째 열 받아오기
//                cell = row.getCell(2);
////                if(null != cell) fruit.setPrice((long)cell.getNumericCellValue());
//                // 행의 네번째 열 받아오기
//                cell = row.getCell(3);
////                if(null != cell) fruit.setQuantity((int)cell.getNumericCellValue());
//                
////                list.add(fruit);\
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//		
//		
//	}
}
