import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class LastUpdatedUserVisitor implements Visitor {
	
	@Override
	public String visit(AdminControlPanel aCP) {
		List<User> userList = createListOfUserObjects((DefaultMutableTreeNode) AdminControlPanel.getJTree().getModel().getRoot());
		long lastestUpdateTime = setLatestUpdateTime(userList);
		return String.valueOf(lastestUpdateTime);
	}
	
	private List<User> createListOfUserObjects(DefaultMutableTreeNode group) {
		List<User> userList = new ArrayList<User>();
		for (int i = 0; i < group.getChildCount(); i++) {
			if (group.getChildAt(i).getAllowsChildren() == false) {
				DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) group.getChildAt(i);
	    		User user = (User) dmtn.getUserObject();
	    		userList.add(user);
			}
			else userList.addAll(createListOfUserObjects((DefaultMutableTreeNode) group.getChildAt(i)));
		}
		return userList;
	}

	private long setLatestUpdateTime(List<User> userList) {
		long temp = 0;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getLastUpdateTime() > temp) {
				temp = userList.get(i).getLastUpdateTime();
			}
		}
		return temp;
	}
}
