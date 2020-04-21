package br.com.jakartaEE.tests.units;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.modelmapper.ModelMapper;

import br.com.jakartaEE.dto.ShoppingListResponse;
import br.com.jakartaEE.entities.ShoppingList;
import br.com.jakartaEE.utils.TypesUtils;

public class ModelMapperTest {

	private ModelMapper mapper = new ModelMapper();

	@Test
	public void mapToList() {

		try {

			List<ShoppingList> list = new ArrayList<>();
			list.add(new ShoppingList(1l, "Lista 1"));
			list.add(new ShoppingList(3l, "Lista 2"));
			list.add(new ShoppingList(2l, "Lista 3"));

			List<ShoppingListResponse> mapToList = mapper.map(list, TypesUtils.listShoppingListResponseType());

			assertTrue(!mapToList.isEmpty());

			mapToList.forEach(m -> assertNotNull(m.getId()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
