/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JMSearchPanel.java
 *
 * Created on Apr 1, 2009, 12:35:48 AM
 */

package SoundPack;

import edu.uml.sl.*;

import java.util.Vector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 *
 * @author James
 */
public class JMSearchPanel extends javax.swing.JPanel {

    private SoundLibraryQuery query;
//    private Object jbttnSearch;
//    private Object jlblFind;
    private enum search_type{TITLE, AUTHOR, GENRE, TAGS, CUSTOM}
    private search_type current_search = search_type.CUSTOM;


    /** Creates new form JMSearchPanel */
    public JMSearchPanel() {
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

    jcbSearching = new javax.swing.JComboBox();
    jtfSearchText = new javax.swing.JTextField();
    jlblSearchBy = new javax.swing.JLabel();
    jlblFind = new javax.swing.JLabel();
    jbttnSearch = new javax.swing.JButton();

    jcbSearching.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Title", "Artist", "Genre", "Tags", "Custom" }));
    jcbSearching.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jcbSearchingActionPerformed(evt);
      }
    });

    jlblSearchBy.setText("Search By:");

    jlblFind.setText("Search For:");

    jbttnSearch.setText("Search");
    jbttnSearch.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jbttnSearchActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(jlblSearchBy)
          .addComponent(jtfSearchText, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
          .addComponent(jlblFind)
          .addComponent(jbttnSearch)
          .addComponent(jcbSearching, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap(281, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jlblSearchBy)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jcbSearching, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(33, 33, 33)
        .addComponent(jlblFind)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jtfSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(37, 37, 37)
        .addComponent(jbttnSearch)
        .addContainerGap(116, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

    private void jcbSearchingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSearchingActionPerformed
        // TODO add your handling code here:
        int sel = jcbSearching.getSelectedIndex();
        System.out.println(sel);
        if(sel == 0){
            current_search = search_type.TITLE;
        }
        else if(sel == 1){
            current_search = search_type.AUTHOR;
        }
        else if(sel == 2){
            current_search = search_type.GENRE;
        }
        else if(sel == 3){
            current_search = search_type.TAGS;
        }
        else if(sel == 4){
            current_search = search_type.CUSTOM;
        }
}//GEN-LAST:event_jcbSearchingActionPerformed

    private void jbttnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbttnSearchActionPerformed
        // TODO add your handling code here:
        try{
            Vector<SoundLibraryEntry> results = new Vector<SoundLibraryEntry>();
            query = new SoundLibraryQuery();
            if(jtfSearchText.getText().isEmpty()){
                results = query.executeQuery();
                }
            else{
                switch( current_search ){
                    case TITLE:
                        query.setQuerySearchByTitle(jtfSearchText.getText());
                        break;
                    case AUTHOR:
                        query.setQuerySearchByAuthor(jtfSearchText.getText());
                        break;
                    case GENRE:
                        query.setQuerySearchByGenre(jtfSearchText.getText());
                        break;
                    case TAGS:
                        query.setQuerySearchByTags(jtfSearchText.getText());
                        break;
                    case CUSTOM:
                        query.setQuery(jtfSearchText.getText());
                        break;
                    }
                    results = query.executeQuery();
//                    JMMusicMain.Singleton.updateFromSearch( results );
                    }
            }
               catch( Exception exception ){
                   System.err.println( exception );
               }
    }//GEN-LAST:event_jbttnSearchActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jbttnSearch;
  private javax.swing.JComboBox jcbSearching;
  private javax.swing.JLabel jlblFind;
  private javax.swing.JLabel jlblSearchBy;
  private javax.swing.JTextField jtfSearchText;
  // End of variables declaration//GEN-END:variables

}