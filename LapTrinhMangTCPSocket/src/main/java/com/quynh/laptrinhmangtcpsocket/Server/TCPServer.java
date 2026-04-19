/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.quynh.laptrinhmangtcpsocket.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServer {

    public static void main(String[] args) {
        ServerSocket server = null;
        Socket cl =  null;
        
        BufferedReader inp = null;
        PrintWriter outp = null;
        
        int pServer = 3456;
        
        String s;
        InetAddress address;
        
        int pClient;
        
        try{
            server = new ServerSocket(pServer);
            System.out.println("Server is ready....");
            cl = server.accept();
            if(cl!= null){
               inp = new BufferedReader(new InputStreamReader(cl.getInputStream()));
               outp = new PrintWriter(cl.getOutputStream(),true);
               s = inp.readLine();
               pClient = cl.getPort();
               address = cl.getInetAddress();
                System.out.println("Client: ");
                System.out.println("Name: " + address.getHostName());
                System.out.println("IP: " + address.getHostAddress());
                System.out.println("Port: " + pClient);
                System.out.println("Data: " + s);
                s = s.toUpperCase();
                outp.println(s);
            }
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                if(inp != null){
                    inp.close();
                }
                if(outp != null){
                    outp.close();
                }
                if(cl!= null){
                    cl.close();
                }
                if(server!= null){
                    server.close();
                }
            }catch(IOException e){
                System.out.println(e);
            }
        }

    }
}
