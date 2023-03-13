package repasoAntesDeExamen;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;



public class HiloConexion implements Runnable {

	//Ahora vamos a crear este hilo para poder identificar las conexiones y que cada conexion sea un cliente distinto simulando conexiones reales.
	
	Socket sock;
		
	public HiloConexion(Socket sock) {
		this.sock = sock;
	}



	@Override
	public void run() {
		try {
			
			DataInputStream entrada = new DataInputStream(sock.getInputStream());
			String texto = entrada.readUTF();
			
			System.out.println("Este es el texto: " + texto);
			entrada.close();
			System.out.println("IP del cliente: " + sock.getInetAddress());
			
			Thread.sleep(2000); //Simulando un retardo
			
			//Sacamos el hash
			 MessageDigest hashCreator = MessageDigest.getInstance("SHA-256");
			 hashCreator.update(texto.getBytes());
		     byte[] hashEnBytes = hashCreator.digest();
		     String hashTraducido = "";
		     for (byte b : hashEnBytes) {
		    	 hashTraducido += Integer.toHexString(0xFF & b);
		     }
		     System.out.println(hashTraducido);
		     //////
			
			
		     //Se lo devolvemos al cliente para que pueda probar que es igual al que el cliente ha generado
		     DataOutputStream salida = new DataOutputStream(sock.getOutputStream());
		     salida.writeUTF(hashTraducido);
		     salida.close();
		     sock.close();
		     
		     
			sock.close();
		} catch (IOException e) {
			System.out.println("Error en socket recibido");
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error en creacion de hash");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Error en Sleep");
			e.printStackTrace();
		}
		
	}
}
