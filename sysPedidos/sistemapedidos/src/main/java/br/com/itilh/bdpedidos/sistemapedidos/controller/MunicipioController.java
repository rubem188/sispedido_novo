package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.itilh.bdpedidos.sistemapedidos.model.Municipio;
import br.com.itilh.bdpedidos.sistemapedidos.repository.MunicipioRepository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class MunicipioController {

    private final MunicipioRepository repositorio;

    public MunicipioController (MunicipioRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/municipios")
    public List<Municipio> getMunicipios() {
        return (List<Municipio>) repositorio.findAll();
    }

    @GetMapping("/municipio/{id}")
    public Municipio getMunicipioPorID(@PathVariable BigInteger id) throws Exception {
        
        return repositorio.findById(id).orElseThrow(() -> new Exception("Id não encontrado"));
    }
    
    @PostMapping("/municipio")
    public Municipio postMunicipio(@RequestBody Municipio entity) throws Exception {
        try{
            return repositorio.save(entity);
        } catch (Exception e) {
            throw new Exception("Não foi possível criar o município" + e.getMessage());
        }
    }

    @PutMapping("municipio/{id}")
    public Municipio putMunicipio(@PathVariable BigInteger id, @RequestBody Municipio entity) throws Exception {
        try{
            return repositorio.save(entity);
        } catch (Exception e) {
            throw new Exception("Não foi possível alterar o município" + e.getMessage());
        }
    }

    @DeleteMapping("/municipio/{id}") 
    public String deleteMunicipio(@PathVariable BigInteger id) throws Exception {
        try {
            repositorio.deleteById(id);
            return "Excluído";
        } catch (Exception e) {
            throw new Exception("Não foi possível excluir o id informado." + e.getMessage());
        }

    }
}