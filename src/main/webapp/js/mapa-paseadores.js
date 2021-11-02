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
        var behavior = new H.mapevents.Behavior(new    H.mapevents.MapEvents(map));
        const ui = H.ui.UI.createDefault(map, maptypes, 'es-ES');

        var group = new H.map.Group();
        map.addObject(group);

        group.addEventListener('tap', function (evt) {

            var bubble = new H.ui.InfoBubble(evt.target.getGeometry(), {
                content: evt.target.getData()
            });
            // show info bubble
            ui.addBubble(bubble);
        }, false);

        var pointer = new H.map.Icon('images/home.png', {size: {w: 40, h: 40}});
        var casa = new H.map.Marker({lat: latitud, lng: longitud}, {icon:pointer});
        map.addObject(casa);

        var paseador = new H.map.Icon('images/paseador.png', {size: {w: 40, h: 40}});
        var paseador1 = new H.map.Marker({lat:-34.58856, lng:-58.41066}, {icon: paseador});
        const distance = casa.getGeometry().distance(paseador1.getGeometry());
        const html1='<div>Paseador</div>' +
            '<div>Se encuenta a <br />'+Math.round(distance)+' mts.</div>'
        paseador1.setData(html1);
        group.addObject(paseador1);

        var paseador2 = new H.map.Marker({lat:-34.585991, lng:-58.407848}, {icon: paseador});

        map.addObject(paseador2);

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