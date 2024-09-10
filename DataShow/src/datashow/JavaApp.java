/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp;

import java.awt.Color;
import static javafx.scene.paint.Color.color;

/**
 *
 * @author minam
 */
public class JavaApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        firm1 f1 = new firm1();
        f1.setLocationRelativeTo(null);
        f1.getContentPane().setBackground(new Color(161,161,161));
        f1.setVisible(true); 
    }
    
}
