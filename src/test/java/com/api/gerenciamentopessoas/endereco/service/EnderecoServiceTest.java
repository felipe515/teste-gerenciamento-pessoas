/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.api.gerenciamentopessoas.endereco.service;

import com.api.gerenciamentopessoas.endereco.model.EnderecoModel;
import com.api.gerenciamentopessoas.endereco.repository.EnderecoRepository;
import com.api.gerenciamentopessoas.pessoa.model.PessoaModel;
import java.util.Arrays;
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
public class EnderecoServiceTest {
    
    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @Test
    public void testSave() {
        
        PessoaModel pessoaModel = new PessoaModel();
        pessoaModel.setId(1L);

        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setLogradouro("Rua Teste");
        enderecoModel.setCep("12345-678");
        enderecoModel.setNumero("123");
        enderecoModel.setCidade("São Paulo");
        enderecoModel.setEstado("SP");
        enderecoModel.setEnderecoPrincipal(false);
        enderecoModel.setPessoa(pessoaModel);

        when(enderecoRepository.save(any(EnderecoModel.class))).thenReturn(enderecoModel);

        EnderecoModel savedEndereco = enderecoService.save(enderecoModel);

        verify(enderecoRepository).save(enderecoModel);

        assertEquals(enderecoModel, savedEndereco);
    }

    /**
     * Test of findAll method, of class EnderecoService.
     */
   @Test
    public void testFindAll() {
        PessoaModel pessoaModel = new PessoaModel();
        pessoaModel.setId(1L);

        EnderecoModel endereco1 = new EnderecoModel();
        endereco1.setId(1L);
        endereco1.setPessoa(pessoaModel);

        EnderecoModel endereco2 = new EnderecoModel();
        endereco2.setId(2L);
        endereco2.setPessoa(pessoaModel);

        List<EnderecoModel> enderecos = Arrays.asList(endereco1, endereco2);

        when(enderecoRepository.findAll()).thenReturn(enderecos);

        List<EnderecoModel> allEnderecos = enderecoService.findAll();

        verify(enderecoRepository).findAll();

        assertEquals(enderecos, allEnderecos);
    }

    /**
     * Test of findById method, of class EnderecoService.
     */
    @Test
    public void testFindById() {

        PessoaModel pessoaModel = new PessoaModel();
        pessoaModel.setId(1L);

        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(1L);
        enderecoModel.setPessoa(pessoaModel);

        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(enderecoModel));

        Optional<EnderecoModel> foundEndereco = enderecoService.findById(1L);

        verify(enderecoRepository).findById(1L);

        assertTrue(foundEndereco.isPresent()); 
        assertEquals(enderecoModel, foundEndereco.get());
    }

    @Test
    public void testFindByIdNotFound() {

        when(enderecoRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<EnderecoModel> foundEndereco = enderecoService.findById(2L);

        verify(enderecoRepository).findById(2L);

        assertFalse(foundEndereco.isPresent());
    }

    /**
     * Test of delete method, of class EnderecoService.
     */
    @Test
    public void testDelete() {

        PessoaModel pessoaModel = new PessoaModel();
        pessoaModel.setId(1L);

        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(1L);
        enderecoModel.setPessoa(pessoaModel);

        enderecoService.delete(enderecoModel);

        verify(enderecoRepository).delete(enderecoModel);
    }
    
}
