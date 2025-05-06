package runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Agendamento.feature", // arquivo específico
        glue = {"steps"}, // pacote onde estão os steps
        plugin = {"pretty", "html:target/reports/usuario-report.html"},
        tags = "@padrão", // opcional
        monochrome = true
)
public class AgendamentoRunner {
}
