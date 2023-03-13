package clientes;

import java.io.Serializable;

public class Persona implements Serializable{

	//Esto estaria mal, habria que poner la clase persona en el paquete model, que es todo lo que comparte el cliente y el servidor, por que en realidad
	//cuando enviamos o recibimos objetos, no es el mismo objeto en cada maquina, por que al serializarlo se serializa con un id distinto
	//asi que habria que incluirle un id ademas de serializable o meterlo en un paquete comun model en este caso (pero para que esto ultimo funcione tendriamos que tener
	//exactamente los mismos atributos y todo exactamente igual).
	String nombre;
	int edad;
	
	private static final long serialVersionUID = 1L; //Le indicamos que esta clase tiene un id al serializar 1L por ejemplo
	
	public Persona(String nombre, int edad) {
		super();
		this.nombre = nombre;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", edad=" + edad + "]";
	}
	
	
	
	
}
