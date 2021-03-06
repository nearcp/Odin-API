package br.com.gerenciamento.sistema.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gerenciamento.sistema.model.VendaModel;
import br.com.gerenciamento.sistema.service.VendaService;

@RestController
@RequestMapping(value = "/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public ResponseEntity<List<VendaModel>>findAll(){
        return ResponseEntity.ok().body(vendaService.findAll());
    }

    @PostMapping
    public ResponseEntity<VendaModel>insert(@RequestParam("itensVenda")String listItemVenda) throws Exception {
        VendaModel venda = vendaService.realizarVenda(listItemVenda);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(venda.getId())
                .toUri();
        return ResponseEntity.created(uri).body(venda);
    }
}
