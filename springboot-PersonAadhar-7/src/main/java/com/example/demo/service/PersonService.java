package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Orders;
import com.example.demo.model.Person;
import com.example.demo.repository.AadharRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AadharRepository aadharRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public Person savePerson(Person person)
	{
		return personRepository.save(person);
	}
	
	public Optional<Person> getPersonByAdharnumber(String adharnumber)
	{
     return	personRepository.findByAadharCard_Adharnumber(adharnumber);
	}
	
	public Person updatePerson(Person person,long id) throws UserNotFoundException
	{
		Person person2=personRepository.findById(id).orElseThrow(()->new UserNotFoundException("User Doesn't Exist in Database"));
		person2.setFirstname(person.getFirstname());
		person2.setLastname(person.getLastname());
		person2.setAge(person.getAge());
		
		personRepository.save(person2);
		return person2;
	}
	public void deletePerson(long id) throws UserNotFoundException
	{
		Person person2=personRepository.findById(id).orElseThrow(()->new UserNotFoundException("user Doesn't Exist Database"));
		personRepository.delete(person2);
	}
	
	public Orders saveOrder(Orders orders)
	{
		return orderRepository.save(orders);
	}
	
	public Optional<Orders> getOrderById(long orderid)
	{
		return orderRepository.findById(orderid);
	}
	
	public Orders updateOrder(Orders orders,long orderid) throws UserNotFoundException
	{
		Orders orders2=orderRepository.findById(orderid).orElseThrow(()->new UserNotFoundException("Order Doesn't Exist in Database"));
		orders2.setShippingaddress(orders.getShippingaddress());
		orders2.setTrackingid(orders.getTrackingid());
		
		orderRepository.save(orders2);
		return orders2;
	}
	
	public void deleteOrder(long orderid) throws UserNotFoundException
	{
		Orders orders=orderRepository.findById(orderid).orElseThrow(()->new UserNotFoundException("Order Doesn't Exist In DataBase"));
		orderRepository.deleteById(orderid);
		
	}
}
