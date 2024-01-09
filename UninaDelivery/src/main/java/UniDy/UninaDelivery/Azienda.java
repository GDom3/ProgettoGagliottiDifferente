package UniDy.UninaDelivery;

import java.util.Objects;

public class Azienda {
	private String nome;
	private String partitaIVA;
	private String indirizzo;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Azienda other = (Azienda) obj;
		return Objects.equals(partitaIVA, other.partitaIVA);
	}

	protected String getNome() {
		return nome;
	}

	protected void setNome(String nome) {
		this.nome = nome;
	}

	protected String getPartitaIVA() {
		return partitaIVA;
	}

	protected void setPartitaIVA(String partitaIVA) {
		this.partitaIVA = partitaIVA;
	}

	protected String getIndirizzo() {
		return indirizzo;
	}

	protected void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
}
