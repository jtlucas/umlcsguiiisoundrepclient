package soundlibrary;

/**
 * This class is an extension of the Exception class. It is used to report
 * errors in Sound Library operations. All Sound Library API files will throw
 * SoundLibraryExceptions when an error is encountered during I/O.
 * @author dan
 */
public class SoundLibraryException extends Exception{
    //This is the error message
    private static String error;
    
    /**
     * This is the empty Constructor. It should be avoided at all costs. Only
     * use this as a last resort. "Unknown" errors are worthless and reveal
     * nothing about what went wrong. Please use the constructor below!
     */
    public SoundLibraryException(){
        super();
        error = "An unknown error has occured.";
    }
    
    /**
     * This the the prefered constructor for this Exception. It takes in a
     * string which is a description of what went wrong.
     * @param err: The given error string for what went wrong
     */
    public SoundLibraryException( String err ){
        super();
        error = err;
    }
    
    /**
     * This method is overridden to return the error message that the Exception
     * was created with.
     * @return the error message
     */
    public String getMessage(){
        return( error );
    }
}
