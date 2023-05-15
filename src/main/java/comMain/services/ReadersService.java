package comMain.services;

import comMain.entities.ReadersEntity;
import comMain.repositories.ReadersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReadersService {

    @Autowired
    private ReadersRepository readersRepository;


    public Integer save(ReadersEntity vO) {
        ReadersEntity bean = new ReadersEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = readersRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        readersRepository.deleteById(id);
    }

    public void update(Integer id, ReadersEntity vO) {
        ReadersEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        readersRepository.save(bean);
    }

    public ReadersEntity getById(Integer id) {
        ReadersEntity original = requireOne(id);
        return toDTO(original);
    }


    private ReadersEntity toDTO(ReadersEntity original) {
        ReadersEntity bean = new ReadersEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private ReadersEntity requireOne(Integer id) {
        return readersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }


    public List<ReadersEntity> getAllReaders(){
        return readersRepository.findAll();
    }
}
