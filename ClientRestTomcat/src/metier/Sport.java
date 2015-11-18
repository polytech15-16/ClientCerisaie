package metier;
// Generated 5 nov. 2015 15:21:55 by Hibernate Tools 4.3.1

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Sport generated by hbm2java
 */
@XmlRootElement(name = "Sport")
public class Sport implements java.io.Serializable {

	private Integer codeSport;
	private String libelleSport;
	private String uniteTpsSport;
	private float tarifUnite;
	@JsonIgnore
	private List<Activite> activites = new ArrayList<Activite>();

	public Sport() {
	}

	public Sport(String libelleSport, String uniteTpsSport, float tarifUnite) {
		this.libelleSport = libelleSport;
		this.uniteTpsSport = uniteTpsSport;
		this.tarifUnite = tarifUnite;
	}

	public Sport(String libelleSport, String uniteTpsSport, float tarifUnite, List<Activite> activites) {
		this.libelleSport = libelleSport;
		this.uniteTpsSport = uniteTpsSport;
		this.tarifUnite = tarifUnite;
		this.activites = activites;
	}

	public Integer getCodeSport() {
		return this.codeSport;
	}

	public void setCodeSport(Integer codeSport) {
		this.codeSport = codeSport;
	}

	public String getLibelleSport() {
		return this.libelleSport;
	}

	public void setLibelleSport(String libelleSport) {
		this.libelleSport = libelleSport;
	}

	public String getUniteTpsSport() {
		return this.uniteTpsSport;
	}

	public void setUniteTpsSport(String uniteTpsSport) {
		this.uniteTpsSport = uniteTpsSport;
	}

	public float getTarifUnite() {
		return this.tarifUnite;
	}

	public void setTarifUnite(float tarifUnite) {
		this.tarifUnite = tarifUnite;
	}

	@JsonIgnore
	public List<Activite> getActivites() {
		return this.activites;
	}

	public void setActivites(List<Activite> activites) {
		this.activites = activites;
	}

}