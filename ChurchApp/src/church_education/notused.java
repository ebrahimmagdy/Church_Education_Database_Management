/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package church_education;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.mytdev.javafx.scene.control.AutoCompleteTextField;

import org.fife.ui.autocomplete.*;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javafx.beans.InvalidationListener;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.IndexRange;
import javafx.scene.control.MenuItem;
//import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javax.swing.JComboBox;

/**
 *
 * @author ebrahim
 */
public class notused extends javax.swing.JPanel {

    /**
     * Creates new form Main
     */
    private static final String url = "jdbc:mysql://localhost/CHURCH_EDUCATION?useUnicode=yes&characterEncoding=UTF-8";
    private static final String user = "Ebrahim";
    private static final String password = "jesus01203952089";
    private static final String namesarabic = "SET NAMES 'utf8'";
    private static final String setarabic = "ALTER DATABASE dbname CHARACTER SET utf8 COLLATE utf8_general_ci";
    Connection con = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs;
    
    private String check = "";
    private String frname = "";
    private String frnumber = "";
    private String frchurch = "";
    private String stname = "";
    private String stname2 = "";
    private String area = "";
    
    private String name1 = "";
    private String name2 = "";
    private String name3 = "";
    private String name4 = "";
    private java.sql.Date date = null;
    private int flat;
    private int floor;
    private int house;
    private String street1 = "";
    private String street2 = "";
    private String father = "";
    private String gender = "";
    private String phone = "";
    private String mobile = "";
    private String family = "";
    private String job = "";
    private String education = "";
    private String mnumber = "";
    private String mjob = "";
    private String fnumber = "";
    private String fjob = "";
    private String servant[];
    
    private String sname1 = "";
    private String sname2 = "";
    private String sname3 = "";
    private String sname4 = "";
    private java.sql.Date sdate = null;
    private int sflat;
    private int sfloor;
    private int shouse;
    private String sstreet1 = "";
    private String sstreet2 = "";
    private String sfather = "";
    private String sgender = "";
    private String sphone = "";
    private String smobile = "";
    private String sfamily1 = "";
    private String sfamily2 = "";
    private String sjob = "";
    private String cname = "";
    private String cfamily1 = "";
    private String cfamily2 = "";
    
//    private javafx.scene.control.TextField textField = new javafx.scene.control.TextField();
    private ArrayList<String> streetsnames = new ArrayList<>();
    private ArrayList<String> areasnames = new ArrayList<>();
    private ArrayList<String> servantsnames = new ArrayList<>();
    private ArrayList<String> fathers = new ArrayList<>();
    private ArrayList<String> families = new ArrayList<>();
    private ArrayList<String> assistance = new ArrayList<>();
    private ArrayList<String> educations = new ArrayList<>();
    private AutoComplete2 ac = new AutoComplete2();
    
    
    public notused() {
        initComponents();
        buttonGroup1.add(Male);
        buttonGroup1.add(Female);
        buttonGroup2.add(Smale);
        buttonGroup2.add(Sfemale);
        
        educations.add("kg1");
        educations.add("kg2");
        educations.add("ب1");
        educations.add("ب2");
        educations.add("ب3");
        educations.add("ب4");
        educations.add("ب5");
        educations.add("ب6");
        educations.add("ع1");
        educations.add("ع2");
        educations.add("ع3");
        educations.add("ث1");
        educations.add("ث2");
        educations.add("ث3");
        educations.add("طالب جامعى");
        educations.add("خريج");
        educations.add("اخرى");
        fillComboBox(Education, educations);
        
        autoComplete();
        System.out.println("ok2");
    }

