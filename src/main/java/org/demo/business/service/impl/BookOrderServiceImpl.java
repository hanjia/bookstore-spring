/*
 * Created on 3 avr. 2014 ( Time 19:39:42 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package org.demo.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.demo.bean.BookOrder;
import org.demo.bean.jpa.BookOrderEntity;
import java.util.Date;
import java.util.List;
import org.demo.business.service.BookOrderService;
import org.demo.business.service.mapping.BookOrderServiceMapper;
import org.demo.data.repository.jpa.BookOrderJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of BookOrderService
 */
@Component
@Transactional
public class BookOrderServiceImpl implements BookOrderService {

	@Resource
	private BookOrderJpaRepository bookOrderJpaRepository;

	@Resource
	private BookOrderServiceMapper bookOrderServiceMapper;
	
	@Override
	public BookOrder findById(Integer id) {
		BookOrderEntity bookOrderEntity = bookOrderJpaRepository.findOne(id);
		return bookOrderServiceMapper.mapBookOrderEntityToBookOrder(bookOrderEntity);
	}

	@Override
	public List<BookOrder> findAll() {
		Iterable<BookOrderEntity> entities = bookOrderJpaRepository.findAll();
		List<BookOrder> beans = new ArrayList<BookOrder>();
		for(BookOrderEntity bookOrderEntity : entities) {
			beans.add(bookOrderServiceMapper.mapBookOrderEntityToBookOrder(bookOrderEntity));
		}
		return beans;
	}

	@Override
	public BookOrder save(BookOrder bookOrder) {
		return update(bookOrder) ;
	}

	@Override
	public BookOrder create(BookOrder bookOrder) {
		BookOrderEntity bookOrderEntity = bookOrderJpaRepository.findOne(bookOrder.getId());
		if( bookOrderEntity != null ) {
			throw new IllegalStateException("already.exists");
		}
		bookOrderEntity = new BookOrderEntity();
		bookOrderServiceMapper.mapBookOrderToBookOrderEntity(bookOrder, bookOrderEntity);
		BookOrderEntity bookOrderEntitySaved = bookOrderJpaRepository.save(bookOrderEntity);
		return bookOrderServiceMapper.mapBookOrderEntityToBookOrder(bookOrderEntitySaved);
	}

	@Override
	public BookOrder update(BookOrder bookOrder) {
		BookOrderEntity bookOrderEntity = bookOrderJpaRepository.findOne(bookOrder.getId());
		bookOrderServiceMapper.mapBookOrderToBookOrderEntity(bookOrder, bookOrderEntity);
		BookOrderEntity bookOrderEntitySaved = bookOrderJpaRepository.save(bookOrderEntity);
		return bookOrderServiceMapper.mapBookOrderEntityToBookOrder(bookOrderEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		bookOrderJpaRepository.delete(id);
	}

	public BookOrderJpaRepository getBookOrderJpaRepository() {
		return bookOrderJpaRepository;
	}

	public void setBookOrderJpaRepository(BookOrderJpaRepository bookOrderJpaRepository) {
		this.bookOrderJpaRepository = bookOrderJpaRepository;
	}

	public BookOrderServiceMapper getBookOrderServiceMapper() {
		return bookOrderServiceMapper;
	}

	public void setBookOrderServiceMapper(BookOrderServiceMapper bookOrderServiceMapper) {
		this.bookOrderServiceMapper = bookOrderServiceMapper;
	}

}