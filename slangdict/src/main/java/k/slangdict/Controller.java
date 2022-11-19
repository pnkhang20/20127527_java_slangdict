/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package k.slangdict;

/**
 *
 * @author kp
 */
public class Controller {
    private View theView;
    private Model theModel;
    
    public Controller(View theView, Model theModel){
        this.theView = theView;
        this.theModel = theModel;
        //this.theView.addCalculationListener(new CalculateListener());
    }
}
