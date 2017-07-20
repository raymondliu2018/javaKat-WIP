
package javaKat.IE;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

abstract class CyberShip extends Thread implements CyberCompass{
    protected CyberPortAlpha cyberPortAlpha;
    protected CyberPortBeta cyberPortBeta;
    protected InetAddress cyberFleet;
    protected String cyberFleet$;
    protected Scanner input;
    protected Thread cyberShipAlpha, cyberShipBeta;
    protected volatile boolean running;
    
    protected CyberShip(Scanner input) {
        this.input = input;
    }
    
    protected void stage1() {
        try {
            cyberPortAlpha = new CyberPortAlpha();
            cyberPortBeta = new CyberPortBeta();
        }
        catch (IOException e) {
            System.out.println("Failed to bind to port " + CYBER_DOCK_ALPHA);
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
            cyberPortAlpha.joinGroup(cyberFleet);
            cyberPortBeta.joinGroup(cyberFleet);
        }
        catch(IOException e) {
            System.out.println("Unable to connect");
            throw new RuntimeException("CYBERSHIP-CONNECT_TO_CYBERFLEET");
        }
    }
    
    abstract void sendShipment();
}
