package br.com.jakartaEE.utils;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.TypeToken;

import br.com.jakartaEE.dto.ShoppingListResponse;

public class TypesUtils {

	public static Type listShoppingListResponseType() {
		return new TypeToken<List<ShoppingListResponse>>() {
		}.getType();
	}

}
