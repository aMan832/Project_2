import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

public class CreateTreeModel {
	
	private static DefaultMutableTreeNode lastSelectedComponent = new DefaultMutableTreeNode();
	
	public static void updateTree(JTree tree, SocialMediaEntity sme) {
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
		DefaultMutableTreeNode child = new DefaultMutableTreeNode(sme);
		model.insertNodeInto(child, root, root.getChildCount());
		tree.scrollPathToVisible(new TreePath(child.getPath()));
	}
	
	public static void addNode(JTree tree, SocialMediaEntity sme) {
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode child = new DefaultMutableTreeNode(sme);
		if (sme instanceof User) {
			child.setAllowsChildren(false);
		} else {
			child.setAllowsChildren(true);
		}
		if (lastSelectedComponent.getAllowsChildren() == false) {
			JOptionPane.showMessageDialog(null, "Cant add node to User");
		} else { 
			model.insertNodeInto(child, (MutableTreeNode) lastSelectedComponent, lastSelectedComponent.getChildCount());
			tree.scrollPathToVisible(new TreePath(child.getPath()));
		}
	}

	public static void setLastSelectedComponent(DefaultMutableTreeNode lastSelectedComponent) {
		CreateTreeModel.lastSelectedComponent = lastSelectedComponent;
	}
	
	public static DefaultMutableTreeNode getLastSelectedComponent() {
		return lastSelectedComponent;
	}
}
