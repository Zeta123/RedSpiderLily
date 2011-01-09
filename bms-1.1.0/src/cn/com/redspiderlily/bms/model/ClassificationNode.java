package cn.com.redspiderlily.bms.model;

public class ClassificationNode {
	private String mainClassification;
	private String subClassification;
	
	public ClassificationNode(){
	}
	
	public String getMainClassification() {
		return mainClassification;
	}
	public void setMainClassification(String mainClassification) {
		this.mainClassification = mainClassification;
	}
	public String getSubClassification() {
		return subClassification;
	}
	public void setSubClassification(String subClassification) {
		this.subClassification = subClassification;
	}
}
