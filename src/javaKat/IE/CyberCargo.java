package javaKat.IE;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
class CyberCargo implements Serializable{
    
    static int alphaSize() {
        return 1024;
    }
    
    static int betaSize(){
        return 1024;
    }
    
    static void setAlpha(byte [] input, int length) {
        ObjectInputStream ois = getObjectInputStream(truncateByteArray(input,length));
    }
    
    static void setBeta(byte [] input, int length) {
        ObjectInputStream ois = getObjectInputStream(truncateByteArray(input,length));
    }
    
    static byte [] getAlpha() {
        ObjectOutputStream oos = getObjectOutputStream();
        return new byte[]{1,2,3,4};
    }
    
    static byte [] getBeta() {
        ObjectOutputStream oos = getObjectOutputStream();
        return new byte[]{1,2,3,4};
    }
    
    private static ObjectOutputStream getObjectOutputStream() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        }
        catch (IOException e) {
            System.out.println("Internal errors");
            throw new RuntimeException("GET_STREAM-UNABLE_TO_CREATE_OBJECT_OUTPUT_STREAM");
        }
        return objectOutputStream;
    }
    
    private static ObjectInputStream getObjectInputStream(byte [] input) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input);
        ObjectInputStream objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
        }
        catch (IOException e) {
            System.out.println("Internal errors");
            throw new RuntimeException("GET_STREAM-UNABLE_TO_CREATE_OBJECT_INPUT_STREAM");
        }
        return objectInputStream;
    } 
    
    private static byte[] truncateByteArray(byte [] input, int length) {
        byte [] temp = new byte[length];
        for (int a = 0; a < length; a++) {
            temp[a] = input[a];
        }
        return temp;
    }
}
