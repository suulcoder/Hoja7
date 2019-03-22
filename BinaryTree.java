import java.util.Collections;
import java.util.ArrayList;
/*
Universidad del valle de Guatemala
Saul Contreras
Michele Benvenuto
Hoja 7

Tomado de https://www.baeldung.com/java-binary-tree
*/
public class BinaryTree {
 
    Node root = null;
 
    private Node addRecursive(Node current, Association value) {
    	if (current == null) {
        	root =  new Node(value);
        	return root;
    	}
 
    	if (value.getEnglish().compareTo(current.value.getEnglish())<0) {
       		current.left = addRecursive(current.left, value);
    	} else if (value.getEnglish().compareTo(current.value.getEnglish())>0) {
        	current.right = addRecursive(current.right, value);
    	} else {
    	    return current;
	    }
 
    	return current;
	}

	public void add(Association value) {
    	root = addRecursive(root, value);
	}

	private String containsNodeRecursive(Node current, String value) {
 		if (current == null) {
    	    return "*"+value+"*";
 	 	} 
    	if (value.equals(current.value.getEnglish()) ) {
        	return current.value.getSpanish();
    	} 
    	return value.compareTo(current.value.getEnglish())<0 
      	? containsNodeRecursive(current.left, value)
      	: containsNodeRecursive(current.right, value);
	}

	private String containsKeyRecursive(Node current, String value) {
		if (current == null) {
			return value;
		}
		if (value.equals(current.value.getEnglish()) ) {
			return current.value.getEnglish();
		}
		return value.compareTo(current.value.getEnglish())<0
				? containsNodeRecursive(current.left, value)
				: containsNodeRecursive(current.right, value);
	}

	public String containsNode(String value) {
 	   return containsNodeRecursive(root, value);
	}

	public Boolean contains(String value){
		return containsRecursive(root, value);
	}

	private Boolean containsRecursive(Node current, String value) {
		if (current == null) {
			return false;
		}
		if (value.equals(current.value.getEnglish()) ) {
			return true;
		}
		return value.compareTo(current.value.getEnglish())<0
				? containsRecursive(current.left, value)
				: containsRecursive(current.right, value);
	}

	public String containsKey(String value) {
		return containsKeyRecursive(root, value);
	}

	public String traversePreOrder(Node node) {
    String retorno  = "";
    if (node != null) {
        retorno = retorno  + node.value.getEnglish() + " , " + node.value.getSpanish() + ".\n" + traversePreOrder(node.left) + traversePreOrder(node.right);
    }
    return  retorno;
	}

	public String getAllKeys(Node node) {
		String retorno  = "";
		if (node != null) {
			retorno = retorno  + node.value.getEnglish() + "," + getAllKeys(node.left) + getAllKeys(node.right);
		}
		return  retorno;
	}


}