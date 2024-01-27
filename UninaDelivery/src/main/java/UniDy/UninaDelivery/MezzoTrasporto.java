package UniDy.UninaDelivery;

import java.util.ArrayList;
import java.util.Objects;

public class MezzoTrasporto {
	
	private String targa;
	private String marca;
	private String modello;
	private int capienza;
	private boolean disponibilità;	
	private float assicurazione;
	private String patentiNecessarie;
	private ArrayList<Spedizione> spedizioni = new ArrayList<Spedizione>();

	public MezzoTrasporto(String targa, String marca, String modello, int capienza, String patente, float costoAssicurazione) {
		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
		this.capienza = capienza;
		this.patentiNecessarie = patente;
		this.assicurazione = costoAssicurazione;
	}
	
	
	@Override
	public String toString() {
		return targa + " (" + marca + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MezzoTrasporto other = (MezzoTrasporto) obj;
		return Objects.equals(targa, other.targa);
	}

	protected String getTarga() {
		return targa;
	}

	protected void setTarga(String targa) {
		this.targa = targa;
	}

	protected String getMarca() {
		return marca;
	}

	protected void setMarca(String marca) {
		this.marca = marca;
	}

	protected String getModello() {
		return modello;
	}

	protected void setModello(String modello) {
		this.modello = modello;
	}

	protected int getCapienza() {
		return capienza;
	}

	protected void setCapienza(int capienza) {
		this.capienza = capienza;
	}

	protected boolean isDisponibilità() {
		return disponibilità;
	}

	protected void setDisponibilità(boolean disponibilità) {
		this.disponibilità = disponibilità;
	}

	protected String getPatentiNecessarie() {
		return patentiNecessarie;
	}

	protected void setPatentiNecessarie(String patentiNecessarie) {
		this.patentiNecessarie = patentiNecessarie;
	}

	protected float getAssicurazione() {
		return assicurazione;
	}

	protected void setAssicurazione(float assicurazione) {
		this.assicurazione = assicurazione;
	}

	protected ArrayList<Spedizione> getSpedizioni() {
		return spedizioni;
	}

	protected void setSpedizioni(ArrayList<Spedizione> spedizioni) {
		this.spedizioni = spedizioni;
	}
	

	
	
	
	
	
}
