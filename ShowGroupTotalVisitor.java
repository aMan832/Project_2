import javax.swing.tree.DefaultMutableTreeNode;

public class ShowGroupTotalVisitor implements Visitor {
	
	@Override
	public String visit(AdminControlPanel aCP) {
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) AdminControlPanel.getJTree().getModel().getRoot();
		int count = 0;
		for (int i = 0; i < root.getChildCount(); i++) {
	    	if (root.getChildAt(i).getAllowsChildren() == true) {
	    		count = count + 1;
	    		count = count + countGroup( (DefaultMutableTreeNode) root.getChildAt(i));
	    	}
		}
		return String.valueOf(count);
	}
	
	private int countGroup(DefaultMutableTreeNode group) {
		int tempCount = 0;
		for (int i = 0; i < group.getChildCount(); i++) {
	    	if (group.getChildAt(i).getAllowsChildren() == true) {
	    		tempCount = tempCount + 1;
	    		tempCount = tempCount + countGroup( (DefaultMutableTreeNode) group.getChildAt(i));
	    	}
		}
		return tempCount;
	}
}
