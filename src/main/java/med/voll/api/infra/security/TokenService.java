package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    /*
    Classe de serviço relacionada com token, irá fazer a geração, validação do token, tudo que for relacionada com token!
    É recomendado configurar uma data de expiração para tokens!
     */

    public String gerarToken(Usuario usuario) {
        try {
            var algoritimo = Algorithm.HMAC256("12345678");
            return JWT.create()
                    .withIssuer("API Voll.med") //Para identificar a ferramenta dona, responsável pela geração do token
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo); // Método para fazer a assinatura e recebe o algoritmo
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    private Instant dataExpiracao() {
        //Será dado 2 horas para expiração do token
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")); //Fuso horário do Brasil
    }
}

//.withSubject(usuario.getLogin) -> Quem é o objeto dono(pessoa) desse token
//withClaim("id", usuario.getId()) -> podemos passar quaisquer informações que desejarmos para guardar dentro do token. Um withClaim para cada item
