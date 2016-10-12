package com.question;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Question2 {
	
	public static void main(String[] args) {

        String fileInput = "data/InputCalculator.txt"; // okunacak dosya
        String fileOutput = "data/OutputCalculator.txt"; // tersine siralanmis hali 
        
        //dosya okunuyor
        List<String> lines = readFile(fileInput);
        

        List<String> output = new ArrayList<>();
        String temp = "";
        String operation = "=";
        
        for (int i=0; i<lines.size(); i++)
        {
        	switch (lines.get(i)) 
        	{
				case "+":
					operation = "+";
					temp = String.format("%.1f", Double.parseDouble(lines.get(i-1))) + "+";
					output.add(temp);
					temp = temp + String.format("%.1f", Double.parseDouble(lines.get(i+1)));
					output.add(temp);
					break;
				case "-":
					operation = "-";
					temp = String.format("%.1f", Double.parseDouble(lines.get(i-1))) + "-";
					output.add(temp);
					temp = temp + String.format("%.1f", Double.parseDouble(lines.get(i+1)));
					output.add(temp);
					break;
				case "*":
					operation = "*";
					temp = String.format("%.1f", Double.parseDouble(lines.get(i-1))) + "*";
					output.add(temp);
					temp = temp + String.format("%.1f", Double.parseDouble(lines.get(i+1)));
					output.add(temp);
					break;
				case "/":
					operation = "/";
					temp = String.format("%.1f", Double.parseDouble(lines.get(i-1))) + "/";
					output.add(temp);
					temp = temp +String.format("%.1f", Double.parseDouble(lines.get(i+1)));
					output.add(temp);
					break;
				case "=":
					temp = calculate(output.get(output.size()-1), operation)+"";
					output.add(temp);
					operation = "=";
					break;
				default:
					
					if (operation.equals("="))
						output.add(String.format("%.1f", Double.parseDouble(lines.get(i))));
					break;
			}
        	
        }//for
        
        writeFile(fileOutput, output);
	}
	
	public static List<String> readFile(String filename)
	{
		List<String> temp = new ArrayList<>();

        String line = null;
        
        
		//dosyayi okuma
        try {
        	
            FileReader fileReader = new FileReader(filename);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                temp.add(line);
            }   

            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '"+ filename + "'");         
        }
        
		return temp;
		
	}
	
	public static boolean writeFile(String filename, List<String> lines)
	{
        
        //dosyaya yazma
		try {
            FileWriter fileWriter = new FileWriter(filename);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //okunan dosyadaki satirlardaki rakamlari ters cevirme
            for (String l : lines){
            	bufferedWriter.write(l);
            	bufferedWriter.newLine();
            }
            
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + filename + "'");
            return false;
        }
		
		return true;
	}
	
	public static double calculate(String val,String op)
	{
		if (op.equals("+")) //+ karakteri regex olduğundan '\+' ile yer degistirilmistir.
			op = "\\"+op;
		
		String[] temp = val.split(op);
		String val1 = temp[0];
		String val2 = temp[1];
		
		double d = 0.0;
		
		switch (op) 
		{
			case "\\+":
				d = Double.parseDouble(val1) + Double.parseDouble(val2);
				break;
			case "-":
				d = Double.parseDouble(val1) - Double.parseDouble(val2);
				break;
			case "*":
				d = Double.parseDouble(val1) * Double.parseDouble(val2);
				break;
			case "/":
				d = Double.parseDouble(val1) / Double.parseDouble(val2);
				break;
		}
		return d;
	}

}
