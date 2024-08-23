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
        if(null != job.getCuttingPlan()){
            job.getCuttingPlan().setMaterial(job.getMaterial());
            job.getCuttingPlan().setMachine(job.getMachine());
        }
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
                if(delim.length>8){
                    cuttingPlan.setName(delim[3]+" "+delim[4]);
                    cuttingPlan.setPlanDimensionXUnit(delim[8]);
                    String temp1=delim[5];
                    if( temp1.indexOf("P") != -1){
                        cuttingPlan.setPlanDimensionX(Double.parseDouble(temp1.substring(0,temp1.indexOf("P"))));
                    }
                }else if(delim.length>6){
                    cuttingPlan.setName("");
                    cuttingPlan.setPlanDimensionXUnit(delim[6]);
                    String temp1=delim[3];
                    if( temp1.indexOf("P") != -1){
                        cuttingPlan.setPlanDimensionX(Double.parseDouble(temp1.substring(0,temp1.indexOf("P"))));
                    }
                }
            }else if (line.startsWith("Material")){
                if(delim.length>2){
                    String temp1=delim[2];
                    if(temp1.indexOf("P") != -1){
                        cuttingPlan.setPlanDimensionY(Double.parseDouble(temp1.substring(0,temp1.indexOf("P"))));
                    }
                }
                if(delim.length>5)
                    cuttingPlan.setPlanDimensionYUnit(delim[5]);
            }else if(line.startsWith("Machine")){
                if(delim.length>5){
                    String temp1=delim[5];
                    if(temp1.indexOf("S") !=-1){
                        cuttingPlan.setSheetDimensionX(Double.parseDouble(temp1.substring(0,temp1.indexOf("S"))));
                    }
                }
                if(delim.length>8){
                    cuttingPlan.setSheetDimensionXUnit(delim[8]);
                }
            }else if(line.startsWith("Kalınlık")){
                if(delim.length>3){
                    String temp1=delim[3];
                    if(temp1.indexOf("S") != -1){
                        cuttingPlan.setSheetDimensionY(Double.parseDouble(temp1.substring(0,temp1.indexOf("S"))));
                    }
                }
                if(delim.length>6){
                    cuttingPlan.setSheetDimensionYUnit(delim[6]);
                }
            }else if (line.startsWith("Number of cycles")){
                if(delim.length>3){
                    cuttingPlan.setNumberOfCycles(Integer.parseInt(delim[3]));
                }
                if(delim.length>7){
                    cuttingPlan.setNumberOfParts(Integer.parseInt(delim[7].trim()));
                }
            }else if (line.startsWith("Waste")){
                if(delim.length>1){
                    String temp1=delim[1];
                    if(temp1.indexOf("%") != -1){
                        cuttingPlan.setWastePercentage(Double.parseDouble(temp1.substring(0,temp1.indexOf("%"))));
                    }
                }
            }else if (line.startsWith("İç Kontursuz")){
                if (delim.length > 6) {
                    cuttingPlan.setNonProdTime(parseTime(delim[6]));
                }
                
            }else if(line.startsWith("Cutting time")){
                if(delim.length>2){
                    cuttingPlan.setCuttingTime(parseTime(delim[2]));
                }
                if(delim.length>5){
                    cuttingPlan.setTotalTime(parseTime(delim[5]));
                }
            }
            ++i;
        }
        return cuttingPlan;
        
    }
    
    private List<Part> readParts(List<String> sections) {
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
                if(delim.length>2)
                    part.setPartNumber(delim[2].trim());
            }else if ( line.startsWith("Name")){
                if(delim.length>1){
                    String str=delim[1].trim();
                    line=sections.get(++i).trim();
                    part.setName(str+line);
                }
                
            }else if(line.startsWith("Debi") ){
                if(delim.length>3)
                    part.setQuantity(Integer.parseInt(delim[3]));
            }else if(line.startsWith("Description Dimension")){
                if(delim.length>3)
                    part.setDimensionX(Double.parseDouble(delim[3]));
                if(delim.length>4)
                    part.setDimensionXUnit(delim[4]);
            }else if(line.startsWith("Info1")){
                if(delim.length>3)
                    part.setDimensionY(Double.parseDouble(delim[3]));
                if(delim.length>4)
                    part.setDimensionYUnit(delim[4]);
            }else if(line.startsWith("Info2")){
                if(delim.length>3)
                    part.setArea(Double.parseDouble(delim[3]));
                if(delim.length>4)
                    part.setAreaUnit(delim[4]);
            }else if (line.startsWith("Info3")){
                if(delim.length>2)
                    part.setWeight(Double.parseDouble(delim[2]));
                if(delim.length>3)
                    part.setWeightUnit(delim[3]);
            }else if (  line.startsWith("Cutting time")){
                if ((delim.length>2))
                    part.setCuttingTime(parseTime(delim[2]));
                if(delim.length>5)
                    part.setNonProdTime(parseTime(delim[5]));
                if(delim.length>8)
                    part.setTotalTime(parseTime(delim[8].trim()));
                parts.add(part);
            }
            ++i;
        }
        return parts;
    }
    
    private Job readJobDetail(List<String> sections ){
        Job job=new Job();
        for(int i=0;i<sections.size();i++){
            String line= sections.get(i);
            String[] delim=line.split(" ");
            if(line.startsWith("Job") && ! line.contains("Job list")) {
                if(delim.length>2){
                    job.setJobName(delim[1]+" "+delim[2]);
                }else if(delim.length>1){
                    job.setJobName(delim[1]);
                }
                if(delim.length>7){
                    job.setMachine(delim[4]+" "+delim[5]+" "+delim[6]+" "+delim[7].trim());
                }else if(delim.length>6){
                    job.setMachine(delim[4]+" "+delim[5]+" "+delim[6].trim());
                }else if(delim.length>5){
                    job.setMachine(delim[4]+" "+delim[5].trim());
                }else if(delim.length>4){
                    job.setMachine(delim[4].trim());
                }
            }else if(line.startsWith("Description Material")){
                if(delim.length>2){
                    job.setMaterial(delim[2].trim());
                }
            }else if (line.startsWith("Thickness")){
                if(delim.length>1){
                    job.setThickness(Double.parseDouble(delim[1]));
                }if(delim.length>2){
                    job.setThicknessUnit(delim[2]);
                }
            }else if(line.contains("Info3")){
                if(delim.length>3){
                    job.setTotalCuttingTime(parseTime(delim[3]));
                }
            } else if (line.startsWith("Non-prod.")) {
                if(delim.length>2){
                    job.setTotalNonProdTime(parseTime(delim[2]));
                }
            }else if(line.startsWith("Total time")){
                if(delim.length>2){
                    job.setTotalTime(parseTime(delim[2]));
                }
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
            if(delim.length>=1)
                sheet.setDimensionX(Double.parseDouble(delim[0].trim()));
            if(delim.length>=2)
                sheet.setDimensionXUnit(delim[1].trim());
            if(delim.length>=3)
                sheet.setDimensionY(Double.parseDouble(delim[2].trim()));
            if(delim.length>=4)
                sheet.setDimensionYUnit(delim[3].trim());
            if(delim.length>=5)
                sheet.setWeight(Double.parseDouble(delim[4].trim()));
            if(delim.length>=6)
                sheet.setWeightUnit(delim[5].trim());
            if(delim.length>=7)
                sheet.setNumberOfSheets(Integer.parseInt(delim[6].trim()));
            sheets.add(sheet);
            ++i;
        }
        return sheets;
    }
    private double parseTime(String timeStr) {
        if(null== timeStr|| timeStr.isEmpty())
            return 0.0;
        timeStr=timeStr.trim();
        int hours=0,minutes=0,seconds=0;
        try{
            String[] timeParts = timeStr.split(":");
            hours=Integer.parseInt(timeParts[0]);
            minutes = Integer.parseInt(timeParts[1]);
            seconds = Integer.parseInt(timeParts[2]);
        }catch (Exception e){
            return 0.0;
        }
        
        return ((hours*60)+minutes + seconds / 60.0)* 60 * 1000;
    }
}
    