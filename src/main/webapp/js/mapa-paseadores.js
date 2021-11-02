function mostrarPaseadores(latitud, longitud){
    window.addEventListener("load",()=>{
        var platform = new H.service.Platform({
            'apikey': MAP_KEY
        });
        var maptypes = platform.createDefaultLayers();

        const mapOptions={
            zoom: 17,
            center: { lat: latitud , lng: longitud }
        }
        const mapaElement=document.getElementById("mapContainer");
        const map=new H.Map(mapaElement,maptypes.vector.normal.map,mapOptions)
        const mapEvents = new H.mapevents.MapEvents(map);
        const ui = H.ui.UI.createDefault(map, maptypes, 'es-ES');

        var icon = new H.map.Icon('https://cdn0.iconfinder.com/data/icons/dog-4/100/dog-10-256.png');
        var marker = new H.map.Marker({lat: latitud, lng: longitud}, {icon: icon});

        map.addObject(marker);

        var service = platform.getSearchService();
        service.reverseGeocode({
            at: `${mapOptions.center.lat},${mapOptions.center.lng}`
        }, (result) => {
            const locacion=document.getElementById("locacion")
            const datos=result.items[0].address.label
            locacion.innerHTML=datos
        });

    })
}