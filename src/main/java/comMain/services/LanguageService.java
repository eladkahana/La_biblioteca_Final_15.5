package comMain.services;

import comMain.entities.LanguageEntity;
import comMain.repositories.LanguageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public Integer save(LanguageEntity vO) {
        LanguageEntity bean = new LanguageEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = languageRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        languageRepository.deleteById(id);
    }

    public void update(Integer id, LanguageEntity vO) {
        LanguageEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        languageRepository.save(bean);
    }

    public LanguageEntity getById(Integer id) {
        LanguageEntity original = requireOne(id);
        return toDTO(original);
    }


    private LanguageEntity toDTO(LanguageEntity original) {
        LanguageEntity bean = new LanguageEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private LanguageEntity requireOne(Integer id) {
        return languageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }


    public List<LanguageEntity> getAllLanguages(){
        return languageRepository.findAll();
    }
}
