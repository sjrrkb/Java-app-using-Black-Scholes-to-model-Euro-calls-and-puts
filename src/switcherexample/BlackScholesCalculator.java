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
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFormattedTextField;


/**
 * FXML Controller class
 *
 * @author Sam Richards
 */
public class BlackScholesCalculator extends Switchable implements Initializable  {

    private PutModel putModel = new PutModel();
    private CallModel callModel = new CallModel();
    private MenuFail switcherExample;
    
    
    
    @FXML
    private TextField bText; 
  
    @FXML
    private TextField stock;
   
    
    @FXML
    private TextField strike;
    
    @FXML
    private TextField volatility;
    
    
    @FXML
    private TextField interest;
    
    @FXML
    private TextField time;
    
    @FXML
    private TextField dividend;
    
    @FXML
    private TextField callPrice;
    
    @FXML
    private TextField putPrice;
    
    @FXML
    private Button call;
    
    @FXML
    private Button put;
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
   @FXML
    private void aboutMenu(ActionEvent event) 
    {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("About");
        about.setHeaderText("Black Scholes Option Modeling Application ");
        about.setContentText("Sam Richards final project for CS 3330.\n\n This is an application for modeling Call and Put Option Prices.\n Opening the new model will allow you to put in your own input for S(stock price), K(strike price), σ(stdev), r(interest rate), T(time), and δ(dividend yield).\n\n Hitting the button for info gives the user a general background on the Black Scholes Equation. Click on the buttons below each input for more information about specific inputs!");
        about.showAndWait();
    }
    
   @FXML
   private void stockInfo(ActionEvent event)
   {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("Stock Information");
        about.setHeaderText("WTF is a stock input?");
        about.setContentText("Black Scholes stock input is the current price of the stock you own or wish to model.");
        about.showAndWait();
   }
   
   @FXML
   private void strikeInfo(ActionEvent event)
   {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("Strike Information");
        about.setHeaderText("WTF is a strike input?");
        about.setContentText("Black Scholes strike input is the Option striking price for the put or call. This means that if one owns a call, the option is exercised once S(stock)>K(strike). If it is a put then the option is exercised once S<K ");
        about.showAndWait();
   }
   
   @FXML
   private void volatilityInfo(ActionEvent event)
   {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("Volatility Information");
        about.setHeaderText("WTF is a volatility input?");
        about.setContentText("Black Scholes volatility input is the annualized standard deviation of continuously compounded returns for an asset. That asset can be stock, options(puts, calls), or other monetary assets. It is a measure of the variability of the asset price over time.");
        about.showAndWait();
   }
   
   @FXML
   private void interestInfo(ActionEvent event)
   {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("Interest Information");
        about.setHeaderText("WTF is a interest input?");
        about.setContentText("Black Scholes interest input is the annualized continuously compounded risk free rate. In practice, all investments carry some risk so the actual rate most investors use is determined by the Federal Reserve.");
        about.showAndWait();    
   }
   
   @FXML
   private void timeInfo(ActionEvent event)
   {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("Time Information");
        about.setHeaderText("WTF is a time input?");
        about.setContentText("Black Scholes time input is the time to expiration in T years. So the value you input will be divided by 365.");
        about.showAndWait();
   }
   
   @FXML
   private void dividendInfo(ActionEvent event)
   {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("Dividend Information");
        about.setHeaderText("WTF is a dividend input?");
        about.setContentText("Black Scholes dividend input is the annualized dividend yield. A dividend is the disbursements of the company equity to shareholders. These are typically paid out four times a year. When the dividends are dispursed, it causes the stock price to drop because the equity represented by  the shares has been reduced.");
        about.showAndWait();
   }
    
  @FXML
  private void handleKeyInputVolatility(final InputEvent event)
  {
     boolean doesPercentExist = false;
     String inputString;
     if (event instanceof KeyEvent)
     {
        final KeyEvent keyEvent = (KeyEvent) event;
        inputString=volatility.getText();
        char [] cArray = inputString.toCharArray();
        for (char c : cArray)
        {
            if (c == '%')
            {
                doesPercentExist=true;
            }
            
        }
        if(!doesPercentExist)
        {
            addPercentageSign(keyEvent, inputString);
        }
     }
  }
   
   @FXML
  private void handleKeyInputInterest(final InputEvent event)
  {
     boolean doesPercentExist = false;
     String inputString;
     if (event instanceof KeyEvent)
     {
        final KeyEvent keyEvent = (KeyEvent) event;
        inputString=interest.getText();
        char [] cArray = inputString.toCharArray();
        for (char c : cArray)
        {
            if (c == '%')
            {
                doesPercentExist=true;
            }
            
        }
        if(!doesPercentExist)
        {
            addPercentageSign(keyEvent, inputString);
        }
     }
  }
  
