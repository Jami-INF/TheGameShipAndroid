package com.iut.thegameship.model.util.save;/*
package com.iut.thegameship.model.util.save;

import com.iut.thegameship.model.util.data.HighScore;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class HighScorePersistence implements IPersistence {

    @Override
    public void save(Object obj, File file) throws Exception {
        if (!(obj instanceof HighScore)) { throw new Exception("L'objet donné n'est pas un HighScore"); }
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
        SerializeHighScore data = new SerializeHighScore((HighScore) obj);
        encoder.writeObject(data);
        encoder.close();
    }

    @Override
    public Object load(File file) throws Exception {
        if (file.length() == 0) { return new SerializeHighScore(); }
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
        SerializeHighScore data = (SerializeHighScore) decoder.readObject();
        if (data == null) { data = new SerializeHighScore(); }
        return data;
    }
}
*/