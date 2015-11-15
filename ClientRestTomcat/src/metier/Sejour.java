package metier;
// Generated 5 nov. 2015 15:21:55 by Hibernate Tools 4.3.1

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Sejour")
public class Sejour implements java.io.Serializable {

	private Integer numSej;
	private Client client;
	private Emplacement emplacement;
	private Date dateDebSej;
	private Date dateFinSej;
	private Integer nbPersonnes;
	private Activite activites;

	public Sejour() {

	}

	public Sejour(Client client, Emplacement emplacement) {
		this.client = client;
		this.emplacement = emplacement;
	}

	public Sejour(Client client, Emplacement emplacement, Date dateDebSej, Date dateFinSej, Integer nbPersonnes,
			Activite activites) {
		this.client = client;
		this.emplacement = emplacement;
		this.dateDebSej = dateDebSej;
		this.dateFinSej = dateFinSej;
		this.nbPersonnes = nbPersonnes;
		this.activites = activites;
	}

	@Override
	public String toString() {
		return "Sejour [numSej=" + numSej + ", client=" + client + ", emplacement=" + emplacement + ", dateDebSej="
				+ dateDebSej + ", dateFinSej=" + dateFinSej + ", nbPersonnes=" + nbPersonnes + ", activites="
				+ activites + "]";
	}

	public Integer getNumSej() {
		return this.numSej;
	}

	public void setNumSej(Integer numSej) {
		this.numSej = numSej;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Emplacement getEmplacement() {
		return this.emplacement;
	}

	public void setEmplacement(Emplacement emplacement) {
		this.emplacement = emplacement;
	}

	public Date getDateDebSej() {
		return this.dateDebSej;
	}

	public void setDateDebSej(Date dateDebSej) {
		this.dateDebSej = dateDebSej;
	}

	public Date getDateFinSej() {
		return this.dateFinSej;
	}

	public void setDateFinSej(Date dateFinSej) {
		this.dateFinSej = dateFinSej;
	}

	public Integer getNbPersonnes() {
		return this.nbPersonnes;
	}

	public void setNbPersonnes(Integer nbPersonnes) {
		this.nbPersonnes = nbPersonnes;
	}

	public Activite getActivites() {
		return this.activites;
	}

	public void setActivites(Activite activites) {
		this.activites = activites;
	}

}
