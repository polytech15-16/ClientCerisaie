package consommation;

import java.io.IOException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import metier.Client;

public class Appel {

	private static final String GET_CLIENTS_LIST = "getClientsList";
	private static final String GET_CLIENT = "getClient";
	private static final String SAVE_CLIENT = "saveClient";
	private static final String DELETE_CLIENT = "deleteClient";

	public String appelTextPlain() {
		String uneChaine;
		WebTarget target = Consommateur.get().target;
		target = target.path("hello/christian");
		System.out.println(" uri :" + target.getUri());
		uneChaine = target.request().accept(MediaType.TEXT_PLAIN).get(String.class);
		return uneChaine;
	}

	public String appelXml() {
		String uneChaine;
		WebTarget target = Consommateur.get().target;
		target = target.path("get");
		// System.out.println(" uri :" + target.getUri());
		uneChaine = target.request().accept(MediaType.APPLICATION_XML).get(String.class);
		return uneChaine;
	}

	public String appelJson() {
		String uneChaine;
		WebTarget target = Consommateur.get().target;
		target = target.path("getjson");
		// System.out.println(" uri :" + target.getUri());
		uneChaine = target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		return uneChaine;
	}

	public String getClients() {
		String uneChaine;
		WebTarget target = Consommateur.get().target;
		target = target.path(GET_CLIENTS_LIST);
		// System.out.println(" uri :" + target.getUri());
		uneChaine = target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		return uneChaine;
	}

	public String getClient(int id) {
		String uneChaine;
		WebTarget target = Consommateur.get().target;
		target = target.path(GET_CLIENT + "/" + id);
		// System.out.println(" uri :" + target.getUri());
		uneChaine = target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		return uneChaine;
	}

	public String saveClient(Client client) throws JsonGenerationException, JsonMappingException, IOException {
		Response uneChaine;
		WebTarget target = Consommateur.get().target;
		target = target.path(SAVE_CLIENT);

		ObjectMapper mapper = new ObjectMapper();
		String clientString = mapper.writeValueAsString(client);

		return target.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(clientString, MediaType.APPLICATION_JSON), String.class);
	}

	public String deleteClient(int id) {
		WebTarget target = Consommateur.get().target;
		target = target.path(DELETE_CLIENT);
		return target.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(String.valueOf(id), MediaType.APPLICATION_JSON), String.class);
	}
}
