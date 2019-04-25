/*
Universidad del valle de Guatemala
Saul Contreras
Michele Benvenuto
Hoja 9
*/
import java.io.*;
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
	private String[] trees = {"SplayTree","RedBlackTree"};
	private int tree_index = 0;
	private String tree_string = trees[tree_index];
	private TreeFactory factory = new TreeFactory();
	private MyTree tree = factory.getTree(tree_string);

	public static void main(String[] args) {
		/*Imprimimos la ventana en la pantalla*/
    	
    	Main miAplicacion = new Main();
     	miAplicacion.setBounds(10,10,100,100);
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

		arbol.setPreferredSize(new Dimension(150,20));	
		scroll1 = new JScrollPane(texto);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll2 = new JScrollPane(ordenado);
		scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		ordenado.setPreferredSize(new Dimension(900,900));																		//dimensiones
		texto.setPreferredSize(new Dimension(900,900));																			//dimensiones
		arbol.setText(tree_string);
		ordenado.setBounds(10,50,400,300);
		texto.setBounds(10,50,400,300);

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
	}

	private void gnomeSort( String[] theArray ) {
		for ( int index = 1; index < theArray.length; ) {
			if ( theArray[index - 1].compareTo(theArray[index])<=0) {
				++index;
			} else {
				String tempVal = theArray[index];
				theArray[index] = theArray[index - 1];
				theArray[index - 1] = tempVal;
				--index;
				if ( index == 0 ) {
					index = 1;
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e){
		if("ordenar".equals((e.getActionCommand()))){
			tree.clear();
			BufferedReader br = null;
			FileReader fr = null;
			String output ="";
			try {
				fr = new FileReader("diccionario.txt");
				br = new BufferedReader(fr);
				while ((output = br.readLine()) != null) {
					String[] lista = output.split("\t");
					Association association = new Association(lista[0],lista[1]);
					tree.insert(association);       	
				}
			} catch (IOException f) {
				f.printStackTrace();
			} finally {
				try {
					if (br != null)
						br.close();
					if (fr != null)
						fr.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			String data = tree.preorder();
			String[] lista = data.split("\n");
			gnomeSort(lista);
			String retorno = "Data:\n";
			for(int i=0;i<lista.length;i++){
				retorno = retorno + "\t" + lista[i] + "\n";
			}
			ordenado.setText(retorno);
			System.out.println(retorno);
		}
		else if("traducir".equals((e.getActionCommand()))){
			tree.clear();
            BufferedReader br = null;
			FileReader fr = null;
			String output ="";
			try {
				fr = new FileReader("diccionario.txt");
				br = new BufferedReader(fr);
				while ((output = br.readLine()) != null) {
					String[] lista = output.split("\t");
					Association association = new Association(lista[0],lista[1]);
					tree.insert(association);       	
				}
			} catch (IOException f) {
				f.printStackTrace();
			} finally {
				try {
					if (br != null)
						br.close();
					if (fr != null)
						fr.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
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
						String variable = tree.containsNode(allStrings[i]);
						String[] traduccion = variable.split(",");
                        retorno = retorno.replaceAll('"'+allStrings[i]+'"',traduccion[0]);

                    }
                    texto.setText(retorno);
					System.out.println(retorno);
                });
            }catch (IOException exception){
                System.out.println("Error");
            }
		}
		else if("cambiar".equals((e.getActionCommand()))){
			arbol.setText(tree_string);
			tree_index++;
			if (tree_index==2){
	            tree_index=0;
			}
			tree_string = trees[tree_index];
			tree = factory.getTree(tree_string);
		}
	}
}