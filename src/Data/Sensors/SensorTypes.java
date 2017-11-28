package Data.Sensors;

/*
 * Uludağ Üniversitesi Akıllı Ev Projesi
 *
 * Copyright (c) 2017.
 *
 * For more information see the LICENSE file.
 *
 * Berkay Dedeoğlu tarafından oluşturulmuştur. Zaman: 27.11.2017 - 23:57.
 */


/**
 * Sensörlerin bilgileri ağ üzerinde gönderilirken sensörlerin
 * tiplerinin bilinmesi çoğu zaman android tarafında da gerekli
 * oluyor. Her tip sensörün bazı farklı özellikleri olduğundan
 * bunların doğru biçimde alınması önemlidir.
 *
 * Ağ üzerinde iletişimi sağlanacak sensörlerin tip değerlerini
 * tutan bir enum.
 */
public enum SensorTypes {
    TEMPERATURE("sicaklik");

    // Sensörün ağ iletişimindeki ismi
    private String valueInWeb;

    SensorTypes(String webValue){this.valueInWeb = webValue;}

    public String getValueInWeb() {return valueInWeb;}
}
