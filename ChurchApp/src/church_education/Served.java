/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package church_education;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.placeholder.PlaceHolder;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ebrahim
 */
public class Served extends javax.swing.JPanel {

    /**
     * Creates new form served
     */
    LogIn login  = new LogIn();
    Connection con = login.con;
    Persons person = new Persons(con);
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs, temprs;
    ResultSet streetsResult;
    ResultSet fathersResult;
    ResultSet servantsResult;
    
    private PlaceHolder holder;
    private String id = "";        
    private String name1 = "";
    private String name2 = "";
    private String name3 = "";
    private String name4 = "";
    private java.sql.Date date1;
    private java.sql.Date date2;
    private String flat = "";
    private String floor = "";
    private String house = "";
    private String street1 = "";
    private String street2 = "";
    private String area = "";
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
    private String listname = "";
    
    private ArrayList<String> streetsnames = new ArrayList<>();
    private ArrayList<String> areasnames = new ArrayList<>();
    private ArrayList<String> servantsnames = new ArrayList<>();
    private ArrayList<String> fathers = new ArrayList<>();
    private ArrayList<String> families = new ArrayList<>();
    private ArrayList<String> assistance = new ArrayList<>();
    private ArrayList<String> educations = new ArrayList<>();
    private AutoComplete2 ac = new AutoComplete2();
    
    Object[] raw;
    
    
    public Served() {
        initComponents();
        init();
    }
    
    public void holders(){
        ID.setText(null);
        Name1.setText(null);
        Name2.setText(null);
        Name3.setText(null);
        Name4.setText(null);
        House.setText(null);
        Street1.setText(null);
        Street2.setText(null);
        Area.setText(null);
        Phone.setText(null);
        Mobile.setText(null);
        Mnumber.setText(null);
        Fnumber.setText(null);
        Father.setText(null);
        Servant.setText(null);
        Education.setSelectedItem(null);
        Family.setSelectedItem(null);
        Gender.setSelectedItem(null);
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        
        holder = new PlaceHolder(ID, "ID");
        holder = new PlaceHolder(Name1, "الاسم الاول");
        holder = new PlaceHolder(Name2, "الاسم الثانى");
        holder = new PlaceHolder(Name3, "الاسم الثالث");
        holder = new PlaceHolder(Name4, "الاسم الرابع");
        holder = new PlaceHolder(House, "رقم المنزل");
        holder = new PlaceHolder(Street1, "اسم الشارع");
        holder = new PlaceHolder(Street2, "متفرع من");
        holder = new PlaceHolder(Area, "المنطقة");
        holder = new PlaceHolder(Phone, "تليفون المنزل");
        holder = new PlaceHolder(Mobile, "موبايل المخدوم");
        holder = new PlaceHolder(Mnumber, "موبايل الام");
        holder = new PlaceHolder(Fnumber, "موبايل الاب");
        holder = new PlaceHolder(Father, "اب الاعتراف");
        holder = new PlaceHolder(Servant, "الخادم");
    }
    
