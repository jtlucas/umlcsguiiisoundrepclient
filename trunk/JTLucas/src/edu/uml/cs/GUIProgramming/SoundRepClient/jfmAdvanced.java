/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jfmAdvanced.java
 *
 * Created on Apr 7, 2009, 9:17:29 AM
 */

package edu.uml.cs.GUIProgramming.SoundRepClient;

import edu.uml.cs.GUIProgramming.heines.*;
/**
 *
 * @author JTLucas
 */
public class jfmAdvanced extends javax.swing.JFrame {

  /** the following is required to keep NetBeans happy */
  static final long serialVersionUID = 0 ;

  private boolean AdvancedFindActive = false;
  private boolean AdvancedOK = false;

    /** Creates new form jfmAdvanced */
    public jfmAdvanced() {
      GUIUtilities.SetNetBeansCompatibleUIManager();
      initComponents();
      jlblError.setText("");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jButton2 = new javax.swing.JButton();
    jtfAdvancedString = new javax.swing.JTextField();
    jbtnFind = new javax.swing.JButton();
    jbtnCancel = new javax.swing.JButton();
    jlblSQLQueryTitle = new javax.swing.JLabel();
    jlblError = new javax.swing.JLabel();

    jButton2.setText("jButton2");

    setTitle("Advanced SQL Find");
    setAlwaysOnTop(true);
    setResizable(false);

    jtfAdvancedString.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jtfAdvancedStringActionPerformed(evt);
      }
    });

    jbtnFind.setText("Find");
    jbtnFind.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jbtnFindActionPerformed(evt);
      }
    });

    jbtnCancel.setText("Cancel");
    jbtnCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jbtnCancelActionPerformed(evt);
      }
    });

    jlblSQLQueryTitle.setText("SQL Query");

    jlblError.setText("ERROR");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(124, 124, 124)
            .addComponent(jbtnFind)
            .addGap(67, 67, 67)
            .addComponent(jbtnCancel))
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jlblSQLQueryTitle)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jtfAdvancedString, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
          .addGroup(layout.createSequentialGroup()
            .addGap(56, 56, 56)
            .addComponent(jlblError, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jtfAdvancedString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jlblSQLQueryTitle))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jlblError)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jbtnFind)
          .addComponent(jbtnCancel))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void jtfAdvancedStringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfAdvancedStringActionPerformed
      jbtnFindActionPerformed(evt);
}//GEN-LAST:event_jtfAdvancedStringActionPerformed

    private void jbtnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnFindActionPerformed
      if(!jtfAdvancedString.getText().isEmpty()){
        //run check on string for error
        //no error then
        AdvancedFindActive = true;
        setVisible(false);
      }
    }//GEN-LAST:event_jbtnFindActionPerformed

    private void jbtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelActionPerformed
      AdvancedFindActive = false;
      AdvancedOK = false;
      setVisible(false);
    }//GEN-LAST:event_jbtnCancelActionPerformed

    public void ResetFields(){
      jtfAdvancedString.setText("");
    }

    public boolean getAdvancedFindActive (){
      return AdvancedFindActive;
    }

    public boolean getAdvancedOK() {
      return AdvancedOK;
    }

    public String getAdvancedSearchStr(){
      return jtfAdvancedString.getText();
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jfmAdvanced().setVisible(true);
            }
        });
    }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton2;
  private javax.swing.JButton jbtnCancel;
  private javax.swing.JButton jbtnFind;
  private javax.swing.JLabel jlblError;
  private javax.swing.JLabel jlblSQLQueryTitle;
  private javax.swing.JTextField jtfAdvancedString;
  // End of variables declaration//GEN-END:variables

}
