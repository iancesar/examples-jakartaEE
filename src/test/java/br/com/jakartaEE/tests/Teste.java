package br.com.jakartaEE.tests;

import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.naming.NamingException;

import org.apache.openejb.util.NetworkUtil;
import org.junit.jupiter.api.Test;

import br.com.jakartaEE.dao.ListsDAO;

public class Teste {

	@Inject
	private ListsDAO dao;

	protected EJBContainer container;

	protected static final int port = Integer.parseInt(System.getProperty("httpejbd.port", "" + NetworkUtil.getNextAvailablePort()));

	public Teste() {
		final Properties properties = new Properties();

		properties.setProperty("httpejbd.port", "" + port);

		properties.setProperty("openejb.ejbcontainer.close", "single-jvm");

		properties.setProperty("openejb.embedded.remotable", "true");

		properties.setProperty("MySql", "new://Resource?type=DataSource");
		properties.setProperty("MySql.JdbcDriver", "com.mysql.cj.jdbc.Driver");
		properties.setProperty("MySql.JtaManaged", "true");
		properties.setProperty("MySql.ValidationQuery", "select 1");
		properties.setProperty("MySql.TimeBetweenEvictionRunsMillis", "30000");
		properties.setProperty("MySql.MinEvictableIdleTimeMillis", "60000");

		properties.setProperty("MySql.JdbcUrl", "jdbc:mysql://127.0.0.1:3307/todo-list");
		properties.setProperty("MySql.UserName", "root");
		properties.setProperty("MySql.Password", "root");

		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");

		container = EJBContainer.createEJBContainer(properties);
	}

	@Test
	public void teste() throws NamingException {
		container.getContext().bind("inject", this);

		System.out.println(dao);
	}

}
