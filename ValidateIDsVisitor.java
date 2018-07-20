import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.tree.DefaultMutableTreeNode;

public class ValidateIDsVisitor implements Visitor {
	
	@Override
	public String visit(AdminControlPanel aCP) {
		List<String> userList = createListOfUsers((DefaultMutableTreeNode) AdminControlPanel.getJTree().getModel().getRoot());
		List<String> invalidUserIDs = searchForDuplicates(userList);
		invalidUserIDs.addAll(searchForSpaces(userList));
		String string = new String();
		for (int i = 0; i < invalidUserIDs.size(); i++) {
			string = string + invalidUserIDs.get(i) + ", ";
		}
		return string;
	}
	
	private List<String> createListOfUsers(DefaultMutableTreeNode group) {
		List<String> userList = new ArrayList<String>();
		for (int i = 0; i < group.getChildCount(); i++) {
			if (group.getChildAt(i).getAllowsChildren() == false) {
				DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) group.getChildAt(i);
	    		User user = (User) dmtn.getUserObject();
	    		userList.add(user.getId());
			}
			else userList.addAll(createListOfUsers((DefaultMutableTreeNode) group.getChildAt(i)));
		}
		return userList;
	}
	
	private List<String> searchForDuplicates(List<String> userList) {
		List<String> duplicateIDs = new ArrayList<String>();
		for (int i = 0; i < userList.size(); i++) {
			for (int k = 0; k < userList.size(); k++) {
				if (k != i & userList.get(k).equals(userList.get(i))) {
					duplicateIDs.add(userList.get(k));
				}
			}
		}
		return duplicateIDs;
	}
	
	private List<String> searchForSpaces(List<String> userList) {
		List<String> invalidIds = new ArrayList<String>();
		Pattern pattern = Pattern.compile("\\s");
		for (int i = 0; i < userList.size(); i++) {
			Matcher matcher = pattern.matcher(userList.get(i));
			if (matcher.find()) {
				invalidIds.add(userList.get(i));
			}
		}
		return invalidIds;
	}
}
