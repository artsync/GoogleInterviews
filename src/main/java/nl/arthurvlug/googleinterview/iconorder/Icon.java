package nl.arthurvlug.googleinterview.iconorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode
public class Icon implements Comparable<Icon> {
	private int priority;
	private String name;
	
	public int compareTo(Icon o) {
		return priority - o.getPriority();
	}
}
