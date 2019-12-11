package JUnitTests;

import org.junit.Before;
import javax.swing.table.DefaultTableModel;
import ferramentas.Comparador_is_long_method_Thresholds;

public class JUnitComparador_is_long_method_ThresholdsTest {
	
	private DefaultTableModel excel;
	private DefaultTableModel threshold;
	private DefaultTableModel destino;
	
	private Comparador_is_long_method_Thresholds comparadorISMT1;
	private Comparador_is_long_method_Thresholds comparadorISMT2;
	private Comparador_is_long_method_Thresholds comparadorISMT3;

	@Before
	public void setUp() throws Exception {
		
		excel = new DefaultTableModel();
		threshold = new DefaultTableModel();
		destino = new DefaultTableModel();
		
		excel.addColumn("M_ID");
		excel.addColumn("package");
		excel.addColumn("class");
		excel.addColumn("method");
		excel.addColumn("loc");
		excel.addColumn("cyclo");
		excel.addColumn("atfd");
		excel.addColumn("laa");
		excel.addColumn("M_ID");
		excel.addColumn("iPlasma");
		excel.addColumn("PMD");
		excel.addColumn("is_feature_envy");
		
		String [] dados1 = {"1","fat","DocumentParseFixture","Output()", "3","1","0","1","FALSE","FALSE","FALSE","FALSE"};
		String [] dados2 = {"1","fat","DocumentParseFixture","Output()", "45","32","34","90","FALSE","TRUE","TRUE","FALSE"};
		String [] dados3 = {"1","fat","DocumentParseFixture","Output()", "30","2","70","30","TRUE","TRUE","TRUE","FALSE"};
		String [] dados4 = {"1","fat","DocumentParseFixture","Output()", "3","1","0","1","TRUE","FALSE","FALSE","FALSE"};
		
		excel.addRow(dados1);
		excel.addRow(dados2);
		excel.addRow(dados3);
		excel.addRow(dados4);
		
		threshold.addColumn("M_ID");
		threshold.addColumn("M_ID");
		threshold.addColumn("is_feature_envy");
		
		String [] dadosth1 = {"1","FALSE","FALSE"};
		String [] dadosth2 = {"1","TRUE","FALSE"};
		String [] dadosth3 = {"1","FALSE","FALSE"};
		String [] dadosth4 = {"1","TRUE","FALSE"};
		
		threshold.addRow(dadosth1);
		threshold.addRow(dadosth2);
		threshold.addRow(dadosth3);
		threshold.addRow(dadosth4);
		
		comparadorISMT1 = new Comparador_is_long_method_Thresholds(excel,threshold,destino);
		comparadorISMT2 = new Comparador_is_long_method_Thresholds(excel,threshold,destino);
		comparadorISMT3 = new Comparador_is_long_method_Thresholds(excel,threshold,destino);
	
	}
}
