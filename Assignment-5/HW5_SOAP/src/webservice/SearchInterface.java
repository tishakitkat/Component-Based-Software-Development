package webservice;
import controller.Student;
/**
 * 
 */

import javax.jws.soap.SOAPBinding.Style;

import controller.Student;

import javax.jws.soap.SOAPBinding;
import javax.jws.*;
import java.util.*;

/**
 * @author tishachoudhuri
 *
 */
@WebService
@SOAPBinding(style=Style.DOCUMENT)
public interface SearchInterface {
	@WebMethod
	public List<Student> getResult(String fName, String lName, String city, String state);
	@WebMethod
	public String deleteRow(Student student);
}