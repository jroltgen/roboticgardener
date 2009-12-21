package garden.comm;

import garden.GardenClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class RabbitConnection {

	private Socket _socket;
	
	public RabbitConnection() {
		
	}
	
	public boolean connect(String hostName) {
		try {
			_socket = new Socket(InetAddress.getByName(hostName), 
					GardenClient.PORT);
			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public GardenStateMessage getGardenState() {
		// TODO implement this message.
		return null;
		
	}
}
