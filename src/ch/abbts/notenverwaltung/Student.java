/*
Notenverwaltung 2.0
@author Sascha Kümmerli, Janic Gloor
12.06.2016
*/
package ch.abbts.notenverwaltung;

import java.util.ArrayList;

public class Student {
    
    //Membervariablen werdne definiert
    private String mVorname;
    private String mNachname;
    private ArrayList<Note> mNoten = new ArrayList <Note>();

    // Konstruktor
    public Student (String pVorname, String pNachname) {
        this.mVorname = pVorname;
        this.mNachname = pNachname;
    }
    // Die Note wird der Arraylist hinzugefügt
    public void addNote(Note pName) {
        mNoten.add(pName);
    }

    // Methode berechnet den Gesamtdurchschnitt des Studenten 
    public double getDurchschnitt (){
        double notenSumme=0.0;
        int p = 0;
        
        for (p=0 ; p<mNoten.size(); p++){
            notenSumme = notenSumme + mNoten.get(p).getNote();
        }
        notenSumme = (notenSumme)/p;
        notenSumme = Math.round(notenSumme*20)/20.0;

        return notenSumme;
    }

    public String getVorname() {
        return mVorname;
    }
    
    public String getNachname (){
        return mNachname;
    }

    public ArrayList<Note> getNoten() {
        return mNoten;
    }

    @Override
    public String toString() {
        return  mVorname + " " + mNachname;
    }    
}
