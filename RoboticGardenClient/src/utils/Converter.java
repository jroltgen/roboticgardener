package utils;

/**
 * Contains conversion methods that are needed by several elements of the
 * Gesture Server.
 * 
 * @author Jay Roltgen
 * 
 */
public class Converter {
	
	/**
	 * Converts an integer intBits into a byte array.
	 * @param intBits The integer in network byte order.
	 * @return A byte array in network byte order.
	 */
	public static byte[] intToByteArray(int intBits) {
		byte[] ret = new byte[4];
		
		ret[0] = (byte) ((intBits & 0xff000000) >> 24);
		ret[1] = (byte) ((intBits & 0x00ff0000) >> 16);
		ret[2] = (byte) ((intBits & 0x0000ff00) >> 8);
		ret[3] = (byte) ((intBits & 0x000000ff) >> 0);
		
		return ret;
	}
	
	/**
	 * Converts a byte array to an integer.
	 * @param b 
	 * 		A byte array representing an integer in network
	 * 		byte order.
	 * @return
	 * 		An integer that was created from the byte array.
	 */
	public static int byteArrayToInt(byte[] b) {
        int returnValue = 0;
        
        returnValue |= (b[3]      ) & 0x000000FF;
        returnValue |= (b[2] << 8 ) & 0x0000FF00;
        returnValue |= (b[1] << 16) & 0x00FF0000;
        returnValue |= (b[0] << 24) & 0xFF000000;
        
        return returnValue;
    }

}
