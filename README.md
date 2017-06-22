# downloader
Консольное приложение, которое осуществляет загрузку файлов по HTTP протоколу.
### Входные параметры
*`l`* - ссылка на файл.<br>
*`p`* - путь на файловой системе, куда сохранять файл.<br>
*`n`* - имя сохраняемого файла.<br>
*`f`* - файл со списком ссылок и названий. Исключает использование параметров *`l`* и *`n`*.<br>
*`t`* - количество потоков(необязательный). Ограничение на потоки - не более 20.<br>
 **`Поддерживает 3 типа файлов:`**
 * CSV<br>
 * XML<br>
 * JSON <br>

 **Пример входного   ` CSV `  файла. Элементы разделяются запятыми.**  <br>
 ```cmd
  https://oboi.ws/wallpapers/27_12502_oboi_ryzhaja_lisa_v_snegu_2560x2048.jpg,fox.jpg 
  https://oboi.ws/wallpapers/27_12489_oboi_pes_sredi_listvy_2560x2048.jpg,dog.jpg 
  https://oboi.ws/wallpapers/27_12456_oboi_leto_v_lesu_2560x2048.jpg,forest.jpg 
  https://oboi.ws/wallpapers/27_12391_oboi_himicheskie_formuly_2560x2048.jpg,him.jpg 
  http://cosmostv.by/docs/speed/10_m.zip,test1.zip 
 ```
 
 **Пример входного  ` XML `   файла.**  <br>
```xml <?xml version="1.0" encoding="UTF-8"?>
<filesInfo>
  <fileInfo>
    <link>http://mathus.ru/math/isogonal.pdf</link>
    <fileName>isogonal.pdf</fileName>
  </fileInfo>
  <fileInfo>
    <link>http://eloquentjavascript.net/Eloquent_JavaScript.pdf</link>
    <fileName>JS.pdf</fileName>
  </fileInfo>
  <fileInfo>
    <link>https://assets.rbl.ms/4314213/980x.jpg</link>
    <fileName>cat.jpg</fileName>
  </fileInfo>
</filesInfo>
```
 **Пример входного  ` JSON `   файла.**  <br>
 
```php
 {
    "filesInfo": [
      {
        "link": "http://mathus.ru/math/isogonal.pdf",
        "fileName": "isogonal.pdf"
      },
      {
        "link": "http://eloquentjavascript.net/Eloquent_JavaScript.pdf",
        "fileName": "JS.pdf"
      },
      {
        "link": "https://assets.rbl.ms/4314213/980x.jpg",
        "fileName": "cat.jpg"
      }
    ]
}
```
### Выполнение
Для запуска приложения необходимо запустить `downloader-0.0.1-SNAPSHOT-jar-with-dependencies.jar` из папки `target` в командной строке, передав туда необходимые параметры.<br>
**Примеры:**
```cmd
java -jar downloader-0.0.1-SNAPSHOT-jar-with-dependencies.jar -p C://1 -n cat.jpg -l https://dobbless.files.wordpress.com/2010/03/cheshir_726123.png
java -jar downloader-0.0.1-SNAPSHOT-jar-with-dependencies.jar -p C://1 -f myfiles.csv -t 10
java -jar downloader-0.0.1-SNAPSHOT-jar-with-dependencies.jar -p C://1 -f files.json 
```


