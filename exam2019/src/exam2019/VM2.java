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

    
    
    
  
public class VM2{
    private ServerSocket serverSocket;
    private Socket socket;
    public static final int port = 10000;
    private static final int poolSize = 10;
    private ExecutorService pool = null;
    private Boolean isFinished = false;
    public ArrayList<ListeDeDiscution> AllList = new ArrayList(); 
    
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
    public VM2(int port, int size) {
       try {
           serverSocket = new ServerSocket(port,size);
           pool = Executors.newFixedThreadPool(poolSize);
        } catch (IOException ex) {
            Logger.getLogger(VM1.class.
                    getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void ManageRequest(){
        while (!isFinished) {
            try { 
                pool.execute(new EsclaveVM2(serverSocket.accept(),this));
            }
            catch (IOException e) {System.out.println(e);}
            finally {
                try { if (socket != null) socket.close();}
                catch (IOException e) {}
            }
        }
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
