package lab5_3cucumberselenium;



import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("lab5_3cucumberselenium")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "teamcity")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "lab5_3cucumberselenium")
public class Test {

}   