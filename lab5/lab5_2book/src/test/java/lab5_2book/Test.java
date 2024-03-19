package lab5_2book;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("lab5_2book")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "teamcity")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "lab5_2book")
public class Test {
}