package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.expenditure.repository.HeadOfExpenseRepository;
import com.metaverse.workflow.model.HeadOfExpense;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeadOfExpenseService {

    private final HeadOfExpenseRepository headOfExpenseRepository;

    public List<HeadOfExpense> getAllExpenses() {
        return headOfExpenseRepository.findAll();
    }
}
