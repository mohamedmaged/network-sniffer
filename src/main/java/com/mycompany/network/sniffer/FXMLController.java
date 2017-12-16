package com.mycompany.network.sniffer;

import java.io.EOFException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;





public class FXMLController implements Initializable {
   // PcapThread p ;
            
    Runnable r=new thread();
        Thread p= new Thread(r);

        public static  MenuItem wifi=new MenuItem("wifi");
      public  MenuItem eth=new MenuItem("ethernet");
        @FXML public MenuButton device;
       @FXML public TableColumn<IpV4Packet,String> source;
       @FXML public TableView <IpV4Packet> table;
       
       
       
 @FXML
    private void wificlicked(ActionEvent event)
    {
        device.setText(wifi.getText());
    }
 @FXML
      private void ethclicked(ActionEvent event)
    {
        device.setText(eth.getText());
    }
      public void w(String x)
      {
          device.setText(x);
      }

    boolean ya=true;
     public static ObservableList <IpV4Packet> packets=FXCollections.observableArrayList();
 public void loginButtonPress(ActionEvent event)
 {
     // p.start();
                   p.setPriority(Thread.MIN_PRIORITY);
                   p.setDaemon(true);
                  p.start();
 }
   
    public void stopsn(ActionEvent event) {
        ya=false;
    }
    
    
  @Override
    public void initialize(URL url, ResourceBundle rb) {
                // TODO
                
                //  p.start();
                  
               // p=new PcapThread();
                source.setCellValueFactory(e->new ReadOnlyStringWrapper(e.getValue().getHeader().getSrcAddr().toString()));
                table.setItems(MainApp.packets);
                wifi.setText("Wifi");
                eth.setText("ethernet");
                device.getItems().addAll(wifi,eth);
                
                
                
                
                
                //  f.setText("wifi");
          
    }    

   
}
