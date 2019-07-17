package br.com.gerenciamento.sistema.controller;

import br.com.gerenciamento.sistema.model.PessoaModel;
import br.com.gerenciamento.sistema.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PessoaModel>>findAll(){
        return ResponseEntity.ok().body(pService.findAll());
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity <Void> delete(@PathVariable("id")Long id){ pService.delete(id); return ResponseEntity.noContent().build();}

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PessoaModel> insert(@Valid @RequestBody PessoaModel p){
        pService.save(p);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PessoaModel> findById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(pService.findById(id));
    }

    @RequestMapping(value = "/status/inativos", method = RequestMethod.GET)
    public ResponseEntity<List<PessoaModel>> findInativo(){
        return ResponseEntity.ok().body(pService.findInativo());
    }
}