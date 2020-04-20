package com.anibal.mimacher.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;


import com.anibal.mimacher.item.DTO.ItemIO;
import com.anibal.mimacher.item.DTO.ItemInput;
import com.anibal.mimacher.item.DTO.ItemOutput;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/item")
@Api(tags = "Item")
@CrossOrigin
public class ItemController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class.getSimpleName());
    
    
    private ItemIO itemIO;
    private ItemService itemService;

    @Autowired
    public ItemController(ItemIO itemIO, ItemService itemService) {
        this.itemIO = itemIO;
        this.itemService = itemService;
    }

    @PostMapping({"/",""})
    @ApiOperation(value = "Create Item")
    public ResponseEntity<Item> create(@Valid @RequestBody ItemInput itemInput){
            LOGGER.info("Criando Item");
        Item item = this.itemIO.mapTo(itemInput);
        try {
            this.itemService.create(item);
            LOGGER.info("Item criado");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException();
        }


    }

    @ApiOperation(value =  "Get a Item")
    @GetMapping({"/{id}/", "/{id}"})
    public ItemOutput show(@PathVariable("id") Long id){
        LOGGER.info("Show item by id " +id);
        Item item = this.itemService.show(id);
        ItemOutput output =this.itemIO.mapTo(item);
        return output;
    }

    @ApiOperation(value =  "Get all Item")
    @GetMapping({"/", ""})
    public List<ItemOutput> index(@RequestParam(required = false) String descricao){
        LOGGER.info("Index Itens");
        Type type = new TypeToken<List<ItemOutput>>() {}.getType();
        return this.itemIO.toList(this.itemService.indexFilterByName(descricao), type);
    }

}
