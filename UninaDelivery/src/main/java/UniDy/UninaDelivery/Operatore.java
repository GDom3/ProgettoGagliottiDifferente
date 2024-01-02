package UniDy.UninaDelivery;

import java.util.Objects;

public class Operatore {

	private String nome;
	private String cognome;
	private String username;
	private String password;
	
	protected Operatore(String usernameIN, String passwordIN){
		username = usernameIN;
		password = passwordIN;
	}
	
	protected void setUsername(String username) {
		this.username = username;
	}
	protected void setPassword(String password) {
		this.password = password;
	}
	protected void setNome(String nome) {
		this.nome = nome;
	}

	protected void setCognome(String cognome) {
		this.cognome = cognome;
	}

	protected String presentati() {
		return nome + " " + cognome;
	}
	
	protected String getUsername() {
		return username;
	}
	
	protected String getPassword() {
		return password;
	}

	protected void impostaCredenziali(String username2, String password2) {
		setUsername(username2);
		setPassword(password2);
	}

	protected String getNome() {
		return nome;
	}

	protected String getCognome() {
		return cognome;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operatore other = (Operatore) obj;
		return Objects.equals(username, other.username);
	}



	

}
