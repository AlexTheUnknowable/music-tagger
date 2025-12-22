package com.alextheunknowable.musictagger.service;

import com.alextheunknowable.musictagger.dao.AliasDao;
import com.alextheunknowable.musictagger.exception.NotFoundException;
import com.alextheunknowable.musictagger.model.Alias;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Service
public class AliasServiceImpl implements AliasService{
    private final AliasDao aliasDao;
    private final AuthService authService;
    public AliasServiceImpl(AliasDao aliasDao, AuthService authService) {
        this.aliasDao = aliasDao;
        this.authService = authService;
    }

    @Override
    public List<Alias> getAliases() {
        return aliasDao.getAliases();
    }

    @Override
    public Alias getAliasById(int id) {
        return aliasDao.getAliasById(id);
    }

    @Override
    public Map<Integer, List<Alias>> getAliasesByTrackIds(List<Integer> trackIds) {
        return aliasDao.getAliasesByTrackIds(trackIds);
    }

    @Override
    public Alias createAlias(Alias alias, Principal principal) {
        if (alias.getAlias() == null || alias.getAlias().isBlank()) {
            throw new IllegalArgumentException("Alias must not be blank");
        }
        alias.setUploaderId(authService.getUserIdFromPrincipal(principal));
        return aliasDao.createAlias(alias);
    }

    @Override
    public Alias updateAlias(int id, Alias updatedAlias, Principal principal) {
        Alias existingAlias = aliasDao.getAliasById(id);

        if (existingAlias == null) throw new NotFoundException("Alias not found.");
        authService.assertUserCanModify(principal, existingAlias.getUploaderId(),
                "User does not have permissions to modify this alias.");

        updatedAlias.setId(id);
        updatedAlias.setUploaderId(existingAlias.getUploaderId());
        return aliasDao.updateAlias(updatedAlias);
    }

    @Override
    public void deleteAlias(int id, Principal principal) {
        Alias alias = aliasDao.getAliasById(id);

        if (alias == null) throw new NotFoundException("Alias with id " + id + " not found.");
        authService.assertUserCanModify(principal, alias.getUploaderId(),
                "User does not have permissions to delete this alias.");

        int numberOfRowsDeleted = aliasDao.deleteAliasById(id);

        if (numberOfRowsDeleted == 0) throw new NotFoundException("Alias with id " + id + " not found.");
    }

}
