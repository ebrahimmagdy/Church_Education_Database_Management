/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package church_education;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ebrahim
 */
public class EditServant extends javax.swing.JFrame {

    /**
     * Creates new form EditServant
     */
    LogIn login = new LogIn();
    Connection con = login.con;
    Persons person = new Persons(con);
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs, temprs;
    
    private JTable table;
    private int r;
    
    private String check = "";
    private String frname = "";
    private String frnumber = "";
    private String frchurch = "";
    private String stname = "";
    private String stname2 = "";
    private String area = "";
    
    private int pID;
    private String name1 = "";
    private String name2 = "";
    private String name3 = "";
    private String name4 = "";
    private java.sql.Date date;
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
    private String servant = "";
    
    private ArrayList<String> streetsnames = new ArrayList<>();
    private ArrayList<String> areasnames = new ArrayList<>();
    private ArrayList<String> servantsnames = new ArrayList<>();
    private ArrayList<String> fathers = new ArrayList<>();
    private ArrayList<String> families = new ArrayList<>();
    private ArrayList<String> assistance = new ArrayList<>();
    private ArrayList<String> educations = new ArrayList<>();
    private AutoComplete2 ac = new AutoComplete2();
    
    public EditServant(int ID) {
        initComponents();
        buttonGroup1.add(Male);
        buttonGroup1.add(Female);
        this.pID = ID;
        getInformation(pID);
        fill();
        
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
        person.fillComboBox(Education, educations);
        
    }
    
