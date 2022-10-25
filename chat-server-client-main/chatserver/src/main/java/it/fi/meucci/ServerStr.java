package it.fi.meucci;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerStr {

    ServerSocket server = null;
    Socket client = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoCliente;
    ArrayList<Socket> socketConnessi = new ArrayList<>();

    public void avvia(){
        try{
            ServerSocket serversocket = new ServerSocket(7777);
            for(;;){
                System.out.println("1 Server in attesa...");
                Socket socket =  serversocket.accept();
                System.out.println("3 Server socket " + socket + '\n');
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
                socketConnessi.add(client);
                if(serverThread.flag == false){
                    this.chiusuraCompleta();
                }
            } 
        }
            catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("Errore durante l'istanza del server");
                System.exit(1);
            }
        }

        public void chiusuraCompleta() throws IOException {
            for(int i=0; i<socketConnessi.size(); i++){
                socketConnessi.get(i).close();   
            }
        }

}
