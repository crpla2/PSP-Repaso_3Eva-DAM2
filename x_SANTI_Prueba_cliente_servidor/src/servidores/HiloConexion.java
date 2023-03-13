package servidores;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class HiloConexion extends Thread {

	//Ahora vamos a crear este hilo para poder identificar las conexiones y que cada conexion sea un cliente distinto simulando conexiones reales.
	
	Socket sock;
		
	public HiloConexion(Socket sock) {
		super();
		this.sock = sock;
	}



	@Override
	public void run() {
		try {
			DataOutputStream salida = new DataOutputStream(sock.getOutputStream());
			Date fecha = new Date();
			Thread.sleep(5000); //Simulando un retardo
			salida.writeUTF(fecha.toString());
			salida.close();
			sock.close();
		} catch (Exception e) {e.printStackTrace();}
		
	}
}
