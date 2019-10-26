//package Extra;
//
//
//import User.MenuView;
//import User.PersonalChatView;
//import java.awt.Component;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.sql.Connection;
//import javax.swing.DefaultCellEditor;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JCheckBox;
//import javax.swing.JOptionPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.plaf.IconUIResource;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// * @author Asus
// */
//public class ButtonEditor extends DefaultCellEditor{
//    protected JButton button;
//    private String label;
//    private boolean isPushed;
//   // private PersonalChatView personchatview = new PersonalChatView();
//    private ImageIcon icon;
//    public ButtonEditor(JCheckBox checkbox) {
//        super(checkbox);
//        button = new JButton();
//        icon = new ImageIcon("C:\\Users\\CongNguyen\\Documents\\NetBeansProjects\\New folder\\LTMang-project\\Chat-App\\src\\img\\download1.png");
//        button.setIcon(icon);
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                fireEditingStopped(); //To change body of generated methods, choose Tools | Templates.
//                
//                personchatview.setVisible(true);
//            }
//        });
//    }
//    @Override
//    public Component getTableCellEditorComponent(JTable table,Object value, boolean isSelected, int row, int column){
//        if (isSelected) {
//            button.setForeground(table.getSelectionForeground());
//            button.setBackground(table.getSelectionBackground());
//            
//          
//        } else {
//            button.setForeground(table.getForeground());
//            button.setBackground(table.getBackground());
//            
//        }
//        label = (value == null) ? "" : value.toString();
//        button.setText("Nhắn tin");
//        isPushed = true;
//        return button;
//    }
//}
//    
