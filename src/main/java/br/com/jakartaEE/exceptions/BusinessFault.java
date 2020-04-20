
package br.com.jakartaEE.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "BusinessFault")
public class BusinessFault extends Exception {

	private static final long serialVersionUID = 1L;

	public BusinessFault() {
		super();
	}

	public BusinessFault(String message) {
		super(message);
	}

	public BusinessFault(String message, Throwable cause) {
		super(message, cause);
	}

}
