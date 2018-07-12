
import java.util.*;

public class Group extends SocialMediaEntity {

	public Group(String id) {
		super(id);
	}

	List<SocialMediaEntity> socialMediaEntity = new ArrayList<SocialMediaEntity>();

	@Override
	public void update(Observable o, Object arg) {
		// Not used
	}

	@Override
	public void add(SocialMediaEntity socialMediaEntity) {
		this.socialMediaEntity.add(socialMediaEntity);
	}

	@Override
	public SocialMediaEntity getChild(int i) {
		return this.socialMediaEntity.get(i);
	}

	@Override
	public void addText(String text) {
		// Not Used	
	}

	@Override
	public void followUser(User socialMediaEntity) {
		// Not Used
	}

	protected int getSize() {
		return socialMediaEntity.size();
	}

	@Override
	public List<String> getNewsFeed() {
		// Not Used
		return null;
	}
	
	protected SocialMediaEntity getEntity(String iD) {
		int i = 0;
		while (socialMediaEntity.get(i) != null) {
	    	if (socialMediaEntity.get(i) instanceof Group) {
	    		if (socialMediaEntity.get(i).getId() == iD) {
	    			return socialMediaEntity.get(i);
	    		}
	    		return getEntityPrivate(iD,socialMediaEntity.get(i));
	    	} else if (socialMediaEntity.get(i) instanceof User) {
	    		if (socialMediaEntity.get(i).getId() == iD) {
	    			return socialMediaEntity.get(i);
	    		}
	    	}
		}
		return null;
	}
	
	private SocialMediaEntity getEntityPrivate(String iD, SocialMediaEntity sme) {
		int i = 0;
		while (sme.getChild(i) != null) {
	    	if (sme.getChild(i) instanceof Group) {
	    		if (sme.getChild(i).getId() == iD) {
	    			return sme.getChild(i);
	    		}
	    		sme = getEntityPrivate(iD,sme.getChild(i));
	    	} else if (sme.getChild(i) instanceof User) {
	    		if (sme.getChild(i).getId() == iD) {
	    			return sme.getChild(i);
	    		}
	    	}
		}
		return null;
	}
}
