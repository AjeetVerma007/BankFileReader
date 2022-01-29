package com.lichfl.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lichfl.dao.BankFileRepository;
import com.lichfl.entity.BankFile;
import com.lichfl.service.BankService;

@Service
public class BankServiceImpl implements BankService{
	
	@Autowired
	BankFileRepository bankFileRepository;

	@Override
	public List<BankFile> getBankFiles() {
		// TODO Auto-generated method stub
		List<BankFile> axisFiles = null;
		axisFiles= bankFileRepository.findAll();
		return axisFiles;
	}

	@Override
	public void saveFile(BankFile bankFile) {
		// TODO Auto-generated method stub
		bankFileRepository.save(bankFile);
		
	}

}
