package repasoAntesDeExamen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException, NoSuchAlgorithmException {
		Scanner teclado = new Scanner(System.in);
		String textoIntroducido = teclado.nextLine();
		
		Socket sock =  new Socket("localhost", 4321);
		
		//Enviamos el texto conectando con el servidor
		DataOutputStream salida =  new DataOutputStream(sock.getOutputStream());
		salida.writeUTF(textoIntroducido);
		sock.close();
		
		//Calculamos de nuevo el hash a traves del cliente y comprobamos que sea igual que el que nos ha pasado el servidor
		DataInputStream entrada = new DataInputStream(sock.getInputStream());
		String hashrecibido = entrada.readUTF();
		
		//Sacamos el hash
		 MessageDigest hashCreator = MessageDigest.getInstance("SHA-256");
		 hashCreator.update(textoIntroducido.getBytes());
	     byte[] hashEnBytes = hashCreator.digest();
	     String hashTraducido = "";
	     for (byte b : hashEnBytes) {
	    	 hashTraducido += Integer.toHexString(0xFF & b);
	     }
	     System.out.println("Hash calculado: " + hashTraducido);
	     System.out.println("Hash recibido: " + hashrecibido);
	     if(hashrecibido.equals(hashrecibido)) {
	    	 System.out.println("Los hashes son iguales");
	     }else {
	    	 System.out.println("Los hashes no son iguales");
	     }
	     //////
		
		
	}

}
