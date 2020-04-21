package br.com.jakartaEE.tests;

import java.net.URL;

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
		return ShrinkWrap.create(MavenImporter.class, "jakartaEE.war")//
				.offline()//
				.loadPomFromFile("pom.xml")//
				.importBuildOutput()//
				.as(WebArchive.class);

	}

}
