/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.gerenciamentopessoas.pessoa.controller;

import com.api.gerenciamentopessoas.pessoa.dtos.PessoaDto;
import com.api.gerenciamentopessoas.pessoa.model.PessoaModel;
import com.api.gerenciamentopessoas.pessoa.service.PessoaService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author felip
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/pessoa/v1")
public class PessoaController {
    
    final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

   @PostMapping
    public ResponseEntity<Object> savePessoa(@RequestBody @Valid PessoaDto pessoaDto){
        PessoaModel pessoaModel = new PessoaModel();
        //convertendo dto para model
        BeanUtils.copyProperties(pessoaDto, pessoaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoaModel));
    }
    
    @GetMapping
    public ResponseEntity<List<PessoaModel>> getAllPessoa(){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePessoa(@PathVariable(value = "id") Long id){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(id);
        if(!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not found.");
        }       
        return ResponseEntity.status(HttpStatus.OK).body(pessoaModelOptional.get());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePessoa(@PathVariable(value = "id")Long id){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(id);
        if(!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not found");
        }
        pessoaService.delete(pessoaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deleted with sucessfully.");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePessoa(@PathVariable(value = "id")Long id, @RequestBody @Valid PessoaDto pessoaDto){    
        Optional <PessoaModel> pessoaModelOptional = pessoaService.findById(id);
        if(!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not Found");
        }
        PessoaModel pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(pessoaDto, pessoaModel);
        pessoaModel.setId(pessoaModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoaModel));
    }
}
