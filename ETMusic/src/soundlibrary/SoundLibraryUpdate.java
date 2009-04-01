/**
 * SoundLibraryUpdate.java
 */

package soundlibrary;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;

/**
 * This class will be used to execute an update on an existing entry. The update
 * will take in a SoundLibraryEntry (or equivalent data) and update the given
 * entry.
 * @author dan
 */
public class SoundLibraryUpdate {
    
    private String page_url = "http://teaching.cs.uml.edu/~daniel/sound_library/remote_update.jsp";
    
    private URL url_connection;
    private boolean response = false;
    private org.w3c.dom.Document reply;
    
    private String file_path="";
    private SoundLibraryEntry entry;
    
    public SoundLibraryUpdate(){
        entry = null;
    }
    
    public SoundLibraryUpdate( SoundLibraryEntry given_entry ){
        entry = given_entry;
    }
    
    public void setEntry( SoundLibraryEntry given_entry ){
         entry = given_entry;
    }
    
    public String getFilePath(){
        return( file_path );
    }
    
    public void setFilePath( String new_path ){
        file_path = new_path;
    }
    
    public boolean executeUpdate() throws Exception{
        
        if( entry == null ) throw new SoundLibraryException( "executeUpdate() called with no Entry!" );
        
        //Create a new ClientHTTPRequest
        ClientHttpRequest post = new ClientHttpRequest( page_url );

        //Set the parameters needed to update
        //First we add the sequence number
        post.setParameter( "Sequence", entry.getSequence() );
        //If there is a file, we add it
        if( !file_path.isEmpty() ){
            post.setParameter( "FileName", new File( file_path ) );
        }
        post.setParameter( "Title" , entry.getTitle() );
        post.setParameter( "Author" , entry.getAuthor() );
        post.setParameter( "Genre" , entry.getGenre() );
        post.setParameter( "Tags" , entry.getTags() );

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
    
    public boolean getResponse(){
        return( response );
    }
    
    public org.w3c.dom.Document getReply() throws Exception{
        if( reply == null ) throw new SoundLibraryException( "getReply() called before executeUpdate()." );
        
        return( reply );
    }
}
