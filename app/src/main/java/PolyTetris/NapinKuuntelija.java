/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polytetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Ilari
 */
public class NapinKuuntelija implements ActionListener {
    
    private Peli peli;
    private Thread oldThread;
    
    public NapinKuuntelija(Peli peli, Thread oldThread){
        this.peli= peli;
        this.oldThread = oldThread;
    }
    
    @ Override
    public void actionPerformed(ActionEvent e){
        if (oldThread.isAlive()) {
            oldThread.interrupt();
            peli.resetScores();
            oldThread = new Thread(peli);
            oldThread.start();
        } else {
            peli.resetScores();
            oldThread = new Thread(peli);
            oldThread.start();
        }
        
        JButton nappi = (JButton) e.getSource();
        nappi.setFocusable(false);
        peli.setFocusable(true);
    }
    
    
}
