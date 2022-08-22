package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.data.dto.feed.CreateFeedDto;
import betterplace.betterplacebcd.entidade.Feed;
import betterplace.betterplacebcd.entidade.Ong;
import betterplace.betterplacebcd.repositorio.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
        Optional<Ong> ong = ongRepository.findByCod(novoFeed.getOng().getCod());
        if (ong.isEmpty())
            return status(404).build();

        Feed feed = new Feed(ong.get(), novoFeed.getDataPublicacao(), novoFeed.getDescricao());
        repository.save(feed);
        return status(201).body(feed.getCodigo());
    }

    @PatchMapping(value = "/{idFeed}", consumes = "image/jpeg")
    public  ResponseEntity<Void> atualizarFotosFeed(@RequestBody byte[] fotoFeed, @PathVariable long idFeed){
        Feed novoFeed = repository.findByCodigo(idFeed);

        if (novoFeed == null)
            return status(404).build();

        novoFeed.setFotoFeed(fotoFeed);

        Optional<Ong> ong = ongRepository.findByCod(novoFeed.getOng().getCod());
        if (ong.isEmpty())
            return status(404).build();

        novoFeed.setFotoPerfilOng(ong.get().getFotoPerfil());
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