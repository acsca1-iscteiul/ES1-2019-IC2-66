package JUnitTests;

import org.junit.Before;
import javax.swing.table.DefaultTableModel;
import org.junit.Test;
import Interface.GUI;
import ferramentas.Comparador_is_long_method;
import junit.framework.TestCase;

public class JUnitComparador_is_long_method_Test extends TestCase {

	private GUI gui;

	private DefaultTableModel origem;
	private DefaultTableModel destino;

	private int coluna1;
	private int coluna2;

	private Comparador_is_long_method comparadorISM1;
	private Comparador_is_long_method comparadorISM2;

	@Before
	public void setUp() throws Exception {
		
		gui = new GUI();

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
		
		String [] dados1 = {"1","fat","DocumentParseFixture","Output()", "3","1","0","1","FALSE","FALSE","FALSE","FALSE"};
		String [] dados2 = {"1","fat","DocumentParseFixture","Output()", "45","32","34","90","FALSE","TRUE","TRUE","FALSE"};
		String [] dados3 = {"1","fat","DocumentParseFixture","Output()", "30","2","70","30","TRUE","TRUE","TRUE","FALSE"};
		String [] dados4 = {"1","fat","DocumentParseFixture","Output()", "3","1","0","1","TRUE","FALSE","FALSE","FALSE"};
		
		origem.addRow(dados1);
		origem.addRow(dados2);
		origem.addRow(dados3);
		origem.addRow(dados4);
		
		coluna1= 9;
		coluna2= 10;

		comparadorISM1 = new Comparador_is_long_method(origem,destino,coluna1);
		comparadorISM2 = new Comparador_is_long_method(origem,destino,coluna2);
		
	}

	@Test
	public void test() {
		
		comparadorISM1.start();
		comparadorISM2.start();
		
	}
}




















