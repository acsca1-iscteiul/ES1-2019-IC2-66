package JUnitTests;



import javax.swing.table.DefaultTableModel;
import org.junit.jupiter.api.Test;

import Interface.GUI;

import org.junit.Assert;
import org.junit.Before;

import ferramentas.Avaliador_Thresholds;
import junit.framework.TestCase;

public class JUnitAvaliador_ThresholdsTest extends TestCase{

	private GUI gui;
	
	private DefaultTableModel origem;
	private DefaultTableModel destino;

	private Avaliador_Thresholds avaliadorT1;
	private Avaliador_Thresholds avaliadorT2;
	private Avaliador_Thresholds avaliadorT3;
	private Avaliador_Thresholds avaliadorT4;

	@Before
	public void setUp() throws Exception {
		
		gui = new GUI();

		int loc1 =50;
		int loc2 =30;
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
		double laa4 = 1.2;

		origem = gui.getModel(); 
		destino = new DefaultTableModel(); 
		
		origem.addColumn("M_ID");
		origem.addColumn("package");
		origem.addColumn("class");
		origem.addColumn("method");
		origem.addColumn("loc");
		origem.addColumn("cyclo");
		origem.addColumn("atfd");
		origem.addColumn("laa");
		origem.addColumn("is_long_method");
		origem.addColumn("iPlasma");
		origem.addColumn("PMD");
		origem.addColumn("is_feature_envy");
		
		String [] dados1 = {"1","fat","DocumentParseFixture","Output()", "3","1","0","1","FALSE","FALSE","FALSO","FALSO"};
		String [] dados2 = {"1","fat","DocumentParseFixture","Output()", "45","32","34","90","FALSE","FALSE","FALSO","FALSO"};
		String [] dados3 = {"1","fat","DocumentParseFixture","Output()", "30","2","70","30","FALSE","FALSE","FALSO","FALSO"};
		String [] dados4 = {"1","fat","DocumentParseFixture","Output()", "3","1","0","1","FALSE","FALSE","FALSO","FALSO"};
		
		origem.addRow(dados1);
		origem.addRow(dados2);
		origem.addRow(dados3);
		origem.addRow(dados4);

		String op1 = "AND";
		String op2 = "OR";

		avaliadorT1 = new Avaliador_Thresholds(loc1, cyclo1, atfd1, laa1, origem, destino, op1);
		avaliadorT2 = new Avaliador_Thresholds(loc2, cyclo2, atfd2, laa2, origem, destino, op2);
		avaliadorT3 = new Avaliador_Thresholds(loc3, cyclo3, atfd3, laa3, origem, destino, op1);
		avaliadorT4 = new Avaliador_Thresholds(loc4, cyclo4, atfd4, laa4, origem, destino, op2);
		
	}

	@Test	
	public void test() {
		
		assertEquals(avaliadorT1.isInteger("testeES"), avaliadorT2.isInteger("testeES"));
		assertEquals(avaliadorT1.isInteger("testeES"), avaliadorT2.isInteger("-"));
		assertEquals(avaliadorT1.isNumeric(null),avaliadorT2.isNumeric(null));
		assertEquals(avaliadorT1.isNumeric("1000.00.00"),avaliadorT2.isNumeric("1000.00.00"));
		assertEquals(avaliadorT1.isNumeric("1"),avaliadorT2.isNumeric("1"));
		assertTrue(avaliadorT1.isInteger("1"));
		assertFalse(avaliadorT1.isInteger(""));
		assertFalse(avaliadorT1.isInteger("-"));
		assertTrue(avaliadorT1.isInteger("-1"));
		
	}
}
