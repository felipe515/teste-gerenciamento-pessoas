/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.api.gerenciamentopessoas.pessoa.service;

import com.api.gerenciamentopessoas.pessoa.model.PessoaModel;
import com.api.gerenciamentopessoas.pessoa.repository.PessoaRepository;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author felip
 */
@ExtendWith(MockitoExtension.class) 
public class PessoaServiceTest {
    
    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;
    
    public PessoaServiceTest() {
    }

    /**
     * Test of save method, of class PessoaService.
     */
    @Test
    public void testSave() {
   
        PessoaModel pessoaModel = new PessoaModel();
        pessoaModel.setNomeCompleto("Fulano de Tal");
        pessoaModel.setDataNascimento(new Date()); 

        when(pessoaRepository.save(any(PessoaModel.class))).thenReturn(pessoaModel);

        PessoaModel savedPessoa = pessoaService.save(pessoaModel);

        verify(pessoaRepository).save(pessoaModel);

        assertEquals(pessoaModel, savedPessoa); 
    }


    /**
     * Test of findAll method, of class PessoaService.
     */
    @Test
    public void testFindAll() {
       
        PessoaModel pessoa1 = new PessoaModel();
        pessoa1.setNomeCompleto("Fulano de Tal");
        pessoa1.setDataNascimento(new Date());

        PessoaModel pessoa2 = new PessoaModel();
        pessoa2.setNomeCompleto("Ciclano de Tal");
        pessoa2.setDataNascimento(new Date());

        List<PessoaModel> pessoas = Arrays.asList(pessoa1, pessoa2);

        when(pessoaRepository.findAll()).thenReturn(pessoas);

        List<PessoaModel> allPessoas = pessoaService.findAll();

        verify(pessoaRepository).findAll();

        assertEquals(pessoas, allPessoas); 
    }

    /**
     * Test of findById method, of class PessoaService.
     */
    @Test
    public void testFindById() {
        
        PessoaModel pessoaModel = new PessoaModel();
        pessoaModel.setId(1L); 
        pessoaModel.setNomeCompleto("Fulano de Tal");
        pessoaModel.setDataNascimento(new Date());

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoaModel));

        Optional<PessoaModel> foundPessoa = pessoaService.findById(1L);

        verify(pessoaRepository).findById(1L);

        assertTrue(foundPessoa.isPresent()); 
        assertEquals(pessoaModel, foundPessoa.get()); 
    }

    @Test
    public void testFindByIdNotFound() {
  
        when(pessoaRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<PessoaModel> foundPessoa = pessoaService.findById(2L);

        verify(pessoaRepository).findById(2L);

        assertFalse(foundPessoa.isPresent()); 
    }

    /**
     * Test of delete method, of class PessoaService.
     */
 @Test
    public void testDelete() {
        
        PessoaModel pessoaModel = new PessoaModel();
        pessoaModel.setId(1L); 
        pessoaModel.setNomeCompleto("Fulano de Tal");
        pessoaModel.setDataNascimento(new Date());

        pessoaService.delete(pessoaModel);

        verify(pessoaRepository).delete(pessoaModel);
    }
    
}
