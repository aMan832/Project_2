import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class ShowPositivePercentage implements Visitor {

	private double count = 0;
	private double positiveCount = 0;
	
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
		positiveCount = countPositive(root);
	}
	
	private double messageNum(DefaultMutableTreeNode level) {
		double tempCount = 0;
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
		double percentage = 100 * (positiveCount / count);
		return Double.toString(percentage);
	}

	private double countPositive(DefaultMutableTreeNode level) {
		double positiveCountTemp = 0;
		for (int i = 0; i < level.getChildCount(); i++) {
			if (level.getChildAt(i).getAllowsChildren() == false) {
				DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) level.getChildAt(i);
	    		User user = (User) dmtn.getUserObject();
	    		if (user.getNewsFeed().get(i).equals("good")) {
	    			positiveCountTemp = positiveCountTemp + 1;
	    		}
	    	} else {
	    		positiveCountTemp = positiveCountTemp + messageNum((DefaultMutableTreeNode) level.getChildAt(i));
	    	}
	    }
		return positiveCountTemp;
	}
}
