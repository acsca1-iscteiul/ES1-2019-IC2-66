package testes;



import javax.swing.table.DefaultTableModel;
import static org.junit.Assert.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Interface.GUI;
import ferramentas.Avaliador_Thresholds;
import junit.framework.TestCase;

public class JUnitAvaliador_ThresholdsTest extends TestCase{

	private Avaliador_Thresholds avaliadorT1;
	private Avaliador_Thresholds avaliadorT2;
	private Avaliador_Thresholds avaliadorT3;
	private Avaliador_Thresholds avaliadorT4;

	DefaultTableModel origem;
	DefaultTableModel destino;

//	private GUI gui;



	@Before
	public void setUp() throws Exception {
//		gui = new GUI();

		int loc1 = 50;
		int loc2 = 30;
		int loc3=25;
		int loc4=2;

		int cyclo1 = 80;
		int cyclo2 = 40;
		int cyclo3 = 20;
		int cyclo4 = 3;

		int atfd1= 100;
		int atfd2 = 30;
		int atfd3 = 60;
		int atfd4 = 5;

		int laa1 = 120;
		int laa2 = 45;
		int laa3 = 20;
		int laa4 = 60;

		destino = new DefaultTableModel(); // DefaultTableModel leva um ficheiro excel XSSFWorkbook 
		origem = new DefaultTableModel();

		String op1 = "AND";
		String op2 = "OR";
		String op3 = " ";

		avaliadorT1 = new Avaliador_Thresholds(loc1, cyclo1, atfd1, laa1, origem, destino, op1);
		avaliadorT2 = new Avaliador_Thresholds(loc2, cyclo2, atfd2, laa2, origem, destino, op2);
		avaliadorT3 = new Avaliador_Thresholds(loc3, cyclo3, atfd3, laa3, origem, destino, op3);
		avaliadorT4 = new Avaliador_Thresholds(loc4, cyclo4, atfd4, laa4, origem, destino, op2);



	}

	@Test	
	public void testAvaliador1() {
//		origem.addColumn("M_ID");
//		origem.addColumn("is_long_Method");
//		origem.addColumn("is_feature_envy");
//		origem.addColumn("M_ID");
//		origem.addColumn("iPlasma");
//		origem.addColumn("PMD");
//		origem.addColumn("Thresholds");
		avaliadorT1.preenche_tabela();
	}

	@Test	
	public void testAvaliador2() {
		avaliadorT2.preenche_tabela();
	}
	@Test	
	public void testAvaliador3() {
		avaliadorT3.preenche_tabela();
	}
	@Test	
	public void testAvaliador4() {
		avaliadorT4.preenche_tabela();
	}
	


}
