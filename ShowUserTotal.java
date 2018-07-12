import javax.swing.tree.DefaultMutableTreeNode;

public class ShowUserTotal implements Visitor {

	private int count = 0;
	
	@Override
	public void visit(AdminControlPanel aCP) {
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) AdminControlPanel.getJTree().getModel().getRoot();
		count = 0;
		for (int i = 0; i < root.getChildCount(); i++) {
	    	if (root.getChildAt(i).getAllowsChildren() == false) {
	    		count = count + 1;
	    	} else {
	    		count = count + userNum( (DefaultMutableTreeNode) root.getChildAt(i));
	    	}
	    }
	}
	
	private int userNum(DefaultMutableTreeNode sME) {
		int tempCount = 0;
		for (int i = 0; i < sME.getChildCount(); i++) {
			if (sME.getChildAt(i).getAllowsChildren() == false) {
	    		tempCount = tempCount + 1;
	    	} else {
	    		tempCount = tempCount + userNum( (DefaultMutableTreeNode) sME.getChildAt(i));
	    	}
		}
		return tempCount;
	}

	protected String getCount() {
		return Integer.toString(count);
	}
}
