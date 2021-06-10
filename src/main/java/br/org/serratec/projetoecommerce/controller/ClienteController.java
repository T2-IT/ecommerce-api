package br.org.serratec.projetoecommerce.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.projetoecommerce.model.Cliente;
import br.org.serratec.projetoecommerce.repository.ClienteRepository;
import br.org.serratec.projetoecommerce.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Insere dados de um cliente", notes = "Inserir cliente")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Cliente cadastrado com sucesso"),
            @ApiResponse(code = 401, message = "Erro de Autenticação"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 505, message = "Quando ocorre uma exceçãooo") })
    public Cliente inserir(@Valid @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PostMapping("/inserirTodos")
    @ApiOperation(value = "Insere dados de vários clientes", notes = "Inserir clientes")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Clientes cadastrados com sucesso"),
            @ApiResponse(code = 401, message = "Erro de Autenticação"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 505, message = "Quando ocorre uma exceção") })
    @ResponseStatus(HttpStatus.CREATED)
    public List<Cliente> inserirTodos(List<Cliente> clientes) {
        return clienteRepository.saveAll(clientes);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Retorna um cliente", notes = "Cliente")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um cliente"),
            @ApiResponse(code = 401, message = "Erro de Autenticação"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 505, message = "Quando ocorre uma exceção") })
    public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @ApiOperation(value = "Lista todos os clientes ", notes = "Listagem de  clientes")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os  clientes"),
            @ApiResponse(code = 401, message = "Erro de Autenticação"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 505, message = "Quando ocorre uma exceção") })
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Atualiza dados de um  cliente", notes = "Atualizar  cliente")
    @ApiResponses(value = { @ApiResponse(code = 200, message = " cliente atualizado"),
            @ApiResponse(code = 401, message = "Erro de Autenticação"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 505, message = "Quando ocorre uma exceção") })
    public ResponseEntity<Cliente> atualizar(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(id);
        cliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Remove um  cliente", notes = "Remover  cliente")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Dependente removido"),
            @ApiResponse(code = 401, message = "Erro de Autenticação"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 505, message = "Quando ocorre uma exceção") })
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}