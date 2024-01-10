package UniDy.UninaDelivery;

import java.util.Objects;

public class Merce {

	private String nome;
	private float peso;
	private String marca;
	private Fornitore azienda;
	private int anno;
	private int scorte;
	
	protected Merce(String nome, float peso, String marca, int anno, Fornitore fornitore) {
		this.nome = nome;
		this.peso = peso;
		this.marca = marca;
		this.anno = anno;
		azienda = fornitore;
	}
	
	@Override
	public String toString() {
		return nome + " " + marca + " " + anno;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Merce other = (Merce) obj;
		return anno == other.anno && Objects.equals(marca, other.marca) && Objects.equals(nome, other.nome);
	}

	protected String getNome() {
		return nome;
	}

	protected void setNome(String nome) {
		this.nome = nome;
	}
	
	protected float getPeso() {
		return peso;
	}

	protected void setPeso(float peso) {
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

	protected int getAnno() {
		return anno;
	}

	protected void setAnno(int anno) {
		this.anno = anno;
	}

	protected Fornitore getAzienda() {
		return azienda;
	}

	protected void setAzienda(Fornitore azienda) {
		this.azienda = azienda;
	}



	
	
	

}
