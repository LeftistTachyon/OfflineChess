package offlinechess;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * A (crappy) encryption class
 * @author Jed Wang
 */
public class Encryption {
    
    private static final String order = "rdNslE8M1zneT BGiSLDghyWR3OZXbJ.50PoqmUItf2k4YwK6axHpvAcFj-9CuQV"; // 64 chars

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
        int[] ints = encodeString(plaintext); // Right
        int keyNum = key.hashCode()%64; // Right
        HashMap<Integer, Character> pairs = new HashMap<>();
        for(int i = 0;i<ints.length;i++) {
            if(ints[i] == -1) {
                pairs.put(i, plaintext.charAt(i));
            }
            ints[i]--;
            keyNum %= 64;
            ints[i] ^= keyNum;
            keyNum++;
        } // Right
        return decodeString(ints, pairs); // ??
    }
    
    /**
     * Decrypts the text from a writableEncypt
     * @param ciphertext the encoded text
     * @param key the key
     * @return the plaintext
     */
    public static String writeableDecrypt(String ciphertext, String key) {
        int[] ints = encodeString(ciphertext); // Right
        int keyNum = key.hashCode()%64; // Right
        HashMap<Integer, Character> pairs = new HashMap<>();
        for(int i = 0;i<ints.length;i++) {
            if(ints[i] == -1) {
                pairs.put(i, ciphertext.charAt(i));
            }
            keyNum %= 64;
            ints[i] ^= keyNum;
            keyNum++;
            ints[i]++;
        } // Right
        return decodeString(ints, pairs); // ??
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
            int encoded;
            if(chars[i] == '\'') {
                i++;
                encoded = -encodeChar(chars[i]);
            } else {
                encoded = encodeChar(chars[i]);
            }
            ints.add(encoded);
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
     * @param pairs the pairs to add back
     * @return a converted String
     */
    private static String decodeString(int[] ii, HashMap<Integer, Character> pairs) {
        String output = "";
        for(int i = 0;i<ii.length;i++) {
            char c;
            if(pairs.containsKey(i)) {
                c = pairs.get(i);
            } else if(ii[i] < 0) {
                output += "\'";
                c = decodeChar(-ii[i]);
            } else {
                c = decodeChar(ii[i]);
            }
            output += c;
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
    
    /**
     * Determines whether a String is encryptable
     * @param s the String the check
     * @return whether the String is encryptable
     */
    public static boolean writableEncryptable(String s) {
        for(char c:s.toCharArray()) {
            if(!order.contains(c + "")) return false;
        }
        return true;
    }
}
