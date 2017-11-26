package Data;

/*
 * Uludağ Üniversitesi Akıllı Ev Sistemi Projesi
 *
 * Copyright (c) 2017.
 *
 * Daha fazla bilgi için LICENSE dosyasına bakın.
 * Berkay Dedeoğlu tarafından geliştirildi. Zaman:  25.11.2017 - 12:31 .
 */


import org.json.JSONObject;

import java.util.LinkedList;

/**
 * Genel projede evde bilgi toplayan, sensörleri ve anahtarları
 * içinde bulundurup durumlarını izleyen kutuların sanal karşılığı.
 */
public class Component implements IElement{

    // Componentler dahil sistemdeki tüm cihazların birer ID numarası vardır.
    private String componentId;

    // Componentin evde bulunduğu yer
    private String location;

    // Componentin pil durumu bilgisi
    private int batteryState;

    // Componentte bulunan anahtarlar
    private LinkedList<Switch> switches;

    // Componentte bulunan sensörler
    private LinkedList<Sensor> sensors;

    // Component durumu (Çalışıyor/Çalışmıyor)
    private boolean state;

    //MaintainMessage: Yeni tür bir element geldiğinde elementin ebeveyn türünü içeren liste buraya eklenmelidir!

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
    }

    /**
     * Component nesnesini sadece id verisi ile oluşturma. Component
     * nesnesine id numarası sadece başlangıçta atanabilir.
     *
     * @param id
     *      Component id numarası.
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
        //TODO: inner class ya da metod ile parse işlemini gerçekleştir.
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


    // Bir Component nesnesinin tüm verileri dışarıdan alınabilir.
    public int getBatteryState(){return this.batteryState;}
    public String getLocation(){return this.location;}
    public LinkedList<Sensor> getSensors(){return this.sensors;}
    public LinkedList<Switch> getSwitches(){return this.switches;}

    // Bir Component nesnesinin ID değeri hariç tüm değerleri dışarıdan değiştirilebilir.
    public void setLocation(String location){this.location = location;}
    public void setBatteryState(int state){this.batteryState = state;}
    public void setSwitches(LinkedList<Switch> switches) {this.switches = switches;}
    public void setSensors(LinkedList<Sensor> sensors) {this.sensors = sensors;}
    public void setState(boolean state) {this.state = state;}


}
