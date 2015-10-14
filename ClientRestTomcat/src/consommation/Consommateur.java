package consommation;


import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Consommateur {

	/**
	 * @param args
	 */

	protected static final String SERVICE_URI = "http://localhost:8080/ProjetRestTomcat/ressources";
	protected static Consommateur singleton = null;
	protected  Client client;
	protected  WebTarget target; // permet de récupérer l'URL du WS

	protected Consommateur() {
		
		client = ClientBuilder.newClient(); 
         target = client.target(SERVICE_URI);
	}

	protected static Consommateur get() {
		if (singleton == null)
			singleton = new Consommateur();
		return singleton;
	}

	protected WebTarget target() {
		return client.target(SERVICE_URI);
	}

	

}


