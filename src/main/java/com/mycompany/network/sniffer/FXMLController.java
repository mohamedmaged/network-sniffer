package com.mycompany.network.sniffer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.HBox;
import static sun.audio.AudioDevice.device;

public class FXMLController implements Initializable {
    
         
        @FXML public MenuButton device;
        @FXML public MenuItem wifi;
        @FXML public MenuItem eth;
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

  @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        wifi.setText("Wifi");
        eth.setText("ethernet");
   
      //  f.setText("wifi");
    }    
}
