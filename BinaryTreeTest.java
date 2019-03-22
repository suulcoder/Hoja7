import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {
    BinaryTree tree=new BinaryTree();
    Association prueba= new Association("hi","hola");
    Node node = new Node(prueba);

    @org.junit.jupiter.api.Test
    void containsNode() {
        assertEquals("hola",tree.containsNode("hi"));
    }

    @org.junit.jupiter.api.Test
    void containsKey() {
        assertEquals("hi", tree.containsKey("hi"));
    }
}