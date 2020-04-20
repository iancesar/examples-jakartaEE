package br.com.jakartaEE.jax.contracts;

import br.com.jakartaEE.dto.ListsRequest;
import br.com.jakartaEE.exceptions.BusinessFault;

public interface ListsContract {

	public Object listAll();

	public Object get(Long id);

	public Object create(ListsRequest list);

	public Object update(Long id, ListsRequest list) throws BusinessFault;

	public Object delete(Long id) throws BusinessFault;

}
