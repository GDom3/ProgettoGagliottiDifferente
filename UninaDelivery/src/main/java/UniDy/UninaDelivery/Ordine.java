package UniDy.UninaDelivery;

import java.util.ArrayList;
import java.util.Objects;

public class Ordine {
	
	private String codOrdine;
	private String statoOrdine;
	private Cliente acquirente;
	private float costoTotale;
	private ArrayList<Spedizione> spedizioni = new ArrayList<Spedizione>();
	private int numMerci;
	
	protected Ordine(String codOrdine, Cliente acquirente, float costoTotale, int numMerci) {
		this.codOrdine = codOrdine;
		this.acquirente = acquirente;
		this.costoTotale = costoTotale;
		this.numMerci = numMerci;
	}
	
	protected Ordine(String codOrdine, String string) {
		this.codOrdine = codOrdine;
		statoOrdine = string;
	}
	
	protected String getStatoOrdine() {
		return statoOrdine;
	}

	protected void setStatoOrdine(String statoOrdine) {
		this.statoOrdine = statoOrdine;
	}
	
	protected int getNumMerci() {
		return numMerci;
	}

	protected void setNumMerci(int numMerci) {
		this.numMerci = numMerci;
	}
	
	protected String getCodOrdine() {
		return codOrdine;
	}

	protected void setCodOrdine(String codOrdine) {
		this.codOrdine = codOrdine;
	}

	protected Cliente getAcquirente() {
		return acquirente;
	}

	protected void setAcquirente(Cliente acquirente) {
		this.acquirente = acquirente;
	}

	protected float getCostoTotale() {
		return costoTotale;
	}

	protected void setCostoTotale(float costoTotale) {
		this.costoTotale = costoTotale;
	}

	protected void addSpedizioni(Spedizione spedizione) {
		this.spedizioni.add(spedizione);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordine other = (Ordine) obj;
		return Objects.equals(codOrdine, other.codOrdine);
	} 
	
}
