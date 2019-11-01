/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author png99
 */
public class ChooseFile extends FileFilter{

    private final String extension;
    private final String description;
    public ChooseFile(String extension, String description) {
        this.description = description;
        this.extension = extension;
    }

    /*public ChooseFile(String currentDirectoryPath) {
        super(currentDirectoryPath);
    }*/

    @Override
    public boolean accept(File f) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(f.isDirectory()){
            return true;
        }
        return f.getName().endsWith(extension);
    }

    @Override
    public String getDescription() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return description + String.format(" (%s)", extension);
    }
    
}
