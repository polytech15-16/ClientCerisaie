package metier;
// Generated 5 nov. 2015 15:21:55 by Hibernate Tools 4.3.1

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@XmlRootElement(name = "Emplacement")
public class Emplacement implements java.io.Serializable {

	private Integer numEmpl;
	private TypeEmplacement typeEmplacement;
	private float surfaceEmpl;
	private int nbPersMaxEmpl;
	@JsonIgnore
	private List<Sejour> sejours = new ArrayList<Sejour>();

	public Emplacement() {
	}

	public Emplacement(TypeEmplacement typeEmplacement, float surfaceEmpl, int nbPersMaxEmpl) {
		this.typeEmplacement = typeEmplacement;
		this.surfaceEmpl = surfaceEmpl;
		this.nbPersMaxEmpl = nbPersMaxEmpl;
	}

	public Emplacement(TypeEmplacement typeEmplacement, float surfaceEmpl, int nbPersMaxEmpl, List<Sejour> sejours) {
		this.typeEmplacement = typeEmplacement;
		this.surfaceEmpl = surfaceEmpl;
		this.nbPersMaxEmpl = nbPersMaxEmpl;
		this.sejours = sejours;
	}

	public Integer getNumEmpl() {
		return this.numEmpl;
	}

	public void setNumEmpl(Integer numEmpl) {
		this.numEmpl = numEmpl;
	}

	public TypeEmplacement getTypeEmplacement() {
		return this.typeEmplacement;
	}

	public void setTypeEmplacement(TypeEmplacement typeEmplacement) {
		this.typeEmplacement = typeEmplacement;
	}

	public float getSurfaceEmpl() {
		return this.surfaceEmpl;
	}

	public void setSurfaceEmpl(float surfaceEmpl) {
		this.surfaceEmpl = surfaceEmpl;
	}

	public int getNbPersMaxEmpl() {
		return this.nbPersMaxEmpl;
	}

	public void setNbPersMaxEmpl(int nbPersMaxEmpl) {
		this.nbPersMaxEmpl = nbPersMaxEmpl;
	}

	@JsonIgnore
	public List<Sejour> getSejours() {
		return this.sejours;
	}

	public void setSejours(List<Sejour> sejours) {
		this.sejours = sejours;
	}

}
