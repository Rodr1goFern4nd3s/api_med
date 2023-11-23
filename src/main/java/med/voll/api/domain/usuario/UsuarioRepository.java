package med.voll.api.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //<Quem é a entidade?, Qual é o tipo da chave primária>
    //No geral, cada entidade jpa, vai ter uma interface repository(que faz acesso ao BD aquela tabela da entidade específica)
    UserDetails findByLogin(String login);
}
