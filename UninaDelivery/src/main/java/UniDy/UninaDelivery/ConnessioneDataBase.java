package UniDy.UninaDelivery;

import java.io.*;
import java.sql.*;
import java.util.*;

public class ConnessioneDataBase {
	private static ConnessioneDataBase istanzaClasse = null;
	private Connection connessione = null;
	private String driver = "org.postgresql.Driver";
	//private String indirizzo = "jdbc:postgresql://localhost:5432/UninaDelivery";
	private String indirizzo = "jdbc:postgresql://localhost:5432/postgres";
	private String tipoDB = "postgres";
	private String messaggioErrore;
	
	
	
	private ConnessioneDataBase(){} //Costruttore privato
	
	protected static ConnessioneDataBase getConnessioneDataBase(){   
		
        if (istanzaClasse == null) //Se la classe connessione è vuota, fa la new 
        	istanzaClasse = new ConnessioneDataBase();
        
        return istanzaClasse;
    }
	
	//Creo la connessione 
	protected Connection getConnection(){
        String passwordDataBase = null;
        BufferedReader buffer = null;
       
        try{   
            if(connessione==null || connessione.isClosed()){ //Quando la connessione non c'è o ormai è stata chiusa   
            	
            	//Prendiamo la password dal file
                buffer = new BufferedReader(new FileReader(new File("src/main/java/File/Password.txt")));
                passwordDataBase = buffer.readLine();
                buffer.close();
                
                Class.forName(driver); //Carica il driver adottato
                
                // Chiamiamo il DriverManager e chiediamo la connessione necessaria
                connessione = DriverManager.getConnection(indirizzo, tipoDB, passwordDataBase);
                
            }
        } catch (SQLException throwables) {
        	messaggioErrore = "Errore nella comunicazione con il database (" + throwables.getErrorCode() + ")";
        }catch(ClassNotFoundException classeNonTrovata) {
        	messaggioErrore = "Driver per connettersi al database non trovato";
        }catch(IOException fileNonTrovato){
        	messaggioErrore = "File contenente la password del database non trovato";	
        }

        return connessione;
    }
	///Creo la connessione con lo schema
    public Connection getConnectionConSchema(String nomeSchema){
        String passwordDataBase = null;
        BufferedReader buffer = null;
        
        if (Objects.equals(nomeSchema, ""))
            throw new RuntimeException("Nome schema è vuoto");
        try {   
        	if(connessione==null || connessione.isClosed()){ //Quando la connessione non c'è o ormai è stata chiusa   
       
        		//Prendiamo la password dal file
                buffer = new BufferedReader(new FileReader(new File("src/main/java/File/Password.txt")));
                passwordDataBase = buffer.readLine();
                   
                Class.forName(driver); //Carica il driver adottato
                
                String indirizzoConSchema = indirizzo + "?currentSchema=" + nomeSchema; //Aggiungo all'url anche lo schema
                
                // Chiamiamo il DriverManager e chiediamo la connessione necessaria
                connessione = DriverManager.getConnection(indirizzoConSchema, tipoDB, passwordDataBase);

            }
        }catch (SQLException throwables) {
        	messaggioErrore = "Errore nella comunicazione con il database (" + throwables.getErrorCode() + ")";
        }catch(ClassNotFoundException classeNonTrovata) {
        	messaggioErrore = "Driver per connettersi al database non trovato";
        }catch(IOException fileNonTrovato){
        	messaggioErrore = "File contenente la password del database non trovato";	
        }
        
        return connessione;
    }
    
    protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	
}
