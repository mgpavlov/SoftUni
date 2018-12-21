package usersystemapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usersystemapp.repository.TownRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(final TownRepository townRepository) {
        this.townRepository = townRepository;
    }

}
