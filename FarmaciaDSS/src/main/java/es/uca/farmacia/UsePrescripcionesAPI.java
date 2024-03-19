package es.uca.farmacia;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UsePrescripcionesAPI 
{	
	public static void prescripcionPost(MedicamentoTarjeta medtarjeta)
	{
        try
        {
            URL url = new URL("http://medicamentoapi-env.eba-g2cxegny.eu-north-1.elasticbeanstalk.com/prescripciones");
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection)con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);
        
            String input = "{\"codMedicamento\":\""+ medtarjeta.getCodMed() + "\",\"nombreMedicamento\":\"nombreMed\",\"codUsuario\":\"" + medtarjeta.getCodUsuario() + "\",\"unidadesPorToma\":\"" + medtarjeta.getUdportoma() +  "\",\"horasPorToma\":\"" + medtarjeta.getHportoma() + "\"}";
            int length = input.getBytes().length;
            
            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try(OutputStream os = http.getOutputStream()) 
            {
                os.write(input.getBytes());
            }
        }
        catch(IOException IoEx)
        {
        	System.out.println(IoEx);
        }
	}
	
	public static JSONArray prescripcionGet(String codUsuario)
	{
        try
        {
            URL url = new URL("http://medicamentoapi-env.eba-g2cxegny.eu-north-1.elasticbeanstalk.com/prescripciones?codUsuario="+codUsuario);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.connect();
            int responseCode = conn.getResponseCode();

            // 200 Ok
            if(responseCode != 200)
            {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }
            else
            {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                
                while(scanner.hasNext())
                {
                    informationString.append(scanner.nextLine());
                }

                // Cerrando scanner
                scanner.close();

                // Convierte String a JSON
                JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
                
                return dataObject;
            }
        }
        catch(IOException IoEx)
        {
        	System.out.println(IoEx);
        }
        catch(ParseException parseEx)
        {
            System.out.println(parseEx);
        }
		return null;
	}
	
	public static void prescripcionPatch(String codMed, String codUsuario) 
	{
        try
        {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPatch httpPatch = new HttpPatch(new URI("http://medicamentoapi-env.eba-g2cxegny.eu-north-1.elasticbeanstalk.com/prescripciones?codigoMedicamento=" + codMed + "&codUsuario=" + codUsuario));
            @SuppressWarnings("unused")
			CloseableHttpResponse response = httpClient.execute(httpPatch);
        }
        catch(Exception exception)
        {
            System.out.println("Excepcion: Error al usar el metodo Patch de la API!");
            System.out.println(exception);
        }   
	}
}
