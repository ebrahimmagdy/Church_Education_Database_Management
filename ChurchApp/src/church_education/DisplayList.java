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


public class DisplayList extends javax.swing.JFrame {

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
    
    public DisplayList(String family, String listName, String servant) {
        initComponents();
        init(family, listName, servant);
    }

    void init(String family, String listName, String servant){
        //initComponents();
        this.setResizable(false);
        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        int c = jTable1.getColumnCount();
        for(int i = 0; i < c; i++){
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(100);
        }
        
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
                pst = (PreparedStatement) con.prepareStatement("select  p.FirstName," +
                    " p.SecondName," +
                    " p.ThirdName," +
                    " p.FourthName," +
                    " p.DOB," +
                    " p.ApartmentNumber," +
                    " p.FloorNumber," +
                    " p.Job," +
                    " p.Telephon," +
                    " p.Mobile," +
                    " p.Gender," +
                    " p.EducationalLevel," +
                    " p.MotherJob," +
                    " p.MotherMobile," +
                    " p.FatherMobile," +
                    " p.FatherJob," +
                    " p.House," +
                    " st.StreetName as street1," +
                    " st.Area,"+
                    " st2.StreetName as street2," +
                    " fr.Name as frName" +
                    " from " +
                    "Persons p left join Streets st " +
                    "on p.StreetID = st.StreetID left join Streets st2 " +
                    "on p.StreetID2 = st2.StreetID left join Fathers fr " +
                    "on fr.FatherID = p.FatherID " +
                    "where p.PersonID = ?");
                pst.setInt(1, temprs.getInt("ServedID"));
                System.out.println(pst.asSql());
                rs = pst.executeQuery();
                while(rs.next()){
                    if(area.equals("") && rs.getString("Area") != null){
                        area = rs.getString("Area");
                    }
                    if(education.equals("") && rs.getString("EducationalLevel") != null){
                        education = rs.getString("EducationalLevel");
                    }
                    Object[] raw = new Object[22];
                    raw[0] = false;
                    raw[1] = temprs.getInt("ServedID");
                    raw[2] = rs.getString("FirstName");
                    raw[3] = rs.getString("SecondName");
                    raw[4] = rs.getString("ThirdName");
                    raw[5] = rs.getString("FourthName");
                    raw[6] = rs.getDate("DOB");
                    raw[7] = rs.getInt("ApartmentNumber");
                    raw[8] = rs.getInt("FloorNumber");
                    raw[9] = rs.getInt("House");
                    raw[10] = rs.getString("street1");
                    raw[11] = rs.getString("street2");
                    raw[12] = rs.getString("Mobile");
                    raw[13] = rs.getString("MotherMobile");
                    raw[14] = rs.getString("FatherMobile");
                    raw[15] = rs.getString("Telephon");
                    raw[16] = rs.getString("FatherJob");
                    raw[17] = rs.getString("MotherJob");
                    raw[18] = rs.getString("Job");
                    raw[19] = rs.getString("EducationalLevel");
                    raw[20] = rs.getString("frName");
                    raw[21] = rs.getString("Gender");
                    for(int i = 1; i < 22; i++){
                        if(raw[i] == null || raw[i].toString().equals("null") || raw[i].toString().equals(Integer.toString(0)) ){
                            raw[i] = "";
                        }
                    }
                    model.addRow(raw);
                }
            }
            Area.setText(area);
            Education.setText(education);
            Year.setText("2020/2021");
            Servant.setText(servant);
            Family.setText(family);
            List.setText(listName);
            jTable1.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(DisplayList.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        First = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        Family = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        Year = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Servant = new javax.swing.JTextField();
        List = new javax.swing.JTextField();
        Education = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Area = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setText("طباعة");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setText("تعديل");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setText("حذف من القائمة");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(jButton5.getFont().deriveFont(jButton5.getFont().getStyle() | java.awt.Font.BOLD));
        jButton5.setText("refresh");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 150, 620));

        First.setBackground(new java.awt.Color(255, 255, 255));
        First.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        First.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "اختار", "ID", "الاسم", "الاسم الثانى", "الاسم الثالث", "الاسم الرابع", "تاريخ الميلاد", "رقم الشقة", "رقم الدور", "رقم المنزل", "اسم الشارع", "متفرع من", "الموبايل", "موبايل الام", "موبايل الاب", "التليفون الارضى", "وظيفة الاب", "وظيفة الام", "العمل", "المستوى الدراسى", "اب الاعتراف", "النوع"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
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

        First.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 860, 490));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(jLabel13.getFont().deriveFont(jLabel13.getFont().getSize()+4f));
        jLabel13.setText("الاسرة");

        Family.setEditable(false);
        Family.setBackground(new java.awt.Color(255, 255, 255));
        Family.setFont(Family.getFont().deriveFont(Family.getFont().getStyle() | java.awt.Font.BOLD, Family.getFont().getSize()+4));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(jLabel14.getFont().deriveFont(jLabel14.getFont().getSize()+4f));
        jLabel14.setText("العام الخدمى");

        Year.setEditable(false);
        Year.setBackground(new java.awt.Color(255, 255, 255));
        Year.setFont(Year.getFont().deriveFont(Year.getFont().getStyle() | java.awt.Font.BOLD, Year.getFont().getSize()+4));

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

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(jLabel18.getFont().deriveFont(jLabel18.getFont().getSize()+4f));
        jLabel18.setText("المنطقة");

        Area.setEditable(false);
        Area.setBackground(new java.awt.Color(255, 255, 255));
        Area.setFont(Area.getFont().deriveFont(Area.getFont().getStyle() | java.awt.Font.BOLD, Area.getFont().getSize()+4));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Servant, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                    .addComponent(List)
                    .addComponent(Area))
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel18))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel16)))
                .addGap(210, 210, 210)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Family, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(Year)
                    .addComponent(Education))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addComponent(jLabel14))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel13)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)))
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
                            .addComponent(jLabel16))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Family, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel14)))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Education, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))))
                .addGap(36, 36, 36))
        );

        First.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, 860, 130));

        jPanel1.add(First, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 860, -1));

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
                FileWriter file = new FileWriter("files/list.csv");
                //os = new PrintWriter(file);
                CSVWriter writer = new CSVWriter(file);
                int r = jTable1.getRowCount();
                int c = jTable1.getColumnCount();
                String[][] obj = new String[r][14];
                for(int i = 0; i < r; i++){
                    //id
                    obj[i][0] = jTable1.getValueAt(i, 1).toString();
                    //name
                    obj[i][1] = jTable1.getValueAt(i, 2).toString() + " " + jTable1.getValueAt(i, 3).toString() + " " +
                            jTable1.getValueAt(i, 4).toString() + " " + jTable1.getValueAt(i, 5).toString();
                    //DOB
                    obj[i][2] = jTable1.getValueAt(i, 6).toString();
                    //address
                    obj[i][3] = jTable1.getValueAt(i, 7).toString();
                    obj[i][4] = jTable1.getValueAt(i, 8).toString();
                    obj[i][5] = jTable1.getValueAt(i, 9).toString();
                    obj[i][6] = jTable1.getValueAt(i, 10).toString();
                    obj[i][7] = jTable1.getValueAt(i, 11).toString();
                    //mobile
                    obj[i][8] = jTable1.getValueAt(i, 12).toString();
                    //m
                    obj[i][9] = jTable1.getValueAt(i, 13).toString();
                    //f
                    obj[i][10] = jTable1.getValueAt(i, 14).toString();
                    //job
                    //f
                    obj[i][11] = jTable1.getValueAt(i, 16).toString();
                    //m
                    obj[i][12] = jTable1.getValueAt(i, 17).toString();
                    //fr
                    obj[i][13] = jTable1.getValueAt(i, 20).toString();
                }   String[] colNames = new String[]{"ID", "الاسم", "تاريخ الميلاد", "رقم الشقة", "رقم الدور", "رقم المنزل", "اسم الشارع", "متفرع من", "الموبايل", "موبايل الام", "موبايل الاب", "عمل الاب", "عمل الام", "اب الاعتراف"};
                writer.writeNext(new String[]{"اسم الخادم", servant,"    ", listName, "اسم القائمة"});
                writer.writeNext(new String[]{"الاسرة", family,"    ", area, "المنطقة"});
                writer.writeNext(new String[]{"المرحلة الدراسية", education,"    ", year, "العام الخدمى"});
                writer.writeNext(new String[]{});
                writer.writeNext(colNames);
                for (int i = 0; i < r; i++) {
                    writer.writeNext(obj[i]);
                }
                writer.close();
                JOptionPane.showMessageDialog(null,  "your file is saved successfuly go to \"(program file)/file/list.csv\"", "Alert", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                Logger.getLogger(DisplayList.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,  "some thing wrong happen!", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        JFrame frame = new JFrame("Edit");
        int r = jTable1.getRowCount();
        for(int i = 0; i < r; i++){
            if((boolean)jTable1.getValueAt(i, 0)){
                System.out.println((int)jTable1.getValueAt(i, 1));
                EditServed es = new EditServed((int)jTable1.getValueAt(i, 1));
                es.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                es.setVisible(true);
            }
        }
        init(family, listName, servant);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int r = jTable1.getRowCount();
        for(int i = 0; i < r; i++){
            if((boolean)jTable1.getValueAt(i, 0)){
                delete((int)jTable1.getValueAt(i, 1));
            }
        }
        init(family, listName, servant);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        init(family, listName, servant);
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Area;
    private javax.swing.JTextField Education;
    private javax.swing.JTextField Family;
    private javax.swing.JPanel First;
    private javax.swing.JTextField List;
    private javax.swing.JTextField Servant;
    private javax.swing.JTextField Year;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
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
            Logger.getLogger(DisplayList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
