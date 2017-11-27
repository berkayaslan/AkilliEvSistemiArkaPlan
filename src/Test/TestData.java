package Test;

import Data.Component;
import Data.IElement;
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
    public static void main(String[] args){

        System.out.println("Hello world");
        Component aComponent = new Component("Comp015");
        aComponent.addSensor(new temperatureSensor("Sens0151"));
        aComponent.addSensor(new temperatureSensor("Sens0152"));
        aComponent.addSensor(new temperatureSensor("Sens0153"));
        aComponent.addSensor(new temperatureSensor("Sens0154"));
        aComponent.addSensor(new temperatureSensor("Sens0155"));
        aComponent.addSwitch(new Switch("Swi0156"));
        aComponent.addSwitch(new Switch("Swi0157"));
        aComponent.addSwitch(new Switch("Swi0158"));
        aComponent.addSwitch(new Switch("Swi0159"));
        aComponent.addSwitch(new Switch("Swi01510"));

        System.out.println("Veriler eklendi. 1 componenette sorgu yapılıyor" );

        //IElement e = aComponent.findElement("Sens0151");
        //System.out.println(e.getElementId());

        Switch aSwitch = aComponent.findSwitch("Swi0156");
        aSwitch.setState(true);
        System.out.println(aSwitch.serialize());
    }
}
