package UniDy.UninaDelivery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Cliente extends Persona {
	private String PreferenzaContatto;
	private ArrayList<Ordine> ordini = new ArrayList<Ordine>();
	
	public Cliente(String codiceFiscale, String nome, String cognome, LocalDate dataDiNascita, String email, String numeroTelefono,String PrefetrenzaContatto) {
		setCodiceFiscale(codiceFiscale);
		setNome(nome);
		setCognome(cognome);
		setDataNascita(dataDiNascita);
		setEmail(email);
		setNumeroCellulare(numeroTelefono);
		PreferenzaContatto = PrefetrenzaContatto;
	}

	protected void addOrdini(Ordine ordine) {
		this.ordini.add(ordine);
	}

	protected String getPreferenzaContatto() {
		return PreferenzaContatto;
	}


	protected void setPreferenzaContatto(String preferenzaContatto) {
		PreferenzaContatto = preferenzaContatto;
	}


}
