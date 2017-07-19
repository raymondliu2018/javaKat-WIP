package javaKat.IE;

import java.io.IOException;
import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import static javaKat.IE.CyberCompass.CYBER_DOCK;

class CyberSubmarine extends CyberShip{
    private byte id;
    CyberSubmarine(Scanner input) {
        super(input);
        stage1();
        System.out.println("Enter server IP Address: ");
        cyberFleet$ = input.nextLine();
        stage2();
        String join = "join";
        byte [] cyberMap = join.getBytes(StandardCharsets.UTF_8);
        DatagramPacket cyberFrigate = new DatagramPacket(cyberMap, cyberMap.length, cyberFleet, CYBER_DOCK);
        try {
            cyberPort.send(cyberFrigate);
            cyberPort.receive(cyberFrigate);
        }
        catch (IOException e) {
            System.out.println("Unable to connect");
            throw new RuntimeException("CYBERSUB-JOIN_CYBERFLEET");
        }
        id = cyberFrigate.getData()[0];
        System.out.println("ID: " + id);
        System.out.println("Waiting for server...");
    }
}
