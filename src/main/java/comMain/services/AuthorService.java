package comMain.services;

import comMain.entities.AuthorEntity;
import comMain.repositories.AuthorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Integer save(AuthorEntity vO) {
        AuthorEntity bean = new AuthorEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = authorRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        authorRepository.deleteById(id);
    }

    public void update(Integer id, AuthorEntity vO) {
        AuthorEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        authorRepository.save(bean);
    }

    public AuthorEntity getById(Integer id) {
        AuthorEntity original = requireOne(id);
        return toDTO(original);
    }


    private AuthorEntity toDTO(AuthorEntity original) {
        AuthorEntity bean = new AuthorEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private AuthorEntity requireOne(Integer id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<AuthorEntity> getAllAuthors(){
        return authorRepository.findAll();
    }
}
