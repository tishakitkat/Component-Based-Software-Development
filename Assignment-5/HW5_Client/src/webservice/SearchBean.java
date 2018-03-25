/*****************************
*@author tisha kanjilal
*
*@title  HW4 -SWE645 -Fall2017
*
*******************************/
package webservice;

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

    private Student[] studList;
    
    
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
		
		try {
		SearchImplementationServiceLocator sl = new SearchImplementationServiceLocator();
		SearchInterface searchService = sl.getSearchImplementationPort();
		
		Student[] students=searchService.getResult(fname, lname, city, state);
		System.out.println("Student Lenght"+students.length);
		setStudList(students);
		} catch(Exception ex)
		{
			
		}

		return "SearchResult"; 
	
	}
	
	
	public Student[] getStudList() {
		return studList;
	}
	public void setStudList(Student[] studList) {
		this.studList = studList;
	}
	
	//Student student
	public String deleteRow(Student student)
	{ 
		System.out.println("Inside deleteRow method");
		try{
		SearchImplementationServiceLocator sl = new SearchImplementationServiceLocator();
		SearchInterface searchService = sl.getSearchImplementationPort();
		
		searchService.deleteRow(student);
		System.out.println("One Row Deleted");
		
	}catch(Exception ex){
		System.out.println();
	}
	
	return "deleted";
	}
}
