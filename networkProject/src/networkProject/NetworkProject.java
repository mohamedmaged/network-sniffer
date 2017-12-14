/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package networkProject;
import java.util.Scanner;
import jpcap.packet.*;
import jpcap.*;

/**
 *
 * @author Gina Salib
 */
public class NetworkProject {
    NetworkInterface [] NETWORK_INTERFACES;
    JpcapCaptor CAP;
    int index;
    static boolean CaptureState=false;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     // System.out.println(System.getProperty("java.library.path"));\
     Scanner sc = new Scanner(System.in);
     NetworkProject mypcap = new NetworkProject();
     mypcap.ListInterfaces();
     mypcap.ChooseInterface();
     mypcap.CapturePackets();
        if (sc.nextInt()==1)
            CaptureState=false;
        // TODO code application logic here
       
      
       
    }
    void ListInterfaces()
    {
        NETWORK_INTERFACES = JpcapCaptor.getDeviceList();
       for(int i=0;i<NETWORK_INTERFACES.length;i++)
       {
           System.out.println(NETWORK_INTERFACES[i].name+" "+NETWORK_INTERFACES[i].description+" "+NETWORK_INTERFACES[i].datalink_name+" "+NETWORK_INTERFACES[i].datalink_description);
           //byte[]R= NETWORK_INTERFACES[i].mac_address;
           System.out.println("MAC address ");
           for(byte X: NETWORK_INTERFACES[i].mac_address)
           System.out.print(Integer.toHexString(X&0xff)+":");
           System.out.println();
           
           NetworkInterfaceAddress [] INT =NETWORK_INTERFACES[i].addresses;
           System.out.println("IP:" +INT[i].address);
           System.out.println("Subnet:" +INT[i].subnet);
           System.out.println("broadcast:" +INT[i].broadcast);
           
       }
      
}
     void ChooseInterface()
       {
           index=1;
            //todo save index of interface and make sure it is a valid number
       }
     void CapturePackets()
     {
         MyPacketReceiver.capturedPackets.clear();
         CaptureState = true;
          new Thread(new Runnable() {
             @Override
             public void run() {
                 try{
                     CAP = JpcapCaptor.openDevice(NETWORK_INTERFACES[index], 65535, false, 20);
                     while(CaptureState){
                         CAP.processPacket(1, new MyPacketReceiver());
                     }
                     CAP.close();
                 
                 }
                  catch (Exception e){
                  }
                    
                 
             }
         }
         ).start();

          
     }
}