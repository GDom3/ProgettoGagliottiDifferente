package UniDy.UninaDelivery;

public class Hal {
	
	FinestraLogin loginWindow;
	FinestraMenu menuWindow;
	
	public static void main(String[] args) {
		
		Hal gestoreApplicazione = new Hal();
		
	}

	public Hal() {
		loginWindow = new FinestraLogin(this);
		menuWindow = new FinestraMenu(this);
		
		
		
		
		loginWindow.setVisible(true);
	}

	public void accesso(String username, String password) {
		
		//loginWindow.messaggioPopUp("Errore : Username non esistente", "Attenzione");
		//loginWindow.messaggioPopUp("Errore : Password errata", "Attenzione");
		
		
		
	}

}
