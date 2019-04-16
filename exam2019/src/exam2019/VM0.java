/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam2019;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krich
 */

    
    
    
  
public class VM0 {
    
    
    private ServerSocket serverSocket;
    private Socket socket;
    public static final int port = 10000;
   // private static final int poolSize = 5;
    private ExecutorService pool = null;
    private Boolean isFinished = false;
    public ArrayList<ListeDeDiscution> AllList = new ArrayList(); 
    public ArrayList<ListeDePersonne> AllList1 = new ArrayList(); 
    public ArrayList<ListeDeContenu> AllList11= new ArrayList(); 

    //Getters&setters
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ExecutorService getPool() {
        return pool;
    }

    public void setPool(ExecutorService pool) {
        this.pool = pool;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }
    
    
    public ArrayList<ListeDeDiscution> getAllList() {
        return AllList;
    }

    public void setAllList(ArrayList<ListeDeDiscution> AllList) {
        this.AllList = AllList;
    }
      
    //constructor
    public VM0(int port, int size) {
       try {
           serverSocket = new ServerSocket(port,size);
         //  pool = Executors.newFixedThreadPool(poolSize);
        } catch (IOException ex) {
            Logger.getLogger(VM1.class.
                    getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void ManageRequest(){

    }
    
    public boolean ManageList(ListeDeDiscution l)
    {
        boolean added = AllList.add(l);
        return added;
    }

    void DeleteList(ListeDeDiscution n) {
        AllList.remove(n);
    }


}
