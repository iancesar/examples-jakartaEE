package br.com.jakartaEE.jax.ws;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.modelmapper.ModelMapper;

import br.com.jakartaEE.dao.ListsDAO;
import br.com.jakartaEE.dto.ListsRequest;
import br.com.jakartaEE.entities.Lists;
import br.com.jakartaEE.exceptions.BusinessFault;
import br.com.jakartaEE.jax.contracts.ListsContract;

@Stateless
@WebService
public class ListsWS implements ListsContract {

	@Inject
	private ListsDAO dao;

	@Inject
	private ModelMapper modelMapper;

	@Override
	@WebMethod
	@WebResult(name = "list")
	public List<Lists> listAll() {
		return dao.findAll();
	}

	@Override
	@WebMethod
	@WebResult(name = "list")
	public Lists get(@WebParam(name = "id") @XmlElement(required = true) Long id) {
		return dao.findById(id);
	}

	@Override
	@WebMethod
	@WebResult(name = "list")
	public Lists create(@WebParam(name = "list") @XmlElement(required = true) ListsRequest listRequest) {

		Lists list = modelMapper.map(listRequest, Lists.class);

		return dao.save(list);
	}

	@Override
	@WebMethod
	@WebResult(name = "list")
	public Lists update(@WebParam(name = "id") @XmlElement(required = true) Long id, @WebParam(name = "list") @XmlElement(required = true) ListsRequest listRequest) throws BusinessFault {
		try {
			Lists list = modelMapper.map(listRequest, Lists.class);
			list.setId(id);
			return dao.save(list);
		} catch (Exception e) {
			throw new BusinessFault(ExceptionUtils.getRootCause(e).getMessage());
		}
	}

	@Override
	public Object delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
