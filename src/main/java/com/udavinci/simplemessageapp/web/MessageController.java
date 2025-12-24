package com.udavinci.simplemessageapp.web;

import com.udavinci.simplemessageapp.service.MessageService;
import com.udavinci.simplemessageapp.web.dto.MessageForm;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showForm(Model model) {
        if (!model.containsAttribute("messageForm")) {
            model.addAttribute("messageForm", new MessageForm());
        }
        model.addAttribute("messageCount", service.count());
        return "index";
    }

    @PostMapping("/messages")
    public String submitMessage(@Valid @ModelAttribute("messageForm") MessageForm form,
                                BindingResult bindingResult,
                                Model model) {

        if (bindingResult.hasErrors()) {
            // volvemos a la misma vista con los errores y el contador
            model.addAttribute("messageCount", service.count());
            return "index";
        }

        // aquí ya sabemos que el texto NO está vacío y cumple tamaño
        service.save(form.getText());

        model.addAttribute("lastMessage", form.getText());
        model.addAttribute("messageCount", service.count());

        return "thanks";
    }

    @GetMapping("/thanks")
    public String showThanks() {
        return "thanks";
    }
}