/*****************************
*@author tisha kanjilal
*
*@title  HW2 -SWE645 -Fall2017
*
*******************************/

package controller;

import java.util.ArrayList;
import java.util.List;

public class WinningResult {
	
	double  mean,stDev;
	List<Student> list;
	 
	 /*** setters and getters for mean ***/
	 public double getmean(){ return mean; }
	 public void setmean(double mean){this.mean=mean;}
	
	 
	 /*** setters and getters for standard Deviation ***/
	 public double getstDev(){return stDev;}
	 public void setstDev(double stDev) {this.stDev=stDev;}
	 
	 
	 /** getter and setter for List of students - survey info **/
	 public List<Student> getStudentList(){return this.list; }
	 public void setStudentList(List<Student> studRow){this.list=studRow;}
	 

}
