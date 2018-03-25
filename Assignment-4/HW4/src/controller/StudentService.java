/*****************************
f*@author tisha kanjilal
*
*@title  HW4 -SWE645 -Fall2017
*
*******************************/
package controller;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.postgresql.*;
import java.util.HashMap;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.driver.OracleDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

public class StudentService {
	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "SWE645HW4" );
    private static EntityManager entitymanager = emfactory.createEntityManager();

	ArrayList<Student> alStudent=new ArrayList<Student>();
	ArrayList<Student> alFilter=new ArrayList<Student>();
	
	 private static double myTokens[];
	 /**Calculate Mean**/
	 public double calculateMean(String Data)
	 {
	  String data=Data;
	  double sum=0.0,avg=0.0;
	  StringTokenizer stringTokenizer = new StringTokenizer(data, ",");
	  int tokenLength=stringTokenizer.countTokens(),i=0;
	  myTokens=new double[tokenLength];
	  while(stringTokenizer.hasMoreElements())
	  {
	   myTokens[i]=Double.parseDouble(stringTokenizer.nextElement().toString());
	   sum+=myTokens[i];
	   i++;
	  }
	  avg=sum/tokenLength;
	  return avg;
	 }
	 
	 /**Calculate Standard deviation**/
	 public double calculateStDev(String Data)
	 {
	  double mean=calculateMean(Data);
	  double variance=0.0,stDev=0.0;
	  double sum=0.0;
	  int len=myTokens.length;
	  for(int i=0;i<len;i++)
	  {
	   double val=myTokens[i]-mean;
	   double valsqr=val*val;
	   sum+=valsqr;
	   
	  }
	  variance=sum/len;
	  stDev=Math.sqrt(variance);
	    
	  return stDev;
	 }
	 
	 
	 /**Store survey data**/
	 /*
	 public void storeData(SurveyBean obj)
	 { 
		 
		 
		 String url = "jdbc:postgresql://localhost:5432/tisha";
		 Properties props = new Properties();
		 props.setProperty("user","tkanjila");
		 props.setProperty("password","ooboap");
		 props.setProperty("ssl","false");
		 
		 
		 //Connection conn = DriverManager.getConnection(url, props);
		 
		// SurveyBean sb=new SurveyBean();
				 String chkbx="";
				 for(String s:obj.getCheck())
					{
						chkbx+=s;
						chkbx+=",";
					}
				 
				 Connection con = null;
				 Statement statement = null;
				 //ResultSet rs=null;
				 try{
					//Class.forName ("oracle.jdbc.driver.OracleDriver");
					 Class.forName("org.postgresql.Driver");
					 con = DriverManager.getConnection (url, props);
					 //con = DriverManager.getConnection ("jdbc:oracle:thin:@apollo.ite.gmu.edu:1521:ite10g", "tkanjila", "ooboap");
					 statement = con.createStatement();
					 String sql = "INSERT INTO surveyinfo "+"VALUES('"+obj.getFname()+"', '"+obj.getLname()+"','"+obj.getAddress()+"','"+obj.getCity()+"','"+obj.getState()+"','"+obj.getZip()+"','"+obj.getPhone()+"','"+obj.getEmail()+"','"+obj.getDate()+"','"+chkbx+"','"+obj.getRadio()+"','"+obj.getMenu()+"','"+obj.getComment()+"')";
					 
					 statement.executeUpdate (sql); 
					 System.out.println("Able to save data to database!!!!");
				 } catch (SQLException e) {
					   e.printStackTrace();
				  }catch (ClassNotFoundException e) {
				   e.printStackTrace();
				   }	 
				  
		 
	 }*/
	// , ,,,,,,
	 public void storeData(SurveyBean obj)
	 {
		
	      entitymanager.getTransaction( ).begin( );
	      
//	      Test t=new Test();
//	      t.setFname("Bruce");
//	      t.setLname("Wayne");
		 Student stud = new Student();
		 stud.setFname(obj.getFname());
		 stud.setLname(obj.getLname());
		 stud.setAddress(obj.getAddress());
		 stud.setCity(obj.getCity());
		 stud.setState(obj.getState());
		 stud.setZip(obj.getZip());
		 stud.setPhone(obj.getPhone());
		 stud.setEmail(obj.getEmail());	
		 stud.setRaffle(obj.getRaffle());
		 stud.setRadio(obj.getRadio());
		 stud.setMenu(obj.getMenu());
		 stud.setComment(obj.getComment());
		 stud.setDate1(obj.getDate().toString());
		
		 String[] appeals = obj.getCheck();
		 
		 StringBuilder appealsBuilder = new StringBuilder();
		 
		 boolean firstValue = true;
		 for(String appeal : appeals) {
			 if(firstValue) {
				 appealsBuilder.append(appeal);
				 firstValue = false;
			 } else {
				 appealsBuilder.append(",");
				 appealsBuilder.append(appeal);
			 }
		 }
		 
		 stud.setCampusAppeals(appealsBuilder.toString());
		 System.out.println("appealsBuilder.toString(): "+stud.getCampusAppeals());
		 entitymanager.persist( stud );
		 //entitymanager.persist( t );
	     entitymanager.getTransaction().commit( );

	     //entitymanager.close( );
	     //emfactory.close( );
		 	System.out.println("successfully saved");
	 }
	 
	 /**Retrieve survey data**/
	 /*
	 public ArrayList<Student> fetchData()
	 {
		 
		 
		 String url = "jdbc:postgresql://localhost:5432/tisha";
		 Properties props = new Properties();
		 props.setProperty("user","tkanjila");
		 props.setProperty("password","ooboap");
		 props.setProperty("ssl","false");
		 
		 //Connection conn = DriverManager.getConnection(url, props);
		 
		 Connection con = null;
		  Statement statement = null;
		  ResultSet rs=null;
		  
		  try {
		   //Class.forName ("oracle.jdbc.driver.OracleDriver"); 
		   Class.forName("org.postgresql.Driver");
		   con = DriverManager.getConnection (url, props);
		   //con = DriverManager.getConnection ("jdbc:oracle:thin:@apollo.ite.gmu.edu:1521:ite10g", "tkanjila", "ooboap");
		   statement = con.createStatement();
		   String sql="SELECT * FROM surveyinfo";
		   rs=statement.executeQuery(sql);
		   while(rs.next()){
			   Student s = new Student();
			   s.setFname(rs.getString(1));
			   s.setLname(rs.getString(2));
			   s.setAddress(rs.getString(3));
			   s.setCity(rs.getString(4));
			   s.setState(rs.getString(5));
			   s.setZip(rs.getString(6));
			   s.setPhone(rs.getString(7));
			   s.setEmail(rs.getString(8));
			   s.setDate1(rs.getString(9));
			   s.setChkbx(rs.getString(10));
			   s.setRadio(rs.getString(11));
			   s.setMenu(rs.getString(12));
			   s.setComment(rs.getString(13));
			   alStudent.add(s);
			  System.out.println("the firstname and lastname are: "+rs.getString(1)+" "+rs.getString(2)); 
		   }
		   
		  } 
		  catch (SQLException e) {e.printStackTrace();}
		  catch (ClassNotFoundException e) { e.printStackTrace();}
		return alStudent;
		 
	
		 
	 }
	 */
	 
	 
	 public List<Student> fetchData(){
		 
		 List<Student> al = entitymanager.createNamedQuery("findAllWithName").getResultList();
			return al; 
	 }

}
