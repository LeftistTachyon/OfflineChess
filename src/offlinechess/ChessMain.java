package offlinechess;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Main class
 * @author Jed Wang
 */
public class ChessMain {
    
    /**
     * The Main Method
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        new ChessFrame();
        /*DataLogger dl = new DataLogger(new File("src/userdata/users.cpd"));
        Scanner input = new Scanner(new File("src/userdata/users.cpd"));
        dl.close();
        input.close();*/
        String e = Encryption.writableEncrypt("nargles", "nargles");
        System.out.println(e);
        System.out.println(Encryption.writeableDecrypt(e, "nargles"));
    }
}
