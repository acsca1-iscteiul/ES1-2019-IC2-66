package ferramentas;

import javax.swing.table.DefaultTableModel;

public class Comparador_is_long_method_Thresholds {
	private DefaultTableModel excel ;
	private DefaultTableModel threshold;
	private DefaultTableModel destino;
	
	public Comparador_is_long_method_Thresholds(DefaultTableModel excel, DefaultTableModel threshold, DefaultTableModel destino) {
		this.excel = excel;
		this.threshold = threshold;
		this.destino = destino;
		preencher_tabela();
	}
	
	private void preencher_tabela() {
		int dci=0, dii=0, adci=0, adii=0;
		for (int i = 0; i < excel.getRowCount(); i++) {
			if(String.valueOf(excel.getValueAt(i, 8)).equals("TRUE") && String.valueOf(threshold.getValueAt(i, 1)).equals("true") ) {
				dci++;
			}
			if(String.valueOf(excel.getValueAt(i, 8)).equals("TRUE") && String.valueOf(threshold.getValueAt(i, 1)).equals("false") ) {
				dii++;
			}
			if(String.valueOf(excel.getValueAt(i, 8)).equals("FALSE") && String.valueOf(threshold.getValueAt(i, 1)).equals("false") ) {
				adci++;
			}
			if(String.valueOf(excel.getValueAt(i, 8)).equals("FALSE") && String.valueOf(threshold.getValueAt(i, 1)).equals("true") ) {
				adii++;
			}
		}
		String[] v = {String.valueOf(dci),String.valueOf(dii),String.valueOf(adci),String.valueOf(adii)};
		destino.addRow(v);
	}
	
	

}
