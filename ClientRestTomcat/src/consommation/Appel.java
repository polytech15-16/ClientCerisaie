package consommation;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class Appel {

	private static final String GET_CLIENTS_LIST = "getClientsList";
	private static final String GET_CLIENT = "getClient";

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
}
