package UniDy.UninaDelivery;

import java.util.Objects;

public class Esemplare {
	
	private String codiceBarre;
	private String colore;
	private float costo;
	private Merce merceRiferimento = new Merce(null,0,null,0,null);;
	
	protected Esemplare(String cod, String Nome) {
		codiceBarre = cod;
		merceRiferimento.setNome(Nome);
		
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
	
	
	

}
