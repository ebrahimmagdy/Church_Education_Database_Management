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
public class EditFather extends javax.swing.JFrame {

    /**
     * Creates new form EditFathern
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
    private String name = "";
    private String mobile = "";
    private String church = "";
    
    private int fID;
    private String street1 = "";
    private String street2 = "";
    
    private ArrayList<String> streetsnames = new ArrayList<>();
    private ArrayList<String> areasnames = new ArrayList<>();
    private ArrayList<String> servantsnames = new ArrayList<>();
    private ArrayList<String> fathers = new ArrayList<>();
    private ArrayList<String> families = new ArrayList<>();
    private ArrayList<String> assistance = new ArrayList<>();
    private ArrayList<String> educations = new ArrayList<>();
    private AutoComplete2 ac = new AutoComplete2();
    
    public EditFather(int ID) {
        initComponents();
        this.fID = ID;
        getInformation(fID);
        fill();
        
    }
    
    private void getInformation(int fID){
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
                pst = (PreparedStatement) con.prepareStatement("select * from Fathers where FatherID = ?");
                pst.setInt(1, fID);
                rs = pst.executeQuery();
                while(rs.next()){
                    this.name = rs.getString("Name");
                    this.mobile = rs.getString("Mobile");
                    this.church = rs.getString("Church");
                }
//            }
        } catch (Exception ex) {
            Logger.getLogger(EditFather.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fill(){
        Name.setText(name);
        Mobile.setText(mobile);
        Church.setText(church);
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
        jLabel19 = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        Mobile = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        Church = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));

        jLabel19.setFont(jLabel19.getFont().deriveFont(jLabel19.getFont().getSize()+4f));
        jLabel19.setText("الاسم ");

        Name.setFont(Name.getFont().deriveFont(Name.getFont().getSize()+4f));
        Name.setToolTipText("");
        Name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NameMouseEntered(evt);
            }
        });

        Mobile.setFont(Mobile.getFont().deriveFont(Mobile.getFont().getSize()+4f));
        Mobile.setToolTipText("");
        Mobile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MobileMouseEntered(evt);
            }
        });
        Mobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MobileKeyPressed(evt);
            }
        });

        jLabel21.setFont(jLabel21.getFont().deriveFont(jLabel21.getFont().getSize()+4f));
        jLabel21.setText("الموبايل");

        jButton1.setFont(jButton1.getFont().deriveFont(jButton1.getFont().getStyle() | java.awt.Font.BOLD, jButton1.getFont().getSize()+3));
        jButton1.setText("حفظ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel22.setFont(jLabel22.getFont().deriveFont(jLabel22.getFont().getSize()+4f));
        jLabel22.setText("الكنيسة");

        Church.setFont(Church.getFont().deriveFont(Church.getFont().getSize()+4f));
        Church.setToolTipText("");
        Church.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ChurchMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Mobile, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Church, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Name)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Mobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Church, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jButton1)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NameMouseEntered
        try {
            // TODO add your handling code here:
            fathers = person.getFathers();
            ac.autoComplete(Name, fathers);
        } catch (SQLException ex) {
            Logger.getLogger(EditFather.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_NameMouseEntered

    private void MobileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MobileMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_MobileMouseEntered

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            name = Name.getText();
            if(name.isEmpty()){
                JOptionPane.showMessageDialog(null,  "رجاء ادخل اسم الاب الكاهن", "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            mobile = Mobile.getText();
            church = Church.getText();
            
            pst = (PreparedStatement) con.prepareStatement("select * from Fathers where Name = ? and Mobile = ? and Church = ?");
            pst.setString(1, name);
            pst.setString(2, mobile);
            pst.setString(3, church);
            rs = pst.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null,  "هذا الكاهن موجود بالفعل !", "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            pst = (PreparedStatement) con.prepareStatement("UPDATE Fathers \n" +
                "SET \n" +
                "Name = ?, Mobile = ?, Church = ? \n" +
                " WHERE\n" +
                "FatherID = ?;");
            pst.setString(1, name);
            pst.setString(2, mobile);
            pst.setString(3, church);
            pst.setInt(4, fID);
            System.out.println(pst);
            pst.executeUpdate();

            Name.setText("");
            Mobile.setText("");
            Church.setText("");

            JOptionPane.showMessageDialog(null, "Done!", "Alert", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(EditFather.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "some thing wronge hapen!", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ChurchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChurchMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ChurchMouseEntered

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Church;
    private javax.swing.JTextField Mobile;
    private javax.swing.JTextField Name;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