    private void getInformation(int pID){
        try {
//            pst = (PreparedStatement) (Statement) con.prepareStatement("Select * from Persons where PersonID = ? LIMIT 1");
//            pst.setInt(1, pID);
//            rs = pst.executeQuery();                                
//            while(rs.next()){
//                String frname2, scname, thname, foname, servantName = null, fatherName = null, familyName = null, listName = null, st1 = null, st2 = null;
//                pst = (PreparedStatement) con.prepareStatement("select * from Streets Where StreetID = ? LIMIT 1");
//                pst.setInt(1, rs.getInt("StreetID"));
//                temprs = pst.executeQuery();
//                while(temprs.next()){
//                    st1 = temprs.getString("StreetName");
//                    st2 = temprs.getString("StreetName2");
//                }
//                pst = (PreparedStatement) con.prepareStatement("SELECT FirstName, SecondName, ThirdName, FourthName\n"+
//                     "from Persons\n" + 
//                     "Inner join Lists where Persons.PersonID = Lists.ServantID and Lists.ServedID = ? LIMIT 1");
//                pst.setInt(1, rs.getInt("PersonID"));
//                temprs = pst.executeQuery();
//                while(temprs.next()){
//                    frname2 = temprs.getString("FirstName");
//                    scname = temprs.getString("SecondName");
//                    thname = temprs.getString("ThirdName");
//                    foname = temprs.getString("FourthName");
//                    servantName = frname2 + " " + scname + " " + thname + " " + foname;
//                }
//                pst = (PreparedStatement) con.prepareStatement("SELECT ListName \n"+
//                     "from Lists\n" + 
//                     "where ServedID = ?  LIMIT 1");
//                pst.setInt(1, rs.getInt("PersonID"));
//                temprs = pst.executeQuery();
//                while(temprs.next()){
//                    listName = temprs.getString("ListName");
//                }
//                pst = (PreparedStatement) con.prepareStatement("SELECT * \n"+
//                     "from Families\n" + 
//                     "Inner join Distribution where Families.FamilyID = Distribution.FamilyID and Distribution.PersonID = ? and Distribution.Role = \"Student\" LIMIT 1");
//                pst.setInt(1, rs.getInt("PersonID"));
//                temprs = pst.executeQuery();
//                while(temprs.next()){
//                    familyName = temprs.getString("FamilyName");
//                }
//                pst = (PreparedStatement) con.prepareStatement("SELECT Name \n"+
//                     "from Fathers\n" + 
//                     "Inner join Persons where Fathers.FatherID = Persons.FatherID and Persons.PersonID = ? LIMIT 1");
//                pst.setInt(1, rs.getInt("PersonID"));
//                temprs = pst.executeQuery();
//                while(temprs.next()){
//                    fatherName = temprs.getString("Name");
//                }
//                name1 = rs.getString("FirstName");
//                name2 = rs.getString("SecondName");
//                name3 = rs.getString("ThirdName");
//                name4 = rs.getString("FourthName");
//                date = rs.getDate("DOB");
//                flat = rs.getInt("ApartmentNumber");
//                floor = rs.getInt("FloorNumber");
//                house = rs.getInt("House");
//                this.street1 = st1;
//                this.street2 = st2;
//                mobile = rs.getString("Mobile");
//                mnumber = rs.getString("MotherMobile");
//                frnumber = rs.getString("FatherMobile");
//                phone = rs.getString("Telephon");
//                fjob = rs.getString("FatherJob");
//                mjob = rs.getString("MotherJob");
//                job = rs.getString("Job");
//                education = rs.getString("EducationalLevel");
//                this.servant = servantName;
//                this.father = fatherName;
//                this.family = familyName;
//                gender = rs.getString("Gender");
                
//                name1 = (String) table.getValueAt(r, 2);
//                name2 = (String) table.getValueAt(r, 3);
//                name3 = (String) table.getValueAt(r, 4);
//                name4 = (String) table.getValueAt(r, 5);
//                date = (Date) new SimpleDateFormat("yyy-mm-dd").parse((String) table.getValueAt(r, 6));
//                flat = (int) table.getValueAt(r, 7);
//                floor = (int) table.getValueAt(r, 8);
//                house = (int) table.getValueAt(r, 9);
//                this.street1 = (String) table.getValueAt(r, 10);
//                this.street2 = (String) table.getValueAt(r, 11);
//                mobile = (String) table.getValueAt(r, 12);
//                mnumber = (String) table.getValueAt(r, 13);
//                frnumber = (String) table.getValueAt(r, 14);
//                phone = (String) table.getValueAt(r, 15);
//                fjob = (String) table.getValueAt(r, 16);
//                mjob = (String) table.getValueAt(r, 17);
//                job = (String) table.getValueAt(r, 18);
//                education = (String) table.getValueAt(r, 19);
//                this.servant = (String) table.getValueAt(r, 20);
//                this.father = (String) table.getValueAt(r, 21);
//                this.family = (String) table.getValueAt(r, 22);
//                gender = (String) table.getValueAt(r, 23);
                
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
                pst.setInt(1, pID);
                rs = pst.executeQuery();
                while(rs.next()){
                    name1 = rs.getString("FirstName");
                    name2 = rs.getString("SecondName");
                    name3 = rs.getString("ThirdName");
                    name4 = rs.getString("FourthName");
                    date = rs.getDate("DOB");
                    flat = rs.getInt("ApartmentNumber");
                    floor = rs.getInt("FloorNumber");
                    house = rs.getInt("House");
                    this.street1 = rs.getString("street1");
                    this.street2 = rs.getString("street2");
                    mobile = rs.getString("Mobile");
                    phone = rs.getString("Telephon");
                    job = rs.getString("Job");
                    education = rs.getString("EducationalLevel");
                    this.father = rs.getString("frName");
                    gender = rs.getString("Gender");
                }
//            }
        } catch (Exception ex) {
            Logger.getLogger(EditServant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fill(){
        Name1.setText(name1);
        Name2.setText(name2);
        Name3.setText(name3);
        Name4.setText(name4);
        jDateChooser1.setDate(date);
        Flat.setText(Integer.toString(flat));
        Floor.setText(Integer.toString(floor));
        House.setText(Integer.toString(house));
        if(flat == 0){
            Flat.setText("");
        }
        if(floor == 0){
            Floor.setText("");
        }
        if(house == 0){
            House.setText("");
        }
        Street1.setText(street1);
        Street2.setText(street2);
        Phone.setText(phone);
        Mobile.setText(mobile);
        Education.setSelectedItem(education);
        Father.setText(father);
        Job.setText(job);
        if(gender.equals("Male")){
            Male.setSelected(true);
        }else if(gender.equals("Female")){
            Female.setSelected(true);
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
        jPanel1 = new javax.swing.JPanel();
        Name1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Name2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Name3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Name4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Flat = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Floor = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        House = new javax.swing.JTextField();
        Phone = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Mobile = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Father = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        Street1 = new javax.swing.JTextField();
        Street2 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Education = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        Male = new javax.swing.JRadioButton();
        Female = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        Job = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));

        Name1.setFont(Name1.getFont().deriveFont(Name1.getFont().getSize()+4f));
        Name1.setToolTipText("");

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getSize()+4f));
        jLabel2.setText("الاسم الاول");

        Name2.setFont(Name2.getFont().deriveFont(Name2.getFont().getSize()+4f));
        Name2.setToolTipText("");

        jLabel3.setFont(jLabel3.getFont().deriveFont(jLabel3.getFont().getSize()+4f));
        jLabel3.setText("الاسم الثانى");

        Name3.setFont(Name3.getFont().deriveFont(Name3.getFont().getSize()+4f));
        Name3.setToolTipText("");

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getSize()+4f));
        jLabel4.setText("الاسم الثالث");

        Name4.setFont(Name4.getFont().deriveFont(Name4.getFont().getSize()+4f));
        Name4.setToolTipText("");

        jLabel5.setFont(jLabel5.getFont().deriveFont(jLabel5.getFont().getSize()+4f));
        jLabel5.setText("الاسم الرابع");

        jLabel6.setFont(jLabel6.getFont().deriveFont(jLabel6.getFont().getSize()+4f));
        jLabel6.setText("رقم المنزل");

        jLabel7.setFont(jLabel7.getFont().deriveFont(jLabel7.getFont().getSize()+4f));
        jLabel7.setText("تاريخ الميلاد");

        Flat.setFont(Flat.getFont().deriveFont(Flat.getFont().getSize()+4f));
        Flat.setToolTipText("");
        Flat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FlatKeyPressed(evt);
            }
        });

        jLabel8.setFont(jLabel8.getFont().deriveFont(jLabel8.getFont().getSize()+4f));
        jLabel8.setText("رقم الشقة");

        Floor.setFont(Floor.getFont().deriveFont(Floor.getFont().getSize()+4f));
        Floor.setToolTipText("");
        Floor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FloorKeyPressed(evt);
            }
        });

        jLabel9.setFont(jLabel9.getFont().deriveFont(jLabel9.getFont().getSize()+4f));
        jLabel9.setText("رقم الدور");

        House.setFont(House.getFont().deriveFont(House.getFont().getSize()+4f));
        House.setToolTipText("");
        House.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HouseKeyPressed(evt);
            }
        });

        Phone.setFont(Phone.getFont().deriveFont(Phone.getFont().getSize()+4f));
        Phone.setToolTipText("");
        Phone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PhoneKeyPressed(evt);
            }
        });

        jLabel11.setFont(jLabel11.getFont().deriveFont(jLabel11.getFont().getSize()+4f));
        jLabel11.setText("التيليفون الارضى");

        jLabel12.setFont(jLabel12.getFont().deriveFont(jLabel12.getFont().getSize()+4f));
        jLabel12.setText("المستوى الدراسى");

        Mobile.setFont(Mobile.getFont().deriveFont(Mobile.getFont().getSize()+4f));
        Mobile.setToolTipText("");
        Mobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MobileKeyPressed(evt);
            }
        });

        jLabel15.setFont(jLabel15.getFont().deriveFont(jLabel15.getFont().getSize()+4f));
        jLabel15.setText("رقم الموبايل");

        jLabel18.setFont(jLabel18.getFont().deriveFont(jLabel18.getFont().getSize()+4f));
        jLabel18.setText("اب الاعتراف");

        Father.setFont(Father.getFont().deriveFont(Father.getFont().getSize()+4f));
        Father.setToolTipText("");
        Father.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                FatherMouseEntered(evt);
            }
        });

        jLabel19.setFont(jLabel19.getFont().deriveFont(jLabel19.getFont().getSize()+4f));
        jLabel19.setText("اسم الشارع");

        Street1.setFont(Street1.getFont().deriveFont(Street1.getFont().getSize()+4f));
        Street1.setToolTipText("");
        Street1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Street1MouseEntered(evt);
            }
        });

        Street2.setFont(Street2.getFont().deriveFont(Street2.getFont().getSize()+4f));
        Street2.setToolTipText("");
        Street2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Street2MouseEntered(evt);
            }
        });

        jLabel21.setFont(jLabel21.getFont().deriveFont(jLabel21.getFont().getSize()+4f));
        jLabel21.setText("متفرع من");

        jLabel22.setFont(jLabel22.getFont().deriveFont(jLabel22.getFont().getSize()+4f));
        jLabel22.setText("النوع");

        jButton1.setFont(jButton1.getFont().deriveFont(jButton1.getFont().getStyle() | java.awt.Font.BOLD, jButton1.getFont().getSize()+3));
        jButton1.setText("حفظ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Education.setBackground(new java.awt.Color(253, 253, 253));
        Education.setToolTipText("");

        Male.setFont(Male.getFont().deriveFont(Male.getFont().getSize()+4f));
        Male.setText("ذكر");
        Male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaleActionPerformed(evt);
            }
        });

        Female.setFont(Female.getFont().deriveFont(Female.getFont().getSize()+4f));
        Female.setText("انثى");
        Female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FemaleActionPerformed(evt);
            }
        });

        jLabel23.setFont(jLabel23.getFont().deriveFont(jLabel23.getFont().getSize()+4f));
        jLabel23.setText("العمل");

        Job.setFont(Job.getFont().deriveFont(Job.getFont().getSize()+4f));
        Job.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Phone, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Mobile, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(Female)
                        .addGap(39, 39, 39)
                        .addComponent(Male)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Education, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Father, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Job, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Street1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Street2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Name1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Name2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Name3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Name4, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(House, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Flat, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Floor, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Name1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Name2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Phone)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Mobile)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Name3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Name4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Education, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Father)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Job)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Flat)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Floor)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(House)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Street1)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Street2)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(Male)
                            .addComponent(Female))))
                .addGap(38, 38, 38)
                .addComponent(jButton1)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 726, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 527, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FlatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FlatKeyPressed
        // TODO add your handling code here:
        String value = Flat.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            Flat.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
            Flat.setEditable(false);
        }
    }//GEN-LAST:event_FlatKeyPressed

    private void FloorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FloorKeyPressed
        // TODO add your handling code here:
        String value = Floor.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            Floor.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
            Floor.setEditable(false);
        }
    }//GEN-LAST:event_FloorKeyPressed

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

    private void PhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PhoneKeyPressed
        // TODO add your handling code here:
        String value = Phone.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            Phone.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
            Phone.setEditable(false);
        }
    }//GEN-LAST:event_PhoneKeyPressed

    private void MobileKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MobileKeyPressed
        // TODO add your handling code here:
        String value = Mobile.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            Mobile.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
            Mobile.setEditable(false);
        }
    }//GEN-LAST:event_MobileKeyPressed

    private void FatherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FatherMouseEntered
        try {
            // TODO add your handling code here:
            fathers = person.getFathers();
            ac.autoComplete(Father, fathers);
        } catch (SQLException ex) {
            Logger.getLogger(EditServant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_FatherMouseEntered

    private void Street1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Street1MouseEntered
        try {
            // TODO add your handling code here:
            streetsnames = person.getStreetsNames();
            ac.autoComplete(Street1, streetsnames);
        } catch (SQLException ex) {
            Logger.getLogger(EditServant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Street1MouseEntered

    private void Street2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Street2MouseEntered
        try {
            // TODO add your handling code here:
            streetsnames = person.getStreetsNames();
            ac.autoComplete(Street2, streetsnames);
        } catch (SQLException ex) {
            Logger.getLogger(EditServant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Street2MouseEntered

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            name1 = person.FixString(Name1.getText());
            name2 = person.FixString(Name2.getText());
            if(name1.isEmpty()){
                JOptionPane.showMessageDialog(null,  "رجاء ادخل اسم المخدوم", "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(name2.isEmpty()){
                JOptionPane.showMessageDialog(null, "رجاء ادخل الاسم الثانى ", "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            name3 = person.FixString(Name3.getText());
            name4 = person.FixString(Name4.getText());
            if(jDateChooser1.getDate() != null)
            date = new java.sql.Date(jDateChooser1.getDate().getTime());
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
            }else{
                gender = "Male";
            }
            if(!Phone.getText().isEmpty())
            phone = Phone.getText();
            if(!Mobile.getText().isEmpty())
            mobile = Mobile.getText();
            job = Job.getText();
            education = (String)Education.getSelectedItem();
            String ser[];
            int stID1 = -1;
            if(!street1.isEmpty()){
                pst = (PreparedStatement) con.prepareStatement("SELECT StreetID FROM Streets WHERE StreetName = ? LIMIT 1");
                pst.setString(1, street1);
                rs = pst.executeQuery();
                if(rs.next())
                stID1 = rs.getInt(1);
            }
            int stID2 = -1;
            if(!street2.isEmpty()){
                pst = (PreparedStatement) con.prepareStatement("SELECT StreetID FROM Streets WHERE StreetName = ? LIMIT 1");
                pst.setString(1, street2);
                rs = pst.executeQuery();
                if(rs.next())
                stID2 = rs.getInt(1);
            }
            int fatherID = -1;
            if(!father.isEmpty()){
                pst = (PreparedStatement) con.prepareStatement("SELECT FatherID FROM Fathers WHERE Name = ? LIMIT 1");
                pst.setString(1,father);
                rs = pst.executeQuery();
                if(rs.next())
                fatherID = rs.getInt(1);
            }
            
            pst = (PreparedStatement) con.prepareStatement("select * from Persons where " +
                "FirstName = ? and (Mobile = ? or Telephon = ?)");
            pst.setString(1, name1);
            pst.setString(2, mobile);
            pst.setString(3, phone);
            rs = pst.executeQuery();
            if(rs.next() && rs.getInt("PersonID") != pID){
                JOptionPane.showMessageDialog(null, "هذا الخادم موجود بالفعل يمكنك البحث عنه عن طريق ال ID " + rs.getInt("PersonID"), "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }

            pst = (PreparedStatement) con.prepareStatement("UPDATE Persons \n" +
                "SET \n" +
                "FirstName = ?, SecondName = ?, ThirdName = ?, " +
                "FourthName = ?, DOB = ?, ApartmentNumber = ?, FloorNumber = ?, Job = ?, Telephon = ?, Mobile = ?, Gender = ?, FatherID = ?, StreetID = ?, " +
                "StreetID2 = ?, EducationalLevel = ?, House = ?" +
                " WHERE\n" +
                "PersonID = ? ;");
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
            if(father.isEmpty())
            pst.setString(12, null);
            else
            pst.setInt(12, fatherID);
            if(street1.isEmpty())
            pst.setString(13, null);
            else
            pst.setInt(13, stID1);
            if(street2.isEmpty())
            pst.setString(14, null);
            else
            pst.setInt(14, stID2);
            pst.setString(15, education);
            pst.setInt(16, house);
            pst.setInt(17, pID);
            System.out.println(pst);
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
            Education.setSelectedItem(null);
            jDateChooser1.setDate(null);

            JOptionPane.showMessageDialog(null, "Done!", "Alert", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(EditServant.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "some thing wronge hapen!", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void MaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MaleActionPerformed

    private void FemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FemaleActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Education;
    private javax.swing.JTextField Father;
    private javax.swing.JRadioButton Female;
    private javax.swing.JTextField Flat;
    private javax.swing.JTextField Floor;
    private javax.swing.JTextField House;
    private javax.swing.JTextField Job;
    private javax.swing.JRadioButton Male;
    private javax.swing.JTextField Mobile;
    private javax.swing.JTextField Name1;
    private javax.swing.JTextField Name2;
    private javax.swing.JTextField Name3;
    private javax.swing.JTextField Name4;
    private javax.swing.JTextField Phone;
    private javax.swing.JTextField Street1;
    private javax.swing.JTextField Street2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
