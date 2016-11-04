/*
Notenverwaltung 2.0
@author Sascha Kümmerli, Janic Gloor
12.06.2016
*/
package ch.abbts.notenverwaltung;

import java.util.ArrayList;

public class Klasse {
    
    //Membervariablen werdne definiert
    private String mName;
    private ArrayList<Fach> mFaecher = new ArrayList <Fach>();

    // Konstruktor
    public Klasse(String pName) {
        this.mName = pName;
    }
    
    // Fach wird der Arraylist hinzugefügt
    public void addFach(Fach pFach) {
        mFaecher.add(pFach);
    }
    
    // Methode rechnet den Klassedurschnitt vom Durchschnitt der Studenten aus
    public double getDurchschnittKlasse () {
        double notenSumme=0.0;
        int anzahlNoten=0;
        double klassenDurchschnitt = 0;

        for (int j=0; j<mFaecher.size(); j++){
                   
            for (int k=0; k<mFaecher.get(j).getStudenten().size(); k++) {
                if (mFaecher.get(j).getStudenten().get(k).getDurchschnitt()== 0.0) {
                    continue;
                }
                else {
                    notenSumme += mFaecher.get(j).getStudenten().get(k).getDurchschnitt();
                    anzahlNoten++;
                }
            } 
                
        }
         
        klassenDurchschnitt = notenSumme / (double) anzahlNoten;
        klassenDurchschnitt = Math.round(klassenDurchschnitt* 10) / 10.0;
        
        return klassenDurchschnitt;
    }
    
    public String getName() {
        return mName;
    }
   
    public ArrayList<Fach> getFaecher() {
        return mFaecher;
    }

    public String toString() {
        return mName;
    }
 
}
