package org.brapi.test.BrAPITestServer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import io.swagger.model.Metadata;
import io.swagger.model.MetadataPagination;

public class PagingUtility {
	public static void calculateMetaData(Metadata metaData) {
		int totalCount = metaData.getPagination().getTotalCount();
		int pageSize = metaData.getPagination().getPageSize();
		metaData.getPagination().setTotalPages((totalCount / pageSize) + 1);
	}

	public static Pageable getPageRequest(Metadata metaData) {
		if (metaData == null) {
			metaData = new Metadata();
		}
		if (metaData.getPagination() == null) {
			metaData.setPagination(new MetadataPagination());
		}
		return getPageRequest(metaData, null);
	}

	public static Pageable getPageRequest(Metadata metaData, Sort sort) {
		int page = 0;
		if (metaData.getPagination().getCurrentPage() != null && metaData.getPagination().getCurrentPage() >= 0) {
			page = metaData.getPagination().getCurrentPage();
		}
		
		int pageSize = 1000;
		if (metaData.getPagination().getPageSize() != null && metaData.getPagination().getPageSize() > 0) {
			pageSize = metaData.getPagination().getPageSize();
		}
		
		if (sort == null) {
			return PageRequest.of(page, pageSize);
		}
		return PageRequest.of(page, pageSize, sort);
	}

	public static void calculateMetaData(Metadata metaData, Page<?> page) {
		// metaData.getPagination().setPageSize(page.getNumberOfElements());
		metaData.getPagination().setCurrentPage(page.getNumber());
		metaData.getPagination().setTotalCount((int) page.getTotalElements());
		metaData.getPagination().setTotalPages((int) page.getTotalPages());
	}
}
