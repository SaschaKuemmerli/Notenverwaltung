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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class MenuFachHinzufuegen extends JFrame {
    
    //Membervariablen werden definiert
    JComboBox mBox1;
    JButton mBtnhinzufuegen;
    JTextField mNameFach;
    JButton mBtnZurueck;
    
    public MenuFachHinzufuegen (String pTitel){
        super (pTitel);
        //Components werden erzeugt und Angaben definiert
        mBtnhinzufuegen = new JButton ("Fach hinzufügen");
        mNameFach = new JTextField ();
        mNameFach.setColumns(15);
        mBtnZurueck = new JButton ("Zurück");
        mBox1 = new JComboBox(Notenverwaltung.mKlassen.toArray());
        
        setLayout (new FlowLayout());
        //Components werden am Frame hinzugefügt 
        add(mBox1);
        add(mNameFach);
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
            JButton button = (JButton)e.getSource();
            String nameFach;
            
            if (button == mBtnhinzufuegen){
                nameFach = mNameFach.getText();
                //Wenn das Textfeld noch leer ist wird eine Meldung ausgeben
                if (nameFach.trim().isEmpty()){
                    JOptionPane.showMessageDialog(null,
                    "Du hast kein Fach eingeben, Bitte erneut versuchen. ", 
                    "Fehler", JOptionPane.ERROR_MESSAGE);
                }
                /*sonst wird ein Objekt vom  Typ Fach erzeugt und der Arraylist hinzugefügt
                zusätzlich wird das aktuelle Fenster geschlossen und das vorhergehende
                geöffnet
                */
                else {
                    int g = mBox1.getSelectedIndex();         
                    Notenverwaltung.mKlassen.get(g).addFach(new Fach (nameFach));
                    Notenverwaltung.mHauptMenu.mMenuLinks.mMenuFach.setVisible(false);
                    Notenverwaltung.mHauptMenu.mMenuLinks.setVisible(true);
                }
            }
            /*Wenn der Button zurück betätigt wird, wird das aktuelle Fenster geschlossen
            und das vorhergehende geöffnet.            
            */
            else if(button == mBtnZurueck){
                Notenverwaltung.mHauptMenu.mMenuLinks.setVisible(true);
                Notenverwaltung.mHauptMenu.mMenuLinks.mMenuFach.setVisible(false);
            }
        }
    }
}

    

    