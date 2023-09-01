package br.com.cnpj.api.controller;

import br.com.cnpj.api.model.DTO.CNPJInfoDTO;
import br.com.cnpj.api.model.entity.CNPJ;
import br.com.cnpj.api.model.service.CNPJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cnpj")
public class CNPJController {

    @Autowired
    private CNPJService cnpjService;

    @GetMapping
    public ResponseEntity<?> getAllCNPJ() {
        try {
            List<CNPJ> response = cnpjService.findAll();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unexpected error, please try again in a moment.");
        }
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<?> getCNPJInfo(@PathVariable String cnpj) {
        try {
            CNPJInfoDTO response = cnpjService.searchCNPJ(cnpj);

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> postCNPJ(@RequestBody CNPJInfoDTO cnpj) {
        try {
            CNPJ response = cnpjService.saveCNPJ(cnpj);

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unexpected error, please try again in a moment.");
        }
    }
}
