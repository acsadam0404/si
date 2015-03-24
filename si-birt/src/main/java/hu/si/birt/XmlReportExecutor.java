package hu.si.birt;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.RenderOption;

public class XmlReportExecutor {

	private IReportEngine engine;
	private EngineConfig config;
	private Path designPath;
	private Path outputPath;

	public XmlReportExecutor(Path designPath, Path outputPath) throws BirtException {
		this.designPath = designPath;
		this.outputPath = outputPath;
		config = new EngineConfig();
		Platform.startup(config);
		IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
		engine = factory.createReportEngine(config);
	}

	public void close() {
		engine.destroy();
		Platform.shutdown();
	}

	public void execute(String rptDesignName, String dataSource, SIRenderOption renderOption, String outputFileName) throws EngineException, IOException {
		execute(Files.newInputStream(designPath.resolve(rptDesignName)), new ByteArrayInputStream(dataSource.getBytes()), renderOption, outputFileName);
	}
	
	public void execute(InputStream rptDesign, InputStream dataSource, SIRenderOption renderOption, String outputFileName) throws EngineException {
		config.getAppContext().put("org.eclipse.datatools.enablement.oda.xml.inputStream", dataSource);
		IReportRunnable design = engine.openReportDesign(rptDesign);

		IRunAndRenderTask task = engine.createRunAndRenderTask(design);
		RenderOption option = renderOption.get();
		option.setOutputFileName(outputPath.resolve(outputFileName).toString());
		task.setRenderOption(option);
		task.run();
		task.close();
	}


}