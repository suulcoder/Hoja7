/*
Universidad del valle de Guatemala
Saul Contreras
Michele Benvenuto
Hoja 9

Tomado de https://www.sanfoundry.com/java-program-implement-splay-tree/
*/
 class SplayTree

 {

     private SplayNode root;

     private int count = 0;

 

     /** Constructor **/

     public SplayTree()

     {

         root = null;

     }

 

     /** Function to check if tree is empty **/

     public boolean isEmpty()

     {

         return root == null;

     }

 

     /** clear tree **/

     public void clear()

     {

         root = null;

         count = 0;

     }

 

     /** function to insert element */

     public void insert(Association ele)

     {

         SplayNode z = root;

         SplayNode p = null;

         while (z != null)

         {

             p = z;

             if (ele.compareTo(p.element)<0)

                 z = z.right;

             else

                 z = z.left;

         }

         z = new SplayNode();

         z.element = ele;

         z.parent = p;

         if (p == null)

             root = z;

         else if (ele.compareTo(p.element)<0)

             p.right = z;

         else

             p.left = z;

         Splay(z);

         count++;

     }

     /** rotate **/

     public void makeLeftChildParent(SplayNode c, SplayNode p)

     {

         if ((c == null) || (p == null) || (p.left != c) || (c.parent != p))

             throw new RuntimeException("WRONG");

 

         if (p.parent != null)

         {

             if (p == p.parent.left)

                 p.parent.left = c;

             else 

                 p.parent.right = c;

         }

         if (c.right != null)

             c.right.parent = p;

 

         c.parent = p.parent;

         p.parent = c;

         p.left = c.right;

         c.right = p;

     }

 

     /** rotate **/

     public void makeRightChildParent(SplayNode c, SplayNode p)

     {

         if ((c == null) || (p == null) || (p.right != c) || (c.parent != p))

             throw new RuntimeException("WRONG");

         if (p.parent != null)

         {

             if (p == p.parent.left)

                 p.parent.left = c;

             else

                 p.parent.right = c;

         }

         if (c.left != null)

             c.left.parent = p;

         c.parent = p.parent;

         p.parent = c;

         p.right = c.left;

         c.left = p;

     }

 

     /** function splay **/

     private void Splay(SplayNode x)

     {

         while (x.parent != null)

         {

             SplayNode Parent = x.parent;

             SplayNode GrandParent = Parent.parent;

             if (GrandParent == null)

             {

                 if (x == Parent.left)

                     makeLeftChildParent(x, Parent);

                 else

                     makeRightChildParent(x, Parent);                 

             } 

             else

             {

                 if (x == Parent.left)

                 {

                     if (Parent == GrandParent.left)

                     {

                         makeLeftChildParent(Parent, GrandParent);

                         makeLeftChildParent(x, Parent);

                     }

                     else 

                     {

                         makeLeftChildParent(x, x.parent);

                         makeRightChildParent(x, x.parent);

                     }

                 }

                 else 

                 {

                     if (Parent == GrandParent.left)

                     {

                         makeRightChildParent(x, x.parent);

                         makeLeftChildParent(x, x.parent);

                     } 

                     else 

                     {

                         makeRightChildParent(Parent, GrandParent);

                         makeRightChildParent(x, Parent);

                     }

                 }

             }

         }

         root = x;

     }

 

     /** function to remove element **/

     public void remove(Association ele)

     {

         SplayNode node = findNode(ele);

        remove(node);

     }

 

     /** function to remove node **/

     private void remove(SplayNode node)

     {

         if (node == null)

             return;

 

         Splay(node);

         if( (node.left != null) && (node.right !=null))

         { 

             SplayNode min = node.left;

             while(min.right!=null)

                 min = min.right;

 

             min.right = node.right;

             node.right.parent = min;

             node.left.parent = null;

             root = node.left;

         }

         else if (node.right != null)

         {

             node.right.parent = null;

             root = node.right;

         } 

         else if( node.left !=null)

         {

             node.left.parent = null;

             root = node.left;

         }

         else

         {

             root = null;

         }

         node.parent = null;

         node.left = null;

         node.right = null;

         node = null;

         count--;

     }

 

     /** Functions to count number of nodes **/

     public int countNodes()

     {

         return count;

     }

 

     /** Functions to search for an element **/

     public boolean search(Association val)

     {

         return findNode(val) != null;

     }

	 private String containsNodeRecursive(SplayNode current, String value)

     {
		if (current == null) {
    	    return '"'+value+'"';
 	 	} 
    	if (value.equals(current.element.getEnglish()) ) {
			return current.element.getSpanish();
    	} 
    	return value.compareTo(current.element.getEnglish())>0 
      	? containsNodeRecursive(current.left, value)
      	: containsNodeRecursive(current.right, value);

     }

	public String containsNode(String ele)
	{
		return containsNodeRecursive(root,ele);
    }

     private SplayNode findNode(Association ele)

     {

    	 SplayNode PrevNode = null;

         SplayNode z = root;

         while (z != null)

         {

        	 PrevNode = z;

             if (ele.compareTo(z.element)<0)

                 z = z.right;

             else if (ele.compareTo(z.element)>0)

                 z = z.left;

             else if(ele.compareTo(z.element)==0) {

                 Splay(z);

                 return z;

             }

 

         }

         if(PrevNode != null)

         {

             Splay(PrevNode);

             return null;

         }

         return null;

     }

 

     /** Function for inorder traversal **/ 

     public void inorder()

     {

         inorder(root);

     }

     private void inorder(SplayNode r)

     {

         if (r != null)

         {

             inorder(r.left);

             System.out.print(r.element +" ");

             inorder(r.right);

         }

     }

 

     /** Function for preorder traversal **/

     public String preorder()

     {

         return preorder(root);

     }

     private String preorder(SplayNode r)

     {
		String retorno  = "";
		if (r != null) {
			retorno = retorno  + r.element.getEnglish() + " , " + r.element.getSpanish() + ".\n" + preorder(r.left) + preorder(r.right);
		}
		return  retorno;
     }

	public String getAllKeys()

     {

         return getAllKeys(root);

     }

     private String getAllKeys(SplayNode r)

     {
		String retorno  = "";
		if (r != null) {
			retorno = retorno  + r.element.getEnglish() + "," + getAllKeys(r.left) + getAllKeys(r.right);
		}
		return  retorno;
     }


     /** Function for postorder traversal **/

     public void postorder()

     {

         postorder(root);

     }

     private void postorder(SplayNode r)

     {

         if (r != null)

         {

             postorder(r.left);             

             postorder(r.right);

             System.out.print(r.element +" ");

         }

     }     

 }