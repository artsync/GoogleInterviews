package nl.arthurvlug.googleinterview.unicode;


public class UnicodeChecker {
	public boolean checkUnicode(byte[] bytes) {
		for(int i = 0; i < bytes.length; i++) {
			if (isBytes(bytes[i], Prefix.CONTINUATION)) {
				return false;
			} else if (!isBytes(bytes[i], Prefix.ASCII)) {
				int requiredBytes = requiredBytes(bytes[i]);
				int newEnd = i + requiredBytes;
				for(; i < newEnd; i++) {
					if(i+1 >= bytes.length || !isBytes(bytes[i + 1], Prefix.CONTINUATION)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private int requiredBytes(byte b) {
		if (isBytes(b, Prefix.BYTE2)) {
			return 2;
		} else if (isBytes(b, Prefix.BYTE3)) {
			return 3;
		} else if (isBytes(b, Prefix.BYTE4)) {
			return 4;
		} else if (isBytes(b, Prefix.BYTE5)) {
			return 5;
		} else if (isBytes(b, Prefix.BYTE6)) {
			return 6;
		} else if (isBytes(b, Prefix.BYTE7)) {
			return 7;
		}
		throw new RuntimeException("Could not determine size of b");
	}

	private boolean isBytes(byte b, Prefix prefix) {
		String bByteString = printByte(b);
		String prefixByteString = printByte(prefix.getPrefix());
		return prefixByteString.substring(0, prefix.getOnes()+1).equals(bByteString.substring(0, prefix.getOnes()+1));
	}

	String printByte(byte b) {
		String binaryString = Integer.toBinaryString(b);
		if (b == 0) {
			return "00000000";
		} else {
			return binaryString.substring(binaryString.length() - 8, binaryString.length());
		}
	}
}
