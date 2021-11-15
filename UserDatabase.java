package frameworks_and_drivers;

import entities.User;

import java.io.*;

public class UserDatabase implements Serializable {

    public void write(User u) {
        System.out.println("Saving users");


        try(FileOutputStream f = new FileOutputStream("Users.txt")) {

            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(u);
            o.close();

        } catch (IOException e) {

            e.printStackTrace();
        }


    }

    public User read() {
        System.out.println("Reading users");

        try(FileInputStream f1 = new FileInputStream("Users.txt")) {

            ObjectInputStream o = new ObjectInputStream(f1);
            User u = (User) o.readObject();
            o.close();
            return u;

        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();
        }

        return null;
    }



}
