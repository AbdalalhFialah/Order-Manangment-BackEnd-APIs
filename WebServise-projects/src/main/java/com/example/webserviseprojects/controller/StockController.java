package com.example.webserviseprojects.controller;

import com.example.webserviseprojects.DTO.ProductDto;
import com.example.webserviseprojects.DTO.StockDto;
import com.example.webserviseprojects.entity.Order;
import com.example.webserviseprojects.entity.Stock;
import com.example.webserviseprojects.service.impl.StockServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.tomcat.jni.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(description = "CRUD REST APIs for Stock Resources")

public class StockController {

    private StockServiceImpl stockService;

    public StockController(StockServiceImpl stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/test")
    public List<StockDto> getStocks() {
        List<StockDto> stockList = new ArrayList<StockDto>();
        for (int i = 0; i < 2; i++)
            stockList.add(new StockDto(i,i*3, LocalDateTime.now()));

        return stockList;
    }
    @GetMapping("/stocks")
    public List<StockDto> getALlStocks (){
        return stockService.getAllStocks();
    }


    @PostMapping("/stocks")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Stock.class, responseContainer = "Response") })

    public ResponseEntity<StockDto> createStocks (@Valid @RequestBody StockDto stockDto){

        return new ResponseEntity<>(stockService.createStockRecords(stockDto), HttpStatus.CREATED);
    }
    @GetMapping("/stocks/{id}")
    public ResponseEntity<StockDto> getStocksById (@PathVariable(name = "id") long id){
        return ResponseEntity.ok(stockService.getStockById(id));
    }
    @PutMapping("/stocks/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Stock.class, responseContainer = "Response") })

    public ResponseEntity<StockDto> updateStock (@Valid @RequestBody StockDto stockDto , @PathVariable (name ="id") long id){
        StockDto stockDto1 = stockService.updateStock(stockDto,id);
        return new ResponseEntity<>(stockDto,HttpStatus.OK);
    }
    @DeleteMapping("/stocks/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Stock.class, responseContainer = "Response") })
    public ResponseEntity<String> deleteStock (@PathVariable(name = "id") long id){
        stockService.deleteStockById(id);
        return new ResponseEntity<>("Stock Product Was Done Successfully",HttpStatus.OK);
    }


    // TODO Re-Implement CRUD Operation in Controller

    @PostMapping ("/products/{productId}/stocks")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Stock.class, responseContainer = "Response") })
    ResponseEntity<StockDto> createStock (@Valid @RequestBody StockDto stockDto ,
                                        @PathVariable(value = "productId") long productId ){
        return new ResponseEntity<>(stockService.createStock(productId,stockDto),HttpStatus.CREATED);
    }

    @GetMapping("/products/{productId}/stocks")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Stock.class, responseContainer = "Response") })
    public List<StockDto>getStockByProductId (@PathVariable(value = "productId") long productId){
        return stockService.getStockByProductId(productId);
    }
    @GetMapping("/products/{productId}/stocks/{stockId}")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Stock.class, responseContainer = "Response") })
    public ResponseEntity<StockDto>getStockById (@PathVariable(value = "productId") long productId ,
                                                 @PathVariable (value ="stockId") long stockId){
        StockDto stockDto = stockService.getStockById(productId,stockId);
        return  new ResponseEntity<>(stockDto , HttpStatus.OK);

    }
    @PutMapping("/products/{productId}/stocks/{stockId}")
    @PreAuthorize("hasRole('ADMIN')")

    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Stock.class, responseContainer = "Response") })
    public ResponseEntity <StockDto> updateStock (@PathVariable(value = "productId") long productId ,
                                                  @PathVariable (value ="stockId") long stockId ,
                                                  @Valid @RequestBody StockDto stockDto){
        StockDto newUpdateStock = stockService.updateStock(productId,stockId,stockDto);
        return  new ResponseEntity<>(newUpdateStock,HttpStatus.OK);

    }
    @DeleteMapping("/products/{productId}/stocks/{stockId}")
    @PreAuthorize("hasRole('ADMIN')")

    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error "),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message="Forbidden"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Stock.class, responseContainer = "Response") })

    public ResponseEntity<String > deleteStockById (@PathVariable(value = "productId") long productId ,
                                                    @PathVariable (value ="stockId") long stockId){
        stockService.deleteStock(productId,stockId);
        return new ResponseEntity<>("Stock Delete Was Done successfuly",HttpStatus.OK);
    }


}
