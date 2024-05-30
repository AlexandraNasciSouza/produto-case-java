package org.example.Produto.ProdutoController;

import org.example.Produto.ProdutoRepository.ProdutoRepository;
import org.example.Produto.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> getAllProdutos(){
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoByID(@PathVariable Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Produto createdProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produtoDetails){
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
           Produto produtoAtualizado = produto.get();
           produtoAtualizado.setNome(produtoDetails.getNome());
           produtoAtualizado.setDescricao(produtoDetails.getDescricao());
           produtoAtualizado.setPreco(produtoDetails.getPreco());
           return ResponseEntity.ok(produtoRepository.save(produtoAtualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        if (produtoRepository.existsById(id)){
            produtoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
