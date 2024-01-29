    var scriptTag = document.getElementById('myScript');
    var x = scriptTag.getAttribute('arg1');
    var y = scriptTag.getAttribute('arg2');

    let center = [x, y];

    function init() {
    let map = new ymaps.Map("map-test", {
    center: center,
    zoom: 17,
    });

    let placemark = new ymaps.Placemark(center, {}, {});

    map.controls.remove("geolocationControl"); // удаляем геолокацию
    map.controls.remove("searchControl"); // удаляем поиск
    map.controls.remove("trafficControl"); // удаляем контроль трафика
    map.controls.remove("typeSelector"); // удаляем тип
    map.controls.remove("fullscreenControl"); // удаляем кнопку перехода в полноэкранный режим
    map.controls.remove("rulerControl"); // удаляем контрол правил
    map.behaviors.disable(["scrollZoom"]); // отключаем скролл карты (опционально)

    map.geoObjects.add(placemark);
    }

    ymaps.ready(init);