import javax.swing.tree.DefaultMutableTreeNode;

public class ShowUserTotalVisitor implements Visitor {
	
	@Override
	public String visit(AdminControlPanel aCP) {
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) AdminControlPanel.getJTree().getModel().getRoot();
		int count = 0;
		for (int i = 0; i < root.getChildCount(); i++) {
	    	if (root.getChildAt(i).getAllowsChildren() == false) {
	    		count = count + 1;
	    	} else {
	    		count = count + userNum( (DefaultMutableTreeNode) root.getChildAt(i));
	    	}
	    }
		return String.valueOf(count);
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
}
