package hu.si.birt;

import java.nio.file.Paths;

import org.eclipse.birt.core.exception.BirtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutorConfig {
	@Bean
	public XmlReportExecutor xmlReportExecutor(@Value("${inputPath}") String inputPath, @Value("${outputPath}") String outputPath) throws BirtException {
		return new XmlReportExecutor(Paths.get(inputPath), Paths.get(outputPath));
	}
}
