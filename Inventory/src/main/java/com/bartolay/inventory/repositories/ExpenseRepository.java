package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Expense;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Integer>{

}
