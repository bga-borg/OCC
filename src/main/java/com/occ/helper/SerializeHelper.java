package com.occ.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class SerializeHelper<G> {
    private Logger logger = LoggerFactory.getLogger(SerializeHelper.class);


    public void writeObject(String fileName, G objectOut) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(objectOut);
            out.close();
            fileOut.close();
            logger.info("Serialized data is saved to: " + fileName);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public G readObject(String fileName) {
        ObjectInputStream in = null;
        FileInputStream fileIn = null;
        G objectIn = null;

        try {
            fileIn = new FileInputStream(fileName);
            in = new ObjectInputStream(fileIn);
            objectIn = (G) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return objectIn;
    }


}
