package javaKat.IE;

import java.io.IOException;
import java.net.MulticastSocket;

class CyberPortAlpha extends MulticastSocket implements CyberCompass{
    protected CyberPortAlpha() throws IOException {
        super(CYBER_DOCK_ALPHA);
    }
}
