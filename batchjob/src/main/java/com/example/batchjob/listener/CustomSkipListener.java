package com.example.batchjob.listener;

import com.example.batchjob.model.Transaction;
import org.springframework.batch.core.SkipListener;

public class CustomSkipListener implements SkipListener<Transaction, Transaction> {

    @Override
    public void onSkipInRead(Throwable t) {
        System.err.println("Skipped during read: " + t.getMessage());
    }

    @Override
    public void onSkipInWrite(Transaction item, Throwable t) {
        System.err.println("Skipped during write: " + item + " - " + t.getMessage());
    }

    @Override
    public void onSkipInProcess(Transaction item, Throwable t) {
        System.err.println("Skipped during process: " + item + " - " + t.getMessage());
    }
}