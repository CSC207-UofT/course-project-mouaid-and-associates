package frameworks_and_drivers;

import application_business_rules.FileReaderAndWriter;
import entities.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserDataAccess implements FileReaderAndWriter {
    /**
     * Reads the objects in the file and returns them.
     *
     * @param fileName The file to be read.
     * @return The object stored in the file.
     *
     * Preconditions:
     * - The object being read from the file is of type Map<String, User>
     */
    @Override
    public Map<String, User> read(String fileName) {
        try(FileInputStream f1 = new FileInputStream(fileName)) {

            ObjectInputStream o = new ObjectInputStream(f1);
            @SuppressWarnings("unchecked")  // Since this class will always be used
            Map<String, User> accounts = (HashMap<String, User>) o.readObject();
            o.close();
            return accounts;
        } catch (EOFException e) {
            // When the file is empty, just return null.
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Oops, something went wrong :P. Please restart the program.");
            e.printStackTrace();
        }

        return null;

    }

    /**
     * Writes objects into the file so that they are stored. This method is specifically
     * for writing a map of String to User into a file.
     *
     * @param filename The file we are writing objects to.
     */
    @Override
    public void write(String filename, Serializable objToSerialize) {
        System.out.println("Saving User Information and Program State.");

        try(FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            // This is Bora's code to serialize and store objects.
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(objToSerialize);
            objectOutputStream.close();

        } catch (IOException e) {
            System.out.println("Sorry, something went wrong :P");
            e.printStackTrace();
        }
    }
}
