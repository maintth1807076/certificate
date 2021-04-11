package com.heleyquin.certificate.controller;

import com.heleyquin.certificate.entity.Certificate;
import com.heleyquin.certificate.service.CertificateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/certificates")
public class CertificateController {
    @Autowired
    CertificateService service;
    @GetMapping()
    public String getCertificates(Pageable pageable, Model model) {
        model.addAttribute("certificates", service.getCertificates(pageable));
        return "list";
    }

    @GetMapping("/{id}")
    public String getCertificateDetail(@PathVariable int id, Model model) {
        model.addAttribute("certificate", service.getCertificateById(id));
        return "detail";
    }

    @GetMapping("/create")
    public String formCreateCertificate(Model model) {
        model.addAttribute("certificate", new Certificate());
        return "create";
    }

    @PostMapping("/create")
    public String createCertificate(@ModelAttribute Certificate certificate) {
        service.createCertificate(certificate);
        return "redirect:/certificates";
    }

    @GetMapping("/{id}/edit")
    public String formEditCertificate(@PathVariable int id, Model model) {
        model.addAttribute("certificate", service.getCertificateById(id));
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String updateCertificate(@PathVariable int id, @ModelAttribute Certificate certificate) {
        if(service.updateCertificate(id, certificate)) {
            return "redirect:/certificates";
        }
        return "";
    }

    @GetMapping("/{id}/delete")
    public String deleteCertificate(@PathVariable int id) {
        service.deleteCertificate(id);
        return "redirect:/certificates";
    }
}
