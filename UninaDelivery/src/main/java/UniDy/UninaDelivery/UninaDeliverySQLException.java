package UniDy.UninaDelivery;

import java.sql.SQLException;

public class UninaDeliverySQLException extends SQLException {

	String getMessaggioErrore() {
		return "Si è verificato un errore con i Database di UninaDelivery";
	}

	String getTipoErrore() {
		return "UninaDelivery Error";
	}
}
