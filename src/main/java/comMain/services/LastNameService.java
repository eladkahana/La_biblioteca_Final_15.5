package comMain.services;

import comMain.entities.LastNameEntity;
import comMain.repositories.LastNameRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LastNameService {

    @Autowired
    private LastNameRepository lastNameRepository;

    public Integer save(LastNameEntity vO) {
        LastNameEntity bean = new LastNameEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = lastNameRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        lastNameRepository.deleteById(id);
    }

    public void update(Integer id, LastNameEntity vO) {
        LastNameEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        lastNameRepository.save(bean);
    }

    public LastNameEntity getById(Integer id) {
        LastNameEntity original = requireOne(id);
        return toDTO(original);
    }

    private LastNameEntity toDTO(LastNameEntity original) {
        LastNameEntity bean = new LastNameEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private LastNameEntity requireOne(Integer id) {
        return lastNameRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<LastNameEntity> getAllLastName(){
        return lastNameRepository.findAll();
    }
}
