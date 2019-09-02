package eletrica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Calculo extends JFrame {

	private JPanel contentPane;
	private JTextField tfDisjuntor;
	private JTextField tfFio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculo frame = new Calculo();
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
	public Calculo() {
		setTitle("Configura\u00E7\u00E3o para chuveiros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEscolhaATenso = new JLabel("Escolha a Tens\u00E3o: ");
		lblEscolhaATenso.setBounds(30, 23, 140, 14);
		contentPane.add(lblEscolhaATenso);
		
		JLabel lblEscolhaAPontncia = new JLabel("Escolha a Pont\u00EAncia:");
		lblEscolhaAPontncia.setBounds(30, 65, 140, 14);
		contentPane.add(lblEscolhaAPontncia);
		
		JComboBox comboTensao = new JComboBox();
		comboTensao.setModel(new DefaultComboBoxModel(new String[] {"127", "220"}));
		comboTensao.setBounds(180, 15, 60, 22);
		comboTensao.setSelectedItem(null);
		contentPane.add(comboTensao);
		
		JComboBox comboPotencia = new JComboBox();
		comboPotencia.setModel(new DefaultComboBoxModel(new String[] {"2500", "3200", "4000", "4500", "4700", "5500", "6000", "6800", "7500", "7800"}));
		comboPotencia.setBounds(180, 57, 60, 22);
		comboPotencia.setSelectedItem(null);
		contentPane.add(comboPotencia);
		
		JLabel lblV = new JLabel("Volts");
		lblV.setBounds(250, 19, 48, 14);
		contentPane.add(lblV);
		
		JLabel lblW = new JLabel("Watts");
		lblW.setBounds(250, 61, 48, 14);
		contentPane.add(lblW);
		
		JLabel lblNewLabel = new JLabel("Disjuntor Recomendado:");
		lblNewLabel.setBounds(30, 117, 193, 14);
		contentPane.add(lblNewLabel);
		
		tfDisjuntor = new JTextField();
		tfDisjuntor.setEditable(false);
		tfDisjuntor.setBounds(183, 114, 255, 20);
		contentPane.add(tfDisjuntor);
		tfDisjuntor.setColumns(10);
		
		JLabel lblFioRecomendado = new JLabel("Fio recomendado:");
		lblFioRecomendado.setBounds(30, 148, 140, 14);
		contentPane.add(lblFioRecomendado);
		
		tfFio = new JTextField();
		tfFio.setEditable(false);
		tfFio.setBounds(183, 145, 255, 20);
		contentPane.add(tfFio);
		tfFio.setColumns(10);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int tensao = Integer.parseInt(""+comboTensao.getSelectedItem());
				int potencia = Integer.parseInt(""+ comboPotencia.getSelectedItem());
				
				double arredondar = ((potencia/tensao)* 0.10)+(potencia/tensao);
				
				
				if (tensao == 127) 
				{
					if(potencia <= 3200) 
					{
						tfFio.setText("Fio de 4mm");
						tfDisjuntor.setText(verificaCorrente(arredondar));
					}
					else if(potencia < 5500) 
					{
						tfFio.setText("Fio de 6mm");
						tfDisjuntor.setText(verificaCorrente(arredondar));
					}
					else if (potencia == 5500)
					{
						tfFio.setText("Fio de 10mm");
						tfDisjuntor.setText(verificaCorrente(arredondar));
					}
					else 
					{
						tfFio.setText("Potencia inexistente no mercado");
						tfDisjuntor.setText("Potencia inexistente no mercado");
					}
				}
				else 
				{
					if(potencia <= 3200) 
					{
						tfFio.setText("Fio de 2,5mm");
						tfDisjuntor.setText(verificaCorrente(arredondar));
					}
					else if(potencia < 6800) 
					{
						tfFio.setText("Fio de 4mm");
						tfDisjuntor.setText(verificaCorrente(arredondar));
					}
					else 
					{
						tfFio.setText("Fio de 6mm");
						tfDisjuntor.setText(verificaCorrente(arredondar));
					}
				}
				
			}
		});
		btnCalcular.setMnemonic('c');
		btnCalcular.setBounds(509, 11, 89, 23);
		contentPane.add(btnCalcular);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tfFio.setText("");
				tfDisjuntor.setText("");
				comboTensao.setSelectedItem(null);
				comboPotencia.setSelectedItem(null);
				
			}
		});
		btnLimpar.setMnemonic('l');
		btnLimpar.setBounds(509, 53, 89, 23);
		contentPane.add(btnLimpar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSair.setMnemonic('s');
		btnSair.setBounds(509, 136, 89, 23);
		contentPane.add(btnSair);
	}
	
	public String verificaCorrente(double arredondar) {
		
		String resultado = "";
		
		if(arredondar < 15) 
		{
			resultado = "15A";
		}
		else if(arredondar < 20) 
		{
			resultado = "20A";
		}
		else if(arredondar < 25) 
		{
			resultado = "25A";
		}
		else if(arredondar < 30) 
		{
			resultado = "30A";
		}
		else if(arredondar < 35) 
		{
			resultado = "35A";
		}
		else if(arredondar < 40) 
		{
			resultado = "40A";
		}
		else 
		{
			resultado = "50A";
		}
		return resultado;
	}
}
