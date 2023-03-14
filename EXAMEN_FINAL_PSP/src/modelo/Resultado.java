package modelo;

import java.io.Serializable;
import java.util.Date;

import org.jsoup.nodes.Element;



public class Resultado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	String URL;
	String selector;
	Date fecha;
	String element;
	
	
	public Resultado(String uRL, String selector, Date fecha, String element) {
		super();
		URL = uRL;
		this.selector = selector;
		this.fecha = fecha;
		this.element = element;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getSelector() {
		return selector;
	}
	public void setSelector(String selector) {
		this.selector = selector;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	@Override
	public String toString() {
		return "Respuesta [URL=" + URL + ", selector=" + selector + ", fecha=" + fecha + ", element=" + element + "]";
	}
	
	

}
