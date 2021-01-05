package com.cursodev.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursodev.dsdeliver.dto.ProductDTO;
import com.cursodev.dsdeliver.entities.Product;
import com.cursodev.dsdeliver.repositories.ProductRepository;


@Service //Classe se torna um componente que pode ser injetado em outros componentes
public class ProductService {
	
	@Autowired //injeção de dependência
	private ProductRepository repository;
	
	@Transactional(readOnly = true) //somente leitura
	public List<ProductDTO> findAll() {
		List<Product> list = repository.findAllByOrderByNameAsc();
		return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
	}
}
