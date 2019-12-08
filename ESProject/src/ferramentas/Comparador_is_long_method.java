package ferramentas;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class Comparador_is_long_method extends Thread {

	private DefaultTableModel origem ;
	private DefaultTableModel destino;
	private int coluna;

	public Comparador_is_long_method(DefaultTableModel origem, DefaultTableModel destino, int coluna) {
		this.origem=origem;
		this.destino=destino;
		this.coluna=coluna;
	}

	@Override
	public void run() {
		if(destino.getRowCount()>0) {
			destino.removeRow(0);
		}
		int dci=0, dii=0, adci=0, adii=0;
		for (int i = 0; i < origem.getRowCount(); i++) {
			if(String.valueOf(origem.getValueAt(i, 8)).equals("TRUE") && String.valueOf(origem.getValueAt(i, coluna)).equals("TRUE") ) {
				dci++;
			}
			if(String.valueOf(origem.getValueAt(i, 8)).equals("TRUE") && String.valueOf(origem.getValueAt(i, coluna)).equals("FALSE") ) {
				dii++;
			}
			if(String.valueOf(origem.getValueAt(i, 8)).equals("FALSE") && String.valueOf(origem.getValueAt(i, coluna)).equals("FALSE") ) {
				adci++;
			}
			if(String.valueOf(origem.getValueAt(i, 8)).equals("FALSE") && String.valueOf(origem.getValueAt(i, coluna)).equals("TRUE") ) {
				adii++;
			}
		}
		String[] v = {String.valueOf(dci),String.valueOf(dii),String.valueOf(adci),String.valueOf(adii)};
		destino.addRow(v);
	}

}
