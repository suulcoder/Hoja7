/*
Universidad del valle de Guatemala
Saul Contreras
Michele Benvenuto
Hoja 7

Tomado de https://www.baeldung.com/java-binary-tree
*/
public class BinaryTree {
 
    Node root;
 
    private Node addRecursive(Node current, Association value) {
    	if (current == null) {
        	return new Node(value);
    	}
 
    	if (value < current.value) {
       		current.left = addRecursive(current.left, value);
    	} else if (value > current.value) {
        	current.right = addRecursive(current.right, value);
    	} else {
        // value already exists
    	    return current;
	    }
 
    	return current;
	}

	public void add(Association value) {
    	root = addRecursive(root, value);
	}
}