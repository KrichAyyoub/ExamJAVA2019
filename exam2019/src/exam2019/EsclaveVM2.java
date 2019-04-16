/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam2019;

/**
 *
 * @author krich
 */



import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

public class EsclaveVM2 implements Runnable {
    private final Socket socket;
    private final VM2 serveur;
 
        

   
    EsclaveVM2(Socket socket, VM2 serveur) {
        this.socket = socket;
        this.serveur = serveur;
    }

    
   
  
    @Override
    public void run() {
        try { 
            //initialisations:
            Writer output = new OutputStreamWriter(socket.getOutputStream()); //flux de sortie
            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream(),
                            "8859_1"),1024); // flux en lecture
            StringBuilder sb = new StringBuilder();
            //lire les données reçu:
            sb.append(input.readLine());
            //on commence à traiter la requête:
            String[] commande = sb.toString().split(" ");
            
            ListeDeDiscution l = null;
            //test sur commande
            boolean test; // un boolean pour la verification si l'execution s'est bien passé ou pas
            switch (commande[0]) {
                case "create_list": 
                    test = createList(commande);                    
                    if (test)
                        output.write("liste cree avec succes !");
                    else
                        output.write("liste non cree");
                    break;
                    
                case "remove_list":
                    String nom = commande[1];
                    String pswd = commande[2];
                    test = deleteList(nom,pswd);
                    if (test)
                        output.write("la liste "+nom+" supprime avec succes !");
                    else
                        output.write("liste "+nom+" non supprime");
                    break;
                    
                case "subscribe_list":
                    String liste = commande[1];
                    String mail = commande[2];
                    test=subscribeList(liste,mail);
                    if (test)
                        output.write("abonne ajoute a la liste "+liste+" avec succes !");
                    else
                        output.write("abonne non ajoute, verifier si la liste existe");
                    break;
                case "unscribe_list":
                    String Liste = commande[1];
                    String Mail = commande[2];
                    test=unscribeList(Liste,Mail);
                    if (test)
                        output.write("abonne supprime de la liste "+Liste+" avec succes !");
                    else
                        output.write("abonne non supprime, verifiez s'il existe bien");
                    break;
                case "send_email_to_list":
                    String _Liste = commande[1];
                    String sender = commande[2];
                    String Pswd = commande[3];
                    String subject = commande[4];
                    String body = commande[5];
                    test= sendMail(_Liste,sender,Pswd,subject,body);
                    if (test)
                        output.write("Email envoyé");
                    else
                        output.write("erreur lors de l'envoi du mail..."
                                + "causes possible: sécurité de gmail, adresse email invalide, pas de connection internet");
                    break;
      
                    
                case "afficher_list":
                    String response=afficheList();
                    output.write(response);
                    break;
                default: ;
            }
            output.flush();
        }
        catch (IOException e) {System.out.println(e);} 
        catch (AddressException ex) {
            Logger.getLogger(Esclave.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("adresse mail invalide");
        }
        finally {
            try { if (socket != null) socket.close();}
            catch (IOException e) {}
        }    }

    public boolean createList(String[] commande) {
        ListeDeDiscution L=new ListeDeDiscution(commande[1],commande[2],commande[3],commande[4]);
        return (serveur.ManageList(L));
        
    }
    
    public String afficheList(){
        List<ListeDeDiscution> AllList = serveur.getAllList();
        int i =1;
        String affich ="";
        affich+="Il y'a "+ String.valueOf(AllList.size())+ " liste de diffusion : ";
        //System.out.println("Il y'a "+ AllList.size()+ " liste de diffusion :");
        //AllList.stream().forEach((x) -> System.out.println(x));
        for(ListeDeDiscution n : AllList)
            { 
                affich+= " liste numero (" + String.valueOf(i);
                                affich+= "), Nom: "+ n.nomListe + ", theme: "+ n.getTheme()
                                +" , nombre d'abonnées: "+ n.getAbonnes().size()+ "\n ";
                i++;
            }
        return affich;

    }
        
    private boolean deleteList(String nomlist, String pswd) {
        ArrayList<ListeDeDiscution> allList = serveur.getAllList();
        boolean deleted =
                allList.removeIf(l->l.getPassword().equals(pswd) && l.getNomListe().equals(nomlist));
        if(deleted){
            this.afficheList();}
        return deleted;
    }

    private boolean subscribeList(String liste, String mail) throws AddressException {
        ArrayList<ListeDeDiscution> allList = serveur.getAllList();
        boolean added = false ;
        for(ListeDeDiscution list : allList){
            if(list.getNomListe().equals(liste)){
                return (added = list.addAbonne(mail));
               
            }
        }
        return added;
    }
    
    private boolean unscribeList(String liste, String Mail) {
        ArrayList<ListeDeDiscution> allList = serveur.getAllList();
        //boolean deleted = allList.removeIf(list->list.getNomListe().equals(liste));
        //ce qui est en haut est faut: il supprime toute la liste au lieu de supprimé un abonné!
        //le suivant est correcte:
        boolean deleted=false;
        searchloop:
        for(ListeDeDiscution n : allList)
        {
            if(n.getNomListe().equals(liste))
            {deleted= n.removeAbonne(Mail);
            }
        }
        return deleted;
    }
    
    private ListeDeDiscution GetList(String nomListe) {

        Optional<ListeDeDiscution> list =
                serveur.getAllList().stream().filter(l -> l.getNomListe().equals(nomListe)).findFirst();
        if (list.isPresent()){
            ListeDeDiscution ListeDeDiscution = list.get();
            return ListeDeDiscution;
        }
        return null;
    }
   
    private boolean sendMail(String NameList, String from, String password, String MailSubject, String MailBody)
    {
        ListeDeDiscution liste = GetList(NameList);
        if (liste ==null)
        {
            System.out.println("liste de diffusion n'existe pas!!");
            return false;
        }
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");     
        Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
                        @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		  });
        try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);
         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));
         // Set To: header field of the header.
         List<Personne> Abonnes = liste.getAbonnes();
         List<String> to =new ArrayList();
         String receivers;
         Abonnes.forEach((p) -> {
             to.add(p.getMailAdress());
            });
         receivers = String.join(",", to);
         InternetAddress[] parse = InternetAddress.parse(receivers , true);
         message.setRecipients(javax.mail.Message.RecipientType.TO,  parse);
         // Set Subject: header field
         message.setSubject(MailSubject);
         // Now set the actual message
         message.setText(MailBody);
         // Send message
         Transport.send(message);
         return true;
      } catch (MessagingException mex) {
         return false;
      }
   }        

   
    
}
