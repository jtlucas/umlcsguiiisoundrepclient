/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JMMusicMain.java
 *
 * Created on Apr 1, 2009, 12:22:25 AM
 */

package SoundPack;

import edu.uml.cs.sl.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author James
 */
public class JMMusicMain extends javax.swing.JFrame implements WindowListener {

//    private JTabbedPane main_panel;

//    private JLabel progress_text;
//    private JButton stream_button;
//    private JButton stop_button;
//    private JButton reload;

    private StreamThread stream_thread = null;
//    private Thread player_thread = null;

//    private JTable dataTable;
    private DefaultTableModel tableModel;
    private Vector<String> columnNames;

    private Vector<SoundLibraryEntry> stored_results;

    public static final JMMusicMain Singleton = new JMMusicMain();

    /** Creates new form JMMusicMain */
    public JMMusicMain() {
        super( "James' Sound Library Client" );
        SetNetBeansCompatibleUIManager();
        initComponents();
        

        //Initialize the JFrame and basic settings
        setSize( 640, 480 );
        Dimension d = new Dimension(660, 450);
        Dimension d2 = new Dimension(800,600);
        setMinimumSize(d);
        setMaximumSize(d2);
        setVisible( true );

        //Create the GUI
        main_panel = new JTabbedPane();

        JPanel result_panel = new JPanel( new BorderLayout() );

        JPanel list_panel = new JPanel();

        columnNames = new Vector<String>();
        columnNames.add( "Title" );
        columnNames.add( "Artist" );
        columnNames.add( "Genre" );
        columnNames.add( "Tags" );
        columnNames.add( "Mime Type" );

        Vector<Vector<String>> entries = new Vector<Vector<String>>();
        tableModel = new DefaultTableModel( entries, columnNames );
        dataTable = new JTable( tableModel );
        JScrollPane scrollPane = new JScrollPane( dataTable );
        scrollPane.setPreferredSize( new Dimension( 640, 350 ) );
        list_panel.add( scrollPane );

        result_panel.add( list_panel, BorderLayout.CENTER );

        JPanel control_panel = new JPanel( new BorderLayout() );

        //Create the Stream and stop buttons
//        stream_button = new JButton( "Play" );
        stream_button.addActionListener( new ActionListener(){
           public void actionPerformed( ActionEvent e ){
//               stream_button.setEnabled( false );
//               stop_button.setEnabled( true );
               try{
                   URL stream_url = stored_results.elementAt( dataTable.getSelectedRow() ).getURL();
//                   Thread player_thread = new java.lang.Thread(stream_thread);


                   if( stream_thread != null ){
                       stream_thread.requestStop();}
////                       stream_thread.setStream( stream_url );
//                   }
//                   else{
                       stream_thread = new StreamThread( stream_url );
//                   }
                   
                   updateStatusText( "Streaming: " + tableModel.getValueAt( dataTable.getSelectedRow(), 0 ) );

                   stream_thread.start();
//                   player_thread.start();
               }
               catch( Exception exception ){
                   System.err.println( exception );
               }
           }
        });

//        stop_button = new JButton( "Stop" );
//        stop_button.addActionListener( new ActionListener(){
//           public void actionPerformed( ActionEvent e ){
//               stream_button.setEnabled( true );
//               stop_button.setEnabled( false );
//               try{
//                    if( ( stream_thread != null ) &&
//                         (stream_thread.isAlive() ) ){
//                        stream_thread.requestStop();
//                    }
//                    updateStatusText( "Stopped." );
//                    stopAudio();
//               }
//               catch( Exception exception ){
//                   System.err.println( exception );
//               }
//           }
//        });
//        stop_button.setEnabled( false );

//        reload = new JButton("Refresh List");
        reload.addActionListener( new ActionListener(){
           public void actionPerformed( ActionEvent e ){
               updateList();
           }
        });

        //Create the status box and add it to the main panel
//        progress_text = new JLabel();
        progress_text.setText( "Stopped." );
        JPanel containPanel = new JPanel();
//        stop_button.setEnabled(false);
        containPanel.setLayout(new FlowLayout());
        containPanel.add(stream_button);
//        containPanel.add(stop_button);
        containPanel.add(reload);
        control_panel.add(containPanel, BorderLayout.WEST);
        control_panel.add(progress_text, BorderLayout.CENTER);
        result_panel.add(control_panel, BorderLayout.SOUTH);

//        control_panel.add( stream_button, BorderLayout.WEST );
//        control_panel.add( progress_text, BorderLayout.CENTER );
//        control_panel.add( stop_button, BorderLayout.EAST );
//        control_panel.add(reload, BorderLayout.EAST);

//        result_panel.add( control_panel, BorderLayout.SOUTH );

        main_panel.add( "Songs", result_panel );

        JMSearchPanel search_panel = new JMSearchPanel();
        main_panel.add( "Search", search_panel );

        JMUploadPanel upload_panel = new JMUploadPanel();
        main_panel.add( "Add", upload_panel );

        //Set the main panel to be the content pane of the main frame
        setContentPane( main_panel );

        //Make this app its own WindowListener
        addWindowListener( this );

        updateList();
        //Pull entries from MySQL Database
//        try{
//            //SoundLibraryQuery query = new SoundLibraryQuery( "SELECT * FROM library WHERE MATCH (Tags) AGAINST ('plop' IN BOOLEAN MODE)" );
//            SoundLibraryQuery query = new SoundLibraryQuery();
//            Vector<SoundLibraryEntry> results = query.executeQuery();
//            stored_results = results;
//            //for( SoundLibraryEntry entry: results ){
//            //    System.out.println( entry.getURL().toURI() + " " + entry.getTitle() + " " + entry.getAuthor() + " " + entry.getGenre() + " " + entry.getTags() );
//            //}
//            for( SoundLibraryEntry entry: results ){
//                Vector<String> new_row = new Vector<String>();
//                new_row.add( entry.getTitle() );
//                new_row.add( entry.getAuthor() );
//                new_row.add( entry.getGenre() );
//                new_row.add( entry.getTags() );
//                new_row.add( entry.getMimeType() );
//
//                tableModel.addRow( new_row );
//            }
//        }
//        catch( Exception ex ){
//           System.err.println( ex );
//        }
        //Finally, display all contents
        //paintAll( getGraphics() );
        pack();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    main_panel = new javax.swing.JTabbedPane();
    jScrollPane1 = new javax.swing.JScrollPane();
    dataTable = new javax.swing.JTable();
    stream_button = new javax.swing.JButton();
    reload = new javax.swing.JButton();
    progress_text = new javax.swing.JLabel();
    jmbMenu = new javax.swing.JMenuBar();
    jmFile = new javax.swing.JMenu();
    jmQuit = new javax.swing.JMenuItem();
    jmEdit = new javax.swing.JMenu();
    jmSearch = new javax.swing.JMenu();
    jmiTitle = new javax.swing.JMenuItem();
    jmiAuthor = new javax.swing.JMenuItem();
    jmiGenre = new javax.swing.JMenuItem();
    jmiTags = new javax.swing.JMenuItem();
    jmiCustom = new javax.swing.JMenuItem();
    jmUpload = new javax.swing.JMenu();
    jMenuItem1 = new javax.swing.JMenuItem();
    jMenuItem2 = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    dataTable.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null}
      },
      new String [] {
        "Title 1", "Title 2", "Title 3", "Title 4"
      }
    ));
    jScrollPane1.setViewportView(dataTable);

    main_panel.addTab("tab1", jScrollPane1);

    stream_button.setText("Play");
    stream_button.setPreferredSize(new java.awt.Dimension(89, 23));

    reload.setText("Refresh List");

    progress_text.setText("jLabel1");

    jmFile.setText("File");

    jmQuit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
    jmQuit.setText("Quit");
    jmQuit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jmQuitActionPerformed(evt);
      }
    });
    jmFile.add(jmQuit);

    jmbMenu.add(jmFile);

    jmEdit.setText("Edit");
    jmbMenu.add(jmEdit);

    jmSearch.setText("Search");

    jmiTitle.setText("Title");
    jmSearch.add(jmiTitle);

    jmiAuthor.setText("Artist");
    jmSearch.add(jmiAuthor);

    jmiGenre.setText("Genre");
    jmSearch.add(jmiGenre);

    jmiTags.setText("Tags");
    jmSearch.add(jmiTags);

    jmiCustom.setText("Custom");
    jmSearch.add(jmiCustom);

    jmbMenu.add(jmSearch);

    jmUpload.setText("Upload");

    jMenuItem1.setText("Choose");
    jmUpload.add(jMenuItem1);

    jMenuItem2.setText("Upload");
    jmUpload.add(jMenuItem2);

    jmbMenu.add(jmUpload);

    setJMenuBar(jmbMenu);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(stream_button, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(reload)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(progress_text)
            .addGap(89, 89, 89))
          .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(42, Short.MAX_VALUE))
    );

    layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {reload, stream_button});

    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(stream_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(reload)
          .addComponent(progress_text))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void jmQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmQuitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jmQuitActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {}
