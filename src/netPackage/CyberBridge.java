
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
    
    public static void setSail(Object requestor) {
        if (requestor instanceof GameMaster){
            new CyberBridge().start();
        }
        else {
            System.out.println("Object starting server was not GameMaster!!!");
            System.exit(0);
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
            System.exit(0);
        }
        if (role == Role.FLAGSHIP) {
            System.out.print("Server created at: ");
            System.out.println(cyberPort.getInetAddress().toString());
        }
        else if (role == Role.SUBMARINE){
            System.out.println("Enter server IP Address: ");
            CYBER_FLEET.concat(input.nextLine());
        }
        try {
            cyberFleet = InetAddress.getByName(CYBER_FLEET);
        }
        catch (UnknownHostException e) {
            cyberFleet = null;
            System.out.println("Unable to connect");
            System.exit(0);
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
                    System.exit(0);
                }
                try {
                    cyberPort.send(cyberFrigate);
                }
                catch (IOException e) {
                    System.out.println("Internal error");
                    System.exit(0);
                }
            }
            else if (role == Role.SUBMARINE){
                try {
                    cyberPort.receive(cyberFrigate);
                }
                catch(IOException e) {
                    System.out.println("Internal error");
                    System.exit(0);
                }
                try {
                    this.wait();
                }
                catch (InterruptedException e) {
                    System.out.println("Internal error");
                    System.exit(0);
                }
                cyberMap = cyberFrigate.getData();
                CyberChart.replaceMap(cyberMap);
            }
        }
    }
            
}
