/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author lucas
 */
public class Simulacion extends javax.swing.JFrame {
    private Simulador simulador; 
    private List<JLabel> textos;
    /**
     * Creates new form 
     */
    public Simulacion(String ruta) throws FileNotFoundException, IOException {
        initComponents();
        this.setLocationRelativeTo(null);
        textos = new ArrayList<>();
        simulador = new Simulador(ruta);
        simulador.DefinirCombinaciones();
        Estacion auxE;
        Tren auxT;
        for(int i = 0; i < simulador.getLineas().size(); i++) {
            JLabel texto = new JLabel("Línea " + simulador.getLineas().get(i).getNombre()+": ");
            for(int j = 0; j < simulador.getLineas().get(i).getEstaciones().size(); j++) {
                auxE = simulador.getLineas().get(i).GetEstacion(j);
                texto.setText(texto.getText() + auxE.getNombre() + " " + auxE.getGenteEsperando() + " - ");
                
            }
            panel.add(texto);
            textos.add(texto);
        }   
        JLabel textoTrenes = new JLabel("Trenes:");
        for(int i = 0; i < simulador.getLineas().size(); i++) {
            for(int j = 0; j < simulador.getLineas().get(i).getTrenes().size(); j++) {
                auxT = simulador.getLineas().get(i).getTrenes().get(j);
                textoTrenes.setText(textoTrenes.getText() + auxT.getEstacion().getNombre() + " " + auxT.getPasajerosActuales() + " - ");
            }
        }
        textos.add(textoTrenes);
        panel.add(textoTrenes);
        panel.updateUI();
    }

    private Simulacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Siguiente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Terminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Simulacion");

        panel.setLayout(new java.awt.GridLayout(7, 0));
        jScrollPane1.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 600, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Terminar obj = new Terminar(simulador);
        obj.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        for(int i = 0; i < textos.size(); i++)
            textos.get(i).setText(null);
        panel.removeAll();
        panel.updateUI();
        simulador.NuevoCiclo(simulador);
        simulador.CorregirGente();
        Estacion auxE;
        Tren auxT;
        for(int i = 0; i < simulador.getLineas().size(); i++) {
            JLabel texto = new JLabel("Línea " + simulador.getLineas().get(i).getNombre()+": ");
            for(int j = 0; j < simulador.getLineas().get(i).getEstaciones().size(); j++) {
                auxE = simulador.getLineas().get(i).GetEstacion(j);
                texto.setText(texto.getText() + auxE.getNombre() + " " + auxE.getGenteEsperando() + " - ");
                
            }
            panel.add(texto);
            textos.add(texto);
        }   
        JLabel textoTrenes = new JLabel("Trenes:");
        for(int i = 0; i < simulador.getLineas().size(); i++) {
            for(int j = 0; j < simulador.getLineas().get(i).getTrenes().size(); j++) {
                auxT = simulador.getLineas().get(i).getTrenes().get(j);
                textoTrenes.setText(textoTrenes.getText() + auxT.getEstacion().getNombre() + " " + auxT.getPasajerosActuales() + " - ");
            }
        }
        textos.add(textoTrenes);
        panel.add(textoTrenes);
        panel.updateUI();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Simulacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
