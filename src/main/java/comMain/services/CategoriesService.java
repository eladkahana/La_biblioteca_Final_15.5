package comMain.services;

import comMain.entities.CategoriesEntity;
import comMain.repositories.CategoriesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    public Integer save(CategoriesEntity vO) {
        CategoriesEntity bean = new CategoriesEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = categoriesRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        categoriesRepository.deleteById(id);
    }

    public void update(Integer id, CategoriesEntity vO) {
        CategoriesEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        categoriesRepository.save(bean);
    }

    public CategoriesEntity getById(Integer id) {
        CategoriesEntity original = requireOne(id);
        return toDTO(original);
    }


    private CategoriesEntity toDTO(CategoriesEntity original) {
        CategoriesEntity bean = new CategoriesEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private CategoriesEntity requireOne(Integer id) {
        return categoriesRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<CategoriesEntity> getAllCategories(){
        return categoriesRepository.findAll();
    }
}
