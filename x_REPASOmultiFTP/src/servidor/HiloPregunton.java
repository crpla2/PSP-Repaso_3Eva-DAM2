package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.List;

import model.Pregunta;

public class HiloPregunton extends Thread {
	private Socket sock;
	private List<Pregunta> preguntas;

	
	 public HiloPregunton(Socket sock, List<Pregunta> preguntas) {
		this.sock = sock;
		this.preguntas = preguntas;
	}
	

	@Override
	public void run() {
		
		try {
			//Abro el stream de entrada con el socket y guardo el mensaje en un string
			DataInputStream entrada;
			entrada= new DataInputStream(sock.getInputStream());
			String pregunta=entrada.readUTF();
			//Lo mismo para el segundo mensaje
			entrada=new DataInputStream(sock.getInputStream());
			String respuesta= entrada.readUTF();
			//Creo el objeto pregunta con los mensajes recibidos + la fecha/hora actuales + la ip del que manda el mensaje 
			Pregunta objetoPregunta=new Pregunta(pregunta, respuesta, new Date(), sock.getInetAddress());
			//Añado la pregunta a la lista
			preguntas.add(objetoPregunta);
			//Imprimo el objeto pregunta
			System.out.println(objetoPregunta);
			//Abro el stream de salida con el socket
			DataOutputStream salida = new DataOutputStream(sock.getOutputStream());
			//Recojo todo lo que hay en la lista en un string
			String lista="";
			for (Pregunta pregunta2 : preguntas) {
				lista+=pregunta2.getPregunta()+"\n"+pregunta2.getRespuesta()+"\n------------------------\n";
			}
			//meto el string en el stream de salida
			salida.writeUTF(lista);
			//cierro todo
			entrada.close();
			salida.close();
			sock.close();
			} catch (IOException e) {
			// TODO Auto-generated catch block
				
			e.printStackTrace();
		}
	}

}
