package javaKat.IE;

import java.io.IOException;
import java.net.MulticastSocket;

class CyberPort extends MulticastSocket implements CyberCompass{
    protected CyberPort() throws IOException {
        super(CYBER_DOCK);
    }
}
