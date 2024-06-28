package itu.examen.projetnaina.service;

import itu.examen.projetnaina.model.Stock;
import itu.examen.projetnaina.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    public Stock findById(Long id) {
        return stockRepository.findById(id).orElse(null);
    }

    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public void delete(Long id) {
        stockRepository.deleteById(id);
    }

    public void updateQuantiteByIdlivre(int newStock, Long id) {
        stockRepository.updateQuantiteByIdlivre(newStock, id);
    }

    public Stock findByIdlivre(Long id) {
        return stockRepository.findByIdlivre(id);
    }
}

