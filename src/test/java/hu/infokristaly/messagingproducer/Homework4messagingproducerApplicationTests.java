package hu.infokristaly.messagingproducer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = Homework4messagingproducerApplication.class)
class Homework4messagingproducerApplicationTests {

	@Autowired
    private ApplicationContext context;
	
	@Test
	void sendMessage() {
		Homework4messagingproducerApplication bean = context.getBean(Homework4messagingproducerApplication.class);
		assertNotNull(bean);
		assertNull(Homework4messagingproducerApplication.jmsTemplate);
		Homework4messagingproducerApplication.main(new String[] {});
		assertNotNull(Homework4messagingproducerApplication.jmsTemplate);
		Homework4messagingproducerApplication.sendMessagePeriodically();
		assertTrue(Homework4messagingproducerApplication.counter == 1);
	}

}
