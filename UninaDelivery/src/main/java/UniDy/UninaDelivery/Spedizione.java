package UniDy.UninaDelivery;

import java.util.ArrayList;
import java.util.Objects;

public class Spedizione {
	
	private String codSpedizione;
	private ArrayList <Ordine> ordini ;
	
	
	protected Spedizione(String codSpedizione, ArrayList<Ordine> ordini) {
		this.codSpedizione = codSpedizione;
		this.ordini = ordini;
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
		return Objects.equals(codSpedizione, other.codSpedizione);
	}
	

	protected String getCodSpedizione() {
		return codSpedizione;
	}

	protected void setOrdini(ArrayList<Ordine> ordini) {
		this.ordini = ordini;
	}

	protected void setCodSpedizione(String codSpedizione) {
		this.codSpedizione = codSpedizione;
	}
	
	protected ArrayList<Ordine> getOrdini() {
		return ordini;
	}
	
	
	protected void addOrdini(Ordine ordini) {
		this.ordini.add(ordini);
	} 
	
	

}
