package javaKat.IE;

import java.io.IOException;
import java.net.MulticastSocket;

class CyberPortBeta extends MulticastSocket implements CyberCompass{
    protected CyberPortBeta() throws IOException {
        super(CYBER_DOCK_BETA);
    }
}
