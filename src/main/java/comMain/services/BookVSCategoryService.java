package comMain.services;

import comMain.entities.BookVsCategoryEntity;
import comMain.repositories.BookVSCategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class BookVSCategoryService {

    @Autowired
    private BookVSCategoryRepository bookVSCategoryRepository;

    public Integer save(BookVsCategoryEntity vO) {
        BookVsCategoryEntity bean = new BookVsCategoryEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = bookVSCategoryRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        bookVSCategoryRepository.deleteById(id);
    }

    public void update(Integer id, BookVsCategoryEntity vO) {
        BookVsCategoryEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        bookVSCategoryRepository.save(bean);
    }

    public BookVsCategoryEntity getById(Integer id) {
        BookVsCategoryEntity original = requireOne(id);
        return toDTO(original);
    }


    private BookVsCategoryEntity toDTO(BookVsCategoryEntity original) {
        BookVsCategoryEntity bean = new BookVsCategoryEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private BookVsCategoryEntity requireOne(Integer id) {
        return bookVSCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
