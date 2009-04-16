/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JMUploadPanel.java
 *
 * Created on Apr 1, 2009, 12:33:39 AM
 */

package SoundPack;

import edu.uml.cs.sl.*;


import java.net.URL;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

//audio stuff
import javax.sound.sampled.*;



/**
 *
 * @author Jerron
 */
public class JMMainListPanel extends javax.swing.JPanel {

     private Vector<SoundLibraryEntry> stored_results;
      private StreamThread stream_thread = null;
      DefaultListModel defModelJList = new DefaultListModel();
      double volume = -1;
    private boolean isPlaying = false;


    AudioInputStream decoded_input_stream;
    AudioInputStream input_stream;

    /** Creates new form JMUploadPanel */
    public JMMainListPanel()  {

    UIManager.LookAndFeelInfo[] info = UIManager.getInstalledLookAndFeels() ;
    int lafno = 3 ;   // desired LookAndFeel for Windows XP
    try {
      UIManager.setLookAndFeel( info[lafno].getClassName() ) ;
    } catch (Exception e) {
      System.out.println( "Couldn't load " + info[lafno].getClassName() +
          " look and feel " + e ) ;
      System.exit( 1 ) ;  // abort run
    }
        
        initComponents();
        SoundLibraryQuery query = new SoundLibraryQuery();
        Vector<SoundLibraryEntry> results = null;
        try {
            results = query.executeQuery();
        } catch (Exception ex) {
            Logger.getLogger(JMMainListPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        stored_results = results;

        volume = volumeJSlider.getValue();
        volume = volume /10;
        
        jList.setModel(defModelJList);
        updateList();
        

    }



    public void sort(){
        defModelJList.clear();
        try{
            SoundLibraryQuery query = new SoundLibraryQuery( "SELECT * FROM library ORDER BY title");

            Vector<SoundLibraryEntry> results = query.executeQuery();
            stored_results = results;

            for( SoundLibraryEntry entry: results ){
                String song = entry.getTitle();
                defModelJList.addElement(song);
            }
        }
        catch( Exception ex ){
            System.err.println( ex );
        }

    }


    //Called to update labels
    public void updateText() {
        if(!jList.isSelectionEmpty()){
            int x = jList.getSelectedIndex();
            songLabel.setText(stored_results.elementAt(x).getTitle());
            artistLabel.setText(stored_results.elementAt(x).getAuthor());
            genreLabel.setText(stored_results.elementAt(x).getGenre());
            tagsLabel.setText(stored_results.elementAt(x).getTags());
        }

    }

    //sets the initial JList of songs.
    public void updateList(){

        try{
            SoundLibraryQuery query = new SoundLibraryQuery();
            Vector<SoundLibraryEntry> results = query.executeQuery();
            stored_results = results;

            for( SoundLibraryEntry entry: results ){
                String song = entry.getTitle();
                defModelJList.addElement(song);
            }
        }
        catch( Exception ex ){
            System.err.println( ex );
        }
    }


    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    jList = new javax.swing.JList();
    playButton = new javax.swing.JButton();
    songTxtLabel = new javax.swing.JLabel();
    artistTxtLabel = new javax.swing.JLabel();
    genreTxtLabel = new javax.swing.JLabel();
    tagsTxtLabel = new javax.swing.JLabel();
    songLabel = new javax.swing.JLabel();
    artistLabel = new javax.swing.JLabel();
    genreLabel = new javax.swing.JLabel();
    tagsLabel = new javax.swing.JLabel();
    errorLabel = new javax.swing.JLabel();
    sortButton = new javax.swing.JButton();
    pauseButton = new javax.swing.JButton();
    stopButton = new javax.swing.JButton();
    volumeJSlider = new javax.swing.JSlider();
    volumeLabel = new javax.swing.JLabel();
    muteButton = new javax.swing.JToggleButton();

    jList.setModel(new javax.swing.AbstractListModel() {
      String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
      public int getSize() { return strings.length; }
      public Object getElementAt(int i) { return strings[i]; }
    });
    jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
        jListValueChanged(evt);
      }
    });
    jScrollPane1.setViewportView(jList);

    playButton.setText("Play");
    playButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        playButtonActionPerformed(evt);
      }
    });

    songTxtLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
    songTxtLabel.setText("Song:");

    artistTxtLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
    artistTxtLabel.setText("Artist:");

    genreTxtLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
    genreTxtLabel.setText("Genre:");

    tagsTxtLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
    tagsTxtLabel.setText("Tags:");

    songLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
    songLabel.setText(" ");

    artistLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
    artistLabel.setText(" ");

    genreLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
    genreLabel.setText(" ");

    tagsLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
    tagsLabel.setText(" ");

    errorLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    errorLabel.setText("     ");

    sortButton.setText("Sort");
    sortButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        sortButtonActionPerformed(evt);
      }
    });

    pauseButton.setText("Pause");
    pauseButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        pauseButtonActionPerformed(evt);
      }
    });

    stopButton.setText("Stop");
    stopButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        stopButtonActionPerformed(evt);
      }
    });

    volumeJSlider.setMaximum(10);
    volumeJSlider.setMinimum(1);
    volumeJSlider.setMinorTickSpacing(1);
    volumeJSlider.setPaintTicks(true);
    volumeJSlider.setSnapToTicks(true);
    volumeJSlider.setValue(5);
    volumeJSlider.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(javax.swing.event.ChangeEvent evt) {
        volumeJSliderStateChanged(evt);
      }
    });

    volumeLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    volumeLabel.setText("Volume:");

    muteButton.setText("Mute");
    muteButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        muteButtonActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(1, 1, 1)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                  .addComponent(songTxtLabel)
                  .addComponent(artistTxtLabel)
                  .addComponent(tagsTxtLabel)
                  .addComponent(genreTxtLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
              .addComponent(pauseButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(tagsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(songLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                  .addComponent(genreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                  .addComponent(artistLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
              .addGroup(layout.createSequentialGroup()
                .addComponent(muteButton)
                .addGap(33, 33, 33)
                .addComponent(volumeLabel)
                .addGap(37, 37, 37)
                .addComponent(volumeJSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())))
          .addGroup(layout.createSequentialGroup()
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(sortButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(errorLabel)
            .addGap(293, 293, 293))))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addGap(25, 25, 25)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(songTxtLabel)
              .addComponent(songLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(artistLabel)
              .addComponent(artistTxtLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(genreLabel)
              .addComponent(genreTxtLabel))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(tagsLabel)
              .addComponent(tagsTxtLabel))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(sortButton)
              .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(playButton)
              .addComponent(stopButton)
              .addComponent(pauseButton)
              .addComponent(muteButton)
              .addComponent(volumeLabel))
            .addContainerGap())
          .addComponent(volumeJSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
    );
  }// </editor-fold>//GEN-END:initComponents

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed

        
        //setting the stop flag to false in case it was set.
        StreamThread.stop = false;
//        if(isPlaying == true){
//                StreamThread.stop = true;
//                isPlaying = false;
//            }

        //if it is paused, just want to continue the thread.
        if(StreamThread.paused == true){
            StreamThread.userPressedPlay();
        }
        //else if(isPlaying == true && )
        else {//if(isPlaying == false ){
            
            
            isPlaying = true; //my local variable to determine if its playing or not.
            errorLabel.setText(" ");
            if(!jList.isSelectionEmpty()){
                try{
                       URL stream_url = stored_results.elementAt(  jList.getSelectedIndex() ).getURL();
                       //stream_thread.setGain(.1);
                       if( stream_thread != null ){
                           stream_thread.requestStop();}

                           stream_thread = new StreamThread( stream_url );
                           
                           //if(volumeJSlider.getValueIsAdjusting()){
                               //double volume = volumeJSlider.getValue();
                               //volume = volume / 10;
                              // System.err.println(volume);
                               stream_thread.setGain(volume);
                           //}


                       //updateStatusText( "Streaming: " + tableModel.getValueAt( dataTable.getSelectedRow(), 0 ) );

                       stream_thread.start();

                       }
                       catch( Exception exception ){
                           System.err.println( exception );
                       }
                }
            else{
                errorLabel.setText("Please select a song.");
            }
        }
}//GEN-LAST:event_playButtonActionPerformed

    private void jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListValueChanged
        
        try {
            updateText();
        } catch (Exception ex) {
            Logger.getLogger(JMMainListPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jListValueChanged

    private void sortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortButtonActionPerformed
        sort();
    }//GEN-LAST:event_sortButtonActionPerformed

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
        StreamThread.userPressedPause();
        isPlaying = false;
}//GEN-LAST:event_pauseButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        StreamThread.stop = true;
        isPlaying = false;

  
}//GEN-LAST:event_stopButtonActionPerformed

    private void volumeJSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_volumeJSliderStateChanged
        volume = volumeJSlider.getValue();
        volume = volume / 10;
        System.err.println(volume);
        if(stream_thread != null){
            stream_thread.setGain(volume);
        }
    }//GEN-LAST:event_volumeJSliderStateChanged

    private void muteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muteButtonActionPerformed
        
        //if it is selected and thread is not null, mute it
        if(muteButton.isSelected() && stream_thread != null){
            stream_thread.setMuted(true);
        }
        else if(stream_thread != null){
            stream_thread.setMuted(false); //unMute it
        }


}//GEN-LAST:event_muteButtonActionPerformed





  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel artistLabel;
  private javax.swing.JLabel artistTxtLabel;
  private javax.swing.JLabel errorLabel;
  private javax.swing.JLabel genreLabel;
  private javax.swing.JLabel genreTxtLabel;
  private javax.swing.JList jList;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JToggleButton muteButton;
  private javax.swing.JButton pauseButton;
  private javax.swing.JButton playButton;
  private javax.swing.JLabel songLabel;
  private javax.swing.JLabel songTxtLabel;
  private javax.swing.JButton sortButton;
  private javax.swing.JButton stopButton;
  private javax.swing.JLabel tagsLabel;
  private javax.swing.JLabel tagsTxtLabel;
  private javax.swing.JSlider volumeJSlider;
  private javax.swing.JLabel volumeLabel;
  // End of variables declaration//GEN-END:variables


}


