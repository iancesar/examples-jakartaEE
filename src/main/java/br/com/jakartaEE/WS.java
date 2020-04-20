package br.com.jakartaEE;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
@Stateless
public class WS {

	@WebMethod
	public String teste() {
		return "teste";
	}

}
