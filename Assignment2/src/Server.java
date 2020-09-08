import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.net.Socket;
import java.net.ServerSocket;
public class Server {

    public static void main(String[] args){
        try {
            System.out.println("Waiting for clients..");
            ServerSocket ss = new ServerSocket(9806);
            Socket s = ss.accept();
            System.out.println("Connection Established");
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            int num = Integer.parseInt(in.readLine());
            PrintWriter out = new PrintWriter(s.getOutputStream(),true);
            out.println("Factorial of " + num + " is: " + calculatefact(num));

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    static int calculatefact(int number){
        int fact = 1;
        for(int i=1;i<=number;i++){
            fact = fact*i;
        }
        return fact;
    }
}
