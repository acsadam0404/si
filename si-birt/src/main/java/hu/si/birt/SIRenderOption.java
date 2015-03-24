package hu.si.birt;

import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.PDFRenderOption;

public enum SIRenderOption {
	HTML {
		public HTMLRenderOption get() {
			HTMLRenderOption htmlOptions = new HTMLRenderOption();
			htmlOptions.setOutputFormat("html");
			// HTML_OPTIONS.setHtmlRtLFlag(false);
			// HTML_OPTIONS.setEmbeddable(false);
			// HTML_OPTIONS.setImageDirectory("C:\\test\\images");
			return htmlOptions;
		}
	},
	PDF {
		public PDFRenderOption get() {
			PDFRenderOption pdfOptions = new PDFRenderOption();
			pdfOptions.setOutputFormat("pdf");
			return pdfOptions;
		}
	};
	public abstract org.eclipse.birt.report.engine.api.RenderOption get();
}
