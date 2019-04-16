/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam2019;

/**
 *
 * @author ameni
 */
public class Main {
    
    public static void main(String[] args) {
        VM1 serveurListe = new VM1(VM1.port, 1);
        serveurListe.ManageRequest();
    } 
}
