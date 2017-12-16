/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.network.sniffer;

import java.io.EOFException;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.util.NifSelector;



/**
 *
 * @author User
 */
public class PcapThread {
    public ObservableList <IpV4Packet> packets=FXCollections.observableArrayList();
    boolean ya=false;
     PcapHandle handle ;
    public PcapThread()
    {
        try {
            InetAddress addr = InetAddress.getByName("12.0.0.191");
            PcapNetworkInterface nif = Pcaps.getDevByAddress(addr);
            int snapLen = 65536;
            PromiscuousMode mode = PromiscuousMode.PROMISCUOUS;
            int timeout = 50;
             handle = nif.openLive(snapLen, mode, timeout);
        } catch (UnknownHostException ex) {
            Logger.getLogger(PcapThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PcapNativeException ex) {
            Logger.getLogger(PcapThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void start (){
    Service<Void> service = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {           
                @Override
                protected Void call() throws Exception {
                    //Background work    
                    
                    Packet packet = handle.getNextPacketEx();
                                      System.out.println(packet);
 IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);

                    
                    final CountDownLatch latch = new CountDownLatch(1);
                    Platform.runLater(new Runnable() {                          
                        @Override
                        public void run() {
                           try{
                             
FXMLController.packets.add(ipV4Packet);
                               
                  
                                //FX Stuff done here
                           
                               //FX Stuff done here
                          
                            }finally{
                                latch.countDown();
                            }
                        }
                    });
                    latch.await();                      
                    //Keep with the background work
                    return null;
                }
            };
        }
    };
    service.start();
    }
    public void stop()
    {
        handle.close();
     
    }
}
