package UniDy.UninaDelivery;

import java.time.LocalDate;
import java.util.Objects;

public class Esemplare {
	
	private String codiceBarre;
	private String colore;
	private float costo;
	private LocalDate garanzia;
	private String descrizione;
	private Magazzino magazzinoRiferimento = new Magazzino(null,null,null,null,null,0);
	private Merce merceRiferimento = new Merce(null,0,null,0,null);;
	
	protected Esemplare(String cod, String Nome) {
		codiceBarre = cod;
		merceRiferimento.setNome(Nome);
		
	}

	protected Esemplare(String cod, String colore, float value, LocalDate garanzia, String Desc,Merce merce, Magazzino magazzino) {
		codiceBarre = cod;
		this.colore = colore;
		costo = value;
		this.garanzia = garanzia;
		descrizione = Desc;
		merceRiferimento = merce;
		magazzinoRiferimento = magazzino;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Esemplare other = (Esemplare) obj;
		return Objects.equals(codiceBarre, other.codiceBarre);
	}
	
	@Override
	public String toString() {
		return "(" + codiceBarre + ") " + merceRiferimento.getNome();
	}

	protected String getCodiceBarre() {
		return codiceBarre;
	}

	protected void setCodiceBarre(String codiceBarre) {
		this.codiceBarre = codiceBarre;
	}

	protected String getColore() {
		return colore;
	}

	protected void setColore(String colore) {
		this.colore = colore;
	}

	protected float getCosto() {
		return costo;
	}

	protected void setCosto(float costo) {
		this.costo = costo;
	}

	protected Merce getMerceRiferimento() {
		return merceRiferimento;
	}

	protected void setMerceRiferimento(Merce merceRiferimento) {
		this.merceRiferimento = merceRiferimento;
	}
	
	protected LocalDate getGaranzia() {
		return garanzia;
	}

	protected void setGaranzia(LocalDate garanzia) {
		this.garanzia = garanzia;
	}

	protected String getDescrizione() {
		return descrizione;
	}

	protected void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	protected Magazzino getMagazzinoRiferimento() {
		return magazzinoRiferimento;
	}

	protected void setMagazzinoRiferimento(Magazzino magazzinoRiferimento) {
		this.magazzinoRiferimento = magazzinoRiferimento;
	}

	

}
