package soundlibrary;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;

/**
 * This class, much like the SoundLibraryQuery, acts as an intermediary to the
 * Server-Side MySQL access. Like with SoundLibraryQuery, there is a Java Server
 * Page on the other end which will process the request once it is sent.
 * This page will take in arguments of a file path and the appropriate
 * SoundLibrary data fields. It will then update the MySQL server and upload the
 * selected file from the local machine this class is runnign on.
 * @author dan
 */
public class SoundLibraryUpload {
    
    /**
     * Instead of hardcoding the URL within the code, I have put it here to make
     * moving to other servers etc. easier. Just point to the page you want!
     */
    private String page_url = "http://teaching.cs.uml.edu/~daniel/sound_library/remote_uploader.jsp";
    
    //These are the five nessecary private data members.
    private String file_path;
    private String title;
    private String author;
    private String genre;
    private String tags;
    
    private URL url_connection;
    private boolean response;
    private org.w3c.dom.Document reply;
    
    /**
     * This is the basic constructor. If this is called, the user must then
     * manually set all of the data members before executeUpdate() may be
     * called.
     */
    public SoundLibraryUpload(){}
    
    /**
     * This constructor allows for the specification of the five data members as
     * strings.
     * @param given_path
     * @param given_title
     * @param given_author
     * @param given_genre
     * @param given_tags
     */
    public SoundLibraryUpload( String given_path, String given_title, String given_author, String given_genre, String given_tags ){
        file_path = given_path;
        title     = given_title;
        author    = given_author;
        genre     = given_genre;
        tags      = given_tags;
    }
    
    /**
     * This constructor takes in two arguments. The first is the local file
     * path. The second is a SoundLibraryEntry. All of the members of the entry
     * are used except for url, which is not applicable and discarded.
     * @param given_path
     * @param entry
     */
    public SoundLibraryUpload( String given_path, SoundLibraryEntry entry ){
        file_path = given_path;
        title     = entry.getTitle();
        author    = entry.getAuthor();
        genre     = entry.getGenre();
        tags      = entry.getTags();
    }
 
    public String getFilePath(){
        return( file_path );
    }
    
    public String getTitle(){
        return( title );
    }
    
    public String getAuthor(){
        return( author );
    }
    
    public String getGenre(){
        return( genre );
    }
    
    public String getTags(){
        return( tags );
    }
    
    public boolean getResponse(){
        return( response );
    }
    
    public org.w3c.dom.Document getReply() throws Exception{
        if( reply == null ) throw new SoundLibraryException( "getReply() called before executeUpload()." );
        
        return( reply );
    }
    
    /**
     * This method allows the user to set all the data members of the update
     * except for file_path.
     * @param entry
     */
    public void setEntry( SoundLibraryEntry entry ){
        title     = entry.getTitle();
        author    = entry.getAuthor();
        genre     = entry.getGenre();
        tags      = entry.getTags();
    }
    
    public void setFilePath( String new_path ){
        file_path = new_path;
    }
    public void setTitle( String new_title ){
        title = new_title;
    }
    public void setAuthor( String new_author ){
        author = new_author;
    }
    public void setGenre( String new_genre ){
        genre = new_genre;
    }
    public void setTags( String new_tags ){
        tags = new_tags;
    }
    
    /**
     * This method will execute an update to the Sound Library. What this means
     * is that it will do two things. The first thing it will do is upload the
     * file described to the library folder. Next, it will execute an update to
     * the SQl database with the information given. Becuase all of the fields
     * must be filled out, it will throw an exception if any of them are left
     * blank.
     * 
     * Once it executes the update, it will read the response from the server
     * and will return a boolean value based on the response. A sucess will
     * return a true and a failure will return a false.
     * 
     * @return Success or Failure
     * @throws java.lang.Exception
     */
    public boolean executeUpload() throws Exception{
        
        //Create a new ClientHTTPRequest
        ClientHttpRequest post = new ClientHttpRequest( page_url );

        //Set the parameters needed to update
        post.setParameter( "FileName", new File( file_path ) );
        post.setParameter( "Title" , title );
        post.setParameter( "Author" , author );
        post.setParameter( "Genre" , genre );
        post.setParameter( "Tags" , tags );

        //Post the request and read in the response stream
        InputStream response_stream = post.post();
        
        //XML processing code modified from example at: http://java.sun.com/developer/technicalArticles/xml/JavaTechandXML/
        //Get a new DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        //Parse the xml returned by the server
        try{
            reply = builder.parse( response_stream );
        }
        catch( Exception e ){
            //Pass it on up!
            throw e;
        }
        //Get our list of errors. Hopefully 0 elements
        NodeList errors = reply.getElementsByTagName( "Error" );
        //NodeList warnings = reply.getElementsByTagName( "Warning" );
        //get our list of successes. Hopefully 1
        NodeList successes = reply.getElementsByTagName( "Success" );
        
        //If there were successes, return true
        if( successes.getLength() > 0 ) response = true;
        //otherwise, return false. I didn't use an if-else statement because
        //although there should never be a success AND a failure, I want to be
        //able to handle it just in case.
        if( errors.getLength() > 0 ) response = false;
        
        //Finally, return the response
        return( response );
    }
}
