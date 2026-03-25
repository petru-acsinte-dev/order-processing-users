package com.orderprocessing.users.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.orderprocessing.common.constants.Constants;

@Component
@ConfigurationProperties(prefix = "com.orderprocessing.users")
public class UserProps {

	private int pageSize;

	private int maxPageSize;

	private String defaultSortAttribute;

	/**
	 * @return The page size for listing users.
	 */
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return Maximum customizable limit for a page size.
	 * Cannot exceed {@link Constants.PAGE_SIZE_HARD_LIMIT}
	 */
	public int getMaxPageSize() {
		return maxPageSize;
	}

	public void setMaxPageSize(int maxPageSize) {
		this.maxPageSize = maxPageSize;
	}

	/**
	 * @return The attribute to sort by
	 */
	public String getDefaultSortAttribute() {
		return defaultSortAttribute;
	}

	public void setDefaultSortAttribute(String defaultSortAttribute) {
		this.defaultSortAttribute = defaultSortAttribute;
	}

}
