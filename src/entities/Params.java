package entities;

public class Params {
	private String range;
	private String precipitation;
	private String tipeTp;
	private String countPR;
	private String countD;
	
	public Params(){}
	
	public String getTipeTp() {
		return tipeTp;
	}
	public Params setTipeTp(String tipeTp) {
		this.tipeTp = tipeTp;
		return this;
	}
	
	public String getCountD() {
		return countD;
	}
	public Params setCountD(String countD) {
		this.countD = countD;
		return this;
	}
	public String getCountPR() {
		return countPR;
	}
	public Params setCountPR(String countPR) {
		this.countPR = countPR;
		return this;
	}
	public String getPrecipitation() {
		return precipitation;
	}
	public Params setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
		return this;
	}
	public String getRange() {
		return range;
	}
	public Params setRange(String range) {
		this.range = range;
		return this;
	}
	
	public String toString(){
		return range + " " + precipitation + " " + countD + " " + countPR + " " + tipeTp;
	}
}
