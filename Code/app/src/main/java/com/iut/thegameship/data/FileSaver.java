package com.iut.thegameship.data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class FileSaver implements ISave {
    @Override
    public void save(FileOutputStream file, Serializable toSave) {
        try (ObjectOutputStream oos = new ObjectOutputStream(file)) {
            oos.writeObject(toSave);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
