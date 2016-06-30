package application;



public class Things {
	
	private String thingname;
	private int quantity;

	 Things(){
		 this.thingname = "";
		 this.quantity = 0;
	 }
	
	 Things(String thingName, int quantity){
		this.thingname = thingName;
		this.quantity = quantity;
	}
	
	 String getThingname(){
		return thingname;
	}
	
	 int getQuantity(){
		return quantity;
	}
	
	void setThing(String thing, int quantity){
		this.thingname = thing;
		this.quantity = quantity;
	}
	
}
