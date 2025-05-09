package scrappers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import Commons.ExcelReader;
import Commons.LoggerLoad;

public class FilterAllergytables {
	
	ArrayList<String> allergycontents = new ArrayList<String>();
	
	public ArrayList<String> allergylist(String ingredients) throws IOException {
		ExcelReader er = new ExcelReader();
		allergycontents.clear();
		String sheetname = "Allergies";
		String allergyitem;
		int i,j;
		
		int totalcheckitems = er.readlastrowindex(1, 0, sheetname);
		
		for(i=1;i<=totalcheckitems;i++) 
		{
			allergyitem = er.readexcelvalue(i,0,sheetname);
			
				if(ingredients.contains(allergyitem))
					allergycontents.add(allergyitem);
			}
		
		
		int len = allergycontents.size();
		  for(j=0;j<len;j++)
		  {			  
			  if (allergycontents.get(j).contentEquals("peanut") || allergycontents.get(j).contentEquals("walnut") || allergycontents.get(j).contentEquals("almond") || allergycontents.get(j).contentEquals("hazelnut") || allergycontents.get(j).contentEquals("pecan") || allergycontents.get(j).contentEquals("cashew") || allergycontents.get(j).contentEquals("pista")) 
	            {
	        	allergycontents.set(j, "nuts");
	            }
	        }
		  
		 allergycontents = (ArrayList<String>) allergycontents.stream().distinct().collect(Collectors.toList());
		  
		  
		  if(allergycontents.size()==0)
			  LoggerLoad.info("No Allergy content found");
		return allergycontents;
	}

}
