package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features/Recipiente.feature", // CORRIGIDO
        glue = {"steps"},
        plugin = {"pretty", "html:target/reports/recipiente-report.html"},
        tags = "@padrão",
        monochrome = true
)
public class RecipienteRunner {
}
