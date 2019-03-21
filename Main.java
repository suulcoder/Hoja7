import java.io.File;
import java.util.Map;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class Main extends JFrame implements ActionListener{

	private JButton ordenar = new JButton("Ordenar");
	private JButton traducir = new JButton("Traducir");
	private JTextArea texto = new JTextArea();
	private JTextArea  ordenado = new JTextArea();//nos dice el map preferido
	private JScrollPane scroll1;
	private JScrollPane scroll2;
	private Panel panelEntrada, panelSalida;
	private JPanel panelDeLaVentana;
	private BinaryTree<Association> tree = new BinaryTree<Association>()

	public static void main(String[] args) {
		/*Imprimimos la ventana en la pantalla*/
    	
    	Main miAplicacion = new Main();
     	miAplicacion.setBounds(10,10,200,200);
    	miAplicacion.pack();
		miAplicacion.setVisible(true);
	}

	public Main(){
		super("Hoja 6");/*Sera el nombre de la ventana*/

		//Necesitamos realizar este proceso ya que es necesario para la interfaz grafica
		ordenar.setActionCommand("ordenar");
		traducir.setActionCommand("traducir");
	
		ordenado.setPreferredSize(new Dimension(400,250));//dimensiones
		texto.setPreferredSize(new Dimension(400,250));//dimensiones
		scroll1 = new JScrollPane(texto);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll2 = new JScrollPane(ordenado);
		scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		panelDeLaVentana = (JPanel)this.getContentPane();
		panelEntrada = new Panel();//los siguientes paneles son para poner orden y estetica
		panelSalida = new Panel();

		panelEntrada.add(ordenar);
		panelEntrada.add(traducir);

		panelSalida.add(scroll1);
		panelSalida.add(scroll2);

		panelDeLaVentana.add(panelEntrada,BorderLayout.NORTH);//agreamos las ventanas a la interfaz grafica
		panelDeLaVentana.add(panelSalida,BorderLayout.SOUTH);
		
		try {
            Stream<String> lines = Files.lines(
                    Paths.get("diccionario.txt"),//Leemos el archivo
                    StandardCharsets.UTF_8
            );
            lines.forEach(s ->{
                String[] lista = s.split(",");
                System.out.println(lista[0]);
                System.out.println(lista[1]);
                Association association = new Association(lista[0],lista[1]);
                BinaryTree<Association> newtre = new BinaryTree<Association>(association);
                if(tree.getValue()==null){
                    tree=newtre;
                }
            });
        }catch (IOException exception){
            System.out.println("Error");
		}

	}

	public void actionPerformed(ActionEvent e){
		if("ordenar".equals((e.getActionCommand()))){
			//Mostrar ordenado
		}
		else if("traducir".equals((e.getActionCommand()))){
			//Traducir texto.txt
		}
	}
}