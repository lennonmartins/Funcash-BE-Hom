package com.example.demo.servicos;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controladores.AlterarStatusTarefaDTO;
import com.example.demo.dto.TarefaRequestDTO;
import com.example.demo.dto.TarefaResponseDTO;
import com.example.demo.mappers.TarefaMapper;
import com.example.demo.modelos.Tarefa;
import com.example.demo.modelos.usuario.UserRole;
import com.example.demo.repositorios.TarefaRepository;
import com.example.demo.utils.DataConvert;

import jakarta.validation.Valid;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaMapper tarefaMapper;
    Tarefa tarefaAlterada = new Tarefa();

    public TarefaResponseDTO cadastrar(@Valid TarefaRequestDTO tarefaRequestDTO) {
        Tarefa tarefa = tarefaMapper.tarefaRequestparaTarefa(tarefaRequestDTO);
        tarefaRepository.save(tarefa);
        return tarefaMapper.tarefaParaTarefaResponseDTO(tarefa);
    }

    public void deletar(Long id) {
        tarefaRepository.deleteById(id);
    }

    public Collection<TarefaResponseDTO> buscarTodas() {
        return tarefaMapper.tarefasParaTarefasResponsesDtos((Collection<Tarefa>) tarefaRepository.findAll());
    }

    public TarefaResponseDTO buscarPorId(Long id) {
        return tarefaMapper.tarefaParaTarefaResponseDTO(buscarTarefaPeloId(id));
    }

    public TarefaResponseDTO alterar(@Valid TarefaRequestDTO tarefaRequestDTO, Long id) {
        Tarefa tarefaParaAlterar = buscarTarefaPeloId(id);
        tarefaParaAlterar.setDataLimite(DataConvert.obterData(tarefaRequestDTO.getDataLimite()));
        tarefaParaAlterar.setHoraLimite(DataConvert.obterHoraLimiteCompleta(tarefaRequestDTO.getDataLimite(),
                tarefaRequestDTO.getHoraLimite()));
        tarefaParaAlterar.setTitulo(tarefaRequestDTO.getNome());
        tarefaParaAlterar.setValor(tarefaRequestDTO.getValor());

        tarefaRepository.save(tarefaParaAlterar);

        return tarefaMapper.tarefaParaTarefaResponseDTO(tarefaParaAlterar);
    }

    public Collection<TarefaResponseDTO> buscarTarefasPelaCrianca(long id) {
        return tarefaMapper.tarefasParaTarefasResponsesDtos((Collection<Tarefa>) tarefaRepository.findAllByCrianca(id));
    }

    public TarefaResponseDTO alterarStatusTarefa(@Valid AlterarStatusTarefaDTO alteraStatusTarefaRequestDTO,
            Long idTarefa)
            throws Exception {
        var tarefaParaAlterar = buscarTarefaPeloId(idTarefa);
        var novoStatusDaTarefa = alteraStatusTarefaRequestDTO.getTarefaRequestDTO().getStatus();
        var usuarioDarequisicao = alteraStatusTarefaRequestDTO.getUsuarioRequestDTO().getRole();

        if (usuarioDarequisicao.equals(UserRole.CRIANCA)) {
            var criancaDaTarefa = tarefaParaAlterar.getCrianca();
            tarefaAlterada = criancaDaTarefa.realizarTarefa(tarefaParaAlterar, novoStatusDaTarefa);
            tarefaRepository.save(tarefaAlterada);
        } else {
            var responsavelDaTarefa = tarefaParaAlterar.getCrianca().getResponsavel();
            tarefaAlterada = responsavelDaTarefa.concluirTarefa(tarefaParaAlterar, novoStatusDaTarefa);
            tarefaRepository.save(tarefaAlterada);
        }

        return tarefaMapper.tarefaParaTarefaResponseDTO(tarefaAlterada);
    }

    private Tarefa buscarTarefaPeloId(Long id) {
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);
        if (tarefaOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        return tarefaOptional.get();
    }
}
