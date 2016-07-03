package entities;

import java.util.Optional;


public class Things {

	private String thingName;
	private int quantity;
	private String priority;
	private String value;
	
	public Things() {
		this("", 0);
	}

	public Things(String thingname, int quantity) {
		this.thingName = thingname;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setThing(String thing, int quantity) {
		this.thingName = thing;
		this.quantity = quantity;
	}
	public Things safeSet(String thing, String quantity){
		quantity = Optional
				.ofNullable(quantity)
				.get()
				.replaceAll("\\D+", "");
		
		int value;
		if (quantity.isEmpty())
			value = 0;
		else value = Integer.parseInt(quantity);
		setThing(thing, value);
		return this;
		
	}


	public String getThingName() {
		return thingName;
	}

	public Things setThingName(String name){
		this.thingName = name;
		return this;
	}
	public String getPriority() {
		return priority;
	}

	public Things setPriority(String priority) {
		this.priority = priority;
		return this;
	}

	public String getValue() {
		return value;
	}

	public Things setValue(String value) {
		this.value = value;
		return this;
	}

	public String toString(){
		return this.thingName +"  "+ this.priority +"  "+ this.value;
	}
}
