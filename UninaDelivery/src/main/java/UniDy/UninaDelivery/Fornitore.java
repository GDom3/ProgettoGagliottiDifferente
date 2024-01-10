package UniDy.UninaDelivery;

import java.util.ArrayList;

public class Fornitore extends Azienda {
	private ArrayList<Merce> catalogoMerci = new ArrayList<Merce>();

	public Fornitore(String Nome, String piva) {
		setNome(Nome);
		setPartitaIVA(piva);
	}

	protected ArrayList<Merce> getCatalogoMerci() {
		return catalogoMerci;
	}

	protected void setCatalogoMerci(ArrayList<Merce> catalogoMerci) {
		this.catalogoMerci = catalogoMerci;
	}

}
