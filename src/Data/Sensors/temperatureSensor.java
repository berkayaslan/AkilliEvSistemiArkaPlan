package Data.Sensors;

/**
 * Uludağ Üniversitesi Akıllı Ev Projesi
 *
 * Copyright (c) 2017.
 *
 * For more information see the LICENSE file.
 * Berkay Dedeoğlu tarafından oluşturulmuştur. Zaman: 27.11.2017 - 21:51.
 */

public class temperatureSensor  extends Sensor{


    public temperatureSensor(String id) {
        super(id);
    }

    @Override
    public String getUnit() {
        return "°C";
    }

    @Override
    protected SensorTypes getSensorType() {return SensorTypes.TEMPERATURE;}
}
