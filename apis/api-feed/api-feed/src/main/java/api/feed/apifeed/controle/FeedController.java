package api.feed.apifeed.controle;

import api.feed.apifeed.dto.feed.CreateFeedDto;
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
    public ResponseEntity<List<Feed>> getFeeds() {
        List<Feed> feeds = repository.findAllByOrderByDataPublicacaoDesc();
        if (feeds.isEmpty())
            return status(204).build();

        return status(200).body(feeds);
    }
    // para usar esse post limpar o campo de byte[]
    @PostMapping()
    public ResponseEntity<Long> createFeed(@RequestBody @Valid CreateFeedDto novoFeed) {

        Feed feed = new Feed(novoFeed.getCodigo(), novoFeed.getDataPublicacao(), novoFeed.getDescricao());
        repository.save(feed);
        return status(201).body(novoFeed.getCodigo());
    }

    @PatchMapping(consumes = "image/jpg")
    public  ResponseEntity atualizarFotosFeed(@RequestBody byte[] fotoFeed, long codigo){
        Feed novoFeed = repository.findByCodigo(codigo);

        if (novoFeed == null)
            return status(404).build();


        novoFeed.setFotoFeed(fotoFeed);

        //Fazer ligação com a api que tem as infos da Ong pra colocar a foto dela, fazer tambem a classe seguidores
        repository.save(novoFeed);

        return status(200).build();
    }

}