   @FXML
  private void handleKeyInputDividend(final InputEvent event)
  {
     boolean doesPercentExist = false;
     String inputString;
     if (event instanceof KeyEvent)
     {
        final KeyEvent keyEvent = (KeyEvent) event;
        inputString=dividend.getText();
        char [] cArray = inputString.toCharArray();
        for (char c : cArray)
        {
            if (c == '%')
            {
                doesPercentExist=true;
            }
            
        }
        if(!doesPercentExist)
        {
            addPercentageSign(keyEvent, inputString);
        }
     }
  }
  
  private void addPercentageSign(KeyEvent keyEvent, String inputString)
{
    switch ( keyEvent.getCode())
    {
        case DIGIT0:
            inputString = inputString + "%";
            dividend.setText(inputString);
        break;

        case DIGIT1:
            inputString = inputString + "%";
            dividend.setText(inputString);
        break;

        case DIGIT2:
            inputString = inputString + "%";
            dividend.setText(inputString);
        break;

        case DIGIT3:
            inputString = inputString + "%";
            dividend.setText(inputString);
        break;

        case DIGIT4:
            inputString = inputString + "%";
            dividend.setText(inputString);
        break;

        case DIGIT5:
            inputString = inputString + "%";
            dividend.setText(inputString);
        break;

        case DIGIT6:
            inputString = inputString + "%";
            dividend.setText(inputString);
        break;

        case DIGIT7:
            inputString = inputString + "%";
            dividend.setText(inputString);
        break;

        case DIGIT8:
            inputString = inputString + "%";
            dividend.setText(inputString);
        break;

        case DIGIT9:
            inputString = inputString + "%";
            dividend.setText(inputString);
        break;

        case PERIOD:
            inputString = inputString + "%";
            dividend.setText(inputString);
        break;
    }
}
  
@FXML
  private void handleKeyInputStock(final InputEvent event)
  {
//     boolean doesPercentExist = false;
//     String inputString;
//     if (event instanceof KeyEvent)
//     {
//        final KeyEvent keyEvent = (KeyEvent) event;
//        inputString=stock.getText();
//        char [] cArray = inputString.toCharArray();
//        for (char c : cArray)
//        {
//            if (c == '$')
//            {
//                doesPercentExist=true;
//            }
//            
//        }
//        if(!doesPercentExist)
//        {
//            switch ( keyEvent.getCode())
//            {
//                case DIGIT0:
//                    //inputString = "$" + inputString;
//                    stock.setText("$"+inputString);
//                break;
//
//                case DIGIT1:
//                    //inputString = "$" + inputString;
//                    stock.setText("$" + inputString);
//                break;
//
//                case DIGIT2:
//                    //inputString = "$" + inputString;
//                    stock.setText("$" + inputString);
//                break;
//
//                case DIGIT3:
//                    //inputString = "$" + inputString;
//                    stock.setText("$" + inputString);
//                break;
//
//                case DIGIT4:
//                    //inputString = inputString + "%";
//                    stock.setText("$" + inputString);
//                break;
//
//                case DIGIT5:
//                    //inputString = inputString + "%";
//                    stock.setText("$" + inputString);
//                break;
//
//                case DIGIT6:
//                    //inputString = inputString + "%";
//                    stock.setText("$" + inputString);
//                break;
//
//                case DIGIT7:
//                    //inputString = inputString + "%";
//                    stock.setText("$" + inputString);
//                break;
//
//                case DIGIT8:
//                    //inputString = inputString + "%";
//                    stock.setText("$" + inputString);
//                break;
//
//                case DIGIT9:
//                    //inputString = inputString + "%";
//                    stock.setText("$" + inputString);
//                break;
//                
//                case PERIOD:
//                    //inputString = inputString + "%";
//                    stock.setText("$" + inputString);
//                break;
//            }
//        }
//     }
  }  
 
  //quit game
    @FXML
    private void exitGame(ActionEvent event) 
    {
        System.exit(0);
    }
    
    @FXML
    private void handleGoToA(ActionEvent event) {
        Switchable.switchTo("MainMenuFXML");
        
    
    }
    
    @FXML
    private void handleGoToC(ActionEvent event) {   
        Switchable.switchTo("BlackScholesInfoFXML");
    }
    
