/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package switcherexample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Sam Richards
 */
public class MainMenu extends Switchable implements Initializable 
{
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }
    
    @FXML
    private void handleGoToB(ActionEvent event) 
    {
        Switchable.switchTo("BlackScholesCalculatorFXML");
    }
    
//    @FXML
//    private void handleGoToC(ActionEvent event) 
//    {
//        Switchable.switchTo("C");
//    }
    
    //quit game
    @FXML
    private void exitGame(ActionEvent event) 
    {
        System.exit(0);
    }
    
    //info about me and the game
    @FXML
    private void aboutMenu(ActionEvent event) 
    {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("About");
        about.setHeaderText("Black Scholes Option Modeling Application ");
        about.setContentText("Sam Richards final project for CS 3330.\n\n This is an application for modeling Call and Put Option Prices.\n Opening the new model will allow you to put in your own input for S(stock price), K(strike price), σ(stdev), r(interest rate), T(time), and δ(dividend yield).\n\n Hitting the button for info gives the user a general background on the Black Scholes Equation. Click on the buttons below each input for more information about specific inputs!");
        about.showAndWait();
    }
    
}