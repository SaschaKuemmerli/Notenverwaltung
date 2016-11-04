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
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class MenuKlasseHinzufuegen extends JFrame {
    
    //Membervariablen werden definiert
    JTextField mNameKlasse;
    JButton mBtnhinzufuegen;
    JButton mBtnZurueck;
    
    public MenuKlasseHinzufuegen (String pTitel){
        super (pTitel);
        //Components werden erzeugt und Angaben definiert
        mNameKlasse = new JTextField();
        mNameKlasse.setColumns(15);
        mBtnhinzufuegen = new JButton ("Klasse hinzufügen");
        mBtnZurueck = new JButton ("Zurück");
        
        setLayout (new FlowLayout());
        //Components werden am Frame hinzugefügt 
        add(mNameKlasse);
        add(mBtnhinzufuegen);
        add(mBtnZurueck);

        //ActonListener wird erzeugt
        MyActionListener listener = new MyActionListener ();
        mBtnhinzufuegen.addActionListener(listener);
        mBtnZurueck.addActionListener(listener);
        //Wenn das Fenster geschlossen wird, wird das Programm beendet
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
    }
        
    class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nameKlasse;
            JButton button = (JButton)e.getSource();
       
            if (button == mBtnhinzufuegen){
                nameKlasse = mNameKlasse.getText();
                //Wenn das Textfeld noch leer ist wird eine Meldung ausgeben
                if (nameKlasse.trim().isEmpty()){
                    JOptionPane.showMessageDialog(null,
                    "Du hast keine Klasse eingeben, Bitte erneut versuchen. ", 
                    "Fehler", JOptionPane.ERROR_MESSAGE);
                }
                /*sonst wird ein Objekt vom  Typ Klasse erzeugt und der Arraylist hinzugefügt
                zusätzlich wird das aktuelle Fenster geschlossen und das vorhergehende
                geöffnet
                */
                else {
                    Notenverwaltung.mKlassen.add(new Klasse (nameKlasse));
                    Notenverwaltung.mHauptMenu.mMenuLinks.mMenuKlasse.setVisible(false);
                    Notenverwaltung.mHauptMenu.mMenuLinks.setVisible(true);
                }
            }
            /*Wenn der Button zurück betätigt wird, wird das aktuelle Fenster geschlossen
            und das vorhergehende geöffnet.            
            */
            else if (button == mBtnZurueck) {
                Notenverwaltung.mHauptMenu.mMenuLinks.setVisible(true);
                Notenverwaltung.mHauptMenu.mMenuLinks.mMenuKlasse.setVisible(false);
            }
        }
    }
}