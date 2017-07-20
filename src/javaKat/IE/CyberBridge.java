package javaKat.IE;

import java.util.Scanner;
import javaKat.GameMaster;

public final class CyberBridge {
    private static CyberShip cyberShip;
    public static void setSail(Object requestor) {
        if (!(requestor instanceof GameMaster)){
            System.out.println("Object starting server was not GameMaster!!!");
            throw new IllegalArgumentException("SET_SAIL-ATTEMPTING_STARTUP");
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Server or Client?");
        String temp = input.nextLine();
        while (true) {
            if (temp.equals("server")){
                cyberShip = new CyberFlagship(input);
                break;
            }
            else if (temp.equals("client")){
                cyberShip = new CyberSubmarine(input);
                break;
            }
            else {
                System.out.println("Unrecoginzed; Please try again");
                System.out.println("Server or Client?");
                temp = input.nextLine();
            }
        }
        cyberShip.start();
    }
    
    public static void run() {
        cyberShip.sendShipment();
    }
}
