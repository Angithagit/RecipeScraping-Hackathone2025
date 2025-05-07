package Scrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Commons.ExcelReader;
import Commons.LoggerLoad;

public class FilterLowFatRecipe {
	String tablename;
	ExcelReader er = new ExcelReader();
	String eliminatethis, addthis, avoidthismethod;
	
	
	public String recipecheckLowFatdiet(String ingredient, String method) throws IOException {
		boolean recipelfveliminated = checklfveliminationlist(ingredient);
		boolean checklfvaddlist = checklfvaddlist(ingredient);
		boolean checkavoidmethod = checkavoidmethod(method);
		
			if(recipelfveliminated == false) 
			{
					if(checklfvaddlist == true) 
					{
						if(checkavoidmethod == true)
						tablename = "LFV eliminated";
						else
						tablename = "LFV Added";
					}	
					else
						tablename = "LFV eliminated";
			}
			else
				tablename = "LFV eliminated";
			
			if(tablename == "LFV eliminated" && checklfvaddlist == true && checkavoidmethod == false)
			{
				boolean eliminateafternonvegancheck = checklfnonveganaddlist(ingredient);
				if(eliminateafternonvegancheck == false)
					tablename = "LF Nonvegan Added";		
			}
		return tablename;
	}
    
	public boolean checklfveliminationlist(String ingredient) throws IOException {
		int i;
		boolean eliminate = false;
		String sheetname = "LFD";
		int totalcheckitems = er.readlastrowindex(1, 0, sheetname);
		
				for(i=1;i<=totalcheckitems;i++) 
					{
						if(eliminate == false)
						{
							eliminatethis = er.readexcelvalue(i,0,sheetname);
							if(ingredient.contains(eliminatethis))
								eliminate = true;
						}
					}
				if(eliminate==false)
					LoggerLoad.info("Recipe can be included");
					else
						LoggerLoad.info("Recipe Rejected by Low Fat vegan table. Has ingredient: "+eliminatethis);
				return eliminate;
	}
	
	public boolean checklfvaddlist(String ingredient) throws IOException {
		int i;
		boolean add = false;
		String sheetname = "LFD";
		int totalcheckitems = er.readlastrowindex(1, 1, sheetname);
		for(i=1;i<=totalcheckitems;i++) 
			{
					addthis = er.readexcelvalue(i,1,sheetname);
					if(ingredient.contains(addthis))
						add = true;
			}
		if(add==true)
			LoggerLoad.info("Recipe has Low Fat Vegan ingredient");
			else
				LoggerLoad.info("Recipe Rejected by Low Fat Vegan Ingredient table");
		return add;
		
	}
	
	public boolean checklfnonveganaddlist(String ingredient) throws IOException {
		int i;
		boolean eliminate = false;
		String sheetname = "LFD";
		
		int totalnonveganadditems = er.readlastrowindex(1, 2, sheetname);
		ArrayList<String> nonveganlist = new ArrayList<String>();
		for(i=1;i<=totalnonveganadditems;i++) 
		{
			nonveganlist.add(er.readexcelvalue(i,2,sheetname));
		}
	
		int totalcheckitems = er.readlastrowindex(1, 0, sheetname);
					for(i=1;i<=totalcheckitems;i++) 
					{
						if(eliminate == false)
						{
							eliminatethis = er.readexcelvalue(i,0,sheetname);
							if(nonveganlist.contains(eliminatethis))
								break; 
							else if(ingredient.contains(eliminatethis))
								eliminate = true;
						}
					}
				if(eliminate==false)
					LoggerLoad.info("Recipe can be included in Non Vegan table");
					else
						LoggerLoad.info("Recipe Rejected by Non Vegan table");
				return eliminate;
	}

	
	public boolean checkavoidmethod(String method) throws IOException {
		int i;
		boolean avoid = false;
		String sheetname = "LFD";
		int totalcheckitems = er.readlastrowindex(1, 3, sheetname);
		
				for(i=1;i<=totalcheckitems;i++) 
					{
						if(avoid == false)
						{
							avoidthismethod = er.readexcelvalue(i,3,sheetname);
							if(method.contains(avoidthismethod))
								avoid = true;
						}
					}
				if(avoid==false)
					LoggerLoad.info("Cooking Method accepted");
					else
						LoggerLoad.info("Cooking Method Rejected");
				return avoid;
	}
	
}
