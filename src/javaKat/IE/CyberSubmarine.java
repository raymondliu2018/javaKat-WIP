package javaKat.IE;

import java.io.IOException;
import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

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
        DatagramPacket cyberFrigate = new DatagramPacket(cyberMap, cyberMap.length, cyberFleet, CYBER_DOCK_ALPHA);
        try {
            cyberPortAlpha.send(cyberFrigate);
            cyberPortAlpha.receive(cyberFrigate);
        }
        catch (IOException e) {
            System.out.println("Unable to connect");
            throw new RuntimeException("CYBERSUB-JOIN_CYBERFLEET");
        }
        id = cyberFrigate.getData()[0];
        System.out.println("ID: " + id);
        System.out.println("Waiting for server...");
        start();
    }
    
    public void run() {
        class CyberSubmarineAlpha extends Thread{
            public void run() {
                while (running) {
                    try {
                        wait();
                    }
                    catch(InterruptedException e) {
                        System.out.println("Internal error");
                        throw new RuntimeException("RUN-CYBERSUBALPHA_UNABLE_TO_WAIT");
                    }
                    byte [] alphaCargo = CyberCargo.getAlpha();
                    DatagramPacket cyberFrigate = new DatagramPacket(alphaCargo,
                            alphaCargo.length,cyberFleet, CYBER_DOCK_ALPHA);
                    try {
                        cyberPortAlpha.send(cyberFrigate);
                    }
                    catch (IOException e) {
                        System.out.println("Internal error");
                        throw new RuntimeException("RUN-CYBERSUBALPHA_UNABLE_TO_SEND");
                    }
                }
            }
        }
        
        class CyberSubmarineBeta extends Thread{
            public void run() {
                while (running) {
                    DatagramPacket cyberFrigate = new DatagramPacket(new byte[CyberCargo.betaSize()],
                            CyberCargo.betaSize(),cyberFleet, CYBER_DOCK_BETA);
                    try {
                        cyberPortBeta.receive(cyberFrigate);
                    }
                    catch (IOException e) {
                        System.out.println("Internal error");
                        throw new RuntimeException("RUN-CYBERSUBBETA_UNABLE_TO_WAIT");
                    }
                    CyberCargo.setBeta(cyberFrigate.getData(),cyberFrigate.getLength());
                }
                //TODO
            }
        }
        
        CyberSubmarineAlpha cyberSubmarineAlpha = new CyberSubmarineAlpha();
        cyberShipAlpha = cyberSubmarineAlpha;
        cyberSubmarineAlpha.start();
        CyberSubmarineBeta cyberSubmarineBeta = new CyberSubmarineBeta();
        cyberShipBeta = cyberSubmarineBeta;
        cyberSubmarineBeta.start();
    }
    
    void sendShipment() {
        cyberShipAlpha.notify();
    }
}
