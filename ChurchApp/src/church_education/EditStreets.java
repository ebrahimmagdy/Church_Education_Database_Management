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
public class EditStreets extends javax.swing.JFrame {

    /**
     * Creates new form EditStreets
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
    private String stname = "";
    private String stname2 = "";
    private String area = "";
    
    private int sID;
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
    
    public EditStreets(int ID) {
        initComponents();
        this.sID = ID;
        getInformation(sID);
        fill();
        
    }
    
    private void getInformation(int sID){
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
                pst = (PreparedStatement) con.prepareStatement("select * from Streets where StreetID = ?");
                pst.setInt(1, sID);
                rs = pst.executeQuery();
                while(rs.next()){
                    this.street1 = rs.getString("StreetName");
                    this.street2 = rs.getString("StreetName2");
                    this.area = rs.getString("Area");
                }
//            }
        } catch (Exception ex) {
            Logger.getLogger(EditStreets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fill(){
        Street1.setText(street1);
        Street2.setText(street2);
        Area.setText(area);
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
        Street1 = new javax.swing.JTextField();
        Street2 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        Area = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));

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

        jButton1.setFont(jButton1.getFont().deriveFont(jButton1.getFont().getStyle() | java.awt.Font.BOLD, jButton1.getFont().getSize()+3));
        jButton1.setText("حفظ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel22.setFont(jLabel22.getFont().deriveFont(jLabel22.getFont().getSize()+4f));
        jLabel22.setText("المنطقة");

        Area.setFont(Area.getFont().deriveFont(Area.getFont().getSize()+4f));
        Area.setToolTipText("");
        Area.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AreaMouseEntered(evt);
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
                        .addComponent(Street1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Street2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Area, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(Street1)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(Street2))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(Area))
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

    private void Street1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Street1MouseEntered
        try {
            // TODO add your handling code here:
            streetsnames = person.getStreetsNames();
            ac.autoComplete(Street1, streetsnames);
        } catch (SQLException ex) {
            Logger.getLogger(EditStreets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Street1MouseEntered

    private void Street2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Street2MouseEntered
        try {
            // TODO add your handling code here:
            streetsnames = person.getStreetsNames();
            ac.autoComplete(Street2, streetsnames);
        } catch (SQLException ex) {
            Logger.getLogger(EditStreets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Street2MouseEntered

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            street1 = Street1.getText();
            if(street1.isEmpty()){
                JOptionPane.showMessageDialog(null,  "رجاء ادخل اسم الشارع", "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            street2 = Street2.getText();
            area = Area.getText();
            
            pst = (PreparedStatement) con.prepareStatement("select * from Streets where StreetName = ? and Area = ? and StreetName2 = ?");
            pst.setString(1, street1);
            pst.setString(2, area);
            pst.setString(3, street2);
            rs = pst.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "هذا الشارع موجود بالفعل !", "Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }

            pst = (PreparedStatement) con.prepareStatement("UPDATE Streets \n" +
                "SET \n" +
                "StreetName = ?, StreetName2 = ?, Area = ? \n" +
                " WHERE\n" +
                "StreetID = ?;");
            pst.setString(1, street1);
            pst.setString(2, street2);
            pst.setString(3, area);
            pst.setInt(4, sID);
            pst.executeUpdate();
            
            if(!street2.isEmpty()){
                pst = (PreparedStatement) con.prepareStatement("select * from Streets where StreetName = ? and Area = ?");
                pst.setString(1, street2);
                pst.setString(2, area);
                rs = pst.executeQuery();
                if(!rs.next()){
                    pst = (PreparedStatement) con.prepareStatement("INSERT INTO Streets(StreetName, Area) VALUES (?, ?)");
                    pst.setString(1, street2);
                    pst.setString(2, area);
                    pst.executeUpdate();
                }
            }

            Street1.setText("");
            Street2.setText("");
            Area.setText("");

            JOptionPane.showMessageDialog(null, "Done!", "Alert", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "some thing wronge hapen!", "Alert", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void AreaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AreaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_AreaMouseEntered

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Area;
    private javax.swing.JTextField Street1;
    private javax.swing.JTextField Street2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
