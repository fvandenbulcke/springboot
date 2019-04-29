package sb.test;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class User {
	
    public int id;
    public String name;
    
    @JsonIgnore
    public List<Item> userItems;
    
	public User() {}

	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public User(int id, String name, List<Item> userItems) {
		this.id = id;
		this.name = name;
		this.userItems = userItems;
	}
	
	public void addItem(Item item) {
		if (userItems == null) {
			userItems = new ArrayList<Item>();
		}
		userItems.add(item);
	}

}
