package Data.Sensors;

/**
 * Uludağ Üniversitesi Akıllı Ev Projesi
 * <p>
 * Copyright (c) 2017.
 * <p>
 * For more information see the LICENSE file.
 * <p>
 * Berkay Dedeoğlu tarafından oluşturulmuştur. Zaman: 27.11.2017 - 21:51.
 */
public class temperatureSensor  extends Sensor{


    public temperatureSensor(String id) {
        super(id);
    }

    @Override
    protected String getUnit() {
        return "°C";
    }
}
