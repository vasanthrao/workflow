package com.metaverse.workflow.expenditure.controller;

import com.metaverse.workflow.expenditure.service.HeadOfExpenseService;
import com.metaverse.workflow.model.HeadOfExpense;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class HeadOfExpenseController {

    private final HeadOfExpenseService headOfExpenseService;

    @GetMapping
    public List<HeadOfExpense> getAllExpenses() {
        return headOfExpenseService.getAllExpenses();
    }
}
