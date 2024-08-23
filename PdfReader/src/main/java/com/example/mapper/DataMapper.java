package com.example.mapper;

import com.example.entity.CuttingPlan;
import com.example.entity.Job;
import com.example.entity.Part;
import com.example.entity.Sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataMapper {
    public Job mapToJob(String text) {
        Job job ;
        String[] sections = text.split("\n");
        List<String> sectionsList=Arrays.asList(sections);
        job=readJobDetail(sectionsList);
        job.setSheets(readSheets(sectionsList));
        job.setParts(readParts(sectionsList));
        job.setCuttingPlan(readCuttingPlan(sectionsList));
        job.getCuttingPlan().setMaterial(job.getMaterial());
        job.getCuttingPlan().setMachine(job.getMachine());
         return job;
    }
    
    private CuttingPlan readCuttingPlan(List<String> sections) {
        CuttingPlan cuttingPlan=new CuttingPlan();
        int i=0;
        String temp= sections.get(i);
        while(i<sections.size() && !temp.startsWith("Cutting plans")){
            i++;
            temp=sections.get(i);
        }
        
        while( i <sections.size()){
            String line= sections.get(i);
            if(line.startsWith("No. Part name"))
                return cuttingPlan;
            String[] delim=line.split(" ");
            if(line.startsWith("Name")){
                String temp1=delim[3];
                cuttingPlan.setPlanDimensionX(Double.parseDouble(temp1.substring(0,temp1.indexOf("P"))));
                cuttingPlan.setPlanDimensionXUnit(delim[6]);
            }else if (line.startsWith("Material")){
                String temp1=delim[2];
                cuttingPlan.setPlanDimensionY(Double.parseDouble(temp1.substring(0,temp1.indexOf("P"))));
                cuttingPlan.setPlanDimensionYUnit(delim[5]);
            }else if(line.startsWith("Machine")){
                String temp1=delim[5];
                cuttingPlan.setSheetDimensionX(Double.parseDouble(temp1.substring(0,temp1.indexOf("S"))));
                cuttingPlan.setSheetDimensionXUnit(delim[8]);
            }else if(line.startsWith("Kalınlık")){
                String temp1=delim[3];
                cuttingPlan.setSheetDimensionY(Double.parseDouble(temp1.substring(0,temp1.indexOf("S"))));
                cuttingPlan.setSheetDimensionYUnit(delim[6]);
            }else if (line.startsWith("Number of cycles")){
                cuttingPlan.setNumberOfCycles(Integer.parseInt(delim[3]));
                cuttingPlan.setNumberOfParts(Integer.parseInt(delim[7].trim()));
            }else if (line.startsWith("Waste")){
                String temp1=delim[1];
                cuttingPlan.setWastePercentage(Double.parseDouble(temp1.substring(0,temp1.indexOf("%"))));
            }else if (line.startsWith("İç Kontursuz")){
                cuttingPlan.setNonProdTime(parseTime(delim[6]));
            }else if(line.startsWith("Cutting time")){
                cuttingPlan.setCuttingTime(parseTime(delim[2]));
                cuttingPlan.setTotalTime(parseTime(delim[5]));
            }
            ++i;
        }
        return cuttingPlan;
        
    }
    
    private List<Part> readParts (List<String> sections) {
        List<Part> parts = new ArrayList<>();
        int i=0;
        String temp= sections.get(i);
        while(i<sections.size() && !temp.startsWith("Parts")){
            i++;
            temp=sections.get(i);
        }
        i++;
        Part part=null;
        while( i <sections.size()){
            String line= sections.get(i);
            if(line.startsWith("Cutting plans"))
                return parts;
            String[] delim=line.split(" ");
            if(line.startsWith("Part number")){
                part=new Part();
                part.setPartNumber(delim[2].trim());
            }else if ( line.startsWith("Name")){
                String str=delim[1].trim();
                line=sections.get(++i).trim();
                part.setName(str+line);
            }else if(line.startsWith("Debi") ){
                part.setQuantity(Integer.parseInt(delim[3]));
            }else if(line.startsWith("Description Dimension")){
                part.setDimensionX(Double.parseDouble(delim[3]));
                part.setDimensionXUnit(delim[4]);
            }else if(line.startsWith("Info1")){
                part.setDimensionY(Double.parseDouble(delim[3]));
                part.setDimensionYUnit(delim[4]);
            }else if(line.startsWith("Info2")){
                part.setArea(Double.parseDouble(delim[3]));
                part.setAreaUnit(delim[4]);
            }else if (line.startsWith("Info3")){
                part.setWeight(Double.parseDouble(delim[2]));
                part.setWeightUnit(delim[3]);
            }else if (  line.startsWith("Cutting time")){
                part.setCuttingTime(parseTime(delim[2]));
                part.setNonProdTime(parseTime(delim[5]));
                part.setTotalTime(parseTime(delim[8].trim()));
                parts.add(part);
            }
            ++i;
        }
        return parts;
    }
    
    private Job readJobDetail (List<String> sections ){
        Job job=new Job();
        for(int i=0;i<sections.size();i++){
            String line= sections.get(i);
            String[] delim=line.split(" ");
            if(line.startsWith("Job") && ! line.contains("Job list")) {
                job.setJobName(delim[1]+" "+delim[2]);
                job.setMachine(delim[4]+" "+delim[5]+" "+delim[6]+" "+delim[7].trim());
            }else if(line.startsWith("Description Material")){
                 job.setMaterial(delim[2].trim());
            }else if (line.startsWith("Thickness")){
                 job.setThickness(Double.parseDouble(delim[1]));
                 job.setThicknessUnit(delim[2]);
            }else if(line.contains("Info3")){
                 job.setTotalCuttingTime(parseTime(delim[3]));
            } else if (line.startsWith("Non-prod.")) {
                 job.setTotalNonProdTime(parseTime(delim[2]));
            }else if(line.startsWith("Total time")){
                 job.setTotalTime(parseTime(delim[2]));
            }
            if(line.startsWith("Sheets")) {
                break;
            }
        }
        return job;
    }
    private List<Sheet> readSheets(List<String> sections) {
        List<Sheet> sheets = new ArrayList<>();
        int i=0;
        String temp= sections.get(i);
        while(i<sections.size() && !temp.startsWith("Sheets")){
            i++;
            temp=sections.get(i);
        }
        i=i+2;
        
        while( i <sections.size()){
            Sheet sheet=new Sheet();
            String line= sections.get(i);
            if(line.startsWith("Parts"))
                return sheets;
            String[] delim=line.split(" ");
            sheet.setDimensionX(Double.parseDouble(delim[0]));
            sheet.setDimensionXUnit(delim[1]);
            sheet.setDimensionY(Double.parseDouble(delim[2]));
            sheet.setDimensionYUnit(delim[3]);
            sheet.setWeight(Double.parseDouble(delim[4]));
            sheet.setWeightUnit(delim[5]);
            sheet.setNumberOfSheets(Integer.parseInt(delim[6].trim()));
            sheets.add(sheet);
            ++i;
        }
        return sheets;
    }
    private double parseTime(String timeStr) {
        timeStr=timeStr.trim();
        String[] timeParts = timeStr.split(":");
        int hours=Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        int seconds = Integer.parseInt(timeParts[2]);
        return ((hours*60)+minutes + seconds / 60.0)* 60 * 1000;
    }
}
    