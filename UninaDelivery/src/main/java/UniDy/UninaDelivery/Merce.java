package UniDy.UninaDelivery;

import java.util.Objects;

public class Merce {
	
	private String codMerce;
	private String nome;
	private String peso;
	private String marca;
	private int scorte;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Merce other = (Merce) obj;
		return Objects.equals(codMerce, other.codMerce);
	}

	protected String getCodMerce() {
		return codMerce;
	}

	protected void setCodMerce(String codMerce) {
		this.codMerce = codMerce;
	}

	protected String getNome() {
		return nome;
	}

	protected void setNome(String nome) {
		this.nome = nome;
	}
	
	protected String getPeso() {
		return peso;
	}

	protected void setPeso(String peso) {
		this.peso = peso;
	}

	protected String getMarca() {
		return marca;
	}

	protected void setMarca(String marca) {
		this.marca = marca;
	}

	protected int getScorte() {
		return scorte;
	}

	protected void setScorte(int scorte) {
		this.scorte = scorte;
	}
	
	

}
