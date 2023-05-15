package comMain.services;

import comMain.entities.BookVsAuthorEntity;
import comMain.repositories.BookVSAuthorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class BookVSAuthorService {

    @Autowired
    private BookVSAuthorRepository bookVSAuthorRepository;

    public Integer save(BookVsAuthorEntity vO) {
        BookVsAuthorEntity bean = new BookVsAuthorEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = bookVSAuthorRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        bookVSAuthorRepository.deleteById(id);
    }

    public void update(Integer id, BookVsAuthorEntity vO) {
        BookVsAuthorEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        bookVSAuthorRepository.save(bean);
    }

    public BookVsAuthorEntity getById(Integer id) {
        BookVsAuthorEntity original = requireOne(id);
        return toDTO(original);
    }


    private BookVsAuthorEntity toDTO(BookVsAuthorEntity original) {
        BookVsAuthorEntity bean = new BookVsAuthorEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private BookVsAuthorEntity requireOne(Integer id) {
        return bookVSAuthorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
