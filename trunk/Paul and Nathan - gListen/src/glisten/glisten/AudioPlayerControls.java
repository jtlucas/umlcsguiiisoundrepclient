/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AudioPlayerControls.java
 *
 * Created on Mar 30, 2009, 4:09:24 PM
 */

package glisten;

import java.awt.event.MouseEvent;

/**
 *
 * @author paul
 */
public class AudioPlayerControls extends javax.swing.JPanel {

    private java.util.Timer updateTimer;
    private AudioSource src;
    private boolean isSeeking;
    private int loading_dots;

    /** Creates new form AudioPlayerControls */
    public AudioPlayerControls() {
        src = null;
        initComponents();

        jsld_seek.setValue(0);

        jlbl_duration.setText("");
        jlbl_position.setText("");
        jlbl_title.setText("");

        jsld_seek.addMouseListener(new javax.swing.event.MouseInputListener() {

            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                isSeeking = true;
            }

            public void mouseReleased(MouseEvent e) {
                if(src != null)
                {
                    src.setPositionPercentageSafe((float)(jsld_seek.getValue() / 100.0));
                }
                isSeeking = false;
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {

            }

            public void mouseDragged(MouseEvent e) {

            }

            public void mouseMoved(MouseEvent e) {

            }
        }
        );

        updateTimer = new java.util.Timer();
        updateTimer.scheduleAtFixedRate(new updateTask(), 0, 20);
    }

    class updateTask extends java.util.TimerTask {
        public void run() {
          if( src != null) {
              jlbl_title.setText(src.getTitle());
              if( !isSeeking )
              {
                long duration_microseconds = src.getDuration();
                long duration_milliseconds = duration_microseconds / 1000;
                long duration_seconds = duration_milliseconds / 1000;
                long duration_minutes = duration_seconds / 60;

                duration_seconds = duration_seconds - (duration_minutes * 60);
                duration_milliseconds = duration_milliseconds - (duration_minutes * 60000 + duration_seconds * 1000);
                duration_microseconds = duration_microseconds - (duration_minutes * 60000000 + duration_seconds * 1000000 + duration_milliseconds * 1000);
                
                String duration = String.valueOf(duration_minutes) + "m." + String.valueOf(duration_seconds) + "s." + String.valueOf(duration_milliseconds) + "ms." + String.valueOf(duration_microseconds) + "mus";

                long position_microseconds = src.getPosition();
                long position_milliseconds = position_microseconds / 1000;
                long position_seconds = position_milliseconds / 1000;
                long position_minutes = position_seconds / 60;

                position_seconds = position_seconds - (position_minutes * 60);
                position_milliseconds = position_milliseconds - (position_minutes * 60000 + position_seconds * 1000);
                position_microseconds = position_microseconds - (position_minutes * 60000000 + position_seconds * 1000000 + position_milliseconds * 1000);

                String position = String.valueOf(position_minutes) + "m." + String.valueOf(position_seconds) + "s." + String.valueOf(position_milliseconds) + "ms." + String.valueOf(position_microseconds) + "mus";


                jlbl_duration.setText(duration);
                jlbl_position.setText(position);
                jsld_seek.setValue((int)((float)(src.getPosition()) / (float)(src.getDuration()) * 100.0));
              }
              else if ( src.getDuration() > 0 )
              {
                long position_microseconds = (long)(src.getPositionPercentage(jsld_seek.getValue()) / 100.0);
                long position_milliseconds = position_microseconds / 1000;
                long position_seconds = position_milliseconds / 1000;
                long position_minutes = position_seconds / 60;

                position_seconds = position_seconds - (position_minutes * 60);
                position_milliseconds = position_milliseconds - (position_minutes * 60000 + position_seconds * 1000);
                position_microseconds = position_microseconds - (position_minutes * 60000000 + position_seconds * 1000000 + position_milliseconds * 1000);

                String position = String.valueOf(position_minutes) + "m." + String.valueOf(position_seconds) + "s." + String.valueOf(position_milliseconds) + "ms." + String.valueOf(position_microseconds) + "mus";

                jlbl_position.setText(position);
              }
              else if( src.isOpening() )
              {
                jlbl_position.setText("");
                String dots = "";
                for(int i = 0; i < loading_dots; i ++)
                {
                    dots += ".";
                }
                jlbl_duration.setText("loading" + dots);
                loading_dots = (loading_dots + 1) % 4;
              }
              if( src.getPosition() == src.getDuration() && src.isPlaying() ) //seems to be a bug with line listener... hrm, oh well fixed now
              { src.stop();
                src.setPosition(0);
              }
              else if( src.isOpenFailed() )
              {
                jlbl_duration.setText("Failed to load...");
                jlbl_position.setText("");
              }

          }
          else
          {
              jlbl_duration.setText("");
              jlbl_position.setText("");
              jlbl_title.setText("");
          }
        }
      }

