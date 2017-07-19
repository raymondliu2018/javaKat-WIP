package javaKat.IE;

import java.io.IOException;
import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import static javaKat.IE.CyberCompass.CYBER_DOCK;
import workspace.Script;

class CyberFlagship extends CyberShip{
    private int [] ids;
    CyberFlagship(Scanner input) {
        super(input);
        stage1();
        System.out.print("Server created at: ");
        cyberFleet$ = createServerAddress();
        System.out.println(cyberFleet$);
        stage2();
        String join = "join";
        byte [] cyberMap = join.getBytes(StandardCharsets.UTF_8);
        DatagramPacket cyberFrigate = new DatagramPacket(cyberMap, cyberMap.length, cyberFleet, CYBER_DOCK);
        int connected = 0;
        ids = new int[Script.getExpectedNumberOfClients()];
        while (connected < ids.length) {
            try {
                cyberPort.receive(cyberFrigate);
                cyberMap[0] = (byte)(Math.random() * Byte.MAX_VALUE);
                cyberFrigate.setData(cyberMap);
                cyberPort.send(cyberFrigate);
                connected += 1;
            }
            catch (IOException e) {
                System.out.println("Unable to connect");
                throw new RuntimeException("CYBERFLASHIP-WAITING_FOR_CYBERSUBS");
            }
        }
        
    }
    
    private String createServerAddress(){
            String stack = Integer.toString((int) (Math.random() * 14) + 225);
            stack += ".";
            stack += Integer.toString((int) (Math.random() * 255));
            stack += ".";
            stack += Integer.toString((int) (Math.random() * 255));
            stack += ".";
            stack += Integer.toString((int) (Math.random() * 255));
            System.out.println(stack);
            return stack;
    }    
}
