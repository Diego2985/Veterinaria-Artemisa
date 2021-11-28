package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Conversacion;
import ar.edu.unlam.tallerweb1.modelo.Mensaje;
import ar.edu.unlam.tallerweb1.modelo.OutputMessage;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioChat;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ControladorChat {

//    private final SimpMessagingTemplate template;

    private final ServicioChat servicioChat;
    private final ServicioUsuarios servicioUsuarios;
    private Long userId;
    private Long receptorId;

    @Autowired
    public ControladorChat(ServicioChat servicioChat, ServicioUsuarios servicioUsuarios) {
        this.servicioChat = servicioChat;
        this.servicioUsuarios = servicioUsuarios;
    }

    @RequestMapping(path="/chatear", method= RequestMethod.GET)
    public ModelAndView irAChatear(@RequestParam("idUsuario") String idUsuario, HttpServletRequest request) {
        ModelMap model = new ModelMap();
        userId = (Long) request.getSession().getAttribute("userId");

        receptorId = Long.parseLong(idUsuario);
        Usuario usuario = servicioUsuarios.getUsuarioPorId(receptorId);

        model.put("userId", userId);
        model.put("receptor", usuario);

        return new ModelAndView("chat", model);
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(Mensaje mensaje) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        OutputMessage mensajeAGuardar = new OutputMessage(mensaje.getFrom(), mensaje.getText(), time);
        mensajeAGuardar.setUserReceptorId(receptorId);
        servicioChat.guardarMensaje(mensajeAGuardar);
        return mensajeAGuardar;
    }
}
