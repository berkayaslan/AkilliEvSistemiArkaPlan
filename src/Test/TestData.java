package Test;

import Data.Component;
import Data.Components;
import Data.IElement;
import Data.Sensors.Sensor;
import Data.Sensors.temperatureSensor;
import Data.Switch;

/**
 * COPYRIGTH (c) 2017.
 * <p>
 * For more information see the LICENSE file.
 * <p>
 * Created by berkay on 25.11.2017 at 11:40.
 */
public class TestData {
    public static void main(String[] args) {
        Component comp_1 = new Component("component_1");
        Component comp_2 = new Component("component_2");
        Component comp_3  = new Component("component_3");

        Components components = new Components();
        components.add(comp_1);
        components.add(comp_2);
        components.add(comp_3);

        // 1. component switch ve sensör içermez.
        comp_1.setBatteryState(25);
        comp_1.setLocation("mutfak");
        comp_1.setState(true);

        // 2. component sadece switch içerir.
        comp_2.setBatteryState(100);
        comp_2.setLocation("yatak_odasi");
        comp_2.addSwitch(new Switch("switch_1", true));
        comp_2.addSwitch(new Switch("switch_2"));

        // 3. component her şey içerir
        comp_3.setBatteryState(82);
        comp_3.setLocation("koridor");
        comp_3.addSwitch(new Switch("switch_3", false));
        Sensor sens = new temperatureSensor("sensor_1");
        sens.setValue(21);
        comp_3.addSensor(sens);

        System.out.println(components.serializeAll());
    }
}
