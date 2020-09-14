/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package church_education;

import com.lowagie.text.pdf.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.opencsv.CSVWriter;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFileChooser;


/**
 *
 * @author ebrahim
 */


public class ListReview extends javax.swing.JFrame {

    /**
     * Creates new form DisplayList
     */
     
    LogIn log = new LogIn();
    Connection con = (Connection) log.con;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs, temprs;
    
    private String listName = "";
    private String family = "";
    private String servant = "";
    private String area = "";
    private String education = "";
    private String year = "";
    private java.sql.Date date = null;
    private int weeks = 1;
    
    public ListReview(String family, String listName, String servant) {
        initComponents();
        init(family, listName, servant);
    }

    void init(String family, String listName, String servant){
        this.setResizable(false);
        //jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
//        for(int i = 0; i < c; i++){
//            jTable1.getColumnModel().getColumn(i).setPreferredWidth(100);
//        }
        
        this.listName = listName;
        this.servant = servant;
        this.family = family;
        
        fill();
    }
    void fill(){
        try {
            String l, f, s, fq, sq;
            if(family.equals("")){
                f = " l.FamilyID is null ";
                fq = "";
            }else{
                f = "f.FamilyName = " + "\"" + family + "\"" + " ";
                fq = " inner join Families f on f.FamilyID = l.FamilyID ";
            }
            if(servant.equals("")){
                s = " l.ServantID is null ";
                sq = "";
            }else{
                String[] ser = servant.split("\\s+");
                if(ser.length == 3){
                    s = " (s.FirstName = " + "\"" + ser[0] + "\"" + " and s.SecondName = " + "\"" + ser[1] + "\"" + " and s.ThirdName = " + "\"" + ser[2] + "\"" + ") ";
                }else if(ser.length == 2){
                    s = " (s.FirstName = " + "\"" + ser[0] + "\"" + " and s.SecondName = " + "\"" + ser[1] + "\"" + ") ";
                }else if(ser.length == 1){
                    s = " (s.FirstName = " + "\"" + ser[0] + "\"" + ") ";
                }else{
                    s = " true ";
                }
                sq = " inner join Persons s on l.ServantID = s.PersonID ";
            }
            if(listName.equals("")){
                l = " l.ListName is null ";
            }else{
                l = " l.ListName = " + "\"" + listName + "\" ";
            }
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.getDataVector().removeAllElements();
                
            pst = (PreparedStatement) con.prepareStatement("select  l.ServedID" +
                    " from Lists l " + fq + sq +
                    "where " + l + " and " + s + " and " + f);
            System.out.println(pst.asSql());
            temprs = pst.executeQuery();
            while(temprs.next()){
                pst = (PreparedStatement) con.prepareStatement("select  FirstName," +
                    " SecondName," +
                    " ThirdName," +
                    " FourthName," +
                    " EducationalLevel" +
                    " from " +
                    "Persons  " +
                    "where PersonID = ?");
                pst.setInt(1, temprs.getInt("ServedID"));
                rs = pst.executeQuery();
                while(rs.next()){
                    if(education.equals("") && rs.getString("EducationalLevel") != null){
                        education = rs.getString("EducationalLevel");
                    }
                    Object[] raw = new Object[8];
                    raw[0] = temprs.getInt("ServedID");
                    raw[1] = rs.getString("FirstName");
                    raw[2] = rs.getString("SecondName");
                    raw[3] = rs.getString("ThirdName");
                    raw[4] = rs.getString("FourthName");
                    raw[5] = false;
                    raw[6] = false;
                    raw[7] = false;
                    for(int i = 1; i < 8; i++){
                        if(raw[i] == null || raw[i].toString().equals("null") || raw[i].toString().equals(Integer.toString(0)) ){
                            raw[i] = "";
                        }
                    }
                    model.addRow(raw);
                }
            }
            Education.setText(education);
            Servant.setText(servant);
            Family.setText(family);
            List.setText(listName);
            jTable1.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(ListReview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public void printComponenet(Component component){
//        PrinterJob pj = PrinterJob.getPrinterJob();
//        pj.setJobName(" Print Component ");
//
//        pj.setPrintable (new Printable() {    
//          public int print(Graphics pg, PageFormat pf, int pageNum){
//            if (pageNum > 0){
//            return Printable.NO_SUCH_PAGE;
//            }
//
//            Graphics2D g2 = (Graphics2D) pg;
//            g2.translate(pf.getImageableX(), pf.getImageableY());
//            component.paint(g2);
//            return Printable.PAGE_EXISTS;
//          }
//        });
//        if (pj.printDialog() == false)
//        return;
//
//        try {
//              pj.print();
//        } catch (PrinterException ex) {
//              // handle exception
//        }
//      }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        First = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        Family = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Servant = new javax.swing.JTextField();
        List = new javax.swing.JTextField();
        Education = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Weeks = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        First.setBackground(new java.awt.Color(255, 255, 255));
        First.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        First.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "الاسم", "الاسم الثانى", "الاسم الثالث", "الاسم الرابع", "حضور", "قداس", "افتقاد"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        First.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 860, 480));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(jLabel13.getFont().deriveFont(jLabel13.getFont().getSize()+4f));
        jLabel13.setText("الاسرة");

        Family.setEditable(false);
        Family.setBackground(new java.awt.Color(255, 255, 255));
        Family.setFont(Family.getFont().deriveFont(Family.getFont().getStyle() | java.awt.Font.BOLD, Family.getFont().getSize()+4));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(jLabel15.getFont().deriveFont(jLabel15.getFont().getSize()+4f));
        jLabel15.setText("اسم القائمة");

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(jLabel16.getFont().deriveFont(jLabel16.getFont().getSize()+4f));
        jLabel16.setText("الخادم");

        Servant.setEditable(false);
        Servant.setBackground(new java.awt.Color(255, 255, 255));
        Servant.setFont(Servant.getFont().deriveFont(Servant.getFont().getStyle() | java.awt.Font.BOLD, Servant.getFont().getSize()+4));

        List.setEditable(false);
        List.setBackground(new java.awt.Color(255, 255, 255));
        List.setFont(List.getFont().deriveFont(List.getFont().getStyle() | java.awt.Font.BOLD, List.getFont().getSize()+4));

        Education.setEditable(false);
        Education.setBackground(new java.awt.Color(255, 255, 255));
        Education.setFont(Education.getFont().deriveFont(Education.getFont().getStyle() | java.awt.Font.BOLD, Education.getFont().getSize()+4));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(jLabel17.getFont().deriveFont(jLabel17.getFont().getSize()+4f));
        jLabel17.setText("المرحلة الدراسية");

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(jLabel19.getFont().deriveFont(jLabel19.getFont().getSize()+4f));
        jLabel19.setText("التاريخ");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(jButton1.getFont().deriveFont(jButton1.getFont().getStyle() | java.awt.Font.BOLD, jButton1.getFont().getSize()+4));
        jButton1.setText("تسجيل");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(jButton2.getFont().deriveFont(jButton2.getFont().getStyle() | java.awt.Font.BOLD, jButton2.getFont().getSize()+4));
        jButton2.setText("طباعة");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Weeks.setFont(Weeks.getFont().deriveFont(Weeks.getFont().getSize()+4f));
        Weeks.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getSize()+4f));
        jLabel1.setText("عدد الاسابيع");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Servant, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                            .addComponent(List))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel16)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(Family, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                .addComponent(jLabel13))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(Education)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Weeks, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(87, 87, 87)
                        .addComponent(jLabel19)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(List, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel15)))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Servant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Family, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Education, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                        .addComponent(Weeks, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(30, 30, 30))
        );

        First.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, 860, 130));

        getContentPane().add(First);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            FileWriter file = new FileWriter("files/Attendance/" + family + " " + listName + " " + servant + ".csv");
            //os = new PrintWriter(file);
            CSVWriter writer = new CSVWriter(file);
            int r = jTable1.getRowCount();
            int c = jTable1.getColumnCount();
            weeks = (int)Weeks.getValue();
            String[] colNames = new String[2 + 3 * weeks];
            colNames[0] = "ID";
            colNames[1] = "الاسم";
            for(int i = 0; i < 3 * weeks; i += 3){
                colNames[i + 2] = "حضور";
                colNames[i + 3] = "قداس";
                colNames[i + 4] = "افتقاد";
            }
            String[][] obj = new String[r][2 + 3 * weeks];
            for(int i = 0; i < r; i++){
                //id
                obj[i][0] = jTable1.getValueAt(i, 0).toString();
                //name
                obj[i][1] = jTable1.getValueAt(i, 1).toString() + " " + jTable1.getValueAt(i, 2).toString() + " " +
                jTable1.getValueAt(i, 3).toString() + " " + jTable1.getValueAt(i, 4).toString();
                
                for(int j = 0; j < 3 * weeks; j += 3){
                    obj[i][j + 2] = "   ";
                    obj[i][j + 3] = "   ";
                    obj[i][j + 4] = "   ";
                }
            }
            writer.writeNext(new String[]{"الاسرة", "  ", family});
            writer.writeNext(new String[]{"اسم القائمة", "  ", listName});
            writer.writeNext(colNames);
            for (int i = 0; i < r; i++) {
                writer.writeNext(obj[i]);
            }
            writer.close();
            JOptionPane.showMessageDialog(null,  "your file is saved successfuly go to \"(program file)\files/Attendance/" + family + " " + listName + " " + servant + ".csv" + "\"", "Alert", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(ListReview.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,  "some thing wrong happen!", "Alert", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(jDateChooser1.getDate() == null){
            JOptionPane.showMessageDialog(null, "رجاء ادخال التاريخ!", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        date = new java.sql.Date(jDateChooser1.getDate().getTime());
        int r = jTable1.getRowCount();
        for(int i = 0; i < r; i++){
            try {
                pst = (PreparedStatement) con.prepareStatement("delete from ServedReview where ServedID = ? and Date = ?");
                pst.setInt(1, (int) jTable1.getValueAt(i, 0));
                pst.setDate(2, date);
                pst.executeUpdate();
                pst = (PreparedStatement) con.prepareStatement("insert into ServedReview (ServedID, Attendance, Liturgy, Visit, Date) values (?, ?, ?, ?, ?)");
                pst.setInt(1, (int) jTable1.getValueAt(i, 0));
                pst.setBoolean(2, (boolean) jTable1.getValueAt(i, 5));
                pst.setBoolean(3, (boolean) jTable1.getValueAt(i, 6));
                pst.setBoolean(4, (boolean) jTable1.getValueAt(i, 7));
                pst.setDate(5, date);
                pst.executeUpdate();
            } catch (Exception e) {
                System.err.println(e);
                JOptionPane.showMessageDialog(null, "some thing wrong happen !", "Messege", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        for(int i = 0; i < r; i++){
            jTable1.setValueAt(null, i, 0);
            jTable1.setValueAt(null, i, 1);
            jTable1.setValueAt(null, i, 2);
            jTable1.setValueAt(null, i, 3);
            jTable1.setValueAt(null, i, 4);
            jTable1.setValueAt(false, i, 5);
            jTable1.setValueAt(false, i, 6);
            jTable1.setValueAt(false, i, 7);
        }
        JOptionPane.showMessageDialog(null, "Done!", "Messege", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

    
    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Education;
    private javax.swing.JTextField Family;
    private javax.swing.JPanel First;
    private javax.swing.JTextField List;
    private javax.swing.JTextField Servant;
    private javax.swing.JSpinner Weeks;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void delete(int id) {
        try {
            String l, f, s, fq, sq;
            if(family.equals("")){
                f = " l.FamilyID is null ";
                fq = "";
            }else{
                f = "f.FamilyName = " + "\"" + family + "\"" + " ";
                fq = " inner join Families f on f.FamilyID = l.FamilyID ";
            }
            if(servant.equals("")){
                s = " l.ServantID is null ";
                sq = "";
            }else{
                String[] ser = servant.split("\\s+");
                if(ser.length == 3){
                    s = " (s.FirstName = " + "\"" + ser[0] + "\"" + " and s.SecondName = " + "\"" + ser[1] + "\"" + " and s.ThirdName = " + "\"" + ser[2] + "\"" + ") ";
                }else if(ser.length == 2){
                    s = " (s.FirstName = " + "\"" + ser[0] + "\"" + " and s.SecondName = " + "\"" + ser[1] + "\"" + ") ";
                }else if(ser.length == 1){
                    s = " (s.FirstName = " + "\"" + ser[0] + "\"" + ") ";
                }else{
                    s = " true ";
                }
                sq = " inner join Persons s on l.ServantID = s.PersonID ";
            }
            if(listName.equals("")){
                l = " l.ListName is null ";
            }else{
                l = " l.ListName = " + "\"" + listName + "\" ";
            }
                
            pst = (PreparedStatement) con.prepareStatement("delete l from Lists l " + fq + sq +
                    "where " + l + " and " + s + " and " + f + " and ServedID = ?");
            pst.setInt(1, id);
            System.out.println(pst.asSql());
            int fl = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the ID = " + Integer.toString(id) + " from this list", "Alert", JOptionPane.YES_NO_OPTION);
            if(fl == 0){
                pst.executeUpdate();
                
            }
            //temprs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ListReview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
