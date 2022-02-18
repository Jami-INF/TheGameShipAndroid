package com.iut.thegameship.util.save;/*package com.iut.thegameship.util.save;

import com.iut.thegameship.util.data.Settings;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class SettingsPersistence implements IPersistence {

    @Override
    public void save(Object obj, File file) throws Exception {
        if (!(obj instanceof Settings)) { throw new Exception("L'objet donné n'est pas un Settings"); }
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
        SerializeSettings data = new SerializeSettings((Settings) obj);
        encoder.writeObject(data);
        encoder.close();
    }

    @Override
    public Object load(File file) throws Exception {
        if (file.length() == 0) { return new Settings(); }
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
        SerializeSettings data = (SerializeSettings) decoder.readObject();
        if (data == null) { data = new SerializeSettings(); }
        return data;
    }
}
*/