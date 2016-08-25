/**
 * 
 */
package linktester;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Ciro Baron Neto
 * 
 *         Aug 25, 2016
 */
public class ConnectionService {

	private HttpURLConnection connection;

	private Integer responseCode = null;

	/**
	 * Opens a new connection 
	 * 
	 * @param link
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void openConnection(String link) throws MalformedURLException, IOException {
		connection = (HttpURLConnection) new URL(link).openConnection();
		setRequestProperties();
		responseCode = connection.getResponseCode();
	}
	
	/**
	 * Opens a new connection and set cookies
	 * 
	 * @param link
	 * @param cookies
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void openConnection(String link, String cookies) throws MalformedURLException, IOException {
		connection = (HttpURLConnection) new URL(link).openConnection();
		setRequestProperties();
		connection.setRequestProperty("Cookie", cookies);		
		responseCode = connection.getResponseCode();
	}
	
	private void setRequestProperties(){
		connection.setRequestProperty("Accept", "application/rdf+xml");
		connection.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
		connection.addRequestProperty("User-Agent", "Mozilla");
		connection.addRequestProperty("Referer", "google.com");
	}

	/**
	 * Return HTTP response code
	 * 
	 * @return
	 */
	public Integer getResponseCode() {
		if (responseCode != null)
			return responseCode;
		else
			throw new NullPointerException();
	}
	
	public HttpURLConnection getHttpConnection(){
		return connection;
	}
	
	

}
