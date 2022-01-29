package com.lichfl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lichfl.entity.BankFile;

@Repository
public interface BankFileRepository extends  JpaRepository<BankFile,Long >{

}
