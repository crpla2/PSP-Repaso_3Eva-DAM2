package model;

import java.io.Serializable;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pregunta  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String pregunta;
	String respuesta;
	Date fechaCreacion;
	InetAddress IP;
	public Pregunta(String pregunta, String respuesta, Date fechaCreacion, InetAddress iP) {
		super();
		this.pregunta = pregunta;
		this.respuesta = respuesta;
		this.fechaCreacion = fechaCreacion;
		IP = iP;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public InetAddress getIP() {
		return IP;
	}
	public void setIP(InetAddress iP) {
		IP = iP;
	}
	//Extraer la hora de un DATE
	public String getHoraCreacion() {
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
		String hora = formatoHora.format(fechaCreacion);
        return hora;
	}
	@Override
	public String toString() {
		return "Pregunta [pregunta=" + pregunta + ", respuesta=" + respuesta + ", creacion="+ getHoraCreacion()
				+ ", IP=" + IP + "]";
	}
	

}
