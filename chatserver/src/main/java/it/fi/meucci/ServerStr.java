package it.fi.meucci;
import java.io.*;
import java.net.*;

public class ServerStr {

    ServerSocket server = null;
    Socket client = null;
    //String stringaRicevuta = null;
    //String stringaModifica = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoCliente;

    public void avvia(){
        try{
            ServerSocket serversocket = new ServerSocket(7777);
            for(;;){
                System.out.println("1 Server in attesa...");
                Socket socket =  serversocket.accept();
                System.out.println("3 Server socket " + socket + '\n');
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
            } 
        }
            catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("Errore durante l'istanza del server");
                System.exit(1);
            }
        }




}
