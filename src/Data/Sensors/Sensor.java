package Data.Sensors;

import Data.IElement;
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

    // Sensörün ölçtüğü verinin birimi
    private String unit;

    // Sensörün ölçüp geri döndürdüğü değer
    private int value;

    public Sensor(String id){
        this.elementId = id;
    }

    @Override
    public String getElementId() {
        return elementId;
    }

    @Override
    public boolean IdControl(String IDs) {return false;}

    @Override
    public JSONObject serialize() {
        return null;
    }

    /**
     * Her sensörün ölçtüğü değerin bir birimi olmalıdır. (İstisnaları var)
     * Bu sınıfı kalıtım olarak alan sınıfların bu birimi belirtmesi zorunlu
     * tutulmuştur. Çünkü kullanıcıya gösterilecek.
     *
     * @return Sensörün ölçtüğü birim. Yoksa boş string döndürür..
     */
    protected abstract String getUnit();

    public int getValue() {return value;}

    public void setValue(int value) {this.value = value;}
}
