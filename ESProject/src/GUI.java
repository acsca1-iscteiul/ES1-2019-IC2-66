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
	private JTable table_MT_Threshold;

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
		//		model.addColumn("Code");
		//		model.addColumn("Name");
		//		model.addColumn("Quantity");
		//		model.addColumn("Unit Price");
		//		model.addColumn("Price");

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
								//							XSSFCell excelName = excelRow.getCell(0);
								//							XSSFCell excelGender = excelRow.getCell(1);
								//							XSSFCell excelProgrammingLanguage = excelRow.getCell(2);
								//							XSSFCell excelSubject = excelRow.getCell(3);
								//							XSSFCell excelImage = excelRow.getCell(4);
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
		
		table_iPlasma = new JTable();
		
		table_PMD = new JTable();
		
		table_Threshold = new JTable();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lbl_iPlasma = new JLabel("iPlasma");
		lbl_iPlasma.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));
		
		JLabel lbl_Pmd = new JLabel("PMD");
		lbl_Pmd.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));
		
		JLabel lblThreshold = new JLabel("Threshold");
		lblThreshold.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		GroupLayout gl_contentPane_2 = new GroupLayout(contentPane_2);
		gl_contentPane_2.setHorizontalGroup(
			gl_contentPane_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane_2.createSequentialGroup()
					.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane_2.createSequentialGroup()
							.addComponent(label_ficheiro, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(botao_pesquisar, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
							.addGap(140))
						.addGroup(gl_contentPane_2.createSequentialGroup()
							.addGap(12)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
							.addGap(19)))
					.addGroup(gl_contentPane_2.createParallelGroup(Alignment.TRAILING, false)
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
											.addComponent(TF_LAA, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
									.addGap(9))
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(12)
									.addComponent(label_OL)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(CB_OL, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)))
							.addGap(0))
						.addGroup(gl_contentPane_2.createSequentialGroup()
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lbl_iPlasma))
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(16)
									.addComponent(lbl_Pmd)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(table_PMD, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(table_iPlasma, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(gl_contentPane_2.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(table_Threshold, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addComponent(botao_iniciar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, gl_contentPane_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
					.addGap(13))
				.addGroup(gl_contentPane_2.createSequentialGroup()
					.addContainerGap(528, Short.MAX_VALUE)
					.addComponent(lblThreshold)
					.addGap(307))
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
							.addGap(28)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
							.addGap(18))
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
									.addGap(18)
									.addComponent(table_iPlasma, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(table_PMD, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addGap(27)
									.addComponent(table_Threshold, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane_2.createSequentialGroup()
									.addGap(30)
									.addComponent(lbl_iPlasma)
									.addGap(49)
									.addComponent(lbl_Pmd)
									.addGap(56)
									.addComponent(lblThreshold)))))
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(botao_iniciar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
		);
		
		table_MT_Threshold = new JTable();
		scrollPane.setViewportView(table_MT_Threshold);
		tabela_excel = new JTable(model);
		scrollPane_1.setViewportView(tabela_excel);
		tabela_excel.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		contentPane_2.setLayout(gl_contentPane_2);
	}
}

