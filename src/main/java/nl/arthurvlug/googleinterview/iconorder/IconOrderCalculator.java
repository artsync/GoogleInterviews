package nl.arthurvlug.googleinterview.iconorder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class IconOrderCalculator {
	private Map<String, Icon> orderedIcons = orderedIcons();

	private Map<String, Icon> orderedIcons() {
		Map<String, Icon> list = new HashMap<String, Icon>();
		list.put("Triangle", new Icon(1, "Triangle"));
		list.put("Circle", new Icon(2, "Circle"));
		list.put("Rectangle", new Icon(3, "Rectangle"));
		list.put("Diamond", new Icon(4, "Diamond"));
		return list;
	}
	
	/**
	 * Calculates the order of the given requested icons. From that sorted list of Icons, we only need the first N results.
	 * @param requestedIcons
	 * @param N
	 * @return
	 */
	public List<Icon> calculateOrder(Set<String> requestedIcons, int N) {
		List<Icon> firstNIcons = getFirstN(requestedIcons, N);
		Collections.sort(firstNIcons);
		return firstNIcons;
	}

	/**
	 * Returns the N {@link Icon}s with the highest priority
	 * @param requestedIcons
	 * @param N
	 * @return
	 */
	private List<Icon> getFirstN(Set<String> requestedIcons, int N) {
		// Create a heap of the icons
		PriorityQueue<Icon> queue = new PriorityQueue<Icon>();
		for(String iconString : requestedIcons) {
			queue.add(orderedIcons.get(iconString));
		}
		
		// Pop N elements from the heap
		List<Icon> firstN = new ArrayList<Icon>();
		for(int i = 0; i < N; i++) {
			if(queue.size() > 0) {
				firstN.add(queue.poll());
			}
		}
		return firstN;
	}
}
