package com.qainfotech.Tatoc.Cucumber_Tatoc;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(monochrome=true,features = {"Feature/Advanced.feature"},glue = "stepDefinition")

public class Test02Advanced {

    private TestNGCucumberRunner testNGCucumberRunner;
    @BeforeClass(alwaysRun = true)

    public void setUpClass() throws Exception {
                    testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
 
    @DataProvider
    public Object[][] features() {
                   return testNGCucumberRunner.provideFeatures();
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
                  testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }             

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
                    testNGCucumberRunner.finish();
    }
}
