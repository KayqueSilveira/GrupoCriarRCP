package com.grupo.criar.RCP.Controller;

import com.grupo.criar.RCP.Models.Proof;
import com.grupo.criar.RCP.Service.impl.ProofServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("proof")
public class ProofController {

    @Autowired
    ProofServiceImpl proofService;

    @GetMapping("get/{id}")
    public Proof getProof(@PathVariable Long id) {
        return proofService.getProof(id);
    }
}
