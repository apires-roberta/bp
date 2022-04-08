package com.bp.bplogincadastro.repositorio;

import com.bp.bplogincadastro.entidade.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DoadorRepository extends JpaRepository<Doador, Long> {
    List<Doador> findByEmail(String email);
    //Para alteracoes no banco precisa usar essas anotacoes
    @Transactional //Tem que pegar o do Spring FrameWork
    @Modifying //Tem que pegar o do JPA
    @Query("update Doador d set d.autenticado = ?2 where d.email =?1")
    void Logar(String email, boolean autenticado);
}
