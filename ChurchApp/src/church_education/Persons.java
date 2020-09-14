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
public class Persons{

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
    
    
    public Persons(Connection con) {
        this.con = con;
    }
    
    public ArrayList<String> getStreetsNames() throws SQLException{
        streetsnames.clear();
        st = (Statement) con.createStatement();
        rs = st.executeQuery("select StreetName from Streets");
        while(rs.next()){
            streetsnames.add(rs.getString("StreetName"));
        }
        return streetsnames;   
    }
    
    public ArrayList<String> getAreasNames() throws SQLException{
        areasnames.clear();
        st = (Statement) con.createStatement();
        rs = st.executeQuery("select Area from Streets");
        while(rs.next()){
            areasnames.add(rs.getString("Area"));
        }
        return areasnames;
    }
    
    public ArrayList<String> getFathers() throws SQLException{
        fathers.clear();
        st = (Statement) con.createStatement();
        rs = st.executeQuery("select Name from Fathers");
        while(rs.next()){
            fathers.add(rs.getString("Name"));
        }
        return fathers;
    }
    
    public ArrayList<String> getServantsNames() throws SQLException{
        servantsnames.clear();
        String frname, scname, thname, foname; 
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
        return servantsnames;
    }
    
    public ArrayList<String> getFamilies() throws SQLException{
        families.clear();
        st = (Statement) con.createStatement();
        rs = st.executeQuery("select FamilyName from Families");
        while(rs.next()){
            families.add(rs.getString("FamilyName"));
        }
        return families;
    }
    
    public ArrayList<String> getAssistance(String family) throws SQLException{
        assistance.clear();
        String frname, scname, thname, foname; 
        int fID = -1;
        pst = (PreparedStatement) con.prepareStatement("SELECT FamilyID FROM Families WHERE FamilyName = ? LIMIT 1");
        pst.setString(1, family);
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
        return assistance;
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
        if(out.endsWith("ي")){
            out = out.substring(0,out.length() - 1) + 'ى';
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
            
            pst = (PreparedStatement) con.prepareStatement("SELECT * FROM Distribution WHERE FamilyID = ? and PersonID = ? and Role = ?");
            pst.setInt(1, fID);
            pst.setInt(2, pID);
            if(role == 0)
                pst.setString(3, "Servant");
            else
                pst.setString(3, "Student");
            rs = pst.executeQuery();
            if(rs.next())
                return;
            
            
            pst = (PreparedStatement) con.prepareStatement("insert into Distribution(FamilyID, PersonID, Role) values(?, ?, ?)");
            pst.setInt(1, fID);
            pst.setInt(2, pID);
            if(role == 0)
                pst.setString(3, "Servant");
            else
                pst.setString(3, "Student");
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Persons.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}