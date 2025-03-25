//package group.chatting.application;
package groupchatting;
import java.net.*;
import java.io.*;
import java.util.*;



//if we implement any interface in java having abstract function in it then there are two scenarios -
//either declare class as abstract class but then you can't instantiate any object of the class,
//the other method is to 
public class Server implements Runnable{
    
    Socket socket;
    
    public static Vector client = new Vector();
    
    public Server(Socket socket){
        try{
            this.socket = socket;
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void run(){
//        user input so use bufferedreader
        try{
            BufferedReader  reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            mwssages of server
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            client.add(writer);
            
//            message will come without any duration
            while(true){
                String data = reader.readLine().trim();
                System.out.println("Received "+ data);
                
//                no of clients for broadcasting
                for(int i=0;i<client.size();i++){
                    try{
                      
                    
                        BufferedWriter bw = (BufferedWriter) client.get(i);
                        bw.write(data);
                        bw.write("\r\n");
                        bw.flush();
//                      message send 
                        
                    }catch(Exception e){
                        e.printStackTrace();
                    }
            }
            }    
            
        }catch(Exception e){
            e.printStackTrace();
            
        }
        
    }
    public static void main(String[] args) throws Exception{
        ServerSocket s = new ServerSocket(2003);
        while(true){
            Socket socket = s.accept();
            Server server = new Server(socket);
            
            Thread thread = new Thread(server);
            thread.start();
//            run method call by default
         
        }
    }
}
