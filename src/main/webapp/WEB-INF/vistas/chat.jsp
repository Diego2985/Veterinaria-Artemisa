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

        function setConnected(connected) {
            document.getElementById('connect').disabled = connected;
            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('conversationDiv').style.visibility
                = connected ? 'visible' : 'hidden';
            document.getElementById('response').innerHTML = '';
        }

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
            var from = document.getElementById('from').value;
            var text = document.getElementById('text').value;
            stompClient.send("/app/chat", {},
                JSON.stringify({'from':${userId}, 'text': text}));
        }

        function showMessageOutput(messageOutput) {
            // var response = document.getElementById('response');
            // var p = document.createElement('p');
            // p.style.wordWrap = 'break-word';
            // p.appendChild(document.createTextNode(messageOutput.from + ": "
            //     + messageOutput.text + " (" + messageOutput.time + ")"));
            // response.appendChild(p);
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
            document.getElementById('text').innerText = '';
        }
    </script>
</head>
<body onload="disconnect()">
<div>
    <div>
        <input type="text" id="from" placeholder="Choose a nickname"/>
    </div>
    <br/>
    <div>
        <button id="connect" onclick="connect();">Connect</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">
            Disconnect
        </button>
    </div>
    <br/>
    <div id="conversationDiv">
        <%--                <input type="text" id="text" placeholder="Write a message..."/>--%>
        <%--                <button id="sendMessage" onclick="sendMessage();">Send</button>--%>
        <p id="response"></p>
    </div>
</div>

<div class="container">
    <div class="row clearfix">
        <div class="col-lg-12">
            <div class="card">
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
                                        <small>3 Mascotas</small>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="chat-history">
                        <ul class="m-b-0" id="mensajes">

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
