package comMain.services;

import comMain.entities.RanksEntity;
import comMain.repositories.RanksRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RanksService {

    @Autowired
    private RanksRepository ranksRepository;

    public Integer save(RanksEntity vO) {
        RanksEntity bean = new RanksEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = ranksRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        ranksRepository.deleteById(id);
    }

    public void update(Integer id, RanksEntity vO) {
        RanksEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ranksRepository.save(bean);
    }

    public RanksEntity getById(Integer id) {
        RanksEntity original = requireOne(id);
        return toDTO(original);
    }


    private RanksEntity toDTO(RanksEntity original) {
        RanksEntity bean = new RanksEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private RanksEntity requireOne(Integer id) {
        return ranksRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
