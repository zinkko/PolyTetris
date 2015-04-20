/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polytetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import polytetris.pieces.*;

/**
 *
 * @author Ilari
 */
public class Peli extends JPanel implements Runnable{ 
    
    public static final int BLOCK_SIZE = 10;
    
    private int maxX, maxY;
    private int lines,score;
    private int delay;
    
    private Piece currentPiece, pohja, ghost;
    private Random random;
    
    private int seedX, seedY;
    
    private JLabel scoreLbl;
    private JLabel lineLbl;
    private JLabel levelLbl;
    private boolean end;
    
    public Peli(){
        super();
        this.end = false;
        lines = 0;
        this.delay = 50;
        this.random = new Random();
        
        this.maxX = 20; this.maxY = 40;
        
        this.seedX = maxX/2; this.seedY = 2;
    } 
    
    @Override
    protected void paintComponent(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, maxX*(Peli.BLOCK_SIZE+1), maxY*(Peli.BLOCK_SIZE+1));
        
        if (currentPiece != null){
            currentPiece.piirra(g);
        }
        
        if (ghost != null) {
            ghost.piirra(g);
        }
        
        if (pohja != null){
            pohja.piirra(g);
        }
    }
    
    public void supplyLabels(JLabel... labels){
        this.scoreLbl = labels[0];
        this.lineLbl = labels[1];
        this.levelLbl = labels[2];
    }
    
    public void resetScores(){
        this.score = 0;
        this.lines = 0;
        this.lineLbl.setText("Lines: 0");
        this.scoreLbl.setText("Score: 0");
        this.levelLbl.setText("Level: 1");
    }
    
    public void addLine(){
        lines++;
        if (lines%4==0){
            delay*=3;
            delay/=2;
            if (delay<0) {
                delay = 0;
            }
            //System.out.println("delay: "+delay);
        }
        
        try{
            this.lineLbl.setText("Lines: "+this.lines);
            this.levelLbl.setText("Level: "+(this.lines/4+1));
        }catch(NullPointerException e){
            
        }
        //System.out.println("score: "+lines);
        
        increaseScore(100);
    }
    
    public int getRequiredHeight(){
        return (maxY-1)*(BLOCK_SIZE+1);
    }
    
    public int getRequiredWidth(){
        return (maxX-1)*(BLOCK_SIZE+1);
    }
    
    public void endGame(){
        this.end = true;
    }
    
    public void increaseScore(int amount){
        score += amount;
        
        try{
            this.scoreLbl.setText("Score: "+this.score);
        }catch (NullPointerException e){
        }
    }
    
    public int getScore(){
        return this.score;
    }
    
    public int getLines(){
        return this.lines;
    }
    
    public void turn(boolean clockwise){
        try{
            this.currentPiece.turn(clockwise);
            updateGhost();
        }catch (NullPointerException e){
            
        }
    }
    
    
    
    public boolean currentPieceCollides(){
        if (pohja==null){
            return false;
        }
        
        return currentPiece.collide(pohja) || currentPiece.isOffscreenX(maxX) || currentPiece.isOffscreenY(maxY);
    }
    
    public int getMaxY(){
        return maxY;
    }
    
    public int getMaxX(){
        return this.maxX;
    }
    
    public void setPohja(Piece pala){
        this.pohja = new Pohja(this,pala.getParts());
    }
    
    public void move(Direction d){
        try{
            this.currentPiece.move(d);
            if (this.currentPiece.isOffscreenX(maxX)){
                if (d == Direction.LEFT){
                    this.currentPiece.move(Direction.RIGHT);
                }else{
                    this.currentPiece.move(Direction.LEFT);
                }
            }else if (currentPiece.collide(pohja)){
                this.currentPiece.move(d.reverse());
            }
            updateGhost();
        } catch (NullPointerException e){
        }
    }
    
    public Piece getCurrentPiece(){
        return this.currentPiece;
    }
    
    public Piece getPohja(){
        return this.pohja;
    }
    
    public void resetPala(){
        currentPiece = uusiPala();
        updateGhost();
    }
    
    
    private void updateGhost(){
        ghost = currentPiece.clone(currentPiece.getX(), seedY);
        if (pohja == null) {
            while (!ghost.isOffscreenY(maxY)) {
                ghost.move(Direction.DOWN);
            }
            ghost.move(Direction.UP);
            return;
        }
        while (!ghost.collide(pohja) && ! ghost.isOffscreenY(maxY)){
            ghost.move(Direction.DOWN);
        }
        ghost.move(Direction.UP);
    }
    
    @Override
    public void run(){
        int tick = 0;
        this.pohja = null; // so that this will work also when re-starting
        lines = 0;
        delay = 50;
        if (currentPiece == null){
            currentPiece = this.uusiPala();
            updateGhost();
        }
        

        MAIN_LOOP:
        while (true){
            try{
                
                if(end){
                    end = false;
                    break MAIN_LOOP; 
                }
                
               Thread.sleep(10);
               
               if (tick ==0){
                   currentPiece.move(Direction.DOWN);
               }
               
               
               if (currentPiece.isOffscreenY(maxY) || (pohja != null && this.currentPiece.collide(pohja))){
                   
                   currentPiece.move(Direction.UP);
                   
                   if (this.pohja == null){
                       this.pohja = new Pohja(this,currentPiece.getParts());
                   }else{
                        this.pohja.addBlocks(currentPiece.getParts());
                        
                        int x = this.currentPiece.getY();
                        this.pohja.checkRows(currentPiece.getLowY(),currentPiece.getHighY());
                   }
                   
                   currentPiece = this.uusiPala();
                   increaseScore(5);
                   if (pohja != null && currentPiece.collide(pohja)){
                       System.out.println("Game Over");
                       break MAIN_LOOP;
                   }
               }
               
               tick++;
               tick %= delay;
               
            }catch (InterruptedException e){
                
            }
            
            this.repaint();
        }
    }  
    
    private Piece uusiPala(){
        
        Type type = Type.values()[this.random.nextInt(Type.values().length)];
        
        return new Piece(seedX,seedY,type,Math.random()<0.5);

    }
}
