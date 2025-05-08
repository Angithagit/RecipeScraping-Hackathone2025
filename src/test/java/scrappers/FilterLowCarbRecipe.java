package scrappers;

import java.io.IOException;

import Commons.ExcelReader;
import Commons.LoggerLoad;

public class FilterLowCarbRecipe {
	String tablename;
	ExcelReader er = new ExcelReader();
	String eliminatethis, addthis, avoidthismethod;
	
	public String recipecheckLowCarbdiet(String ingredient, String method) throws IOException {
		
		boolean recipelchfveliminated = checklchfeliminationlist(ingredient);
		boolean checklchfvaddlist = checklchfaddlist(ingredient);
		boolean checkavoidmethod = checkavoidmethod(method);
		boolean acceptedcookingmethod = checkcookingmethod(method);
		
		LoggerLoad.info("Recipe LCHF eliminated: "+recipelchfveliminated);
			if(recipelchfveliminated == false) 
			{
					if(checklchfvaddlist == true && acceptedcookingmethod == true) 
					{
						if(checkavoidmethod == true)
						tablename = "LCHF eliminated";
						else
						tablename = "LCHF Added";
					}	
					else
						tablename = "LCHF eliminated";
			}
			else
				tablename = "LCHF eliminated";

		return tablename;
	}
    
	public boolean checklchfeliminationlist(String ingredient) throws IOException {
		int i;
		boolean eliminate = false;
		String sheetname = "LCFH";
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
						LoggerLoad.info("Recipe Rejected by Low Carb High Fat table. Has ingredient: "+eliminatethis);
				return eliminate;
	}
	
	public boolean checklchfaddlist(String ingredient) throws IOException {
		int i;
		boolean add = false;
		String sheetname = "LCFH";
		int totalcheckitems = er.readlastrowindex(1, 1, sheetname);
		for(i=1;i<=totalcheckitems;i++) 
			{
					addthis = er.readexcelvalue(i,1,sheetname);
					if(ingredient.contains(addthis))
						add = true;
			}
		if(add==true)
			LoggerLoad.info("Recipe has Low Carb High Fat ingredient");
			else
				LoggerLoad.info("Recipe Rejected by Low Carb High Fat Ingredient table");
		return add;
	}
	public boolean checkavoidmethod(String method) throws IOException {
		int i;
		boolean avoid = false;
		String sheetname = "LCFH";
		int totalcheckitems = er.readlastrowindex(1, 2, sheetname);
		
				for(i=1;i<=totalcheckitems;i++) 
					{
						if(avoid == false)
						{
							avoidthismethod = er.readexcelvalue(i,2,sheetname);
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
	public boolean checkcookingmethod(String method) throws IOException {
		int i;
		boolean add = false;
		String sheetname = "LCFH";
		int totalcheckitems = er.readlastrowindex(1, 3, sheetname);
		for(i=1;i<=totalcheckitems;i++) 
			{
					addthis = er.readexcelvalue(i,3,sheetname);
					if(method.contains(addthis))
						add = true;
			}
		return add;
	}
}