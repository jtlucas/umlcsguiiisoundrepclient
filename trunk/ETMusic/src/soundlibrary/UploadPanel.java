package soundlibrary;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

/**
 * Based off of demo from: http://java.sun.com/docs/books/tutorial/uiswing/examples/components/FileChooserDemoProject/src/components/FileChooserDemo.java
 * @author dan
 */
public class UploadPanel extends JPanel implements ActionListener{

    private JTextArea title;
    private JTextArea author;
    private JTextArea genre;
    private JTextArea tags;
    
    private JTextArea file_path;
    private JFileChooser fc;
    private JButton file_button;
    
    private JButton upload_button;
    
    public UploadPanel(){
        super();
        setLayout( new GridLayout( 6, 2 ) );
        
        add( new JLabel( "File:" ) );
        file_path = new JTextArea();
        add( file_path );
        
        add( new JLabel( "Title:" ) );
        title = new JTextArea();
        add( title );
        
        add( new JLabel( "Author" ) );
        author = new JTextArea();
        add( author );
        
        add( new JLabel( "Genre" ) );
        genre = new JTextArea();
        add( genre );
        
        add( new JLabel( "Tags:" ) );
        tags = new JTextArea();
        add( tags );
        
        upload_button = new JButton( "Upload!" );
        upload_button.addActionListener( this );
        add( upload_button );
        
        file_button = new JButton( "Choose File" );
        file_button.addActionListener( this );
        add( file_button );
        
        fc = new JFileChooser();
        fc.addChoosableFileFilter( new SoundFilter() ); //Only accept sound files
        
        add( file_button, BorderLayout.CENTER );
        setVisible( true );
    }

    public void actionPerformed(ActionEvent e ) {
        
        if( e.getSource() == file_button ) {
            int returnVal = fc.showOpenDialog( UploadPanel.this );

            if ( returnVal == JFileChooser.APPROVE_OPTION ) {
                file_path.setText( fc.getSelectedFile().getPath() );
            } 
        }
        if( e.getSource() == upload_button ){
            SoundLibraryUpload update = new SoundLibraryUpload();
            update.setFilePath( file_path.getText() );
            update.setTitle( title.getText() );
            update.setAuthor( author.getText() );
            update.setGenre( genre.getText() );
            update.setTags( tags.getText() );
            //Now to send the update
            try{
                update.executeUpload();
            }
            catch( Exception exception ){
                System.err.println( exception.getMessage() );
            }
        }
    }
}