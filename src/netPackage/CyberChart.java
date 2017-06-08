package netPackage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class CyberChart {
    protected static byte[] getMap() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            
            objectOutputStream.writeObject(CyberCargo.pack());
            return byteArrayOutputStream.toByteArray();
        }
        catch (IOException e) {
            System.out.println("Internal errors");
            System.exit(0);
            return null;
        }
    }
    
    protected static void replaceMap(byte [] input) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input);
        ObjectInputStream objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
        }
        catch (IOException e) {
            objectInputStream = null;
            System.out.println("Internal errors");
            System.exit(0);
        }
        try {
            CyberCargo cyberCargo = (CyberCargo) objectInputStream.readObject();
            CyberCargo.unpack(cyberCargo);
        }
        catch (IOException e) {
            System.out.println("Internal errors");
            System.exit(0);
        }
        catch (ClassNotFoundException e) {
            System.out.println("Internal errors");
            System.exit(0);
        }
    }
}
