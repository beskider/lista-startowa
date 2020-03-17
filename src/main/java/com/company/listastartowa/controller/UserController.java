package com.company.listastartowa.controller;

import com.company.listastartowa.model.User;
import com.company.listastartowa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final ServletContext context;

    @Autowired
    public UserController(UserService userService, ServletContext context) {
        this.userService = userService;
        this.context = context;
    }

    @GetMapping("/")
    public String home() {
        logger.debug("Wywolano metode home() klasy UserController");
        return "index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        logger.debug("Wywolano metode users() klasy UserController");
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/users/add")
    public String addUserForm(Model model) {
        logger.debug("Wywolano metode addUserForm() klasy UserController (HTTP GET)");
        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping("/users/add")
    public String addUser(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        logger.debug("Wywolano metode addUser() klasy UserController (HTTP POST), uzytkownik: {}", user);

        if(bindingResult.hasErrors()) {
            logger.debug("Bledy w formularzu, podano: " + user);
            logger.debug("Bledy: " + bindingResult.toString());
            return "user";
        }

        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable String id, Model model) {
        logger.debug("Wywolano metode editUserForm() klasy UserController, id: {}", id);
        model.addAttribute("user", userService.getUser(Long.parseLong(id)));
        return "user";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        logger.debug("Wywolano metode deleteUser() klasy UserController, id: {}", id);
        userService.deleteUser(Long.parseLong(id));
        return "redirect:/users";
    }

    @GetMapping("/users/createPdf")
    public void createPdf(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Wywolano metode createPdf() klasy UserController");
        List<User> users = userService.getAllUsers();
        boolean isFlag = userService.createPdf(users, context, request, response);
        if(isFlag) {
            String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "uczestnicy" + ".pdf");
            filedownload(fullPath, response, "uczestnicy.pdf");
        }
    }

    @GetMapping("/users/createExcel")
    public void createExcel(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Wywolano metode createExcel() klasy UserController");
        List<User> users = userService.getAllUsers();
        boolean isFlag = userService.createExcel(users, context, request, response);
        if(isFlag) {
            String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "uczestnicy" + ".xls");
            filedownload(fullPath, response, "uczestnicy.xls");
        }
    }

    private void filedownload(String fullPath, HttpServletResponse response, String fileName) {
        File file = new File(fullPath);
        final int ROZMIAR_BUFORA = 4096;
        if(file.exists()) {
            try {
                FileInputStream inputStream = new FileInputStream(file);
                String mimeType = context.getMimeType(fullPath);
                response.setContentType(mimeType);
                response.setHeader("content-disposition", "attachment; filename=" + fileName);
                OutputStream outputStream = response.getOutputStream();
                byte[] buffer = new byte[ROZMIAR_BUFORA];
                int bytesRead = -1;
                while((bytesRead = inputStream.read(buffer))!= -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
                outputStream.close();
                file.delete();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

}
