/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package switcherexample;

import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.text.DecimalFormat;

/**
 *
 * @author Sam Richards
 */
public class PutModel extends Guassian implements CommonEquations
{
    private double stock;
    private double strike;
    private double volatility;
    private double interest;
    private double time;
    private double dividend;
    private double putPrice;
    private double d1;
    private double d2;
    private String value="";
    
    public PutModel()
    {
        
        stock=0.0;
        strike=0.0;
        volatility=0.0;
        interest=0.0;
        time=0.0;
        dividend=0.0;
        putPrice=0.0;
    }
    
    
    
    public void setStockInput(double stockInput)
    {
        this.stock=stockInput;
    }
    
    public double getStockInput()
    {
        return stock;
    }
    
    public void setStikeInput(double strikeInput)
    {
        this.strike=strikeInput;
    }
    
    public double getStrikeInput()
    {
        return strike;
    }
    
    public void setVolatilityInput(double volatilityInput)
    {
        this.volatility=volatilityInput/100;
    }
    
    public double getVolatilityInput()
    {
        return volatility;
    }
    
    public void setInterestInput(double interestInput)
    {
        this.interest=interestInput/100;
    }
    
    public double getInterestInput()
    {
        return interest;
    }
    
    public void setTimeInput(Double timeInput)
    {
        this.time=timeInput/365;
    }
    
    public double getTimeInput()
    {
        return time;
    }
    
    public void setDividendInput(Double dividendInput)
    {
        this.dividend=dividendInput/100;
    }
    
    public double getDividentInput()
    {
        return dividend;
    }
    
  
    @Override
    public double calculateD1()
    {
        d1=(log(this.stock/this.strike)+(this.interest-this.dividend+pow(this.volatility,2)/2)*this.time)/(this.volatility * sqrt(this.time));
        return d1;
    }
    
    
    @Override
    public double calculateD2()
    {
        d2=(log(this.stock/this.strike)+(this.interest-this.dividend-pow(this.volatility,2)/2)*this.time)/(this.volatility * sqrt(this.time));
        return d2;
    }
    

    @Override
    public String calculateOptionPrice()
    {
        putPrice = -this.stock*exp(-1*this.dividend*this.time)*Guassian.cdf(-d1) + this.strike*exp(-1*this.interest * this.time)*Guassian.cdf(-d2);
        value = String.format("%.8f", putPrice);
        return (value); 
      
    }
}
