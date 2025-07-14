package br.edu.ufop.web.ticket.sales.controllers;

import br.edu.ufop.web.ticket.sales.dtos.events.CreateSalesDTO;
import br.edu.ufop.web.ticket.sales.dtos.events.DeleteSalesDTO;
import br.edu.ufop.web.ticket.sales.dtos.events.UpdateSalesDTO;
import br.edu.ufop.web.ticket.sales.models.SalesModel;

import br.edu.ufop.web.ticket.sales.service.SalesService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/sales")
public class salesController {

    private final SalesService salesService;

    @PostMapping
    public ResponseEntity<CreateSalesDTO> saveSales(@RequestBody CreateSalesDTO createSalesDTO) {

        CreateSalesDTO salesDTO = salesService.saveSales(createSalesDTO);
        if (salesDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(salesDTO);



    }

    @GetMapping
    public ResponseEntity<List<CreateSalesDTO>> getAllSales() {

        List<CreateSalesDTO> salesList = salesService.getAllSales();

        if (salesList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(salesList);
    }

    @GetMapping("/{salesId}")
    public ResponseEntity<CreateSalesDTO> getSalesById(@PathVariable String salesId) {

        if (salesId == null || salesId.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        CreateSalesDTO salesDTO = salesService.getSalesById(salesId);

        if (salesDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(salesDTO);
    }
    @DeleteMapping
    public ResponseEntity<Object> deleteSalesById(@RequestBody DeleteSalesDTO deleteSalesDTO) {

        if (deleteSalesDTO == null || deleteSalesDTO.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        salesService.deleteSalesById(deleteSalesDTO);

        return ResponseEntity.ok("Sales deleted successfully");
    }

    @PutMapping
    public ResponseEntity<UpdateSalesDTO> updateSales(@RequestBody UpdateSalesDTO updateSalesDTO) {
        if (updateSalesDTO == null || updateSalesDTO.getUuid() == null) {
            return ResponseEntity.badRequest().build();
        }

        UpdateSalesDTO updatedSales = salesService.updateSales(updateSalesDTO);

        if (updatedSales == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedSales);
    }
}
