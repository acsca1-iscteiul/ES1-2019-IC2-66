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

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblFifcheiro = new JLabel("Ficheiro:");
		lblFifcheiro.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("LOC:");
		lblNewLabel.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JLabel lblNewLabel_1 = new JLabel("CYCLO:");
		lblNewLabel_1.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);

		table = new JTable();
		table.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
				},
				new String[] {
						"New column", "New column", "New column"
				}
				));

		table_1 = new JTable();
		table_1.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
				},
				new String[] {
						"New column", "New column", "New column", "New column", "New column", "New column", "New column"
				}
				));

		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JLabel lblOperadorLgico = new JLabel("Operador L\u00F3gico:");
		lblOperadorLgico.setFont(new Font("Verdana Pro Black", Font.PLAIN, 15));

		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(Color.BLACK);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"AND", "OR"}));
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Code");
        model.addColumn("Name");
        model.addColumn("Quantity");
        model.addColumn("Unit Price");
        model.addColumn("Price");
		table_2 = new JTable(model);
		table_2.setFont(new Font("Verdana Pro", Font.PLAIN, 15));

		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.addMouseListener(new MouseAdapter() {
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

						for (int row = 0; row < excelSheet.getLastRowNum(); row++) {
							XSSFRow excelRow = excelSheet.getRow(row);

							XSSFCell excelName = excelRow.getCell(0);
							XSSFCell excelGender = excelRow.getCell(1);
							XSSFCell excelProgrammingLanguage = excelRow.getCell(2);
							XSSFCell excelSubject = excelRow.getCell(3);
							XSSFCell excelImage = excelRow.getCell(4);

							model.addRow(new Object[] { excelName, excelGender, excelProgrammingLanguage, excelSubject, excelImage });

							JOptionPane.showMessageDialog(null, "IMPORTED SUCCESSFULLY!");

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
		btnNewButton.setFont(new Font("Verdana Pro", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(table_1, GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
						.addComponent(btnIniciar, GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblFifcheiro, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(btnNewButton)
									.addGap(5))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(12)
									.addComponent(table_2, GroupLayout.PREFERRED_SIZE, 456, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblOperadorLgico)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(17)
										.addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textField_1, 0, 85, Short.MAX_VALUE)
										.addGap(34)))
								.addComponent(table, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE))))
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFifcheiro, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(table_2, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblOperadorLgico)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
					.addComponent(table_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnIniciar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}

