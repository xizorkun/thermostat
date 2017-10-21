package xizorkun.thermostat.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import xizorkun.thermostat.model.ApiVersion;
import xizorkun.thermostat.model.ThermosInfo;
import xizorkun.thermostat.model.ThermosModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import xizorkun.thermostat.ThermostatService;
public class ThermostatViewController {
	@FXML
	private Label temperature;
	@FXML
	private Label heat;
	@FXML
	private Label cool;
	@FXML
	private Label version;
	@FXML
	private Label mode;
	
	@FXML
	private void initialize(){
        handleUpdate();
	}
	
	@FXML
	private void handleUpdate(){
		final ThermosModel thermos = new ThermosModel();
		Task<ThermosModel> task = new Task<ThermosModel>(){

			@Override
			protected ThermosModel call() throws Exception {
				updateMessage("Calling webservice . . .");
				final ThermosModel tmodel = callTstatWebservice();
				updateMessage("Finished.");
				
				Platform.runLater(new Runnable(){

					public void run() {
						thermos.setCool(tmodel.getCool());
						thermos.setHeat(tmodel.getHeat());
						thermos.setTemp(tmodel.getTemp());
						thermos.setVersion(tmodel.getVersion());
						thermos.setMode(tmodel.getMode());
						
					}
					
				});
				return tmodel;
			}
		
		};
		new Thread(task).start();
		task.stateProperty().addListener(new ChangeListener<Worker.State>() {

			public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue,
					Worker.State newValue) {
				switch (newValue){
				case CANCELLED:
					break;
				case FAILED:
					temperature.textProperty().bind(new SimpleStringProperty("Error"));
					heat.textProperty().bind(new SimpleStringProperty("Error"));
					cool.textProperty().bind(new SimpleStringProperty("Error"));
					version.textProperty().bind(new SimpleStringProperty("Error"));
					mode.textProperty().bind(new SimpleStringProperty("Error"));
					break;
				case READY:
				case RUNNING:
				case SCHEDULED:
					temperature.textProperty().bind(new SimpleStringProperty("..."));
					heat.textProperty().bind(new SimpleStringProperty("..."));
					cool.textProperty().bind(new SimpleStringProperty("..."));
					version.textProperty().bind(new SimpleStringProperty("..."));
					mode.textProperty().bind(new SimpleStringProperty("..."));
					break;
				case SUCCEEDED:
					temperature.textProperty().bind(thermos.getTemp());
					heat.textProperty().bind(thermos.getHeat());
					cool.textProperty().bind(thermos.getCool());
					version.textProperty().bind(thermos.getVersion());
					mode.textProperty().bind(thermos.getMode());
					break;
				default:
					break;
				
				}
			}

		});
	}
	
	private ThermosModel callTstatWebservice(){
		final ThermosInfo info;
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		restTemplate.setMessageConverters(messageConverters);
		info = restTemplate.getForObject("http://192.168.1.2/tstat", ThermosInfo.class);
		final ApiVersion version = restTemplate.getForObject("http://192.168.1.2/tstat/version", ApiVersion.class);
		System.out.println("current temp: " + info.getTemp());
		System.out.println("current heat: " + info.getT_heat());
		System.out.println("current cool: " + info.getT_cool());
		ThermostatService thermosService = new ThermostatService();
		final ThermosModel tmodel = new ThermosModel(String.valueOf(info.getTemp()), String.valueOf(info.getT_heat()), String.valueOf(info.getT_cool()), String.valueOf(version.getVersion()), thermosService.tmode(info.getTmode()));
			
		return tmodel;
	}

}
