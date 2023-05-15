package comMain.services;

import comMain.entities.AudienceVsBookEntity;
import comMain.repositories.AudienceVSBookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AudienceVSBookService {

    @Autowired
    private AudienceVSBookRepository audienceVSBookRepository;

    public Integer save(AudienceVsBookEntity vO) {
        AudienceVsBookEntity bean = new AudienceVsBookEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = audienceVSBookRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        audienceVSBookRepository.deleteById(id);
    }

    public void update(Integer id, AudienceVsBookEntity vO) {
        AudienceVsBookEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        audienceVSBookRepository.save(bean);
    }

    public AudienceVsBookEntity getById(Integer id) {
        AudienceVsBookEntity original = requireOne(id);
        return toDTO(original);
    }


    private AudienceVsBookEntity toDTO(AudienceVsBookEntity original) {
        AudienceVsBookEntity bean = new AudienceVsBookEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private AudienceVsBookEntity requireOne(Integer id) {
        return audienceVSBookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
