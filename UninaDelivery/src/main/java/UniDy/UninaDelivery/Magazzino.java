package UniDy.UninaDelivery;

import java.util.ArrayList;
import java.util.Objects;

public class Magazzino {
	private String nome;
	private String numeroCivico;
	private String citta;
	private String via;
	private String CAP;
	private int capienza;
	private ArrayList<Esemplare> esemplariDepositati = new ArrayList<Esemplare>();
	
	
	public Magazzino(String nome, String numeroCivico, String citta, String via, String cAP, int capienza) {
		super();
		this.nome = nome;
		this.numeroCivico = numeroCivico;
		this.citta = citta;
		this.via = via;
		CAP = cAP;
		this.capienza = capienza;
	}

	@Override
	public String toString() {
		return nome + " ("  + citta + " " + via + " "+ numeroCivico + " " + CAP + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Magazzino other = (Magazzino) obj;
		return Objects.equals(CAP, other.CAP) && Objects.equals(citta, other.citta) && Objects.equals(nome, other.nome)
				&& Objects.equals(numeroCivico, other.numeroCivico) && Objects.equals(via, other.via);
	}

	protected String getNome() {
		return nome;
	}


	protected void setNome(String nome) {
		this.nome = nome;
	}


	protected String getNumeroCivico() {
		return numeroCivico;
	}


	protected void setNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}


	protected String getCitta() {
		return citta;
	}


	protected void setCitta(String citta) {
		this.citta = citta;
	}


	protected String getVia() {
		return via;
	}


	protected void setVia(String via) {
		this.via = via;
	}


	protected String getCAP() {
		return CAP;
	}


	protected void setCAP(String cAP) {
		CAP = cAP;
	}


	protected int getCapienza() {
		return capienza;
	}


	protected void setCapienza(int capienza) {
		this.capienza = capienza;
	}

	protected ArrayList<Esemplare> getEsemplariDeositati() {
		return esemplariDepositati;
	}

	protected void setEsemplariDeositati(ArrayList<Esemplare> esemplariDeositati) {
		this.esemplariDepositati = esemplariDeositati;
	}
	
	
}
