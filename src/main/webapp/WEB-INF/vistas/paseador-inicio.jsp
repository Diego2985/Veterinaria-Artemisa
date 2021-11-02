<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file = "partial/header.jsp" %>
<html>
<head>
    <title>Veterinaria</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <div class="container mt-3">
        <h2>Pasear a la mascota</h2>
        <form action="ver-paseadores" method="post">
            <div>
                <label>Por favor haga click aquí para capturar su ubicación</label>
                <div class="input-group mb-3">
                    <input type="text" class="form-control" readonly="true" name="mensaje" placeholder="Espere hasta que aparezca el mensaje de Listo" id="setMensaje" aria-describedby="button-addon1">
                    <input type="hidden" name="latitud" id="setLatitud">
                    <input type="hidden" name="longitud" id="setLongitud">
                    <button class="btn btn-primary" type="button" id="getUbicacion">
                        <i class="fas fa-map-marker-alt"></i>
                    </button>
                </div>
            </div>
            <button type="submit" id="continuar" disabled="true" class="btn btn-primary">Continuar</button>
        </form>
    </div>
    <script src="js/capturar-ubicacion.js"></script>
</body>
</html>
