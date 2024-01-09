package UniDy.UninaDelivery;

import java.util.ArrayList;
import java.util.Objects;

public class Spedizione {
	

	private String codiceSpedizione;
	private String StatoSpedizione;
	private ArrayList <Ordine> ordini = new ArrayList<Ordine>();;
	private int KM;
	private MezzoTrasporto mezzoUtilizzato;
	private Corriere corriere;
	
	
	protected Spedizione(String codSpedizione, ArrayList<Ordine> ordini) {
		this.codiceSpedizione = codSpedizione;
		this.ordini = ordini;
	}
	
	protected Spedizione(Ordine ordine, MezzoTrasporto mezzo, Corriere corriere) {
		mezzoUtilizzato = mezzo;
		this.corriere = corriere;
		ordini.add(ordine);
		
	}
	
	
	@Override
	public String toString() {
		return  codiceSpedizione ;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Spedizione other = (Spedizione) obj;
		return Objects.equals(codiceSpedizione, other.codiceSpedizione);
	}
	

	protected String getCodSpedizione() {
		return codiceSpedizione;
	}

	protected void setOrdini(ArrayList<Ordine> ordini) {
		this.ordini = ordini;
	}

	protected void setCodSpedizione(String codSpedizione) {
		this.codiceSpedizione = codSpedizione;
	}
	
	protected ArrayList<Ordine> getOrdini() {
		return ordini;
	}
	
	
	protected void addOrdini(Ordine ordini) {
		this.ordini.add(ordini);
	}

	protected String getStatoSpedizione() {
		return StatoSpedizione;
	}

	protected int getKM() {
		return KM;
	}


	protected void setKM(int kM) {
		KM = kM;
	}

	protected void setStatoSpedizione(String statoSpedizione) {
		StatoSpedizione = statoSpedizione;
	}

	protected MezzoTrasporto getMezzoUtilizzato() {
		return mezzoUtilizzato;
	}

	protected Corriere getCorriere() {
		return corriere;
	}

	protected Ordine getCodOrdineIndex(int i) {
		return ordini.get(i);
	} 
	
	

}
