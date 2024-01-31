package UniDy.UninaDelivery;

public class UninaDeliveryException extends Exception{

	String getMessaggioErrore() {
		return "C'è stato un errore nel servizio di UninaDelivery";
	}

	String getTipoErrore() {
		return "UninaDelivery Error";
	}

}