    @FXML
    private void clearInputs(ActionEvent event) {   
        stock.setText("");
        strike.setText("");
        volatility.setText("");
        interest.setText("");
        time.setText("");
        dividend.setText("");
        putPrice.setText("");
        callPrice.setText("");
    }

    @FXML
    private void handleLoad(ActionEvent event) {   
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(switcherExample.stage);
        if (selectedFile != null) 
        {
           String openedFile = readFile(selectedFile);
           String splitStrings [] = openedFile.split(" | ");
           stock.setText(splitStrings[0]);
           strike.setText(splitStrings[2]);
           volatility.setText(splitStrings[4]);
           interest.setText(splitStrings[6]);
           time.setText(splitStrings[8]);
           dividend.setText(splitStrings[10]);
        }
    }
    
    @FXML
    private void handleSave(ActionEvent event) {   
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = 
        new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        //Show save file dialog
        File file = fileChooser.showSaveDialog(switcherExample.stage);
        if(file != null){
            String content;
            content = stock.getText() + " | " + strike.getText() + " | " + volatility.getText() + " | " + interest.getText() + " | "
                    + time.getText() + " | " + dividend.getText();
            SaveFile(content, file);
        }
    }
    
    @FXML
    private void calculatePutPrice(ActionEvent event)
    {       
        try{
            putModel.setStockInput(Double.parseDouble((stock.getText().replaceAll(" ", "").replaceAll("$",""))));
            putModel.setStikeInput(Double.parseDouble((strike.getText().replaceAll(" ", "").replaceAll("$",""))));
            putModel.setVolatilityInput(Double.parseDouble((volatility.getText().replaceAll(" ", "").replaceAll("%",""))));
            putModel.setInterestInput(Double.parseDouble((interest.getText().replaceAll(" ", "").replaceAll("%",""))));
            putModel.setTimeInput(Double.parseDouble((time.getText().replaceAll(" ", "").replaceAll("%",""))));
            putModel.setDividendInput(Double.parseDouble((dividend.getText().replaceAll(" ", "").replaceAll("%",""))));
            putModel.calculateD1();
            putModel.calculateD2();
            putModel.calculateOptionPrice();
            
            putPrice.setText( (putModel.calculateOptionPrice()) );
        } catch (NumberFormatException e){
            //System.out.println("Invalid number format!");
            Alert about = new Alert(Alert.AlertType.INFORMATION);
            about.setTitle("Invalid Number Format Error!!");
            about.setHeaderText("Numbers Are Your Friend!");
            about.setContentText("Black Scholes Inputs must bet numeric values. Please put in numbers and try again!");
            about.showAndWait();
        } catch (NullPointerException e){
            System.out.println("Null pointer exception");
        }
        
        
    }
    
    @FXML
    private void calculateCallPrice(ActionEvent event)
    {
        try{
            callModel.setStockInput(Double.parseDouble((stock.getText().replaceAll(" ", "").replaceAll("$",""))));
            callModel.setStikeInput(Double.parseDouble((strike.getText().replaceAll(" ", "").replaceAll("$",""))));
            callModel.setVolatilityInput(Double.parseDouble((volatility.getText().replaceAll(" ", "").replaceAll("%",""))));
            callModel.setInterestInput(Double.parseDouble((interest.getText().replaceAll(" ", "").replaceAll("%",""))));
            callModel.setTimeInput(Double.parseDouble((time.getText().replaceAll(" ", "").replaceAll("%",""))));
            callModel.setDividendInput(Double.parseDouble((dividend.getText().replaceAll(" ", "").replaceAll("%",""))));
            callModel.calculateD1();
            callModel.calculateD2();    
            callModel.calculateOptionPrice();
            callPrice.setText( (callModel.calculateOptionPrice()) );
            
            
        } catch (NumberFormatException e){
            //System.out.println("Invalid number format!");
            Alert about = new Alert(Alert.AlertType.INFORMATION);
            about.setTitle("Invalid Number Format Error!!");
            about.setHeaderText("Numbers Are Your Friend!");
            about.setContentText("Black Scholes Inputs must bet numeric values. Please put in numbers and try again!");
            about.showAndWait();
        } catch (NullPointerException e){
            System.out.println("Null pointer exception");
        }
        
    }
    
    
    private String readFile(File file){
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;       
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = bufferedReader.readLine()) != null) 
            {
                stringBuffer.append(text);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MenuFail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuFail.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(MenuFail.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return stringBuffer.toString();
    }
    
    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter; 
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuFail.class
                .getName()).log(Level.SEVERE, null, ex);
        }
          
    }
     
    
   
            
    
}

