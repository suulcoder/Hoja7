/*
Universidad del valle de Guatemala
Saul Contreras
Michele Benvenuto
Hoja 9

Tomado de https://www.sanfoundry.com/java-program-implement-splay-tree/
*/
 class SplayNode

 {    

     SplayNode left, right, parent;

     Association element;

 

     /** Constructor **/

     public SplayNode()

     {
		Association raiz = new Association("Structure","Estructura");//Set a prefab element
		this.left=null;
		this.right=null;
		this.parent=null;
		this.element=raiz;
     }          

     /** Constructor **/

     public SplayNode(Association ele, SplayNode left, SplayNode right, SplayNode parent)

     {

         this.left = left;

         this.right = right;

         this.parent = parent;

         this.element = ele;         

     }    

 }