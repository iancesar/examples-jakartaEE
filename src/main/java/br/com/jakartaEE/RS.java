package br.com.jakartaEE;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class RS {

	@GET
	public String teste() {
		return "oi";
	}

}
