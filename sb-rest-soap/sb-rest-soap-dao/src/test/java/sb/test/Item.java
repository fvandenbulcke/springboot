package sb.test;

import lombok.Data;

@Data
public class Item {
	
    public int id;
    public String itemName;
    public User owner;
    
    
	public Item() {}
    
	public Item(int id, String itemName, User owner) {
		this.id = id;
		this.itemName = itemName;
		this.owner = owner;
	}
}
