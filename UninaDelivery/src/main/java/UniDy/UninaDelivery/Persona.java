package UniDy.UninaDelivery;

import java.time.LocalDate;

public class Persona {
	protected String codiceFiscale;
	protected String nome;
	protected String cognome;
	protected String email;
	protected String numeroCellulare;
	protected LocalDate DataNascita;
	
	protected String getCodiceFiscale() {
		return codiceFiscale;
	}
	
	protected void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	protected String getNome() {
		return nome;
	}

	protected void setNome(String nome) {
		this.nome = nome;
	}

	protected String getCognome() {
		return cognome;
	}

	protected void setCognome(String cognome) {
		this.cognome = cognome;
	}

	protected String getEmail() {
		return email;
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	protected String getNumeroCellulare() {
		return numeroCellulare;
	}

	protected void setNumeroCellulare(String numeroCellulare) {
		this.numeroCellulare = numeroCellulare;
	}

	protected LocalDate getDataNascita() {
		return DataNascita;
	}

	protected void setDataNascita(LocalDate dataNascita) {
		DataNascita = dataNascita;
	}
	
}
