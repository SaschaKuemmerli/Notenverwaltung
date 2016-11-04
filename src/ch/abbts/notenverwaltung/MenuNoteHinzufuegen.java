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
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class MenuNoteHinzufuegen extends JFrame {
    
    //Membervariablen werden definiert
    JComboBox mBox1;
    JComboBox mBox2;
    JComboBox mBox3;
    JComboBox mBox4;
    JComboBox mBox5;
    JButton mBtnhinzufuegen;
    JButton mBtnZurueck;
    
    public MenuNoteHinzufuegen (String pTitel){
        super (pTitel);
        //Components werden erzeugt und Angaben definiert
        mBtnhinzufuegen = new JButton ("Note hinzufügen");
        mBtnZurueck = new JButton ("Zurück");
        mBox1 = new JComboBox(Notenverwaltung.mKlassen.toArray());
        mBox2 = new JComboBox (Notenverwaltung.mKlassen.get(0).getFaecher().toArray());                              
        mBox3 = new JComboBox (Notenverwaltung.mKlassen.get(0).getFaecher().get(0).getStudenten().toArray());
        mBox4 = new JComboBox (initBox4());
        mBox5 = new JComboBox (initBox5());

        setLayout (new FlowLayout());
        //Components werden am Frame hinzugefügt 
        add(mBox1);
        add(mBox2);
        add(mBox3);
        add(mBox4);
        add(mBox5);
        add(mBtnhinzufuegen);
        add(mBtnZurueck);
        
        /*Wenn bei Combobox 1 ein anderer Eintrag ausgewählt wird, wird der Inhalt der 
        Combobox 2 neu initialisiert        
        */
        mBox1.addItemListener((ItemEvent e) -> {
            if(e.getStateChange() ==ItemEvent.SELECTED){
                mBox2.setModel(new DefaultComboBoxModel(Notenverwaltung.mKlassen.get(mBox1.getSelectedIndex()).getFaecher().toArray()));
                //Wenn dann in der Combobox 2 noch kein Eintrag vorhanden sein sollte, wird die Combobox 3 leer angezeigt
                if(mBox2.getItemCount() == 0){
                    mBox3.setModel(new DefaultComboBoxModel());
                }
                //Wenn in der Combobox 2 ein Eintrag vorhanden ist, wird der Inhalt der Combobox 3 initialisert
                else {
                    mBox3.setModel(new DefaultComboBoxModel(Notenverwaltung.mKlassen.get(mBox1.getSelectedIndex()).getFaecher().get(mBox2.getSelectedIndex()).getStudenten().toArray()));
                }
            }
        });
        /*Wenn bei Combobox 2 ein anderer Eintrag ausgewählt wird, wird der Inhalt der 
        Combobox 3 neu initialisiert        
        */
        mBox2.addItemListener((ItemEvent e) -> {
            if(e.getStateChange() ==ItemEvent.SELECTED){
                mBox3.setModel(new DefaultComboBoxModel(Notenverwaltung.mKlassen.get(mBox1.getSelectedIndex()).getFaecher().get(mBox2.getSelectedIndex()).getStudenten().toArray()));
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
            JButton button = (JButton)e.getSource();

            if (button == mBtnhinzufuegen){
                //Wenn in der ausgewählten Klasse noch kein Fach vorhanden ist wird eine Meldung ausgegeben
                if (Notenverwaltung.mKlassen.get(mBox1.getSelectedIndex()).getFaecher().isEmpty()){
                    JOptionPane.showMessageDialog(null,
                    "In der ausgewählten Klasse ist noch kein Fach vorhanden.", 
                    "Fehler", JOptionPane.ERROR_MESSAGE); 
                }
                //Wenn im ausgewählten Fach noch kein Student vorhanden ist wird eine Meldung ausgegeben
                else if (Notenverwaltung.mKlassen.get(mBox1.getSelectedIndex()).getFaecher().get(mBox2.getSelectedIndex()).getStudenten().isEmpty()){
                    JOptionPane.showMessageDialog(null,
                    "Im ausgewählten Fach ist noch kein Student vorhanden.", 
                    "Fehler", JOptionPane.ERROR_MESSAGE);
                }
                /*Es wird überprüft anhand der Indexe der Combobox, ob eine Note höher als 6.0 ausgewählt wurde,
                sonst wird eine Meldung ausgegeben
                */
                else if (mBox4.getSelectedIndex()== 5 & mBox5.getSelectedIndex() != 0){
                    JOptionPane.showMessageDialog(null,
                    "Note höher als 6.0 nicht möglich.", 
                    "Fehler", JOptionPane.ERROR_MESSAGE);
                }
                /*sonst wird ein Objekt vom Typ Note erzeugt und der Arraylist hinzugefügt,
                dazu wird der Index der Comboboxen ausgelesen und als Index für die 
                Arraylist verwendet,
                zusätzlich wird das aktuelle Fenster geschlossen und das vorhergehende
                geöffnet
                */
                else {
                    int b1 = mBox1.getSelectedIndex();
                    int b2 = mBox2.getSelectedIndex();
                    int b3 = mBox3.getSelectedIndex();
                    double note = getBox4()+ getBox5();
                    Notenverwaltung.mKlassen.get(b1).getFaecher().get(b2).getStudenten().get(b3).addNote(new Note(note));
                    Notenverwaltung.mHauptMenu.mMenuLinks.mMenuNote.setVisible(false);
                    Notenverwaltung.mHauptMenu.mMenuLinks.setVisible(true); 
                }
            }
            /*Wenn der Button zurück betätigt wird, wird das aktuelle Fenster geschlossen
            und das vorhergehende geöffnet.            
            */
            else if(button == mBtnZurueck){
                Notenverwaltung.mHauptMenu.mMenuLinks.setVisible(true);
                Notenverwaltung.mHauptMenu.mMenuLinks.mMenuNote.setVisible(false);
            }
        }
    }  
    /*Methode um den Inhalt der Box 4 zu erhalten, der Index wird direkt dem
    entsprechenden double Wert zugewiesen und zurückgegeben.
    */
    private double getBox4() {
        double inhaltBox4 = 0.0;
        
        switch(mBox4.getSelectedIndex()){
            case 0:
                inhaltBox4 = 1.0;
                break;
            case 1:
                inhaltBox4 = 2.0;
                break;
            case 2:
                inhaltBox4 = 3.0;
                break;
            case 3:
                inhaltBox4 = 4.0;
                break;
            case 4:
                inhaltBox4 = 5.0;
                break;
            case 5:
                inhaltBox4 = 6.0;
                break;
        }     
    return inhaltBox4;
    }
    /*Methode um den Inhalt der Box 5 zu erhalten, der Index wird direkt dem
    entsprechenden double Wert zugewiesen und zurückgegeben.
    */
    private double getBox5() {        
        double inhaltBox5 = 0.0;
            
        switch(mBox5.getSelectedIndex()){
            case 0:
                inhaltBox5 = 0.0;
                break;
            case 1:
                inhaltBox5 = 0.1;
                break;
            case 2:
                inhaltBox5 = 0.2;
                break;
            case 3:
                inhaltBox5 = 0.3;
                break;
            case 4:
                inhaltBox5 = 0.4;
                break;
            case 5:
                inhaltBox5 = 0.5;
                break;
            case 6:
                inhaltBox5 = 0.6;
                break;
            case 7:
                inhaltBox5 = 0.7;
                break;
            case 8:
                inhaltBox5 = 0.8;
                break;
            case 9:
                inhaltBox5 = 0.9;
                break;    
        }
    return inhaltBox5;
    }
    //Methode um Box 4 zu initialisieren
    private String [] initBox4() {
        String[] array = { "1", "2", "3", "4", "5","6" };
        return array;  
    }  
    //Methode um Box 5 zu initialisieren    
    private String [] initBox5() {
        String[] array = { ".0", ".1", ".2", ".3", ".4",".5",".6",".7",".8",".9" };
        return array;        
    }
}    

    