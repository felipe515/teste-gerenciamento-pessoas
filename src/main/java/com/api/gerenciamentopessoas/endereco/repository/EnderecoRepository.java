/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.api.gerenciamentopessoas.endereco.repository;

import com.api.gerenciamentopessoas.endereco.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author felip
 */
@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long>{
    
}
