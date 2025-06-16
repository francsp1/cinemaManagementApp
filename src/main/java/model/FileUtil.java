package model;

import java.io.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class FileUtil {

    public static void ensureDataDirExists(String dataDir) {
        File dir = new File(dataDir);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.err.println("Failed to create data directory: " + dataDir);
            }
        }
    }

    public static <T extends Serializable> void saveToFile(String filename, ArrayList<T> list, String dataDir) {
        ensureDataDirExists(dataDir);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(list);
        } catch (IOException e) {
            System.err.println("Failed to save file: " + filename + " - " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> void loadFromFile(String filename, Class<T> type, Consumer<ArrayList<T>> assignTo) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found: " + filename + ", skipping load.");
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = in.readObject();
            if (obj instanceof ArrayList<?>) {
                ArrayList<?> rawList = (ArrayList<?>) obj;
                ArrayList<T> castedList = new ArrayList<>();
                for (Object o : rawList) {
                    if (!type.isInstance(o)) {
                        throw new ClassCastException("Element is not of type " + type.getName());
                    }
                    castedList.add(type.cast(o));
                }
                assignTo.accept(castedList);
            } else {
                throw new ClassCastException("Data in file is not an ArrayList");
            }
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.err.println("Failed to load file: " + filename + " - " + e.getMessage());
        }
    }
}
