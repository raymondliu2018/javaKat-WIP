package netPackage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

class CyberPort extends MulticastSocket implements CyberCompass{
    public CyberPort() throws IOException {
        super(CYBER_DOCK);
        this.joinGroup(InetAddress.getByName(CYBER_FLEET));
    }
}
