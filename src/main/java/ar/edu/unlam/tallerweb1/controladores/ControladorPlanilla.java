package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



    @Controller
    public class ControladorPlanilla {
        @Value("TEST-0bd8f6ff-620f-4df7-a047-670ca51a949d")
        private String mercadoPagoSamplePublicKey;


        @RequestMapping("/")
        public String renderMainPage(Model model) {
            model.addAttribute("publicKey", mercadoPagoSamplePublicKey);
            return "index";
        }
    }

