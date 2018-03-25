package com.queryzip;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

@Path("/citystate")
public class CityStateRestService {
	@XmlRootElement
	class response{
		String city;
		String state;
		response(String city,String state)
		{
			this.city=city;
			this.state=state;
		}
	}
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{zip}")
	public String getCityAndState(@PathParam("zip") String zip)
	{
		String city="";
		String state="";
		response r=null;
		if(zip.equalsIgnoreCase("22030"))
		{
			city="Fairfax";
			state="VA";
			
		}
		if(zip.equalsIgnoreCase("22312"))
		{
			city="Alexandria";
			state="VA";
		}
		if(zip.equalsIgnoreCase("22301"))
		{
			city="Tysons Corner";
			state="MD";
		}
		if(zip.equalsIgnoreCase("20148"))
		{
			city="Ashburn";
			state="VA";
		}
		
		
		String citystate=city+","+state;
		return citystate;
		//return new response(city,state); 
		
		
	}
	

}
