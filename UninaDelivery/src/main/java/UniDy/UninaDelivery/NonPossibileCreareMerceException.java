package UniDy.UninaDelivery;

public class NonPossibileCreareMerceException extends Exception {
	
		private String messaggioErrore;
		private String tipoErrore = "Errore";
		
		public NonPossibileCreareMerceException() {
			messaggioErrore = "Non è possibile creare una merce con i valori inseriti, incoerenza tra parti";
			
		}

		protected String getMessaggioErrore() {
			return messaggioErrore;
		}

		protected String getTipoErrore() {
			return tipoErrore;
		}


}