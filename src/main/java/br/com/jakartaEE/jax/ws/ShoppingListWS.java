package br.com.jakartaEE.jax.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import br.com.jakartaEE.dto.ShoppingListRequest;
import br.com.jakartaEE.dto.ShoppingListResponse;
import br.com.jakartaEE.exceptions.BusinessFault;

@WebService(targetNamespace = "http://ws.jax.jakartaEE.com.br/", name = "ShoppingListWS")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ShoppingListWS {

	@WebMethod
	@WebResult(name = "list", targetNamespace = "http://ws.jax.jakartaEE.com.br/", partName = "list")
	public ShoppingListResponse listAll();

	@WebMethod
	@WebResult(name = "list", targetNamespace = "http://ws.jax.jakartaEE.com.br/", partName = "list")
	public ShoppingListResponse create(@WebParam(partName = "list", name = "list") ShoppingListRequest shoppingListRequest);

	@WebMethod
	@WebResult(name = "list", targetNamespace = "http://ws.jax.jakartaEE.com.br/", partName = "list")
	public ShoppingListResponse update(@WebParam(partName = "id", name = "id") Long id, @WebParam(partName = "list", name = "list") ShoppingListRequest shoppingListRequest) throws BusinessFault;

	@WebMethod
	@WebResult(name = "list", targetNamespace = "http://ws.jax.jakartaEE.com.br/", partName = "list")
	public ShoppingListResponse get(@WebParam(partName = "id", name = "id") Long id) throws BusinessFault;;

	@WebMethod
	@WebResult(name = "return", targetNamespace = "http://ws.jax.jakartaEE.com.br/", partName = "return")
	public void delete(@WebParam(partName = "id", name = "id") Long id) throws BusinessFault;
}
