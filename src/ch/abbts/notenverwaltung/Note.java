/*
Notenverwaltung 2.0
@author Sascha Kümmerli, Janic Gloor
12.06.2016
*/
package ch.abbts.notenverwaltung;

public class Note {
    
    //Membervariablen werdne definiert
    private double mNote;
    
    
    // Konstruktor
    public Note (double pNote) {
        
        this.mNote = pNote;
    }

    public double getNote() {
        return mNote;
    }
}
