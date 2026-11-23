package mylab.order.di.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
public class OrderSpringTest {

	@Autowired
	private ShoppingCart cart;

	@Autowired
	private OrderService service;

	// ShoppingCart 스프링 Bean 검증
	@Test
	public void testShoppingCart() {
		assertNotNull(cart);
		assertEquals(2, cart.getProducts().size());
		assertEquals("노트북", cart.getProducts().get(0).getName());
		assertEquals("스마트폰", cart.getProducts().get(1).getName());
	}

	// OrderService 스프링 Bean 검증
	@Test
	public void testOrderService() {
		assertNotNull(service);
		assertNotNull(service.getShoppingCart());
		assertEquals(950000.0, service.calculateOrderTotal());
	}
}
