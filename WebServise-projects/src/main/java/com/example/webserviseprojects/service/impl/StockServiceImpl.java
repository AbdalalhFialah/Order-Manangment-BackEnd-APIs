package com.example.webserviseprojects.service.impl;

import com.example.webserviseprojects.DTO.ProductDto;
import com.example.webserviseprojects.DTO.StockDto;
import com.example.webserviseprojects.entity.Product;
import com.example.webserviseprojects.entity.Stock;
import com.example.webserviseprojects.exception.BlogAPIException;
import com.example.webserviseprojects.exception.ResourceNotFoundException;
import com.example.webserviseprojects.repository.ProductRepository;
import com.example.webserviseprojects.repository.StockRepository;
import com.example.webserviseprojects.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    private StockRepository stockRepository;
    private ProductRepository productRepository;

    public StockServiceImpl(StockRepository stockRepository, ProductRepository productRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    @Override
    public StockDto createStockRecords(StockDto StockDto) {
        Stock stock = mapToEntity(StockDto);
        Stock newStock = stockRepository.save(stock);

        StockDto StockDto1 = mapToDto(newStock);
        return StockDto1;
    }

    @Override
    public List<StockDto> getAllStocks() {
        List<Stock> list = stockRepository.findAll();
        return list.stream().map(stock -> mapToDto(stock)).collect(Collectors.toList());
    }

    @Override
    public StockDto getStockById(long id) {
        Stock stock =  stockRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Stock","Id",id)
        );
        return mapToDto(stock);
    }

    @Override
    public StockDto updateStock(StockDto StockDto, long id) {
        Stock stock = stockRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Stock","Id",id));

        stock.setDateTime(StockDto.getDateTime());
        stock.setQuantity(StockDto.getQuantity());

        Stock updateStock = stockRepository.save(stock);
        return mapToDto(updateStock);

    }

    @Override
    public void deleteStockById(long id) {
        Stock stock = stockRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Stock","Id",id));
        stockRepository.delete(stock);

    }

    // TODO Re-Implemnt CRUD Operation
    @Override
    public StockDto createStock(long productId, StockDto stockDto) {
        Stock stock = mapToEntity(stockDto);
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFoundException("Product","Id",productId)
        );
        stock.setProduct(product);
        Stock newStock = stockRepository.save(stock);

        return mapToDto(newStock);

    }

    @Override
    public List<StockDto> getStockByProductId(long productId) {
        List<Stock> list = stockRepository.findByProductId(productId);

        return list.stream().map(stock -> mapToDto(stock)).collect(Collectors.toList());
    }

    @Override
    public StockDto getStockById(long productId, long stockId) {
        Product product = productRepository.findById(productId).orElseThrow(()->
                new ResourceNotFoundException("Product","Id",productId));
        Stock stock = stockRepository.findById(stockId).orElseThrow(()->
                new ResourceNotFoundException("Product","Id",productId));
        if (!stock.getProduct().getId().equals(product.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Stock Doesn't Belong To Product");

        }
        return mapToDto(stock);
    }

    @Override
    public StockDto updateStock(long productId, long stockId, StockDto stockDto) {

        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", productId));
        Stock stock = stockRepository.findById(stockId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", productId));
        if (!stock.getProduct().getId().equals(product.getId()))
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Stock Doesn't Belong To Product");
        stock.setQuantity(stockDto.getQuantity());
        stock.setDateTime(stockDto.getDateTime());

        Stock updateStock = stockRepository.save(stock);

        return  mapToDto(updateStock);
    }

    @Override
    public void deleteStock(long productId, long stockId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", productId));
        Stock stock = stockRepository.findById(stockId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", productId));
        if (!stock.getProduct().getId().equals(product.getId()))
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Stock Doesn't Belong To Product");
        stockRepository.delete(stock);


    }


    // Convert From  Entity to DTO
    private StockDto mapToDto (Stock stock){
        StockDto stockDto = new StockDto();
        stockDto.setId(stock.getId());
        stockDto.setDateTime(stock.getDateTime());
        stockDto.setQuantity(stock.getQuantity());

        return stockDto;
    }
    // Convert From DTO to Entity
    private Stock mapToEntity (StockDto StockDto){
        Stock stock = new Stock();
        stock.setDateTime(StockDto.getDateTime());
        stock.setQuantity(StockDto.getQuantity());

        return stock;
    }
}
