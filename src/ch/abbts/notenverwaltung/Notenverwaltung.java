/*
Notenverwaltung 2.0
@author Sascha Kümmerli, Janic Gloor
12.06.2016
*/
package ch.abbts.notenverwaltung;

import java.util.ArrayList;

public class Notenverwaltung {
   
    public static ArrayList<Klasse> mKlassen = new ArrayList <Klasse>();
    public static Hauptmenu mHauptMenu;
    
    public static void main(String[] args) {
        //Hauptmenu wird erzeugt
        mHauptMenu  = new Hauptmenu ("Notenverwaltung");
        mHauptMenu.setVisible(true);
        mHauptMenu.setSize(700, 300);
        mHauptMenu.setLocationRelativeTo(null);
  
    }  
}
    


      
    
    
