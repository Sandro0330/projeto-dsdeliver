package com.cursodev.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursodev.dsdeliver.dto.OrderDTO;
import com.cursodev.dsdeliver.dto.ProductDTO;
import com.cursodev.dsdeliver.entities.Order;
import com.cursodev.dsdeliver.entities.OrderStatus;
import com.cursodev.dsdeliver.entities.Product;
import com.cursodev.dsdeliver.repositories.OrderRepository;
import com.cursodev.dsdeliver.repositories.ProductRepository;


@Service //Classe se torna um componente que pode ser injetado em outros componentes
public class OrderService {
	
	@Autowired //injeção de dependência
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true) //somente leitura
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findOrderWithProducts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(), OrderStatus.PENDING);
		
		for(ProductDTO p : dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		order = repository.save(order);
		return new OrderDTO(order);
	}
	
	@Transactional
	public OrderDTO setDelivered(Long id) {
		Order order = repository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);//pedido entregue
		order = repository.save(order);
		return new OrderDTO(order);
	}
}
