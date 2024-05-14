/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.gerenciamentopessoas.pessoa.service;


import com.api.gerenciamentopessoas.pessoa.model.PessoaModel;
import com.api.gerenciamentopessoas.pessoa.repository.PessoaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author felip
 */
@Service
public class PessoaService {
    
    final PessoaRepository pessoaRepository;
    
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }
    
    @Transactional
    public PessoaModel save(PessoaModel pessoaModel){
        return pessoaRepository.save(pessoaModel);
    }
    
    public List<PessoaModel> findAll(){
        return pessoaRepository.findAll();
    }
    
    public Optional<PessoaModel> findById(Long id){
        return pessoaRepository.findById(id);
    }
    @Transactional
    public void delete(PessoaModel pessoaModel){
        pessoaRepository.delete(pessoaModel);
    }  
}
