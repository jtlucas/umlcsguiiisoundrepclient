/*
 * File: GUIUtilities.java
 * Project Location: /public_html/91.462/91.462-2007-08s/462-lecs/code/XMLDemo
 * Copyright (c) 2008 by Jesse M. Heines.  All rights reserved.  May be freely 
 *   copied or excerpted for educational purposes with credit to the author.
 * created by JMH on February 28, 2008 at 11:07 AM
 * updated by JMH on March 8, 2009 at 11:02 PM
 */
package edu.uml.cs.GUIProgramming.heines;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * This class contains a collection of methods used to manage a program's
 * graphical user interface.
 * @author Jesse Heines, UMass Lowell Computer Science
 * @author <a href="mailto:heines@cs.uml.edu">heines@cs.uml.edu</a>
 * @version 1.2, 2008-03-08, March 8, 2008
 */
public class GUIUtilities {

  /** properties read from properties file */
  private static Properties props = null;

  /**
   * Set the application look-and-feel to match NetBeans.
   * @return true if look-and-feel is set, false otherwise
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

  /**
   * Determine the default system directory for different OS platforms.
   * @return "user.home" for Linux systems and "C:/" for Windows systems
   */
  public static String getDefaultDirectory() {
    if ( System.getProperty( "os.name" ).equalsIgnoreCase( "linux" ) ) {
      return System.getProperty( "user.home" );
    } else {
      return "C:/";
    }
  }

  /**
   * Construct the full file reference for the properties file to be loaded or saved.
   * @param strPropertiesFilePath path to properties file, default = "{user.home}/Application Data/UMassLowellCS"
   * @param strPropertiesFileName name of properties file, default = "CompositionProject.properties" ;
   * @return String to pass to the FileInputStream or FileOutputStream constructor
   */
  static String constructPropertiesFileString( String strPropertiesFilePath,
      String strPropertiesFileName ) {

    // if no path is defined for the properties file, set it to the
    //    UMassLowellCS directory
    if ( strPropertiesFilePath == null ) {
      strPropertiesFilePath = System.getProperty( "user.home" ) +
          System.getProperty( "file.separator" ) + "Application Data" +
          System.getProperty( "file.separator" ) + "UMassLowellCS";
    }

    // if no name is defined for the properties file, set it to a generic name
    // if a file name is defined but it does not have an extension of .properties,
    //    append that extension
    if ( strPropertiesFileName == null ) {
      strPropertiesFileName = "CompositionProject.properties";
    } else if ( !strPropertiesFileName.toLowerCase().endsWith( ".properties" ) ) {
      strPropertiesFileName += ".properties";
    }

    // return the full constructed String to pass to the FileInputStream or
    //    FileOutputStream constructor
    return strPropertiesFilePath + System.getProperty( "file.separator" ) + strPropertiesFileName;
  }

  /**
   * Attempt to open the default properties file and load its contents.
   * @return true if properties file is opened and loaded successfully, false otherwise
   */
  public static boolean loadProperties() {
    return loadProperties( null, null );
  }

  /**
   * Attempt to open a properties file and load its contents.
   * @param strPropertiesFilePath path to properties file, default = "{user.home}/Application Data/UMassLowellCS"
   * @param strPropertiesFileName name of properties file, default = "CompositionProject.properties" ;
   * @return true if properties file is opened and loaded successfully, false otherwise
   */
  public static boolean loadProperties( String strPropertiesFilePath,
      String strPropertiesFileName ) {

    // String to pass to the FileInputStream constructor
    String strPropertiesFile =
        constructPropertiesFileString( strPropertiesFilePath, strPropertiesFileName );

    // create Properties object if it does not exist
    if ( props == null ) {
      props = new Properties();
    }

    try {
      // load properties from file
      FileInputStream in = new FileInputStream( strPropertiesFile );
      props.load( in );
      in.close();
      return true;
    } catch ( IOException ioe ) {
      // System.out.println(ioe.getMessage());
      return false;
    }
  }

  /**
   * Attempt to save the properties file.
   */
  public static void saveProperties() {
    saveProperties( null, null, null );
  }

  /**
   * Attempt to save the properties file.
   * @param strPropertiesFilePath path to properties file, default = "user.home/Application Data/UMassLowellCS"
   * @param strPropertiesFileName name of properties file, default = "GUIProgramming.properties" ;
   * @param strPropertiesTitle title to write into properties file, default = "GUI Programming"
   */
  public static void saveProperties( String strPropertiesFilePath,
      String strPropertiesFileName, String strPropertiesTitle ) {

    // String to pass to the FileInputStream constructor
    String strPropertiesFile =
        constructPropertiesFileString( strPropertiesFilePath, strPropertiesFileName );

    // set default properties title
    if ( strPropertiesTitle == null ) {
      strPropertiesTitle = "GUI Programming";
    }

    // isolate the properties file path
    if ( strPropertiesFilePath == null ) {
      strPropertiesFilePath =
          strPropertiesFile.substring( 0, strPropertiesFile.lastIndexOf( System.getProperty( "file.separator" ) ) );
    }

    // if the properties file path does not exist, create it
    File filePropertiesFilePath = new File( strPropertiesFilePath );
    if ( !filePropertiesFilePath.exists() ) {
      filePropertiesFilePath.mkdirs();
    }

    // save properties file
    // reference: Deitel & Deitel, Java How To Program, 6th ed., p. 948
    try {
      File file = new File( strPropertiesFile );
      FileOutputStream out = new FileOutputStream( file );
      props.store( out, strPropertiesTitle );
      out.close();
    } catch ( FileNotFoundException fnfe ) {
      System.err.println( fnfe );
    } catch ( IOException ioe ) {
      System.err.println( ioe );
    }
  }

