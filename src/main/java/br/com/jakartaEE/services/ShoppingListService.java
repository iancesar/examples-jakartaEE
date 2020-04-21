package br.com.jakartaEE.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.modelmapper.ModelMapper;

import br.com.jakartaEE.dao.ShoppingListDAO;
import br.com.jakartaEE.dto.ShoppingListRequest;
import br.com.jakartaEE.dto.ShoppingListResponse;
import br.com.jakartaEE.entities.ShoppingList;
import br.com.jakartaEE.utils.TypesUtils;

@Stateless
public class ShoppingListService {

	@Inject
	private ShoppingListDAO dao;

	@Inject
	private ModelMapper mapper;

	public ShoppingListResponse listAll() {

		List<ShoppingList> shoppingLists = dao.findAll();

		List<ShoppingListResponse> listResponses = mapper.map(shoppingLists, TypesUtils.listShoppingListResponseType());

		ShoppingListResponse response = new ShoppingListResponse();

		response.setShoppingLists(listResponses);

		return response;

	}

	public ShoppingListResponse get(Long id) {
		ShoppingList shoppingList = dao.findById(id).get();

		ShoppingListResponse response = mapper.map(shoppingList, ShoppingListResponse.class);

		return response;
	}

	public ShoppingListResponse create(ShoppingListRequest shoppingListRequest) {

		ShoppingList shoppingList = mapper.map(shoppingListRequest, ShoppingList.class);

		ShoppingListResponse response = mapper.map(dao.save(shoppingList), ShoppingListResponse.class);

		return response;
	}

	public ShoppingListResponse update(Long id, ShoppingListRequest shoppingListRequest) {

		ShoppingList list = mapper.map(shoppingListRequest, ShoppingList.class);
		list.setId(id);

		ShoppingListResponse response = mapper.map(dao.save(list), ShoppingListResponse.class);

		return response;
	}

}
