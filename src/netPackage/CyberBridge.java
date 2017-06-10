
package netPackage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import thePackage.GameMaster;

public final class CyberBridge extends Thread implements CyberCompass{
    private CyberPort cyberPort;
    private InetAddress cyberFleet;
    private Role role;
    private static CyberBridge cyberBridge;
    
    public static void setSail(Object requestor) {
        if (requestor instanceof GameMaster){
            cyberBridge = new CyberBridge();
            cyberBridge.start();
        }
        else {
            System.out.println("Object starting server was not GameMaster!!!");
            throw new IllegalArgumentException("SET_SAIL-ATTEMPTING_STARTUP");
        }
    }
    
    private CyberBridge() {
        Scanner input = new Scanner(System.in);
        System.out.println("Server or Client?");
        String temp = input.nextLine();
        while (true) {
            if (temp.equals("server")){
                role = Role.FLAGSHIP;
                break;
            }
            else if (temp.equals("client")){
                role = Role.SUBMARINE;
                break;
            }
            else {
                System.out.println("Unrecoginzed; Please try again");
            }
        }
        try {
            cyberPort = new CyberPort();
        }
        catch (IOException e) {
            System.out.println("Failed to bind to port " + CYBER_DOCK);
            throw new RuntimeException("CYBERBRIDGE-PORT_BINDING");
        }
        if (role == Role.FLAGSHIP) {
            System.out.print("Server created at: ");
            System.out.println(cyberPort.getInetAddress().toString());
            CYBER_FLEET.concat(cyberPort.getInetAddress().toString());
        }
        else if (role == Role.SUBMARINE){
            System.out.println("Enter server IP Address: ");
            CYBER_FLEET.concat(input.nextLine());
        }
        try {
            cyberFleet = InetAddress.getByName(CYBER_FLEET);
        }
        catch (UnknownHostException e) {
            System.out.println("Unable to connect");
            throw new RuntimeException("CYBERBRIDGE-LOCATE_CYBERFLEET");
        }
        try {
            cyberPort.joinGroup();
        }
        catch(IOException e) {
            System.out.println("Unable to connect");
            throw new RuntimeException("CYBERBRIDGE-CONNECT_TO_CYBERFLEET");
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
                    throw new RuntimeException("RUN-CYBERBRIDGE_INTERRUPTED");
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
