package br.com.jakartaEE.jax.ws;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import org.apache.commons.lang3.exception.ExceptionUtils;

import br.com.jakartaEE.dao.ShoppingListDAO;
import br.com.jakartaEE.dto.ShoppingListRequest;
import br.com.jakartaEE.dto.ShoppingListResponse;
import br.com.jakartaEE.exceptions.BusinessFault;
import br.com.jakartaEE.services.ShoppingListService;

@Stateless(name = "ShoppingListWS")
@WebService
public class ShoppingListImplWS implements ShoppingListWS {

	@Inject
	private ShoppingListDAO dao;

	@Inject
	private ShoppingListService service;

	@Override
	public ShoppingListResponse listAll() {
		return service.listAll();
	}

	@Override
	public ShoppingListResponse get(Long id) throws BusinessFault {
		try {
			return service.get(id);
		} catch (Exception e) {
			throw new BusinessFault(//
					ExceptionUtils.getRootCause(e).getMessage(), //
					ExceptionUtils.getRootCause(e));
		}
	}

	@Override
	public ShoppingListResponse create(ShoppingListRequest shoppingListRequest) {
		return service.create(shoppingListRequest);
	}

	@Override
	public ShoppingListResponse update(Long id, ShoppingListRequest shoppingListRequest) throws BusinessFault {
		try {
			return service.update(id, shoppingListRequest);
		} catch (Exception e) {
			throw new BusinessFault(//
					ExceptionUtils.getRootCause(e).getMessage(), //
					ExceptionUtils.getRootCause(e));
		}
	}

	@Override
	public void delete(Long id) throws BusinessFault {
		try {
			dao.delete(id);
		} catch (Exception e) {
			throw new BusinessFault(//
					ExceptionUtils.getRootCause(e).getMessage(), //
					ExceptionUtils.getRootCause(e));
		}
	}

}
