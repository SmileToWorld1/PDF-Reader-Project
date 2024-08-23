package com.example;

import com.example.entity.Job;
import com.example.entity.Part;
import com.example.entity.Sheet;
import com.example.mapper.DataMapper;
import com.example.parser.PDFParser;

import java.io.IOException;

public class Driver {
	public static void main(String[] args) throws IOException {
		String filePath = "C:\\Users\\user1\\IdeaProjects\\PdfReaderProject\\PdfReader\\src\\main\\resources\\72163 8mm.pdf";
		
		PDFParser parser = new PDFParser();
		String text = parser.extractText(filePath);
		
		DataMapper mapper = new DataMapper();
		Job job = mapper.mapToJob(text);
		System.out.println("Job Details are:");
		System.out.println(job);
		
		int i=1;
		System.out.println("Sheet Details are:");
		for(Sheet sheet: job.getSheets()){
			System.out.println("\nSheet No."+i++);
			System.out.println(sheet);
		}
		
		i=1;
		System.out.println("Parts Details are:");
		for(Part part: job.getParts()){
			System.out.println("\nPart No."+i++);
			System.out.println(part);
		}
		
		System.out.println("\nCutting Plan Details are:\n");
		System.out.println(job.getCuttingPlan());
		

		
	}
}