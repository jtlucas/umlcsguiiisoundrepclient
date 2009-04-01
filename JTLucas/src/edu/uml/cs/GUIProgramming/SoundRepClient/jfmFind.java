/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jfmFind.java
 *
 * Created on Mar 28, 2009, 3:08:47 PM
 */

package edu.uml.cs.GUIProgramming.SoundRepClient;

import edu.uml.cs.GUIProgramming.heines.*;
import javax.swing.JOptionPane;

/**
 *
 * @author JTLucas
 */
public class jfmFind extends javax.swing.JFrame {

    /** Creates new form jfmFind */
    public jfmFind() {
      GUIUtilities.SetNetBeansCompatibleUIManager();
      initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jcbTitle = new javax.swing.JCheckBox();
    jcbArtist = new javax.swing.JCheckBox();
    jcbGenre = new javax.swing.JCheckBox();
    jcbTags = new javax.swing.JCheckBox();
    jtfTitle = new javax.swing.JTextField();
    jtfArtist = new javax.swing.JTextField();
    jtfGenre = new javax.swing.JTextField();
    jtfTags = new javax.swing.JTextField();
    jbtnAdvanced = new javax.swing.JButton();
    jbtnFind = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Find");
    setAlwaysOnTop(true);
    setMinimumSize(new java.awt.Dimension(297, 208));
    setResizable(false);

    jcbTitle.setText("Title");

    jcbArtist.setText("Artist");

    jcbGenre.setText("Genre");

    jcbTags.setText("Tags");

    jtfTitle.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jtfTitleActionPerformed(evt);
      }
    });

    jtfArtist.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jtfArtistActionPerformed(evt);
      }
    });

    jtfGenre.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jtfGenreActionPerformed(evt);
      }
    });

    jtfTags.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jtfTagsActionPerformed(evt);
      }
    });

    jbtnAdvanced.setText("Advanced");

    jbtnFind.setText("Search");
    jbtnFind.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jbtnFindActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jcbTitle)
              .addComponent(jcbArtist)
              .addComponent(jcbGenre)
              .addComponent(jcbTags))
            .addGap(39, 39, 39)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(jtfTags)
              .addComponent(jtfGenre)
              .addComponent(jtfArtist)
              .addComponent(jtfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jbtnAdvanced)
            .addGap(87, 87, 87)
            .addComponent(jbtnFind)))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jcbTitle)
          .addComponent(jtfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jcbArtist)
          .addComponent(jtfArtist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jcbGenre)
          .addComponent(jtfGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jcbTags)
          .addComponent(jtfTags, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jbtnFind)
          .addComponent(jbtnAdvanced))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void jbtnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnFindActionPerformed
      // TODO add your handling code here:
      // this will start the search
      setVisible(false);
}//GEN-LAST:event_jbtnFindActionPerformed

    private void jtfTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfTitleActionPerformed
      // If enter key is pressed run search
      jbtnFindActionPerformed(evt);
    }//GEN-LAST:event_jtfTitleActionPerformed

    private void jtfArtistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfArtistActionPerformed
      // If enter key is pressed run search
      jbtnFindActionPerformed(evt);
    }//GEN-LAST:event_jtfArtistActionPerformed

    private void jtfGenreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfGenreActionPerformed
      // If enter key is pressed run search
      jbtnFindActionPerformed(evt);
    }//GEN-LAST:event_jtfGenreActionPerformed

    private void jtfTagsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfTagsActionPerformed
      // If enter key is pressed run search
      jbtnFindActionPerformed(evt);
    }//GEN-LAST:event_jtfTagsActionPerformed

    public void ClearTextFields() {
      jtfTitle.setText("");
      jtfArtist.setText("");
      jtfGenre.setText("");
      jtfTags.setText("");
    }

    public String getSearchstrTitle() {
      if(jcbTitle.isEnabled()){
        return jtfTitle.getText();
      }
      else {
        return "";
      }
    }

    public String getSearchstrArtist() {
      if(jcbArtist.isEnabled()){
        return jtfArtist.getText();
      }
      else {
        return "";
      }
    }

    public String getSearchstrGenre() {
      if(jcbGenre.isEnabled()){
        return jtfGenre.getText();
      }
      else {
        return "";
      }
    }

    public String getSearchstrTags() {
      if(jcbTags.isEnabled()){
        return jtfTags.getText();
      }
      else {
        return "";
      }
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jfmFind().setVisible(true);
            }
        });
    }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jbtnAdvanced;
  private javax.swing.JButton jbtnFind;
  private javax.swing.JCheckBox jcbArtist;
  private javax.swing.JCheckBox jcbGenre;
  private javax.swing.JCheckBox jcbTags;
  private javax.swing.JCheckBox jcbTitle;
  private javax.swing.JTextField jtfArtist;
  private javax.swing.JTextField jtfGenre;
  private javax.swing.JTextField jtfTags;
  private javax.swing.JTextField jtfTitle;
  // End of variables declaration//GEN-END:variables

}