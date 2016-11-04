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
import javax.swing.WindowConstants;

public class Hauptmenu extends JFrame{
    
    //Membervariablen werdne definiert
    JButton mBtnLinks;
    JButton mBtnRechts;
    MenuDatenEingeben mMenuLinks;
    MenuDatenAusgeben mMenuRechts;

    public Hauptmenu (String pTitel){
        super (pTitel);
        //Buttons werden erzeugt
        mBtnLinks = new JButton ("Daten eingeben");
        mBtnRechts = new JButton ("Daten auslesen");

        setLayout (new FlowLayout());
        //Buttons werden am Frame hinzugefügt
        add(mBtnLinks);
        add(mBtnRechts);
        
        //ActonListener wird erzeugt
        MyActionListener listener = new MyActionListener ();
        mBtnLinks.addActionListener(listener);
        mBtnRechts.addActionListener(listener);
        //Wenn das Fenster geschlossen wird, wird das Programm beendet
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
       
    
    class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton)e.getSource();
            
            /*Wenn der Button Links betätigt wird, wird das neue Fenster erzeugt
            und sichtbar gemacht. Das aktuelle Fenster wird dann geschlossen.            
            */
            if (button == mBtnLinks){
                mMenuLinks = new MenuDatenEingeben ("Daten eingeben");
                mMenuLinks.setVisible(true);
                mMenuLinks.setSize(700, 300);
                mMenuLinks.setLocationRelativeTo(null);
                Notenverwaltung.mHauptMenu.setVisible(false);
            }
            /*Wenn der Button Rechts betätigt wird, wird das neue Fenster erzeugt
            und sichtbar gemacht. Das aktuelle Fenster wird dann geschlossen.            
            */
            else if (button == mBtnRechts) {
                mMenuRechts = new MenuDatenAusgeben ("Datenanzeige");
                mMenuRechts.setVisible (true);
                mMenuRechts.setSize(500, 300);
                mMenuRechts.setLocationRelativeTo(null);
                Notenverwaltung.mHauptMenu.setVisible(false);
            }
        }
    }
}