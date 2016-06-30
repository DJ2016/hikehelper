package entities;

public class Things {

	private String thingName;
	private int quantity;

	public Things() {
		this.thingName = "";
		this.quantity = 0;
	}

	public Things(String thingname, int quantity) {
		this.thingName = thingname;
		this.quantity = quantity;
	}

	public String getThingName() {
		return thingName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setThing(String thing, int quantity) {
		this.thingName = thing;
		this.quantity = quantity;
	}

}
