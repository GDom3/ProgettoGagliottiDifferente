package UniDy.UninaDelivery;

import java.util.Objects;

public class Indirizzo {
	private String città;
	private String via;
	private String numeroCivico;
	private String CAP;
	
	protected Indirizzo(String città, String via, String numeroCivico, String cAP) {
		this.città = città;
		this.via = via;
		this.numeroCivico = numeroCivico;
		CAP = cAP;
	}

	@Override
	public String toString() {
		return città + " " + via + " " + numeroCivico + " " + CAP;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Indirizzo other = (Indirizzo) obj;
		return Objects.equals(CAP, other.CAP) && Objects.equals(città, other.città)
				&& Objects.equals(numeroCivico, other.numeroCivico) && Objects.equals(via, other.via);
	}

	protected String getCittà() {
		return città;
	}

	protected void setCittà(String città) {
		this.città = città;
	}

	protected String getVia() {
		return via;
	}

	protected void setVia(String via) {
		this.via = via;
	}

	protected String getNumeroCivico() {
		return numeroCivico;
	}

	protected void setNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	protected String getCAP() {
		return CAP;
	}

	protected void setCAP(String cAP) {
		CAP = cAP;
	}
	
	
	
}
