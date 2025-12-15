package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.dao.ArtistDao;
import com.alextheunknowable.musictagger.model.Artist;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService{
    private final ArtistDao artistDao;
    public ArtistServiceImpl(ArtistDao artistDao) {
        this.artistDao = artistDao;
    }

    @Override
    public List<Artist> getArtists(String name) {

        List<Artist> artists = artistDao.getArtists();

        // name filter
        if (name != null && !name.isBlank()) {
            artists = artists.stream()
                    .filter(artist -> artist.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }

        return artists;
    }

    @Override
    public Artist getArtistById(int id) {
        return artistDao.getArtistById(id);
    }

    @Override
    public Artist createArtist(Artist artist) {
        return artistDao.createArtist(artist);
    }

    @Override
    public Artist createArtist(String name, int userId) {
        Artist newArtist = new Artist();
        newArtist.setName(name);
        newArtist.setUploadedByUserId(userId);
        return artistDao.createArtist(newArtist);
    }

    @Override
    public Artist updateArtist(int id, Artist artist, Principal principal) {
        artist.setId(id);
        return artistDao.updateArtist(artist);
        //requires user to be admin or uploader
    }

    @Override
    public void deleteArtist(int id) {
        int numberOfRowsDeleted = artistDao.deleteArtistById(id);
        //requires user to be admin or uploader
    }

}