    public void open(AudioSource src)
    {
        if(this.src != null)
        {
            this.src.removeAudioStateListeners();
            this.src.close();
        }
        
        jsld_seek.setValue(0);
        this.src = src;
        this.src.checkURL();
        this.src.checkAudioFormat();
        this.src.setVolume(jsld_volume.getValue() / 100.0);
        this.src.open();

    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtn_play = new javax.swing.JButton();
        jbtn_pause = new javax.swing.JButton();
        jbtn_stop = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jlbl_position = new javax.swing.JLabel();
        jlbl_duration = new javax.swing.JLabel();
        jlbl_title = new javax.swing.JLabel();
        jsld_seek = new javax.swing.JSlider();
        jsld_volume = new javax.swing.JSlider();

        jbtn_play.setIcon(new javax.swing.ImageIcon(getClass().getResource("/glisten/PLAY.png"))); // NOI18N
        jbtn_play.setMaximumSize(new java.awt.Dimension(32, 32));
        jbtn_play.setMinimumSize(new java.awt.Dimension(32, 32));
        jbtn_play.setPreferredSize(new java.awt.Dimension(32, 32));
        jbtn_play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_playActionPerformed(evt);
            }
        });

        jbtn_pause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/glisten/PAUSE.png"))); // NOI18N
        jbtn_pause.setMaximumSize(new java.awt.Dimension(32, 32));
        jbtn_pause.setMinimumSize(new java.awt.Dimension(32, 32));
        jbtn_pause.setPreferredSize(new java.awt.Dimension(32, 32));
        jbtn_pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_pauseActionPerformed(evt);
            }
        });

        jbtn_stop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/glisten/STOP.png"))); // NOI18N
        jbtn_stop.setMaximumSize(new java.awt.Dimension(32, 32));
        jbtn_stop.setMinimumSize(new java.awt.Dimension(32, 32));
        jbtn_stop.setPreferredSize(new java.awt.Dimension(32, 32));
        jbtn_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_stopActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLayeredPane1.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.setOpaque(true);

        jlbl_position.setForeground(new java.awt.Color(255, 255, 255));
        jlbl_position.setText(".");
        jlbl_position.setBounds(10, 30, 320, 14);
        jLayeredPane1.add(jlbl_position, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jlbl_duration.setForeground(new java.awt.Color(255, 255, 255));
        jlbl_duration.setText(".");
        jlbl_duration.setBounds(10, 50, 320, 14);
        jLayeredPane1.add(jlbl_duration, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jlbl_title.setForeground(new java.awt.Color(255, 255, 255));
        jlbl_title.setText(".");
        jlbl_title.setBounds(10, 10, 320, 14);
        jLayeredPane1.add(jlbl_title, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
        );

        jsld_seek.setToolTipText("Seek");
        jsld_seek.setValue(0);
        jsld_seek.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jsld_seek.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsld_seekStateChanged(evt);
            }
        });

        jsld_volume.setMinorTickSpacing(3);
        jsld_volume.setPaintTicks(true);
        jsld_volume.setToolTipText("Volume");
        jsld_volume.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsld_volumeStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jsld_seek, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jbtn_play, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtn_pause, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtn_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jsld_volume, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbtn_play, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbtn_pause, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbtn_stop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jsld_volume, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsld_seek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtn_playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_playActionPerformed
        // TODO add your handling code here:
        if(src != null){ src.play(); }
    }//GEN-LAST:event_jbtn_playActionPerformed

    private void jbtn_pauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_pauseActionPerformed
        // TODO add your handling code here:
        if(src != null){ src.pause(); }
    }//GEN-LAST:event_jbtn_pauseActionPerformed

    private void jbtn_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_stopActionPerformed
        // TODO add your handling code here:
        if(src != null){ src.stop();}
    }//GEN-LAST:event_jbtn_stopActionPerformed

    private void jsld_seekStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsld_seekStateChanged

}//GEN-LAST:event_jsld_seekStateChanged

    private void jsld_volumeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsld_volumeStateChanged
        // TODO add your handling code here:
        if( src != null && !jsld_volume.getValueIsAdjusting() ){
            src.setVolume(jsld_volume.getValue() / 100.0);
        }
    }//GEN-LAST:event_jsld_volumeStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtn_pause;
    private javax.swing.JButton jbtn_play;
    private javax.swing.JButton jbtn_stop;
    private javax.swing.JLabel jlbl_duration;
    private javax.swing.JLabel jlbl_position;
    private javax.swing.JLabel jlbl_title;
    private javax.swing.JSlider jsld_seek;
    private javax.swing.JSlider jsld_volume;
    // End of variables declaration//GEN-END:variables

}
