package com.bp.feedbcd.services.feed;

import com.bp.feedbcd.data.dto.feed.CreateFeedDto;
import com.bp.feedbcd.data.dto.feed.ReadFeedDto;

import java.util.List;
public interface IFeedService {
    List<ReadFeedDto> getFeeds();

    Long createFeed(CreateFeedDto novoFeed);

    boolean atualizarFotosFeed(byte[] fotoFeed, Long idFeed);

    boolean deleteFeed(long idFeed);

    List<ReadFeedDto> getFeedsByIdOng(Integer idOng);
}