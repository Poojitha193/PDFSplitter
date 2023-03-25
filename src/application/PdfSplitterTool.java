package application;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

public class PdfSplitterTool {
	public static String splitPdf(String filePath) throws IOException , NoClassDefFoundError{
		if (!filePath.endsWith("pdf")) {
			return "Not a PDF File!";
		}
		File pdffile = new File(filePath);
		PDDocument document = PDDocument.load(pdffile);
		String fileName = pdffile.getName();
		Splitter splitter = new Splitter();
		String path = getNewDirPath(pdffile.getPath(), fileName);
		pdffile = new File(path);
		pdffile.mkdir();

		// Splitting the pages into multiple PDFs
		List<PDDocument> pages = splitter.split(document);
		if(pages.size() <= 1) {
			return "PDF has only 1 page!!";
		}
		// Using a iterator to Traverse all pages
		Iterator<PDDocument> iteration = pages.listIterator();

		// Saving each page as an individual document
		int j = 1;
		while (iteration.hasNext()) {
			PDDocument pd = iteration.next();
			pd.save(path + "\\" + fileName + "-" + j++ + ".pdf");
		}
		document.close();
		return "Splitted Pdf Successfully";
	}
	public static String getNewDirPath(String path, String fileName) {
		return path.substring(0, path.lastIndexOf("\\")) + "\\"
				+ fileName.substring(0, fileName.indexOf(".")) + "-pages";
	}
}
