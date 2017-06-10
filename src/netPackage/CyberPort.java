package netPackage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

class CyberPort extends MulticastSocket implements CyberCompass{
    protected CyberPort() throws IOException {
        super(CYBER_DOCK);
    }
    
    protected void joinGroup() throws IOException{
        this.joinGroup(InetAddress.getByName(CYBER_FLEET));
    }
}
