package JUnitTests;

import org.junit.Before;
import static org.junit.Assert.*;

import javax.swing.table.DefaultTableModel;

import org.junit.Test;

import ferramentas.Comparador_is_long_method_Thresholds;

public class JUnitComparador_is_long_method_ThresholdsTest {
	
	private Comparador_is_long_method_Thresholds comparadorISMT1;
	private Comparador_is_long_method_Thresholds comparadorISMT2;
	private Comparador_is_long_method_Thresholds comparadorISMT3;
	private Comparador_is_long_method_Thresholds comparadorISMT4;
	
	private DefaultTableModel excel;
	private DefaultTableModel threshold;
	private DefaultTableModel destino;

	

	@Before
	public void setUp() throws Exception {
		
		excel = new DefaultTableModel();
		threshold = new DefaultTableModel();
		destino = new DefaultTableModel();
		
		comparadorISMT1 = new Comparador_is_long_method_Thresholds(excel,threshold,destino);
		comparadorISMT2 = new Comparador_is_long_method_Thresholds(excel,threshold,destino);
		comparadorISMT3 = new Comparador_is_long_method_Thresholds(excel,threshold,destino);
	
	}
	
	@Test	
	public void test() {
	
	}
}
