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
public class Lists extends javax.swing.JPanel {

    /**
     * Creates new form served
     */
    LogIn login = new LogIn();
    Connection con = login.con;
    Persons person = new Persons(con);
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs, temprs, persons, Serveds;
    ResultSet streetsResult;
    ResultSet fathersResult;
    ResultSet servantsResult;
    
    private PlaceHolder holder;
        
    private String family = "";
    private String list = "";
    private String servant = "";
    private String served = "";
    private int models = 0;
    private int maxModels = 0;
    
    private ArrayList<String> streetsnames = new ArrayList<>();
    private ArrayList<String> areasnames = new ArrayList<>();
    private ArrayList<String> servantsnames = new ArrayList<>();
    private ArrayList<String> fathers = new ArrayList<>();
    private ArrayList<String> families = new ArrayList<>();
    private ArrayList<String> assistance = new ArrayList<>();
    private ArrayList<String> educations = new ArrayList<>();
    private AutoComplete2 ac = new AutoComplete2();
    
    Models model[] = new Models[100000];

    Object[] raw;
    
    
    public Lists() {
        initComponents();
        init();
    }
    
    public void holders(){
        Family.setText(null);
        Servant.setText(null);
        List.setText(null);
        
        holder = new PlaceHolder(Family, "اسم الاسرة");
        holder = new PlaceHolder(Servant, "الخادم");
        holder = new PlaceHolder(List, "اسم القائمة");
    }
    
    public void insertAll(){
        Family.setText("");
        List.setText("");
        Servant.setText("");
        holders();
        try {
            st = (Statement) con.createStatement();
            rs = st.executeQuery("SELECT distinct l.ListName, f.FamilyName, s.FirstName, s.SecondName, s.ThirdName " + 
                    "FROM Lists l left join Families f on l.FamilyID = f.FamilyID " + 
                    "left join Persons s on l.ServantID = s.PersonID");
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.getDataVector().removeAllElements();
            while(rs.next()){
                servant = "";
                if(rs.getString("FirstName") != null)
                    servant += rs.getString("FirstName");
                if(rs.getString("SecondName") != null)
                    servant += " " + rs.getString("SecondName");
                if(rs.getString("ThirdName") != null)
                    servant += " " + rs.getString("ThirdName");
                raw = new Object[4];
                raw[0] = false;
                raw[1] = rs.getString("FamilyName");
                raw[2] = rs.getString("ListName");
                raw[3] = servant;
                for(int i = 1; i < 4; i++){
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
        Servant = new javax.swing.JTextField();
        Family = new javax.swing.JTextField();
        List = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        display = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 240));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));

