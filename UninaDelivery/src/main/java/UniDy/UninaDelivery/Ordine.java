package UniDy.UninaDelivery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Ordine {
	
	private String codOrdine;
	private String statoOrdine;
	private Cliente acquirente;
	private float costoSpedizione;
	private LocalDate DataE;
	private LocalDate DataConsegna;
	private Indirizzo indirizzo;
	private float costoTotale;
	private ArrayList<Spedizione> spedizioni = new ArrayList<Spedizione>();
	private ArrayList<Esemplare> articoliVenduti = new ArrayList<Esemplare>();
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
	
	protected Ordine(Cliente cliente, Esemplare esemplare, float value, LocalDate dataE, LocalDate dataConsegna,String city, String via, String numCiv, String cap) {
		acquirente = cliente;
		articoliVenduti.add(esemplare);
		costoSpedizione = value;
		DataE = dataE;
		DataConsegna = dataConsegna;
		indirizzo = new Indirizzo(city, via, numCiv, cap);
		
	}

	@Override
	public String toString() {
		return  codOrdine;
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
	
	protected void addEsemplare(Esemplare articolo) {
		this.articoliVenduti.add(articolo);
	}

	protected float getCostoSpedizione() {
		return costoSpedizione;
	}

	protected void setCostoSpedizione(float costoSpedizione) {
		this.costoSpedizione = costoSpedizione;
	}

	protected LocalDate getDataE() {
		return DataE;
	}

	protected void setDataE(LocalDate dataE) {
		DataE = dataE;
	}

	protected LocalDate getDataConsegna() {
		return DataConsegna;
	}

	protected void setDataConsegna(LocalDate dataConsegna) {
		DataConsegna = dataConsegna;
	}

	protected Indirizzo getIndirizzo() {
		return indirizzo;
	}

	protected void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

	protected Esemplare getArticoliVenduti(int i) {
		return articoliVenduti.get(i);
	}

}
