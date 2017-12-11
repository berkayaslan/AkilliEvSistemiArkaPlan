package ConnectionHelper;

import Data.Sensors.Sensor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SensorStateAsker implements Askable {
    private final String COMM_KEY = "sensor_durumu";
    private LinkedList<Sensor> sensors;
    private Map<Sensor, Integer> sensorLimitMap;
    private ICommunicationUser user;

    public SensorStateAsker(ICommunicationUser user){
        this.sensors = new LinkedList<>();
        this.sensorLimitMap = new HashMap<>();
        this.user = user;
    }

    @Override
    public String[] getAskMessages() {
        String[] messages = new String[2];
        messages[0] = this.COMM_KEY;
        messages[1] = this.createMessage();
        return messages;
    }

    @Override
    public void onAnswer(String answer) {
        // Todo: Değişim olursa göndermelisin
        user.doOnAnswer(COMM_KEY, answer);
    }

    private String createMessage(){
        JSONObject message = new JSONObject();
        JSONArray jSensors = new JSONArray();

        for (Sensor sensor: sensors) {
            jSensors.put(sensor.serialize());
        }

        try {
            message.put("sensorler", jSensors);
            return message.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }

    public void addSensor(Sensor sensor, int limit){
        this.sensorLimitMap.put(sensor, limit);
        this.sensors.add(sensor);
    }

    public void removeSensor(Sensor sensor){
        this.sensorLimitMap.remove(sensor);
        this.sensors.remove(sensor);
    }
}
