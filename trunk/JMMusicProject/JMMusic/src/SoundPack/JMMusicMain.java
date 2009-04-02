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

import edu.uml.sl.*;
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
        stream_button = new JButton( "Stream" );
        stream_button.addActionListener( new ActionListener(){
           public void actionPerformed( ActionEvent e ){
               stream_button.setEnabled( false );
               stop_button.setEnabled( true );               
               try{
                   URL stream_url = stored_results.elementAt( dataTable.getSelectedRow() ).getURL();

                   if( stream_thread == null ){
                       stream_thread = new StreamThread( stream_url );
                   }
                   else{
                       stream_thread.setStream( stream_url );
                   }
                   updateStatusText( "Streaming: " + tableModel.getValueAt( dataTable.getSelectedRow(), 0 ) );
//                   stream_thread.run();
                   Thread player_thread = new java.lang.Thread(stream_thread);
                   player_thread.start();
               }
               catch( Exception exception ){
                   System.err.println( exception );
               }
           }
        });

        stop_button = new JButton( "Stop" );
        stop_button.addActionListener( new ActionListener(){
           public void actionPerformed( ActionEvent e ){
               stream_button.setEnabled( true );
               stop_button.setEnabled( false );
               try{
                    if( ( stream_thread != null ) &&
                         (stream_thread.isAlive() ) ){
                        stream_thread.requestStop();
                    }
                    updateStatusText( "Stopped." );
                    stopAudio();
               }
               catch( Exception exception ){
                   System.err.println( exception );
               }
           }
        });
//        stop_button.setEnabled( false );

        reload = new JButton("Reload");
        reload.addActionListener( new ActionListener(){
           public void actionPerformed( ActionEvent e ){
               updateList();
           }
        });

        //Create the status box and add it to the main panel
//        progress_text = new JLabel();
        progress_text.setText( "Stopped." );
        JPanel containPanel = new JPanel();
        stop_button.setEnabled(false);
        containPanel.setLayout(new FlowLayout());
        containPanel.add(stream_button);
        containPanel.add(stop_button);
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
    stop_button = new javax.swing.JButton();
    reload = new javax.swing.JButton();
    progress_text = new javax.swing.JLabel();

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

    stop_button.setText("Stop");

    reload.setText("Reload");

    progress_text.setText("jLabel1");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(stream_button)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(stop_button)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(reload)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(progress_text))
          .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(52, Short.MAX_VALUE))
    );

    layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {reload, stop_button, stream_button});

    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(main_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(stream_button)
          .addComponent(stop_button)
          .addComponent(reload)
          .addComponent(progress_text))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

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
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTabbedPane main_panel;
  private javax.swing.JLabel progress_text;
  private javax.swing.JButton reload;
  private javax.swing.JButton stop_button;
  private javax.swing.JButton stream_button;
  // End of variables declaration//GEN-END:variables

}
