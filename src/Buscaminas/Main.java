package Buscaminas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JOptionPane;

/**
 *
 * @author sharco-code
 */
public class Main extends javax.swing.JFrame {

    /**
     * Clase principal
     */
    static boolean b = false;
    static Button[][] table;
    
    public static void gameOver() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                table[i][j].gameOver();
            }
        }
        JOptionPane.showMessageDialog(null, "Has perdido", "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void propagation(int i,int j) {
                
        if(i!=0) {
            if(j!=0) {
                if(!table[i-1][j-1].isPressed()) table[i-1][j-1].propagar();
            }
            if(!table[i-1][j].isPressed()) table[i-1][j].propagar();
            if(j!=table[0].length-1){
                if(!table[i-1][j+1].isPressed()) table[i-1][j+1].propagar();
            }
        }

        if(j!=0) {
            if(!table[i][j-1].isPressed()) table[i][j-1].propagar();
        }
        if(j!=table[0].length-1) {
            if(!table[i][j+1].isPressed()) table[i][j+1].propagar();
        }


        if(i!=table.length-1) {
            if(j!=0){
                if(!table[i+1][j-1].isPressed()) table[i+1][j-1].propagar();
            }
            if(!table[i+1][j].isPressed()) table[i+1][j].propagar();
            if(j!=table[0].length-1){
                if(!table[i+1][j+1].isPressed()) table[i+1][j+1].propagar();
            }
        }

    }
    
    public static boolean checkWin() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if(!table[i][j].isMina()&&!table[i][j].isPressed()) return false;
            }
        }
        return true;
    }
    public static void gameWin() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                //table[i][j].gameOver();
            }
        }
        JOptionPane.showMessageDialog(null, "Has ganado", "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
    }
    public Main() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_create = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        input1 = new javax.swing.JTextField();
        input2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        mineCount = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btn_create.setText("Crear");
        btn_create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_createActionPerformed(evt);
            }
        });

        mainPanel.setLayout(new java.awt.GridBagLayout());

        input1.setText("10");

        input2.setText("10");
        input2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Número de minas: ");

        mineCount.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(input1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(input2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_create)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mineCount)
                        .addGap(0, 138, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_create)
                    .addComponent(input1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(input2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(mineCount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btn_createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_createActionPerformed
        try {
            if(Integer.parseInt(input1.getText())<1||Integer.parseInt(input2.getText())<1) {
                JOptionPane.showMessageDialog(null, "Numero de filas o columnas invalido", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Datos en filas o columnas invalido", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(b) {
            table=null;
            Button.firstclic=true;
            mainPanel.removeAll();
            b=false;
        }
        
        table =  new Button[Integer.parseInt(input1.getText())][Integer.parseInt(input2.getText())];
        Button.setMatrix(new int[Integer.parseInt(input1.getText())][Integer.parseInt(input2.getText())]);
        Button.setNumMinas((Integer.parseInt(input1.getText())*Integer.parseInt(input2.getText())*15/100));
        Button.determinate();
        Button.determinateNums();
        
        mineCount.setText(Integer.toString(Button.getNumMinas()));
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx=1;
        constraints.weighty=1;
        constraints.fill=1;
        for (int i = 0; i < Integer.parseInt(input1.getText()); i++) {
            for (int j = 0; j < Integer.parseInt(input2.getText()); j++) {
                constraints.gridx=i;
                constraints.gridy=j;
                table[i][j] = new Button(i,j,Button.check(i,j),Button.checkNums(i,j));
                mainPanel.add(table[i][j],constraints);
                this.pack();
            }
        }
        b = true;
        
    }//GEN-LAST:event_btn_createActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    private javax.swing.JButton btn_create;
    private javax.swing.JTextField input1;
    private javax.swing.JTextField input2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel mineCount;
}
