package aemet;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DescargaDatos {
	public static void main(String[] args) throws IOException, InterruptedException {
		String server = "https://opendata.aemet.es/opendata";
		String apikey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZW8ubGFpcmxhQGllc3NpZXJyYWRlZ3VhcmEuY29tIiwianRpIjoiODVjNmIwY2MtMTZiNC00OGFhLWIzMzAtNTlhMWVmYWVmMDM1IiwiaXNzIjoiQUVNRVQiLCJpYXQiOjE2NDQxODkxNTQsInVzZXJJZCI6Ijg1YzZiMGNjLTE2YjQtNDhhYS1iMzMwLTU5YTFlZmFlZjAzNSIsInJvbGUiOiIifQ.SP46yMOxpf3Qvs8GadWzC5Qu7SOz238deb-PF8PK2hc";
		String endpoint = "/api/observacion/convencional/todas";
		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder()
				.uri(URI.create(server+endpoint+"?api_key="+apikey))
				.GET()
				.build();
		
		HttpResponse<String> res = cliente.send(req, HttpResponse.BodyHandlers.ofString());
		System.out.println(res.body());
		System.out.println(res.statusCode());
		
		//https://opendata.aemet.es/opendata/api/observacion/convencional/todas?api_key=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZW8ubGFpcmxhQGllc3NpZXJyYWRlZ3VhcmEuY29tIiwianRpIjoiODVjNmIwY2MtMTZiNC00OGFhLWIzMzAtNTlhMWVmYWVmMDM1IiwiaXNzIjoiQUVNRVQiLCJpYXQiOjE2NDQxODkxNTQsInVzZXJJZCI6Ijg1YzZiMGNjLTE2YjQtNDhhYS1iMzMwLTU5YTFlZmFlZjAzNSIsInJvbGUiOiIifQ.SP46yMOxpf3Qvs8GadWzC5Qu7SOz238deb-PF8PK2hc
		//usar jackson para obtener la cadena de datos y de metadatos
		ObjectMapper mapper = new ObjectMapper();
		String urlDatos = mapper.readTree(res.body()).at("/datos").asText();
		//hacer las peticiones correspondientes de esos dos JSON
		HttpRequest reqDatos = HttpRequest.newBuilder()
				.uri(URI.create(urlDatos))
				.GET()
				.build();
		
		HttpResponse<String> resDatos = cliente.send(reqDatos, HttpResponse.BodyHandlers.ofString());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		mapper.setDateFormat(df);
		//Mapeamos los datos en una lista.
		List<EstacionMeteo> listaEstaciones = 
				mapper.readValue(resDatos.body(), 
								new TypeReference<List<EstacionMeteo>>(){});
		System.out.println(listaEstaciones.get(0).getIdema());
		System.out.println(listaEstaciones.get(0).getUbi());
		System.out.println(listaEstaciones.get(0).getTa());
		
		HashMap<String, EstacionMeteo> mapaEstaciones = 
				new HashMap<String, EstacionMeteo>();
		
		for (EstacionMeteo estacionMeteo : listaEstaciones) {
			mapaEstaciones.put(estacionMeteo.getIdema(), estacionMeteo);
		}
		
		System.out.println(mapaEstaciones.get("9901X"));
	}
}
