package logic;

import java.io.Serializable;
import java.io.*;

public class FileManager<T extends Serializable> {

    private final File fileData;

    public FileManager(String filePath) {
        fileData = new File(filePath);
    }

    public T Load() throws FileNotFoundException, IOException, ClassNotFoundException {
        if (!fileData.exists()) {
            return null;
        }

        T object;
        try (FileInputStream fis = new FileInputStream(fileData);
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(fis))) {
            object = (T) ois.readObject();
        }

        return object;
    }

    public void Save(T object) throws FileNotFoundException, IOException {
        try (FileOutputStream fos = new FileOutputStream(fileData);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
        }
    }
}
