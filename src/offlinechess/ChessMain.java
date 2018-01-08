package offlinechess;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * The Main class
 * @author Jed Wang
 */
public class ChessMain {
    
    /**
     * The Main Method
     * @param args the command line arguments
     * @throws java.io.IOException stuff happens with the file
     */
    public static void main(String[] args) throws IOException {
        //System.out.println(Encryption.writableEncrypt("nargles", "Leftist_Tachyon"));
        ChessFrame cf = new ChessFrame();
        Scanner input = new Scanner(new File("src/userdata/users.cpd"));
        DataLogger dl = new DataLogger(new File("src/userdata/users.cpd"));
        LinkedList<User> users = new LinkedList<>();
        while(input.hasNext()) {
            String[] data = input.nextLine().split(Pattern.quote("|"));
            users.add(new User(data[0], Encryption.decrypt(data[1]), Integer.parseInt(data[2])));
        }
        while(cf.isVisible()) {
            dl.appendAll(users);
        }
        cf.stop();
        dl.appendAll(users);
        System.out.println("Closing!");
        dl.close();
        input.close();
    }
    
    /**
     * A class that represents a user
     */
    static class User {
        
        /**
         * The name and password of a user
         */
        private String name, password;
        
        /**
         * The ELO rating of the user
         */
        private int rating;
        
        /**
         * Setting constructor
         * @param name user's name
         * @param password user's password
         * @param rating user's ELO rating
         */
        public User(String name, String password, int rating) {
            this.name = name;
            this.password = password;
            this.rating = rating;
        }
        
        /**
         * Changes the user's name
         * @param s the new name
         */
        public void changeName(String s) {
            name = s;
        }
        
        /**
         * Changes the user's password
         * @param s the new password
         */
        public void changePassword(String s) {
            password = s;
        }
        
        /**
         * Changes the user's ELO rating
         * @param change how much to increase the rating
         */
        public void changeRating(int change) {
            rating += change;
        }

        /**
         * Returns the user's name
         * @return the user's name
         */
        public String getName() {
            return name;
        }

        /**
         * Returns the user's password
         * @return the user's password
         */
        public String getPassword() {
            return password;
        }

        /**
         * Returns the user's ELO rating
         * @return the user's ELO rating
         */
        public int getRating() {
            return rating;
        }

        @Override
        public String toString() {
            return name + "|" + Encryption.encrypt(password) + "|" + rating;
        }
    }
    
    /**
     * A class that keeps track of time.
     */
    static class Timer {
        /**
         * The nano the timer started
         */
        private static long start;
        
        /**
         * No instantiation.
         */
        private Timer(){}
        
        /**
         * Starts the timer.
         */
        public static void start() {
            start = System.nanoTime();
        }
        
        /**
         * Stops the timer.
         */
        public static void end() {
            System.out.println(((System.nanoTime()-start)*1.0/1000000000L) + " seconds");
        }
    }
}
