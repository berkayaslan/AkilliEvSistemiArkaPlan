package Data.Sensors;

import Data.IElement;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * Uludağ Üniversitesi Akıllı Ev Sistemi Projesi
 *
 * Copyright (c) 2017.
 *
 * Daha fazla bilgi için LICENSE dosyasına bakın.
 * Berkay Dedeoğlu tarafından geliştirildi. Zaman:  25.11.2017 - 12:31 .
 */

/**
 * Sistemdeki componentlere bağlı sensörleri temsil eder.
 * Bir element olduğundan IElemet arayüzünü atası olarak kabul eder.
 *
 * @see Data.IElement
 */
public abstract class Sensor implements IElement {

    // Sensor nesnesinin sistemdeki ID numarası
    private String elementId;

    // Sensörün ölçüp geri döndürdüğü değer
    private double value;

    public Sensor(String id){
        this.elementId = id;
    }

    @Override
    public String getElementId() {return elementId;}

    @Override
    public boolean IdControl(String IDs) {return false;}

    @Override
    public JSONObject serialize() {
        JSONObject jsonSensor = new JSONObject();
        try{
            jsonSensor.put("deger", this.getValue());
            jsonSensor.put("tip", this.getSensorType().getValueInWeb());
            jsonSensor.put("id", this.getElementId());
        }catch (JSONException e){
            e.printStackTrace();
        }
        return jsonSensor;
    }

    /**
     * Her sensörün ölçtüğü değerin bir birimi olmalıdır. (İstisnaları var)
     * Bu sınıfı kalıtım olarak alan sınıfların bu birimi belirtmesi zorunlu
     * tutulmuştur. Çünkü kullanıcıya gösterilecek.
     *
     * @return Sensörün ölçtüğü birim. Yoksa boş string döndürür..
     */
    protected abstract String getUnit();

    /**
     * Sensörün tipini döndürür. Bu metod web üzerinden verileri
     * alırken kullanılır.
     *
     * @return sensörün tipi.
     */
    public abstract SensorTypes  getSensorType();

    public double getValue() {return value;}

    public void setValue(double value) {this.value = value;}

    public static Sensor buildSensorFromJson(JSONObject JSensor){
        String type = SensorTypes.TEMPERATURE.getValueInWeb();
        String id = "unknown";
        double value = 1 ;
        Sensor sensor;

        try {
            id = JSensor.getString("id");
            value = JSensor.getDouble("deger");
            type = JSensor.getString("tip");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (type.equals(SensorTypes.TEMPERATURE.getValueInWeb())){
            sensor = new TemperatureSensor(id);
            sensor.value = value;
            return sensor;
        }
        return null;
    }
}
