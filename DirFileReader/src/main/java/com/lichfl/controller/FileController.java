package com.lichfl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lichfl.dao.BankFileRepository;
import com.lichfl.entity.BankFile;
import com.lichfl.service.BankService;

@Controller
public class FileController {
	
	@Autowired
	BankService  bankService;
	
	@Autowired
	BankFileRepository  bankFileRepository;
	
	

	@GetMapping({ "/list" })
	public String getAllFiles(
			@RequestParam(value = "clientCode", defaultValue = "Axis", required = true) String clientCode, Model m) {
		
	System.out.println("reached controller : /list");

		
		 List<BankFile> bankFileList = bankService.getBankFiles();
		System.out.println("File List : " + bankFileList);
		m.addAttribute("bankFileList", bankFileList);
		return "fileList";

	}



}
