package pokladna;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializationUtil {

    public static void serializeArray(ArrayList<Object> array, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (Object o : array) {
            oos.writeObject(o);
        }
        fos.close();
    }

    public static ArrayList<Object> deserializeArray(String fileName) throws IOException, ClassNotFoundException {
        ArrayList<Object> out = new ArrayList<>();
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object o;
        try {
            o = ois.readObject();
            while (o != null) {
                out.add(o);
                o = ois.readObject();
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }


    public static void serializeObject(Object object, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        fos.close();
    }

    public static Object deserializeObject(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = null;
        try {
            object = ois.readObject();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}