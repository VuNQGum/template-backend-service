//package com.evnit.hrms3.employe.config;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//
//import java.util.Map;
//
//@EnableKafka
//@Configuration
//public class EVNIDKafkaConsumerConfig {
//   @Value(value = "${kafka.evnid.bootstrap-servers}")
//   private String bootstrapAddress;
//   private final KafkaProperties evnIdKafkaProperties;
//
//   @Autowired
//   public EVNIDKafkaConsumerConfig(KafkaProperties evnIdKafkaProperties) {
//       this.evnIdKafkaProperties = evnIdKafkaProperties;
//   }
//
//   @Bean
//   public ConcurrentKafkaListenerContainerFactory<String, String> EvnIdKafkaListenerContainerFactory() {
//       ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//       factory.setConsumerFactory(EvnIdConsumerFactory());
//       factory.setBatchListener(true);
//       factory.setConcurrency(evnIdKafkaProperties.getListener().getConcurrency());
//       factory.getContainerProperties().setAckMode(evnIdKafkaProperties.getListener().getAckMode());
//       return factory;
//   }
//
//   @Bean
//   public ConsumerFactory<String, String> EvnIdConsumerFactory() {
//       return new DefaultKafkaConsumerFactory<>(EvnIdConsumerConfigs());
//   }
//
//   @Bean
//   public Map<String, Object> EvnIdConsumerConfigs() {
//       Map<String, Object> props2 = evnIdKafkaProperties.buildConsumerProperties();
//       props2.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//       props2.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//       props2.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//       return props2;
//   }
//}
