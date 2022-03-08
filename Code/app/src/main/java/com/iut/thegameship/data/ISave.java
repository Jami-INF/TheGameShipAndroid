package com.iut.thegameship.data;

import java.io.FileOutputStream;
import java.io.Serializable;

public interface ISave {

    void save(FileOutputStream file, Serializable toSave);
}
