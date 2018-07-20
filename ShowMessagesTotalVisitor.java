import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class ShowMessagesTotalVisitor implements Visitor {
	
	@Override
	public String visit(AdminControlPanel aCP) {
	    DefaultMutableTreeNode root = (DefaultMutableTreeNode) AdminControlPanel.getJTree().getModel().getRoot();
	    DefaultTreeModel treeModel = (DefaultTreeModel) AdminControlPanel.getJTree().getModel();
		int count = 0;
		for (int i = 0; i < treeModel.getChildCount(root); i++) {
	    	if (root.getChildAt(i).getAllowsChildren() == false) {
	    		DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) treeModel.getChild(root, i);
	    		User user = (User) dmtn.getUserObject();
	    		count = count + user.getNewsFeed().size();
	    	} else {
	    		count = count + messageNum((DefaultMutableTreeNode) treeModel.getChild(root, i));
	    	}
	    }
		return String.valueOf(count);
	}
	
	private int messageNum(DefaultMutableTreeNode level) {
		int tempCount = 0;
		for (int i = 0; i < level.getChildCount(); i++) {
			if (level.getChildAt(i).getAllowsChildren() == false) {
				DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) level.getChildAt(i);
	    		User user = (User) dmtn.getUserObject();
	    		tempCount = tempCount + user.getNewsFeed().size();
	    	} else {
	    		tempCount = tempCount + messageNum((DefaultMutableTreeNode) level.getChildAt(i));
	    	}
	    }
		return tempCount;
	}
}
