package webservice;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


import controller.Student;
@WebService(endpointInterface="webservice.SearchInterface")
public class SearchImplementation implements SearchInterface {
	
	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "SWE645HW4" );
    private static EntityManager entitymanager = emfactory.createEntityManager();

	@Override
	public List<Student> getResult(String fName, String lName, String city, String state)
	{
		List<Student> studList= new ArrayList<Student>();
	if(lName.length()==0 && fName.length()==0 && city.length()==0 && state.length()==0)
	{
		return null;
	}
	
	String qry="SELECT s from Student s where ";
	boolean flag1=false,flag2=false,flag3=false,flag4=false;
	
	if(lName != null && lName.length()>0){
		flag1=true;
		qry+="LAST_NAME LIKE '"+lName+"'";
		
	}
	
	if (fName != null && fName.length()>0) {
		flag2=true;
		if(flag1){
			qry+=" and FIRST_NAME LIKE '"+fName+"'";
		}else{
			qry+="FIRST_NAME LIKE '"+fName+"'";
		}
	}
	
	if(city != null && city.length()>0){
		flag3=true;
		if(flag1||flag2){
			qry+=" and CITY LIKE '"+city+"'";
		}else{
			qry+="CITY LIKE '"+city+"'";
		}
	}
	
	if(state != null && state.length()>0){
		flag4=true;
		
		if(flag1||flag2||flag3){
			qry+=" and STATE LIKE '"+state+"'";
		}else{
			qry+="STATE LIKE '"+state+"'";
		}
	}

	
	
//	final List<Student> studRow;
//	studRow=studentService.fetchData();    // deal here
//	c
	
	
	
	final List<Student> studRow;
	Query query = entitymanager.createQuery(qry);
	studList =query.getResultList(); 
	System.out.println("Soap Student lennght"+studList.size());
	
//	final List<Student> studRow;
//	studRow=query.getResultList();;    // deal here
//	sb.setStudentList(studRow);
	//return "SearchResult"; 
	return studList;

}
	
	@Override
	public String deleteRow(Student student)
	{ try{
		EntityTransaction et=entitymanager.getTransaction();
		et.begin();
		
		Student obj=entitymanager.find(Student.class, student.getEmail());
		entitymanager.remove(obj);
		et.commit();
		
		
	}catch(Exception ex){
		System.out.println("Delete not working");
	}
	
	return "deleted";
	}




}
