package com.bp.feedbcd.controle;

import com.bp.feedbcd.data.dto.feed.CreateFeedDto;
import com.bp.feedbcd.entidade.Feed;
import com.bp.feedbcd.services.feed.IFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;
@Service
@RestController
@RequestMapping("/feed")
@CrossOrigin
public class FeedController {
    @Autowired
    private IFeedService _feedService;
    @GetMapping
    public ResponseEntity<List<Feed>> getFeeds() {
        List<Feed> feeds = _feedService.getFeeds();
        return feeds != null ? status(200).body(feeds) : status(204).build();
    }

    @PostMapping()
    public ResponseEntity<Long> createFeed(@RequestBody @Valid CreateFeedDto novoFeed) {
        if (novoFeed == null)
            return status(400).build();
        Long feedId = _feedService.createFeed(novoFeed);
        return feedId != null ? status(201).body(feedId) : status(404).build();
    }

    @PatchMapping(value = "/{idFeed}", consumes = "image/jpeg")
    public  ResponseEntity<Void> atualizarFotosFeed(@RequestBody byte[] fotoFeed, @PathVariable long idFeed){
        if (fotoFeed == null)
            return status(400).build();

        boolean atualizado = _feedService.atualizarFotosFeed(fotoFeed, idFeed);
        return atualizado ? status(204).build() : status(404).build();
    }

    @DeleteMapping("/{idFeed}")
    public ResponseEntity<Void> deleteFeed(@PathVariable long idFeed){
        boolean deletado = _feedService.deleteFeed(idFeed);

        return deletado ? status(204).build() : status(404).build();
    }
}