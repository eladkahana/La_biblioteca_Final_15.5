package comMain.services;

import comMain.entities.RequestsEntity;
import comMain.entities.ResponesEntity;
import comMain.repositories.ResponseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ResponseService {

    @Autowired
    private ResponseRepository responesRepository;

    public Integer save(ResponesEntity vO) {
        ResponesEntity bean = new ResponesEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = responesRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        responesRepository.deleteById(id);
    }

    public void update(Integer id, ResponesEntity vO) {
        ResponesEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        responesRepository.save(bean);
    }

    public ResponesEntity getById(Integer id) {
        ResponesEntity original = requireOne(id);
        return toDTO(original);
    }


    private ResponesEntity toDTO(ResponesEntity original) {
        ResponesEntity bean = new ResponesEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private ResponesEntity requireOne(Integer id) {
        return responesRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    @Transactional(readOnly  = true)
    public void addResponse(Integer requestID, String content) {
        responesRepository.addResponse(requestID, content);
    }


    @Transactional(readOnly = true)
    public List<Object[]> getResponse(){
        return responesRepository.getResponse();
    }
}
