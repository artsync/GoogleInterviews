package nl.arthurvlug.googleinterview.iconorder;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Sets;

public class IconOrderTest {
	@Test
	public void testOrderOne() throws Exception {
		IconOrderCalculator iconOrderCalculator = new IconOrderCalculator();
		List<Icon> resultingOrder = iconOrderCalculator.calculateOrder(Sets.newHashSet(
				"Triangle"
		), 1);
		assertEquals(Arrays.asList(
				new Icon(1, "Triangle")
		), resultingOrder);
	}

	@Test
	public void testOrderTooBigN() throws Exception {
		IconOrderCalculator iconOrderCalculator = new IconOrderCalculator();
		List<Icon> resultingOrder = iconOrderCalculator.calculateOrder(Sets.newHashSet(
				"Triangle"
		), 2);
		assertEquals(Arrays.asList(
				new Icon(1, "Triangle")
		), resultingOrder);
	}
	
	@Test
	public void testOrderThree() throws Exception {
		IconOrderCalculator iconOrderCalculator = new IconOrderCalculator();
		List<Icon> resultingOrder = iconOrderCalculator.calculateOrder(Sets.newHashSet(
				"Rectangle",
				"Diamond",
				"Triangle"
		), 2);
		assertEquals(Arrays.asList(
				new Icon(1, "Triangle"),
				new Icon(3, "Rectangle")
		), resultingOrder);
	}
}
