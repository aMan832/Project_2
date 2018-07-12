
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class UserView extends JFrame {
	
	private static final long serialVersionUID = 8566532219328889498L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnFollowUser;
	private JButton btnPostTweet;
	private String string;
	private String string1;
	private JTextPane textPane_1;
	private JTextPane textPane;

	public UserView(User user) {
		user.setUserView(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(18, 32, 285, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnFollowUser = new JButton("Follow User");
		btnFollowUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode root = (DefaultMutableTreeNode) AdminControlPanel.getJTree().getModel().getRoot();
				if (searchForUser(root, textField.getText()) == null) {
					JOptionPane.showMessageDialog(null, "This User does not exsist");
				} else {
					user.followUser(searchForUser(root, textField.getText()));
				}
			}
		});
		btnFollowUser.setBounds(315, 32, 117, 29);
		contentPane.add(btnFollowUser);
		
		textPane = new JTextPane();
		textPane.setBounds(18, 60, 410, 76);
		string = new String("Currently Following\n");
		for (int i = 0; i < user.getFollowing().size(); i++) {
			string = String.format("%s%s%s", string, user.getFollowing().get(i), "\n");
		}
		textPane.setText(string);
		contentPane.add(textPane);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(18, 148, 285, 26);
		contentPane.add(textField_1);
		
		btnPostTweet = new JButton("Post Tweet");
		btnPostTweet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.addText(textField_1.getText());
			}
		});
		btnPostTweet.setBounds(315, 148, 117, 29);
		contentPane.add(btnPostTweet);
		
		textPane_1 = new JTextPane();
		textPane_1.setBounds(18, 186, 410, 76);
		string1 = new String("NewsFeed\n");
		for (int i = 0; i < user.getNewsFeed().size(); i++) {
			string1 = String.format("%s%s%s", string1, user.getNewsFeed().get(i), "\n");
		}
		textPane_1.setText(string1);
		contentPane.add(textPane_1);
		
		JLabel label = new JLabel(user.getId());
		label.setBounds(18, 6, 61, 16);
		contentPane.add(label);
	}
	
	private User searchForUser(DefaultMutableTreeNode root, String userID) {
		for (int i = 0; i < root.getChildCount(); i++) {
	    	if (root.getChildAt(i).getAllowsChildren() == false) {
	    		DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) root.getChildAt(i);
	    		User user = (User) dmtn.getUserObject();
	    		if (user.getId().equals(userID)) {
	    			return user;
	    		}
	    	} else {
	    		return searchForUser((DefaultMutableTreeNode) root.getChildAt(i), userID);
	    	}
	    }
		return null;
	}

	public void refresh(User user) {
		string1 = new String("NewsFeed\n");
		string = new String("Currently Following\n");
		for (int i = 0; i < user.getNewsFeed().size(); i++) {
			string1 = String.format("%s%s%s", string1, user.getNewsFeed().get(i), "\n");
		}
		textPane_1.setText(string1);
		textPane_1.updateUI();
		for (int i = 0; i < user.getFollowing().size(); i++) {
			string = String.format("%s%s%s", string, user.getFollowing().get(i), "\n");
		}
		textPane.setText(string);
		textPane.updateUI();
	}
}
