package hu.si.birt

import org.eclipse.birt.report.engine.api.EngineException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class BirtRestController {
	@Autowired
	private XmlReportExecutor xmlex;


	@RequestMapping(value = "/xml/pdf", method = RequestMethod.POST, produces = "text/plain")
	@ResponseBody
	public ResponseEntity<String> generateXmlReport(@RequestBody XmlReportConfig config) throws EngineException, IOException {
		xmlex.execute(config.rptdesign, config.xml, SIRenderOption.PDF, config.outputFileName);
		return new ResponseEntity<String>("OK", HttpStatus.OK)
	}

	private static class XmlReportConfig {
		String rptdesign
		String xml
		String outputFileName
	}
}
