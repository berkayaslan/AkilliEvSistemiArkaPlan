package Data;

/*
 * Uludağ Üniversitesi Akıllı Ev Projesi
 *
 * Copyright (c) 2017.
 *
 * For more information see the LICENSE file.
 *
 * Berkay Dedeoğlu tarafından oluşturulmuştur. Zaman: 26.11.2017 - 14:16.
 */


import Data.Sensors.Sensor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

/**
 * Component nesnelerini saklamak ve onları aramak için kullanılabilecek
 * sınıf.
 *
 * @see LinkedList
 */
public class Components extends LinkedList<Component>{

    public Components(){}

    public Components(JSONObject JComponents){
        this.buildAll(JComponents);
    }

    /**
     * Id numarasına göre tüm componentler arasından istenilen component nesnesini
     * bulan metod.
     *
     * @param id İstenilen componentin id numarası.
     * @return İstenilen component varsa döndürülür. Yoksa null döndürülür.
     */
    public Component findComponent(String id){
        for (Component aComponent: this){
            if (aComponent.getElementId().equals(id))
                return aComponent;
        }
        return null;
    }

    public Switch findSwitch(String id){
        for (Component component: this){
            for (Switch aSwitch: component.getSwitches()){
                if (aSwitch.getElementId().equals(id))
                    return aSwitch;
            }
        }
        return null;
    }

    public Sensor findSensor(String id){
        for (Component component: this){
            for (Sensor sensor: component.getSensors()){
                if (sensor.getElementId().equals(id))
                    return sensor;
            }
        }
        return null;
    }

    /**
     * Web üzerinde iletişim kurabilmek için içinde bulunan tüm
     * componentleri JSONObject formatına dönüştüren metod.
     *
     * @return Tüm componentleri içeren bir JSon dizisi.
     */
    public JSONObject serializeAll(){
        JSONObject allComponentsObj = new JSONObject();
        JSONArray allComponents = new JSONArray();

        for (Component aComponent: this){
            allComponents.put(aComponent.serialize());
        }

        try {
            allComponentsObj.put("tumVeriler", allComponents);
            return allComponentsObj;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void buildAll(JSONObject serialized){

        try {
            JSONArray JComponents = serialized.getJSONArray("tumVeriler");
            for (int i=0; i<JComponents.length();i++){
                this.add(new Component(JComponents.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
