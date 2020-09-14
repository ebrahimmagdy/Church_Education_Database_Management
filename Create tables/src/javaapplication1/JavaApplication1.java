/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ebrahim
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    private static final String url = "jdbc:mysql://localhost/CHURCH_EDUCATION?useUnicode=yes&characterEncoding=UTF-8";
 
    private static final String user = "Ebrahim";
 
    private static final String password = "jesus01203952089";
    
    private static final String namesarabic = "SET NAMES 'utf8'";
    
    private static final String setarabic = "ALTER DATABASE CHURCH_EDUCATION CHARACTER SET utf8 COLLATE utf8_general_ci";
 
    public static void main(String args[]) {
        //connect database
        Connection con = null;
        Statement st = null;
        ResultSet rs;
        try {
            con = DriverManager.getConnection(url, user, password);
            //st = (Statement) con.createStatement();
            //String query = "SHOW COLUMNS FROM Persons";
            
            //st.executeUpdate(namesarabic);
            //st.executeUpdate(setarabic);
            //rs = st.executeQuery(query);
            //System.out.println("Success");
//            rs = st.executeQuery("SELECT PersonID, FirstName, SecondName FROM Persons");
//            System.out.println("Success");
//            int i = 1;
//            if (rs.next()) {
//                
//                System.out.println(rs.getString(3));
//                i++;
//            }
//            String sql = "INSERT INTO Persons(FirstName, SecondName) VALUES(?, ?)";
//            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//            pst.setString(1, "eb");
//            pst.setString(2, "mg");
//            pst.executeUpdate();
//            System.out.println("Success");
            
//            while (rs.next()) {
//
//                System.out.print(rs.getInt(1));
//                System.out.print(": ");
//                System.out.print(rs.getString(2));
//                System.out.print(": ");
//                System.out.println(rs.getString(3));
//            }

 
        } catch (Exception e) {
            e.printStackTrace();
        }
        // drop tabels
//        try{
//            String table_name = "Persons";
//            String sql = "DROP TABLE " + table_name;
//            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//            pst.executeUpdate();
//            System.out.println("Success");
//            
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        
        //drop all tables
//        try{
//            String q0 = "SET FOREIGN_KEY_CHECKS = 0";
//            String q1 = "DROP TABLE IF EXISTS Persons";
//            String q2 = "DROP TABLE IF EXISTS Servants";
//            String q3 = "DROP TABLE IF EXISTS Streets";
//            String q4 = "DROP TABLE IF EXISTS Lists";
//            String q5 = "DROP TABLE IF EXISTS Families";
//            String q6 = "DROP TABLE IF EXISTS Distribution";
//            String q7 = "DROP TABLE IF EXISTS Fathers";
//            String q8 = "SET FOREIGN_KEY_CHECKS = 1";
//            st = (Statement) con.createStatement();
//            st.executeUpdate(q0);
//            st.executeUpdate(q1);
//            st.executeUpdate(q2);
//            st.executeUpdate(q3);
//            st.executeUpdate(q4);
//            st.executeUpdate(q5);
//            st.executeUpdate(q6);
//            st.executeUpdate(q7);
//            st.executeUpdate(q8);
//        }catch(Exception e){
//            
//        }
//        // create tabel Streets
//        try{
//            String sql = "CREATE TABLE Streets (" +
//            "StreetID int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
//            "StreetName nvarchar(255) NOT NULL " +
//            ")";
//            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//            pst.executeUpdate();
//            st  = (Statement) con.createStatement();
//            st.executeUpdate("ALTER TABLE Streets ADD COLUMN Area nvarchar(255)");
//            st.executeUpdate("ALTER TABLE Streets ADD COLUMN StreetName2 nvarchar(255)");
//            System.out.println("Success");
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        
//        
////        //create table father
//        try{
//            String sql = "CREATE TABLE Fathers (" +
//            "FatherID int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
//            "Name nvarchar(255) NOT NULL, " +
//            "Mobile nvarchar(11), " +
//            "Church nvarchar(255) " +
//            ")";
//            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//            pst.executeUpdate();
//            System.out.println("Success");            
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        
//        // create tabel Persons
//        String sql0 = "alter table Persons add foreign key (FatherID) references Fathers(FatherID)";
//        String sql1 = "alter table Persons add foreign key (StreetID) references Streets(StreetID)";
//        String sql2 = "alter table Persons add foreign key (StreetID2) references Streets(StreetID)";
//        try{
//            String table_name = "Persons";
//            String sql = "CREATE TABLE Persons (" +
//            "PersonID int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
//            "FirstName nvarchar(255) NOT NULL, " +
//            "SecondName nvarchar(255) NOT NULL, " +
//            "ThirdName nvarchar(255), " +   
//            "FourthName nvarchar(255), " +
//            "DOB date, " +
//            "ApartmentNumber int(11), " +
//            "FloorNumber int(11), " +
//            "Job nvarchar(255), " +
//            "Telephon nvarchar(10), " +
//            "Mobile nvarchar(11), " +
//            "Gender nvarchar(10), " +
//            "EducationalLevel nvarchar(10), " +
//            "FatherID int(11), " +
//            "StreetID int(11) " +
//            ")";
//            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//            pst.executeUpdate();
//            
//            PreparedStatement pst0 = (PreparedStatement) con.prepareStatement(sql0);
//            pst0.executeUpdate();
//            pst0 = (PreparedStatement) con.prepareStatement(sql1);
//            pst0.executeUpdate();
//            st = (Statement) con.createStatement();
//            st.executeUpdate("ALTER TABLE Persons ADD COLUMN Img LONGBLOB");
//            st.executeUpdate("ALTER TABLE Persons ADD COLUMN MotherJob nvarchar(255)");
//            st.executeUpdate("ALTER TABLE Persons ADD COLUMN MotherMobile nvarchar(255)");
//            st.executeUpdate("ALTER TABLE Persons ADD COLUMN FatherMobile nvarchar(255)");
//            st.executeUpdate("ALTER TABLE Persons ADD COLUMN FatherJob nvarchar(255)");
//            st.executeUpdate("ALTER TABLE Persons ADD COLUMN StreetID2 int(11)");
//            st.executeUpdate(sql2);
//            st.executeUpdate("ALTER TABLE Persons ADD COLUMN House int(11)");
//            System.out.println("Success");
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//            
//            //create table Links
//        try{
////            String sql = "CREATE TABLE Links (" +
////            "PersonID1 int(11), " +
////            "PersonID2 int(11) " +
////            ")";
////            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
////            pst.executeUpdate();
////            
////            st = (Statement) con.createStatement();
////            String sql0 = "alter table Links add foreign key (PersonID1) references Persons(PersonID)";
////            String sql1 = "alter table Links add foreign key (PersonID2) references Persons(PersonID)";
////            
////            st.executeUpdate(sql0);
////            st.executeUpdate(sql1);
////            System.out.println("Success");
//            
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        
//        // create tabel Servants
//        //String sql0 = "alter table Servants add foreign key (ServantID) references Persons(PersonID)";
//        try{
//            String sql = "CREATE TABLE Servants (" +
//            "ServantID int(11), " +
//            "FamilyName nvarchar(255), " +
//            "GraduationYear date, " +
//            "Posetion nvarchar(255) " +
//            ")";
//            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//            pst.executeUpdate();
//            
//            PreparedStatement pst0 = (PreparedStatement) con.prepareStatement(sql0);
//            pst0.executeUpdate();
//            System.out.println("Success");
//            
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        
//        // create tabel Families
//        try{
//            String sql = "CREATE TABLE Families (" +
//            "FamilyID int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
//            "FamilyName nvarchar(255) NOT NULL " +
//            ")";
//            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//            pst.executeUpdate();
//            st  = (Statement) con.createStatement();
//            st.executeUpdate("alter table Families add COLUMN Leader int(11)");
//            st.executeUpdate("alter table Families add foreign key (Leader) references Persons(PersonID)");
//            System.out.println("Success");
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
////        //create table Lists
//        try{
//            String sql = "CREATE TABLE Lists (" +
//            "ServantID int(11), " +
//            "ServedID int(11) " +
//            ")";
//            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//            pst.executeUpdate();
//            
//            st = (Statement) con.createStatement();
//            String sql12 = "alter table Lists add foreign key (ServantID) references Persons(PersonID)";
//            String sql11 = "alter table Lists add foreign key (ServedID) references Persons(PersonID)";
//            
//            st.executeUpdate(sql12);
//            st.executeUpdate(sql11);
//            st.executeUpdate("alter table Lists add COLUMN ListName nvarchar(255)");
//            st.executeUpdate("alter table Lists add COLUMN FamilyID int(11)");
//            st.executeUpdate("alter table Lists add foreign key (FamilyID) references Families(FamilyID)");
//            System.out.println("Success");
//            
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        
//        // create tabel Distribution
//        try{
//            String sql = "CREATE TABLE Distribution (" +
//            "FamilyID int(11), " +
//            "PersonID int(11) " +
//            ")";
//            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//            pst.executeUpdate();
//            st  = (Statement) con.createStatement();
//            st.executeUpdate("alter table Distribution add foreign key (FamilyID) references Families(FamilyID)");
//            st.executeUpdate("alter table Distribution add foreign key (PersonID) references Persons(PersonID)");
//            st.executeUpdate("ALTER TABLE Distribution ADD COLUMN Role nvarchar(255)");
//            System.out.println("Success");
//        }catch(Exception e){
//            e.printStackTrace();
//        }

        // create tabel ServedReview
//        try{
//            st  = (Statement) con.createStatement();
//            
//            String sql = "CREATE TABLE ServedReview (" +
//            "ServedID int(11) not null, " +
//            "Attendance boolean not null default 0, " +
//            "Liturgy boolean not null default 0, " +
//            "Visit boolean not null default 0, " +
//            "Date Date not null" +
//            ")";
//            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//            pst.executeUpdate();
//            
//            st.executeUpdate("alter table ServedReview add foreign key (ServedID) references Persons(PersonID)");
//            System.out.println("Success");
//        }catch(Exception e){
//            e.printStackTrace();
//        }

// create tabel ServantReview
        try{
            st  = (Statement) con.createStatement();
            st.executeUpdate("drop table ServantReview");
            String sql = "CREATE TABLE ServantReview (" +
            "ServantID int(11) not null, " +
            "Attendance boolean not null default 0, " +
            "FamilyAttendance boolean not null default 0, " +        
            "Liturgy boolean not null default 0, " +
            "Preparation boolean not null default 0, " +
            "Visit int(11) not null default 0, " +
            "Date Date not null" +
            ")";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.executeUpdate();
            
            st.executeUpdate("alter table ServantReview add foreign key (ServantID) references Persons(PersonID)");
            System.out.println("Success");
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    
}   