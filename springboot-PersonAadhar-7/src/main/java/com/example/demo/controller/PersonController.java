package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Orders;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;
	
	
	@PostMapping("/save")
	public ResponseEntity<Person> savePerson(@RequestBody  Person person)
	{
		return new ResponseEntity<Person>(personService.savePerson(person),HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{adharnumber}")
	public ResponseEntity<Person> getPersonByAdharnumber(@PathVariable   String adharnumber)
	{
		Optional<Person> person=personService.getPersonByAdharnumber(adharnumber);
		return person.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
	}
	 
	@PutMapping("/update/{id}")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person,@PathVariable  long id) throws UserNotFoundException
	{
		return new ResponseEntity<Person>(personService.updatePerson(person, id),HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePerson(@PathVariable long id) throws UserNotFoundException
	{
		personService.deletePerson(id);
		return new ResponseEntity<String>("User is Deleted From Database",HttpStatus.OK);
	}
	
	@PostMapping("/saves")
	public ResponseEntity<Orders> saveOrder(@RequestBody  Orders orders)
	{
		return new ResponseEntity<Orders>(personService.saveOrder(orders),HttpStatus.CREATED);
	}
	@GetMapping("/getting/{orderid}")
	public ResponseEntity<Orders> getOrderById(@PathVariable(name="orderid") long orderid)
	{
		Optional<Orders> optional=personService.getOrderById(orderid);
		return optional.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
	}
	
	@PutMapping("/updates/{orderid}")
	public ResponseEntity<Orders> updateOrder(@RequestBody Orders orders,@PathVariable  long orderid) throws UserNotFoundException
	{
		return new ResponseEntity<Orders>(personService.updateOrder(orders, orderid), HttpStatus.OK);
	}
	
	@DeleteMapping("/deletes/{orderid}")
	public ResponseEntity<String> deleteOrder(@PathVariable  long orderid) throws UserNotFoundException
	{
		personService.deleteOrder(orderid);
		return new ResponseEntity<String>("Order Deleted From DataBase",HttpStatus.OK);
	}
	
}
