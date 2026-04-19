/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quynh.laptrinhmangtcpsocket.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Smileboiz
 */
public class TCPClient {
    public static void main(String[] args) {
       Socket client = null;
       BufferedReader input = null;
       PrintWriter output = null;
       int pserver = 3456;
       String ipserver = "localhost";
       String s = "hello world";
       try{
           client = new Socket(ipserver,pserver);
           input = new BufferedReader(new InputStreamReader(client.getInputStream()));
           output = new PrintWriter(client.getOutputStream(),true);
           output.println(s);
           System.out.println("Gui du lieu thanh cong den server");
           s = input.readLine();
           System.out.println("Du lieu nhan tu server: " + s);
       } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
       finally{
           try{
               if(input != null){
                   input.close();
               }
               if(output != null){
                   output.close();
               }
               if(client != null){
                   client.close();
               }
           }catch(IOException ex){
               System.out.println(ex);
           }
       }
       
    }
}
