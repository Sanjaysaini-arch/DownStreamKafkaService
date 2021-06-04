package com.hsbc.downstream.writer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hsbc.downstream.model.TransactionRecord;
import com.hsbc.downstream.repo.TransactionRecordRepository;



@Component
public class DBWriter   {
	
	
	@Autowired
    private TransactionRecordRepository transactionRecordRepository;
  
    public DBWriter (TransactionRecordRepository transactionRecordRepository) {
        this.transactionRecordRepository = transactionRecordRepository;
    }

    public void write(TransactionRecord records){
		
        
        	transactionRecordRepository.save(records);
        	
        
        
    }}
