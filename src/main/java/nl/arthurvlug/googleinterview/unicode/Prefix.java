package nl.arthurvlug.googleinterview.unicode;

import lombok.Getter;

public enum Prefix {
	ASCII(0),
	CONTINUATION(1),
	BYTE2(2),
	BYTE3(3),
	BYTE4(4),
	BYTE5(5),
	BYTE6(6),
	BYTE7(7);
	
	@Getter
	private byte prefix;
	@Getter
	private int ones;
	
	private Prefix(int ones) {
		this.ones = ones;
		for(int i = 0; i < ones; i++) {
			prefix |= 1 << (7 - i);
		}
	}
}
