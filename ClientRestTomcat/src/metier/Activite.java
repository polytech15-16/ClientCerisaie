package metier;
// Generated 5 nov. 2015 15:21:55 by Hibernate Tools 4.3.1

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@XmlRootElement(name = "Activite")
public class Activite implements java.io.Serializable {

	private Integer idActivite;
	@JsonIgnore
	private Sejour sejour;
	private Sport sport;
	private Date dateJour;
	private Integer nbloc;

	public Activite() {
	}

	public Activite(Sejour sejour, Sport sport, Date dateJour) {
		this.sejour = sejour;
		this.sport = sport;
		this.dateJour = dateJour;
	}

	public Activite(Sejour sejour, Sport sport, Date dateJour, Integer nbloc) {
		this.sejour = sejour;
		this.sport = sport;
		this.dateJour = dateJour;
		this.nbloc = nbloc;
	}

	public Integer getIdActivite() {
		return this.idActivite;
	}

	public void setIdActivite(Integer idActivite) {
		this.idActivite = idActivite;
	}

	@JsonIgnore
	public Sejour getSejour() {
		return this.sejour;
	}

	public void setSejour(Sejour sejour) {
		this.sejour = sejour;
	}

	public Sport getSport() {
		return this.sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public Date getDateJour() {
		return this.dateJour;
	}

	public void setDateJour(Date dateJour) {
		this.dateJour = dateJour;
	}

	public Integer getNbloc() {
		return this.nbloc;
	}

	public void setNbloc(Integer nbloc) {
		this.nbloc = nbloc;
	}

}
