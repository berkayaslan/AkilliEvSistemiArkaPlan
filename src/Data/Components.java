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


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

/**
 * Component nesnelerini saklamak ve onları aramak için kullanılabilecek
 * sınıf.
 *
 * @see LinkedList
 */
public class Components extends LinkedList<Component>{

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

    /**
     * Web üzerinde iletişim kurabilmek için içinde bulunan tüm
     * componentleri JSONObject formatına dönüştüren metod.
     *
     * @return Tüm componentleri içeren bir JSon dizisi.
     */
    public JSONArray serializeAll(){
        JSONArray allComponents = new JSONArray();

        for (Component aComponent: this){
            allComponents.put(aComponent.serialize());
        }

        return allComponents;
    }



}
