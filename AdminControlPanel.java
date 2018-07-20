

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Frame;

public class AdminControlPanel extends JFrame implements Visitable{

	private static AdminControlPanel instance;
	private static final long serialVersionUID = 4328812655724494443L;
	private JPanel contentPane;
	private JTextField txtUserId;
	private JTextField txtGroupId;
	private JButton btnAddGroup;
	private JButton btnOpenUserView;
	private JButton btnNewButton_1;
	private JButton btnShowUserTotal;
	private JButton btnShowGroupTotal;
	private JButton btnShowPositivePercentage;
	private static JTree tree;
	public Group root;

	private AdminControlPanel(Group root) {
		this.root = root;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tree = new JTree(new DefaultTreeModel(new DefaultMutableTreeNode("Root")));
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setBounds(18, 6, 135, 317);
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				CreateTreeModel.setLastSelectedComponent(selectedNode);
			}
		});
		contentPane.add(tree);
		
		txtUserId = new JTextField();
		txtUserId.setText("User ID");
		txtUserId.setBounds(160, 21, 188, 26);
		contentPane.add(txtUserId);
		txtUserId.setColumns(10);
		
		txtGroupId = new JTextField();
		txtGroupId.setText("Group ID");
		txtGroupId.setBounds(160, 51, 188, 26);
		contentPane.add(txtGroupId);
		txtGroupId.setColumns(10);
		
		JButton btnNewButton = new JButton("Add User");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateTreeModel.addNode(tree, new User(txtUserId.getText()));
				txtUserId.setText("");
			}
		});
		btnNewButton.setBounds(360, 21, 117, 29);
		contentPane.add(btnNewButton);
		
		btnAddGroup = new JButton("Add Group");
		btnAddGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateTreeModel.addNode(tree, new Group(txtGroupId.getText()));
				txtGroupId.setText("");
			}
		});
		btnAddGroup.setBounds(360, 51, 117, 29);
		contentPane.add(btnAddGroup);
		
		btnOpenUserView = new JButton("Open User View");
		btnOpenUserView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CreateTreeModel.getLastSelectedComponent().getUserObject() instanceof User) {
					Frame frame = new UserView((User) CreateTreeModel.getLastSelectedComponent().getUserObject());
					frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Cant open with type Group");
				}
			}
		});
		btnOpenUserView.setBounds(259, 89, 142, 40);
		contentPane.add(btnOpenUserView);
		
		btnNewButton_1 = new JButton("Show Messages Total");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, accept(new ShowMessagesTotalVisitor()));
			}
		});
		btnNewButton_1.setBounds(165, 204, 160, 51);
		contentPane.add(btnNewButton_1);
		
		btnShowUserTotal = new JButton("Show User Total");
		btnShowUserTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, accept(new ShowUserTotalVisitor()));
			}
		});
		btnShowUserTotal.setBounds(165, 137, 160, 51);
		contentPane.add(btnShowUserTotal);
		
		btnShowGroupTotal = new JButton("Show Group Total");
		btnShowGroupTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, accept(new ShowGroupTotalVisitor()));
			}
		});
		btnShowGroupTotal.setBounds(337, 137, 160, 51);
		contentPane.add(btnShowGroupTotal);
		
		btnShowPositivePercentage = new JButton("Show Positive Percentage");
		btnShowPositivePercentage.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnShowPositivePercentage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, accept(new ShowPositivePercentageVisitor()));
			}
		});
		btnShowPositivePercentage.setBounds(337, 204, 160, 51);
		contentPane.add(btnShowPositivePercentage);
		
		JButton btnShowInvalidIds = new JButton("Show Invalid IDs");
		btnShowInvalidIds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, String.format("%s%s", "Invalid User IDs: ", accept(new ValidateIDsVisitor())));
			}
		});
		btnShowInvalidIds.setBounds(165, 272, 160, 51);
		contentPane.add(btnShowInvalidIds);
		
		JButton btnShowLastUpdate = new JButton("Show Last Update Time");
		btnShowLastUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, accept(new LastUpdatedUserVisitor()));
			}
		});
		btnShowLastUpdate.setBounds(337, 272, 160, 51);
		contentPane.add(btnShowLastUpdate);
	}
	
	protected static AdminControlPanel getInstance() {

		if (instance == null) {
			synchronized (AdminControlPanel.class) {
				if (instance == null) {
					instance = new AdminControlPanel(new Group("root"));
				} else {
					JOptionPane.showMessageDialog(null, "Admin Control Panel Already Open");
				}
			}
		}
		return instance;
	}

	protected static JTree getJTree() {
		return tree;
	}
	
	@Override
	public String accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
