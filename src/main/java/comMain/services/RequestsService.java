package comMain.services;

import comMain.entities.RequestsEntity;
import comMain.repositories.RequestsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RequestsService {

    @Autowired
    private RequestsRepository requestsRepository;

    public Integer save(RequestsEntity vO) {
        RequestsEntity bean = new RequestsEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = requestsRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        requestsRepository.deleteById(id);
    }

    public void update(Integer id, RequestsEntity vO) {
        RequestsEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        requestsRepository.save(bean);
    }

    public RequestsEntity getById(Integer id) {
        RequestsEntity original = requireOne(id);
        return toDTO(original);
    }


    private RequestsEntity toDTO(RequestsEntity original) {
        RequestsEntity bean = new RequestsEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private RequestsEntity requireOne(Integer id) {
        return requestsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
