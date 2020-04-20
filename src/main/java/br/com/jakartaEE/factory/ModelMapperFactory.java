package br.com.jakartaEE.factory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.modelmapper.ModelMapper;

/**
 * Factory para o Model Mapper, para usar o CDI
 * 
 * @author ian
 *
 */
@ApplicationScoped
public class ModelMapperFactory {

	private ModelMapper mapper;

	@Produces
	public ModelMapper createModelMapper() {
		if (mapper == null) {
			mapper = new ModelMapper();
		}
		return mapper;
	}

}
