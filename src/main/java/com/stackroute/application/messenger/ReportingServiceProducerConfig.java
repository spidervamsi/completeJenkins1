package com.stackroute.application.messenger;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.stackroute.application.model.ProduceManualModel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


@Configuration
public class ReportingServiceProducerConfig {
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;
	
	
	@Bean
    public ProducerFactory<String, ProduceManualModel> producerFactory() {
        Map<String, Object> configProps = new HashMap<String, Object>();
        configProps.put(
          ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          bootstrapServer);
        configProps.put(
          ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
          StringSerializer.class);
        configProps.put(
          ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
          JsonSerializer.class);
        return new DefaultKafkaProducerFactory<String, ProduceManualModel>(configProps);
    }
 
    @Bean
    public KafkaTemplate<String, ProduceManualModel> kafkaTemplate() {
        return new KafkaTemplate<String, ProduceManualModel>(producerFactory());
    }
}
