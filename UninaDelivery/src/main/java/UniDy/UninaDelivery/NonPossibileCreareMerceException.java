package UniDy.UninaDelivery;

public class NonPossibileCreareMerceException extends Exception {
	
		private String messaggioErrore= "Non è possibile creare una merce con i valori inseriti, incoerenza tra parti";
		private String tipoErrore = "Errore";
		
		public NonPossibileCreareMerceException() {
			
		}

		protected String getMessaggioErrore() {
			return messaggioErrore;
		}

		protected String getTipoErrore() {
			return tipoErrore;
		}


}