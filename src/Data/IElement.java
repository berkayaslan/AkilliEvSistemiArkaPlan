package Data;

/*
 * Uludağ Üniversitesi Akıllı Ev Projesi
 *
 * Copyright (c) 2017.
 *
 * Daha fazla bilgi için LICENSE dosyasına bakın.
 *
 * Berkay Dedeoğlu tarafından oluşturuldu. Zaman: 25.11.2017 - 12:33.
 */


import org.json.JSONObject;

/**
 * Bir sınıfın, sistemde bir element olduğunu kanıtlaması kullanılan
 * arabirim.
 *
 * (Component, Switch, Camera)
 */
public interface IElement {

    /**
     * Bu sistemdeki her elementin benzersiz bir ID numarası vardır.
     * Elementin benzersiz ID numarasının alınabildiği metod.
     * @return id
     *      Elementin benzersiz id numarası
     */
    String getElementId();


    /**
     * Sistemdeki her element gerektiğinde kontrol edilmelidir. Bu kontrolü sağlayan
     * fonksiyon.
     *
     * @param IDs
     *      Sistemdeki tüm idlerin listesi
     * @return true/false
     *      Sistem kayıtlarında kendi idsini bulursa true döndürür.
     */
    boolean IdControl(String IDs);

    /**
     * Sistemdeki parçalar sürekli web ile iletişim halinde olacaklar. Bu iletişim sırasında
     * elementin kendini bir JSON objesine dönüştüme metodudur.
     *
     * @return JSONObject
     *      Sunucuya gönderilmeye hazırlanmış JSON objesi.
     */
    JSONObject serialize();
}
