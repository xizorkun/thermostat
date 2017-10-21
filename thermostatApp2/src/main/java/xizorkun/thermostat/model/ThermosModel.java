package xizorkun.thermostat.model;

import javafx.beans.property.SimpleStringProperty;

public class ThermosModel {
	private SimpleStringProperty temp;
	private SimpleStringProperty heat;
	private SimpleStringProperty cool;
	private SimpleStringProperty version;
	private SimpleStringProperty mode;
	
	public ThermosModel(){
		
	}
	
	public ThermosModel(String temp, String heat, String cool, String version, String mode){
		this.heat = new SimpleStringProperty(heat);
		this.temp = new SimpleStringProperty(temp);
		this.cool = new SimpleStringProperty(cool);
		this.version = new SimpleStringProperty(version);
		this.mode = new SimpleStringProperty(mode);
	}

	public SimpleStringProperty getTemp() {
		return temp;
	}

	public void setTemp(SimpleStringProperty temp) {
		this.temp = temp;
	}

	public SimpleStringProperty getHeat() {
		return heat;
	}

	public void setHeat(SimpleStringProperty heat) {
		this.heat = heat;
	}
	
	public SimpleStringProperty getCool() {
		return cool;
	}

	public void setCool(SimpleStringProperty cool) {
		this.cool = cool;
	}

	public String getTempValue() {
		return temp.getValue();
	}

	

	public String getHeatValue() {
		return heat.getValue();
	}
	
	public String getCoolValue(){
		return cool.getValue();
	}

	public SimpleStringProperty getVersion() {
		return version;
	}

	public void setVersion(SimpleStringProperty version) {
		this.version = version;
	}
	
	public SimpleStringProperty getMode() {
		return mode;
	}

	public void setMode(SimpleStringProperty mode) {
		this.mode = mode;
	}
	
	
}
