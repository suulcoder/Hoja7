public class TreeFactory{
	public MyTree getTree(String needed){
		if(needed.equals("SplayTree")){
			return new SplayTree();
		}
		else if(needed.equals("RedBlackTree")){
			return new RBTree();
		}
		return	null;
	}
}