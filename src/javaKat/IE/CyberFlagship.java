package javaKat.IE;

import java.io.IOException;
import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
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
        DatagramPacket cyberFrigate = new DatagramPacket(cyberMap, cyberMap.length, cyberFleet, CYBER_DOCK_ALPHA);
        int connected = 0;
        ids = new int[Script.getExpectedNumberOfClients()];
        while (connected < ids.length) {
            try {
                cyberPortAlpha.receive(cyberFrigate);
                cyberMap[0] = (byte)(Math.random() * Byte.MAX_VALUE);
                cyberFrigate.setData(cyberMap);
                cyberPortAlpha.send(cyberFrigate);
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

    public void run() {
        class CyberFlagshipAlpha extends Thread {
            public void run() {
                while (running) {
                    DatagramPacket cyberFrigate = new DatagramPacket(new byte[CyberCargo.alphaSize()],
                            CyberCargo.alphaSize(),cyberFleet, CYBER_DOCK_ALPHA);
                    try {
                        cyberPortAlpha.receive(cyberFrigate);
                    }
                    catch (IOException e){
                        System.out.println("Internal error");
                        throw new RuntimeException("RUN-CYBERFLAGSHIPALPHA_UNABLE_TO_LISTEN");
                    }
                    CyberCargo.setAlpha(cyberFrigate.getData(),cyberFrigate.getLength());
                    //TODO
                }
            }
        }
        
        class CyberFlagshipBeta extends Thread {
            public void run() {
                while (running){
                    try {
                        wait();
                    }
                    catch( InterruptedException e ) {
                        System.out.println("Internal error");
                        throw new RuntimeException("RUN-CYBERFLAGSHIPBETA_UNABLE_TO_WAIT");
                    }
                    byte [] betaCargo = CyberCargo.getBeta();
                    DatagramPacket cyberFrigate = new DatagramPacket(betaCargo,
                            betaCargo.length,cyberFleet, CYBER_DOCK_BETA);
                    //TODO
                    try {
                        cyberPortBeta.send(cyberFrigate);
                    }
                    catch (IOException e) {
                        System.out.println("Internal error");
                        throw new RuntimeException("RUN-CYBERFLAGSHIPBETA_UNABLE_TO_SEND");
                    }
                }
            }
        }
        
        CyberFlagshipAlpha cyberFlagshipAlpha = new CyberFlagshipAlpha();
        cyberShipAlpha = cyberFlagshipAlpha;
        cyberFlagshipAlpha.start();
        CyberFlagshipBeta cyberFlagshipBeta = new CyberFlagshipBeta();
        cyberShipBeta = cyberFlagshipBeta;
        cyberFlagshipBeta.start();
    }
    
    void sendShipment() {
        cyberShipBeta.notify();
    }
}
