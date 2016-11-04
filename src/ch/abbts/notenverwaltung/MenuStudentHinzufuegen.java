/*
Notenverwaltung 2.0
@author Sascha Kümmerli, Janic Gloor
12.06.2016
*/
package ch.abbts.notenverwaltung;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class MenuStudentHinzufuegen extends JFrame {
   
    //Membervariablen werden definiert
    JComboBox mBox1;
    JComboBox mBox2;
    JButton mBtnhinzufuegen;
    JButton mBtnZurueck;
    JTextField mVornameStudent;
    JTextField mNachnameStudent;
    
    public MenuStudentHinzufuegen (String pTitel){
        super (pTitel);
        //Components werden erzeugt und Angaben definiert
        mBtnhinzufuegen = new JButton ("Student hinzufügen");
        mBtnZurueck = new JButton ("Zurück");
        mVornameStudent = new JTextField ();
        mNachnameStudent = new JTextField ();
        mVornameStudent.setColumns(15);
        mNachnameStudent.setColumns(15);
        mBox1 = new JComboBox(Notenverwaltung.mKlassen.toArray());
        mBox2 = new JComboBox (Notenverwaltung.mKlassen.get(0).getFaecher().toArray());
       
        setLayout (new FlowLayout());
        //Components werden am Frame hinzugefügt 
        add(mBox1);
        add(mBox2); 
        add(new JLabel("Vorname"));
        add(mVornameStudent);
        add(new JLabel ("Nachaname"));
        add(mNachnameStudent);
        add(mBtnhinzufuegen);
        add(mBtnZurueck);
        
        /*Wenn bei Combobox 1 ein anderer Eintrag ausgewählt wird, wird der Inhalt der 
        Combobox 2 neu initialisiert        
        */
        mBox1.addItemListener((ItemEvent e) -> {
            if(e.getStateChange() ==ItemEvent.SELECTED){
                mBox2.setModel(new DefaultComboBoxModel(Notenverwaltung.mKlassen.get(mBox1.getSelectedIndex()).getFaecher().toArray()));
            }
        });      
         
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
            String vorname = mVornameStudent.getText();
            String nachname = mNachnameStudent.getText();
            JButton button = (JButton)e.getSource();
            
            if (button == mBtnhinzufuegen){
                //Wenn in der ausgewählten Klasse noch kein Fach vorhanden ist wird eine Meldung ausgegeben
                if (Notenverwaltung.mKlassen.get(mBox1.getSelectedIndex()).getFaecher().isEmpty()){
                    JOptionPane.showMessageDialog(null,
                    "In der ausgewählten Klasse ist noch kein Fach vorhanden.", 
                    "Fehler", JOptionPane.ERROR_MESSAGE);
                }
                //Wenn eines der Textfelder noch leer ist wird eine Meldung ausgeben
                else if (vorname.trim().isEmpty()  || nachname.trim().isEmpty()){
                    JOptionPane.showMessageDialog(null,
                    "Du hast kein vollständigen Namen eingeben, Bitte erneut versuchen. ", 
                    "Fehler", JOptionPane.ERROR_MESSAGE);
                }
                /*sonst wird ein Objekt vom Typ Student erzeugt und der Arraylist hinzugefügt,
                dazu wird der Index der Comboboxen ausgelesen und als Index für die 
                Arraylist verwendet,
                zusätzlich wird das aktuelle Fenster geschlossen und das vorhergehende
                geöffnet
                */
                else {
                    int b1 = mBox1.getSelectedIndex();
                    int b2 = mBox2.getSelectedIndex();
                    Notenverwaltung.mKlassen.get(b1).getFaecher().get(b2).addStudent(new Student (vorname, nachname));
                    Notenverwaltung.mHauptMenu.mMenuLinks.mMenuStudent.setVisible(false);
                    Notenverwaltung.mHauptMenu.mMenuLinks.setVisible(true);
                }
            }
            /*Wenn der Button zurück betätigt wird, wird das aktuelle Fenster geschlossen
            und das vorhergehende geöffnet.            
            */
            else if (button == mBtnZurueck){
                Notenverwaltung.mHauptMenu.mMenuLinks.setVisible(true);
                Notenverwaltung.mHauptMenu.mMenuLinks.mMenuStudent.setVisible(false);
            }
        }
    }
}