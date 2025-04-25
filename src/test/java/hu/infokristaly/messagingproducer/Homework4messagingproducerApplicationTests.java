package hu.infokristaly.messagingproducer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = Homework4messagingproducerApplication.class)
class Homework4messagingproducerApplicationTests {

	@Autowired // Inject the ApplicationContext itself
    private ApplicationContext context;
	
	@Test
	void contextLoads() {
		Homework4messagingproducerApplication bean = context.getBean(Homework4messagingproducerApplication.class);
		assertNotNull(bean);
		bean.main(new String[] {});
		bean.sendMessagePeriodically();
	}

}
