/*****************************
*@author tisha kanjilal
*
*@title  HW4 -SWE645 -Fall2017
*
*******************************/
package controller;

import java.io.Serializable;
import java.util.Date;


import org.hibernate.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="STUDNT_TBL")
@NamedQuery(name ="findAllWithName",
query="SELECT s FROM Student s")
public class Student implements Serializable {


	
	@Column(name="FIRST_NAME")
	private String fname;
	
	@Column(name="LAST_NAME")
	private String lname;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="ZIP")
	private String zip;
	
	@Column(name="PHONE")
	private String phone;
	
	@Id
	private String email;
	
	
	private String raffle;
	
	
	private String radio;
	
	
	private String menu;
	
	private String comment;
	
	
	
	private String date1;
	
	@Column(name="APPEALS")
	private String campusAppeals;
	
	@Transient
	private String[] check;
	
	@Transient
	private String chkbx;
	@Transient
	private Date date;
	
	@Transient
	private Date startdate;
	
	
	private static final long serialVersionUID = 1L;
	
	/**Setters and getters for all form fields from survey form**/
	
	
	public String getFname() {return fname;}
	public void setFname(String fname) {this.fname = fname;}
	
	
	public String getLname() {return lname;}
	public void setLname(String lname) {this.lname = lname;}

	
	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}

	
	public String getCity() {return city;}
	public void setCity(String city) {this.city = city;}

	
	public String getState() {return state;	}
	public void setState(String state) {this.state = state;}

	
	public String getZip() {return zip;}
	public void setZip(String zip) {this.zip = zip;}

	
	public String getPhone() {return phone;}
	public void setPhone(String phone) {this.phone = phone;}

	
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	

	public Date getDate() {System.out.println("DAte :"+date); return date;}
	public void setDate(Date date) {this.date = date;}
	
	@Column(name="DATE")
	public String getDate1() {return date1;}
	public void setDate1(String date) {this.date1 = date;}
	
	public Date getStartdate() {return startdate;}
	public void setStartdate(Date startdate) {this.startdate = startdate;}
	
	@Column(name="RAFFLE")
	public String getRaffle() {return raffle;}
	public void setRaffle(String raffle) {this.raffle = raffle;System.out.print("Already set the raffle"+this.raffle);}

	public String[] getCheck() {return check;}
	public void setCheck(String[] check) {this.check = check;}
	
	@Column(name="RADIO")
	public String getRadio() {System.out.println("Radio:"+radio);return radio;}
	public void setRadio(String radio) {this.radio = radio;}
	
	@Column(name="MENU")
	public String getMenu() {System.out.println("Menu:"+menu);return menu;}
	public void setMenu(String menu) {this.menu = menu;}

	public String getChkbx() {return chkbx;}
	public void setChkbx(String chkbx) {this.chkbx = chkbx;}

	@Column(name="COMMENT")
	public String getComment() {System.out.println("Comment:"+comment);return comment;}
	public void setComment(String comment) {this.comment = comment;}
	
	
	public String getCampusAppeals() {
		return this.campusAppeals;
	}
	public void setCampusAppeals(String campusAppeals) {
		this.campusAppeals = campusAppeals;
	}

	
}
