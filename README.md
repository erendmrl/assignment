### Testleri Çalıştırma
- **Browser ve driver path'leri /config altındaki config.properties dosyasından değiştirin. 'browser' parametresi tamamen küçük harflerden oluşmalıdır,** örn. browser = chrome. Şu an desteklenen browser'lar: Chrome ve Firefox
- **Testler @Nested annotation'ı kullanılarak üç adet class'ta organize edilmiştir, lokasyonları:** src/test/java/testclasses/
### Dosya lokasyonları
- Log dosyası ve ürün bilgileri dosyası proje altına çıkmaktadır. Log dosyası lokasyonu: src/main/resources/logfile.log
- Ürün adı ve fiyatının yazdırıldığı txt dosyası lokasyonu: src/main/resources/productinfo.txt
- Test datalarının json şeklinde tutulduğu dosya lokasyonu: testdata/testdata.json. Halihazırdaki dosyada gerekli tüm bilgiler vardır.
### API testlerinde kullanılan siteler
- Rest Assured ile API testleri için iki farklı site kullanışmıştır: http://thedemosite.co.uk/ ve http://dummy.restapiexample.com/ . http://thedemosite.co.uk/ html, http://dummy.restapiexample.com/ json döner.

