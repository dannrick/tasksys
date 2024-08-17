package com.example.tasksys.service;


import com.example.tasksys.model.Tarefa;
import com.example.tasksys.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> findAll() {
        return tarefaRepository.findAll();
    }

    public void save(Tarefa tarefa) {
        tarefaRepository.save(tarefa);
    }
}
