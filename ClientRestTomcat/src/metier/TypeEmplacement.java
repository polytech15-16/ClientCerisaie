package metier;
// Generated 5 nov. 2015 15:21:55 by Hibernate Tools 4.3.1

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@XmlRootElement(name = "TypeEmplacement")
public class TypeEmplacement implements java.io.Serializable {

	private Integer codeTypeE;
	private String libtypepl;
	private float tariftypepl;
	@JsonIgnore
	private List<Emplacement> emplacements = new ArrayList<Emplacement>();

	public TypeEmplacement() {
	}

	public TypeEmplacement(String libtypepl, float tariftypepl) {
		this.libtypepl = libtypepl;
		this.tariftypepl = tariftypepl;
	}

	public TypeEmplacement(String libtypepl, float tariftypepl, List<Emplacement> emplacements) {
		this.libtypepl = libtypepl;
		this.tariftypepl = tariftypepl;
		this.emplacements = emplacements;
	}

	public Integer getCodeTypeE() {
		return this.codeTypeE;
	}

	public void setCodeTypeE(Integer codeTypeE) {
		this.codeTypeE = codeTypeE;
	}

	public String getLibtypepl() {
		return this.libtypepl;
	}

	public void setLibtypepl(String libtypepl) {
		this.libtypepl = libtypepl;
	}

	public float getTariftypepl() {
		return this.tariftypepl;
	}

	public void setTariftypepl(float tariftypepl) {
		this.tariftypepl = tariftypepl;
	}

	@JsonIgnore
	public List<Emplacement> getEmplacements() {
		return this.emplacements;
	}

	public void setEmplacements(List<Emplacement> emplacements) {
		this.emplacements = emplacements;
	}

}
