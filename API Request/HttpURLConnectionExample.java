import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
public class HttpURLConnectionExample {


	public static void main(String[] args) throws Exception 
	{
		HttpURLConnectionExample http = new HttpURLConnectionExample();
		
		http.sentGet();

	}
	
	
	
	private void sentGet() throws Exception {
		String url = "";  //URL goes here
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		
		while((inputLine = in.readLine()) != null){
			response.append(inputLine);
		}
		
		in.close();
		
		System.out.println(response.toString());
		
		
		
		
	}

}


