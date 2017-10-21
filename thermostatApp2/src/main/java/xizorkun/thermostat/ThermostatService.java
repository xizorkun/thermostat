package xizorkun.thermostat;

public class ThermostatService {

	public String tmode (int mode){
		String tmode = "...";
		switch (mode){
		case 0:
			tmode = "Off";
			break;
		case 1:
			tmode = "Heat";
			break;
		case 2:
			tmode = "Cool";
			break;
		case 3:
			tmode ="Auto";
			break;
		default:
			break;
			
		}
		return tmode;
	}
}
