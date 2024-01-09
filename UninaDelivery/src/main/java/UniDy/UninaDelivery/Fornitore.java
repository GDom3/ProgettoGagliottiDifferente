package UniDy.UninaDelivery;

import java.util.ArrayList;

public class Fornitore extends Azienda {
	private ArrayList<Merce> catalogoMerci = new ArrayList<Merce>();
	private int codFornitore;

	public Fornitore(int codice, String Nome) {
		setNome(Nome);
		codFornitore = codice;
		
	}

	@Override
	public String toString() {
		return getNome();
	}

	protected ArrayList<Merce> getCatalogoMerci() {
		return catalogoMerci;
	}

	protected void setCatalogoMerci(ArrayList<Merce> catalogoMerci) {
		this.catalogoMerci = catalogoMerci;
	}

	protected int getCodFornitore() {
		return codFornitore;
	}

	protected void setCodFornitore(int codFornitore) {
		this.codFornitore = codFornitore;
	}
	
	
}
