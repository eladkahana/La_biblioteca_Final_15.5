package comMain.services;

import comMain.entities.PublisherEntity;
import comMain.repositories.PublisherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public Integer save(PublisherEntity vO) {
        PublisherEntity bean = new PublisherEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = publisherRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        publisherRepository.deleteById(id);
    }

    public void update(Integer id, PublisherEntity vO) {
        PublisherEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        publisherRepository.save(bean);
    }

    public PublisherEntity getById(Integer id) {
        PublisherEntity original = requireOne(id);
        return toDTO(original);
    }


    private PublisherEntity toDTO(PublisherEntity original) {
        PublisherEntity bean = new PublisherEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private PublisherEntity requireOne(Integer id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }


    public List<PublisherEntity> getAllPublishers(){
        return publisherRepository.findAll();
    }
}
