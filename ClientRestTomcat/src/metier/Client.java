package metier;
// Generated 5 nov. 2015 15:21:55 by Hibernate Tools 4.3.1

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@XmlRootElement(name = "Client")
public class Client implements java.io.Serializable {
	private Integer numCli;
	private String nomCli;
	private String adrRueCli;
	private String cpCli;
	private String villeCli;
	private String pieceCli;
	private String numPieceCli;

	@JsonIgnore
	private List sejours;

	public Client() {
	}

	public Client(Integer numCli, String nomCli, String adrRueCli, String cpCli, String villeCli, String pieceCli,
			String numPieceCli) {
		this.numCli = numCli;
		this.nomCli = nomCli;
		this.adrRueCli = adrRueCli;
		this.cpCli = cpCli;
		this.villeCli = villeCli;
		this.pieceCli = pieceCli;
		this.numPieceCli = numPieceCli;
	}

	public String getNomCli() {
		return this.nomCli;
	}

	public void setNomCli(String nomCli) {
		this.nomCli = nomCli;
	}

	public String getAdrRueCli() {
		return this.adrRueCli;
	}

	public void setAdrRueCli(String adrRueCli) {
		this.adrRueCli = adrRueCli;
	}

	public String getCpCli() {
		return this.cpCli;
	}

	public void setCpCli(String cpCli) {
		this.cpCli = cpCli;
	}

	public String getVilleCli() {
		return this.villeCli;
	}

	public void setVilleCli(String villeCli) {
		this.villeCli = villeCli;
	}

	public String getPieceCli() {
		return this.pieceCli;
	}

	public void setPieceCli(String pieceCli) {
		this.pieceCli = pieceCli;
	}

	public String getNumPieceCli() {
		return this.numPieceCli;
	}

	public void setNumPieceCli(String numPieceCli) {
		this.numPieceCli = numPieceCli;
	}

	@Override
	public String toString() {
		return "Client [nomCli=" + nomCli + ", adrRueCli=" + adrRueCli + ", cpCli=" + cpCli + ", villeCli=" + villeCli
				+ ", pieceCli=" + pieceCli + ", numPieceCli=" + numPieceCli + "]";
	}

	public Integer getNumCli() {
		return numCli;
	}

	public void setNumCli(Integer numCli) {
		this.numCli = numCli;
	}

	@JsonIgnore
	public List getSejours() {
		return sejours;
	}

	public void setSejours(List sejours) {
		this.sejours = sejours;
	}

}
