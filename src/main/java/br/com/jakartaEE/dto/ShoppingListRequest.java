package br.com.jakartaEE.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ShoppingListRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	@Size(max = 100)
	@XmlElement(required = true)
	private String name;

}
