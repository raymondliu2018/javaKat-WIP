
package javaKat.IE;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

class CyberShip extends Thread implements CyberCompass{
    protected CyberPort cyberPort;
    protected InetAddress cyberFleet;
    protected String cyberFleet$;
    protected Scanner input;
    
    protected CyberShip(Scanner input) {
        this.input = input;
        
        if (role == Role.SUBMARINE) {
            
        }
        
    }
    
    protected void stage1() {
        try {
            cyberPort = new CyberPort();
        }
        catch (IOException e) {
            System.out.println("Failed to bind to port " + CYBER_DOCK);
            throw new RuntimeException("CYBERSHIP-PORT_BINDING");
        }
    }
    
    protected void stage2() {            
        try {
            cyberFleet = InetAddress.getByName(cyberFleet$);
        }
        catch (UnknownHostException e) {
            System.out.println("Unable to connect");
            throw new RuntimeException("CYBERSHIP-LOCATE_CYBERFLEET");
        }
        try {
            cyberPort.joinGroup(cyberFleet);
        }
        catch(IOException e) {
            System.out.println("Unable to connect");
            throw new RuntimeException("CYBERSHIP-CONNECT_TO_CYBERFLEET");
        }
    }
    public void run() {
        while (true) {
            byte [] cyberMap = CyberChart.getMap();
            DatagramPacket cyberFrigate = new DatagramPacket(cyberMap,cyberMap.length,cyberFleet,CYBER_DOCK);
            
            if (role == Role.FLAGSHIP){
                try {
                    this.wait();
                }
                catch (InterruptedException e){
                    System.out.println("Internal error");
                    throw new RuntimeException("RUN-CYBERSHIP_INTERRUPTED");
                }
                try {
                    cyberPort.send(cyberFrigate);
                }
                catch (IOException e) {
                    System.out.println("Internal error");
                    throw new RuntimeException("RUN-SEND_CYBERFRIGATE");
                }
            }
            else if (role == Role.SUBMARINE){
                try {
                    cyberPort.receive(cyberFrigate);
                }
                catch(IOException e) {
                    System.out.println("Internal error");
                    throw new RuntimeException("RUN-RECEIVE_CYBERFRIGATE");
                }
                cyberMap = cyberFrigate.getData();
                CyberChart.replaceMap(cyberMap);
            }
        }
    }
    
}
