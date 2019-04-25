public interface MyTree{
	
	public boolean isEmpty();
	public int countNodes();
	public void clear();
	public void insert(Association item);
	public boolean search(Association val);
	public void inorder();
	public String preorder();
	public String getAllKeys();
	public void postorder();
	public String containsNode(String ele);
}