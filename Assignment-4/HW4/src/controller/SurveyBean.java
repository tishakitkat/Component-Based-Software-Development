/*****************************
*@author tisha kanjilal
*
*@title  HW2 -SWE645 -Fall2017
*
*******************************/
package controller;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
//import javax.annotation.ManagedBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


@ManagedBean(name="surveyBean")
public class SurveyBean {
	
boolean flag1=false,flag2=false,flag=false;	
private Student student=new Student();
private StudentService studentService=new StudentService();
private WinningResult winningResult=new WinningResult();


SurveyBean getInstance(){return this;}

public SurveyBean() {
	this.setStudentList(studentService.fetchData());
}

/** The method menuList() returns only list values matching the user's input string**/
 

public List<String> menuList(String query) {  
ArrayList<String> l = new ArrayList<String>();  
String[] list =new String[3];
list[0]="Very Likely";
list[1]="Likely";
list[2]="Unlikely";
for (String val:list)
{
	if(val.toLowerCase().contains(query.toLowerCase()))
		l.add(val);
			
}
return l;  
} 



/** getter and setter for List of students - survey info **/
public List<Student> getStudentList(){return winningResult.getStudentList();}
public void setStudentList(List<Student> studRow){winningResult.setStudentList(studRow);}





/** getters and setters of WinningResult model properties **/

public double getmean(){return winningResult.getmean();}
public void setmean(double mean){winningResult.setmean(mean);}


public double getstDev(){return winningResult.getstDev();}
public void setstDev(double stDev){winningResult.setstDev(stDev);}


/**getters and setters of Student  model properties**/

public String getFname() {return this.student.getFname();}
public void setFname(String fname) {this.student.setFname(fname);}

public String getLname() {return this.student.getLname();}
public void setLname(String lname) {this.student.setLname(lname);}

public String getAddress() {return this.student.getAddress();}
public void setAddress(String address) {this.student.setAddress(address);}

public String getCity() {return this.student.getCity();}
public void setCity(String city) {this.student.setCity(city);;}

public String getState() {return this.student.getState();}
public void setState(String state) {this.student.setState(state);}

public String getZip() {return this.student.getZip();}
public void setZip(String zip) {this.student.setZip(zip);}

public String getPhone() {return this.student.getPhone();}
public void setPhone(String phone) {this.student.setPhone(phone);}

public String getEmail() {return this.student.getEmail();}
public void setEmail(String email) {this.student.setEmail(email);}

public Date getDate() {return this.student.getDate();}
public void setDate(Date date) {this.student.setDate(date);}

public Date getStartdate() {return this.student.getStartdate();}
public void setStartdate(Date startdate) {this.student.setStartdate(startdate);}


public String[] getCheck() {return student.getCheck();}
public void setCheck(String[] check) {this.student.setCheck(check);}

public String getChkbx() {return student.getChkbx();}
public void setChkbx(String chkbx) {student.setChkbx(chkbx);;}

public String getRadio() {return student.getRadio();}
public void setRadio(String radio) {this.student.setRadio(radio);}

public String getMenu() {return student.getMenu();}
public void setMenu(String menu) {student.setMenu(menu);}

public String getRaffle() {return this.student.getRaffle();}
public void setRaffle(String raffle) {student.setRaffle(raffle);}

public String getComment() {return student.getComment();}
public void setComment(String comment) {student.setComment(comment);;}

public String showData(){
	FacesContext context = FacesContext.getCurrentInstance();
	if(registerDate(getStartdate())==true)
	{
		flag1=true;
	}else{
		FacesMessage errorMessage=new FacesMessage("Start date cannot be before Survey Date");
		errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		context.addMessage("startdate", errorMessage);
		flag1=false;
	}
	
	/***********************/
	double mean=0.0,stDev=0.0;
	if(validateRaffle(getRaffle())){
		// If true we set the Raffle
		System.out.println("Correct Raffle:"+getRaffle());
		//student.setRaffle(raffle);
		flag2=true;
		mean=studentService.calculateMean(student.getRaffle());
		stDev=studentService.calculateStDev(student.getRaffle());
		setmean(mean);
		setstDev(stDev);
		
		
		
	}else{
		//else goto page and show error for raffle validation  
		System.out.println("Error Raffle:"+getRaffle());
		//FacesContext context = FacesContext.getCurrentInstance();
		flag2=false;
		context.addMessage("raffle",
				new FacesMessage("Please  enter atleast 10 valid numbers between 1 and 100"));
		
		
	}
	/***********************/
	

	
	
	/** Setting checkbox options as string **/
	String chkbx="";
	for(String s:getCheck())
	{
		chkbx+=s;
		chkbx+=",";
	}
	setChkbx(chkbx);
	
	/** store the survey data **/
	studentService.storeData(this.getInstance());
	System.out.println("Printing date inside show data: "+this.getDate());
	
	/** setting the list of all surveys taken till date **/
	final List<Student> studRow;
	studRow=studentService.fetchData();    // deal here
	setStudentList(studRow);
	if(flag1==true&&flag2==true){
		if(getmean()>90)
			return "winner";
		else 
			return "simple";
		
	}
	else{
		
		return "index.xhtml";
	}
	

}

/*Validating start date against survey date*/
public boolean registerDate(Date startdate){
	
	Date d=getDate();
	Date sdate=startdate;
	if(!d.before(sdate))
	{
		
		System.out.println("Inside register date!!!!, wrong start date:"+startdate);
		return false;
	}
	else{
		System.out.println("Inside register date!!!!, correct start date:"+startdate);
		return true;	
	}
	
}

public boolean validateRaffle(String myraffle){
	System.out.println("Am going to validate the raffle");
	
	int min,max,token;
	
	//check if myraffle is null
	if(myraffle==null)
		return false;
	
	String[] tokens = myraffle.split(",");
	System.out.println("tokens.length is:"+tokens.length);
	if(tokens.length >= 10)
	{
		flag=true;
		System.out.println("The tokens flag is alpha "+flag);
	}
	else{
		flag=false;
		System.out.println("The tokens flag is beta "+flag);
		return flag;
	}
	
	
	for(int i=0;i<tokens.length;i++){
		//check each value 
		//if the value is not number then return false
		try{
		token=Integer.parseInt(tokens[i]);
		}
		catch(Exception e){
			return false;
		}
			
		if(token < 1 || token > 100 )
		{
			
			flag=false;
			return false;
		}
		
		flag=true;
	}
	
	return flag;
}

public void populateFromZip(){
	Client client=ClientBuilder.newClient();
	WebTarget target=client.target("http://ec2-18-221-145-166.us-east-2.compute.amazonaws.com/HW3_V1.0/webresources/citystate/"+getZip().toString());
	String data=target.request(MediaType.TEXT_PLAIN).get(String.class);
	StringTokenizer stringTokenizer = new StringTokenizer(data, ",");
	student.setCity(stringTokenizer.nextElement().toString());
	student.setState(stringTokenizer.nextElement().toString());

}

}
