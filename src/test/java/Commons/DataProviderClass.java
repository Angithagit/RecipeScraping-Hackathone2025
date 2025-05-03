package Commons;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	
	@DataProvider(name = "LFVEliminate")
	public Object[][] LFVEliminatelist() throws IOException {
		ExcelReader read = new ExcelReader();
		int i,j=0;
		Object[ ][ ] data = new Object[74][1];
		for(i=2;i<=75;i++) {
		data[j][0] = read.LFV_checklist(i,0);
		j++;
		}
		return data;
		}
	
	@DataProvider(name = "LFVAdd")
	public Object[][] LFVAddlist() throws IOException {
		ExcelReader read = new ExcelReader();
		int i,j=0;
		Object[ ][ ] data = new Object[88][1];
		for(i=2;i<=89;i++) {
		data[j][0] = read.LFV_checklist(i,1);
		j++;
		}
		return data;
		}
	
	@DataProvider(name = "LFVToAdd")
	public Object[][] LFVToAddlist() throws IOException {
		ExcelReader read = new ExcelReader();
		int i,j=0;
		Object[ ][ ] data = new Object[5][1];
		for(i=2;i<=6;i++) {
		data[j][0] = read.LFV_checklist(i,2);
		j++;
		}
		return data;
		}
	
	
	@DataProvider(name = "LFVToAvoidRecipe")
	public Object[][] LFVToAvoidRecipe() throws IOException {
		ExcelReader read = new ExcelReader();
		int i,j=0;
		Object[ ][ ] data = new Object[4][1];
		for(i=2;i<=5;i++) {
		data[j][0] = read.LFV_checklist(i,3);
		j++;
		}
		return data;
		}
		
	@DataProvider(name = "LFVOptionalRecipe")
	public Object[][] LFVOptionalRecipe() throws IOException {
		ExcelReader read = new ExcelReader();
		int i,j=0;
		Object[ ][ ] data = new Object[2][1];
		for(i=2;i<=3;i++) {
		data[j][0] = read.LFV_checklist(i,4);
		j++;
		}
		return data;
		}
	
	@DataProvider(name = "LCHFEliminate")
	public Object[][] LCHFEliminatelist() throws IOException {
		ExcelReader read = new ExcelReader();
		int i,j=0;
		Object[ ][ ] data = new Object[90][1];
		for(i=2;i<=91;i++) {
		data[j][0] = read.LCHF_checklist(i,0);
		j++;
		}
		return data;
		}
	
	@DataProvider(name = "LCHFAdd")
	public Object[][] LCHFAddlist() throws IOException {
		ExcelReader read = new ExcelReader();
		int i,j=0;
		Object[ ][ ] data = new Object[32][1];
		for(i=2;i<=33;i++) {
		data[j][0] = read.LCHF_checklist(i,1);
		j++;
		}
		return data;
		}
	
	@DataProvider(name = "LCHFRecipestoAvoid")
	public Object[][] LCHFRecipestoAvoid() throws IOException {
		ExcelReader read = new ExcelReader();
		int i,j=0;
		Object[ ][ ] data = new Object[1][1];
		for(i=2;i<=2;i++) {
		data[j][0] = read.LCHF_checklist(i,2);
		j++;
		}
		return data;
		}
	@DataProvider(name = "LCHFFoodProcessing")
	public Object[][] LCHFProcessing() throws IOException {
		ExcelReader read = new ExcelReader();
		int i,j=0;
		Object[ ][ ] data = new Object[7][1];
		for(i=2;i<=8;i++) {
		data[j][0] = read.LCHF_checklist(i,3);
		j++;
		}
		return data;
		}
	
	@DataProvider(name = "AllergyList")
	public Object[][] AllergyList() throws IOException {
		ExcelReader read = new ExcelReader();
		int i,j=0;
		Object[ ][ ] data = new Object[13][1];
		for(i=1;i<=13;i++) {
		data[j][0] = read.Allergy_list(i,0);
		j++;
		}
		return data;
		}
}
