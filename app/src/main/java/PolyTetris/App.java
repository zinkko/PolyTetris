/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polytetris;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Ilari
 */
public class App implements Runnable{
    
    private Kuuntelija kuuntelija;
    private JFrame frame;
    private Peli peli;
    
    
    public App(){
        this.frame = new JFrame();
        this.peli = new Peli();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new App());
    }
    
    @Override
    public void run(){
        frame.setPreferredSize(new Dimension(700,700));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        Thread thread = new Thread(peli); 
        luoKomponentit(frame.getContentPane(), thread);
        
        thread.start();

        frame.pack();
        frame.setVisible(true);
        
        
        
    }
    
    private void luoKomponentit(Container c, Thread thread){
        c.setLayout(new GridLayout(1,2));
        peli.addKeyListener(new Kuuntelija(peli));
        peli.setFocusable(true);
        
        JPanel stuffPanel = new JPanel();
        stuffPanel.setLayout(new GridLayout(4,1));
        JLabel nameLabel = new JLabel("PolyTetris"); 
        nameLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        JLabel scoreLabel =  new JLabel("Score: 0"); 
        scoreLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        JLabel lineLabel = new JLabel("Lines: 0"); 
        lineLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        JLabel levelLabel = new JLabel("Level: 1"); 
        levelLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
        
        stuffPanel.add(nameLabel);
        stuffPanel.add(scoreLabel);
        stuffPanel.add(lineLabel);
        stuffPanel.add(levelLabel);
        
        peli.supplyLabels(scoreLabel, lineLabel, levelLabel);
        
        
        GridBagConstraints rajoite = new GridBagConstraints();
        
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridBagLayout());
        
        rajoite.fill = GridBagConstraints.BOTH; 
        rajoite.gridx = 0;
        rajoite.gridy = 0;
        rajoite.ipady = peli.getRequiredHeight();
        rajoite.ipadx = peli.getRequiredWidth();
        gamePanel.add(peli, rajoite);
        
        JButton nappi = new JButton("New Game");
        nappi.addActionListener(new NapinKuuntelija(peli, thread));
        
        rajoite.fill = GridBagConstraints.REMAINDER;
        rajoite.gridx = 0;
        rajoite.gridy = 1;
        rajoite.ipady = 0;
        rajoite.ipadx = 0;
        gamePanel.add(nappi,rajoite);
        
        c.add(gamePanel);
        c.add(stuffPanel);
        
    }
}
