public class Main extends JFrame implements ActionListener{

	private JButton ordenar = new JButton("Ordenar");
	private JButton traducir = new JButton("Traducir");
	private JTextArea texto = new JTextArea();
	private JTextArea  ordenado = new JTextArea();//nos dice el map preferido
	private JScrollPane scroll1;
	private JScrollPane scroll2;
	private Panel panelEntrada, panelSalida;
	private JPanel panelDeLaVentana;

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
	
		scroll1 = new JScrollPane(texto);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll2 = new JScrollPane(ordenado);
		scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		panelDeLaVentana = (JPanel)this.getContentPane();
		panelEntrada = new Panel();//los siguientes paneles son para poner orden y estetica
		panelCentro = new Panel();
		panelSalida = new Panel();

		map_state.setText(map_string);

		panelEntrada.add(ordenar);
		panelEntrada.add(traducir);

		panelSalida.add(scroll1);
		panelSalida.add(scroll2);

		panelDeLaVentana.add(panelEntrada,BorderLayout.NORTH);//agreamos las ventanas a la interfaz grafica
		panelDeLaVentana.add(panelSalida,BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e){
		if("ordenar".equals((e.getActionCommand()))){
			//Mostrar ordenado
		}
		else if(state.getText().equals("traducir")){
			//Traducir texto.txt
		}
	}
}