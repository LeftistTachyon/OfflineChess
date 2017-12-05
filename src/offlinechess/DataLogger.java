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
     * The writer
     */
    private BufferedWriter writer;
    
    /**
     * Constructor
     * @param file the file to write to
     * @throws IOException if file not good
     */
    public DataLogger(File file) throws IOException {
        this.file = file;
        writer = new BufferedWriter(new FileWriter(file, true));
    }
    
    /**
     * Writes all of the data in a Collection
     * @param c the Collection to write
     */
    public void writeAll(Collection c) throws IOException {
        for(Object o:c) {
            writer.write(o.toString());
        }
    }
    
    /**
     * Closes the writer
     * @throws IOException if something bad happens
     */
    public void close() throws IOException {
        writer.close();
    }
}