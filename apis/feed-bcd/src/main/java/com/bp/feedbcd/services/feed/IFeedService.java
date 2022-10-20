package com.bp.feedbcd.services.feed;

import com.bp.feedbcd.data.dto.feed.CreateFeedDto;
import com.bp.feedbcd.data.dto.feed.ReadFeedDto;

import java.net.URL;
import java.util.List;
public interface IFeedService {
    List<ReadFeedDto> getFeeds();

    Long createFeed(CreateFeedDto novoFeed);

    boolean atualizarFotosFeed(URL fotoFeed, Long idFeed);

    boolean deleteFeed(long idFeed);

    List<ReadFeedDto> getFeedsByIdOng(Integer idOng);

    List<ReadFeedDto> getFeedsByIdDoador(Integer idDoador);
}