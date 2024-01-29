package UniDy.UninaDelivery;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UninaDeliveryMailSender {
    private String mail;
    private String password;

    protected void leggiCredenziali() throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader(new File("src/main/java/File/mail.txt")));
        mail = buffer.readLine();
        password = buffer.readLine();
    }

    protected void mandaMailaCliente(Cliente cliente) throws EmailException, IOException {
        leggiCredenziali();
        
        // Creazione di un oggetto HtmlEmail
        Email email = new HtmlEmail();
        
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
        
        //subject della mail
        email.setSubject("Grazie per l'iscrizione a UninaDelivery");
        
        //messaggio
        String messaggio = "<html><body>" +
                "<p>Benvenuto/a " + cliente.getNome() + " " + cliente.getCognome() + " a UninaDelivery! </p>" +
                "<p>Grazie per esserti iscritto/a a UninaDelivery! Siamo felici di averti con noi.</p>" +
                "<p>Ecco alcune informazioni sul tuo profilo:</p>" +
                "<ul>" +
                "<li>Codice Fiscale: " + cliente.getCodiceFiscale() + "</li>" +
                "<li>Data di Nascita: " + cliente.getDataNascita() + "</li>" +
                "<li>Numero di Telefono: " + cliente.getNumeroCellulare() + "</li>" +
                "<li>Preferenza di Contatto: " + cliente.getPreferenzaContatto() + "</li>" +
                "</ul>" +
                "<p>Cordiali saluti,<br/>Lo staff UninaDelivery</p>" +
                "</body></html>";
        
        //setta il messaggio
        email.setMsg(messaggio);
        
        //manda la mail
        email.send();
    }

    protected void mandaMailAssunzioneCorriere(Corriere corriere) throws EmailException, IOException {
        leggiCredenziali();

     // Creazione di un oggetto HtmlEmail
        Email email = new HtmlEmail();
        
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
        
        //subject della mail
        email.setSubject("Grazie per l'iscrizione a UninaDelivery");
        
        //messaggio
        String messaggio = "<html><body>" +
                "<p>Gentile " + corriere.getNome() + " " + corriere.getCognome() + ",</p>" +
                "<p>Siamo felici di informarti che sei stato assunto presso UninaDelivery come corriere.</p>" +
                "<p>Dettagli dell'assunzione:</p>" +
                "<ul>" +
                "<li>Patenti possedute: " + corriere.getPatente() + "</li>" +
                "<li>Salario: &euro;" + corriere.getContratto() + "</li>" +
                "<li>Recapito contatto: " + corriere.getNumeroCellulare() + "</li>" +
                "<li>Email: " + corriere.getEmail() + "</li>" +
                "</ul>" +
                "<p>Benvenuto nel nostro team!</p>" +
                "<p>Cordiali saluti,<br/>Lo staff UninaDelivery</p>" +
                "</body></html>";


        //setta il messaggio
        email.setMsg(messaggio);
        
        //manda la mail
        email.send();
    }

    protected void informaStatoOrdineCambiato(Ordine ordineModificato) throws EmailException, IOException {
        leggiCredenziali();
        
        // Creazione di un oggetto HtmlEmail
        Email email = new HtmlEmail();
        
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
        email.addTo(ordineModificato.getAcquirente().getEmail());
        
        //subject della mail
        email.setSubject("Cambio Stato Ordine UninaDelivery");
        
        //messaggio
        String messaggio = "<html><body>" +
                "<p>Gentile " + ordineModificato.getAcquirente().getNome() + " " + ordineModificato.getAcquirente().getCognome() + ",</p>" +
                "<p>Le informiamo che il suo ordine ha subito una variazione di stato:</p>" +
                "<ul>" +
                "<li>STATO ATTUALE: " + ordineModificato.getStatoOrdine() + "</li>" +
                "<li>DATA ACQUISTO: " + ordineModificato.getDataE() + "</li>" +
                "<li>DATA CONSEGNA: " + ordineModificato.getDataConsegna() + "</li>" +
                "</ul>" +
                "<p>Cordiali saluti,<br/>Lo staff UninaDelivery</p>" +
                "</body></html>";

        //setta il messaggio
        email.setMsg(messaggio);
        
        //manda la mail
        email.send();
    }
}
