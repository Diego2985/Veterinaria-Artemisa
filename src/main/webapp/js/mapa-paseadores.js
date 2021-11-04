function mostrarPaseadores(latitud, longitud, paseadores) {
    window.addEventListener("load", () => {
        function datosMapa(map, casa, ui){
            var group = new H.map.Group();
            map.addObject(group);

            group.addEventListener('tap', function (evt) {

                var bubble = new H.ui.InfoBubble(evt.target.getGeometry(), {
                    content: evt.target.getData()
                });
                // show info bubble
                ui.addBubble(bubble);
            }, false);

            const customMarker = (latitud, longitud) => {
                const markerPaseador = new H.map.Icon('images/paseador.png', {size: {w: 30, h: 30}});
                const paseador = new H.map.Marker({lat: latitud, lng: longitud}, {icon: markerPaseador});
                return paseador;
            }

            const htmlPaseador = (inicio, marker, paseador) => {
                const distancia = inicio.getGeometry().distance(marker.getGeometry());
                const validarAproxACantMax=paseador.cantidadActual>=paseador.cantidadMaxima-3;
                const validarLlegadaACantMax=paseador.cantidadActual>=paseador.cantidadMaxima;
                return `
                <div>
                    <div><strong>Paseador</strong></div>
                    <div>${paseador.estrellas} <i style='color: #FFE445' class='fas fa-star'></i>
                     | <span style='color:${validarAproxACantMax ? 'red' : 'black'}' }>${paseador.cantidadActual} <i class='fas fa-dog'></i></span>
                     </div>
                    <div>Se encuentra a <strong>${Math.round(distancia)} mts.</strong></div>
                    <div>
                        <form action='contratar-paseador' method='post'>
                            <input type='hidden' value='${paseador.id}' name='idPaseador' />
                            <input type='hidden' value='${latitud}' name='latitud' />
                            <input type='hidden' value='${longitud}' name='longitud' />
                            ${
                    !validarLlegadaACantMax ?
                        '<button type="submit" class="btn btn-success">Contratar</button>' :
                        '<button type="button" disabled class="btn btn-danger">No Disponible</button>'
                }
                        </form>
                    </div>
                </div>
            `
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
        }

        mostrarMapa(latitud, longitud, datosMapa)

    })
}