package com.example.mtsfinalprojectsecurity.controller;

import com.example.mtsfinalprojectsecurity.dto.LoanOrderCreateDto;
import com.example.mtsfinalprojectsecurity.dto.LoanOrderDeleteDto;
import com.example.mtsfinalprojectsecurity.model.Response;
import com.example.mtsfinalprojectsecurity.service.UserService;
import com.example.mtsfinalprojectsecurity.service.WebClientService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@AllArgsConstructor
@RequestMapping("account")
public class CreditController {

    private WebClientService webClientService;

    private UserService userService;

    @GetMapping
    public String showUserAccount(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = userService.getUserByUsername(authentication.getName()).getId();
        model.addAttribute("orders", webClientService.getUserOrders(userId).getData().getOrders());
        return "accountPage";
    }

    @GetMapping("createOrder")
    public String showCreateOrderPage(Model model) {
        model.addAttribute("create", new LoanOrderCreateDto());
        return "createOrderPage";
    }

    @GetMapping("deleteOrder")
    public String showDeleteOrderPage(Model model) {
        model.addAttribute("delete", new LoanOrderDeleteDto());
        return "deleteOrderPage";
    }


    @GetMapping("tariffs")
    public String getTariffs(Model model) {
        model.addAttribute("tariffs", webClientService.getTariffs().getData().getTariffs());
        return "tariffsPage";
    }

    @PostMapping("createOrder")
    public String createOrder(@ModelAttribute("create") LoanOrderCreateDto createDto, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        createDto.setUserId(userService.getUserByUsername(authentication.getName()).getId());

        Response response = webClientService.createOrder(createDto);

        if (response.getError() != null) {
            model.addAttribute("error", response.getError());
            return "errorPage";
        }
        return "redirect:http://localhost:8765/security-service/account";
    }

    @PostMapping("deleteOrder")
    public String deleteOrder(@ModelAttribute("delete") LoanOrderDeleteDto deleteDto, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        deleteDto.setUserId(userService.getUserByUsername(authentication.getName()).getId());

        Response response = webClientService.deleteOrder(deleteDto);

        if (response != null) {
            model.addAttribute("error", response.getError());
            return "errorPage";
        }
        return "redirect:http://localhost:8765/security-service/account";

    }


}
