package JUnitTests;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.junit.Test;

import Interface.GUI;
import ferramentas.Comparador_is_long_method;
import junit.framework.TestCase;

public class JUnitComparador_is_long_method_Test extends TestCase {

	private DefaultTableModel origem;
	DefaultTableModel model_iPlasma;
	DefaultTableModel model_PMD;
	DefaultTableModel model_Threshold;
	private DefaultTableModel destino;

	private int coluna1;
	private int coluna2;
	private int coluna3;
	private int coluna4;

	private Comparador_is_long_method comparadorISM1;
	private Comparador_is_long_method comparadorISM2;
	private Comparador_is_long_method comparadorISM3;
	private Comparador_is_long_method comparadorISM4;

	private GUI gui;

	@Before
	public void setUp() throws Exception {
		

		gui = new GUI();


		origem = gui.getModel();
		destino = new DefaultTableModel();


		coluna1= 8;
		coluna2= 3;
		coluna3=4;
		coluna4=5;

		comparadorISM1 = new Comparador_is_long_method(origem,destino,coluna1);
		comparadorISM2 = new Comparador_is_long_method(origem,destino,coluna2);
		comparadorISM3 = new Comparador_is_long_method(origem,destino,coluna3);
		comparadorISM4 = new Comparador_is_long_method(origem,destino,coluna4);
		

		comparadorISM1.run();
		comparadorISM2.run();
		comparadorISM3.run();
		comparadorISM4.run();

	}

	@Test
	public void test() {

	}

}




















