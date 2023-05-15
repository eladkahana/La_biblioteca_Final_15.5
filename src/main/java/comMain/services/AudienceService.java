package comMain.services;


import comMain.entities.AudienceEntity;
import comMain.repositories.AudienceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AudienceService {

    @Autowired
    private AudienceRepository audienceRepository;

    public Integer save(AudienceEntity vO) {
        AudienceEntity bean = new AudienceEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = audienceRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        audienceRepository.deleteById(id);
    }

    public void update(Integer id, AudienceEntity vO) {
        AudienceEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        audienceRepository.save(bean);
    }

    public AudienceEntity getById(Integer id) {
        AudienceEntity original = requireOne(id);
        return toDTO(original);
    }


    private AudienceEntity toDTO(AudienceEntity original) {
        AudienceEntity bean = new AudienceEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private AudienceEntity requireOne(Integer id) {
        return audienceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<AudienceEntity> getAllAudiences(){
        return audienceRepository.findAll();
    }
}
