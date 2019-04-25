/*
Universidad del valle de Guatemala
Saul Contreras
Michele Benvenuto
Hoja 9
*/
import java.io.File;
import java.util.Map;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class Main extends JFrame implements ActionListener{

	private JButton cambiar = new JButton("   Change Three   ");
	private JButton ordenar = new JButton("   Order   ");
	private JButton traducir = new JButton("   Translate   ");
	private JTextArea texto = new JTextArea();
	private JTextArea arbol = new JTextArea();
	private JTextArea  ordenado = new JTextArea();//nos dice el map preferido
	private JScrollPane scroll1;
	private JScrollPane scroll2;
	private Panel panelEntrada, panelSalida;
	private JPanel panelDeLaVentana;
	private SplayTree tree = new SplayTree();

	public static void main(String[] args) {
		/*Imprimimos la ventana en la pantalla*/
    	
    	Main miAplicacion = new Main();
     	miAplicacion.setBounds(10,10,200,200);
    	miAplicacion.pack();
		miAplicacion.setVisible(true);
	}

	public Main(){
		super("Hoja 9");/*Sera el nombre de la ventana*/
		//Necesitamos realizar este proceso ya que es necesario para la interfaz grafica
		ordenar.setActionCommand("ordenar");
		traducir.setActionCommand("traducir");
		cambiar.setActionCommand("cambiar");
		cambiar.addActionListener(this);
		ordenar.addActionListener(this);
		traducir.addActionListener(this);

        ordenado.setPreferredSize(new Dimension(400,250));																		//dimensiones
		texto.setPreferredSize(new Dimension(400,250));																			//dimensiones
		arbol.setPreferredSize(new Dimension(150,20));	
		scroll1 = new JScrollPane(texto);
		scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll2 = new JScrollPane(ordenado);
		scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		panelDeLaVentana = (JPanel)this.getContentPane();
		panelEntrada = new Panel();																								//los siguientes paneles son para poner orden y estetica
		panelSalida = new Panel();

		panelEntrada.add(traducir);
		panelEntrada.add(ordenar);
		panelEntrada.add(cambiar);
		panelEntrada.add(arbol);
		panelSalida.add(scroll1);
		panelSalida.add(scroll2);

		panelDeLaVentana.add(panelEntrada,BorderLayout.NORTH);																	//agreamos las ventanas a la interfaz grafica
		panelDeLaVentana.add(panelSalida,BorderLayout.SOUTH);
		
		try {
            Stream<String> lines = Files.lines(
                    Paths.get("diccionario.txt"),																				//Leemos el archivo
                    StandardCharsets.UTF_8
            );
            lines.forEach(s ->{
                String[] lista = s.split(",");
                Association association = new Association(lista[0],lista[1]);
                tree.insert(association);
            });
        }catch (IOException exception){
            System.out.println("Error");
		}

	}

	public void actionPerformed(ActionEvent e){
		if("ordenar".equals((e.getActionCommand()))){
			String data = tree.preorder();
			String[] lista = data.split("\n");
			String retorno = "Data:\n";
			for(int i=0;i<lista.length;i++){
				retorno = retorno + "\t" + lista[i] + "\n";
			}
			ordenado.setText(retorno);
		}
		else if("traducir".equals((e.getActionCommand()))){
            try {
                Stream<String> lines = Files.lines(
                        Paths.get("texto.txt"),																											//Leemos el archivo
                        StandardCharsets.UTF_8
                );
                lines.forEach(s ->{
                    String allString = tree.getAllKeys();
                    String [] allStrings = allString.split(",");
                    String[] textToTranslate = s.split(" ");
                    String retorno = "";
                    for(int j=0;j<textToTranslate.length;j++){
                        retorno = retorno + '"'+textToTranslate[j]+'"' + " ";
                    }
                    for(int i=0;i<allStrings.length;i++){
                        retorno = retorno.toLowerCase();
                        retorno = retorno.replaceAll('"'+allStrings[i]+'"',tree.containsNode(allStrings[i]));

                    }
                    texto.setText(retorno);
                });
            }catch (IOException exception){
                System.out.println("Error");
            }
		}
	}
}