package com.kyh.iipa.mvc.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kyh.iipa.mvc.biz.service.ExcelService;
import com.kyh.iipa.mvc.result.Result;
import com.kyh.iipa.mvc.result.ResultData;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ExcelController {

	private final ExcelService excelService;

	@RequestMapping(value = "/excelReader", method = RequestMethod.POST)
	public Result excelReader(MultipartHttpServletRequest request, Model model) {
		Iterator<String> iterator = request.getFileNames();
		
		List<MultipartFile> uploadFileList = request.getFiles("fileInput");
		
		List<Map<String,Object>> rtn = new ArrayList();
		
		uploadFileList.stream().forEach(file->{
			
			Map<String,Object> fileInfo = excelService.excelReader(file);
			rtn.add(fileInfo);
		});
		
		return new ResultData(rtn);
	}

}
