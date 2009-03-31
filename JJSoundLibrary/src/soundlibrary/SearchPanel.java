package soundlibrary;

import edu.uml.sl.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

/**
 *
 * @author dan
 */
public class SearchPanel extends JPanel{

    private JRadioButton titleButton;
    private JRadioButton authorButton;
    private JRadioButton genreButton;
    private JRadioButton tagsButton;
    private JRadioButton customButton;
    
    private JTextArea query_text;
    
    private SoundLibraryQuery query;
    
    private enum search_type{
        TITLE, AUTHOR, GENRE, TAGS, CUSTOM
    }
    private search_type current_search = search_type.CUSTOM;
    
    public SearchPanel(){
        super();
        setLayout( new BorderLayout() );
        
        JPanel radio_panel = new JPanel();
        titleButton = new JRadioButton( "Title" );
        authorButton = new JRadioButton( "Author" );
        genreButton = new JRadioButton( "Genre" );
        tagsButton = new JRadioButton( "Tags" );
        customButton = new JRadioButton( "Custom" );
        
        titleButton.addActionListener( new ActionListener(){
           public void actionPerformed( ActionEvent e ){
               current_search = search_type.TITLE;
           }
        });
        authorButton.addActionListener( new ActionListener(){
           public void actionPerformed( ActionEvent e ){
               current_search = search_type.AUTHOR;
           }
        });
        genreButton.addActionListener( new ActionListener(){
           public void actionPerformed( ActionEvent e ){
               current_search = search_type.GENRE;
           }
        });
        tagsButton.addActionListener( new ActionListener(){
           public void actionPerformed( ActionEvent e ){
               current_search = search_type.TAGS;
           }
        });
        customButton.addActionListener( new ActionListener(){
           public void actionPerformed( ActionEvent e ){
               current_search = search_type.CUSTOM;
           }
        });
        
        ButtonGroup button_group = new ButtonGroup();
        button_group.add( titleButton );
        button_group.add( authorButton );
        button_group.add( genreButton );
        button_group.add( tagsButton );
        button_group.add( customButton );
        radio_panel.add( titleButton );
        radio_panel.add( authorButton );
        radio_panel.add( genreButton );
        radio_panel.add( tagsButton );
        radio_panel.add( customButton );
        
        JPanel query_panel = new JPanel( new BorderLayout() );
        query_text = new JTextArea();
        query_panel.add( query_text, BorderLayout.CENTER );
        JButton search_button = new JButton( "Search" );
        search_button.addActionListener( new ActionListener(){
           public void actionPerformed( ActionEvent e ){
               try{
                   Vector<SoundLibraryEntry> results = new Vector<SoundLibraryEntry>();
                   query = new SoundLibraryQuery();
                   if( query_text.getText().isEmpty() ){
                       results = query.executeQuery();
                   }
                   else{
                       switch( current_search ){
                            case TITLE:
                                query.setQuerySearchByTitle( query_text.getText() );
                                break;
                            case AUTHOR:
                                query.setQuerySearchByAuthor( query_text.getText() );
                                break;
                            case GENRE:
                                query.setQuerySearchByGenre( query_text.getText() );
                                break;
                            case TAGS:
                                query.setQuerySearchByTags( query_text.getText() );
                                break;
                            case CUSTOM:
                                query.setQuery( query_text.getText() );
                                break;
                       }
                       results = query.executeQuery();
                       Main.Singleton.updateFromSearch( results );
                   }
               }
               catch( Exception exception ){
                   System.err.println( exception );
               }
           }
        });
        query_panel.add( search_button, BorderLayout.EAST );
        add( radio_panel, BorderLayout.NORTH );
        add( query_panel, BorderLayout.SOUTH );
        
        setVisible( true );
    }
}
