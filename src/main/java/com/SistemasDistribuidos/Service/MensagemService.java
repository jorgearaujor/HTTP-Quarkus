package com.SistemasDistribuidos.Service;

import com.SistemasDistribuidos.Models.Mensagem;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MensagemService {

    private List<Mensagem> mensagens = new ArrayList<>();
    private Long contador = 1L;

    public List<Mensagem> listar() {
        return mensagens;
    }

    public Optional<Mensagem> buscarPorId(Long id) {
        return mensagens.stream()
                .filter(m -> m.id.equals(id))
                .findFirst();
    }

    public Mensagem adicionar(Mensagem mensagem) {
        Long id = contador++;
        LocalDateTime timestamp = LocalDateTime.now();

        Mensagem novaMensagem = new Mensagem(id, mensagem.remetente, mensagem.conteudo, timestamp);
        mensagens.add(novaMensagem);
        return novaMensagem;
    }

    public boolean remover(Long id) {
        return mensagens.removeIf(m -> m.id.equals(id));
    }
}
