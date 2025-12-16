package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.dao.ArtistDao;
import com.alextheunknowable.musictagger.exception.NotFoundException;
import com.alextheunknowable.musictagger.model.Artist;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService{
    private final ArtistDao artistDao;
    private final AuthService authService;
    public ArtistServiceImpl(ArtistDao artistDao, AuthService authService) {
        this.artistDao = artistDao;
        this.authService = authService;
    }

    @Override
    public List<Artist> getArtists(String name) {
        if (name != null && !name.isBlank()) return artistDao.getArtistsByName(name);
        return artistDao.getArtists();
    }

    @Override
    public Artist getArtistById(int id) {
        return artistDao.getArtistById(id);
    }

    @Override
    public Artist createArtist(Artist artist, Principal principal) {
        if (artist.getName() == null || artist.getName().isBlank()) {
            throw new IllegalArgumentException("Artist must have a name");
        }
        artist.setUploaderId(authService.getUserIdFromPrincipal(principal));
        return artistDao.createArtist(artist);
    }

    @Override
    public Artist createArtist(String name, int userId) {
        //note: make this transactional if i ever add side effects
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Artist must have a name");
        }
        Artist newArtist = new Artist();
        newArtist.setName(name);
        newArtist.setUploaderId(userId);
        return artistDao.createArtist(newArtist);
    }

    @Override
    public Artist updateArtist(int id, Artist updatedArtist, Principal principal) {
        Artist existingArtist = artistDao.getArtistById(id);

        if (existingArtist == null) throw new NotFoundException("Artist not found.");
        authService.assertUserCanModify(principal, existingArtist.getUploaderId(),
                "User does not have permissions to modify this artist.");

        updatedArtist.setId(id);
        updatedArtist.setUploaderId(existingArtist.getUploaderId());
        return artistDao.updateArtist(updatedArtist);
    }

    @Override
    public void deleteArtist(int id, Principal principal) {
        Artist artist = artistDao.getArtistById(id);

        if (artist == null) throw new NotFoundException("Artist with id " + id + " not found.");
        authService.assertUserCanModify(principal, artist.getUploaderId(),
                "User does not have permissions to delete this artist.");

        int numberOfRowsDeleted = artistDao.deleteArtistById(id);

        if (numberOfRowsDeleted == 0) throw new NotFoundException("Artist with id " + id + " not found.");
    }

}
