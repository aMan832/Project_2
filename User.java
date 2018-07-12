import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class User extends SocialMediaEntity {

	private List<String> newsFeed = new ArrayList<String>();
	private List<String> following = new ArrayList<String>();
	private User newsFeedUpdate = null;
	private UserView userView = null;
	
	public User(String id) {
		super(id);
	}

	@Override
	public void update(Observable o, Object arg) {
		newsFeedUpdate = (User) o;
		this.addFollowerPost(newsFeedUpdate.getLatestPost());
	}

	@Override
	public void add(SocialMediaEntity socialMediaEntity) {
		// This Class is a lead and this method is not used
	}

	@Override
	public SocialMediaEntity getChild(int i) {
		// This Class is a lead and this method is not used
		return null;
	}

	@Override
	public void addText(String text) {
		this.newsFeed.add(text);
		setChanged();
		notifyObservers();
		userView.refresh(this);
	}

	@Override
	public void followUser(User user) {
		user.addObserver(this);
		following.add(user.getId());
		userView.refresh(this);
	}
	
	private String getLatestPost() {
		return newsFeed.get(newsFeed.size() - 1);
	}
	
	private void addFollowerPost(String news) {
		this.newsFeed.add(news);
		userView.refresh(this);
	}

	public List<String> getNewsFeed() {
		return newsFeed;
	}
	
	protected List<String> getFollowing() {
		return following;
	}
	
	// associates a user view UI with this user
	protected void setUserView(UserView userView) {
		this.userView = userView;
	}
}
