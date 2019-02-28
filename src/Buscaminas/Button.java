
package Buscaminas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author sharco-code
 */

public class Button extends JButton {
    private static int[][] matrix;
    private static boolean[][] matrixMinas;
    private static int numMinas;
    private static int[][] numOnMinas;
    
    private int i;
    private int j;
    
    private int num;
    
    private boolean mina;
    private boolean pressed;
    private boolean gameOverClick;
    
    static boolean firstclic= true;
    
    private boolean rightclic;
    private static Dimension x = new Dimension(40,40);
    
    Button(int i, int j, boolean mina, int num) {
        
        this.i = i;
        this.j = j;
        this.mina =  mina;
        this.num = num;
        
        this.setFont(new Font("Arial", 1, 20));
        super.setMinimumSize(x);
        super.setPreferredSize(x);
        super.setMaximumSize(x);
        
        this.setFocusable(false);
        
        this.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(SwingUtilities.isLeftMouseButton(e)) {
                    clic();
                }
                if(SwingUtilities.isRightMouseButton(e)) {
                    rightclic=!rightclic;
                    rightclic();
                }
            }
        });
    }
    public void rightclic() {
        if(rightclic) this.setBackground(Color.yellow);
        else this.setBackground(Color.lightGray);
    }
    public void gameOver() {
        if(mina) {
            this.setBackground(Color.red);
            this.gameOverClick = true;
        } else {
            this.setBackground(Color.lightGray);
            this.gameOverClick = true;
        }
    }
    
    public static boolean check(int i, int j) {
        return matrixMinas[i][j];
    }
    public static int checkNums(int i, int j) {
        return numOnMinas[i][j];
    }
    
    public static void determinate() {
        
        int minas = numMinas;
        
        int numRandom;
        int numRandom2;
        
        while (minas!=0) {
            numRandom = (int)(Math.random()*matrix.length);
            numRandom2 = (int)(Math.random()*matrix[0].length);
            
            if(!matrixMinas[numRandom][numRandom2]) {
                matrixMinas[numRandom][numRandom2] = true;
                minas--;
            }
        }
    }
    public static void determinateNums() {
        int c = 0;
        for (int i = 0; i < matrixMinas.length; i++) {
            for (int j = 0; j < matrixMinas[0].length; j++) {
                
                if(i!=0) {
                    if(j!=0) {
                        if(matrixMinas[i-1][j-1]) c++;
                    }
                    if(matrixMinas[i-1][j]) c++;
                    if(j!=matrixMinas[0].length-1){
                        if(matrixMinas[i-1][j+1]) c++;
                    }
                }
                
                if(j!=0) {
                    if(matrixMinas[i][j-1]) c++;
                }
                if(j!=matrixMinas[0].length-1) {
                    if(matrixMinas[i][j+1]) c++;
                }
                
                
                if(i!=matrixMinas.length-1) {
                    if(j!=0){
                        if(matrixMinas[i+1][j-1]) c++;
                    }
                    if(matrixMinas[i+1][j]) c++;
                    if(j!=matrixMinas[0].length-1){
                        if(matrixMinas[i+1][j+1]) c++;
                    }
                }
                
                
                numOnMinas[i][j] = c;
                c=0;
                
                /*
                    i-1,j-1   i-1,j   i-1,j+1
                    i,j-1     i,j     i,j+1
                    i+1,j-1   i+1,j   i+1,j+1
                */
                /*
                    i,j     i,j+1
                    i+1,j   i+1,j+1
                */
                
            }
        }
    }
    
    public void clic() {
        if(firstclic) {
            pressed = true;
            if(num!=0) this.setText(Integer.toString(num));
            else this.setText(" ");
            this.setEnabled(false);
            Main.propagation(i, j);
            if(Main.checkWin()) Main.gameWin();
            firstclic=false;
        } else {
            pressed = true;
            if(this.isEnabled()) {
                if(mina&&!gameOverClick) {
                    this.setBackground(Color.red);
                    Main.gameOver();
                } else if (!mina&&!gameOverClick) {
                    if(num!=0) this.setText(Integer.toString(num));
                    else this.setText(" ");
                    this.setEnabled(false);
                    if(this.num==0) Main.propagation(i, j);
                    if(Main.checkWin()) Main.gameWin();
                }
            } 
        }
        
    }
    
    public void propagar() {
        pressed = true;
        if(!mina) {
            if(num!=0) this.setText(Integer.toString(num));
            else this.setText(" ");
            this.setEnabled(false);            
        }
        if(num==0) {
            Main.propagation(i, j);
        }
    }
    
    public static void setMatrix(int[][] matrix) {
        Button.matrix = matrix;
        matrixMinas = new boolean[matrix.length][matrix[0].length];
        numOnMinas = new int[matrix.length][matrix[0].length];
    }

    public static void setNumMinas(int numMinas) {
        if(numMinas<1) Button.numMinas = 1;
        else Button.numMinas = numMinas;
    }

    public boolean isMina() {
        return mina;
    }

    public boolean isPressed() {
        return pressed;
    }

    public static int[][] getMatrix() {
        return matrix;
    }

    public static int getNumMinas() {
        return numMinas;
    }
    
}
