package ferramentas;

import javax.swing.table.DefaultTableModel;

public class Avaliador_Thresholds {
	private int loc;
	private int cyclo;
	private int atfd;
	private double laa;
	private DefaultTableModel origem;
	private DefaultTableModel destino;
	private String op;



	public Avaliador_Thresholds(int loc, int cyclo, int atfd, double laa, DefaultTableModel origem, DefaultTableModel destino, String op) {
		this.loc = loc;
		this.cyclo = cyclo;
		this.atfd = atfd;
		this.laa = laa;
		this.origem = origem;
		this.destino = destino;
		this.op = op;
		preenche_tabela();
	}


	public void preenche_tabela() {
		

		for (int i = 0; i < origem.getRowCount(); i++) {
			String[] info = new String[3];
			info[0]= String.valueOf(origem.getValueAt(i, 0));
			
			if(op.equals("AND")) {
				info[1]=String.valueOf(!(loc>Double.parseDouble(String.valueOf(origem.getValueAt(i, 4))) && cyclo>Double.parseDouble(String.valueOf(origem.getValueAt(i, 5)))));
				info[2]=String.valueOf(!(atfd>Double.parseDouble(String.valueOf(origem.getValueAt(i, 6))) && laa>Double.parseDouble(String.valueOf(origem.getValueAt(i, 7)))));
			}
			if(op.equals("OR")) {
				info[1]=String.valueOf(!(loc>Double.parseDouble(String.valueOf(origem.getValueAt(i, 4))) || cyclo>Double.parseDouble(String.valueOf(origem.getValueAt(i, 5)))));
				info[2]=String.valueOf(!(atfd>Double.parseDouble(String.valueOf(origem.getValueAt(i, 6))) || laa>Double.parseDouble(String.valueOf(origem.getValueAt(i, 7)))));
			}
			info[0]=info[0].toUpperCase();
			info[1]=info[1].toUpperCase();
			info[2]=info[2].toUpperCase();
			destino.addRow(info);
		}
	}

	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
