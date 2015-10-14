package consommation;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class Appel {
	
	public  String  appelTextPlain()
	{
		String uneChaine;
		
		WebTarget target = Consommateur.get().target;
		target = target.path("hello/christian");
		System.out.println(" uri :"  + target.getUri());
		uneChaine= target.request().accept(MediaType.TEXT_PLAIN).get(String.class);
		return uneChaine;
	}
	
	
	public  String  appelXml()
	{
		String uneChaine;
		
		WebTarget target = Consommateur.get().target;
		target = target.path("get");
		//System.out.println(" uri :"  + target.getUri());
		uneChaine= target.request().accept(MediaType.APPLICATION_XML).get(String.class);
		return uneChaine;
	}
	
	
	public  String  appelJson()
	{
		String uneChaine;
		
		WebTarget target = Consommateur.get().target;
		target = target.path("getjson");
		//System.out.println(" uri :"  + target.getUri());
		uneChaine= target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		return uneChaine;
	}
}
