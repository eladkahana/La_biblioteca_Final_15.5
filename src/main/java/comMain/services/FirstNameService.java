package comMain.services;

import comMain.entities.FirstNameEntity;
import comMain.repositories.FirstNameRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FirstNameService {

    @Autowired
    private FirstNameRepository firstNameRepository;

    public Integer save(FirstNameEntity vO) {
        FirstNameEntity bean = new FirstNameEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = firstNameRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        firstNameRepository.deleteById(id);
    }

    public void update(Integer id, FirstNameEntity vO) {
        FirstNameEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        firstNameRepository.save(bean);
    }

    public FirstNameEntity getById(Integer id) {
        FirstNameEntity original = requireOne(id);
        return toDTO(original);
    }


    private FirstNameEntity toDTO(FirstNameEntity original) {
        FirstNameEntity bean = new FirstNameEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private FirstNameEntity requireOne(Integer id) {
        return firstNameRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<FirstNameEntity> getAllFirstName(){
        return firstNameRepository.findAll();
    }
}
