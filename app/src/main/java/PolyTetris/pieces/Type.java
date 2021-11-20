/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polytetris.pieces;

import java.awt.Color;

/**
 *
 * @author Ilari
 */
public enum Type {
    MONOMINO(Color.PINK, new int[][][]{{{}}}),
    DOMINO(Color.PINK, new int[][][]{{{0,1}},{{1,0}}}),
    TROMINO_L(Color.PINK, new int[][][]{{{1,0},{0,1}},{{0,1},{-1,0}},{{-1,0},{0,-1}},{{0,-1},{1,0}}}),
    TROMINO_I(Color.PINK, new int[][][]{{{0,1},{0,-1}},{{1,0},{-1,0}}}),
    //TETRA_I,TETRA_L,TETRA_T,TETRA_O,TETRA_S,
    PENTA_F(Color.CYAN,new int[][][]{{{-1,0},{0,1},{0,-1},{1,-1}},{{-1,0},{0,-1},{1,0},{1,1}},{{-1,1},{0,1},{0,-1},{1,0}},{{-1,-1},{-1,0},{1,0},{0,1}}}),
    PENTA_I(Color.RED,new int[][][]{{{-2,0},{-1,0},{1,0},{2,0}},{{0,-2},{0,-1},{0,1},{0,2}}}),
    PENTA_L(Color.BLUE,new int[][][]{{{-1,-1},{-1,0},{1,0},{2,0}}, {{0,-1},{1,-1},{0,1},{0,2}}, {{-2,0},{-1,0},{1,0},{1,1}}, {{-1,1},{0,1},{0,-1},{0,-2}}}),
    PENTA_N(Color.GREEN,new int[][][]{{{-1,1},{0,1},{1,0},{2,0}},{{-1,0},{-1,-1},{0,1},{0,2}},{{-2,0},{-1,0},{0,-1},{1,-1}},{{0,-2},{0,-1},{1,0},{1,1}}}),
    PENTA_P(Color.MAGENTA,new int[][][]{{{-1,0},{-1,1},{0,1},{0,-1}},{{-1,0},{-1,-1},{0,-1},{1,0}},{{0,-1},{0,1},{1,-1},{1,0}},{{-1,0},{0,1},{1,1},{1,0}}}),
    PENTA_T(Color.YELLOW,new int[][][]{{{-1,-1},{0,-1},{1,-1},{0,1}},{{-1,0},{1,-1},{1,0},{1,1}},{{0,-1},{-1,1},{0,1},{1,1}},{{-1,-1},{-1,0},{-1,1},{1,0}}}),
    PENTA_U(Color.WHITE,new int[][][]{{{-1,0},{-1,-1},{1,0},{1,-1}},{{1,-1},{0,-1},{1,1},{0,1}},{{-1,0},{-1,1},{1,0},{1,1}},{{-1,-1},{0,-1},{-1,1},{0,1}}}),
    PENTA_V(Color.BLUE,new int[][][]{{{2,0},{1,0},{0,-1},{0,-2}},{{2,0},{1,0},{0,1},{0,2}},{{-2,0},{-1,0},{0,1},{0,2}},{{-2,0},{-1,0},{0,-1},{0,-2}}}),
    PENTA_W(Color.GREEN,new int[][][]{{{-1,0},{-1,-1},{0,1},{1,1}},{{-1,0},{-1,1},{0,-1},{1,-1}},{{0,-1},{-1,-1},{1,0},{1,1}},{{0,1},{-1,1},{1,0},{1,-1}}}),
    PENTA_X(Color.RED,new int[][][]{{{-1,0},{1,0},{0,-1},{0,1}}}),
    PENTA_Y(Color.ORANGE,new int[][][]{{{-1,0},{0,-1},{1,0},{2,0}},{{0,-1},{1,0},{0,1},{0,2}},{{-2,0},{-1,0},{0,1},{1,0}},{{-1,0},{0,1},{0,-1},{0,-2}}}),
    PENTA_Z(Color.CYAN,new int[][][]{{{-1,-1},{0,-1},{0,1},{1,1}},{{-1,0},{-1,1},{1,0},{1,-1}}});

    private int[][][] stages;
    private Color color;
    
    private Type(Color c,int [][][] stages){
        this.color = c;
        this.stages = stages;
        if (c == Color.ORANGE){
            color = new Color(255,150,0);
        }
    }
    
    public int[][][] getStages(boolean reverse){ 
        if (!reverse){
            return this.stages;
        }
        return mirror();
    }
    
    private int[][][] mirror(){
        int[][][] palaute = stages;
        if (this == MONOMINO) {
            return stages;
        }
        
        for (int i=0;i< stages.length;i++){
            for (int j=0;j<stages[i].length;j++){
                    palaute[i][j][0] *= -1;
            }
        }
        
        if (palaute.length == 4){
            int[][] apu = palaute[1];
            palaute[1] = palaute[3];
            palaute[3] = apu;
        }
        
        return palaute;
    }
    
    public Color getColor(){
        return this.color;
    }
    
}
