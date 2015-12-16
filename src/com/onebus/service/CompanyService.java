package com.onebus.service;

import java.util.List;

import com.onebus.beans.Company;
import com.onebus.beans.Manager;

public interface CompanyService {

	public void saveCompany(Company company);

	public void updateCompany(Company company);

	public void delete(Company company);

	public List<Company> find(String username);
	
	public List<Company> findById(int id);

}
