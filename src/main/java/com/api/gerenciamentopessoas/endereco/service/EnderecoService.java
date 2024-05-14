/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.gerenciamentopessoas.endereco.service;

import com.api.gerenciamentopessoas.endereco.model.EnderecoModel;
import com.api.gerenciamentopessoas.endereco.repository.EnderecoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author felip
 */
@Service
public class EnderecoService {
    
    final EnderecoRepository enderecoRepository;
    
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }
    
    @Transactional
    public EnderecoModel save(EnderecoModel enderecoModel){
        return enderecoRepository.save(enderecoModel);
    }
    
    public List<EnderecoModel> findAll(){
        return enderecoRepository.findAll();
    }
    
    public Optional<EnderecoModel> findById(Long id){
        return enderecoRepository.findById(id);
    }
     @Transactional
    public void delete(EnderecoModel enderecoModel){
        enderecoRepository.delete(enderecoModel);
    }  
}
