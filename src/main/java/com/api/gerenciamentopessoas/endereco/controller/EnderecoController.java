/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.gerenciamentopessoas.endereco.controller;

import com.api.gerenciamentopessoas.endereco.dtos.EnderecoDto;
import com.api.gerenciamentopessoas.endereco.model.EnderecoModel;
import com.api.gerenciamentopessoas.endereco.service.EnderecoService;
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
@CrossOrigin(origins ="*", maxAge = 3600)
@RequestMapping("/api/endereco/v1")
public class EnderecoController {
    
     final EnderecoService enderecoService;
     
     final PessoaService pessoaService;

    public EnderecoController(EnderecoService enderecoService, PessoaService pessoaService) {
        this.enderecoService = enderecoService;
        this.pessoaService = pessoaService;
    }

  
    @PostMapping("/{pessoaId}")
    public ResponseEntity<Object> saveEndereco(@PathVariable Long pessoaId, @RequestBody @Valid EnderecoDto enderecoDto){
    Optional<PessoaModel> pessoaOptional = pessoaService.findById(pessoaId);
    if (pessoaOptional.isPresent()) {
        
         // Lógica para Endereço Principal:
        if (enderecoDto.isEnderecoPrincipal()) { 
        pessoaOptional.get().getEnderecos().stream()
                .filter(EnderecoModel::isEnderecoPrincipal)
                .findFirst()
                .ifPresent(end -> end.setEnderecoPrincipal(false)); 
        }
        
        EnderecoModel enderecoModel = new EnderecoModel();
        BeanUtils.copyProperties(enderecoDto, enderecoModel);
        enderecoModel.setPessoa(pessoaOptional.get()); 
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(enderecoModel));
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not found.");
    }
}
    
    @GetMapping
    public ResponseEntity<List<EnderecoModel>> getAllEndereco(){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneEndereco(@PathVariable(value = "id") Long id){
        Optional <EnderecoModel> enderecoModelOptional = enderecoService.findById(id);
        if(!enderecoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco not found.");
        }       
        return ResponseEntity.status(HttpStatus.OK).body(enderecoModelOptional.get());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEndereco(@PathVariable(value = "id")Long id){
        Optional <EnderecoModel> enderecoModelOptional = enderecoService.findById(id);
        if(!enderecoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco not found");
        }
        enderecoService.delete(enderecoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Endereco deleted with sucessfully.");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEndereco(@PathVariable(value = "id")Long id, @RequestBody @Valid EnderecoDto enderecoDto){    
        Optional <EnderecoModel> enderecoModelOptional = enderecoService.findById(id);
        if(!enderecoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco not Found");
        }
        
        if (enderecoDto.isEnderecoPrincipal()) {
            Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(enderecoModelOptional.get().getPessoa().getId());
                if (pessoaModelOptional.isPresent()) {
                    pessoaModelOptional.get().getEnderecos().stream()
                        .filter(EnderecoModel::isEnderecoPrincipal)
                        .findFirst()
                        .ifPresent(end -> end.setEnderecoPrincipal(false)); 
                }
        }
        
        EnderecoModel enderecoModel = new EnderecoModel();
        BeanUtils.copyProperties(enderecoDto, enderecoModel);
        enderecoModel.setId(enderecoModelOptional.get().getId());
        enderecoModel.setPessoa(enderecoModelOptional.get().getPessoa()); // Mantendo a associação com a mesma pessoa
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.save(enderecoModel));
    }
}
