
import java.util.*;

public abstract class SocialMediaEntity extends Observable implements Observer{

	private String id;
	private long timeOfLastUpdate = 0;
	
	public SocialMediaEntity (String id) {
		this.setId(id);
	}

	public abstract void add(SocialMediaEntity socialMediaEntity);

	public abstract SocialMediaEntity getChild(int i);

	public abstract void addText(String text);
	
	public abstract void followUser(User socialMediaEntity);
	
	public abstract List<String> getNewsFeed();

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String toString() {
		return id;
	}
	
	protected String getLastUpdateTimeString() {
		return String.valueOf(timeOfLastUpdate);
	}
	
	protected void setLastUpdateTime() {
		timeOfLastUpdate = System.currentTimeMillis();
	}
	
	protected long getLastUpdateTime() {
		return timeOfLastUpdate;
	}
	
	protected void setLastUpdateTime(long lastUpdate) {
		timeOfLastUpdate = lastUpdate;
	}
}
