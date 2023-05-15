package comMain.services;

import comMain.entities.ShelfmarkEntity;
import comMain.repositories.ShelfmarkRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ShelfmarkService {

    @Autowired
    private ShelfmarkRepository shelfmarkRepository;

    public Integer save(ShelfmarkEntity vO) {
        ShelfmarkEntity bean = new ShelfmarkEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = shelfmarkRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        shelfmarkRepository.deleteById(id);
    }

    public void update(Integer id, ShelfmarkEntity vO) {
        ShelfmarkEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        shelfmarkRepository.save(bean);
    }

    public ShelfmarkEntity getById(Integer id) {
        ShelfmarkEntity original = requireOne(id);
        return toDTO(original);
    }


    private ShelfmarkEntity toDTO(ShelfmarkEntity original) {
        ShelfmarkEntity bean = new ShelfmarkEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private ShelfmarkEntity requireOne(Integer id) {
        return shelfmarkRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
