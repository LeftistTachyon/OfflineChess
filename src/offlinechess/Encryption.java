package offlinechess;

import java.util.LinkedList;

public class Encryption {
    
    private static final String order = "rdNslE8M1zneT_BGiSLDghyWR3OZXbJ.50PoqmUItf2k4YwK6axHpvAcFj-9CuQV"; // 64 chars

    /**
     * Private constructor.
     */
    private Encryption() {}
    
    /**
     * Encodes a String
     * @param plaintext the plaintext
     * @return the encoded text
     */
    public static String encrypt(String plaintext) {
        char[] chars = plaintext.toCharArray();
        double pie = Math.PI * Math.E;
        for(int i = 0;i<chars.length;i++) {
            chars[i] -= (int) (pie % 10);
            pie *= 10;
            if((int) (pie % 10) == 0) {
                pie = Math.PI * Math.E;
            }
            chars[i] ^= 'a' + i;
        }
        return new String(chars);
    }
    
    /**
     * Decodes a String
     * @param ciphertext the encoded text
     * @return the plaintext
     */
    public static String decrypt(String ciphertext) {
        char[] chars = ciphertext.toCharArray();
        double pie = Math.PI * Math.E;
        for(int i = 0;i<chars.length;i++) {
            chars[i] ^= 'a' + i;
            chars[i] += (int) (pie % 10);
            pie *= 10;
            if((int) (pie % 10) == 0) {
                pie = Math.PI * Math.E;
            }
        }
        return new String(chars);
    }
    
    /**
     * Encrypts the text in a way that is writable
     * @param plaintext the plaintext
     * @param key the key
     * @return the encoded text
     */
    public static String writableEncrypt(String plaintext, String key) {
        int[] ints = encodeString(key);
        int keyNum = key.hashCode()%64;
        for(int i = 0;i<ints.length;i++) {
            ints[i]--;
            ints[i] ^= keyNum+1;
        }
        return decodeString(ints);
    }
    
    public static String writeableDecrypt(String ciphertext, String key) {
        int[] ints = encodeString(key);
        int keyNum = key.hashCode()%64;
        for(int i = 0;i<ints.length;i++) {
            ints[i] ^= keyNum+1;
            ints[i]++;
        }
        return decodeString(ints);
    }
    
    /**
     * Converts a String to an int[]
     * @param s the String to convert
     * @return an int[] that represents the String
     */
    private static int[] encodeString(String s) {
        char[] chars = s.toCharArray();
        LinkedList<Integer> ints = new LinkedList<>();
        for(int i = 0;i<chars.length;i++) {
            if(chars[i] == '\'') {
                i++;
                ints.add(-encodeChar(chars[i]));
            } else ints.add(encodeChar(chars[i]));
        }
        int[] output = new int[ints.size()];
        for(int i = 0;i<output.length;i++) {
            output[i] = ints.get(i);
        }
        return output;
    }
    
    /**
     * Converts an int[] to a String
     * @param ii the int[] to convert
     * @return a converted String
     */
    private static String decodeString(int[] ii) {
        String output = "";
        for(int i = 0;i<output.length();i++) {
            if(ii[i] < 0) {
                output += "\'" + decodeChar(-ii[i]);
            } else output += decodeChar(ii[i]);
        }
        return output;
    }
    
    /**
     * Converts a char to a number for encoding
     * @param c the character
     * @return the number that represents the character
     */
    private static int encodeChar(char c) {
        return order.indexOf(c + ""); // 0 to 63
    }
    
    /**
     * Converts a number to a char for decoding
     * @param i the number
     * @return the character that represents the number
     */
    private static char decodeChar(int i) {
        return order.charAt(i);
    }
}