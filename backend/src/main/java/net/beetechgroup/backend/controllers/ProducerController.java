package net.beetechgroup.backend.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProducerController {

    @GetMapping
    @PreAuthorize("hasRole('CLIENT')")
    public String list() {
        return "Listando produtos";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String create() {
        return "Cadastrando produtos";
    }
}
