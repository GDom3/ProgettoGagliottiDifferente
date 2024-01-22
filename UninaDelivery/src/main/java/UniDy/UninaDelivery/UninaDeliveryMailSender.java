package UniDy.UninaDelivery;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UninaDeliveryMailSender {
    private String mail;
    private String password;

    protected void leggiCredenziali() {
        try (BufferedReader buffer = new BufferedReader(new FileReader(new File("src/main/java/File/mail.txt")))) {
            mail = buffer.readLine();
            password = buffer.readLine();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    protected void mandaMailaCliente(Cliente cliente) {
        leggiCredenziali();

        try {
            // Verifica che l'indirizzo email del mittente non sia nullo o vuoto
            if (mail == null || mail.isEmpty()) {
                throw new RuntimeException("Indirizzo email del mittente non valido");
            }

            // Creazione di un oggetto Email (si utilizza SimpleEmail per un'email semplice)
            Email email = new SimpleEmail();

            // Configurazione dei dettagli del server SMTP di Outlook
            email.setHostName("smtp-mail.outlook.com");
            email.setSmtpPort(587);

            // Autenticazione con un nome utente e una password
            email.setAuthenticator(new DefaultAuthenticator(mail, password));

            // Abilita l'uso di TLS per la connessione sicura
            email.setStartTLSEnabled(true);

            // Indirizzo email del mittente
            email.setFrom(mail);

            // Indirizzo email del destinatario
            email.addTo(cliente.getEmail());

            // Oggetto dell'email
            email.setSubject("Grazie per l'iscrizione a UninaDelivery");

            // Corpo del messaggio dell'email
            String messaggio = "Benvenuto/a a UninaDelivery, " + cliente.getNome() + " " + cliente.getCognome() + "!\n\n" +
                    "Grazie per esserti iscritto/a a UninaDelivery! Siamo felici di averti con noi.\n" +
                    "\nEcco alcune informazioni sul tuo profilo:\n" +
                    "Codice Fiscale: " + cliente.getCodiceFiscale() + "\n" +
                    "Data di Nascita: " + cliente.getDataNascita() + "\n" +
                    "Numero di Telefono: " + cliente.getNumeroCellulare() + "\n" +
                    "Preferenza di Contatto: " + cliente.getPreferenzaContatto() + "\n\n" +
                    "Cordiali saluti,\nLo staff UninaDelivery";

            email.setMsg(messaggio);

            // Invia l'email
            email.send();

            
            //System.out.println("Email inviata con successo a: " + destinatario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    protected void mandaMailAssunzioneCorriere(Corriere corriere) {
        leggiCredenziali();

        try {
            // Verifica che l'indirizzo email del mittente non sia nullo o vuoto
            if (mail == null || mail.isEmpty()) {
                throw new RuntimeException("Indirizzo email del mittente non valido");
            }

            // Creazione di un oggetto Email (si utilizza SimpleEmail per un'email semplice)
            Email email = new SimpleEmail();

            // Configurazione dei dettagli del server SMTP di Outlook
            email.setHostName("smtp-mail.outlook.com");
            email.setSmtpPort(587);

            // Autenticazione con un nome utente e una password
            email.setAuthenticator(new DefaultAuthenticator(mail, password));

            // Abilita l'uso di TLS per la connessione sicura
            email.setStartTLSEnabled(true);

            // Indirizzo email del mittente
            email.setFrom(mail);

            // Indirizzo email del destinatario
            email.addTo(corriere.getEmail());

            // Oggetto dell'email
            email.setSubject("Assunzione presso UninaDelivery");
            
            // Corpo del messaggio dell'email
            String messaggio = "Gentile " + corriere.getNome() + " " + corriere.getCognome() + ",\n\n" +
                    "Siamo felici di informarti che sei stato assunto presso UninaDelivery come corriere.\n" +
                    "Dettagli dell'assunzione:\n" +
                    "- Patenti possedute: " + corriere.getPatente()+"\n" +
                    "- Salario: " + corriere.getContratto() + "€\n\n" +
                    "- Recapito contatto : " + corriere.getNumeroCellulare() + "\n\n" +
                    "- mail: " + corriere.getEmail() + "\n\n";
            
            //if(corriere.getCodiceFiscaleCordinatore() != null)
            	//messaggio = messaggio + "\n\nPuoi chiedere iformazioni al tuo coordinatore: "+ corriere.getCodiceFiscaleCordinatore();            	
            
       
            messaggio = messaggio + "\n\nBenvenuto nel nostro team!\n\n" +
                                   "Cordiali saluti,\nLo staff UninaDelivery";
            
            email.setMsg(messaggio);

            // Invia l'email
            email.send();

            // Stampa un messaggio di conferma nella console (puoi scommentare questa linea se desideri)
            // System.out.println("Email inviata con successo a: " + destinatario);
        } catch (Exception e) {
            // Se si verifica un'eccezione, la rilancia come RuntimeException (può essere gestita a livello superiore)
            throw new RuntimeException(e);
        }
    }

    
    
}
