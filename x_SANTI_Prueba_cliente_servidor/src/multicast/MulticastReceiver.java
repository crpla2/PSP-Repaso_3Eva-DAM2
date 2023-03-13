package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class MulticastReceiver {

	public static void main(String[] args) throws IOException {
		//Creamos el sock de multicast, lo añadimos a un grupo de escucha creando previamente el grupo
		MulticastSocket mCastSock = new MulticastSocket(3000);
		InetAddress ipGroup = InetAddress.getByName("224.0.0.1");
		mCastSock.joinGroup(ipGroup); //el join esta deprecated por que el nuevo join de la nueva version de java, puedes especificar la interfaz
									  //es por eso que no nos iba antes, por que estaba cogiendo el multicas de la red de virtualBox y logicamente no funcionaria con esa
		//mCastSock.setNetworkInterface(NetworkInterface.getByInetAddress(InetAddress.getByName("192.168.101.100")));
		mCastSock.setNetworkInterface(NetworkInterface.getByName("eth3"));
		
		
		//Con un while tru, estarias constantemente a la escucha para recibir todo lo que se mande, seria como una sala de chat
		//recibo lo que todos envias a la direccion del multixast del grupo.
		//while(true) {  
		
		//Metemos lo del byte[], datagram... por que de esta manera creamos los objetos en cada vuelta y para no tener 
		//que vacia el buffer de los mensajes. Si esto lo dejaramos fuera del while, los mensajes se mezclarian por que el
		//buffer estaria lleno del mensaje anterior y entonces los byte no completados hasta 1000, se mostrarian
			byte[] arrayDeBytes = new byte[1000];
			DatagramPacket info = new DatagramPacket(new byte[1000], 1000);
			mCastSock.receive(info);
			
			
			System.out.println("Recibido de: " +info.getAddress());
			System.out.println("Por el puerto: "+info.getPort());
			System.out.println("Datos recibidos: "+new String(info.getData(),"UTF-8")); 
		//}
		
		

	}

}
