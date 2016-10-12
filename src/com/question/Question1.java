package com.question;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question1 {
	
	public static void main(String[] args) {
	
        String fileCsvToReverse = "data/CsvToReverse.txt"; // okunacak dosya
        String fileReverseCsv = "data/ReverseCsv.txt"; // tersine siralanmis hali 
        
        //soru 1 cevabi 
        writeFile(fileReverseCsv, readFile(fileCsvToReverse));
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
	
	public static boolean writeFile(String filename, List<String> lines){
		
        String[] characters;
        
        //dosyaya yazma
		try {
            FileWriter fileWriter = new FileWriter(filename);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //okunan dosyadaki satirlardaki rakamlari ters cevirme
            for (String l : lines){
            	characters = l.split(",");
            	Collections.reverse(Arrays.asList(characters));
            	bufferedWriter.write(Arrays.toString(characters).replaceAll("\\[|\\]|\\s", ""));
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

}
