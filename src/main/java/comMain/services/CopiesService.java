package comMain.services;

import comMain.entities.CopiesEntity;
import comMain.repositories.CopiesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CopiesService {

    @Autowired
    private CopiesRepository copiesRepository;

    public Integer save(CopiesEntity vO) {
        CopiesEntity bean = new CopiesEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = copiesRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        copiesRepository.deleteById(id);
    }

    public void update(Integer id, CopiesEntity vO) {
        CopiesEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        copiesRepository.save(bean);
    }

    public CopiesEntity getById(Integer id) {
        CopiesEntity original = requireOne(id);
        return toDTO(original);
    }


    private CopiesEntity toDTO(CopiesEntity original) {
        CopiesEntity bean = new CopiesEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private CopiesEntity requireOne(Integer id) {
        return copiesRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
