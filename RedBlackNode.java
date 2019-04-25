 /*
Universidad del valle de Guatemala
Saul Contreras
Michele Benvenuto
Hoja 9

Tomado de https://www.sanfoundry.com/java-program-implement-binary-tree/
*/
 class RedBlackNode

 {    

     RedBlackNode left, right;

     Association element;

     int color;

 

     /* Constructor */

     public RedBlackNode(Association theElement)

     {

        this.color = 1;
        this.left = null;
        this.right = null;
        this.element = element;

     } 

     /* Constructor */

     public RedBlackNode(Association theElement, RedBlackNode lt, RedBlackNode rt)

     {

         this.left = lt;

         this.right = rt;

         this.element = theElement;

         this.color = 1;

     }    

 }