  /**
   * This method saves the main window location, size, and state to the
   * properties file.
   * @param jfrm the JFrame to be set
   * @param strPropertiesFilePath path to properties file, default = "user.home/Application Data/UMassLowellCS"
   * @param strPropertiesFileName name of properties file, default = "GUIProgramming.properties" ;
   */
  public static void saveMainWindowLocation( JFrame jfrm,
      String strPropertiesFilePath, String strPropertiesFileName ) {
    saveMainWindowLocation( jfrm, strPropertiesFilePath, strPropertiesFileName, null );
  }

  /** 
   * This method saves the main window location, size, and state to the 
   * properties file.
   * @param jfrm the JFrame whose properties are to be saved
   * @param strPropertiesFilePath path to properties file, default = "user.home/Application Data/UMassLowellCS"
   * @param strPropertiesFileName name of properties file, default = "GUIProgramming.properties" ;
   * @param strPropertiesTitle title to write into properties file, default = "GUI Programming"
   */
  public static void saveMainWindowLocation( JFrame jfrm,
      String strPropertiesFilePath, String strPropertiesFileName,
      String strPropertiesTitle ) {

    if ( jfrm != null && props != null ) {
      props.setProperty( "WindowX", "" + jfrm.getX() );
      props.setProperty( "WindowY", "" + jfrm.getY() );
      props.setProperty( "WindowWidth", "" + jfrm.getWidth() );
      props.setProperty( "WindowHeight", "" + jfrm.getHeight() );
      props.setProperty( "WindowState", "" + jfrm.getState() );
      saveProperties( strPropertiesFilePath, strPropertiesFileName,
          strPropertiesTitle );
    } else {
      return;
    }
  }

  /**
   * Set the location of a JFrame using the values in a specified properties file 
   * at a specified location.
   * @param jfrm the JFrame whose properties are to be set
   * @param strPropertiesFilePath path to properties file, default = "user.home/Application Data/UMassLowellCS"
   * @param strPropertiesFileName name of properties file, default = "GUIProgramming.properties" ;
   */
  public static void setMainWindowLocation( JFrame jfrm,
      String strPropertiesFilePath, String strPropertiesFileName ) {
    if ( props == null ) {
      props = new Properties();
      loadProperties( strPropertiesFilePath, strPropertiesFileName );
    }
    setMainWindowLocation( jfrm );
  }

  /**
   * This method reads the default properties file (if it exists) and
   * sets the main window location to what it was the last time the
   * program was run.
   * @param jfrm the JFrame whose properties are to be set
   */
  public static void setMainWindowLocation( JFrame jfrm ) {
    int intWindowX = jfrm.getX();
    int intWindowY = jfrm.getY();
    int intWindowWidth = jfrm.getWidth();
    int intWindowHeight = jfrm.getHeight();
    int intWindowState = jfrm.getExtendedState();

    if ( props == null ) {
      props = new Properties();
    } else {
      if ( props.getProperty( "WindowX" ) != null ) {
        intWindowX = Integer.parseInt( props.getProperty( "WindowX" ) );
      }
      if ( props.getProperty( "WindowY" ) != null ) {
        intWindowY = Integer.parseInt( props.getProperty( "WindowY" ) );
      }
      if ( props.getProperty( "WindowWidth" ) != null ) {
        intWindowWidth = Integer.parseInt( props.getProperty( "WindowWidth" ) );
      }
      if ( props.getProperty( "WindowHeight" ) != null ) {
        intWindowHeight = Integer.parseInt( props.getProperty( "WindowHeight" ) );
      }
      if ( props.getProperty( "WindowState" ) != null ) {
        intWindowState = Integer.parseInt( props.getProperty( "WindowState" ) );
      }
    }

    // set window location
    jfrm.setLocation( intWindowX, intWindowY );

    // set window size
    // maximize window if it was maximized when it was last closed
    // note that getProperty returns a String but MAXIMIZED_BOTH is an int
    //    therefore, the comparison is done with strings
    if ( intWindowState == JFrame.MAXIMIZED_BOTH ) {
      jfrm.setExtendedState( JFrame.MAXIMIZED_BOTH );
    } else {  // set stored window size if it was not maximized
      jfrm.setSize( intWindowWidth, intWindowHeight );
    }
  }
}
