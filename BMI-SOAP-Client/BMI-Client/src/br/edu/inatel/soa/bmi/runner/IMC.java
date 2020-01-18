package br.edu.inatel.soa.bmi.runner;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

 /* O Índice de Massa Corporal representada pela sigla IMC 
  é a medida do grau de obesidade de uma pessoa, considerando
  a altura e a massa de uma pessoa.*/

public class IMC extends JPanel{
    
    // Autor: João Matheus Santos Assis
    
	private static final long serialVersionUID = -3679802419531824681L;

	float Mass_a, Altur_a, Resultad_o;
    
    private JPanel Obter_dados = new JPanel();
    private JPanel Exibir_Resultado = new JPanel();

    private JLabel Exibi_IMC = new JLabel();
    private JLabel EXibi_Status = new JLabel();
    
    // Espera-se que a massa esteja em quilogramas e a altura em metros.
    private JLabel Massa = new JLabel("Massa (Kg): ");
    private JLabel Altura = new JLabel(" Altura (m): ");

    private JTextField M_assa = new JTextField(9);
    private JTextField A_ltura = new JTextField(9);

    private JButton Calcular = new JButton("Calcular");
    private JButton Limpar = new JButton("Limpar");

    public IMC(){
        
        // Definindo a fonte dos JLabel's
        Massa.setFont(new Font("Tahoma", Font.BOLD, 14));
        Altura.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        
        // Adicionando os componentes
        Obter_dados.add(Massa);
        Obter_dados.add(M_assa);
        Obter_dados.add(Altura);
        Obter_dados.add(A_ltura);
        Obter_dados.add(Calcular);
        Obter_dados.add(Limpar);
        Obter_dados.add(Exibi_IMC);

        add(Obter_dados);
        // Adicionando título ao JLabel
        Obter_dados.setBorder(BorderFactory.createTitledBorder("IMC"));
        // Dimensão do JPanel 210 de comprimento por 150 de largura
        Obter_dados.setPreferredSize(new Dimension(210,150));
        
        Exibir_Resultado.add(EXibi_Status);
        
        add(Exibir_Resultado);
        Exibir_Resultado.setBorder(BorderFactory.createTitledBorder("Resultado"));
        Exibir_Resultado.setPreferredSize(new Dimension(210,90));
        
        
        Calcular.addActionListener(new ActionListener(){
            
            public void actionPerformed( ActionEvent event){
            
            // Obtendo a massa e a altura e convertendo de String para float
            Mass_a = Float.valueOf(M_assa.getText());
            Altur_a = Float.valueOf(A_ltura.getText());
            
            // Calculo de IMC: massa dividida pelo quadrado da altura
            Resultad_o = Mass_a / (Altur_a * Altur_a);
            
            EXibi_Status.setText(Verifica(Resultad_o));
            Exibi_IMC.setText(String.valueOf(Resultad_o)); 
            
            
            }});
            
            
            Limpar.addActionListener(new ActionListener(){
                
            public void actionPerformed(ActionEvent event){
                
                // Apagando os conteúdos os JTextField's'
                M_assa.setText("");
                A_ltura.setText("");
                Exibi_IMC.setText("");
            
            }                
            });
        
    }
    
    
    public String Verifica (float Resultad_o){
        String resultado = null;
        
        // Verificando a situação do indivíduo
        
        if (Resultad_o<18.5) 
              resultado="Baixo peso";
        else if (Resultad_o>=18.5 && Resultad_o<24.9) 
              resultado="Normal";
        else if (Resultad_o>=25.0 && Resultad_o<=29.9) 
              resultado="Pre-obeso";
        else if (Resultad_o>=30.0 && Resultad_o<=34.9) 
              resultado="Obesidade classe I (leve)";
        else if (Resultad_o>=35.0 && Resultad_o<39.9) 
              resultado="Obesidade classe II (moderada)";
        else if (Resultad_o>=40.0) 
              resultado="Obesidade classe III (grave,morbida)";
        
        // Retorna a variável resultado no modo String
        return resultado;
        
    }
    
    public static void main(String[] args){
        
        JFrame Janela = new JFrame();
        IMC imc = new IMC();
        
        Janela.add(imc);
        
        Janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Janela.setTitle("Calculando o IMC");
        Janela.setSize(240,300);
        Janela.setVisible(true);
        Janela.setResizable(false);
        Janela.setLocationRelativeTo(null);
    }
}
