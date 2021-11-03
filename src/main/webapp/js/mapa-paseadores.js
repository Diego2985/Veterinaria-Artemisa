function mostrarPaseadores(latitud, longitud, paseadores) {
    window.addEventListener("load", () => {
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

        var group = new H.map.Group();
        map.addObject(group);

        group.addEventListener('tap', function (evt) {

            var bubble = new H.ui.InfoBubble(evt.target.getGeometry(), {
                content: evt.target.getData()
            });
            // show info bubble
            ui.addBubble(bubble);
        }, false);

        var pointer = new H.map.Icon('images/home.png', {size: {w: 30, h: 30}});
        var casa = new H.map.Marker({lat: latitud, lng: longitud}, {icon: pointer});
        map.addObject(casa);

        const customMarker = (latitud, longitud) => {
            const markerPaseador = new H.map.Icon('images/paseador.png', {size: {w: 30, h: 30}});
            const paseador = new H.map.Marker({lat: latitud, lng: longitud}, {icon: markerPaseador});
            return paseador;
        }

        const htmlPaseador = (inicio, marker, paseador) => {
            const distancia = inicio.getGeometry().distance(marker.getGeometry());
            const validarCantidadMaxima=paseador.cantidadActual>=paseador.cantidadMaxima-3;
            return `
                <div>
                    <div><strong>Paseador</strong></div>
                    <div>${paseador.estrellas} <i style='color: #FFE445' class='fas fa-star'></i>
                     | <span style='color:${validarCantidadMaxima ? 'red' : 'black'}' }>${paseador.cantidadActual} <i class='fas fa-dog'></i></span>
                     </div>
                    <div>Se encuentra a <strong>${Math.round(distancia)} mts.</strong></div>
                    <div>
                        <form action='contratar-paseador' method='post'>
                            <input type='hidden' value='" + paseador.id + "' name='idPaseador' />
                            <button type='submit' class='btn btn-success'>Contratar</button>
                        </form>
                    </div>
                </div>
            `
            return "<div><strong>Paseador</strong></div>" +
                "<div>" + paseador.estrellas + " <i style='color: #FFE445' class=\"fas fa-star\"></i></div>" +
                "<div>Se encuentra a <strong>" + Math.round(distancia) + " mts.</strong></div>" +
                "<div><i class=\"fas fa-dog\"></i>"+paseador.cantidadActual+"</div>" +
                "<div>" +
                "<form action='contratar-paseador' method='post'>" +
                "<input type='hidden' value='" + paseador.id + "' name='idPaseador' />" +
                "<button type='submit' class='btn btn-success'>Contratar</button>" +
                "</form>" +
                "</div>"
        }


        const addBubble = (html, paseador) => {
            paseador.setData(html);
            group.addObject(paseador);
        }

        const generarData = (inicio, paseador) => {
            const marker = customMarker(paseador.latitud, paseador.longitud);
            const html = htmlPaseador(inicio, marker, paseador);
            addBubble(html, marker)
        }

        if (paseadores != "[]" && paseadores.length > 0) {
            for (let paseador of paseadores) {
                generarData(casa, paseador)
            }
        }

        var service = platform.getSearchService();
        service.reverseGeocode({
            at: `${mapOptions.center.lat},${mapOptions.center.lng}`
        }, (result) => {
            const locacion = document.getElementById("locacion")
            const datos = result.items[0].address.label
            locacion.innerHTML = datos
        });

    })
}