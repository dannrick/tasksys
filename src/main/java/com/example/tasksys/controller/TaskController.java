package com.example.tasksys.controller;

import com.example.tasksys.model.Tarefa;
import com.example.tasksys.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class TaskController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping("/")
    public String index(@RequestParam(value = "status", required = false) String status, Model model) {
        List<Tarefa> tarefasFiltradas;
        
        if (status != null && !status.isEmpty()) {
            tarefasFiltradas = tarefaRepository.findByStatus(status);
        } else {
            tarefasFiltradas = tarefaRepository.findAll();
        }

        model.addAttribute("tarefas", tarefasFiltradas);
        return "index";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarTarefa(@RequestParam String titulo, @RequestParam String descricao) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setStatus("Sem status definido"); // Define o status padrão
        tarefaRepository.save(tarefa);
        return "redirect:/";
    }

    @GetMapping("/detalhe")
    public String detalhe(@RequestParam Long id, Model model) {
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        model.addAttribute("tarefa", tarefa);
        return "detalhe";
    }

    @PostMapping("/atualizar-status")
    public String atualizarStatus(@RequestParam Long id, @RequestParam String status) {
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if (tarefa != null) {
            tarefa.setStatus(status);
            tarefaRepository.save(tarefa);
        }
        return "redirect:/";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam Long id, Model model) {
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        model.addAttribute("tarefa", tarefa);
        return "editar";
    }

    @PutMapping("/atualizar-tarefa")
    public String atualizarTarefa(@RequestParam Long id, @RequestParam String titulo, @RequestParam String descricao) {
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if (tarefa != null) {
            tarefa.setTitulo(titulo);
            tarefa.setDescricao(descricao);
            tarefaRepository.save(tarefa);
        }
        return "redirect:/";
    }

    @DeleteMapping("/excluir-tarefa")
    public String excluirTarefa(@RequestParam Long id) {
        tarefaRepository.deleteById(id);
        return "redirect:/";
    }
}
