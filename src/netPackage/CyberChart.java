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
            
        }
        catch (IOException e) {
            System.out.println("Internal errors");
            throw new RuntimeException("GET_MAP-CREATE_OBJECT_OUTPUT_STREAM");
        }
        try {
            objectOutputStream.writeObject(CyberCargo.pack());
        }
        catch (IOException e) {
            System.out.println("Internal errors");
            throw new RuntimeException("GET_MAP-WRITE_OBJECT");
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    protected static void replaceMap(byte [] input) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input);
        CyberCargo cyberCargo;
        ObjectInputStream objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
        }
        catch (IOException e) {
            System.out.println("Internal errors");
            throw new RuntimeException("REPLACE_MAP-CREATE_OBJECT_INPUT_STREAM");
        }
        try {
            cyberCargo = (CyberCargo) objectInputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Internal errors");
            throw new RuntimeException("REPLACE_MAP-READ_OBJECT");
        }
        CyberCargo.store(cyberCargo);
    }
}
