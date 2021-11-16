package application_business_rules;
import entities.User;

import java.io.*;
import java.util.Map;

/** This interface is for reading files and writing files. This is our Data Access Interface.
 */
public interface FileReaderAndWriter {


    /**
     * Reads the objects in the file and returns them.
     * @param file      The file to be read.
     * @return          The object stored in the file.
     */
    Map<String, User> read(String file);

    /**
     * Writes objects into the file so that they are stored.
     * @param file     The file we are writing objects to.
     */
    void write(String file, Serializable objToSerialize);

}
