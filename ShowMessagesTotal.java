import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class ShowMessagesTotal implements Visitor {

	private int count = 0;
	
	@Override
	public void visit(AdminControlPanel aCP) {
	    DefaultMutableTreeNode root = (DefaultMutableTreeNode) AdminControlPanel.getJTree().getModel().getRoot();
	    DefaultTreeModel treeModel = (DefaultTreeModel) AdminControlPanel.getJTree().getModel();
		count = 0;
		for (int i = 0; i < treeModel.getChildCount(root); i++) {
	    	if (root.getChildAt(i).getAllowsChildren() == false) {
	    		DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) treeModel.getChild(root, i);
	    		User user = (User) dmtn.getUserObject();
	    		count = count + user.getNewsFeed().size();
	    	} else {
	    		count = count + messageNum((DefaultMutableTreeNode) treeModel.getChild(root, i));
	    	}
	    }
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
		
	protected String getCount() {
		return Integer.toString(count);
	}
}
