package com.gumtree.example01;

import com.gumtree.example03.model.Advert;

import java.util.List;

public interface AdvertService {

    List<Advert> getAdverts(long sellerId);

    boolean canRepostForFree(long advertId) throws RuntimeException;

    void repostForFree(long advertId) throws RuntimeException;
}
