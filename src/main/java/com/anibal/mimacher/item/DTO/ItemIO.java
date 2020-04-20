package com.anibal.mimacher.item.DTO;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.anibal.mimacher.item.Item;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import java.lang.reflect.Type;


@Component("itemIO")
public class ItemIO {

	private ModelMapper modelMapper;
	
	final Converter<Item, ItemOutput> itemOutputConverter = new Converter<Item, ItemOutput>() {

		@Override
		public ItemOutput convert(MappingContext<Item, ItemOutput> context){
			Item item = context.getSource();

			return toItemOutput(item);
		}
	};
	
	final Converter<ItemInput, Item> itemInputConverter = new Converter<ItemInput, Item>() {

		@Override
		public Item convert(MappingContext<ItemInput, Item> context) {

			ItemInput input = context.getSource();

			Item item = new Item();
			item.setDescricao(input.getDescricao());
			item.setTipo(input.getTipo());
			return item;
		}

	};
	
	private ItemOutput toItemOutput(Item item){
		ItemOutput itemOutput = new ItemOutput();
		itemOutput.setId(item.getId());
		itemOutput.setDescricao(item.getDescricao());
		itemOutput.setTipo(item.getTipo());
		return itemOutput;
	}
	
	public ItemIO() {
		this.modelMapper = new ModelMapper();
		this.modelMapper.addConverter(itemInputConverter);
		this.modelMapper.addConverter(itemOutputConverter);
	}
	
	public Item mapTo(ItemInput itemInput){
		return this.modelMapper.map(itemInput, Item.class);
	}

	public ItemOutput mapTo(Item item){
		return modelMapper.map(item, ItemOutput.class);
	}

	public List<ItemOutput> toList(List<Item> itens, Type type){
		return this.modelMapper.map(itens, type);
	}
}
