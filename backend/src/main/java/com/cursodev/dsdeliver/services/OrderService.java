package com.cursodev.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursodev.dsdeliver.dto.OrderDTO;
import com.cursodev.dsdeliver.entities.Order;
import com.cursodev.dsdeliver.repositories.OrderRepository;


@Service //Classe se torna um componente que pode ser injetado em outros componentes
public class OrderService {
	
	@Autowired //injeção de dependência
	private OrderRepository repository;
	
	@Transactional(readOnly = true) //somente leitura
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findOrderWithProducts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
}
