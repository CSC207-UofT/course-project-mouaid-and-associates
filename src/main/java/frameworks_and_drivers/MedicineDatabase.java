package frameworks_and_drivers;

import entities.Medicine;

import java.io.*;

public class MedicineDatabase implements Serializable {

    public void write(Medicine medicine) {
        System.out.println("Saving medicines");


        try (FileOutputStream f = new FileOutputStream("Medicines.txt")) {

            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(medicine);
            o.close();

        } catch (IOException e) {

            e.printStackTrace();
        }


    }

    public Medicine read() {
        System.out.println("Reading medicines");

        try (FileInputStream f1 = new FileInputStream("Medicines.txt")) {

            ObjectInputStream o = new ObjectInputStream(f1);
            Medicine medicine = (Medicine) o.readObject();
            o.close();
            return medicine;

        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();
        }

        return null;
    }


}




