import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Imp {

	private void JButton7ActionPerformed(java.awt.event.ActionEvent evt) {

		File excelFile;
		FileInputStream excelFIS = null;
		BufferedInputStream excelBIS = null;
		XSSFWorkbook excelJTableImport = null;

		//select default path 
		String defaultCurrentDirectoryPath = "caminho_ate_excel";
		JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
		int excelChooser = excelFileChooser.showOpenDialog(null);


		//filter only excel files 
		FileNameExtensionFilter  fnef = new FileNameExtensionFilter ("EXCEL FILES", "xls", "xlsx", "xlsm");
		excelFileChooser.setFileFilter(fnef);

		//set dialogue title
		excelFileChooser.setDialogTitle("Select excel file");
		int excelchooser = excelFileChooser.showOpenDialog(null);

		// if button is clicked

		if(excelChooser==JFileChooser.APPROVE_OPTION)
		{
			try {
				excelFile = excelFileChooser.getSelectedFile();
				excelFIS = new FileInputStream(excelFile);
				excelBIS = new BufferedInputStream(excelFIS);
				excelJTableImport = new XSSFWorkbook(excelBIS);
				XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);

				// looping pelas linhas e colunas

				for (int row = 0; row < excelSheet.getLastRowNum(); row++) {
					XSSFRow excelRow = excelSheet.getRow(row);

					XSSFCell excelName = excelRow.getCell(0);
					XSSFCell excelGender = excelRow.getCell(1);
					XSSFCell excelProgrammingLanguage = excelRow.getCell(2);
					XSSFCell excelSubject = excelRow.getCell(3);
					XSSFCell excelImage = excelRow.getCell(4);

					model.addRow(new Object[] { excelName, excelGender, excelProgrammingLanguage, excelSubject,
							excelImage });

					JOptionPane.showMessageDialog(null, "IMPORTED SUCCESSFULLY!");

				}
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			} finally {
				if (excelFIS != null) {
					excelFIS.close();
				}
				if (excelBIS != null) {
					excelBIS.close();
				}
			}
		}
	}
}