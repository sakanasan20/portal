package com.niqdev.app.dto;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@Data
public class PageResponse<T> {
	private List<T> content;
	private int totalPages;
	private long totalElements;
	private int size;
	private int number;
	private boolean first;
	private boolean last;
	private int numberOfElements;
	private boolean empty;
	@JsonDeserialize(using = SortDeserializer.class)
	private Sort sort;
	
//	public PageResponse(Page<T> page) {
//		this.content = page.getContent();
//		this.totalPages = page.getTotalPages();
//		this.totalElements = page.getTotalElements();
//		this.size = page.getSize();
//		this.number = page.getNumber();
//		this.first = page.isFirst();
//		this.last = page.isLast();
//		this.numberOfElements = page.getNumberOfElements();
//		this.empty = page.isEmpty();
//		this.sort = page.getSort().toString();
//	}
}
