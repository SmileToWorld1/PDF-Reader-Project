package com.example.parser;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;

public class PDFParser {
    public String extractText(String filepath) throws IOException {
        File pdfFile = new File(filepath);
        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
			return pdfStripper.getText(document);
        }
	}
}