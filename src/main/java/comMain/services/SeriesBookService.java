package comMain.services;

import comMain.entities.SeriesBookEntity;
import comMain.repositories.SeriesBookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SeriesBookService {

    @Autowired
    private SeriesBookRepository seriesBookRepository;

    public Integer save(SeriesBookEntity vO) {
        SeriesBookEntity bean = new SeriesBookEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = seriesBookRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        seriesBookRepository.deleteById(id);
    }

    public void update(Integer id, SeriesBookEntity vO) {
        SeriesBookEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        seriesBookRepository.save(bean);
    }

    public SeriesBookEntity getById(Integer id) {
        SeriesBookEntity original = requireOne(id);
        return toDTO(original);
    }


    private SeriesBookEntity toDTO(SeriesBookEntity original) {
        SeriesBookEntity bean = new SeriesBookEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private SeriesBookEntity requireOne(Integer id) {
        return seriesBookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<SeriesBookEntity> getAllSeries(){
        return seriesBookRepository.findAll();
    }

}
