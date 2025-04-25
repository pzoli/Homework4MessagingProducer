package hu.infokristaly.messagingproducer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import hu.infokristaly.messagingconsumer.jms.dtos.Customer;

@SpringBootApplication
@EnableScheduling
public class Homework4messagingproducerApplication {

	private static JmsTemplate jmsTemplate;
	private static int counter = 0;
	
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		Map<String, Class<?>> typeIdMap = new HashMap<>();
        typeIdMap.put(Customer.class.getName(),Customer.class);
        converter.setTypeIdMappings(typeIdMap);
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Homework4messagingproducerApplication.class, args);
		jmsTemplate = context.getBean(JmsTemplate.class);
	}

	@Scheduled(fixedRate = 5000)
	public void sendMessage() {
		Customer customer = new Customer();
		customer.setId(String.valueOf(counter));
		customer.setName("John Doe (" + counter + ")");
		jmsTemplate.convertAndSend("someQueue", customer);
		counter++;

	}
}