    public void insertAll(){
        try {
            st = (Statement) con.createStatement();
            rs = st.executeQuery("SELECT * FROM Persons p WHERE NOT EXISTS (SELECT null FROM Servants s WHERE s.ServantID = p.PersonID)");                                
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.getDataVector().removeAllElements();
            while(rs.next()){
                pst = (PreparedStatement) con.prepareStatement("select * from Streets Where StreetID = ?");
                pst.setInt(1, rs.getInt("StreetID"));
                streetsResult = pst.executeQuery();
                String frname, scname, thname, foname, servantName = null, fatherName = null, familyName = null, listName = null;
                pst = (PreparedStatement) con.prepareStatement("SELECT FirstName, SecondName, ThirdName, FourthName\n"+
                     "from Persons\n" + 
                     "Inner join Lists where Persons.PersonID = Lists.ServantID and Lists.ServedID = ? LIMIT 1");
                pst.setInt(1, rs.getInt("PersonID"));
                temprs = pst.executeQuery();
                while(temprs.next()){
                    frname = temprs.getString("FirstName");
                    scname = temprs.getString("SecondName");
                    thname = temprs.getString("ThirdName");
                    foname = temprs.getString("FourthName");
                    servantName = frname + " " + scname + " " + thname + " " + foname;
                }
                pst = (PreparedStatement) con.prepareStatement("SELECT ListName \n"+
                     "from Lists\n" + 
                     "where ServedID = ?  LIMIT 1");
                pst.setInt(1, rs.getInt("PersonID"));
                temprs = pst.executeQuery();
                while(temprs.next()){
                    listName = temprs.getString("ListName");
                }
                pst = (PreparedStatement) con.prepareStatement("SELECT * \n"+
                     "from Families\n" + 
                     "Inner join Distribution where Families.FamilyID = Distribution.FamilyID and Distribution.PersonID = ? and Distribution.Role = \"Student\" LIMIT 1");
                pst.setInt(1, rs.getInt("PersonID"));
                temprs = pst.executeQuery();
                while(temprs.next()){
                    familyName = temprs.getString("FamilyName");
                }
                pst = (PreparedStatement) con.prepareStatement("SELECT Name \n"+
                     "from Fathers\n" + 
                     "Inner join Persons where Fathers.FatherID = Persons.FatherID and Persons.PersonID = ? LIMIT 1");
                pst.setInt(1, rs.getInt("PersonID"));
                temprs = pst.executeQuery();
                while(temprs.next()){
                    fatherName = temprs.getString("Name");
                }
                raw = new Object[24];
                raw[0] = false;
                raw[1] = rs.getInt("PersonID");
                raw[2] = rs.getString("FirstName");
                raw[3] = rs.getString("SecondName");
                raw[4] = rs.getString("ThirdName");
                raw[5] = rs.getString("FourthName");
                raw[6] = rs.getDate("DOB");
                raw[7] = rs.getInt("ApartmentNumber");
                raw[8] = rs.getInt("FloorNumber");
                raw[9] = rs.getInt("House");
                while(streetsResult.next()){
                    raw[10] = streetsResult.getString("StreetName");
                    raw[11] = streetsResult.getString("StreetName2");
                }
                raw[12] = rs.getString("Mobile");
                raw[13] = rs.getString("MotherMobile");
                raw[14] = rs.getString("FatherMobile");
                raw[15] = rs.getString("Telephon");
                raw[16] = rs.getString("FatherJob");
                raw[17] = rs.getString("MotherJob");
                raw[18] = rs.getString("Job");
                raw[19] = rs.getString("EducationalLevel");
                raw[20] = servantName;
                raw[21] = fatherName;
                raw[22] = familyName;
                raw[23] = rs.getString("Gender");
                for(int i = 1; i < 24; i++){
                    if(raw[i] == null || raw[i].toString().equals("null") || raw[i].toString().equals(Integer.toString(0)) ){
                        raw[i] = "";
                    }
                }
                model.addRow(raw);
            }
            jTable1.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Served.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        Name1 = new javax.swing.JTextField();
        Name2 = new javax.swing.JTextField();
        Name3 = new javax.swing.JTextField();
        Street2 = new javax.swing.JTextField();
        Street1 = new javax.swing.JTextField();
        House = new javax.swing.JTextField();
        Mobile = new javax.swing.JTextField();
        Mnumber = new javax.swing.JTextField();
        Phone = new javax.swing.JTextField();
        Servant = new javax.swing.JTextField();
        Father = new javax.swing.JTextField();
        Name4 = new javax.swing.JTextField();
        Area = new javax.swing.JTextField();
        Fnumber = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        Family = new javax.swing.JComboBox<>();
        Gender = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Education = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        ID = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 240));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));

        Name1.setFont(Name1.getFont().deriveFont(Name1.getFont().getSize()+3f));

        Name2.setFont(Name2.getFont().deriveFont(Name2.getFont().getSize()+3f));

        Name3.setFont(Name3.getFont().deriveFont(Name3.getFont().getSize()+3f));

        Street2.setFont(Street2.getFont().deriveFont(Street2.getFont().getSize()+3f));
        Street2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Street2MouseEntered(evt);
            }
        });

        Street1.setFont(Street1.getFont().deriveFont(Street1.getFont().getSize()+3f));
        Street1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Street1MouseEntered(evt);
            }
        });
        Street1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Street1ActionPerformed(evt);
            }
        });

        House.setFont(House.getFont().deriveFont(House.getFont().getSize()+3f));
        House.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HouseKeyPressed(evt);
            }
        });

        Mobile.setFont(Mobile.getFont().deriveFont(Mobile.getFont().getSize()+3f));
        Mobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MobileActionPerformed(evt);
            }
        });
        Mobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MobileKeyPressed(evt);
            }
        });

        Mnumber.setFont(Mnumber.getFont().deriveFont(Mnumber.getFont().getSize()+3f));
        Mnumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MnumberKeyPressed(evt);
            }
        });

        Phone.setFont(Phone.getFont().deriveFont(Phone.getFont().getSize()+3f));
        Phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhoneActionPerformed(evt);
            }
        });
        Phone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PhoneKeyPressed(evt);
            }
        });

        Servant.setFont(Servant.getFont().deriveFont(Servant.getFont().getSize()+3f));
        Servant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ServantMouseEntered(evt);
            }
        });

        Father.setFont(Father.getFont().deriveFont(Father.getFont().getSize()+3f));
        Father.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                FatherMouseEntered(evt);
            }
        });
        Father.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FatherActionPerformed(evt);
            }
        });

        Name4.setFont(Name4.getFont().deriveFont(Name4.getFont().getSize()+3f));
        Name4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Name4ActionPerformed(evt);
            }
        });

        Area.setFont(Area.getFont().deriveFont(Area.getFont().getSize()+3f));
        Area.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AreaMouseEntered(evt);
            }
        });

        Fnumber.setFont(Fnumber.getFont().deriveFont(Fnumber.getFont().getSize()+3f));
        Fnumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FnumberKeyPressed(evt);
            }
        });

        Search.setFont(Search.getFont().deriveFont(Search.getFont().getStyle() | java.awt.Font.BOLD, Search.getFont().getSize()+3));
        Search.setText("بحث");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        Family.setFont(Family.getFont().deriveFont(Family.getFont().getSize()+3f));
        Family.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                FamilyMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FamilyMousePressed(evt);
            }
        });

        Gender.setFont(Gender.getFont().deriveFont(Gender.getFont().getSize()+3f));
        Gender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                GenderMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GenderMousePressed(evt);
            }
        });
        Gender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenderActionPerformed(evt);
            }
        });

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getSize()+3f));
        jLabel1.setText("تاريخ الميلاد");

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getSize()+3f));
        jLabel2.setText("من");

        jLabel3.setFont(jLabel3.getFont().deriveFont(jLabel3.getFont().getSize()+3f));
        jLabel3.setText("الى");

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getSize()+3f));
        jLabel4.setText("النوع");

        jLabel5.setFont(jLabel5.getFont().deriveFont(jLabel5.getFont().getSize()+3f));
        jLabel5.setText("الاسرة");

        Education.setFont(Education.getFont().deriveFont(Education.getFont().getSize()+3f));

        jLabel6.setFont(jLabel6.getFont().deriveFont(jLabel6.getFont().getSize()+3f));
        jLabel6.setText("المرحلة الدراسية");

        jButton3.setFont(jButton3.getFont().deriveFont(jButton3.getFont().getStyle() | java.awt.Font.BOLD, jButton3.getFont().getSize()+3));
        jButton3.setText("الكل");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        ID.setFont(ID.getFont().deriveFont(ID.getFont().getSize()+3f));
        ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDActionPerformed(evt);
            }
        });
        ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IDKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(Gender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(Education, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(Family, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5))
                            .addComponent(Father)
                            .addComponent(Servant))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Mobile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Mnumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Phone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Fnumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Street2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Street1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(House, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Area, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Name1)
                            .addComponent(Name2)
                            .addComponent(Name3)
                            .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Name4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(House, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(Street1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Street2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Area, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Phone, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Servant, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Mobile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Father, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Mnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Education, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Fnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Family, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(Name1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Name2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Name3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Search, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Name4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 270));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(79, 8, 8), 5, true));

        jButton1.setText("تعديل");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("حذف");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("عمل قائمة");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("اضافة");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("refresh");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("اضافة الى اسرة");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jButton5)
                .addGap(37, 37, 37)
                .addComponent(jButton4)
                .addGap(39, 39, 39)
                .addComponent(jButton7)
                .addGap(38, 38, 38)
                .addComponent(jButton1)
                .addGap(37, 37, 37)
                .addComponent(jButton2)
                .addGap(36, 36, 36)
                .addComponent(jButton6)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 210, 400));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "اختار", "ID", "الاسم", "الاسم الثانى", "الاسم الثالث", "الاسم الرابع", "تاريخ الميلاد", "رقم الشقة", "رقم الدور", "رقم المنزل", "اسم الشارع", "متفرع من", "الموبايل", "موبايل الام", "موبايل الاب", "التليفون الارضى", "وظيفة الاب", "وظيفة الام", "العمل", "المستوى الدراسى", "الخادم", "اب الاعتراف", "الاسرة", "النوع"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setResizable(false);
            jTable1.getColumnModel().getColumn(8).setResizable(false);
            jTable1.getColumnModel().getColumn(9).setResizable(false);
            jTable1.getColumnModel().getColumn(10).setResizable(false);
            jTable1.getColumnModel().getColumn(11).setResizable(false);
            jTable1.getColumnModel().getColumn(12).setResizable(false);
            jTable1.getColumnModel().getColumn(13).setResizable(false);
            jTable1.getColumnModel().getColumn(14).setResizable(false);
            jTable1.getColumnModel().getColumn(15).setResizable(false);
            jTable1.getColumnModel().getColumn(16).setResizable(false);
            jTable1.getColumnModel().getColumn(17).setResizable(false);
            jTable1.getColumnModel().getColumn(18).setResizable(false);
            jTable1.getColumnModel().getColumn(19).setResizable(false);
            jTable1.getColumnModel().getColumn(20).setResizable(false);
            jTable1.getColumnModel().getColumn(21).setResizable(false);
            jTable1.getColumnModel().getColumn(22).setResizable(false);
            jTable1.getColumnModel().getColumn(23).setResizable(false);
        }

        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 840, 400));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int r = jTable1.getRowCount();
        for(int i = 0; i < r; i++){
            if((boolean)jTable1.getValueAt(i, 0)){
                System.out.println((int)jTable1.getValueAt(i, 1));
                EditServed es = new EditServed((int)jTable1.getValueAt(i, 1));
                es.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                es.setVisible(true);
            }
        }
        SearchActionPerformed(evt);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Street1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Street1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Street1ActionPerformed

    private void PhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoneActionPerformed

    private void MobileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MobileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MobileActionPerformed

    private void GenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GenderActionPerformed

    private void Street1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Street1MouseEntered
        try {
            // TODO add your handling code here:
            streetsnames = person.getStreetsNames();
            ac.autoComplete(Street1, streetsnames);
        } catch (SQLException ex) {
            Logger.getLogger(Served.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Street1MouseEntered

    private void Street2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Street2MouseEntered
        try {
            // TODO add your handling code here:
            streetsnames = person.getStreetsNames();
            ac.autoComplete(Street2, streetsnames);
        } catch (SQLException ex) {
            Logger.getLogger(Served.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Street2MouseEntered

    private void AreaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AreaMouseEntered
        try {
            // TODO add your handling code here:
            areasnames = person.getAreasNames();
            ac.autoComplete(Area, areasnames);
        } catch (SQLException ex) {
            Logger.getLogger(Served.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_AreaMouseEntered

    private void FatherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FatherMouseEntered
        try {
            // TODO add your handling code here:
            fathers = person.getFathers();
            ac.autoComplete(Father, fathers);
        } catch (SQLException ex) {
            Logger.getLogger(Served.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_FatherMouseEntered

    private void ServantMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ServantMouseEntered
        try {
            // TODO add your handling code here:
            servantsnames = person.getServantsNames();
            ac.autoComplete(Servant, servantsnames);
        } catch (SQLException ex) {
            Logger.getLogger(Served.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ServantMouseEntered

    private void FamilyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FamilyMouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_FamilyMouseEntered

    private void GenderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GenderMouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_GenderMouseEntered

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

    private void MnumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MnumberKeyPressed
        // TODO add your handling code here:
        String value = Mnumber.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
           Mnumber.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
           Mnumber.setEditable(false);
        }
    }//GEN-LAST:event_MnumberKeyPressed

    private void FnumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FnumberKeyPressed
        // TODO add your handling code here:
        String value = Fnumber.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
           Fnumber.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
           Fnumber.setEditable(false);
        }
    }//GEN-LAST:event_FnumberKeyPressed

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

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        ArrayList<String> col = new ArrayList<String>();
        String cols = "";
        String values = "";
        boolean f = false;
        id = ID.getText();
        name1 = Name1.getText();
        name2 = Name2.getText();
        name3 = Name3.getText();
        name4 = Name4.getText();
        house = House.getText();
        street1 = Street1.getText();
        street2 = Street2.getText();
        area = Area.getText();
        phone = Phone.getText();
        mobile = Mobile.getText();
        mnumber = Mnumber.getText();
        fnumber = Fnumber.getText();
        servant = Servant.getText();
        father = Father.getText();
        education = (String)Education.getSelectedItem();
        family = (String)Family.getSelectedItem();
        gender = (String)Gender.getSelectedItem();
        if(jDateChooser1.getDate() != null)
            date1 = new java.sql.Date(jDateChooser1.getDate().getTime());
        if(jDateChooser2.getDate() != null)
            date2 = new java.sql.Date(jDateChooser2.getDate().getTime());
        if(!id.equals("ID")){
            values += " and PersonID = " +  id + " ";
        }
        if(!name1.equals("الاسم الاول")){
            name1 = person.FixString(name1);
            values += " and FirstName = " + "\"" + name1 + "\" ";
        }
        if(!name2.equals("الاسم الثانى")){
            name2 = person.FixString(name2);
            values += " and SecondName = " + "\"" + name2 + "\" ";
        }
        if(!name3.equals("الاسم الثالث")){
            name3 = person.FixString(name3);
            values += " and ThirdName = " + "\"" + name3 + "\" ";
        }
        if(!name4.equals("الاسم الرابع")){
            name4 = person.FixString(name4);
            values += " and FourthName = " + "\"" + name4 + "\" ";
        }
        if(!house.equals("رقم المنزل")){
            values += " and ApartmentNumber = " + house;
        }
        if(!street1.equals("اسم الشارع")){
            try {
                pst = (PreparedStatement) con.prepareStatement("select StreetID from Streets Where StreetName = ? Limit 1");
                pst.setString(1, street1);
                streetsResult = pst.executeQuery();
                if(streetsResult.next()){
                    street1 = String.valueOf(streetsResult.getInt("StreetID"));
                    values += " and StreetID = " + street1;
                }else{
                    JOptionPane.showMessageDialog(null, "رجاء اختيار اسم الشارع من القائمة", "Alert", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Served.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(!street2.equals("متفرع من")){
            try {
                pst = (PreparedStatement) con.prepareStatement("select StreetID from Streets Where StreetName = ? Limit 1");
                pst.setString(1, street2);
                streetsResult = pst.executeQuery();
                if(streetsResult.next()){
                    street2 = String.valueOf(streetsResult.getInt("StreetID"));
                    values += " and StreetID2 = " + street2;
                }else{
                    JOptionPane.showMessageDialog(null, "رجاء اختيار اسم الشارع من القائمة", "Alert", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Served.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(!area.equals("المنطقة")){
            try {
                pst = (PreparedStatement) con.prepareStatement("select StreetID from Streets Where Area = ? ");
                pst.setString(1, area);
                streetsResult = pst.executeQuery();
                if(streetsResult.next()){
                    values += " and (StreetID = " + String.valueOf(streetsResult.getInt("StreetID"));
                    while(streetsResult.next()){
                        values += " or StreetID = " + String.valueOf(streetsResult.getInt("StreetID"));
                    }
                    values += ")";
                }else{
                    JOptionPane.showMessageDialog(null, "رجاء اختيار اسم المنطقة من القائمة", "Alert", JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Served.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(!phone.equals("تليفون المنزل")){
            values += " and Telephon = " + phone;
        } 
        if(!mobile.equals("موبايل المخدوم")){
            values += " and Mobile = " + mobile;
        } 
        if(!mnumber.equals("موبايل الام")){
            values += " and MotherMobile = " + mnumber;
        } 
        if(!fnumber.equals("موبايل الاب")){
            values += " and FatherMobile = " + fnumber;
        } 
        if(!fnumber.equals("موبايل الاب")){
            values += " and FatherMobile = " + fnumber;
        } 
        if(!father.equals("اب الاعتراف")){
            try {               
                pst = (PreparedStatement) con.prepareStatement("select FatherID from Fathers Where Name = ? Limit 1");
                pst.setString(1, father);
                fathersResult = pst.executeQuery();
                if(fathersResult.next()){
                    father = String.valueOf(fathersResult.getInt("FatherID"));
                    values += " and FatherID = " + father;
                }else{
                    JOptionPane.showMessageDialog(null, "رجاء اختيار اسم اب الاعتراف من القائمة", "Alert", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Served.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        if(education != null){
            values += " and EducationalLevel = " + "\"" + education + "\"";
        } 
        if(!servant.equals("الخادم")){
            try {
                String[] s = servant.split("\\s+");
                int servantID = -1;
                if(s.length == 4){
                    pst = (PreparedStatement) con.prepareStatement("SELECT PersonID FROM Persons WHERE FirstName = ? AND SecondName = ? AND ThirdName = ? AND FourthName = ? LIMIT 1");
                    pst.setString(1, s[0]);
                    pst.setString(2, s[1]);
                    pst.setString(3, s[2]);
                    pst.setString(4, s[3]);
                    rs = pst.executeQuery();
                    if(rs.next())    
                        servantID = rs.getInt(1);
                }else if(s.length == 3){
                    pst = (PreparedStatement) con.prepareStatement("SELECT PersonID FROM Persons WHERE FirstName = ? AND SecondName = ? AND ThirdName = ? LIMIT 1");
                    pst.setString(1, s[0]);
                    pst.setString(2, s[1]);
                    pst.setString(3, s[2]);
                    rs = pst.executeQuery();
                    if(rs.next())
                        servantID = rs.getInt(1);
                }else if(s.length == 2){
                    pst = (PreparedStatement) con.prepareStatement("SELECT PersonID FROM Persons WHERE FirstName = ? AND SecondName = ? LIMIT 1");
                    pst.setString(1, s[0]);
                    pst.setString(2, s[1]);
                    rs = pst.executeQuery();
                    if(rs.next())
                        servantID = rs.getInt(1);
                }else{
                    servantID = -1;
                }
                if(servantID != -1){
                    pst = (PreparedStatement) con.prepareStatement("select ServedID from Lists Where ServantID = ? ");
                    pst.setInt(1, servantID);
                    servantsResult = pst.executeQuery();
                    if(servantsResult.next()){
                        values += " and (PersonID = " + String.valueOf(servantsResult.getInt("ServedID"));
                        while(servantsResult.next()){
                            values += " or PersonID = " + String.valueOf(servantsResult.getInt("ServedID"));
                        }
                        values += ")";
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "رجاء اختيار اسم الخادم من القائمة", "Alert", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Served.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(family != null){
            try {
                int id = -1;
                pst = (PreparedStatement) con.prepareStatement("select FamilyID from Families Where FamilyName = ? Limit 1");
                pst.setString(1, family);
                rs = pst.executeQuery();
                if(rs.next()){
                    id = rs.getInt("FamilyID");
                    pst = (PreparedStatement) con.prepareStatement("select PersonID from Distribution Where FamilyID = ? and Role = ?");
                    pst.setInt(1, id);
                    pst.setString(2, "Student");
                    rs = pst.executeQuery();
                    if(rs.next()){
                        values += " and (PersonID = " + String.valueOf(rs.getInt("PersonID"));
                        while(rs.next()){
                            values += " or PersonID = " + String.valueOf(rs.getInt("PersonID"));
                        }
                        values += ")";
                    }
                } 
            } catch (SQLException ex) {
                Logger.getLogger(Served.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        if(gender != null){
            values += " and Gender = " + "\"" + gender + "\"";
        }
        if(date1 != null){
            values += " and DOB >= " + "'" + date1 + "'";
        } 
        if(date2 != null){
            values += " and DOB <= " + "'" + date2 + "'";
        } 
        
        String q = "SELECT * FROM Persons p WHERE (NOT EXISTS (SELECT null FROM Servants s WHERE s.ServantID = p.PersonID)) " + values;
        try {
            st = (Statement) con.createStatement();
            rs = st.executeQuery(q);                                
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.getDataVector().removeAllElements();
            Object[] raw;
            while(rs.next()){
                pst = (PreparedStatement) con.prepareStatement("select * from Streets Where StreetID = ?");
                pst.setInt(1, rs.getInt("StreetID"));
                streetsResult = pst.executeQuery();
                String frname, scname, thname, foname, servantName = null, fatherName = null, familyName = null, listName = null;
                pst = (PreparedStatement) con.prepareStatement("SELECT FirstName, SecondName, ThirdName, FourthName\n"+
                     "from Persons\n" + 
                     "Inner join Lists where Persons.PersonID = Lists.ServantID and Lists.ServedID = ? LIMIT 1");
                pst.setInt(1, rs.getInt("PersonID"));
                temprs = pst.executeQuery();
                while(temprs.next()){
                    frname = temprs.getString("FirstName");
                    scname = temprs.getString("SecondName");
                    thname = temprs.getString("ThirdName");
                    foname = temprs.getString("FourthName");
                    servantName = frname + " " + scname + " " + thname + " " + foname;
                }
                pst = (PreparedStatement) con.prepareStatement("SELECT ListName \n"+
                     "from Lists\n" + 
                     "where ServedID = ?  LIMIT 1");
                pst.setInt(1, rs.getInt("PersonID"));
                temprs = pst.executeQuery();
                while(temprs.next()){
                    listName = temprs.getString("ListName");
                }
                pst = (PreparedStatement) con.prepareStatement("SELECT * \n"+
                     "from Families\n" + 
                     "Inner join Distribution where Families.FamilyID = Distribution.FamilyID and Distribution.PersonID = ? and Distribution.Role = \"Student\" LIMIT 1");
                pst.setInt(1, rs.getInt("PersonID"));
                temprs = pst.executeQuery();
                while(temprs.next()){
                    familyName = temprs.getString("FamilyName");
                }
                pst = (PreparedStatement) con.prepareStatement("SELECT Name \n"+
                     "from Fathers\n" + 
                     "Inner join Persons where Fathers.FatherID = Persons.FatherID and Persons.PersonID = ? LIMIT 1");
                pst.setInt(1, rs.getInt("PersonID"));
                temprs = pst.executeQuery();
                while(temprs.next()){
                    fatherName = temprs.getString("Name");
                }
                raw = new Object[24];
                raw[0] = false;
                raw[1] = rs.getInt("PersonID");
                raw[2] = rs.getString("FirstName");
                raw[3] = rs.getString("SecondName");
                raw[4] = rs.getString("ThirdName");
                raw[5] = rs.getString("FourthName");
                raw[6] = rs.getDate("DOB");
                raw[7] = rs.getInt("ApartmentNumber");
                raw[8] = rs.getInt("FloorNumber");
                raw[9] = rs.getInt("House");
                while(streetsResult.next()){
                    raw[10] = streetsResult.getString("StreetName");
                    raw[11] = streetsResult.getString("StreetName2");
                }
                raw[12] = rs.getString("Mobile");
                raw[13] = rs.getString("MotherMobile");
                raw[14] = rs.getString("FatherMobile");
                raw[15] = rs.getString("Telephon");
                raw[16] = rs.getString("FatherJob");
                raw[17] = rs.getString("MotherJob");
                raw[18] = rs.getString("Job");
                raw[19] = rs.getString("EducationalLevel");
                raw[20] = servantName;
                raw[21] = fatherName;
                raw[22] = familyName;
                raw[23] = rs.getString("Gender");
                for(int i = 1; i < 24; i++){
                    if(raw[i] == null || raw[i].toString().equals("null") || raw[i].toString().equals(Integer.toString(0)) ){
                        raw[i] = "";
                    }
                }
                model.addRow(raw);
            }
            if(model.getRowCount() == 0){
                raw = new Object[23];
                model.addRow(raw);
            }
            jTable1.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Served.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SearchActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int row = jTable1.getRowCount();
        for(int i = 0; i < row; i++){
            if(!(boolean)jTable1.getValueAt(i, 0))
                continue;
            int r = (int) jTable1.getValueAt(i, 1);
            try{
                int f = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the student with ID = " + Integer.toString(r), "Alert", JOptionPane.YES_NO_OPTION);
                if(f == 0){
                    pst = (PreparedStatement) con.prepareStatement("delete from Persons where PersonID = ?");
                    pst.setInt(1, r);
                    pst.executeUpdate();
                }
            }catch(Exception e){
                int f = JOptionPane.showConfirmDialog(null, "هذا المخدوم موجود فى احدى القوائم او ينتمى لاحدى الاسر هل انت متاكد انك تريد حذفه سوف يتم حذفه من كل الاماكن!", "Alert", JOptionPane.YES_NO_OPTION);
                if(f == 0){
                    try {
                        pst = (PreparedStatement) con.prepareStatement("delete from Distribution where PersonID = ?");
                        pst.setInt(1, r);
                        pst.executeUpdate();
                        pst = (PreparedStatement) con.prepareStatement("delete from Lists where ServedID = ?");
                        pst.setInt(1, r);
                        pst.executeUpdate();
                        pst = (PreparedStatement) con.prepareStatement("delete from ServedReview where ServedID = ?");
                        pst.setInt(1, r);
                        pst.executeUpdate();
                        pst = (PreparedStatement) con.prepareStatement("delete from Persons where PersonID = ?");
                        pst.setInt(1, r);
                        pst.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(Served.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }        
            }
        }
        SearchActionPerformed(evt);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        insertAll();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void Name4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Name4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Name4ActionPerformed

    private void FamilyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FamilyMousePressed
        try {
            // TODO add your handling code here:
            families = person.getFamilies();
            person.fillComboBox(Family, families);
        } catch (SQLException ex) {
            Logger.getLogger(Served.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_FamilyMousePressed

    private void GenderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GenderMousePressed
        // TODO add your handling code here:
        ArrayList g = new ArrayList();
        g.add("Male");
        g.add("Female");
        person.fillComboBox(Gender, g);
    }//GEN-LAST:event_GenderMousePressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int r = jTable1.getRowCount();
        ArrayList id = new ArrayList<>();
        for(int i = 0; i < r; i++){
            if((boolean)jTable1.getValueAt(i, 0))
                id.add((int)jTable1.getValueAt(i, 1));
        }
        AddList al = new AddList(id);
        al.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        al.setVisible(true);
        SearchActionPerformed(evt);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void FatherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FatherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FatherActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        NewServedF n = new NewServedF();
        n.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        n.setVisible(true);
        init();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDActionPerformed

    private void IDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IDKeyPressed
        // TODO add your handling code here:
        String value = ID.getText();
        int l = value.length();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
           ID.setEditable(true);
        }else if(evt.getKeyChar() != '\b') {
           ID.setEditable(false);
        }
    }//GEN-LAST:event_IDKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        init();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int r = jTable1.getRowCount();
        for(int i = 0; i < r; i++){
            if((boolean)jTable1.getValueAt(i, 0)){
                AddToFamily af = new AddToFamily((int)jTable1.getValueAt(i, 1), 1);
                af.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                af.setVisible(true);
            }
        }      
    }//GEN-LAST:event_jButton7ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Area;
    private javax.swing.JComboBox<String> Education;
    private javax.swing.JComboBox<String> Family;
    private javax.swing.JTextField Father;
    private javax.swing.JTextField Fnumber;
    private javax.swing.JComboBox<String> Gender;
    private javax.swing.JTextField House;
    private javax.swing.JTextField ID;
    private javax.swing.JTextField Mnumber;
    private javax.swing.JTextField Mobile;
    private javax.swing.JTextField Name1;
    private javax.swing.JTextField Name2;
    private javax.swing.JTextField Name3;
    private javax.swing.JTextField Name4;
    private javax.swing.JTextField Phone;
    private javax.swing.JButton Search;
    private javax.swing.JTextField Servant;
    private javax.swing.JTextField Street1;
    private javax.swing.JTextField Street2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void init() {
        //initComponents();
        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        for(int i = 0; i < 23; i++){
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(150);
        }
        
        holders();
        
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
        
        insertAll();
    }
}
