package com.bp.bplogincadastro.repositorio;

import com.bp.bplogincadastro.entidade.Ong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OngRepository extends JpaRepository<Ong, Long> {
    List<Ong> findByEmail(String email);
    //Para alteracoes no banco precisa usar essas anotacoes
    @Transactional //Tem que pegar o do Spring FrameWork
    @Modifying //Tem que pegar o do JPA
    @Query("update Ong o set o.autenticado = ?2 where o.email =?1")
    void Logar(String email, boolean autenticado);
}