    public void Connect(){
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(notused.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,  "Connection faild!", "Alert", JOptionPane.ERROR_MESSAGE);

        }
    }
    
    public void getStreetsNames(){
        try {
            streetsnames.clear();
            Connect();
            st = (Statement) con.createStatement();
            rs = st.executeQuery("select StreetName from Streets");
            while(rs.next()){
                streetsnames.add(rs.getString("StreetName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(notused.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getAreasNames(){
        try {
            areasnames.clear();
            Connect();
            st = (Statement) con.createStatement();
            rs = st.executeQuery("select Area from Streets");
            while(rs.next()){
                areasnames.add(rs.getString("Area"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(notused.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getFathers(){
        try {
            fathers.clear();
            Connect();
            st = (Statement) con.createStatement();
            rs = st.executeQuery("select Name from Fathers");
            while(rs.next()){
                fathers.add(rs.getString("Name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(notused.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getServantsNames(){
        servantsnames.clear();
        String frname, scname, thname, foname; 
        try {
            Connect();
            st = (Statement) con.createStatement();
            rs = st.executeQuery("SELECT FirstName, SecondName, ThirdName, FourthName\n"+
                 "from Servants\n" + 
                 "INNER JOIN Persons ON Servants.ServantID = Persons.PersonID;");
            while(rs.next()){
                frname = rs.getString("FirstName");
                scname = rs.getString("SecondName");
                thname = rs.getString("ThirdName");
                foname = rs.getString("FourthName");
                servantsnames.add(frname + " " + scname + " " + thname + " " + foname);
            }
        } catch (SQLException ex) {
            Logger.getLogger(notused.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getFamilies(){
        families.clear();
        try {
            Connect();
            st = (Statement) con.createStatement();
            rs = st.executeQuery("select FamilyName from Families");
            while(rs.next()){
                families.add(rs.getString("FamilyName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(notused.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getAssistance(){
        assistance.clear();
        String frname, scname, thname, foname; 
        try {
            Connect();
            int fID = -1;
            pst = (PreparedStatement) con.prepareStatement("SELECT FamilyID FROM Families WHERE FamilyName = ? LIMIT 1");
            pst.setString(1, "اعداد خدام");
            rs = pst.executeQuery();
            if(rs.next())
                fID = rs.getInt("FamilyID");
            
            pst = (PreparedStatement) con.prepareStatement("SELECT FirstName, SecondName, ThirdName, FourthName\n"+
                 "from Persons\n" + 
                 "Inner join Distribution where Persons.PersonID = Distribution.PersonID and Distribution.FamilyID = ? and Distribution.Role = ?");
            pst.setInt(1, fID);
            pst.setString(2, "Student");
            rs = pst.executeQuery();
            while(rs.next()){
                frname = rs.getString("FirstName");
                scname = rs.getString("SecondName");
                thname = rs.getString("ThirdName");
                foname = rs.getString("FourthName");
                assistance.add(frname + " " + scname + " " + thname + " " + foname);
                System.out.println(frname + " " + scname + " " + thname + " " + foname);
            }
        } catch (SQLException ex) {
            Logger.getLogger(notused.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void autoComplete(){
//        getStreetsNames();
//        getAreasNames();
//        getFathers();
//        getServantsNames();
//        getFamilies();
//        getAssistance();
//        fillComboBox(Family, families);
//        fillComboBox(Cfamily, families);
//        fillComboBox(Cfamily2, families);
//        fillComboBox(Sfamily1, families);
//        fillComboBox(Sfamily2, families);
//        fillComboBox(Cname, assistance);

//        System.out.println("ok1");
        
    }
    
    public void fillComboBox(JComboBox<String> combo, ArrayList<String> arr){
        combo.removeAllItems();
        combo.addItem(null);
        for(String s : arr){
            combo.addItem(s);
        }
        combo.setSelectedItem(null);
    }
    
    public String FixString(String in){
        String out = in.replace(" ", "");
        out = out.replace("أ", "ا");
        out = out.replace("إ", "ا");
        out = out.replace("آ", "ا");
        out = out.replace("ؤ", "و");
        if(out.endsWith("ه")){
            out = out.substring(0,out.length() - 1) + 'ة';
        }
        return out;
    }
    
    public void addDistribution(String fname, int pID, int role){
        try {
            int fID = -1;
            pst = (PreparedStatement) con.prepareStatement("SELECT FamilyID FROM Families WHERE FamilyName = ? LIMIT 1");
            pst.setString(1, fname);
            rs = pst.executeQuery();
            if(rs.next())
                fID = rs.getInt("FamilyID");
            
            pst = (PreparedStatement) con.prepareStatement("insert into Distribution(FamilyID, PersonID, Role) values(?, ?, ?)");
            pst.setInt(1, fID);
            pst.setInt(2, pID);
            if(role == 0)
                pst.setString(3, "Servant");
            else
                pst.setString(3, "Student");
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(notused.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Name1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Name2 = new javax.swing.JTextField();
        Name3 = new javax.swing.JTextField();
        Name4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Flat = new javax.swing.JTextField();
        Floor = new javax.swing.JTextField();
        Street1 = new javax.swing.JTextField();
        Street2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        Father = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        Male = new javax.swing.JRadioButton();
        Female = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        Phone = new javax.swing.JTextField();
        Mobile = new javax.swing.JTextField();
        Job = new javax.swing.JTextField();
        Mnumber = new javax.swing.JTextField();
        Mjob = new javax.swing.JTextField();
        Fnumber = new javax.swing.JTextField();
        Fjob = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        Servant = new javax.swing.JTextField();
        Family = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        Jdate = new com.toedter.calendar.JDateChooser();
        jLabel52 = new javax.swing.JLabel();
        Education = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        House = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        Cname = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        Sname1 = new javax.swing.JTextField();
        Sname2 = new javax.swing.JTextField();
        Sname3 = new javax.swing.JTextField();
        Sname4 = new javax.swing.JTextField();
        Sfloor = new javax.swing.JTextField();
        Sstreet1 = new javax.swing.JTextField();
        Sstreet2 = new javax.swing.JTextField();
        Sfather = new javax.swing.JTextField();
        Sphone = new javax.swing.JTextField();
        Smobile = new javax.swing.JTextField();
        Sfamily1 = new javax.swing.JComboBox<>();
        Sjob = new javax.swing.JTextField();
        Sflat = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        Sfamily2 = new javax.swing.JComboBox<>();
        Sfemale = new javax.swing.JRadioButton();
        Smale = new javax.swing.JRadioButton();
        Jsdate = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        Cfamily = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        Cfamily2 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        Shouse = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        Stname = new javax.swing.JTextField();
        Stname2 = new javax.swing.JTextField();
        Area = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        Frname = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        Frnumber = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        Frchurch = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        FamilyName = new javax.swing.JTextField();
        Leader = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1050, 710));
        setMinimumSize(new java.awt.Dimension(1050, 710));
        setPreferredSize(new java.awt.Dimension(1050, 710));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 0), 5, true), "مخدوم جديد", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 24))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel1.setText("الاسم الاول");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, -1, -1));
        jPanel1.add(Name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 130, -1));

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel2.setText("الاسم الثانى");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, -1, -1));

        jLabel3.setFont(jLabel3.getFont().deriveFont(jLabel3.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel3.setText("الاسم الثالث");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, -1, -1));

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel4.setText("الاسم الرابع");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, -1, -1));
        jPanel1.add(Name2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 130, -1));
        jPanel1.add(Name3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 130, -1));
        jPanel1.add(Name4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 130, -1));

        jLabel5.setFont(jLabel5.getFont().deriveFont(jLabel5.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel5.setText("تاريخ الميلاد");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, -1, -1));

        jLabel6.setFont(jLabel6.getFont().deriveFont(jLabel6.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel6.setText("رقم الشقة");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, -1, -1));

        jLabel7.setFont(jLabel7.getFont().deriveFont(jLabel7.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel7.setText("الدور");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 310, -1, -1));

        jLabel8.setFont(jLabel8.getFont().deriveFont(jLabel8.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel8.setText("العنوان ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, -1, -1));

        jLabel9.setFont(jLabel9.getFont().deriveFont(jLabel9.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel9.setText("اسم الشارع");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 380, -1, -1));

        jLabel10.setFont(jLabel10.getFont().deriveFont(jLabel10.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel10.setText("متفرع من");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 410, -1, -1));

        Flat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FlatKeyPressed(evt);
            }
        });
        jPanel1.add(Flat, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 130, -1));

        Floor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FloorKeyPressed(evt);
            }
        });
        jPanel1.add(Floor, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 130, -1));

        Street1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Street1MouseEntered(evt);
            }
        });
        jPanel1.add(Street1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 130, -1));

        Street2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Street2MouseEntered(evt);
            }
        });
        jPanel1.add(Street2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 400, 130, -1));

        jPanel2.setBackground(new java.awt.Color(222, 221, 220));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 2, 500));

        jLabel11.setFont(jLabel11.getFont().deriveFont(jLabel11.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel11.setText("اب الاعتراف ابونا");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 450, -1, -1));

        Father.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                FatherMouseEntered(evt);
            }
        });
        jPanel1.add(Father, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, 120, -1));

        jLabel12.setFont(jLabel12.getFont().deriveFont(jLabel12.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel12.setText("النوع");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 500, -1, -1));

        Male.setFont(Male.getFont().deriveFont(Male.getFont().getStyle() | java.awt.Font.BOLD));
        Male.setText("ذكر");
        Male.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MaleMouseClicked(evt);
            }
        });
        Male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaleActionPerformed(evt);
            }
        });
        jPanel1.add(Male, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 500, -1, -1));

        Female.setFont(Female.getFont().deriveFont(Female.getFont().getStyle() | java.awt.Font.BOLD));
        Female.setText("انثى");
        jPanel1.add(Female, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 500, -1, -1));

        jLabel13.setFont(jLabel13.getFont().deriveFont(jLabel13.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel13.setText("التليفون");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, -1, -1));

        jLabel14.setFont(jLabel14.getFont().deriveFont(jLabel14.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel14.setText("الموبايل");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, -1, -1));

        jLabel15.setFont(jLabel15.getFont().deriveFont(jLabel15.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel15.setText("العمل");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, -1, -1));

        jLabel16.setFont(jLabel16.getFont().deriveFont(jLabel16.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel16.setText("الاسرة");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, -1, -1));

        jLabel17.setFont(jLabel17.getFont().deriveFont(jLabel17.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel17.setText("موبايل الام");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, -1, -1));

        jLabel18.setFont(jLabel18.getFont().deriveFont(jLabel18.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel18.setText("عمل الام");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, -1, -1));

        jLabel19.setFont(jLabel19.getFont().deriveFont(jLabel19.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel19.setText("موبايل الاب");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 400, -1, -1));

        jLabel20.setFont(jLabel20.getFont().deriveFont(jLabel20.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel20.setText("عمل الاب");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, -1, -1));

        Phone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PhoneKeyPressed(evt);
            }
        });
        jPanel1.add(Phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 130, -1));

        Mobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MobileKeyPressed(evt);
            }
        });
        jPanel1.add(Mobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 130, -1));
        jPanel1.add(Job, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 130, -1));

        Mnumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MnumberKeyPressed(evt);
            }
        });
        jPanel1.add(Mnumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 130, -1));
        jPanel1.add(Mjob, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 130, -1));

        Fnumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FnumberKeyPressed(evt);
            }
        });
        jPanel1.add(Fnumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 130, -1));
        jPanel1.add(Fjob, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 130, -1));

        jLabel21.setFont(jLabel21.getFont().deriveFont(jLabel21.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel21.setText("الخادم المسؤل");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, -1, -1));

        Servant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ServantMouseEntered(evt);
            }
        });
        jPanel1.add(Servant, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 130, -1));

        Family.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                FamilyMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FamilyMousePressed(evt);
            }
        });
        jPanel1.add(Family, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 130, -1));

        jButton1.setFont(jButton1.getFont().deriveFont(jButton1.getFont().getStyle() | java.awt.Font.BOLD));
        jButton1.setText("حفظ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, 160, 40));
        jPanel1.add(Jdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 130, -1));

        jLabel52.setFont(jLabel52.getFont().deriveFont(jLabel52.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel52.setText("المستوى الدراسى");
        jPanel1.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, -1, -1));

        jPanel1.add(Education, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 110, 40));

        jLabel53.setFont(jLabel53.getFont().deriveFont(jLabel53.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel53.setText("رقم المنزل");
        jPanel1.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, -1, -1));

        House.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HouseKeyPressed(evt);
            }
        });
        jPanel1.add(House, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 130, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 540));

        jPanel3.setBackground(java.awt.Color.white);
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 50, 0), 5, true), "خادم جديد", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 24))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setBackground(java.awt.Color.white);
        jLabel23.setFont(jLabel23.getFont().deriveFont(jLabel23.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel23.setText("اختار من اعداد خدام");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, 30));

        Cname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CnameMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CnameMousePressed(evt);
            }
        });
        jPanel3.add(Cname, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 220, -1));

        jPanel4.setBackground(new java.awt.Color(222, 221, 220));
        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 490, 2));

        jLabel24.setBackground(java.awt.Color.white);
        jLabel24.setFont(jLabel24.getFont().deriveFont(jLabel24.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel24.setText("الاسم الاول");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, -1, -1));

        jLabel25.setBackground(java.awt.Color.white);
        jLabel25.setFont(jLabel25.getFont().deriveFont(jLabel25.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel25.setText("الاسم الثانى");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, -1, -1));

        jLabel26.setBackground(java.awt.Color.white);
        jLabel26.setFont(jLabel26.getFont().deriveFont(jLabel26.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel26.setText("الاسم الثالث");
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, -1, -1));

        jLabel27.setBackground(java.awt.Color.white);
        jLabel27.setFont(jLabel27.getFont().deriveFont(jLabel27.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel27.setText("الاسم الرابع");
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, -1, -1));

        jLabel28.setBackground(java.awt.Color.white);
        jLabel28.setFont(jLabel28.getFont().deriveFont(jLabel28.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel28.setText("تاريخ الميلاد");
        jPanel3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 440, -1, -1));

        jLabel29.setBackground(java.awt.Color.white);
        jLabel29.setFont(jLabel29.getFont().deriveFont(jLabel29.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel29.setText("العنوان");
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 270, -1, -1));

        jLabel30.setBackground(java.awt.Color.white);
        jLabel30.setFont(jLabel30.getFont().deriveFont(jLabel30.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel30.setText("رقم الشقة");
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, -1, -1));

        jLabel31.setBackground(java.awt.Color.white);
        jLabel31.setFont(jLabel31.getFont().deriveFont(jLabel31.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel31.setText("الدور");
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, -1, -1));

        jLabel32.setBackground(java.awt.Color.white);
        jLabel32.setFont(jLabel32.getFont().deriveFont(jLabel32.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel32.setText("اسم الشارع");
        jPanel3.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 400, -1, -1));

        jLabel33.setBackground(java.awt.Color.white);
        jLabel33.setFont(jLabel33.getFont().deriveFont(jLabel33.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel33.setText("متفرع من");
        jPanel3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 430, -1, -1));

        jLabel34.setBackground(java.awt.Color.white);
        jLabel34.setFont(jLabel34.getFont().deriveFont(jLabel34.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel34.setText("اب الاعتراف ابونا");
        jPanel3.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 470, -1, -1));

        jLabel35.setBackground(java.awt.Color.white);
        jLabel35.setFont(jLabel35.getFont().deriveFont(jLabel35.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel35.setText("النوع");
        jPanel3.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 500, -1, -1));

        jLabel36.setBackground(java.awt.Color.white);
        jLabel36.setFont(jLabel36.getFont().deriveFont(jLabel36.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel36.setText("التليفون");
        jPanel3.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, -1, -1));

        jLabel37.setBackground(java.awt.Color.white);
        jLabel37.setFont(jLabel37.getFont().deriveFont(jLabel37.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel37.setText("الموبايل");
        jPanel3.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, -1, -1));

        jLabel38.setBackground(java.awt.Color.white);
        jLabel38.setFont(jLabel38.getFont().deriveFont(jLabel38.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel38.setText("الاسرة ");
        jPanel3.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, -1, -1));

        jLabel39.setBackground(java.awt.Color.white);
        jLabel39.setFont(jLabel39.getFont().deriveFont(jLabel39.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel39.setText("العمل");
        jPanel3.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 410, -1, -1));
        jPanel3.add(Sname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 130, -1));
        jPanel3.add(Sname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 130, -1));
        jPanel3.add(Sname3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 130, -1));
        jPanel3.add(Sname4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 130, -1));

        Sfloor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SfloorKeyPressed(evt);
            }
        });
        jPanel3.add(Sfloor, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 130, -1));

        Sstreet1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Sstreet1MouseEntered(evt);
            }
        });
        jPanel3.add(Sstreet1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, 130, -1));

        Sstreet2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Sstreet2MouseEntered(evt);
            }
        });
        jPanel3.add(Sstreet2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 420, 130, -1));

        Sfather.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SfatherMouseEntered(evt);
            }
        });
        jPanel3.add(Sfather, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 460, 120, -1));

        Sphone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SphoneKeyPressed(evt);
            }
        });
        jPanel3.add(Sphone, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 130, -1));

        Smobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SmobileKeyPressed(evt);
            }
        });
        jPanel3.add(Smobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 130, -1));

        Sfamily1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Sfamily1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Sfamily1MousePressed(evt);
            }
        });
        jPanel3.add(Sfamily1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 130, -1));
        jPanel3.add(Sjob, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 130, -1));

        Sflat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SflatKeyPressed(evt);
            }
        });
        jPanel3.add(Sflat, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 130, -1));

        jLabel40.setBackground(java.awt.Color.white);
        jLabel40.setFont(jLabel40.getFont().deriveFont(jLabel40.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel40.setText("خدمة اخرى");
        jPanel3.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, -1, -1));

        Sfamily2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Sfamily2MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Sfamily2MousePressed(evt);
            }
        });
        jPanel3.add(Sfamily2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 130, -1));

        Sfemale.setBackground(java.awt.Color.white);
        Sfemale.setFont(Sfemale.getFont().deriveFont(Sfemale.getFont().getStyle() | java.awt.Font.BOLD));
        Sfemale.setText("انثى");
        Sfemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SfemaleActionPerformed(evt);
            }
        });
        jPanel3.add(Sfemale, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 500, -1, -1));

        Smale.setBackground(java.awt.Color.white);
        Smale.setFont(Smale.getFont().deriveFont(Smale.getFont().getStyle() | java.awt.Font.BOLD));
        Smale.setText("ذكر");
        jPanel3.add(Smale, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 500, -1, -1));
        jPanel3.add(Jsdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 130, -1));

        jPanel5.setBackground(new java.awt.Color(222, 221, 220));
        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 2, 380));

        jButton2.setFont(jButton2.getFont().deriveFont(jButton2.getFont().getStyle() | java.awt.Font.BOLD));
        jButton2.setText("حفظ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, 120, 40));

        jLabel42.setBackground(java.awt.Color.white);
        jLabel42.setFont(jLabel42.getFont().deriveFont(jLabel42.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel42.setText("الاسرة");
        jPanel3.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, -1, -1));

        Cfamily.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CfamilyMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CfamilyMousePressed(evt);
            }
        });
        jPanel3.add(Cfamily, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 140, -1));

        jLabel43.setBackground(java.awt.Color.white);
        jLabel43.setFont(jLabel43.getFont().deriveFont(jLabel43.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel43.setText("خدمة اخرى");
        jPanel3.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, -1, -1));

        Cfamily2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Cfamily2MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Cfamily2MousePressed(evt);
            }
        });
        jPanel3.add(Cfamily2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 140, -1));

        jButton3.setFont(jButton3.getFont().deriveFont(jButton3.getFont().getStyle() | java.awt.Font.BOLD));
        jButton3.setText("حفظ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 130, 50));

        Shouse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ShouseKeyPressed(evt);
            }
        });
        jPanel3.add(Shouse, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 360, 130, -1));

        jLabel54.setFont(jLabel54.getFont().deriveFont(jLabel54.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel54.setText("رقم المنزل");
        jPanel3.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 370, -1, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 530, 540));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 5, true), "شارع جديد", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel44.setFont(jLabel44.getFont().deriveFont(jLabel44.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel44.setText("اسم الشارع");
        jPanel6.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, -1, -1));

        jLabel45.setFont(jLabel45.getFont().deriveFont(jLabel45.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel45.setText("متفرع من");
        jPanel6.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, -1, -1));

        jLabel46.setFont(jLabel46.getFont().deriveFont(jLabel46.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel46.setText("منطقة");
        jPanel6.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, -1, 20));
        jPanel6.add(Stname, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 40, 220, -1));
        jPanel6.add(Stname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 80, 220, -1));

        Area.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AreaMouseEntered(evt);
            }
        });
        jPanel6.add(Area, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 120, 100, -1));

        jButton4.setFont(jButton4.getFont().deriveFont(jButton4.getFont().getStyle() | java.awt.Font.BOLD));
        jButton4.setText("حفظ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 110, -1));

        add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 380, 170));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 5, true), "اب كاهن جديد", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel47.setFont(jLabel47.getFont().deriveFont(jLabel47.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel47.setText("ابونا ");
        jPanel7.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, -1, -1));
        jPanel7.add(Frname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 220, -1));

        jLabel48.setFont(jLabel48.getFont().deriveFont(jLabel48.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel48.setText("الموبايل");
        jPanel7.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, -1));

        Frnumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FrnumberKeyPressed(evt);
            }
        });
        jPanel7.add(Frnumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 140, -1));

        jLabel49.setFont(jLabel49.getFont().deriveFont(jLabel49.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel49.setText("الكنيسة");
        jPanel7.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));
        jPanel7.add(Frchurch, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 140, -1));

        jButton5.setFont(jButton5.getFont().deriveFont(jButton5.getFont().getStyle() | java.awt.Font.BOLD));
        jButton5.setText("حفظ");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 70, 70));

        add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 540, 350, 170));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 255), 5, true), "اسرة جديدة", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel50.setFont(jLabel50.getFont().deriveFont(jLabel50.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel50.setText("الاسم");
        jPanel8.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, -1));

        jLabel51.setFont(jLabel51.getFont().deriveFont(jLabel51.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel51.setText("الامين");
        jPanel8.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, -1, -1));
        jPanel8.add(FamilyName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 200, -1));

        Leader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LeaderMouseEntered(evt);
            }
        });
        jPanel8.add(Leader, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 200, -1));

        jButton6.setFont(jButton6.getFont().deriveFont(jButton6.getFont().getStyle() | java.awt.Font.BOLD));
        jButton6.setText("حفظ");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 100, -1));

        add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 540, 320, 170));
    }// </editor-fold>//GEN-END:initComponents

    private void MaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MaleActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Connect();
            pst = (PreparedStatement) con.prepareStatement("Select StreetID from Streets Where StreetName = ?");
            pst.setString(1, "null");
            rs = pst.executeQuery();
            if(!rs.next()){
                pst = (PreparedStatement) con.prepareStatement("insert into Streets(StreetName) values(?)");
                pst.setString(1, "null");
                pst.executeUpdate();
            }
            pst = (PreparedStatement) con.prepareStatement("Select FatherID from Fathers Where Name = ?");
            pst.setString(1, "null");
            rs = pst.executeQuery();
            if(!rs.next()){
                pst = (PreparedStatement) con.prepareStatement("insert into Fathers(Name) values(?)");
                pst.setString(1, "null");
                pst.executeUpdate();
            }
            
            name1 = FixString(Name1.getText());
            name2 = FixString(Name2.getText());
            if(name1.isEmpty()){
                JOptionPane.showMessageDialog(null,  "رجاء ادخل اسم المخدوم", "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(name2.isEmpty()){
                JOptionPane.showMessageDialog(null, "رجاء ادخل الاسم الثانى ", "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            name3 = FixString(Name3.getText());
            name4 = FixString(Name4.getText());
            if(Jdate.getDate() != null)
                date = new java.sql.Date(Jdate.getDate().getTime());
            if(!Flat.getText().isEmpty())
                flat = Integer.parseInt(Flat.getText());
            if(!Floor.getText().isEmpty())
                floor = Integer.parseInt(Floor.getText());
            if(!House.getText().isEmpty())
                house = Integer.parseInt(House.getText());
            street1 = Street1.getText();
            street2 = Street2.getText();
            father = Father.getText();
            if(Female.isSelected()){
                gender = "Female";
            }else if(Male.isSelected()){
                gender = "Male";
            }
            if(!Phone.getText().isEmpty())
                phone = Phone.getText();
            if(!Mobile.getText().isEmpty())
                mobile = Mobile.getText();
            family = (String)Family.getSelectedItem();
            job = FixString(Job.getText());
            education = (String)Education.getSelectedItem();
            if(!Mnumber.getText().isEmpty())
                mnumber = Mnumber.getText();
            mjob = FixString(Mjob.getText());
            if(!Fnumber.getText().isEmpty())
                fnumber = Fnumber.getText();
            fjob = FixString(Fjob.getText());
            servant = Servant.getText().split("\\s+");
            int stID1 = -1;
            if(street1.isEmpty())
                street1 = "null";
            if(!street1.isEmpty()){
                pst = (PreparedStatement) con.prepareStatement("SELECT StreetID FROM Streets WHERE StreetName = ? LIMIT 1");
                pst.setString(1, street1);
                rs = pst.executeQuery();
                if(rs.next())
                    stID1 = rs.getInt(1);
            }
            int stID2 = -1;
            if(street2.isEmpty())
                street2 = "null";
            if(!street2.isEmpty()){
                pst = (PreparedStatement) con.prepareStatement("SELECT StreetID FROM Streets WHERE StreetName = ? LIMIT 1");
                pst.setString(1, street2);
                rs = pst.executeQuery();
                if(rs.next())
                    stID2 = rs.getInt(1);
            }
            int fatherID = -1;
            if(father.isEmpty())
                father = "null";
            if(!father.isEmpty()){
                pst = (PreparedStatement) con.prepareStatement("SELECT FatherID FROM Fathers WHERE Name = ? LIMIT 1");
                pst.setString(1,father);
                rs = pst.executeQuery();
                if(rs.next())
                    fatherID = rs.getInt(1);
            }
            
            int servantID = 0;
            if(servant.length == 4){
                pst = (PreparedStatement) con.prepareStatement("SELECT PersonID FROM Persons WHERE FirstName = ? AND SecondName = ? AND ThirdName = ? AND FourthName = ? LIMIT 1");
                pst.setString(1, servant[0]);
                pst.setString(2, servant[1]);
                pst.setString(3, servant[2]);
                pst.setString(4, servant[3]);
                rs = pst.executeQuery();
                if(rs.next())    
                    servantID = rs.getInt(1);
            }else if(servant.length == 3){
                pst = (PreparedStatement) con.prepareStatement("SELECT PersonID FROM Persons WHERE FirstName = ? AND SecondName = ? AND ThirdName = ? LIMIT 1");
                pst.setString(1, servant[0]);
                pst.setString(2, servant[1]);
                pst.setString(3, servant[2]);
                rs = pst.executeQuery();
                if(rs.next())
                    servantID = rs.getInt(1);
            }else if(servant.length == 2){
                pst = (PreparedStatement) con.prepareStatement("SELECT PersonID FROM Persons WHERE FirstName = ? AND SecondName = ? LIMIT 1");
                pst.setString(1, servant[0]);
                pst.setString(2, servant[1]);
                rs = pst.executeQuery();
                if(rs.next())
                    servantID = rs.getInt(1);
            }else{
                servantID = -1;
            }
            pst = (PreparedStatement) con.prepareStatement("select * from Persons where " + 
                    "FirstName = ? and (MotherMobile = ? or FatherMobile = ? or Mobile = ?)");
            pst.setString(1, name1);
            pst.setString(2, mnumber);
            pst.setString(3, fnumber);
            pst.setString(4, mobile);
            rs = pst.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "هذا الشخص موجود بالفعل يمكنك البحث عنه عن طريق  ID : " + rs.getInt("PersonID"), "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            pst = (PreparedStatement) con.prepareStatement("INSERT INTO Persons(FirstName, SecondName, ThirdName, " + 
                    "FourthName, DOB, ApartmentNumber, FloorNumber, Job, Telephon, Mobile, Gender, FatherID, StreetID, " +
                    "StreetID2, MotherJob, MotherMobile, FatherMobile, FatherJob, EducationalLevel, House)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, name1);
            pst.setString(2, name2);
            pst.setString(3, name3);
            pst.setString(4, name4);
            pst.setDate(5, date);
            pst.setInt(6, flat);
            pst.setInt(7, floor);
            pst.setString(8, job);
            pst.setString(9, phone);
            pst.setString(10, mobile);
            pst.setString(11, gender);
            pst.setInt(12, fatherID);
            pst.setInt(13, stID1);
            pst.setInt(14, stID2);
            pst.setString(15, mjob);
            pst.setString(16, mnumber);
            pst.setString(17, fjob);
            pst.setString(18, fnumber);
            pst.setString(19, education);
            pst.setInt(20, house);
            pst.executeUpdate();
            
            Name1.setText("");
            Name2.setText("");
            Name3.setText("");
            Name4.setText("");
//            date.setDate(2020);
            Flat.setText("");
            Floor.setText("");
            House.setText("");
            Street1.setText("");
            Street2.setText("");
            Father.setText("");
            buttonGroup1.clearSelection();
            Phone.setText("");
            Mobile.setText("");
            Job.setText("");
            Mnumber.setText("");
            Mjob.setText("");
            Fnumber.setText("");
            Fjob.setText("");
            Servant.setText("");
            
            int servedID = -1;
            rs = pst.executeQuery("Select LAST_INSERT_ID()");
                if(rs.next())
                    servedID = rs.getInt(1); 
            if(servantID != -1){
                pst = (PreparedStatement) con.prepareStatement("INSERT INTO Lists(ServantID, ServedID) VALUES (?, ?)");
                pst.setInt(1, servantID);
                pst.setInt(2, servedID);
                pst.executeUpdate();
            }
            
            if(family != null)
                addDistribution(family, servedID, 1);
            
            autoComplete();
            JOptionPane.showMessageDialog(null, "Done!", "Alert", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(notused.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "some thing wronge hapen!", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void SfemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SfemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SfemaleActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            frname = Frname.getText();
            frnumber = FixString(Frnumber.getText());
            frchurch = Frchurch.getText();
            //JOptionPane.showMessageDialog(null,  frname.length() , "Alert", JOptionPane.ERROR_MESSAGE);
            if(frname.length() == 0){
                JOptionPane.showMessageDialog(null,  "رجاء ادخال اسم الاب الكاهن ", "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Connect();
            pst = (PreparedStatement) con.prepareStatement("INSERT INTO Fathers(Name, Mobile, Church) VALUES (?, ?, ?)");
            pst.setString(1, frname);
            pst.setString(2, frnumber);
            pst.setString(3, frchurch);
            pst.executeUpdate();
            Frname.setText("");
            Frnumber.setText("");
            Frchurch.setText("");
            autoComplete();
        } catch (SQLException ex) {
            Logger.getLogger(notused.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,  "some thing wronge happen!", "Alert", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            stname = Stname.getText();
            stname2 = Stname2.getText();
            area = Area.getText();
            check = stname.replaceAll("\\s+", "");
            if(check.length() == 0){
                JOptionPane.showMessageDialog(null,  "رجاء ادخال اسم الشارع ", "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            check = area.replaceAll("\\s+", "");
            if(check.length() == 0){
                JOptionPane.showMessageDialog(null,  "رجاء ادخال اسم المنطقة ", "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Connect();
            pst = (PreparedStatement) con.prepareStatement("INSERT INTO Streets(StreetName, Area, StreetName2) VALUES (?, ?, ?)");
            pst.setString(1, stname);
            pst.setString(2, area);
            pst.setString(3, stname2);
            pst.executeUpdate();
            pst = (PreparedStatement) con.prepareStatement("INSERT INTO Streets(StreetName, Area) VALUES (?, ?)");
            pst.setString(1, stname2);
            pst.setString(2, area);
            pst.executeUpdate();
            Stname.setText("");
            Stname2.setText("");
            Area.setText("");
            autoComplete();
        } catch (SQLException ex) {
            Logger.getLogger(notused.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,  "some thing wronge happen!", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void MaleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaleMouseClicked
       
    }//GEN-LAST:event_MaleMouseClicked

    private void FlatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FlatKeyPressed
        String value = Flat.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
           Flat.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
           Flat.setEditable(false);
        }
    }//GEN-LAST:event_FlatKeyPressed

    private void FloorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FloorKeyPressed
        String value = Floor.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
           Floor.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
           Floor.setEditable(false);
        }
    }//GEN-LAST:event_FloorKeyPressed

    private void PhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PhoneKeyPressed
        String value = Phone.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
           Phone.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
           Phone.setEditable(false);
        }
    }//GEN-LAST:event_PhoneKeyPressed

    private void MobileKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MobileKeyPressed
        String value = Mobile.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
           Mobile.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
           Mobile.setEditable(false);
        }
    }//GEN-LAST:event_MobileKeyPressed

    private void MnumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MnumberKeyPressed
        String value = Mnumber.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
           Mnumber.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
           Mnumber.setEditable(false);
        }
    }//GEN-LAST:event_MnumberKeyPressed

    private void FnumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FnumberKeyPressed
        String value = Fnumber.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
           Fnumber.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
           Fnumber.setEditable(false);
        }
    }//GEN-LAST:event_FnumberKeyPressed

    private void SflatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SflatKeyPressed
        String value = Sflat.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
           Sflat.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
           Sflat.setEditable(false);
        }
    }//GEN-LAST:event_SflatKeyPressed

    private void SfloorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SfloorKeyPressed
        String value = Sfloor.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
           Sfloor.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
           Sfloor.setEditable(false);
        }
    }//GEN-LAST:event_SfloorKeyPressed

    private void SphoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SphoneKeyPressed
        String value = Sphone.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
           Sphone.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
           Sphone.setEditable(false);
        }
    }//GEN-LAST:event_SphoneKeyPressed

    private void SmobileKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SmobileKeyPressed
        String value = Smobile.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
           Smobile.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
           Smobile.setEditable(false);
        }
    }//GEN-LAST:event_SmobileKeyPressed

    private void FrnumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FrnumberKeyPressed
        String value = Frnumber.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
           Frnumber.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
           Frnumber.setEditable(false);
        }
    }//GEN-LAST:event_FrnumberKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String fam = FamilyName.getText();
        String[] servant = Leader.getText().split("\\s+");
        int servantID = -1;
        if(FixString(fam).isEmpty()){
            JOptionPane.showMessageDialog(null,  "رجاء ادخال اسم الاسرة ", "Alert", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Connect();
        try{
            if(servant.length == 4){
                pst = (PreparedStatement) con.prepareStatement("SELECT PersonID FROM Persons WHERE FirstName = ? AND SecondName = ? AND ThirdName = ? AND FourthName = ? LIMIT 1");
                pst.setString(1, servant[0]);
                pst.setString(2, servant[1]);
                pst.setString(3, servant[2]);
                pst.setString(4, servant[3]);
                rs = pst.executeQuery();
                if(rs.next())    
                    servantID = rs.getInt(1);
            }else if(servant.length == 3){
                pst = (PreparedStatement) con.prepareStatement("SELECT PersonID FROM Persons WHERE FirstName = ? AND SecondName = ? AND ThirdName = ? LIMIT 1");
                pst.setString(1, servant[0]);
                pst.setString(2, servant[1]);
                pst.setString(3, servant[2]);
                rs = pst.executeQuery();
                if(rs.next())
                    servantID = rs.getInt(1);
            }else if(servant.length == 2){
                pst = (PreparedStatement) con.prepareStatement("SELECT PersonID FROM Persons WHERE FirstName = ? AND SecondName = ? LIMIT 1");
                pst.setString(1, servant[0]);
                pst.setString(2, servant[1]);
                rs = pst.executeQuery();
                if(rs.next())
                    servantID = rs.getInt(1);
            }else{
                servantID = -1;
            }
            if(servantID == -1){
                pst = (PreparedStatement) con.prepareStatement("insert into Families(FamilyName) values(?)");
                pst.setString(1, fam);
                pst.executeUpdate();
            }else{
                pst = (PreparedStatement) con.prepareStatement("insert into Families(FamilyName, Leader) values(?, ?)");
                pst.setString(1, fam);
                pst.setInt(2, servantID);
                pst.executeUpdate();
            }
            if(servantID != -1)
                addDistribution(fam, servantID, 0);
            FamilyName.setText("");
            Leader.setText("");
        }catch(Exception e){
            System.err.println(e);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void Street1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Street1MouseEntered
        getStreetsNames();
        ac.autoComplete(Street1, streetsnames);
    }//GEN-LAST:event_Street1MouseEntered

    private void Street2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Street2MouseEntered
        getStreetsNames();
        ac.autoComplete(Street2, streetsnames);
    }//GEN-LAST:event_Street2MouseEntered

    private void FatherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FatherMouseEntered
        getFathers();
        ac.autoComplete(Father, fathers);
    }//GEN-LAST:event_FatherMouseEntered

    private void ServantMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ServantMouseEntered
        getServantsNames();
        ac.autoComplete(Servant, servantsnames);
    }//GEN-LAST:event_ServantMouseEntered

    private void Sstreet1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sstreet1MouseEntered
        getStreetsNames();
        ac.autoComplete(Sstreet1, streetsnames);
    }//GEN-LAST:event_Sstreet1MouseEntered

    private void Sstreet2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sstreet2MouseEntered
        getStreetsNames();
        ac.autoComplete(Sstreet2, streetsnames);
    }//GEN-LAST:event_Sstreet2MouseEntered

    private void SfatherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SfatherMouseEntered
        getFathers();
        ac.autoComplete(Sfather, fathers);
    }//GEN-LAST:event_SfatherMouseEntered

    private void LeaderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LeaderMouseEntered
        getServantsNames();
        ac.autoComplete(Leader, servantsnames);
    }//GEN-LAST:event_LeaderMouseEntered

    private void AreaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AreaMouseEntered
        getAreasNames();
        ac.autoComplete(Area, areasnames);
    }//GEN-LAST:event_AreaMouseEntered

    private void FamilyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FamilyMouseEntered
        
    }//GEN-LAST:event_FamilyMouseEntered

    private void Sfamily2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sfamily2MouseEntered
        
    }//GEN-LAST:event_Sfamily2MouseEntered

    private void Sfamily1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sfamily1MouseEntered
        
    }//GEN-LAST:event_Sfamily1MouseEntered

    private void Cfamily2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cfamily2MouseEntered
        
    }//GEN-LAST:event_Cfamily2MouseEntered

    private void CfamilyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CfamilyMouseEntered
        
    }//GEN-LAST:event_CfamilyMouseEntered

    private void CnameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CnameMouseEntered
        
    }//GEN-LAST:event_CnameMouseEntered

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       int servantID = -1;
       if(Cname.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null,  "رجاء اختيار الخادم الجديد ", "Alert", JOptionPane.ERROR_MESSAGE);
            return;
       }
       String[] servant = ((String)Cname.getSelectedItem()).split("\\s+");
       try{
            Connect();
            if(servant.length == 4){
                pst = (PreparedStatement) con.prepareStatement("SELECT PersonID FROM Persons WHERE FirstName = ? AND SecondName = ? AND ThirdName = ? AND FourthName = ? LIMIT 1");
                pst.setString(1, servant[0]);
                pst.setString(2, servant[1]);
                pst.setString(3, servant[2]);
                pst.setString(4, servant[3]);
                rs = pst.executeQuery();
                if(rs.next())    
                    servantID = rs.getInt(1);
            }else if(servant.length == 3){
                pst = (PreparedStatement) con.prepareStatement("SELECT PersonID FROM Persons WHERE FirstName = ? AND SecondName = ? AND ThirdName = ? LIMIT 1");
                pst.setString(1, servant[0]);
                pst.setString(2, servant[1]);
                pst.setString(3, servant[2]);
                rs = pst.executeQuery();
                if(rs.next())
                    servantID = rs.getInt(1);
            }else if(servant.length == 2){
                pst = (PreparedStatement) con.prepareStatement("SELECT PersonID FROM Persons WHERE FirstName = ? AND SecondName = ? LIMIT 1");
                pst.setString(1, servant[0]);
                pst.setString(2, servant[1]);
                rs = pst.executeQuery();
                if(rs.next())
                    servantID = rs.getInt(1);
            }else{
                servantID = -1;
            }
            if(servantID != -1){
                int f = 0;
                pst = (PreparedStatement) con.prepareStatement("insert into Servants(ServantID) values(?)");
                pst.setInt(1, servantID);
                f = pst.executeUpdate();
                if(f > 0){
                    pst = (PreparedStatement) con.prepareStatement("delete from Distribution where PersonID = ?");
                    pst.setInt(1, servantID);
                    pst.executeUpdate();
                }
            }
            JOptionPane.showMessageDialog(null, "Done!", "Alert", JOptionPane.ERROR_MESSAGE);
       }catch(Exception e){
           System.err.println(e);
       }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            Connect();
            pst = (PreparedStatement) con.prepareStatement("Select StreetID from Streets Where StreetName = ?");
            pst.setString(1, "null");
            rs = pst.executeQuery();
            if(!rs.next()){
                pst = (PreparedStatement) con.prepareStatement("insert into Streets(StreetName) values(?)");
                pst.setString(1, "null");
                pst.executeUpdate();
            }
            pst = (PreparedStatement) con.prepareStatement("Select FatherID from Fathers Where Name = ?");
            pst.setString(1, "null");
            rs = pst.executeQuery();
            if(!rs.next()){
                pst = (PreparedStatement) con.prepareStatement("insert into Fathers(Name) values(?)");
                pst.setString(1, "null");
                pst.executeUpdate();
            }
            
            sname1 = FixString(Sname1.getText());
            sname2 = FixString(Sname2.getText());
            if(sname1.isEmpty()){
                JOptionPane.showMessageDialog(null,  "رجاء ادخل اسم الخادم", "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(sname2.isEmpty()){
                JOptionPane.showMessageDialog(null, "رجاء ادخل الاسم الثانى ", "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            sname3 = FixString(Sname3.getText());
            sname4 = FixString(Sname4.getText());
            if(Jsdate.getDate() != null)
                sdate = new java.sql.Date(Jsdate.getDate().getTime());
            if(!Sflat.getText().isEmpty())
                sflat = Integer.parseInt(Sflat.getText());
            if(!Sfloor.getText().isEmpty())
                sfloor = Integer.parseInt(Sfloor.getText());
            if(!Shouse.getText().isEmpty())
                shouse = Integer.parseInt(Shouse.getText());
            sstreet1 = Sstreet1.getText();
            sstreet2 = Sstreet2.getText();
            sfather = Sfather.getText();
            if(Sfemale.isSelected()){
                sgender = "Female";
            }else{
                sgender = "Male";
            }
            if(!Sphone.getText().isEmpty())
                sphone = Sphone.getText();
            if(!Smobile.getText().isEmpty())
                smobile = Smobile.getText();
            sfamily1 = (String)Sfamily1.getSelectedItem();
            sfamily2 = (String)Sfamily2.getSelectedItem();
            sjob = FixString(Sjob.getText());
//            education = (String)Education.getSelectedItem();
//            if(!Mnumber.getText().isEmpty())
//                mnumber = Mnumber.getText();
//            mjob = FixString(Mjob.getText());
//            if(!Fnumber.getText().isEmpty())
//                fnumber = Fnumber.getText();
//            fjob = FixString(Fjob.getText());
//            servant = Servant.getText().split("\\s+");
            int stID1 = -1;
            if(sstreet1.isEmpty())
                sstreet1 = "null";
            if(!sstreet1.isEmpty()){
                pst = (PreparedStatement) con.prepareStatement("SELECT StreetID FROM Streets WHERE StreetName = ? LIMIT 1");
                pst.setString(1, sstreet1);
                rs = pst.executeQuery();
                if(rs.next())
                    stID1 = rs.getInt(1);
            }
            int stID2 = -1;
            if(sstreet2.isEmpty())
                sstreet2 = "null";
            if(!sstreet2.isEmpty()){
                pst = (PreparedStatement) con.prepareStatement("SELECT StreetID FROM Streets WHERE StreetName = ? LIMIT 1");
                pst.setString(1, sstreet2);
                rs = pst.executeQuery();
                if(rs.next())
                    stID2 = rs.getInt(1);
            }
            int fatherID = -1;
            if(sfather.isEmpty())
                sfather = "null";
            if(!sfather.isEmpty()){
                pst = (PreparedStatement) con.prepareStatement("SELECT FatherID FROM Fathers WHERE Name = ? LIMIT 1");
                pst.setString(1,sfather);
                rs = pst.executeQuery();
                if(rs.next())
                    fatherID = rs.getInt(1);
            }
            
//            int servantID = 0;
//            if(servant.length == 4){
//                pst = (PreparedStatement) con.prepareStatement("SELECT PersonID FROM Persons WHERE FirstName = ? AND SecondName = ? AND ThirdName = ? AND FourthName = ? LIMIT 1");
//                pst.setString(1, servant[0]);
//                pst.setString(2, servant[1]);
//                pst.setString(3, servant[2]);
//                pst.setString(4, servant[3]);
//                rs = pst.executeQuery();
//                if(rs.next())    
//                    servantID = rs.getInt(1);
//            }else if(servant.length == 3){
//                pst = (PreparedStatement) con.prepareStatement("SELECT PersonID FROM Persons WHERE FirstName = ? AND SecondName = ? AND ThirdName = ? LIMIT 1");
//                pst.setString(1, servant[0]);
//                pst.setString(2, servant[1]);
//                pst.setString(3, servant[2]);
//                rs = pst.executeQuery();
//                if(rs.next())
//                    servantID = rs.getInt(1);
//            }else if(servant.length == 2){
//                pst = (PreparedStatement) con.prepareStatement("SELECT PersonID FROM Persons WHERE FirstName = ? AND SecondName = ? LIMIT 1");
//                pst.setString(1, servant[0]);
//                pst.setString(2, servant[1]);
//                rs = pst.executeQuery();
//                if(rs.next())
//                    servantID = rs.getInt(1);
//            }else{
//                servantID = -1;
//            }
            
            pst = (PreparedStatement) con.prepareStatement("select * from Persons where " + 
                    "FirstName = ? and (Mobile = ?)");
            pst.setString(1, sname1);
            pst.setString(2, mobile);
            rs = pst.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "هذاالخادم  موجود بالفعل يمكنك البحث عنه عن طريق  ID : " + rs.getInt("PersonID"), "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            pst = (PreparedStatement) con.prepareStatement("INSERT INTO Persons(FirstName, SecondName, ThirdName, " + 
                    "FourthName, DOB, ApartmentNumber, FloorNumber, Job, Telephon, Mobile, Gender, FatherID, StreetID, " +
                    "StreetID2, House)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, sname1);
            pst.setString(2, sname2);
            pst.setString(3, sname3);
            pst.setString(4, sname4);
            pst.setDate(5, (java.sql.Date) sdate);
            pst.setInt(6, sflat);
            pst.setInt(7, sfloor);
            pst.setString(8, sjob);
            pst.setString(9, sphone);
            pst.setString(10, smobile);
            pst.setString(11, sgender);
            pst.setInt(12, fatherID);
            pst.setInt(13, stID1);
            pst.setInt(14, stID2);
            pst.setInt(15, shouse);
            
            pst.executeUpdate();
            
            Sname1.setText("");
            Sname2.setText("");
            Sname3.setText("");
            Sname4.setText("");
//            date.setDate(2020);
            Sflat.setText("");
            Sfloor.setText("");
            Sstreet1.setText("");
            Sstreet2.setText("");
            Sfather.setText("");
            buttonGroup2.clearSelection();
            Sphone.setText("");
            Smobile.setText("");
            Sjob.setText("");
            Shouse.setText("");
            
            int servantID = -1;
            rs = pst.executeQuery("Select LAST_INSERT_ID()");
                if(rs.next())
                    servantID = rs.getInt(1); 
            if(servantID != -1){
                pst = (PreparedStatement) con.prepareStatement("INSERT INTO Servants(ServantID) VALUES (?)");
                pst.setInt(1, servantID);
                pst.executeUpdate();
            }
            
            if(sfamily1 != null)
                addDistribution(sfamily1, servantID, 0);
            if(sfamily2 != null)
                addDistribution(sfamily2, servantID, 0);
            
            autoComplete();
            JOptionPane.showMessageDialog(null, "Done!", "Alert", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(notused.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "some thing wronge hapen!", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void HouseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HouseKeyPressed
        // TODO add your handling code here:
        String value = House.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
           House.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
           House.setEditable(false);
        }
    }//GEN-LAST:event_HouseKeyPressed

    private void ShouseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ShouseKeyPressed
        // TODO add your handling code here:
        String value = Shouse.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
           Shouse.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
           Shouse.setEditable(false);
        }
    }//GEN-LAST:event_ShouseKeyPressed

    private void FamilyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FamilyMousePressed
        // TODO add your handling code here:
        getFamilies();
        fillComboBox(Family, families);
    }//GEN-LAST:event_FamilyMousePressed

    private void Sfamily1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sfamily1MousePressed
        // TODO add your handling code here:
        getFamilies();
        fillComboBox(Sfamily1, families);
    }//GEN-LAST:event_Sfamily1MousePressed

    private void Sfamily2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sfamily2MousePressed
        // TODO add your handling code here:
        getFamilies();
        fillComboBox(Sfamily2, families);
    }//GEN-LAST:event_Sfamily2MousePressed

    private void CnameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CnameMousePressed
        // TODO add your handling code here:
        getAssistance();
        fillComboBox(Cname, assistance);
    }//GEN-LAST:event_CnameMousePressed

    private void CfamilyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CfamilyMousePressed
        // TODO add your handling code here:
        getFamilies();
        fillComboBox(Cfamily, families);
    }//GEN-LAST:event_CfamilyMousePressed

    private void Cfamily2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cfamily2MousePressed
        // TODO add your handling code here:
        getFamilies();
        fillComboBox(Cfamily2, families);
    }//GEN-LAST:event_Cfamily2MousePressed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Area;
    private javax.swing.JComboBox<String> Cfamily;
    private javax.swing.JComboBox<String> Cfamily2;
    private javax.swing.JComboBox<String> Cname;
    private javax.swing.JComboBox<String> Education;
    private javax.swing.JComboBox<String> Family;
    private javax.swing.JTextField FamilyName;
    private javax.swing.JTextField Father;
    private javax.swing.JRadioButton Female;
    private javax.swing.JTextField Fjob;
    private javax.swing.JTextField Flat;
    private javax.swing.JTextField Floor;
    private javax.swing.JTextField Fnumber;
    private javax.swing.JTextField Frchurch;
    private javax.swing.JTextField Frname;
    private javax.swing.JTextField Frnumber;
    private javax.swing.JTextField House;
    private com.toedter.calendar.JDateChooser Jdate;
    private javax.swing.JTextField Job;
    private com.toedter.calendar.JDateChooser Jsdate;
    private javax.swing.JTextField Leader;
    private javax.swing.JRadioButton Male;
    private javax.swing.JTextField Mjob;
    private javax.swing.JTextField Mnumber;
    private javax.swing.JTextField Mobile;
    private javax.swing.JTextField Name1;
    private javax.swing.JTextField Name2;
    private javax.swing.JTextField Name3;
    private javax.swing.JTextField Name4;
    private javax.swing.JTextField Phone;
    private javax.swing.JTextField Servant;
    private javax.swing.JComboBox<String> Sfamily1;
    private javax.swing.JComboBox<String> Sfamily2;
    private javax.swing.JTextField Sfather;
    private javax.swing.JRadioButton Sfemale;
    private javax.swing.JTextField Sflat;
    private javax.swing.JTextField Sfloor;
    private javax.swing.JTextField Shouse;
    private javax.swing.JTextField Sjob;
    private javax.swing.JRadioButton Smale;
    private javax.swing.JTextField Smobile;
    private javax.swing.JTextField Sname1;
    private javax.swing.JTextField Sname2;
    private javax.swing.JTextField Sname3;
    private javax.swing.JTextField Sname4;
    private javax.swing.JTextField Sphone;
    private javax.swing.JTextField Sstreet1;
    private javax.swing.JTextField Sstreet2;
    private javax.swing.JTextField Stname;
    private javax.swing.JTextField Stname2;
    private javax.swing.JTextField Street1;
    private javax.swing.JTextField Street2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    // End of variables declaration//GEN-END:variables
}
