package api.feed.apifeed.controle;

import api.feed.apifeed.entidade.Feed;
import api.feed.apifeed.repositorio.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private FeedRepository repository;

    @GetMapping
    public ResponseEntity<List<Feed>> getFeed() {
        return status(200).body(repository.findAllByOrderByDataPublicacaoDesc());
    }
    // para usar esse post limpar o campo de byte[]
    @PostMapping()
    public ResponseEntity postFeed(
            @RequestBody @Valid Feed novoFeed) {
        repository.save(novoFeed);
        return status(201).build();
    }

    @PatchMapping(consumes = "image/jpg")
    public  ResponseEntity atualizarFotoFeed(@RequestBody byte[] fotoFeed, long codigo){
        Feed novoFeed = repository.findByCodigo(codigo);

        if (novoFeed == null){
            return status(404).build();
        }

        novoFeed.setFotoFeed(fotoFeed);

        repository.save(novoFeed);

        return status(200).build();
    }

}