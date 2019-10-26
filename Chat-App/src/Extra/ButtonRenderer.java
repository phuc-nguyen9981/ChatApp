//package Extra;
//
//
//import java.awt.Component;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JTable;
//import javax.swing.UIManager;
//import javax.swing.table.TableCellRenderer;
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
//public class ButtonRenderer extends JButton implements TableCellRenderer{
//    private ImageIcon icon;
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//         if (isSelected) { 
//            setForeground(table.getSelectionForeground()); 
//            setBackground(table.getSelectionBackground()); 
//        } else{ 
//            setForeground(table.getForeground()); 
//            setBackground(UIManager.getColor("Button.background")); 
//        } 
//        icon = new ImageIcon("C:\\Users\\CongNguyen\\Documents\\NetBeansProjects\\New folder\\LTMang-project\\Chat-App\\src\\img\\download2.png");
//        setIcon(icon);
//        setText("Nhắn tin"); 
//        return this; 
//    }
//    
//}
