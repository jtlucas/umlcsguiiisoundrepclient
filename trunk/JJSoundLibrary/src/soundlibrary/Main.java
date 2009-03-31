package soundlibrary;

import edu.uml.sl.*;

import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * This class is a proof of concept for an eventual sound library API. The
 * application asks for a URL and will attempt to connect to the server
 * specified and stream the designated sound file from the server.
 * @author dan
 */
public class Main extends JFrame implements WindowListener{
    
    private JTabbedPane main_panel;
    
    private JLabel progress_text;
    private JButton stream_button;
    //private JButton stop_button;
    
    private StreamThread stream_thread = null;
    
    private JTable dataTable;
    private DefaultTableModel tableModel;
    private Vector<String> columnNames;

    //added by Jerron, seperate Thread for playing music.
    private Thread player_thread;
    
    private Vector<SoundLibraryEntry> stored_results;
    
    public static final Main Singleton = new Main();
    
    private Main(){
        //Initialize the JFrame and basic settings
        super( "Sound Library Client" );
        setSize( 640, 480 );
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
               //stream_button.setEnabled( false );
               //stop_button.setEnabled( true );
               try{
                   URL stream_url = stored_results.elementAt( dataTable.getSelectedRow() ).getURL();

                   //Daniel's code, left in
//                   if( stream_thread == null ){
//                       stream_thread = new StreamThread( stream_url );
//                   }
//                   else{
//                       stream_thread.setStream( stream_url );
//                   }
//                   updateStatusText( "Streaming: " + tableModel.getValueAt( dataTable.getSelectedRow(), 0 ) );
                    //playing
                    if(stream_thread == null){
                        stream_thread = new StreamThread( stream_url );
                        player_thread = new java.lang.Thread(stream_thread);
                        player_thread.start();
                        
                        stream_thread = null;
                    }
                   else{
                       stream_thread.setStream( stream_url );
                   }
                   updateStatusText( "Streaming: " + tableModel.getValueAt( dataTable.getSelectedRow(), 0 ) );

                   
                   //stream_thread.run();




               }
               catch( Exception exception ){
                   System.err.println( exception );
               }
           } 
        });
        /*stop_button = new JButton( "Stop" );
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
               }
               catch( Exception exception ){
                   System.err.println( exception );
               }
           } 
        });
        stop_button.setEnabled( false );*/
        
        //Create the status box and add it to the main panel
        progress_text = new JLabel();
        progress_text.setText( "Stopped." );
        
        control_panel.add( stream_button, BorderLayout.WEST );
        control_panel.add( progress_text, BorderLayout.CENTER );
        //control_panel.add( stop_button, BorderLayout.EAST );

        result_panel.add( control_panel, BorderLayout.SOUTH );
        
        main_panel.add( "Stream Index", result_panel );
        
        SearchPanel search_panel = new SearchPanel();
        main_panel.add( "Search", search_panel );
        
        UploadPanel upload_panel = new UploadPanel();
        main_panel.add( "Upload", upload_panel );
        
        //Set the main panel to be the content pane of the main frame
        setContentPane( main_panel );
        
        //Make this app its own WindowListener
        addWindowListener( this );
        
        //Pull entries from MySQL Database
        try{
            //SoundLibraryQuery query = new SoundLibraryQuery( "SELECT * FROM library WHERE MATCH (Tags) AGAINST ('plop' IN BOOLEAN MODE)" );
            SoundLibraryQuery query = new SoundLibraryQuery();
            Vector<SoundLibraryEntry> results = query.executeQuery();
            stored_results = results;
            //for( SoundLibraryEntry entry: results ){
            //    System.out.println( entry.getURL().toURI() + " " + entry.getTitle() + " " + entry.getAuthor() + " " + entry.getGenre() + " " + entry.getTags() );
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
        //Finally, display all contents
        //paintAll( getGraphics() );
        pack();
    }
    
    /**
     * This method will update the status message text at the bottom of the
     * program's window. It simply sets the text of the JLabel to whatever the
     * parameter new_text is and then calls the repaint() method to update the
     * display.
     * @param new_text
     */
    public void updateStatusText( String new_text ){
        
        progress_text.setText( new_text );
        repaint();
    }
    
    public void stopAudio(){
        if( stream_thread != null ) stream_thread.requestStop();
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
    
    public static void main(String[] args) {}

    public void windowOpened(WindowEvent arg0) {}

    public void windowClosing(WindowEvent arg0) {
        stopAudio();
        if( isDisplayable() ) dispose();
    }

    public void windowClosed(WindowEvent arg0) {}

    public void windowIconified(WindowEvent arg0) {}

    public void windowDeiconified(WindowEvent arg0) {}

    public void windowActivated(WindowEvent arg0) {}

    public void windowDeactivated(WindowEvent arg0) {}
}
