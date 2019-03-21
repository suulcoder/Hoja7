/*
Universidad del valle de Guatemala
Saul Contreras
Michele Benvenuto
Hoja 7

Tomado de https://www.baeldung.com/java-binary-tree
*/
class Node {
    Association value;
    Node left;
    Node right;
 
    Node(Association value) {
        this.value = value;
        right = null;
        left = null;
    }
}