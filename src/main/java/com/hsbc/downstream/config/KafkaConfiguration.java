package com.hsbc.downstream.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.web.client.RestTemplate;

import com.hsbc.downstream.model.TransactionRecord;


import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfiguration {
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaAdress;
	@Value("${com.hsbc.downstream.group1}")
	private String group1;
	@Value("${com.hsbc.downstream.group2}")
	private String group2;

    @Bean
    public ConsumerFactory<String, TransactionRecord> consumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAdress);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, group1);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(TransactionRecord.class));
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TransactionRecord> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TransactionRecord> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


    @Bean
    public ConsumerFactory<String, TransactionRecord> group2ConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaAdress );
        config.put(ConsumerConfig.GROUP_ID_CONFIG, group2);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(TransactionRecord.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TransactionRecord> group2KafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TransactionRecord> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(group2ConsumerFactory());
        return factory;
    }
    @Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
