package comMain.services;

import comMain.entities.PublisherEntity;
import comMain.entities.ReserveEntity;
import comMain.repositories.ReserveRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReserveService {

    @Autowired
    private ReserveRepository reserveRepository;

    public Integer save(ReserveEntity vO) {
        ReserveEntity bean = new ReserveEntity();
        BeanUtils.copyProperties(vO, bean);
        bean = reserveRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        reserveRepository.deleteById(id);
    }

    public void update(Integer id, ReserveEntity vO) {
        ReserveEntity bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        reserveRepository.save(bean);
    }

    public ReserveEntity getById(Integer id) {
        ReserveEntity original = requireOne(id);
        return toDTO(original);
    }


    private ReserveEntity toDTO(ReserveEntity original) {
        ReserveEntity bean = new ReserveEntity();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private ReserveEntity requireOne(Integer id) {
        return reserveRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Object[]> ReserveDaysLeft(String arrStr) {
        return reserveRepository.ReserveDaysLeft(arrStr);
    }


    public List<ReserveEntity> getAllReserves() {
        return reserveRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Object[]> MonthlyResrvesAmount() {
        return reserveRepository.MonthlyResrvesAmount();
    }

    @Transactional(readOnly = true)
    public List<Object[]> todayResrvesAmount() {
        return reserveRepository.todayResrvesAmount();
    }

    @Transactional(readOnly = true)
    public List<Object[]> displayReservesByDays() {
        return reserveRepository.displayReservesByDays();
    }

    @Transactional(readOnly = true)
    public List<Object[]> displayReservesByHours() {
        return reserveRepository.displayReservesByHours();
    }

    @Transactional(readOnly = true)
    public void AddReserve(String readerIDno, Integer BookID, String dueTo) {
        java.sql.Date sqlDate = java.sql.Date.valueOf(dueTo);

        reserveRepository.AddReserve(readerIDno, BookID, sqlDate);
    }


    @Transactional(readOnly = true)
    public void AddRank(@Param("IDno") String IDno, @Param("copyID") Integer copyID, @Param("rank") Integer rank) {
        reserveRepository.AddRank(IDno, copyID, rank);
    }

    @Transactional(readOnly = true)
    public void AddReturnBook(@Param("copyID") Integer copyID, @Param("readerIDno") String readerIDno) {
        reserveRepository.AddReturnBook(copyID, readerIDno);
    }

    @Transactional(readOnly = true)
    public List<Object[]> getHistoryOfReader(Integer readerID) {
        return reserveRepository.getHistoryOfReader(readerID);
    }

}

