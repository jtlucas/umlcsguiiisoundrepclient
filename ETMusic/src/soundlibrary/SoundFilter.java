package soundlibrary;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;


/**
 * Taken from example at: http://java.sun.com/docs/books/tutorial/uiswing/examples/components/FileChooserDemo2Project/src/components/ImageFilter.java
 * @author dan
 */
public class SoundFilter extends FileFilter{
 //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        String extension = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 &&  i < s.length() - 1) {
            extension = s.substring(i+1).toLowerCase();
        }
        
        if (extension != null) {
            if (extension.equals( "aiff" ) ||
                extension.equals( "wav" ) ||
                extension.equals( "mp3" )) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return "Only sound files";
    }
}
