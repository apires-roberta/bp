package betterplace.betterplacebcd.controle;

//import api.feed.apifeed.dto.feed.CreateFeedDto;
//import api.feed.apifeed.entidade.Feed;
//import api.feed.apifeed.entidade.Ong;
//import api.feed.apifeed.repositorio.FeedRepository;
//import api.feed.apifeed.services.OngService;
import betterplace.betterplacebcd.data.dto.feed.CreateFeedDto;
import betterplace.betterplacebcd.entidade.Feed;
import betterplace.betterplacebcd.entidade.Ong;
import betterplace.betterplacebcd.repositorio.FeedRepository;
import betterplace.betterplacebcd.repositorio.OngRepository;
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
    @Autowired
    private OngRepository ongRepository; //OngService ongService;
    @GetMapping
    public ResponseEntity<List<Feed>> getFeeds() {
        List<Feed> feeds = repository.findAllByOrderByDataPublicacaoDesc();
        if (feeds.isEmpty())
            return status(204).build();

        return status(200).body(feeds);
    }

    @PostMapping()
    public ResponseEntity<Long> createFeed(@RequestBody @Valid CreateFeedDto novoFeed) {
        Feed feed = new Feed(novoFeed.getFkOng(), novoFeed.getDataPublicacao(), novoFeed.getDescricao());
        if(feed.getFkOng() == null || feed.getDataPublicacao() == null || feed.getDescricao() == null)
            return status(400).build();

        repository.save(feed);
        return status(201).body(feed.getCodigo());
    }

    @PatchMapping(value = "/{idFeed}", consumes = "image/jpeg")
    public  ResponseEntity<Void> atualizarFotosFeed(@RequestBody byte[] fotoFeed, @PathVariable long idFeed){
        Feed novoFeed = repository.findByCodigo(idFeed);

        if (novoFeed == null)
            return status(404).build();

        novoFeed.setFotoFeed(fotoFeed);

        Ong ong = ongRepository.getById(novoFeed.getFkOng());
        novoFeed.setFotoPerfilOng(ong.getFotoPerfil());
        //fazer também a classe seguidores
        repository.save(novoFeed);
        return status(200).build();
    }

    @DeleteMapping("/{idFeed}")
    public ResponseEntity<Void> deleteFeed(@PathVariable long idFeed){
        if (!repository.existsById(idFeed))
            return status(404).build();

        repository.deleteById(idFeed);
        return status(200).build();
    }
}