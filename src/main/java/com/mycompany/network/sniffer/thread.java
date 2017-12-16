/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.network.sniffer;

import java.io.EOFException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;

/**
 *
 * @author User
 */
public class thread implements Runnable{
     PcapHandle handle ;
InetAddress addr ;
    @Override
    public void run() {
    try {
        Packet packet = handle.getNextPacketEx();
        System.out.println(packet);
        IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
      
        FXMLController.packets.add(ipV4Packet);
        } 
        catch (PcapNativeException ex) {
        Logger.getLogger(thread.class.getName()).log(Level.SEVERE, null, ex);
    } catch (EOFException ex) {
        Logger.getLogger(thread.class.getName()).log(Level.SEVERE, null, ex);
    } catch (TimeoutException ex) {
        Logger.getLogger(thread.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NotOpenException ex) {
        Logger.getLogger(thread.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
   public thread()
   {
       try 
        } catch (UnknownHostException ex) {
            Logger.getLogger(PcapThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PcapNativeException ex) {
            Logger.getLogger(PcapThread.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
