/*****************************
*@author tisha kanjilal
*
*@title  HW4 -SWE645 -Fall2017
*
*******************************/
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

@ManagedBean(name="searchBean")
@SessionScoped
public class SearchBean {
	private String lname,fname,city,state;
	
	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "SWE645HW4" );
    private static EntityManager entitymanager = emfactory.createEntityManager();
	// getters and setters for last name, first name, city and state respectively.

    private List<Student> studList;
    
    
	public String getLname() {return lname;}
	public void setLname(String lname) {this.lname = lname;}

	public String getFname() {	return fname;}
	public void setFname(String fname) {this.fname = fname;}

	public String getCity() {	return city;}
	public void setCity(String city) {this.city = city;	}

	public String getState() {return state;}
	public void setState(String state) {this.state = state;}
	
	public String getResult()
	{
		if(getLname().length()==0 && getFname().length()==0 && getCity().length()==0 && getState().length()==0)
		{
			return "NoResults";
		}
		
		String qry="SELECT s from Student s where ";
		boolean flag1=false,flag2=false,flag3=false,flag4=false;
		
		if(getLname() != null && getLname().length()>0){
			flag1=true;
			qry+="LAST_NAME LIKE '"+getLname()+"'";
			
		}
		
		if (getFname() != null && getFname().length()>0) {
			flag2=true;
			if(flag1){
				qry+=" and FIRST_NAME LIKE '"+getFname()+"'";
			}else{
				qry+="FIRST_NAME LIKE '"+getFname()+"'";
			}
		}
		
		if(getCity() != null && getCity().length()>0){
			flag3=true;
			if(flag1||flag2){
				qry+=" and CITY LIKE '"+getCity()+"'";
			}else{
				qry+="CITY LIKE '"+getCity()+"'";
			}
		}
		
		if(getState() != null && getState().length()>0){
			flag4=true;
			
			if(flag1||flag2||flag3){
				qry+=" and STATE LIKE '"+getState()+"'";
			}else{
				qry+="STATE LIKE '"+getState()+"'";
			}
		}
	
		
		
//		final List<Student> studRow;
//		studRow=studentService.fetchData();    // deal here
//		c
		
		
		
		final List<Student> studRow;
		Query query = entitymanager.createQuery(qry);
		studList =query.getResultList(); 
		setStudList(studList);
//		final List<Student> studRow;
//		studRow=query.getResultList();;    // deal here
//		sb.setStudentList(studRow);
		return "SearchResult"; 
	
	}
	
	
	public List<Student> getStudList() {
		return studList;
	}
	public void setStudList(List<Student> studList) {
		this.studList = studList;
	}
	
	
	public String deleteRow(Student student)
	{ try{
		EntityTransaction et=entitymanager.getTransaction();
		et.begin();
		
		Student obj=entitymanager.find(Student.class, student.getEmail());
		entitymanager.remove(obj);
		et.commit();
		
		
	}catch(Exception ex){
		System.out.println();
	}
	
	return "deleted";
	}
}
