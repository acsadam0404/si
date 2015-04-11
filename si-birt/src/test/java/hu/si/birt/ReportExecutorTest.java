package hu.si.birt;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Paths;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.api.EngineException;
import org.junit.Test;

public class ReportExecutorTest {
	@Test
	public void test() throws EngineException, BirtException {
		new XmlReportExecutor(Paths.get("C:/tmp"), Paths.get("C:/tmp")).execute(
				getClass().getResourceAsStream("test.rptdesign"), getClass()
						.getResourceAsStream("people.xml"), SIRenderOption.PDF,
				"test.pdf");
	}

	@Test
	public void testInline() throws BirtException {
		XmlReportExecutor executor = null;
		InputStream dataSource = new ByteArrayInputStream(
				"<people><person><name>Ádám</name><age>23</age></person><person><name>Zsolti a béka</name><age>26</age></person><person>	<name>Dorka</name><age>23</age></person></people>"
						.getBytes());

		InputStream rptDesign = getClass()
				.getResourceAsStream("test.rptdesign");
		executor = new XmlReportExecutor(Paths.get("C:/tmp"),Paths.get("C:/tmp"));
		executor.execute(rptDesign, dataSource, SIRenderOption.PDF,
				"test2.pdf");
	}
}
