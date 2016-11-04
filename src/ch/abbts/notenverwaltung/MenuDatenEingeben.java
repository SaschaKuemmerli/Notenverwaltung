/*
Notenverwaltung 2.0
@author Sascha Kümmerli, Janic Gloor
12.06.2016
*/
package ch.abbts.notenverwaltung;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class MenuDatenEingeben extends JFrame {
     
    //Membervariablen werden definiert
    JButton mBtnKlasse;
    JButton mBtnFach;
    JButton mBtnStudent;
    JButton mBtnNote;
    JButton mBtnZurueck;
    MenuKlasseHinzufuegen mMenuKlasse;
    MenuFachHinzufuegen mMenuFach;
    MenuStudentHinzufuegen mMenuStudent;
    MenuNoteHinzufuegen mMenuNote;

    public MenuDatenEingeben (String pTitel){
      super (pTitel);
      //Components werden erzeugt und Angaben definiert
      mBtnKlasse = new JButton ("Klasse hinzufügen");
      mBtnFach = new JButton ("Fach hinzufügen");
      mBtnStudent = new JButton ("Student hinzufügen");
      mBtnNote = new JButton ("Note hinzufügen");
      mBtnZurueck = new JButton ("Zurück");
      
      setLayout (new FlowLayout());
      //Components werden am Frame hinzugefügt
      add(mBtnKlasse);
      add(mBtnFach);
      add(mBtnStudent);
      add(mBtnNote);
      add(mBtnZurueck);
      
      //ActonListener wird erzeugt
      MyActionListener listener = new MyActionListener ();
      mBtnKlasse.addActionListener(listener);
      mBtnFach.addActionListener(listener);
      mBtnStudent.addActionListener(listener);
      mBtnNote.addActionListener(listener);
      mBtnZurueck.addActionListener(listener);    
       //Wenn das Fenster geschlossen wird, wird das Programm beendet
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      
    }
      
    class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton)e.getSource();
            /*Wenn der Button Klasse betätigt wird,
            wird das nächste Fenster angezeigt und das aktuelle geschlossen.
            */
            if (button == mBtnKlasse){
                mMenuKlasse = new MenuKlasseHinzufuegen ("Klasse hinzufügen");
                mMenuKlasse.setVisible(true);
                mMenuKlasse.setSize(500, 200);
                mMenuKlasse.setLocationRelativeTo(null);
                Notenverwaltung.mHauptMenu.mMenuLinks.setVisible(false);
            }
            else if (button == mBtnFach) {
                /*Es wird überprüft, ob schon eine Klasse hinzugefügt wurde,
                sonst wird eine Meldung ausgegeben.
                */
                if(Notenverwaltung.mKlassen.isEmpty()){
                    JOptionPane.showMessageDialog(null,
                    "Es wurde noch keine Klasse hinzugefügt, Bitte zuerst eine Klasse hinzufügen.", 
                    "Fehler", JOptionPane.ERROR_MESSAGE);
                }
                /*Wenn schon mindestens eine Klasse vorhanden ist, 
                wird das nächste Fenster anzezeigt und das akutelle geschlossen.
                */
                else {
                    mMenuFach = new MenuFachHinzufuegen ("Fach hinzufügen");
                    mMenuFach.setVisible(true);
                    mMenuFach.setSize(500, 200);
                    mMenuFach.setLocationRelativeTo(null);
                    Notenverwaltung.mHauptMenu.mMenuLinks.setVisible(false);
                }
            }
            else if (button == mBtnStudent){
                /*Es wird überprüft, ob schon eine Klasse hinzugefügt wurde,
                sonst wird eine Meldung ausgegeben.
                */
                if(Notenverwaltung.mKlassen.isEmpty()){
                    JOptionPane.showMessageDialog(null,
                    "Es wurde noch keine Klasse hinzugefügt, Bitte zuerst eine Klasse hinzufügen.", 
                    "Fehler", JOptionPane.ERROR_MESSAGE);
                }
                /*Es wird überprüft, ob schon ein Fach hinzugefügt wurde,
                sonst wird eine Meldung ausgegeben.
                */
                else if(Notenverwaltung.mKlassen.get(0).getFaecher().isEmpty()){
                    JOptionPane.showMessageDialog(null,
                    "Es wurde noch kein Fach hinzugefügt, Bitte zuerst ein Fach hinzufügen.", 
                    "Fehler", JOptionPane.ERROR_MESSAGE);
                }
                /*Wenn eine Klasse und ein Fach vorhanden ist, wird das nächste Fenster angezeigt
                und das aktuelle geschlossen.
                */
                else {
                    mMenuStudent = new MenuStudentHinzufuegen ("Student hinzufügen");
                    mMenuStudent.setVisible(true);
                    mMenuStudent.setSize(750, 200);
                    mMenuStudent.setLocationRelativeTo(null);
                    Notenverwaltung.mHauptMenu.mMenuLinks.setVisible(false);
                }
            }
            else if (button == mBtnNote){
                /*Es wird überprüft, ob schon eine Klasse hinzugefügt wurde,
                sonst wird eine Meldung ausgegeben.
                */
                if(Notenverwaltung.mKlassen.isEmpty()){
                    JOptionPane.showMessageDialog(null,
                    "Es wurde noch keine Klasse hinzugefügt, Bitte zuerst eine Klasse hinzufügen.", 
                    "Fehler", JOptionPane.ERROR_MESSAGE);
                }
                /*Es wird überprüft, ob schon ein Fach hinzugefügt wurde,
                sonst wird eine Meldung ausgegeben.
                */
                else if(Notenverwaltung.mKlassen.get(0).getFaecher().isEmpty()){
                    JOptionPane.showMessageDialog(null,
                    "Es wurde noch kein Fach hinzugefügt, Bitte zuerst ein Fach hinzufügen.", 
                    "Fehler", JOptionPane.ERROR_MESSAGE);
                }
                /*Es wird überprüft, ob schon ein Student hinzugefügt wurde,
                sonst wird eine Meldung ausgegeben.
                */
                else if(Notenverwaltung.mKlassen.get(0).getFaecher().get(0).getStudenten().isEmpty()){
                    JOptionPane.showMessageDialog(null,
                    "Es wurde noch kein Student hinzugefügt, Bitte zuerst ein Student hinzufügen.", 
                    "Fehler", JOptionPane.ERROR_MESSAGE);
                }
                /*Wenn eine Klasse, ein Fach und ein Student vorhanden ist, wird das nächste Fenster angezeigt
                und das aktuelle geschlossen.
                */
                else {
                    mMenuNote = new MenuNoteHinzufuegen ("Note hinzufügen");
                    mMenuNote.setVisible(true);
                    mMenuNote.setSize(750, 200); 
                    mMenuNote.setLocationRelativeTo(null);
                    Notenverwaltung.mHauptMenu.mMenuLinks.setVisible(false);
                }
            }
            /*Wenn der Button zurück betätigt wird, wird das aktuelle Fenster geschlossen
            und das vorhergehende geöffnet.            
            */
            else if (button == mBtnZurueck){
                Notenverwaltung.mHauptMenu.setVisible(true);
                Notenverwaltung.mHauptMenu.mMenuLinks.setVisible(false);
            }
        }
    }
}
