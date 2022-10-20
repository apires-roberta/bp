package com.bp.feedbcd.controle;

import com.bp.feedbcd.data.dto.feed.CreateFeedDto;
import com.bp.feedbcd.data.dto.feed.ReadFeedDto;
import com.bp.feedbcd.services.feed.IFeedService;
import feign.FeignException;
import feign.RetryableException;
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
    public ResponseEntity<List<ReadFeedDto>> getFeeds() {
        List<ReadFeedDto> feeds = _feedService.getFeeds();
        return feeds != null ? status(200).body(feeds) : status(204).build();
    }

    @PostMapping()
    public ResponseEntity<Long> createFeed(@RequestBody @Valid CreateFeedDto novoFeed) {
        if (novoFeed == null)
            return status(400).build();
        Long feedId = _feedService.createFeed(novoFeed);
        return feedId != null ? status(201).body(feedId) : status(404).build();
    }

    /*@PatchMapping(value = "/{idFeed}", consumes = "image/jpeg")
    public  ResponseEntity<Void> atualizarFotosFeed(@RequestBody URL fotoFeed, @PathVariable long idFeed){
        if (fotoFeed == null)
            return status(400).build();

        boolean atualizado = _feedService.atualizarFotosFeed(fotoFeed, idFeed);
        return atualizado ? status(204).build() : status(404).build();
    }*/

    @DeleteMapping("/{idFeed}")
    public ResponseEntity<Void> deleteFeed(@PathVariable long idFeed){
        boolean deletado = _feedService.deleteFeed(idFeed);

        return deletado ? status(204).build() : status(404).build();
    }

    @GetMapping("/ong/{idOng}")
    public ResponseEntity<List<ReadFeedDto>> getFeedsByIdOng(@PathVariable Integer idOng){
        if (idOng == null || idOng <= 0)
            return status(400).build();

        try {
            List<ReadFeedDto> feeds = _feedService.getFeedsByIdOng(idOng);

            return feeds.isEmpty() ? status(204).build() : status(200).body(feeds);
        }catch (FeignException.NotFound ex){
            return status(404).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/doador/{idDoador}")
    public ResponseEntity<List<ReadFeedDto>> getFeedsDoador(@PathVariable Integer idDoador){
        if (idDoador == null || idDoador <= 0)
            return status(400).build();

        try {
            List<ReadFeedDto> feeds = _feedService.getFeedsByIdDoador(idDoador);

            return feeds.isEmpty() ? status(204).build() : status(200).body(feeds);
        }catch (FeignException.NotFound ex){
            return status(404).build();
        }catch (RetryableException ex){
            return status(503).build();
        }catch (Exception ex){
            throw ex;
        }
    }
}