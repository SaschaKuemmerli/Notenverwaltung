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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class MenuDatenAusgeben extends JFrame{
    //Membervariablen werden definiert
    JTextArea mAnzeige;
    JButton mBtnZurueck;

    public MenuDatenAusgeben (String pTitel){
        super (pTitel);
        //Components werden erzeugt und Angaben definiert
        mAnzeige = new JTextArea ();
        mAnzeige.setColumns(20);
        mAnzeige.setRows(10);
        mAnzeige.setEditable(false);
        mBtnZurueck = new JButton ("Zurück");
        JScrollPane scrollPane = new JScrollPane(mAnzeige);
     
        setLayout (new FlowLayout());
        //Components werden am Frame hinzugefügt
        add(mAnzeige);
        add(mBtnZurueck);
        //Methode für das Anzeigen der Daten wird aufgerufen
        setTextAnzeige();
        //ActonListener wird erzeugt
        MyActionListener listener = new MyActionListener ();
        mBtnZurueck.addActionListener(listener);
        //Wenn das Fenster geschlossen wird, wird das Programm beendet
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton)e.getSource();
            
            if (button == mBtnZurueck){
                Notenverwaltung.mHauptMenu.setVisible(true);
                Notenverwaltung.mHauptMenu.mMenuRechts.setVisible(false);
            }
        }
    } 

    public void setTextAnzeige (){
          
        String anzeige;
        //Namen der Klassen werden geholt und in das Textfeld geschrieben
        for (int i=0; i<Notenverwaltung.mKlassen.size(); i++){
            anzeige = mAnzeige.getText()+"\n"+ Notenverwaltung.mKlassen.get(i).getName()+"\n"+"Durchschnitt: "+Notenverwaltung.mKlassen.get(i).getDurchschnittKlasse()
            +"\n"+" ";
            mAnzeige.setText(anzeige);
            //Namen der Fächer von jeder Klasse werden geholt und in das Textfeld geschrieben
            for (int j=0; j<Notenverwaltung.mKlassen.get(i).getFaecher().size(); j++){
                anzeige = mAnzeige.getText()+"\n"+Notenverwaltung.mKlassen.get(i).getFaecher().get(j).getName();
                mAnzeige.setText(anzeige);
                //Vorname und Nachname der Studenten von jedem Fach werden geholt und in das Textfeld geschrieben
                for (int k=0; k<Notenverwaltung.mKlassen.get(i).getFaecher().get(j).getStudenten().size(); k++) {
                    anzeige = mAnzeige.getText()+"\n"+Notenverwaltung.mKlassen.get(i).getFaecher().get(j).getStudenten().get(k).getVorname()+" "
                    +Notenverwaltung.mKlassen.get(i).getFaecher().get(j).getStudenten().get(k).getNachname()+"\n"
                    +"Durchschnitt: "+Notenverwaltung.mKlassen.get(i).getFaecher().get(j).getStudenten().get(k).getDurchschnitt()
                    +"\n";
                    mAnzeige.setText(anzeige);
                    //Von jedem Student werden die Noten geholt und in das Textfeld geschrieben
                    for(int h=0; h<Notenverwaltung.mKlassen.get(i).getFaecher().get(j).getStudenten().get(k).getNoten().size();h++){
                        anzeige = mAnzeige.getText()+ Notenverwaltung.mKlassen.get(i).getFaecher().get(j).getStudenten().get(k).getNoten().get(h).getNote()+"\n";
                        mAnzeige.setText(anzeige);
                        
                    }
                }
            }
        }
    }
}

        
        
        
        
        
 
        
        
 
        


  
     
 
 


