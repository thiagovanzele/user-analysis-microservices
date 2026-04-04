package br.com.ms.user_service.repository;

import br.com.ms.user_service.model.entities.Usuario;

import br.com.ms.user_service.model.enums.UF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("""
            SELECT u FROM Usuario u
                    WHERE u.endereco.uf = :uf
            """)
    Page<Usuario> buscarUsuariosPorUf(String uf, Pageable pageable);

    @Query("""
                SELECT u FROM Usuario u
                WHERE u.endereco.uf = :uf
                AND u.renda >= :renda
            """)
    Page<Usuario> buscarUsuarioPorUfERenda(String uf, BigDecimal renda, Pageable pageable);

    @Query("""
                SELECT u FROM Usuario u
            """)
    Page<Usuario> buscarTodos(Pageable pageable);

   @Query("""
               SELECT u FROM Usuario u
               WHERE u.renda >= :renda
           """)
    Page<Usuario> buscarUsuarioPorRenda(BigDecimal renda, Pageable pageable);

}
