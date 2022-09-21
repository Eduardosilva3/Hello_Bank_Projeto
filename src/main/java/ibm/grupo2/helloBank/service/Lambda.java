package ibm.grupo2.helloBank.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Lambda {
    public static void buscarLambda(String nome, String cpf) throws IOException {
		String url = String.format("https://yjxpopc5q3.execute-api.us-east-1.amazonaws.com/default/analise?nome=%s&cpf=%s", nome, cpf);
		String url1 = "https://yjxpopc5q3.execute-api.us-east-1.amazonaws.com/default";
		HttpURLConnection conn1 = (HttpURLConnection) new URL(url1).openConnection();
		conn1.setRequestMethod("GET");
		conn1.setRequestProperty("Accept", "application/json");
        
        if (conn1.getResponseCode() != 200) {
			 System.out.println("Erro " + conn1.getResponseCode() + " ao obter dados da URL " + url);
		}

		BufferedReader br1 = new BufferedReader(new InputStreamReader((conn1.getInputStream())));

		String output1 = "";
		String line1;
		while ((line1 = br1.readLine()) != null) {
			output1 += line1;
		}
		conn1.disconnect();

		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output = "";
		String line;
		while ((line = br.readLine()) != null) {
			output += line;
		}

		conn.disconnect();

		
	}
}
