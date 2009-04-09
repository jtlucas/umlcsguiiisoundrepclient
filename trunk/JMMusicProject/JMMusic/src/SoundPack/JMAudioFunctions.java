/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SoundPack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 * @author James
 */
public class JMAudioFunctions {

      static int INTERFERENCE = 500;
      static int RANDOMBYTE = 5;

      public static void main(String[] arguments) {
      }

      public void saveFile(String s){
           try {
             File song = new File(s);
             FileInputStream file = new FileInputStream(song);
             File trashedSong = new File("C:\\trashed.mp3");
             FileOutputStream trash = new FileOutputStream(trashedSong);
             boolean eof = false;
//             int count = 0;
             System.out.println("Creating file ...");
             while (!eof) {
                 int input = file.read();
                 if (input == -1)
                     eof = true;
//                 else {
//                     count++;
//                     if (count % INTERFERENCE == 0) {
//                         int newInput = input + RANDOMBYTE;
//                         if (newInput > 255)
//                             newInput = newInput - 255;
//                         trash.write(newInput);
//                     }
                 else{
                         trash.write(input);
                 }
             }
             file.close();
             trash.close();
             System.out.println("Done");
         } catch (Exception e) {
             System.out.println("Error — " + e.toString());
         }
      }
}
