/*
Notenverwaltung 2.0
@author Sascha Kümmerli, Janic Gloor
12.06.2016
*/
package ch.abbts.notenverwaltung;

import java.util.ArrayList;

public class Fach {
    
    //Membervariablen werdne definiert
    private String mName;
    private ArrayList<Student> mStudenten = new ArrayList <Student>();

    // Konstruktor
    public Fach (String pName) {
        this.mName = pName;
    }

    // Studentenname wird der Arraylist hinzugefügt
    public void addStudent(Student pName) {
        mStudenten.add(pName);
  }
    
    
    public String getName() {
        return mName;
    }

    public ArrayList<Student> getStudenten() {
        return mStudenten;
    }

    @Override
    public String toString() {
        return mName;
    }
}
