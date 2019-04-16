/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam2019;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author krich
 */



public class Clienttest {
    public static enum theme {
        sociale ("sociale"), 
        evenement ("evenement"), 
        reunion ("reunion"),  
        nouvelles ("nouvelles");
        private String theme;
        theme(String theme) {
            this.theme=theme;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        // TODO code application logic here
        Socket connexion = new Socket(InetAddress.getLocalHost(),10000);
        Writer output = new OutputStreamWriter(connexion.getOutputStream(), "8859_1");
        
        /**************** TEST of server ************/
        
        Scanner lecture = new Scanner(System.in);
        System.out.println("Choisi le numero de l'action à faire");
        System.out.println("-------------------------\n");
        System.out.println("1 - Creer une liste de discution");
        System.out.println("2 - Supprimer une liste de discution");
        System.out.println("3 - S'abonner à une liste de discution");
        System.out.println("4 - Se désabonner d'une liste de discution");
        System.out.println("5 - Envoyer un message à une liste de discution");
        System.out.println("6 - Afficher toutes les listes de discution");
        System.out.println("7 - Creer une liste de Personnes");
        System.out.println("8 - Creer une liste de Contenus");
        System.out.println("9 - Afficher toutes les listes de Personnes");
        System.out.println("10 - Afficher toutes les listes de Contenus");
        System.out.println("11- Quitter");
        int choix = lecture.nextInt();
        /***************************************************/
        
        switch (choix) {
            case 1:
                //Creation liste
                Scanner sc = new Scanner(System.in);
                System.out.println("Veuillez saisir le nom de la liste :");
                String nomliste = sc.nextLine();
                System.out.println("Veuillez saisir le sujet qui vous interesse");
                theme th = theme.valueOf(sc.nextLine());
                System.out.println("Veuillez saisir votre adresse mail :");
                String diffuseur = sc.nextLine();
                System.out.println("Veuillez saisir votre mot de passe :");
                String mdp = sc.nextLine();
                output.write("create_list "+nomliste+" "+th+" "+diffuseur+" "+mdp); output.flush();
                connexion.shutdownOutput();
                break;
            case 2:
                //supprimer liste
                Scanner in = new Scanner(System.in);
                System.out.println("Veuillez saisir le nom de la liste que vous voulez supprimer :");
                String nomListe = in.nextLine();
                System.out.println("Veuillez saisir le mdp de la liste");
                String pswd = in.nextLine();
                output.write("remove_list "+nomListe+" "+pswd);
                output.flush();connexion.shutdownOutput();
                break;
            case 3:
                //s'abonner à une liste
                Scanner ab = new Scanner(System.in);
                System.out.println("Veuillez saisir le nom de la liste que vous voulez s'abonner :");
                String NomListe = ab.nextLine();
                System.out.println("Veuillez saisir votre mail");
                String mail = ab.nextLine();
                output.write("subscribe_list "+NomListe+" "+mail);
                output.flush();connexion.shutdownOutput();
                break;
            case 4 :
                //se désabonner d'une liste
                Scanner desaab = new Scanner(System.in);
                System.out.println("Veuillez saisir le nom de la liste que vous voulez se désabonner :");
                String Nomliste = desaab.nextLine();
                System.out.println("Veuillez saisir votre mail");
                String Mail = desaab.nextLine();
                output.write("unscribe_list "+Nomliste+" "+Mail);
                output.flush();connexion.shutdownOutput();
                break;
            case 5 :
                //envoyer un message à une liste de diff
                Scanner msg = new Scanner(System.in);
                System.out.println("Veuillez saisir le nom de la liste :");
                String liste = msg.nextLine();
                System.out.println("Veuillez saisir votre mail");
                String sender = msg.nextLine();
                System.out.println("Veuillez saisir votre mdp");
                String Pswd = msg.nextLine();
                System.out.println("Veuillez saisir l'objet de votre mail");
                String object = msg.nextLine();
                System.out.println("Veuillez saisir le corps de votre mail");
                String body = msg.nextLine();
                output.write("send_email_to_list "+liste+" "+sender+" "+Pswd+" "+object+" "+body);
                output.flush();
                connexion.shutdownOutput();
                break;
            case 6:
                output.write("afficher_list"); output.flush();
                connexion.shutdownOutput();
                break;      
            case 7:
                
                Scanner sc1 = new Scanner(System.in);
                System.out.println("Veuillez saisir le nom de la liste :");
                String nomlistep = sc1.nextLine();
                System.out.println("Veuillez saisir le sujet qui vous interesse");
                String nom = sc1.nextLine();
                System.out.println("Veuillez saisir votre adresse mail :");
                String email = sc1.nextLine();
                output.write("create_list "+nomlistep+" "+nom+" "+email); output.flush();
                connexion.shutdownOutput();
                
                
           case 8:
               
                Scanner sc2 = new Scanner(System.in);
                System.out.println("Veuillez saisir le nom de la liste :");
                String nomlistec = sc2.nextLine();
                System.out.println("Veuillez saisir le sujet qui vous interesse");
                String date = sc2.nextLine();
                System.out.println("Veuillez saisir votre adresse mail :");
                String text = sc2.nextLine();
                output.write("create_list "+nomlistec+" "+date+" "+text); output.flush();
                connexion.shutdownOutput();
                
     
                
            break;

            case 9:
                output.write("afficher_listp"); output.flush();
                connexion.shutdownOutput();
                break;                
            case 10:

                output.write("afficher_listc"); output.flush();
                connexion.shutdownOutput();
                break;                
    
           
           case 11:
               
               
               System.out.println("Au revoir");

                
            break;
        
 
        
        }
        

        }
        
        
        
        
    }
    

