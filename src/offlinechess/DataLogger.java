package offlinechess;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

/**
 * Writes and appends data to a file
 * @author Jed Wang
 */
public class DataLogger {
    
    /**
     * The file to write to
     */
    private File file;
    
    /**
     * Whether this DataLogger is closed
     */
    private boolean closed = false;
    
    /**
     * Constructor
     * @param file the file to write to
     * @throws IOException if file not good
     */
    public DataLogger(File file) throws IOException {
        this.file = file;
    }
    
    /**
     * Writes a String to the file
     * @param s the String to write
     * @throws IOException if something goes wrong
     */
    public void write(String s) throws IOException {
        if(!closed) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                writer.write(s);
            }
        } else throw new IOException("Already closed");
    }
    
    /**
     * Writes all of the data in a Collection
     * @param c the Collection to write
     * @throws java.io.IOException if something goes wrong
     */
    public void writeAll(Collection c) throws IOException {
        if(!closed) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                for(Object o:c) {
                    writer.write(o.toString());
                }
            }
        } else throw new IOException("Already closed");
    }
    
    /**
     * Appends a String to the end of a file
     * @param s the String to append
     * @throws IOException if something goes wrong
     */
    public void append(String s) throws IOException {
        if(!closed) {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                writer.append(s);
            }
        } else throw new IOException("Already closed");
    }
    
    /**
     * 
     * @param c
     * @throws IOException 
     */
    public void appendAll(Collection c) throws IOException {
        if(!closed) {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                for(Object o:c) {
                    writer.write(o.toString());
                }
            }
        } else throw new IOException("Already closed");
    }
    
    public void clearFile() throws IOException {
        new FileWriter(file, false);
    }
    
    /**
     * Closes the writer
     * @throws IOException if something bad happens
     */
    public void close() throws IOException {
        file = null;
        closed = true;
    }
}