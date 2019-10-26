/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import javax.swing.JFileChooser;

/**
 *
 * @author png99
 */
public class ChooseFile extends JFileChooser{

    public ChooseFile() {
    }

    public ChooseFile(String currentDirectoryPath) {
        super(currentDirectoryPath);
    }
    
}
