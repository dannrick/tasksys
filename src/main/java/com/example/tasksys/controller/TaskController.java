package com.example.tasksys.controller;

import com.example.tasksys.model.Tarefa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TaskController {
    private List<Tarefa> tarefas = new ArrayList<>();

    @GetMapping("/")
    public String index(@RequestParam(value = "status", required = false) String status, Model model) {
        List<Tarefa> tarefasFiltradas = new ArrayList<>(tarefas);
        
        if (status != null && !status.isEmpty()) {
            tarefasFiltradas = tarefas.stream()
                .filter(tarefa -> tarefa.getStatus().equals(status))
                .collect(Collectors.toList());
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
        tarefa.setId((long) (tarefas.size() + 1));
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setStatus("Sem status definido"); // Define o status padrão
        tarefas.add(tarefa);
        return "redirect:/";
    }


    @GetMapping("/detalhe")
    public String detalhe(@RequestParam Long id, Model model) {
        Tarefa tarefa = tarefas.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
        model.addAttribute("tarefa", tarefa);
        return "detalhe";
    }
    
    @PostMapping("/atualizar-status")
    public String atualizarStatus(@RequestParam Long id, @RequestParam String status) {
        Tarefa tarefa = tarefas.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
        if (tarefa != null) {
            tarefa.setStatus(status);
        }
        return "redirect:/";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam Long id, Model model) {
        Tarefa tarefa = tarefas.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
        model.addAttribute("tarefa", tarefa);
        return "editar";
    }

    @PostMapping("/atualizar-tarefa")
    public String atualizarTarefa(@RequestParam Long id, @RequestParam String titulo, @RequestParam String descricao) {
        Tarefa tarefa = tarefas.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
        if (tarefa != null) {
            tarefa.setTitulo(titulo);
            tarefa.setDescricao(descricao);
        }
        return "redirect:/";
    }
    

}