//        JMMusicMain Singleton = new JMMusicMain();
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JMMusicMain().setVisible(true);
//            }
//        });
//    }

        public void updateStatusText( String new_text ){
            progress_text.setText( new_text );
            repaint();
        }

        public void updateList(){
            try{
                tableModel.setRowCount(0);
                //SoundLibraryQuery query = new SoundLibraryQuery( "SELECT * FROM library WHERE MATCH (Tags) AGAINST ('plop' IN BOOLEAN MODE)" );
                SoundLibraryQuery query = new SoundLibraryQuery();
                Vector<SoundLibraryEntry> results = query.executeQuery();
                stored_results = results;
                //for( SoundLibraryEntry entry: results ){
                //   System.out.println( entry.getURL().toURI() + " " + entry.getTitle() + " " + entry.getAuthor() + " " + entry.getGenre() + " " + entry.getTags() );
                //}
                for( SoundLibraryEntry entry: results ){
                    Vector<String> new_row = new Vector<String>();
                    new_row.add( entry.getTitle() );
                    new_row.add( entry.getAuthor() );
                    new_row.add( entry.getGenre() );
                    new_row.add( entry.getTags() );
                    new_row.add( entry.getMimeType() );
                    tableModel.addRow( new_row );
                }
            }
            catch( Exception ex ){
                System.err.println( ex );
            }
        }

    public void stopAudio(){
        stream_thread.requestStop();
    }

    public void updateFromSearch( Vector<SoundLibraryEntry> results ){
        try{
            tableModel.setRowCount( 0 );
            for( SoundLibraryEntry entry: results ){
                    Vector<String> new_row = new Vector<String>();
                    new_row.add( entry.getTitle() );
                    new_row.add( entry.getAuthor() );
                    new_row.add( entry.getGenre() );
                    new_row.add( entry.getTags() );
                    new_row.add( entry.getMimeType() );

                    tableModel.addRow( new_row );
            }
            stored_results = results;
        }
        catch( Exception exception ){
            System.err.println( exception );
        }
        main_panel.setSelectedIndex( 0 );
    }

  /**
   * Set the application look-and-feel to match NetBeans.
   * @return true if look-and-feel is set, false otherwise
   * via Jesse Heines
   */
  public static boolean SetNetBeansCompatibleUIManager() {
    // set the LookAndFeel to match the IDE
    // reference: Robinson & Vorobiev p. 728
    UIManager.LookAndFeelInfo[] info =
        UIManager.getInstalledLookAndFeels();
    // default installed LookAndFeels
    // updated for Java 1.6.0_11 2009-01-08
    // 0: javax.swing.plaf.metal.MetalLookAndFeel (default)
    // 1: com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel
    // 2: com.sun.java.swing.plaf.motif.MotifLookAndFeel
    // 3: com.sun.java.swing.plaf.windows.WindowsLookAndFeel
    // 4: com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
    int lafno = 3;   // desired LookAndFeel for Windows XP to match NetBeans
    try {
      UIManager.setLookAndFeel( info[lafno].getClassName() );
    } catch ( Exception e ) {
      System.err.println( "Couldn't load " + info[lafno].getClassName() +
          " look and feel " + e );
      return false;
    }
    return true;
  }

    public void windowOpened(WindowEvent arg0) {}

    public void windowClosing(WindowEvent arg0) {
        if(stream_thread != null ){stopAudio();}
        if( isDisplayable() ) dispose();
        System.exit(0);
    }

    public void windowClosed(WindowEvent arg0) {}

    public void windowIconified(WindowEvent arg0) {}

    public void windowDeiconified(WindowEvent arg0) {}

    public void windowActivated(WindowEvent arg0) {}

    public void windowDeactivated(WindowEvent arg0) {}

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTable dataTable;
  private javax.swing.JMenuItem jMenuItem1;
  private javax.swing.JMenuItem jMenuItem2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JMenu jmEdit;
  private javax.swing.JMenu jmFile;
  private javax.swing.JMenuItem jmQuit;
  private javax.swing.JMenu jmSearch;
  private javax.swing.JMenu jmUpload;
  private javax.swing.JMenuBar jmbMenu;
  private javax.swing.JMenuItem jmiAuthor;
  private javax.swing.JMenuItem jmiCustom;
  private javax.swing.JMenuItem jmiGenre;
  private javax.swing.JMenuItem jmiTags;
  private javax.swing.JMenuItem jmiTitle;
  private javax.swing.JTabbedPane main_panel;
  private javax.swing.JLabel progress_text;
  private javax.swing.JButton reload;
  private javax.swing.JButton stream_button;
  // End of variables declaration//GEN-END:variables

}
