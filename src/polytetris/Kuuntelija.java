/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polytetris;

/**
 *
 * @author Ilari
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import polytetris.pieces.Piece;

public class Kuuntelija implements KeyListener{
    
    private Peli peli;
    
    public Kuuntelija(Peli peli){
        this.peli = peli;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        //System.out.println("key "+e.getKeyChar()+" pressed");
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                peli.turn(true);
                if (peli.currentPieceCollides()){
                    peli.turn(false);
                }
                break;
            case KeyEvent.VK_ENTER:
                this.drop();
                break;
            case KeyEvent.VK_DOWN:
                peli.move(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                peli.move(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                peli.move(Direction.RIGHT);
                break;
            default:
                break;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e){
        
    }
    
    private void drop(){
        Piece pala = peli.getCurrentPiece();
        
        while (true){
            pala.move(Direction.DOWN);
            
            if (peli.getPohja() != null && pala.collide(peli.getPohja())){
                pala.move(Direction.UP);
                peli.resetPala();
                peli.getPohja().addBlocks(pala.getParts());
                peli.getPohja().checkRows(pala.getLowY(),pala.getHighY());
                break;
            }
            
            if (pala.isOffscreenY(peli.getMaxY())){
                pala.move(Direction.UP);
                peli.resetPala();
                if (peli.getPohja() == null){
                    peli.setPohja(pala);
                }else{
                    peli.getPohja().addBlocks(pala.getParts());
                    peli.getPohja().checkRows(pala.getLowY(),pala.getHighY());
                }
                
                break;
            }
            
            peli.increaseScore(1);
        }
        
        
    }
}
