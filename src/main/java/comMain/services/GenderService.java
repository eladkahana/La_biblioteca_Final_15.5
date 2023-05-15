package comMain.services;

import comMain.entities.GenderEntity;
import comMain.repositories.GenderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class GenderService {

    @Autowired
    private GenderRepository genderRepository;

    public Integer save(GenderEntity vO) {
        GenderEntity bean = new GenderEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = genderRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        genderRepository.deleteById(id);
    }

    public void update(Integer id, GenderEntity vO) {
        GenderEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        genderRepository.save(bean);
    }

    public GenderEntity getById(Integer id) {
        GenderEntity original = requireOne(id);
        return toDTO(original);
    }


    private GenderEntity toDTO(GenderEntity original) {
        GenderEntity bean = new GenderEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private GenderEntity requireOne(Integer id) {
        return genderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
