package com.iut.thegameship.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class FileLoader implements ILoad {
    @Nullable
    @Override
    public Serializable load(@NonNull FileInputStream file) {
        Serializable serializable = null;
        try (ObjectInputStream ois = new ObjectInputStream(file)) {
            serializable  = (Serializable) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return serializable;
    }
}
