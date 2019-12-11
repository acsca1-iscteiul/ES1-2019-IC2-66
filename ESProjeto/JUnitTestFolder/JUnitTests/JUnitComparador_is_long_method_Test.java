package JUnitTests;

import org.junit.Before;
import static org.junit.Assert.*;

import javax.swing.table.DefaultTableModel;

import org.junit.Test;

import ferramentas.Comparador_is_long_method;
import junit.framework.TestCase;

public class JUnitComparador_is_long_method_Test extends TestCase {

	private DefaultTableModel origem;
	private DefaultTableModel destino;
	
	private int coluna1;
	private int coluna2;
	private int coluna3;
	private int coluna4;
	
	private Comparador_is_long_method comparadorISM1;
	private Comparador_is_long_method comparadorISM2;
	private Comparador_is_long_method comparadorISM3;
	private Comparador_is_long_method comparadorISM4;
	
	@Before
	public void setUp() throws Exception {
		
		origem = new DefaultTableModel();
		destino = new DefaultTableModel();
		
		coluna1= 2;
		coluna2= 3;
		coluna3=4;
		coluna4=5;
		
		comparadorISM1 = new Comparador_is_long_method(origem,destino,coluna1);
		comparadorISM2 = new Comparador_is_long_method(origem,destino,coluna2);
		comparadorISM3 = new Comparador_is_long_method(origem,destino,coluna3);
		comparadorISM4 = new Comparador_is_long_method(origem,destino,coluna4);
		
	}
	
	@Test
	public void test() {
	
	}
}
