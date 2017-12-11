package Data;

/*
 * Uludağ Üniversitesi Akıllı Ev Sistemi Projesi
 *
 * Copyright (c) 2017.
 *
 * Daha fazla bilgi için LICENSE dosyasına bakın.
 * Berkay Dedeoğlu tarafından geliştirildi. Zaman:  25.11.2017 - 12:31 .
 */


import Data.Sensors.Sensor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.LinkedList;

/**
 * Genel projede evde bilgi toplayan, sensörleri ve anahtarları
 * içinde bulundurup durumlarını izleyen kutuların sanal karşılığı.
 */
public class Component implements IElement{

    // Componentler dahil sistemdeki tüm cihazların birer ID numarası vardır.
    private String componentId;

    // Component durumu (Çalışıyor/Çalışmıyor)
    private boolean state;

    // Componentin evde bulunduğu yer
    private String location;

    // Componentin pil durumu bilgisi
    private int batteryState;

    // Componentte bulunan anahtarlar
    private LinkedList<Switch> switches;

    // Componentte bulunan sensörler
    private LinkedList<Sensor> sensors;

    //MaintainMessage: Yeni tür bir element geldiğinde elementin ebeveyn türünü içeren liste buraya eklenmelidir!

    private LinkedList<LinkedList> elements;


    /**
     * Component sınıfının dışarıdan erişilemeyen varsayılan oluşturucusu.
     * Diğer oluşturucular içinde çağrılacaktır.
     *
     * Amaç her durumda bazı ilklendirilmerin yapılmış olmasıdır ve ID
     * numarasının verildiğini garanti etmektir.
     */
    protected Component() {
        switches = new LinkedList<>();
        sensors = new LinkedList<>();

        //elements.add(switches);// Rewiev: NullPointerExeption
        //elements.add(sensors);
        //MaintainMessage: Yeni element eklendiğinde buraya da eklenmeli.
    }

    /**
     * Component nesnesini sadece id verisi ile oluşturma. Component
     * nesnesine id numarası sadece başlangıçta atanabilir.
     *
     * @param id Component id numarası.
     */
    public Component(String id){this();this.componentId = id;}


    /**
     * Component nesnesini Json objesi ile oluşturma.
     *
     * @param JsonComponent
     *      Bir componentin uygun biçimde düzenlenmiş JSonObject nesnesi
     */
    public Component(JSONObject JsonComponent){
        this();
        build(JsonComponent);
    }


    // Bkz. IElement
    @Override
    public String getElementId() {return componentId;}

    // Bkz. IElement
    @Override
    public boolean IdControl(String IDs) {
        // TODO: Sistemdeki IDler ile karşılaştır.
        return true;
    }

    @Override
    public JSONObject serialize(){
        JSONObject JsonComponent = new JSONObject();
        JSONArray switchArray = new JSONArray();
        JSONArray sensorArray = new JSONArray();

        try {
            JsonComponent.put("id", this.getElementId());
            JsonComponent.put("yer", this.getLocation());
            JsonComponent.put("batarya", this.getBatteryState());

            for (Switch aSwitch: this.switches){
                switchArray.put(aSwitch.serialize());
            }
            for (Sensor aSensor: this.sensors){
                sensorArray.put(aSensor.serialize());
            }

            JsonComponent.put("anahtarlar", switchArray);
            JsonComponent.put("sensorler", sensorArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return JsonComponent;
    }

    private void build(JSONObject serialized) {

        try {
            this.componentId = serialized.get("id").toString();
            this.location = serialized.get("yer").toString();
            this.batteryState = new Integer(serialized.get("batarya").toString());
            JSONArray JAnahtarlar = serialized.getJSONArray("anahtarlar");
            for (int i = 0; i<JAnahtarlar.length();i++){
                Switch aSwitch = new Switch(JAnahtarlar.getJSONObject(i));
                this.switches.add(aSwitch);
            }

            JSONArray JSensor = serialized.getJSONArray("sensorler");
            for (int i = 0; i<JSensor.length();i++){
                Sensor aSensor = Sensor.buildSensorFromJson(JSensor.getJSONObject(i));
                this.sensors.add(aSensor);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Bir Component nesnesinin tüm verileri dışarıdan alınabilir.
    public int getBatteryState(){return this.batteryState;}
    public String getLocation(){return this.location;}
    public LinkedList<Sensor> getSensors(){return this.sensors;}
    public LinkedList<Switch> getSwitches(){return this.switches;}
    public boolean getState(){return this.state;}
    public LinkedList<LinkedList> getElements(){return this.elements;}


    // Bir Component nesnesinin ID değeri hariç tüm değerleri dışarıdan değiştirilebilir.
    public void setLocation(String location){this.location = location;}
    public void setBatteryState(int state){this.batteryState = state;}
    public void setSwitches(LinkedList<Switch> switches) {this.switches = switches;}
    public void setSensors(LinkedList<Sensor> sensors) {this.sensors = sensors;}
    public void setState(boolean state) {this.state = state;}


    /**
     * Component içinde idye göre element araması yapar. Yalnızca componentin içindeki
     * elementlere bakar.
     *
     * @param id Aranılan elementin id değeri
     * @return istenilen element bulunursa istenilen element. Bulunamazsa null.
     * @see IElement
     */
    public IElement findElement(String id){// Rewiev: NullPointerExeption
        for (LinkedList list : this.elements) {// Önce element listelerinin içine girilir. (switches, sensors ...)
            for (Object aElement : list) { // REWIEV: Casting işlemlerinde sıkıntı var. Sorun çıkarsa ilk bakılacak yer.
                IElement element = (IElement) aElement;
                if (element.getElementId().equals(id))
                    return element;
            }
        }
        return null;
    }


    /**
     * Component içinde idye göre swicth arar. Yalnızca component içindeki switchlere bakar.
     * findElement() metodundan farkı geriye gerçek bir switch nesnesi döndürmesidir.
     *
     * @param id Aranan switch'in id numarası
     * @return istenilen switch. Eğer yoksa null döndürülür.
     */
    public Switch findSwitch(String id){
        for (Switch aSwitch: this.switches){
            if (aSwitch.getElementId().equals(id))
                return aSwitch;
        }
        return null;
    }


    /**
     * Component içinde idye göre sensor arar. Yalnızca component içindeki sensorlere bakar.
     * findElement() metodundan farkı geriye gerçek bir Sensor nesnesi döndürmesidir.
     *
     * @param id Aranılan sensörün id numarası
     * @return İstenilen sensör. Eğer yoksa null.
     */
    public Sensor findSensor(String id){
        for (Sensor aSensor: sensors){
            if (aSensor.getElementId().equals(id))
                return aSensor;
        }
        return null;
    }

    // MaintainMessage: Yeni bir element eklendiğinde buradan find<YeniElement>() metodu oluşturulabilir.

    /**
     * Componente yeni bir sensör ekleme
     * @param sensor eklenecek sensör
     */
    public void addSensor(Sensor sensor){sensors.add(sensor);}

    /**
     * Component'e yeni bir switch ekleme
     * @param _switch eklenecek switch
     */
    public void addSwitch(Switch _switch){switches.add(_switch);}

    // MaintainMessage: Yeni bir element eklendiğinde buradan add<YeniElement>() metodu oluşturulabilir.
}
