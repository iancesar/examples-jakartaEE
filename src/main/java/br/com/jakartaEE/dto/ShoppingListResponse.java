package br.com.jakartaEE.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.johnzon.mapper.JohnzonIgnore;

import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ShoppingListResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement
	private Long id;

	@XmlElement
	private String name;

	@JohnzonIgnore
	@XmlElement(name = "shoppingLists")
	private List<ShoppingListResponse> shoppingLists;

}
