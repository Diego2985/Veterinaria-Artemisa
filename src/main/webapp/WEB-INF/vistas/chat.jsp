<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="partial/header.jsp"/>
<!DOCTYPE html>
<html>
<head>
    <title>Chat WebSocket</title>
    <link rel="stylesheet" href="${contextPath}/css/chat.css">
    <script src="${contextPath}/js/sockjs.js"></script>
    <script src="${contextPath}/js/stomp2.js"></script>
    <script type="text/javascript">
        var stompClient = null;

        function setConnected(connected) {}

        function connect() {
            var socket = new SockJS('${contextPath}/chat');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                setConnected(true);
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/messages', function (messageOutput) {
                    showMessageOutput(JSON.parse(messageOutput.body));
                });
            });
        }

        function disconnect() {
            if (stompClient != null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
        }

        function sendMessage() {
            var text = document.getElementById('text').value;
            stompClient.send("/app/chat", {},
                JSON.stringify({'from':${userId}, 'text': text}));
        }

        function showMessageOutput(messageOutput) {
            var response = document.getElementById('mensajes');
            if (parseInt(messageOutput.from) === ${userId}) {
                var newChild = `
                            <li class='clearfix'>
                                <div class='message-data text-right other-message bg-transparent'>
                                     ` + messageOutput.time + `
                                </div>
                                <div class='message other-message float-right'> ` + messageOutput.text + ` </div>
                            </li>
                        `
                response.insertAdjacentHTML('beforeend', newChild);
            } else {
                var newChild2 = `
                            <li class='clearfix'>
                                <div class='message-data'>
                                    <span class='message-data-time'>` + messageOutput.time + `</span>
                                </div>
                                <div class='message my-message'>` + messageOutput.text + `</div>
                            </li>
                        `
                response.insertAdjacentHTML('beforeend', newChild2);
            }
            document.getElementById('text').value = '';
        }
    </script>
</head>
<body onload="connect()" onunload="disconnect()">

    <div class="container">
        <div class="row clearfix">
            <div class="col-lg-12">
                <div class="card mt-5">
                    <div class="chat">
                        <div class="chat-header clearfix">
                            <div class="row">
                                <div class="col-lg-6">
                                    <c:if test="${receptor != null}">
                                        <a href="javascript:void(0);" data-toggle="modal" data-target="#view_info">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar2.png" alt="avatar">
                                        </a>
                                        <div class="chat-about">
                                            <h6 class="m-b-0">${receptor.nombre}</h6>
                                            <c:choose>
                                                <c:when test="${receptor.mascotas.size() == 0}">
                                                    <small>No tiene mascotas</small>
                                                </c:when>
                                                <c:when test="${receptor.mascotas.size() == 1}">
                                                    <small>1 mascota</small>
                                                </c:when>
                                                <c:otherwise>
                                                    <small>${receptor.mascotas.size()} mascotas</small>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="chat-history">
                            <ul class="m-b-0" id="mensajes">
                                <c:forEach var="mensaje" items="${mensajes}">
                                    <c:choose>
                                        <c:when test="${mensaje.from == userId}">
                                            <li class='clearfix'>
                                                <div class='message-data text-right other-message bg-transparent'>
                                                        ${mensaje.time}
                                                </div>
                                                <div class='message other-message float-right'>${mensaje.text}</div>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class='clearfix'>
                                                <div class='message-data'>
                                                    <span class='message-data-time'>${mensaje.time}</span>
                                                </div>
                                                <div class='message my-message'>${mensaje.text}</div>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </ul>
                        </div>

                        <div class="chat-message clearfix">
                            <div class="input-group mb-0">
                                <input type="text" id="text" class="form-control" placeholder="Escribe un mensaje...">
                                <button class="btn btn-primary" id="sendMessage" onclick="sendMessage();">Enviar</button>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

<%@ include file="partial/footer.jsp" %>
</body>
</html>
