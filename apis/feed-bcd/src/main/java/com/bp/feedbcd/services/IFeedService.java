package com.bp.feedbcd.services;

import com.bp.feedbcd.data.dto.feed.CreateFeedDto;
import com.bp.feedbcd.entidade.Feed;

import java.util.List;
public interface IFeedService {
    List<Feed> getFeeds();

    Long createFeed(CreateFeedDto novoFeed);

    boolean atualizarFotosFeed(byte[] fotoFeed, Long idFeed);

    boolean deleteFeed(long idFeed);
}