package com.hsbc.downstream.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.hsbc.downstream.model.TransactionRecord;
import com.hsbc.downstream.writer.DBWriter;
import com.hsbc.downstream.writer.RestApiDownStreamWriter;


@Service
public class KafkaConsumer {
	private static final Logger logger = LoggerFactory.getLogger("KafkaConsumer");


	@Autowired
	RestApiDownStreamWriter restApiDownStreamWriter;
	@Autowired
	DBWriter dbwriter;
    @KafkaListener(topics = "downstream1", group = "group_downstream1",containerFactory= "kafkaListenerContainerFactory")
    public void consume(TransactionRecord record) {
    	logger.info("Consumed message: " + record);
    	dbwriter.write(record);
    }


    @KafkaListener(topics = "downstream2", group = "group_downstream2",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(TransactionRecord  record) {
    	logger.info("Consumed JSON Message: " + record);
        restApiDownStreamWriter.write(record);
    }
}
