package Data;

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
 * Sistemde elektrik kablolarından elektriğin geçmesini
 * ya da geçmemesini sağlayan anahtarların sanal karşılığı.
 */
public class Switch implements IElement {

    // Switch nesnesinin sistemdeki ID numarası
    private String elementId;

    // Switch nesnesinin durumu
    private boolean state;



    /**
     * Switch nesnesinin yapıcı metodu. ID değeri tüm elementlerde yalnızca
     * constructor yoluyla verilir. Anahtarın durumu varsayılan olarak kapalıdır.
     *
     * @param id elementin ID numarası
     */
    public Switch(String id){
        this.elementId = id;
        this.state = false;
    }

    /**
     * Switch nesnesinin iki parametre alan yapıcı metodu. Diğerinden
     * farklı olarak anahtarın durumunu da atanabilmesini sağlar.
     *
     * @param id elementin ID numarası
     * @param state anahtarın durumu
     */
    public Switch(String id, boolean state){
        this.elementId = id;
        this.state = state;
    }

    @Override
    public String getElementId() {return elementId;}

    @Override
    public boolean IdControl(String IDs) {return false;}

    @Override
    public JSONObject serialize() {
        JSONObject object = new JSONObject();

        try {
            object.put("id", this.getElementId());
            object.put("durum", this.getState());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }

    public void setState(boolean state) {this.state = state;}

    public boolean getState() {return state;}
}
