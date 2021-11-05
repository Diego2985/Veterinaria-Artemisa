function mostrarMapa(latitud, longitud, callback=null){
    var platform = new H.service.Platform({
        'apikey': MAP_KEY
    });
    var maptypes = platform.createDefaultLayers();

    const mapOptions = {
        zoom: 17,
        center: {lat: latitud, lng: longitud}
    }
    const mapaElement = document.getElementById("mapContainer");
    const map = new H.Map(mapaElement, maptypes.vector.normal.map, mapOptions)
    var behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(map));
    const ui = H.ui.UI.createDefault(map, maptypes, 'es-ES');

    var pointer = new H.map.Icon('images/home.png', {size: {w: 30, h: 30}});
    var casa = new H.map.Marker({lat: latitud, lng: longitud}, {icon: pointer});
    map.addObject(casa);

    if(callback) callback(map, casa, ui);
}