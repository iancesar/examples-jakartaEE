package br.com.jakartaEE.tests.integration;

import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.ArquillianTest;
import org.jboss.arquillian.junit.ArquillianTestClass;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.archive.importer.MavenImporter;
import org.junit.ClassRule;
import org.junit.Rule;

@ArquillianSuiteDeployment
public class Arquillian {

	@ArquillianResource
	protected URL deploymentUrl;

	@ClassRule
	public static ArquillianTestClass arquillianTestClass = new ArquillianTestClass();

	@Rule
	public ArquillianTest arquillianTest = new ArquillianTest();

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(MavenImporter.class, "shopping-list.war")//
				.offline()//
				.loadPomFromFile("pom.xml")//
				.importBuildOutput()//
				.as(WebArchive.class);

	}

	protected Service createService(String ws, String serviceName) {
		try {
			URL url = new URL(String.format(deploymentUrl + "webservices/%s?wsdl", ws));
			QName qname = new QName("http://ws.jax.jakartaEE.com.br/", serviceName);
			Service service = Service.create(url, qname);
			return service;
		} catch (MalformedURLException e) {
			fail(String.format("Erro ao criar o service %s", ws));
			return null;
		}
	}

}
