package xizorkun.thermostat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ThermosInfo {
	double temp;
	double t_heat;
	double t_cool;
	int tmode;
	
	public int getTmode() {
		return tmode;
	}
	public void setTmode(int tmode) {
		this.tmode = tmode;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public double getT_heat() {
		return t_heat;
	}
	public void setT_heat(double heat) {
		this.t_heat = heat;
	}
	public double getT_cool() {
		return t_cool;
	}
	public void setT_cool(double t_cool) {
		this.t_cool = t_cool;
	}
	
	

}