        Servant.setFont(Servant.getFont().deriveFont(Servant.getFont().getSize()+3f));
        Servant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ServantMouseEntered(evt);
            }
        });
        Servant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ServantActionPerformed(evt);
            }
        });
        Servant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ServantKeyPressed(evt);
            }
        });

        Family.setFont(Family.getFont().deriveFont(Family.getFont().getSize()+3f));
        Family.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FamilyKeyPressed(evt);
            }
        });

        List.setFont(List.getFont().deriveFont(List.getFont().getSize()+3f));
        List.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ListMouseEntered(evt);
            }
        });

        jButton3.setFont(jButton3.getFont().deriveFont(jButton3.getFont().getStyle() | java.awt.Font.BOLD, jButton3.getFont().getSize()+3));
        jButton3.setText("الكل");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(jButton4.getFont().deriveFont(jButton4.getFont().getStyle() | java.awt.Font.BOLD, jButton4.getFont().getSize()+2));
        jButton4.setText("بحث");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Family, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(List, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Servant, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(600, 600, 600))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Servant, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(List, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Family, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(126, 126, 126))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 140));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(79, 8, 8), 5, true));

        jButton1.setFont(jButton1.getFont().deriveFont(jButton1.getFont().getSize()+4f));
        jButton1.setText("تعديل");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(jButton2.getFont().deriveFont(jButton2.getFont().getSize()+4f));
        jButton2.setText("حذف");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        display.setFont(display.getFont().deriveFont(display.getFont().getSize()+4f));
        display.setText("فتح");
        display.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayActionPerformed(evt);
            }
        });

        jButton5.setFont(jButton5.getFont().deriveFont(jButton5.getFont().getSize()+4f));
        jButton5.setText("refresh");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(jButton6.getFont().deriveFont(jButton6.getFont().getSize()+4f));
        jButton6.setText("الحضور");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(display, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(display)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(77, 77, 77)
                .addComponent(jButton1)
                .addGap(76, 76, 76)
                .addComponent(jButton2)
                .addGap(80, 80, 80)
                .addComponent(jButton5)
                .addGap(43, 43, 43))
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 210, 530));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "اختار", "الاسرة", "اسم القائمة", "الخادم"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false
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
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel3.add(jScrollPane2);

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 840, 530));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int r = jTable1.getRowCount();
        for(int i = 0; i < r; i++){
            if(!(boolean)jTable1.getValueAt(i, 0))
                continue;
            EditList el = new EditList(jTable1.getValueAt(i, 1).toString(), jTable1.getValueAt(i, 3).toString(), jTable1.getValueAt(i, 2).toString());
            el.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            el.setVisible(true);
        }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ServantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ServantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ServantActionPerformed

    private void ServantMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ServantMouseEntered
        try {
            // TODO add your handling code here:
            servantsnames = person.getServantsNames();
            ac.autoComplete(Servant, servantsnames);
        } catch (SQLException ex) {
            Logger.getLogger(Lists.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ServantMouseEntered

    private void ListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListMouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ListMouseEntered

    private void FamilyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FamilyKeyPressed
        // TODO add your handling code here:
//        String value = ID.getText();
//        int l = value.length();
//        if (evt.getKeyChar() >= '0' && evFamilygetKeyChar() <= '9') {
//           ID.setEditable(true);
//        }else if(evt.getKeyChar() != '\b') {
//           ID.setEdiFamilyble(false);
//        }
    }//GEN-LAST:event_FamilyKeyPressed

    private void SFamilyrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        
    }//GEN-LAST:event_SearchActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int raw = jTable1.getRowCount();
        for(int i = 0; i < raw; i++){
            if(!(boolean)jTable1.getValueAt(i, 0))
                continue;
            try {
                String familyn = (String)jTable1.getValueAt(i, 1), servantn = (String)jTable1.getValueAt(i, 3), listNamen = (String)jTable1.getValueAt(i, 2);
                String ln, fn, sern;
                String l, f = null, ser;
                int servantIDn = -1, familyIDn = -1;
                int servantID = -1, familyID = -1;
                if(familyn != null && !familyn.equals("")){
                    pst = (PreparedStatement) con.prepareStatement("select FamilyID from Families where FamilyName = ?");
                    pst.setString(1, familyn);
                    rs = pst.executeQuery();
                    if(rs.next()){
                        familyIDn = rs.getInt("FamilyID");
                        f = " FamilyID = " + familyIDn + " ";
                    }
                }else{
                    f = " FamilyID is null ";
                }
                if(!servantn.isEmpty()){
                    String[] s = servantn.split("\\s+");
                    if(s.length == 4){
                        pst = (PreparedStatement) con.prepareStatement("SELECT PersonID FROM Persons WHERE FirstName = ? AND SecondName = ? AND ThirdName = ? AND FourthName = ? LIMIT 1");
                        pst.setString(1, s[0]);
                        pst.setString(2, s[1]);
                        pst.setString(3, s[2]);
                        pst.setString(4, s[3]);
                        rs = pst.executeQuery();
                        if(rs.next())
                        servantIDn = rs.getInt("PersonID");
                    }else if(s.length == 3){
                        pst = (PreparedStatement) con.prepareStatement("SELECT PersonID FROM Persons WHERE FirstName = ? AND SecondName = ? AND ThirdName = ? LIMIT 1");
                        pst.setString(1, s[0]);
                        pst.setString(2, s[1]);
                        pst.setString(3, s[2]);
                        rs = pst.executeQuery();
                        if(rs.next())
                        servantIDn = rs.getInt("PersonID");
                    }else if(s.length == 2){
                        pst = (PreparedStatement) con.prepareStatement("SELECT PersonID FROM Persons WHERE FirstName = ? AND SecondName = ? LIMIT 1");
                        pst.setString(1, s[0]);
                        pst.setString(2, s[1]);
                        rs = pst.executeQuery();
                        if(rs.next())
                        servantIDn = rs.getInt("PersonID");
                    }
                    ser = " ServantID = " + servantIDn + " ";
                }else{
                    ser = " servantID is null ";
                }
                if(listNamen.isEmpty()){
                    l = " ListName is null ";
                }else{
                    l = " listName = " + "\"" + listNamen + "\" ";
                }
                String q = "select ServedID from Lists where " + l + " and " + ser + " and " + f;
                pst = (PreparedStatement) con.prepareStatement(q);
                rs = pst.executeQuery();
                if(rs.next()){
                    JOptionPane.showMessageDialog(this, "لا يمكنك حذف القائمة يمكنك فقط حذف القوائم الفارغة !", "", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                q = "delete from Lists where " + l + " and " + ser + " and " + f;
                pst = (PreparedStatement) con.prepareStatement(q);
                System.out.println("1" + pst.asSql());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Done !", "", JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException e){
                
            }
            init();
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        insertAll();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void ServantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ServantKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ServantKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try{
            String values = "";
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.getDataVector().removeAllElements();
            
            if(!List.getText().equals("اسم القائمة")){
                values += " and l.Listname = " + "\"" + List.getText() + "\" ";
            }
            if(!Family.getText().equals("اسم الاسرة")){
                values += " and f.FamilyName = " + "\"" + Family.getText() + "\" ";
            }
            servant = Servant.getText();
            if(!Servant.getText().equals("الخادم")){
                String[] s = servant.split("\\s+");
                if(s.length == 3){
                    values += " and s.FirstName = " + "\"" + s[0] + "\" ";
                    values += " and s.SecondName = " + "\"" + s[1] + "\" ";
                    values += " and s.ThirdName = " + "\"" + s[2] + "\" ";
                }else if(s.length == 2){
                    values += " and s.FirstName = " + "\"" + s[0] + "\" ";
                    values += " and s.SecondName = " + "\"" + s[1] + "\" ";
                }else if(s.length == 1){
                    values += " and s.FirstName = " + "\"" + s[0] + "\" ";
                }
            }
            String q = "SELECT distinct l.ListName, f.FamilyName, s.FirstName, s.SecondName, s.ThirdName " + 
                    "FROM Lists l left join Families f on l.FamilyID = f.FamilyID " + 
                    "left join Persons s on l.ServantID = s.PersonID " +
                    "where true " + values;
            pst = (PreparedStatement) con.prepareStatement(q);
            System.out.println(pst.toString());
            rs = pst.executeQuery();
            while(rs.next()){
                servant = "";
                if(rs.getString("FirstName") != null)
                    servant += rs.getString("FirstName");
                if(rs.getString("SecondName") != null)
                    servant += " " + rs.getString("SecondName");
                if(rs.getString("ThirdName") != null)
                    servant += " " + rs.getString("ThirdName");
                raw = new Object[4];
                raw[0] = false;
                raw[1] = rs.getString("FamilyName");
                raw[2] = rs.getString("ListName");
                raw[3] = servant;
                for(int i = 1; i < 4; i++){
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
    }//GEN-LAST:event_jButton4ActionPerformed

    private void displayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayActionPerformed
        // TODO add your handling code here:
        int r = jTable1.getRowCount();
        for(int i = 0; i < r; i++){
            boolean b = (boolean) jTable1.getValueAt(i, 0);
            if(b){
                DisplayList d = new DisplayList((String)jTable1.getValueAt(i, 1), (String)jTable1.getValueAt(i, 2), (String)jTable1.getValueAt(i, 3));
                d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                d.setVisible(true);
            }
        }
    }//GEN-LAST:event_displayActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        init();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int r = jTable1.getRowCount();
        for(int i = 0; i < r; i++){
            boolean b = (boolean) jTable1.getValueAt(i, 0);
            if(b){
                ListReview lr = new ListReview((String)jTable1.getValueAt(i, 1), (String)jTable1.getValueAt(i, 2), (String)jTable1.getValueAt(i, 3));
                lr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                lr.setVisible(true);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Family;
    private javax.swing.JTextField List;
    private javax.swing.JTextField Servant;
    private javax.swing.JButton display;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void init() {
        //initComponents();
        for(int i = 0; i < 100000; i++){
            model[i] = new Models();
        }
        holders();    
        insertAll();    
    }

}