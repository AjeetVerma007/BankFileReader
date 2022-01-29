package com.lichfl.service;

import java.util.List;

import com.lichfl.entity.BankFile;

public interface BankService {
	
	public List<BankFile>  getBankFiles();

	public void saveFile(BankFile bankFile);

}
