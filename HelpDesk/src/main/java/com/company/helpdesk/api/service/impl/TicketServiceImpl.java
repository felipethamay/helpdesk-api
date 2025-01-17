package com.company.helpdesk.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.helpdesk.api.entity.ChangeStatus;
import com.company.helpdesk.api.entity.Ticket;
import com.company.helpdesk.api.repository.ChangeStatusRepository;
import com.company.helpdesk.api.repository.TicketRepository;
import com.company.helpdesk.api.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	private ChangeStatusRepository changeStatusRepository;
	
	@Override
	public Ticket createOrUpdate(Ticket ticket) {
		return this.ticketRepository.save(ticket);
	}

	@Override
	public Ticket findById(String id) {
		return this.ticketRepository.findOne(id);
	}

	@Override
	public void delete(String id) {
		this.ticketRepository.deleteById(id);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Ticket> listTicket(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findAll(pages);
	}

	@Override
	public ChangeStatus createChangeStatus(ChangeStatus changeStatus) {
		return this.createChangeStatus(changeStatus);
	}

	@Override
	public Iterable<ChangeStatus> listChangeStatus(String ticketId) {
		return this.changeStatusRepository.findByTicketIdOrderByDateChangeStatusDesc(ticketId);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Ticket> findByCurrentUser(int page, int count, String userId) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findByUserIdOrderByDateDesc(pages, userId);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Ticket> findByParameters(int page, int count, String title, String status, String priority) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findByTitleIgnoreCaseContainingAndStatusAndPriorityOrderByDateDesc(title, status, priority, pages);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Page<Ticket> findByParametersAndCurrentUser(int page, int count, String title, String status,
			String priority) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findByTitleIgnoreCaseContainingAndStatusAndPriorityAndUserIdOrderByDateDesc(title, status, priority, pages);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Ticket> findByNumber(int page, int count, Integer number) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findByNumber(number, pages);
	}

	@Override
	public Iterable<Ticket> findAll() {
		return this.ticketRepository.findAll();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Ticket> findByParameterAndAssignedUser(int page, int count, String title, String status,
			String priority, String assigned) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findByTitleIgnoreCaseContainingAndStatusAndPriorityAndAssignedUserIdOrderByDateDesc(title, status, priority, pages);
	}

}
