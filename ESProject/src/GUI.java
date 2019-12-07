import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import javax.swing.JTabbedPane;

public class GUI extends JFrame {

	private JPanel contentPane_2;
	private JTextField TF_CYCLO;
	private JTextField TF_LOC;
	private JTable tabela_excel;
	private JTextField TF_ATFD;
	private JTextField TF_LAA;
	private JTable table_iPlasma;
	private JTable table_PMD;
	private JTable table_Threshold;
	private JTable table_Results;
	private JTable table_Threshold_results;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("ESProject");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 566);
		contentPane_2 = new JPanel();
		contentPane_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_2);

		JLabel label_ficheiro = new JLabel("Ficheiro:");
		label_ficheiro.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JLabel label_LOC = new JLabel("LOC:");
		label_LOC.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JLabel label_CYCLO = new JLabel("CYCLO:");
		label_CYCLO.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		TF_CYCLO = new JTextField();
		TF_CYCLO.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		TF_CYCLO.setColumns(10);

		TF_LOC = new JTextField();
		TF_LOC.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		TF_LOC.setColumns(10);

		JButton botao_iniciar = new JButton("Iniciar");
		botao_iniciar.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JLabel label_OL = new JLabel("Operador L\u00F3gico:");
		label_OL.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JComboBox CB_OL = new JComboBox();
		CB_OL.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		CB_OL.setForeground(Color.BLACK);
		CB_OL.setModel(new DefaultComboBoxModel(new String[] {"AND", "OR"}));

		DefaultTableModel model = new DefaultTableModel();
		DefaultTableModel model_iPlasma = new DefaultTableModel();
		DefaultTableModel model_PMD = new DefaultTableModel();
		DefaultTableModel model_Threshold = new DefaultTableModel();
		DefaultTableModel model_Threshold_results = new DefaultTableModel();
		DefaultTableModel model_Results = new DefaultTableModel();
		String[] columns = {"DCI","DII","ADCI","ADII"};
		for (String string : columns) {
			model_iPlasma.addColumn(string);
			model_PMD.addColumn(string);
			model_Threshold.addColumn(string);
		}
		model_Threshold_results.addColumn("M_ID");
		model_Threshold_results.addColumn("is_long_Method");
		model_Threshold_results.addColumn("is_feature_envy");
		model_Results.addColumn("M_ID");
		model_Results.addColumn("iPlasma");
		model_Results.addColumn("PMD");
		model_Results.addColumn("Thresholds");

		JButton botao_pesquisar = new JButton("Pesquisar");
		botao_pesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				File excelFile;
				FileInputStream excelFIS = null;
				BufferedInputStream excelBIS = null;
				XSSFWorkbook excelJTableImport = null;

				//select default path 
				String defaultCurrentDirectoryPath = "caminho_ate_excel";
				JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
				int excelChooser = excelFileChooser.showOpenDialog(null);


				//filter only excel files 
				FileNameExtensionFilter  fnef = new FileNameExtensionFilter ("EXCEL FILES", "xls", "xlsx", "xlsm");
				excelFileChooser.setFileFilter(fnef);

				//set dialogue title
				excelFileChooser.setDialogTitle("Select excel file");
				int excelchooser = excelFileChooser.showOpenDialog(null);

				// if button is clicked

				if(excelChooser==JFileChooser.APPROVE_OPTION)
				{
					try {
						excelFile = excelFileChooser.getSelectedFile();
						excelFIS = new FileInputStream(excelFile);
						excelBIS = new BufferedInputStream(excelFIS);
						excelJTableImport = new XSSFWorkbook(excelBIS);
						XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);

						// looping pelas linhas e colunas
						boolean first_line=true;
						for (int row = 0; row < excelSheet.getLastRowNum(); row++) {
							XSSFRow excelRow = excelSheet.getRow(row);
							XSSFCell[] cells = new XSSFCell[excelRow.getLastCellNum()];
							for(int i=0; i<excelRow.getLastCellNum(); i++) {
								cells[i]=excelRow.getCell(i);
							}
							if(first_line) {
								for (XSSFCell xssfCell : cells) {
									model.addColumn(xssfCell);
								}
								first_line=false;
							}
							else
								model.addRow(cells);

							//							JOptionPane.showMessageDialog(null, "IMPORTED SUCCESSFULLY!");

						}
					} catch (FileNotFoundException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					} finally {
						if (excelFIS != null) {
							try {
								excelFIS.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (excelBIS != null) {
							try {
								excelBIS.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}

		});
		botao_pesquisar.setFont(new Font("Verdana Pro", Font.PLAIN, 16));
		botao_pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();

		JLabel label_ATFD = new JLabel("ATFD:");
		label_ATFD.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JLabel label_LAA = new JLabel("LAA:");
		label_LAA.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		TF_ATFD = new JTextField();
		TF_ATFD.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		TF_ATFD.setColumns(10);

		TF_LAA = new JTextField();
		TF_LAA.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		TF_LAA.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();

		JLabel lbl_iPlasma = new JLabel("iPlasma");
		lbl_iPlasma.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JLabel lbl_Pmd = new JLabel("PMD");
		lbl_Pmd.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JLabel lblThreshold = new JLabel("Threshold");
		lblThreshold.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JScrollPane scrollPane_2 = new JScrollPane();

		JScrollPane scrollPane_3 = new JScrollPane();

		JScrollPane scrollPane_4 = new JScrollPane();

		JScrollPane scrollPane_Thresholds = new JScrollPane();

		GroupLayout gl_contentPane_2 = new GroupLayout(contentPane_2);
		gl_contentPane_2.setHorizontalGroup(
			gl_contentPane_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane_2.createSequentialGroup()
					.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane_2.createSequentialGroup()
							.addComponent(label_ficheiro, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(botao_pesquisar, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
							.addGap(140))
						.addGroup(gl_contentPane_2.createSequentialGroup()
							.addGap(12)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane_2.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane_2.createSequentialGroup()
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(21)
									.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane_2.createSequentialGroup()
											.addComponent(label_LOC)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(TF_LOC, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane_2.createSequentialGroup()
											.addComponent(label_ATFD)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(TF_ATFD, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane_2.createSequentialGroup()
											.addComponent(label_CYCLO, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(TF_CYCLO, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane_2.createSequentialGroup()
											.addComponent(label_LAA)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(TF_LAA, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(12)
									.addComponent(label_OL)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(CB_OL, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)))
							.addGap(9))
						.addGroup(gl_contentPane_2.createSequentialGroup()
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblThreshold)
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(21)
									.addComponent(lbl_Pmd))
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(8)
									.addComponent(lbl_iPlasma)))
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(32)
									.addGroup(gl_contentPane_2.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(scrollPane_3, 0, 0, Short.MAX_VALUE)
										.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)))
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(33)
									.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)))))
					.addGap(9))
				.addGroup(gl_contentPane_2.createSequentialGroup()
					.addComponent(botao_iniciar, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane_2.createSequentialGroup()
					.addGap(13)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
					.addGap(42)
					.addComponent(scrollPane_Thresholds, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane_2.setVerticalGroup(
			gl_contentPane_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane_2.createSequentialGroup()
					.addGroup(gl_contentPane_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_ficheiro, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(TF_LOC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_LOC)
						.addComponent(botao_pesquisar)
						.addComponent(label_CYCLO)
						.addComponent(TF_CYCLO, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane_2.createSequentialGroup()
							.addGap(13)
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_ATFD)
								.addComponent(TF_ATFD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_LAA)
								.addComponent(TF_LAA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_OL)
								.addComponent(CB_OL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(33)
									.addComponent(lbl_iPlasma))
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(18)
									.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(50)
									.addComponent(lbl_Pmd))
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(30)
									.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(40)
									.addComponent(lblThreshold))
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(26)
									.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane_2.createSequentialGroup()
							.addGap(28)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
					.addGap(18)
					.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_Thresholds, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addComponent(botao_iniciar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
		);

		table_Threshold_results = new JTable(model_Threshold_results);
		scrollPane_Thresholds.setViewportView(table_Threshold_results);

		table_iPlasma = new JTable(model_iPlasma);
		scrollPane_4.setViewportView(table_iPlasma);

		table_PMD = new JTable(model_PMD);
		scrollPane_3.setViewportView(table_PMD);

		table_Threshold = new JTable(model_Threshold);
		scrollPane_2.setViewportView(table_Threshold);

		table_Results = new JTable(model_Results);
		scrollPane.setViewportView(table_Results);
		tabela_excel = new JTable(model);
		scrollPane_1.setViewportView(tabela_excel);
		tabela_excel.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		contentPane_2.setLayout(gl_contentPane_2);
	}
}

